package org.pmv.myspring.service.integration;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.pmv.myspring.dto.UsuarioDTO;
import org.pmv.myspring.entities.Role;
import org.pmv.myspring.request.RegistroRequest;
import org.pmv.myspring.service.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.transaction.annotation.Transactional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;


@ExtendWith(SpringExtension.class)
@SpringBootTest
@Transactional
@ActiveProfiles("test")
@TestPropertySource(locations = "classpath:application-test.properties")
class AuthServiceIntegrationTest {

    @Autowired
    private AuthService authService;


    @Test
    void registrarUsuarioTest() {
        RegistroRequest registroRequest = new RegistroRequest();
        registroRequest.setEmail("caizo@outlook.es");
        registroRequest.setTelefono("673418121");
        registroRequest.setUsername("USUARIO_TEST");
        registroRequest.setPassword("1234");
        registroRequest.setRole(Role.CLIENTE);

        UsuarioDTO usuarioDTO = this.authService.registroDeUsuario(registroRequest);
        assertNotNull(usuarioDTO);
        assertNotNull(usuarioDTO.getId());
        assertEquals("673418121", usuarioDTO.getTelefono());


    }
}