package org.pmv.myspring.service;

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
public class UsuarioService {

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private JwtUtil jwtUtil;

    @Autowired
    private PasswordEncoder passwordEncoder;

    /**
     * Guarda un usuario en la base de datos
     *
     * @param usuario
     * @return
     */
    public Usuario guardarUsuario(Usuario usuario) {
        return usuarioRepository.save(usuario);
    }

    /**
     * Busca un usuario por su id
     *
     * @param id
     * @return
     * @throws UsuarioNotFoundException
     */
    public Usuario buscarUsuarioPorId(Long id) throws UsuarioNotFoundException {
        return usuarioRepository.findById(id).orElseThrow(() -> new UsuarioNotFoundException("Usuario no encontrado"));
    }

    /**
     * Actualiza un usuario en la base de datos
     *
     * @param usuario
     * @return
     */
    public Usuario actualizarUsuario(Usuario usuario) {
        return usuarioRepository.save(usuario);
    }

    /**
     * Elimina un usuario de la base de datos
     *
     * @param id
     */
    public void eliminarUsuario(Long id) {
        usuarioRepository.deleteById(id);
    }

    /**
     * Busca todos los usuarios en la base de datos
     *
     * @return
     */
    public Iterable<Usuario> buscarUsuarios() {
        return usuarioRepository.findAll();
    }

    /**
     * Busca un usuario por su nombre
     *
     * @param registroRequest
     * @return
     * @throws UsuarioNotFoundException
     */
    public AuthResponse registro(RegistroRequest registroRequest) {

        Usuario usuario = Usuario.builder()
                .email(registroRequest.getEmail())
                .username(registroRequest.getUsername())
                .role(Role.CLIENTE)
                .password(passwordEncoder.encode(registroRequest.getPassword())).build();
        this.usuarioRepository.save(usuario);

        return AuthResponse.builder().jwt(this.jwtUtil.generateToken(usuario.getUsername())).build();
    }

    /**
     * Busca un usuario por su nombre y password
     *
     * @param loginRequest
     * @return
     * @throws UsuarioNotFoundException
     */
    public AuthResponse login(LoginRequest loginRequest) throws UsuarioNotFoundException {
        Usuario usuario = this.usuarioRepository.findByUsername(loginRequest.getUsername())
                .orElseThrow(() -> new UsuarioNotFoundException("Usuario no encontrado"));

        return AuthResponse.builder().jwt(this.jwtUtil.generateToken(usuario.getUsername())).build();
    }
}