package org.pmv.myspring.service.unit;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.pmv.myspring.dto.RestauranteDTO;
import org.pmv.myspring.entities.TipoDeRestaurante;
import org.pmv.myspring.request.RestauranteRequest;
import org.pmv.myspring.service.RestauranteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@Transactional
@SpringBootTest
@ActiveProfiles("test")
@ExtendWith(SpringExtension.class)
@TestPropertySource(locations = "classpath:application-test.properties")
class RestauranteServiceIntegrationTest {

    @Autowired
    private RestauranteService restauranteService;

    @Test
    void buscarPorNombreTest() {
        Page<RestauranteDTO> restauranteDTOS = this.restauranteService.buscarPorNombre("LAs empanadas", 0, 5);

        List<RestauranteDTO> results = restauranteDTOS.getContent();

        assertNotNull(results);
        assertEquals(1, results.size());
        RestauranteDTO result = results.get(0);

        Assertions.assertEquals("La Casa de las Empanadas", result.getNombre());
        Assertions.assertEquals("Avenida Siempre Viva 742", result.getDireccion());
        Assertions.assertEquals("0987654321", result.getTelefono());
        Assertions.assertEquals("info@empanadas.com", result.getEmail());
        Assertions.assertEquals(TipoDeRestaurante.ESPANOLA, result.getTipoRestaurante());

    }

    @Test
    void buscarTodosLosRestaurantesTest() {
        Page<RestauranteDTO> results = this.restauranteService.buscarTodos(0, 3);
        assertNotNull(results);
        assertEquals(5, results.getTotalElements());
    }


    @Test
    void guardarNuevoRestauranteTest() {
        RestauranteRequest request = RestauranteRequest.builder()
                .nombre("El Pescador")
                .direccion("Tolombreo de arriba, 15")
                .telefono("1234567890")
                .email("pescador@gmail.com")
                .tipoRestaurante(TipoDeRestaurante.ESPANOLA).build();

        this.restauranteService.guardarRestaurante(request);

        Page<RestauranteDTO> restauranteDTOS = this.restauranteService.buscarPorNombre("El Pescador", 0, 5);
        List<RestauranteDTO> results = restauranteDTOS.getContent();

        assertNotNull(results);
        assertEquals(1, results.size());

        RestauranteDTO result = results.get(0);
        assertEquals(6L, result.getId());
        assertEquals("El Pescador", result.getNombre());
        assertEquals("Tolombreo de arriba, 15", result.getDireccion());
        assertEquals("1234567890", result.getTelefono());
        assertEquals("pescador@gmail.com", result.getEmail());
        assertEquals(TipoDeRestaurante.ESPANOLA, result.getTipoRestaurante());


    }


}