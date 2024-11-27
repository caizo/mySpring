package org.pmv.myspring.service.integration;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.pmv.myspring.dto.UsuarioDTO;
import org.pmv.myspring.entities.Role;
import org.pmv.myspring.exception.errors.UsuarioNotFoundException;
import org.pmv.myspring.service.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;


@ExtendWith(SpringExtension.class)
@SpringBootTest
@Transactional
@ActiveProfiles("test")
@TestPropertySource(locations = "classpath:application-test.properties")
class UsuarioServiceIntegrationTest {

    @Autowired
    private UsuarioService usuarioService;

    @Test
    void findClientesByUsernameTest() throws UsuarioNotFoundException {
        List<UsuarioDTO> usuarios = usuarioService.buscarUsuarioPorNombre("juan");

        assertNotNull(usuarios);
        assertFalse(usuarios.isEmpty());
        assertEquals(1, usuarios.size());

        UsuarioDTO usuarioDTO = usuarios.get(0);

        assertEquals("juan", usuarioDTO.getUsername());
        assertEquals("juan@gmail.com", usuarioDTO.getEmail());
        assertEquals(Role.CLIENTE, usuarioDTO.getRole());
    }

}