package org.pmv.myspring.controller;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import org.junit.jupiter.api.Test;
import org.pmv.myspring.dto.UsuarioDTO;
import org.pmv.myspring.entities.Role;
import org.pmv.myspring.entities.Usuario;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
public class UsuarioControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    public static String removeProperty(String json, String propertyName) throws Exception {
        ObjectMapper objectMapper = new ObjectMapper();
        JsonNode jsonNode = objectMapper.readTree(json);

        ((ObjectNode) jsonNode).remove(propertyName);

        return objectMapper.writeValueAsString(jsonNode);
    }

    @Test
    @WithMockUser(username = "admin", roles = {"ADMIN"})
    public void testCrearUsuarioValido() throws Exception {
        UsuarioDTO usuario = new UsuarioDTO();
        usuario.setUsername("Paquito");
        usuario.setEmail("caizo@outlook.es");
        usuario.setPassword("password");
        usuario.setRole(Role.CLIENTE);

        String usuarioJson = objectMapper.writeValueAsString(usuario);

        mockMvc.perform(post("/api/usuarios")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(usuarioJson))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.username").value("Paquito"))
                .andExpect(jsonPath("$.email").value("caizo@outlook.es"));
    }

    @Test
    @WithMockUser(username = "admin", roles = {"ADMIN"})
    public void obtenerUsuariosTest() throws Exception {

        mockMvc.perform(get("/api/usuarios")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk()).andExpect(jsonPath("$").isArray());
    }
}