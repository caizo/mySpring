package org.pmv.myspring.service;

import lombok.RequiredArgsConstructor;
import org.pmv.myspring.dto.UsuarioDTO;
import org.pmv.myspring.entities.Usuario;
import org.pmv.myspring.exception.errors.UsuarioNotFoundException;
import org.pmv.myspring.repo.UsuarioRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UsuarioService {

    private final UsuarioRepository usuarioRepository;
    private final PasswordEncoder passwordEncoder;


    public UsuarioDTO guardarUsuario(UsuarioDTO usuarioDTO) {
        Usuario usuario = Usuario.builder()
                .email(usuarioDTO.getEmail())
                .username(usuarioDTO.getUsername())
                .role(usuarioDTO.getRole())
                .password(passwordEncoder.encode(usuarioDTO.getPassword())).build();

        Usuario usuarioGuardado = usuarioRepository.save(usuario);

        return UsuarioDTO.builder()
                .id(usuarioGuardado.getId())
                .email(usuarioGuardado.getEmail())
                .username(usuarioGuardado.getUsername())
                .role(usuarioGuardado.getRole())
                .build();

    }

    public Usuario buscarUsuarioPorId(Long id) throws UsuarioNotFoundException {
        return usuarioRepository.findById(id).orElseThrow(() -> new UsuarioNotFoundException("Usuario no encontrado"));

    }

    public UsuarioDTO actualizarUsuario(UsuarioDTO usuarioDTO) throws UsuarioNotFoundException {
        Usuario usuarioSinModificar = usuarioRepository.findById(usuarioDTO.getId()).orElseThrow(() -> new UsuarioNotFoundException("Usuario no encontrado"));

        usuarioSinModificar.setUsername(usuarioDTO.getUsername());
        usuarioSinModificar.setEmail(usuarioDTO.getEmail());

        Usuario usuarioModificado = usuarioRepository.save(usuarioSinModificar);

        return UsuarioDTO.builder()
                .id(usuarioModificado.getId())
                .email(usuarioModificado.getEmail())
                .username(usuarioModificado.getUsername())
                .role(usuarioModificado.getRole())
                .build();

    }

    public void eliminarUsuario(Long id) {
        usuarioRepository.deleteById(id);

    }

    public List<UsuarioDTO> buscarClientes() throws UsuarioNotFoundException {
        return usuarioRepository.findClientes().orElseThrow(() -> new UsuarioNotFoundException("No se encontraron usuarios"));

    }


    public List<UsuarioDTO> buscarUsuarioPorNombre(String username) throws UsuarioNotFoundException {
        return usuarioRepository.findClientesByUsername(username)
                .orElseThrow(() -> new UsuarioNotFoundException("No se encontraron usuarios"));

    }

}