package org.pmv.myspring.controller;

import com.fasterxml.jackson.core.type.TypeReference;
import org.junit.jupiter.api.Test;
import org.pmv.myspring.deserializers.RestaurantePage;
import org.pmv.myspring.dto.RestauranteDTO;
import org.pmv.myspring.entities.TipoDeRestaurante;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.security.test.context.support.WithMockUser;

import java.util.List;
import java.util.stream.Collectors;

import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

public class RestauranteControllerTest extends BaseControllerTest {

    @Test
    @WithMockUser(username = "admin", roles = {"ADMIN"})
    public void buscarTodosLosRestaurantesTest() throws Exception {
        String jsonResponse = mockMvc.perform(get(URL_RESTAURANTES_BUSCAR_TODOS))
                .andExpect(status().isOk())
                .andReturn()
                .getResponse()
                .getContentAsString();

        Page<RestauranteDTO> restauranteDTOS = deserializePage(jsonResponse);

        assertNotNull(restauranteDTOS);

        List<RestauranteDTO> restaurantes = restauranteDTOS.getContent();

        assertNotNull(restaurantes);
        assertFalse(restaurantes.isEmpty());
        assertEquals(3, restaurantes.size());


        RestauranteDTO result1 = restaurantes.get(0);
        assertEquals("Restaurante El Buen Sabor", result1.getNombre());
        assertEquals("Calle Falsa 123", result1.getDireccion());
        assertEquals("1234567890", result1.getTelefono());
        assertEquals("contacto@elbuen.com", result1.getEmail());
        assertEquals("Restaurante de comida china", result1.getDescripcion());
        assertEquals(TipoDeRestaurante.CHINO, result1.getTipoRestaurante());
    }

    private RestaurantePage deserializePage(String jsonResponse) throws Exception {
        return objectMapper.readValue(jsonResponse, new TypeReference<RestaurantePage>() {});
    }


}