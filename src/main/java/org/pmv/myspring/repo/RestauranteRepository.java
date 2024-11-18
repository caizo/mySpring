package org.pmv.myspring.repo;

import org.pmv.myspring.entities.Restaurante;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface RestauranteRepository extends JpaRepository<Restaurante, Long> {
    List<Restaurante> findByNombreContainingIgnoreCase(String nombre);
}