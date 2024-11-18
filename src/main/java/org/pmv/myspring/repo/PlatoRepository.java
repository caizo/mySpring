package org.pmv.myspring.repo;

import org.pmv.myspring.entities.Plato;
import org.pmv.myspring.entities.TipoPlato;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import java.util.List;

public interface PlatoRepository extends JpaRepository<Plato, Long> {

    @Query("SELECT p FROM Plato p WHERE p.restaurante.id = :restauranteId")
    List<Plato> findPlatosByRestauranteId(@Param("restauranteId") Long restauranteId);


    @Query("SELECT p FROM Plato p WHERE p.restaurante.id = :restauranteId AND p.tipo = :tipoPlato")
    List<Plato> buscarTipoPlatosPorRestauranteId(@Param("restauranteId") Long restauranteId, @Param("tipoPlato") TipoPlato tipoPlato);}