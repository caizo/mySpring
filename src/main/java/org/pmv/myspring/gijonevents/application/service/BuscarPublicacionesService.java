package org.pmv.myspring.gijonevents.application.service;

import lombok.RequiredArgsConstructor;
import org.pmv.myspring.gijonevents.application.port.in.FindPublicacionesUseCase;
import org.pmv.myspring.gijonevents.application.port.out.persistence.BuscarPublicacionesPort;
import org.pmv.myspring.gijonevents.domain.evento.Publicacion;
import org.pmv.myspring.gijonevents.domain.evento.PublicacionFiltro;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class BuscarPublicacionesService
        implements FindPublicacionesUseCase {

    private final BuscarPublicacionesPort buscarPublicacionesPort;

    @Override
    public Page<Publicacion> buscar(PublicacionFiltro filtro, Pageable pageable) {
        return buscarPublicacionesPort.buscar(filtro, pageable);
    }
}