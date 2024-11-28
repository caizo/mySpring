package org.pmv.myspring.dto;

import lombok.*;
import org.pmv.myspring.entities.Restaurante;

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
    private String tipoRestaurante;

    public static RestauranteDTO from(Restaurante restaurante) {
        return RestauranteDTO.builder()
                .id(restaurante.getId())
                .nombre(restaurante.getNombre())
                .direccion(restaurante.getDireccion())
                .telefono(restaurante.getTelefono())
                .email(restaurante.getEmail())
                .tipoRestaurante(restaurante.getTipoRestaurante().getNombre())
                .build();
    }

}