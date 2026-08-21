package org.pmv.myspring.gijonevents.infra.in.rest.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import org.pmv.myspring.gijonevents.domain.enums.EstadoPublicacion;
import org.pmv.myspring.gijonevents.domain.enums.TipoPublicacion;

import java.time.LocalDateTime;

public record PublicacionRequestDto(

        @NotBlank
        @Size(max = 150)
        String titulo,

        @NotBlank
        String descripcion,

        @NotNull
        LocalDateTime fechaCreacion,

        @NotNull
        TipoPublicacion tipo,

        @NotNull
        EstadoPublicacion estado
) {
}
