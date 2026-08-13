package org.pmv.myspring.gijonevents.domain.evento;

import org.pmv.myspring.gijonevents.domain.empresa.EmpresaId;

public class Evento {

    private EventoId id;
    private EmpresaId empresaId;
    private String titulo;
    private String descripcion;

    // fechas, ubicación, etc. las veremos después
}