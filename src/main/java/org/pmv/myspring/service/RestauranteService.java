package org.pmv.myspring.service;

import lombok.RequiredArgsConstructor;
import org.pmv.myspring.dto.RestauranteDTO;
import org.pmv.myspring.entities.Restaurante;
import org.pmv.myspring.exception.errors.ImageNotFoundException;
import org.pmv.myspring.mapper.RestauranteMapper;
import org.pmv.myspring.repo.RestauranteRepository;
import org.pmv.myspring.request.RestauranteRequest;
import org.springframework.stereotype.Service;

import java.util.Base64;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class RestauranteService {

    private final RestauranteRepository restauranteRepository;
    private final RestauranteMapper restauranteMapper = new RestauranteMapper();

    public List<RestauranteDTO> buscarTodos() {
        return restauranteRepository.findAll().stream()
                .map(RestauranteDTO::from)
                .collect(Collectors.toList());
    }

    public List<RestauranteDTO> buscarPorNombre(String nombre) {
        return restauranteRepository.buscarRestaurantes(nombre);
    }

    public void guardarRestaurante(RestauranteRequest restauranteRequest) {
        restauranteRepository.save(Restaurante.from(restauranteRequest));
    }

    public String obtenerImagen(Long idRestaurante) throws ImageNotFoundException {
        Optional<byte[]> imagen = restauranteRepository.getImagenRestauranteById(idRestaurante);
        return imagen.map(bytes -> Base64.getEncoder().encodeToString(bytes))
                .orElseThrow(() -> new ImageNotFoundException("Image not found for id: " + idRestaurante));

    }
}