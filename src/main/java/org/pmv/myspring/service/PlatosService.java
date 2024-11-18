package org.pmv.myspring.service;

import lombok.RequiredArgsConstructor;
import org.pmv.myspring.dto.PlatoDTO;
import org.pmv.myspring.mapper.PlatoMapper;
import org.pmv.myspring.repo.PlatoRepository;
import org.pmv.myspring.repo.RestauranteRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class PlatosService {

    private final PlatoRepository platoRepository;
    private final RestauranteRepository restauranteRepository;
    private final PlatoMapper platoMapper;

    public List<PlatoDTO> findPlatosByRestaurante(Long restauranteId) {
        return platoRepository.findPlatosByRestauranteId(restauranteId)
                              .stream()
                              .map(platoMapper::toPlatoDTO)
                              .collect(Collectors.toList());
    }
}