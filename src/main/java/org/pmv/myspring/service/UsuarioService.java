package org.pmv.myspring.service;

import org.pmv.myspring.entities.Role;
import org.pmv.myspring.entities.Usuario;
import org.pmv.myspring.exception.errors.UsuarioNotFoundException;
import org.pmv.myspring.repo.UsuarioRepository;
import org.pmv.myspring.request.RegistroRequest;
import org.pmv.myspring.response.AuthResponse;
import org.pmv.myspring.jwt.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UsuarioService {

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private JwtUtil jwtUtil;

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
                .nombre(registroRequest.getNombre())
                .role(Role.CLIENTE)
                .password(registroRequest.getPassword()).build();
        this.usuarioRepository.save(usuario);

        return AuthResponse.builder().jwt(this.jwtUtil.generateToken(usuario.getNombre())).build();
    }
}