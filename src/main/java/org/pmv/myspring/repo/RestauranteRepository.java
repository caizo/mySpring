package org.pmv.myspring.repo;

import org.pmv.myspring.dto.RestauranteDTO;
import org.pmv.myspring.entities.Restaurante;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface RestauranteRepository extends JpaRepository<Restaurante, Long> {

    @Query("SELECT new org.pmv.myspring.dto.RestauranteDTO(r.id, r.nombre, r.direccion, r.telefono, r.email, r.tipoRestaurante) " +
            "FROM Restaurante r WHERE LOWER(r.nombre) LIKE LOWER(CONCAT('%', :nombre, '%'))")
    List<RestauranteDTO> buscarRestaurantes(String nombre);
}