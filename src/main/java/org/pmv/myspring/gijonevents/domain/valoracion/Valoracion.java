package org.pmv.myspring.gijonevents.domain.valoracion;

import org.pmv.myspring.gijonevents.domain.usuario.UsuarioId;
import org.pmv.myspring.gijonevents.domain.empresa.EmpresaId;
import org.pmv.myspring.gijonevents.domain.evento.EventoId;

public class Valoracion {

    private ValoracionId id;
    private UsuarioId ciudadanoId;
    private Integer puntuacion;
    private EmpresaId empresaId;
    private EventoId eventoId;

    // ...
}