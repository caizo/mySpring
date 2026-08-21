package org.pmv.myspring.gijonevents.application.port.out.persistence;


import org.pmv.myspring.gijonevents.domain.evento.Publicacion;

import java.util.Optional;

public interface ObtenerPublicacionPort {

    Optional<Publicacion> obtenerPorId(Long id);
}
