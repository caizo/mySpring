package org.pmv.myspring.repo;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.pmv.myspring.dto.UsuarioDTO;
import org.pmv.myspring.entities.Role;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

@DataJpaTest
@ExtendWith(SpringExtension.class)
@ActiveProfiles("test")
class UsuarioRepositoryTest {

    @Autowired
    private UsuarioRepository usuarioRepository;

    @BeforeEach
    void setUp() {
    }

    @AfterEach
    void tearDown() {
    }

    @Test
    void findByUsername() {
    }

    @Test
    void findClientesByUsername() {
        Optional<List<UsuarioDTO>> result = this.usuarioRepository.findClientesByUsername("juan");
        assertTrue(result.isPresent());
        assertEquals(1, result.get().size());
        assertEquals("juan", result.get().get(0).getUsername());
        assertEquals("juan@gmail.com", result.get().get(0).getEmail());
        assertEquals(Role.CLIENTE, result.get().get(0).getRole());
    }
}