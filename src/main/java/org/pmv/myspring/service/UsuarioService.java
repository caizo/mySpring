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

import java.util.List;

@Service
@RequiredArgsConstructor
public class UsuarioService {

    private final UsuarioRepository usuarioRepository;
    private final JwtUtil jwtUtil;
    private final PasswordEncoder passwordEncoder;
    private final TokenService tokenService;
    private final EmailService emailService;


    public Usuario guardarUsuario(Usuario usuario) {
        return usuarioRepository.save(usuario);
    }

    public Usuario buscarUsuarioPorId(Long id) throws UsuarioNotFoundException {
        return usuarioRepository.findById(id).orElseThrow(() -> new UsuarioNotFoundException("Usuario no encontrado"));
    }

    public Usuario actualizarUsuario(Usuario usuario) {
        return usuarioRepository.save(usuario);
    }

    public void eliminarUsuario(Long id) {
        usuarioRepository.deleteById(id);
    }

    public Iterable<Usuario> buscarUsuarios() {
        return usuarioRepository.findAll();
    }

    public void registroDeUsuario(RegistroRequest request) {

        Usuario usuario = guardarUsuario(request);

        enviarEmail(request, this.jwtUtil.generateToken(usuario));

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

    private void enviarEmail(RegistroRequest registroRequest, String jwt) {
        String subject = "Confirmación de registro de usuario";
        String body = "<html><body>"
                + "<h1>Usuario registrado</h1>"
                + "<p>Usuario registrado: " + registroRequest.getUsername() + "</p>"
                + "<p>Token: " + jwt + "</p>"
                + "</body></html>";

        this.emailService.sendEmail(registroRequest.getEmail(), subject, body);
    }

    public AuthResponse login(LoginRequest loginRequest) throws UsuarioNotFoundException {
        Usuario usuario = this.usuarioRepository.findByUsername(loginRequest.getUsername())
                .orElseThrow(() -> new UsuarioNotFoundException("Usuario no encontrado"));

        return AuthResponse.builder().jwt(this.jwtUtil.generateToken(usuario)).build();
    }

    public void logout(String token) {
        this.tokenService.invalidateToken(token);
    }

    public List<UsuarioDTO> findClientesByUsername(String username) throws UsuarioNotFoundException {
        return usuarioRepository.findClientesByUsername(username)
                .orElseThrow(() -> new UsuarioNotFoundException("No se encontraron usuarios"));
    }

}