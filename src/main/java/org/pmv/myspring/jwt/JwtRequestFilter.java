package org.pmv.myspring.jwt;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.pmv.myspring.service.TokenService;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@Component
@RequiredArgsConstructor
public class JwtRequestFilter extends OncePerRequestFilter {

    public static final String HEADER_AUTHORIZATION = "Authorization";

    private final UserDetailsService userDetailsService;
    private final JwtUtil jwtUtil;
    private final TokenService tokenService;

    /**
     * Método que se ejecuta en cada petición para comprobar si el token es válido
     *
     * @param request petición
     * @param response respuesta
     * @param chain cadena de filtros
     * @throws ServletException si ocurre un error en el procesamiento de la solicitud
     * @throws IOException si ocurre un error de entrada/salida durante el procesamiento de la solicitud
     */
    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain chain)
            throws ServletException, IOException {

        final String authHeader = request.getHeader(HEADER_AUTHORIZATION);
        String jwt = jwtFrom(authHeader);

        String username = null;
        if (jwt != null) {
            username = jwtUtil.extractUsername(jwt);

        }

        if (username != null && usuarioNoAutenticado()) {
            UserDetails userDetails = userDetailsService.loadUserByUsername(username);
            if (jwtUtil.validateToken(jwt, userDetails.getUsername()) && !tokenService.isTokenInvalid(jwt)) {
                establecerAutenticacion(request, userDetails);
            }
        }
        chain.doFilter(request, response);
    }

    /**
     * Comprueba si el usuario no está autenticado
     *
     * @return true si el usuario no está autenticado, false en caso contrario
     */
    private boolean usuarioNoAutenticado() {
        return SecurityContextHolder.getContext().getAuthentication() == null;
    }

    /**
     * Extrae el token de la cabecera Authorization
     *
     * @param authHeader token
     * @return token sin la palabra Bearer
     */
    private String jwtFrom(String authHeader) {
        if (authHeader != null && authHeader.startsWith("Bearer ")) {
            return authHeader.substring(7);
        }
        return null;
    }

    /**
     * Establece la autenticación en el contexto de seguridad
     *
     * @param request petición
     * @param userDetails detalles del usuario
     */
    private void establecerAutenticacion(HttpServletRequest request, UserDetails userDetails) {
        UsernamePasswordAuthenticationToken authToken = new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());

        authToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));

        SecurityContextHolder.getContext().setAuthentication(authToken);
    }
}