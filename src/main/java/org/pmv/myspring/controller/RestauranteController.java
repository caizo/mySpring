package org.pmv.myspring.controller;

import lombok.RequiredArgsConstructor;
import org.pmv.myspring.dto.RestauranteDTO;
import org.pmv.myspring.request.RestauranteRequest;
import org.pmv.myspring.service.RestauranteService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/restaurantes")
@CrossOrigin(origins = "http://localhost:4200")
public class RestauranteController {

    private final RestauranteService restauranteService;

    @GetMapping
    public ResponseEntity<List<RestauranteDTO>> buscarTodos() {
        List<RestauranteDTO> restaurantes = restauranteService.buscarTodos();
        return ResponseEntity.ok(restaurantes);
    }

    @GetMapping("/buscar")
    public ResponseEntity<List<RestauranteDTO>> buscarRestaurantesPorNombre(@RequestParam String nombre) {
        List<RestauranteDTO> restaurantes = restauranteService.buscarPorNombre(nombre);
        return ResponseEntity.ok(restaurantes);
    }

    @PutMapping("/editar")
    public ResponseEntity<?> actualizarRestaurante(@RequestBody RestauranteRequest restauranteRequest) {
        restauranteService.guardarRestaurante(restauranteRequest);
        return ResponseEntity.ok().build();
    }

    @PostMapping("/crear")
    public ResponseEntity<?> crearRestaurante(@RequestBody RestauranteRequest restauranteRequest) {
        restauranteService.guardarRestaurante(restauranteRequest);
        return ResponseEntity.ok().build();
    }
}