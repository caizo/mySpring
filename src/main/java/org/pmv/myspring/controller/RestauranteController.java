package org.pmv.myspring.controller;

import lombok.RequiredArgsConstructor;
import org.pmv.myspring.dto.RestauranteDTO;
import org.pmv.myspring.exception.errors.ImageNotFoundException;
import org.pmv.myspring.request.RestauranteRequest;
import org.pmv.myspring.service.RestauranteService;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/restaurantes")
@CrossOrigin(origins = "http://localhost:4200")
public class RestauranteController {

    private final RestauranteService restauranteService;

    @GetMapping
    public ResponseEntity<Page<RestauranteDTO>> buscarTodos(@RequestParam(defaultValue = "0") int page,
                                                            @RequestParam(defaultValue = "5") int size) {
        return ResponseEntity.ok(restauranteService.buscarTodos(page, size));
    }

    @GetMapping("/buscar")
    public ResponseEntity<Page<RestauranteDTO>> buscarRestaurantesPorNombre(@RequestParam String nombre,
                                                                            @RequestParam(defaultValue = "0") int page,
                                                                            @RequestParam(defaultValue = "5") int size) {
        Page<RestauranteDTO> restaurantes = restauranteService.buscarPorNombre(nombre, page, size);
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

    @GetMapping("/{idRestaurante}/imagen")
    public ResponseEntity<String> obtenerImagen(@PathVariable Long idRestaurante) {
        try {
            return ResponseEntity.ok(restauranteService.obtenerImagen(idRestaurante));
        } catch (ImageNotFoundException e) {
            return ResponseEntity.noContent().build();
        }
    }
}