package org.pmv.myspring.gijonevents.application.port.out.persistence;

import org.pmv.myspring.gijonevents.domain.usuario.Usuario;
import org.pmv.myspring.gijonevents.domain.usuario.UsuarioId;

import java.util.Optional;

public interface UserPort {
    Usuario save(Usuario usuario);

    Optional<Usuario> findById(UsuarioId id);

    boolean existsByUsername(String username);

    boolean existsByEmail(String email);

    Usuario findByUsername(String username);
}
