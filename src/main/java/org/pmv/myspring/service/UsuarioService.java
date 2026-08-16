package org.pmv.myspring.service;

import lombok.RequiredArgsConstructor;
import org.pmv.myspring.dto.UsuarioDTO;
import org.pmv.myspring.gijonevents.infra.out.persistence.entity.UsuarioEntity;
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
        UsuarioEntity usuarioGuardado = usuarioRepository.save(UsuarioEntity.from(usuarioDTO, passwordEncoder.encode(usuarioDTO.getPassword())));
        return UsuarioDTO.from(usuarioGuardado);

    }

    public UsuarioDTO buscarUsuarioPorId(Long id) throws UsuarioNotFoundException {
        return usuarioRepository.findById(id)
                .map(UsuarioDTO::from)
                .orElseThrow(() -> new UsuarioNotFoundException("Usuario no encontrado"));

    }

    public UsuarioDTO actualizarUsuario(UsuarioDTO usuarioDTO) throws UsuarioNotFoundException {
        return usuarioRepository.findById(usuarioDTO.getId())
                .map(usuario -> usuarioRepository.save(UsuarioEntity.from(usuarioDTO)))
                .map(UsuarioDTO::from)
                .orElseThrow(() -> new UsuarioNotFoundException("Usuario no encontrado"));
    }

    public void eliminarUsuario(Long id) {
        usuarioRepository.deleteById(id);
    }


    public List<UsuarioDTO> buscarUsuarioPorNombre(String username) throws UsuarioNotFoundException {
        return usuarioRepository.findClientesByUsername(username)
                .orElseThrow(() -> new UsuarioNotFoundException("No se encontraron usuarios"));

    }

}