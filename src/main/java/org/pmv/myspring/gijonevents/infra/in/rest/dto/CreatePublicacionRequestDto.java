package org.pmv.myspring.gijonevents.infra.in.rest.dto;

import lombok.Getter;
import lombok.Setter;

import java.time.Instant;

@Getter
@Setter
public class CreatePublicacionRequestDto {

    private String titulo;
    private String descripcion;
    private Instant fechaInicio;
    private Instant fechaFin;
    private String tipo;
}
