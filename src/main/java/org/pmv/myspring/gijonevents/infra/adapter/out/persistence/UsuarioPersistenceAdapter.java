package org.pmv.myspring.gijonevents.infra.adapter.out.persistence;


import org.pmv.myspring.gijonevents.application.port.out.UsuarioRepository;
import org.pmv.myspring.gijonevents.domain.usuario.Usuario;
import org.pmv.myspring.gijonevents.domain.usuario.UsuarioId;
import org.pmv.myspring.gijonevents.infra.adapter.out.persistence.mapper.UsuarioPersistenceMapper;
import org.pmv.myspring.gijonevents.infra.adapter.out.persistence.repository.SpringDataUsuarioRepository;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class UsuarioPersistenceAdapter implements UsuarioRepository {

    private final SpringDataUsuarioRepository repository;
    private final UsuarioPersistenceMapper mapper;

    public UsuarioPersistenceAdapter(
            SpringDataUsuarioRepository repository,
            UsuarioPersistenceMapper mapper
    ) {
        this.repository = repository;
        this.mapper = mapper;
    }

    @Override
    public Usuario save(Usuario usuario) {

        var entity = mapper.toEntity(usuario);

        var savedEntity = repository.save(entity);

        return mapper.toDomain(savedEntity);
    }

    @Override
    public Optional<Usuario> findById(UsuarioId usuarioId) {

        return repository.findById(usuarioId.value())
                .map(mapper::toDomain);
    }

    @Override
    public boolean existsByUsername(String username) {

        return repository.existsByUsername(username);
    }

    @Override
    public boolean existsByEmail(String email) {

        return repository.existsByEmail(email);
    }
}