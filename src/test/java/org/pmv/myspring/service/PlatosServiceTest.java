package org.pmv.myspring.service;

import org.junit.jupiter.api.Test;
import org.pmv.myspring.dto.PlatoDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Transactional
@Rollback
class PlatosServiceTest {

    @Autowired
    private PlatosService platosService;

    @Test
    void findPlatosByRestaurante() {
        List<PlatoDTO> platos = this.platosService.findPlatosByRestaurante(1L);
        assertNotNull(platos);
        assertFalse(platos.isEmpty());
    }
}