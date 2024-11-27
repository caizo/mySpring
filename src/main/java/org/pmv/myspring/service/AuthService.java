package org.pmv.myspring.service;

import lombok.RequiredArgsConstructor;
import org.pmv.myspring.dto.UsuarioDTO;
import org.pmv.myspring.entities.Usuario;
import org.pmv.myspring.exception.errors.UsuarioNotFoundException;
import org.pmv.myspring.jwt.JwtUtil;
import org.pmv.myspring.repo.UsuarioRepository;
import org.pmv.myspring.request.LoginRequest;
import org.pmv.myspring.request.RegistroRequest;
import org.pmv.myspring.response.AuthResponse;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthService {

    private final UsuarioRepository usuarioRepository;
    private final PasswordEncoder passwordEncoder;
    private final TokenService tokenService;
    private final EmailService emailService;
    private final JwtUtil jwtUtil;

    public AuthResponse login(LoginRequest loginRequest) throws UsuarioNotFoundException {
        Usuario usuario = this.usuarioRepository.findByUsername(loginRequest.getUsername())
                .orElseThrow(() -> new UsuarioNotFoundException("Usuario no encontrado"));

        return AuthResponse.builder().jwt(this.jwtUtil.generateToken(usuario)).build();

    }

    public void logout(String token) {
        this.tokenService.invalidateToken(token);
    }

    public UsuarioDTO registroDeUsuario(RegistroRequest request) {
        Usuario usuario = guardarUsuario(request);
        this.emailService.enviarEmailConfirmacion(request, this.jwtUtil.generateToken(usuario));

        return UsuarioDTO.builder()
                .id(usuario.getId())
                .email(usuario.getEmail())
                .username(usuario.getUsername())
                .role(usuario.getRole())
                .build();
    }

    private Usuario guardarUsuario(RegistroRequest registroRequest) {
        Usuario usuario = Usuario.builder()
                .email(registroRequest.getEmail())
                .username(registroRequest.getUsername())
                .role(registroRequest.getRole())
                .password(passwordEncoder.encode(registroRequest.getPassword())).build();

        this.usuarioRepository.save(usuario);
        return usuario;

    }
}
