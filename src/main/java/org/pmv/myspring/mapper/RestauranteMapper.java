package org.pmv.myspring.mapper;

import org.pmv.myspring.entities.Restaurante;
import org.pmv.myspring.request.RestauranteRequest;
import org.springframework.stereotype.Component;

@Component
public class RestauranteMapper {

    public Restaurante toRestauranteEntity(RestauranteRequest request) {
        Restaurante restaurante = new Restaurante();
        restaurante.setId(request.getId());
        restaurante.setNombre(request.getNombre());
        restaurante.setDireccion(request.getDireccion());
        restaurante.setTelefono(request.getTelefono());
        restaurante.setEmail(request.getEmail());
        return restaurante;
    }
}
