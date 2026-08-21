package org.pmv.myspring.gijonevents.domain.exception;

public class PublicacionNotFoundException extends RuntimeException {

    public PublicacionNotFoundException(Long id) {
        super("Publicación no encontrada con id: " + id);
    }
}
