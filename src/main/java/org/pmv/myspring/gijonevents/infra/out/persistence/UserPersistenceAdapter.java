package org.pmv.myspring.gijonevents.infra.out.persistence;

import lombok.RequiredArgsConstructor;
import org.pmv.myspring.gijonevents.application.port.out.UserPort;
import org.pmv.myspring.gijonevents.domain.usuario.Usuario;
import org.pmv.myspring.gijonevents.domain.usuario.UsuarioId;
import org.pmv.myspring.gijonevents.infra.out.persistence.entity.UsuarioEntity;
import org.pmv.myspring.gijonevents.infra.out.persistence.mapper.UserPersistenceMapping;
import org.pmv.myspring.gijonevents.infra.out.persistence.repository.UsuarioRepositoryJpa;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
@RequiredArgsConstructor
public class UserPersistenceAdapter implements UserPort {

    private final UsuarioRepositoryJpa repository;
    private final UserPersistenceMapping mapper;

    @Override
    public Usuario save(Usuario usuario) {
        UsuarioEntity entity =
                mapper.toEntity(usuario);

        UsuarioEntity saved =
                repository.save(entity);

        return mapper.toDomain(saved);
    }

    @Override
    public Optional<Usuario> findById(UsuarioId id) {
        return Optional.empty();
    }

    @Override
    public boolean existsByUsername(String username) {
        return false;
    }

    @Override
    public boolean existsByEmail(String email) {
        return false;
    }
}
