package org.pmv.myspring.gijonevents.domain.evento;

import org.pmv.myspring.gijonevents.domain.enums.EstadoPublicacion;
import org.pmv.myspring.gijonevents.domain.enums.TipoPublicacion;

import java.time.LocalDate;

public record PublicacionFiltro(
        String titulo,
        TipoPublicacion tipo,
        EstadoPublicacion estado,
        LocalDate fechaDesde,
        LocalDate fechaHasta
) {
}