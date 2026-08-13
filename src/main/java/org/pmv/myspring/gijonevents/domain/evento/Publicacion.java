package org.pmv.myspring.gijonevents.domain.evento;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.pmv.myspring.gijonevents.domain.empresa.EmpresaId;
import org.pmv.myspring.gijonevents.domain.enums.EstadoPublicacion;
import org.pmv.myspring.gijonevents.domain.enums.TipoPublicacion;

import java.time.Instant;
import java.util.UUID;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Publicacion {
    private UUID id;
    private EmpresaId empresaId;
    private String titulo;
    private String descripcion;
    private TipoPublicacion tipo;
    private Instant fechaPublicacion;
    private Instant inicio;
    private Instant fin;
    private EstadoPublicacion estado;

    public static Publicacion create(
            EmpresaId empresaId,
            String titulo,
            String descripcion,
            TipoPublicacion tipo,
            Instant inicio,
            Instant fin
    ) {
        return new Publicacion(
                PublicacionId.generate().value(),
                empresaId,
                titulo,
                descripcion,
                tipo,
                Instant.now(),
                inicio,
                fin,
                EstadoPublicacion.PUBLICADA
        );
    }

    public static Publicacion reconstitute(
            PublicacionId publicacionId,
            EmpresaId empresaId,
            String titulo,
            String descripcion,
            TipoPublicacion tipo,
            Instant fechaPublicacion,
            Instant inicio,
            Instant fin,
            EstadoPublicacion estado
    ) {
        return new Publicacion(
                publicacionId.value(),
                empresaId,
                titulo,
                descripcion,
                tipo,
                fechaPublicacion,
                inicio,
                fin,
                estado
        );
    }
}
