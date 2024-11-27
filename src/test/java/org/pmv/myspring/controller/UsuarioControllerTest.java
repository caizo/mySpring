package org.pmv.myspring.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.pmv.myspring.dto.UsuarioDTO;
import org.pmv.myspring.entities.Role;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
@ActiveProfiles("test")
@TestPropertySource(locations = "classpath:application-test.properties")
public class UsuarioControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    @WithMockUser(username = "admin", roles = {"ADMIN"})
    public void testCrearUsuarioValido() throws Exception {

        String usuarioJson = crearUsuario();

        mockMvc.perform(post("/api/usuarios")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(usuarioJson))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.username").value("Paquito"))
                .andExpect(jsonPath("$.email").value("caizo@outlook.es"));
    }

    private String crearUsuario() throws JsonProcessingException {
        return objectMapper.writeValueAsString(UsuarioDTO.builder()
                .username("Paquito").email("caizo@outlook.es").password("password").role(Role.CLIENTE).build());
    }

    @Test
    @WithMockUser(username = "admin", roles = {"ADMIN"})
    public void obtenerUsuariosTest() throws Exception {
        mockMvc.perform(get("/api/usuarios")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk()).andExpect(jsonPath("$").isArray());
    }

    @Test
    @WithMockUser(username = "admin", roles = {"ADMIN"})
    public void obtenerUsuarioPorNombreOkTest() throws Exception {
            mockMvc.perform(get("/api/usuarios/nombre/paco")
                            .contentType(MediaType.APPLICATION_JSON))
                    .andExpect(status().isOk())
                    .andExpect(jsonPath("$[0].username").value("paco"))
                    .andExpect(jsonPath("$[0].email").value("paco@outlook.com"));

    }

    @Test
    @WithMockUser(username = "admin", roles = {"ADMIN"})
    public void obtenerUsuarioPorNombreNoContentTest() throws Exception {
            mockMvc.perform(get("/api/usuarios/nombre/xxx")
                            .contentType(MediaType.APPLICATION_JSON))
                    .andExpect(status().isNoContent());

    }

}