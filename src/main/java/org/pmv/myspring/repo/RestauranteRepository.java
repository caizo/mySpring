package org.pmv.myspring.repo;

import org.pmv.myspring.dto.RestauranteDTO;
import org.pmv.myspring.entities.Restaurante;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface RestauranteRepository extends JpaRepository<Restaurante, Long> {

    @Query("SELECT new org.pmv.myspring.dto.RestauranteDTO(r.id, r.nombre, r.direccion, r.telefono, r.email, r.tipoRestaurante, r.descripcion) " +
            "FROM Restaurante r WHERE LOWER(r.nombre) LIKE LOWER(CONCAT('%', :nombre, '%'))")
    Page<RestauranteDTO> buscarRestaurantes(String nombre, Pageable pageable);

    @Query("SELECT r.imagen FROM Restaurante r WHERE r.id = :idRestaurante")
    Optional<byte[]> getImagenRestauranteById(Long idRestaurante);
}