package org.pmv.myspring.service;

import lombok.RequiredArgsConstructor;
import org.pmv.myspring.entities.Restaurante;
import org.pmv.myspring.mapper.RestauranteMapper;
import org.pmv.myspring.repo.RestauranteRepository;
import org.pmv.myspring.request.RestauranteRequest;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class RestauranteService {

    private final RestauranteRepository restauranteRepository;
    private final RestauranteMapper restauranteMapper = new RestauranteMapper();

    public List<Restaurante> listarRestaurantes() {
        return restauranteRepository.findAll();
    }

    public List<Restaurante> buscarPorNombre(String nombre) {
        return restauranteRepository.findByNombreContainingIgnoreCase(nombre);
    }

    public Restaurante editarInformacionRestaurante(RestauranteRequest restauranteRequest) {
        return restauranteRepository.save(restauranteMapper.toRestauranteEntity(restauranteRequest));
    }
}