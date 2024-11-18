package org.pmv.myspring.service;

import org.pmv.myspring.entities.Restaurante;
import org.pmv.myspring.repo.RestauranteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RestauranteService {

    @Autowired
    private RestauranteRepository restauranteRepository;

    public List<Restaurante> listarRestaurantes() {
        return restauranteRepository.findAll();
    }

    public List<Restaurante> buscarPorNombre(String nombre) {
        return restauranteRepository.findByNombreContainingIgnoreCase(nombre);
    }
}