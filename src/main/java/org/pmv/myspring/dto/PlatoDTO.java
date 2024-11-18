package org.pmv.myspring.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class PlatoDTO {
    private Long id;
    private String nombre;
    private String descripcion;
    private Double precio;
    private String tipo;

}