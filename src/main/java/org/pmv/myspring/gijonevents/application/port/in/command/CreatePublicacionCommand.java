package org.pmv.myspring.gijonevents.application.port.in.command;


import lombok.Builder;
import lombok.Getter;
import org.pmv.myspring.gijonevents.domain.enums.TipoPublicacion;

import java.time.Instant;
import java.util.List;

@Getter
@Builder
public class CreatePublicacionCommand {

    private String titulo;
    private String descripcion;
    private Instant fechaInicio;
    private Instant fechaFin;
    private Long empresaId;
    private TipoPublicacion tipo;
    private List<ImageInput> imagenes;
}
