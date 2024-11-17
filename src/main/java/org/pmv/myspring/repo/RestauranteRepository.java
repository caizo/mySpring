package org.pmv.myspring.repo;

import org.pmv.myspring.entities.Restaurante;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RestauranteRepository extends JpaRepository<Restaurante, Long> {
}