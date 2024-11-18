package org.pmv.myspring.repo;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.pmv.myspring.entities.Plato;
import org.pmv.myspring.entities.Restaurante;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Transactional
@Rollback
class PlatoRepositoryTest {

    @Autowired
    private PlatoRepository platoRepository;

    @Autowired
    private RestauranteRepository restauranteRepository;

    @Test
    void findPlatosByRestauranteId() {
        Optional<Restaurante> restaurante = restauranteRepository.findById(1L);
        if (restaurante.isEmpty()) {
            fail("El restaurante no existe");
        }
        List<Plato> platos = platoRepository.findPlatosByRestauranteId(restaurante.get().getId());
        assertEquals(6, platos.size());
    }
}