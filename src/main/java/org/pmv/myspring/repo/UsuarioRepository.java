package org.pmv.myspring.repo;

import org.pmv.myspring.dto.UsuarioDTO;
import org.pmv.myspring.entities.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface UsuarioRepository extends JpaRepository<Usuario, Long> {

    Optional<Usuario> findByUsername(String nombre);

    @Query("SELECT new org.pmv.myspring.dto.UsuarioDTO(u.id, u.username, u.email, u.role) " +
            "FROM Usuario u " +
            "WHERE LOWER(u.username) LIKE LOWER(CONCAT('%', :username, '%')) " +
            "and u.role = 'CLIENTE'")
    Optional<List<UsuarioDTO>> findClientesByUsername(String username);
}