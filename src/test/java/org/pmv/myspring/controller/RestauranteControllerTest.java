package org.pmv.myspring.controller;

import com.fasterxml.jackson.core.type.TypeReference;
import org.junit.jupiter.api.Test;
import org.pmv.myspring.dto.RestauranteDTO;
import org.pmv.myspring.entities.TipoDeRestaurante;
import org.springframework.security.test.context.support.WithMockUser;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class RestauranteControllerTest extends BaseControllerTest {

    @Test
    @WithMockUser(username = "admin", roles = {"ADMIN"})
    public void buscarTodosLosRestaurantesTest() throws Exception {
        List<RestauranteDTO> restaurantes = httpResponseFrom(URL_RESTAURANTES_BUSCAR_TODOS, new TypeReference<List<RestauranteDTO>>() {
        });

        assertNotNull(restaurantes);
        assertFalse(restaurantes.isEmpty());
        assertEquals(5, restaurantes.size());

        RestauranteDTO result1 = restaurantes.get(0);
        assertEquals("Restaurante El Buen Sabor", result1.getNombre());
        assertEquals("Calle Falsa 123", result1.getDireccion());
        assertEquals("1234567890", result1.getTelefono());
        assertEquals("contacto@elbuen.com", result1.getEmail());
        assertEquals(TipoDeRestaurante.CHINO, result1.getTipoRestaurante());
    }


}