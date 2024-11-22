package org.pmv.myspring.service;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.pmv.myspring.entities.Restaurante;
import org.pmv.myspring.repo.RestauranteRepository;
import org.pmv.myspring.request.RestauranteRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.any;
import static org.mockito.Mockito.when;

@ExtendWith(SpringExtension.class)
@SpringBootTest
class RestauranteServiceTest {

    @Autowired
    private RestauranteService restauranteService;

    @MockBean
    private RestauranteRepository restauranteRepository;

    @Test
    void listarRestaurantes() {
        // Test implementation
    }

    @Test
    void buscarPorNombre() {
        // Test implementation
    }

    @Test
    void editarInformacionRestauranteTest() {
        // Configura los datos de prueba
        RestauranteRequest request = new RestauranteRequest();
        request.setId(1L);
        request.setNombre("Restaurante Actualizado");
        request.setDireccion("Nueva Dirección");
        request.setTelefono("987654321");
        request.setEmail("actualizado@restaurante.com");

        Restaurante restaurante = new Restaurante();
        restaurante.setId(1L);
        restaurante.setNombre("Restaurante Actualizado");
        restaurante.setDireccion("Nueva Dirección");
        restaurante.setTelefono("987654321");
        restaurante.setEmail("actualizado@restaurante.com");

        // Configura el comportamiento del mock
        when(restauranteRepository.save(any(Restaurante.class))).thenReturn(restaurante);

        // Llama al método del servicio
        Restaurante resultado = restauranteService.editarInformacionRestaurante(request);

        // Verifica el resultado
        assertNotNull(resultado);
        assertEquals("Restaurante Actualizado", resultado.getNombre());
        assertEquals("Nueva Dirección", resultado.getDireccion());
        assertEquals("987654321", resultado.getTelefono());
        assertEquals("actualizado@restaurante.com", resultado.getEmail());
    }
}