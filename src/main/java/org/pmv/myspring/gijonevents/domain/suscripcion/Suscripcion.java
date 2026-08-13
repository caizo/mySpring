package org.pmv.myspring.gijonevents.domain.suscripcion;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.pmv.myspring.gijonevents.domain.empresa.EmpresaId;
import org.pmv.myspring.gijonevents.domain.usuario.UsuarioId;
import org.pmv.myspring.gijonevents.domain.evento.EventoId;

import java.time.Instant;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Suscripcion {

    private SuscripcionId id;
    private UsuarioId ciudadanoId;
    private EventoId eventoId;
    private EmpresaId empresaId;
    private Instant fechaCreacion;

    public static Suscripcion create(
            UsuarioId usuarioId,
            EmpresaId empresaId,
            EventoId eventoId
    ) {
        return new Suscripcion(
                SuscripcionId.generate(),
                usuarioId,
                eventoId,
                empresaId,
                Instant.now()
        );
    }

    public static Suscripcion reconstitute(
            SuscripcionId id,
            UsuarioId usuarioId,
            EventoId eventoId,
            EmpresaId empresaId,
            Instant fechaCreacion
    ) {
        return new Suscripcion(
                id,
                usuarioId,
                eventoId,
                empresaId,
                fechaCreacion
        );
    }
}