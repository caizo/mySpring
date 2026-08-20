package org.pmv.myspring.gijonevents.infra.in.rest.dto;


import lombok.Builder;
import lombok.Getter;
import org.pmv.myspring.gijonevents.domain.enums.EstadoPublicacion;
import org.pmv.myspring.gijonevents.domain.enums.TipoPublicacion;

import java.time.Instant;
import java.util.List;

@Getter
@Builder
public class CreatePublicacionResponse {

    private Long id;
    private String titulo;
    private String descripcion;
    private Instant fechaInicio;
    private Instant fechaFin;
    private TipoPublicacion tipo;
    private EstadoPublicacion estado;
    private List<String> imagenes;
    private Instant fechaCreacion;
    private Instant fechaModificacion;
}
