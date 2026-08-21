package org.pmv.myspring.gijonevents.infra.in.rest.dto;

import lombok.Builder;
import lombok.Getter;
import org.pmv.myspring.gijonevents.domain.enums.EstadoPublicacion;
import org.pmv.myspring.gijonevents.domain.enums.TipoPublicacion;

import java.time.Instant;
import java.util.List;

@Getter
@Builder
public class PublicacionResponseDto {

    private Long id;
    private String titulo;
    private String descripcion;
    private String fechaInicio;
    private String fechaFin;
    private EstadoPublicacion estado;
    private TipoPublicacion tipo;
    private List<String> imagenes;
}
