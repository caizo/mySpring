package org.pmv.myspring.gijonevents.infra.out.persistence.repository;

import org.pmv.myspring.dto.UsuarioDTO;
import org.pmv.myspring.gijonevents.infra.out.persistence.entity.UsuarioEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface UsuarioRepositoryJpa extends JpaRepository<UsuarioEntity, Long> {

    Optional<UsuarioEntity> findByUsername(String nombre);

    @Query("SELECT new org.pmv.myspring.dto.UsuarioDTO(u.id, u.username, u.email,  u.role) " +
            "FROM UsuarioEntity u " +
            "WHERE LOWER(u.username) LIKE LOWER(CONCAT('%', :username, '%')) " +
            "and u.role = 'CLIENTE'")
    Optional<List<UsuarioDTO>> findClientesByUsername(String username);

    boolean existsByEmail(String email);
}