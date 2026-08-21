package org.pmv.myspring.gijonevents.application.port.in.query;

import lombok.Builder;
import lombok.Getter;
import org.pmv.myspring.gijonevents.domain.enums.EstadoPublicacion;
import org.pmv.myspring.gijonevents.domain.enums.TipoPublicacion;

@Getter
@Builder
public class ListPublicacionesQuery {

    private final int page;
    private final int size;
    private TipoPublicacion tipo;
    private EstadoPublicacion estado;
}
