package org.pmv.myspring.service;

import lombok.RequiredArgsConstructor;
import org.pmv.myspring.dto.RestauranteDTO;
import org.pmv.myspring.entities.Restaurante;
import org.pmv.myspring.exception.errors.ImageNotFoundException;
import org.pmv.myspring.mapper.RestauranteMapper;
import org.pmv.myspring.repo.RestauranteRepository;
import org.pmv.myspring.request.RestauranteRequest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Base64;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class RestauranteService {

    private final RestauranteRepository restauranteRepository;
    private final RestauranteMapper restauranteMapper = new RestauranteMapper();

    public Page<RestauranteDTO> buscarTodos(int page, int size) {
        Pageable pageable = PageRequest.of(page, size);
        return restauranteRepository.findAll(pageable)
                .map(RestauranteDTO::from);
    }

    public Page<RestauranteDTO> buscarPorNombre(String nombre, int page, int size) {
        return restauranteRepository.buscarRestaurantes(nombre, PageRequest.of(page, size));
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