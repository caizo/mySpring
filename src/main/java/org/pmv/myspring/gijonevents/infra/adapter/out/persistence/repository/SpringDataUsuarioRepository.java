package org.pmv.myspring.gijonevents.infra.adapter.out.persistence.repository;

import org.pmv.myspring.gijonevents.infra.adapter.out.persistence.entity.UsuarioEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.UUID;

public interface SpringDataUsuarioRepository
        extends JpaRepository<UsuarioEntity, UUID> {

    boolean existsByUsername(String username);

    boolean existsByEmail(String email);

    Optional<UsuarioEntity> findByUsername(String username);
}
