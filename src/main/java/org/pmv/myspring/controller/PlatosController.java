package org.pmv.myspring.controller;

import lombok.RequiredArgsConstructor;
import org.pmv.myspring.dto.PlatoDTO;
import org.pmv.myspring.service.PlatosService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/platos")
@CrossOrigin(origins = "http://localhost:4200")
@RequiredArgsConstructor
public class PlatosController {

    @Autowired
    private PlatosService platosService;

    @GetMapping("/restaurante/{restauranteId}")
    public List<PlatoDTO> findPlatosByRestaurante(@PathVariable Long restauranteId) {
        List<PlatoDTO> platos = platosService.findPlatosByRestaurante(restauranteId);
        return platos;
    }
}