package org.pmv.myspring.repo;

import org.junit.jupiter.api.Test;
import org.pmv.myspring.entities.Menu;
import org.pmv.myspring.entities.Plato;
import org.pmv.myspring.entities.Restaurante;
import org.pmv.myspring.entities.TipoPlato;
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
class MenuRepositoryTest {

    @Autowired
    private RestauranteRepository restauranteRepository;

    @Autowired
    private MenuRepository menuRepository;

    @Autowired
    private PlatoRepository platoRepository;

    @Test
    void createMenuForToday() {
        Restaurante restaurante = restauranteRepository.findById(1L).orElseThrow();
        Menu menu = new Menu();
        menu.setPrecio(13.00);
        menu.setRestaurante(restaurante);


        List<Plato> primerosPlatos =
                platoRepository.buscarTipoPlatosPorRestauranteId(restaurante.getId(), TipoPlato.PRIMERO);
        assertNotNull(primerosPlatos);

        List<Plato> segundosPlatos =
                platoRepository.buscarTipoPlatosPorRestauranteId(restaurante.getId(), TipoPlato.SEGUNDO);
        assertNotNull(segundosPlatos);

        List<Plato> postresPlatos =
                platoRepository.buscarTipoPlatosPorRestauranteId(restaurante.getId(), TipoPlato.POSTRE);
        assertNotNull(postresPlatos);

        menu.setPlatos(List.of(primerosPlatos.get(0), segundosPlatos.get(0), postresPlatos.get(0)));

        menuRepository.save(menu);

        Optional<Menu> foundMenu = menuRepository.findById(menu.getId());
        assertTrue(foundMenu.isPresent());
    }

}