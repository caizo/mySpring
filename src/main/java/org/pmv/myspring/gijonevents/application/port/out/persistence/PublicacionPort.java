package org.pmv.myspring.gijonevents.application.port.out.persistence;


import org.pmv.myspring.gijonevents.domain.evento.Publicacion;

public interface PublicacionPort {

    Publicacion save(Publicacion publicacion);
}
