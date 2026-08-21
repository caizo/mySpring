package org.pmv.myspring.gijonevents.application.port.in;


import org.pmv.myspring.gijonevents.domain.evento.Publicacion;
import org.pmv.myspring.gijonevents.domain.evento.PublicacionFiltro;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface FindPublicacionesUseCase {

    Page<Publicacion> buscar(
            PublicacionFiltro filtro,
            Pageable pageable
    );
}
