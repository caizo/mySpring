package org.pmv.myspring.controller;

import lombok.RequiredArgsConstructor;
import org.pmv.myspring.dto.PlatoDTO;
import org.pmv.myspring.service.PlatosService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/platos")
@CrossOrigin(origins = "http://localhost:4200")
@RequiredArgsConstructor
public class PlatosController {

    private final PlatosService platosService;

    @GetMapping("/restaurante/{restauranteId}")
    public List<PlatoDTO> findPlatosByRestaurante(@PathVariable Long restauranteId) {
        return platosService.findPlatosByRestaurante(restauranteId);

    }
}