package org.pmv.myspring.mapper;

import org.pmv.myspring.dto.PlatoDTO;
import org.pmv.myspring.entities.Plato;
import org.springframework.stereotype.Component;

@Component
public class PlatoMapper {

    public PlatoDTO toPlatoDTO(Plato plato) {
        PlatoDTO platoDTO = new PlatoDTO();
        platoDTO.setId(plato.getId());
        platoDTO.setNombre(plato.getNombre());
        platoDTO.setDescripcion(plato.getDescripcion());
        platoDTO.setPrecio(plato.getPrecio());
        platoDTO.setTipo(plato.getTipo().name());
        return platoDTO;
    }
}