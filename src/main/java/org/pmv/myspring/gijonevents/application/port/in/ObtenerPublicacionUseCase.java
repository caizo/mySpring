package org.pmv.myspring.gijonevents.application.port.in;


import org.pmv.myspring.gijonevents.domain.evento.Publicacion;

public interface ObtenerPublicacionUseCase {

    Publicacion obtenerPorId(Long id);
}