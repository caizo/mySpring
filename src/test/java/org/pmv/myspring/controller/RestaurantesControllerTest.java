package org.pmv.myspring.controller;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.pmv.myspring.dto.RestauranteDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.web.servlet.MockMvc;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
@ActiveProfiles("test")
@TestPropertySource(locations = "classpath:application-test.properties")
public class RestaurantesControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    @WithMockUser(username = "admin", roles = {"ADMIN"})
    public void buscarTodosLosRestaurantesTest() throws Exception {
        String jsonResponse = mockMvc.perform(get("/api/restaurantes"))
                .andExpect(status().isOk())
                .andReturn()
                .getResponse()
                .getContentAsString();

        List<RestauranteDTO> restaurantes = objectMapper.readValue(jsonResponse, new TypeReference<List<RestauranteDTO>>() {
        });

        assertNotNull(restaurantes);
        assertFalse(restaurantes.isEmpty());
        assertEquals("Restaurante El Buen Sabor", restaurantes.get(0).getNombre());
        assertEquals("Calle Falsa 123", restaurantes.get(0).getDireccion());
        assertEquals("1234567890", restaurantes.get(0).getTelefono());
        assertEquals("contacto@elbuen.com", restaurantes.get(0).getEmail());
        assertEquals("Espanola", restaurantes.get(0).getTipoRestaurante());
    }


}