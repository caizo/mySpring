package org.pmv.myspring.controller;

import lombok.RequiredArgsConstructor;
import org.pmv.myspring.entities.Restaurante;
import org.pmv.myspring.request.RestauranteRequest;
import org.pmv.myspring.service.RestauranteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/restaurantes")
@CrossOrigin(origins = "http://localhost:4200")
@RequiredArgsConstructor
public class RestauranteController {

    private final RestauranteService restauranteService;

    @GetMapping
    public ResponseEntity<List<Restaurante>> obtenerRestaurantes() {
        List<Restaurante> restaurantes = restauranteService.listarRestaurantes();
        return ResponseEntity.ok(restaurantes);
    }

    @GetMapping("/buscar")
    public ResponseEntity<List<Restaurante>> buscarRestaurantesPorNombre(@RequestParam String nombre) {
        List<Restaurante> restaurantes = restauranteService.buscarPorNombre(nombre);
        return ResponseEntity.ok(restaurantes);
    }

    @PutMapping("/editar")
    public ResponseEntity<Restaurante> editarInformacionRestaurante(@RequestBody RestauranteRequest restauranteRequest) {
        Restaurante restauranteEditado = restauranteService.editarInformacionRestaurante(restauranteRequest);
        return ResponseEntity.ok(restauranteEditado);
    }
}