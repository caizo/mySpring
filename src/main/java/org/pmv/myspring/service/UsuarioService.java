package org.pmv.myspring.service;

import lombok.RequiredArgsConstructor;
import org.pmv.myspring.entities.Role;
import org.pmv.myspring.entities.Usuario;
import org.pmv.myspring.exception.errors.UsuarioNotFoundException;
import org.pmv.myspring.jwt.JwtUtil;
import org.pmv.myspring.repo.UsuarioRepository;
import org.pmv.myspring.request.LoginRequest;
import org.pmv.myspring.request.RegistroRequest;
import org.pmv.myspring.response.AuthResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UsuarioService {

    private final UsuarioRepository usuarioRepository;
    private final JwtUtil jwtUtil;
    private final PasswordEncoder passwordEncoder;
    private final TokenService tokenService;

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

    public AuthResponse registro(RegistroRequest registroRequest) {

        Usuario usuario = Usuario.builder()
                .email(registroRequest.getEmail())
                .username(registroRequest.getUsername())
                .role(Role.CLIENTE)
                .password(passwordEncoder.encode(registroRequest.getPassword())).build();
        this.usuarioRepository.save(usuario);

        return AuthResponse.builder().jwt(this.jwtUtil.generateToken(usuario)).build();
    }


    public AuthResponse login(LoginRequest loginRequest) throws UsuarioNotFoundException {
        Usuario usuario = this.usuarioRepository.findByUsername(loginRequest.getUsername())
                .orElseThrow(() -> new UsuarioNotFoundException("Usuario no encontrado"));

        return AuthResponse.builder().jwt(this.jwtUtil.generateToken(usuario)).build();
    }

    public void logout(String token) {
        this.tokenService.invalidateToken(token);
    }
}