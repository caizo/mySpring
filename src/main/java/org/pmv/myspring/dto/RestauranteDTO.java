package org.pmv.myspring.dto;

import lombok.*;
import org.pmv.myspring.entities.Restaurante;
import org.pmv.myspring.entities.TipoDeRestaurante;
import org.pmv.myspring.entities.TipoRestaurante;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class RestauranteDTO {
    private Long id;
    private String nombre;
    private String direccion;
    private String telefono;
    private String email;
    private TipoDeRestaurante tipoRestaurante;

    public static RestauranteDTO from(Restaurante restaurante) {
        return RestauranteDTO.builder()
                .id(restaurante.getId())
                .nombre(restaurante.getNombre())
                .direccion(restaurante.getDireccion())
                .telefono(restaurante.getTelefono())
                .email(restaurante.getEmail())
                .tipoRestaurante(restaurante.getTipoRestaurante())
                .build();
    }

}