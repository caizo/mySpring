package org.pmv.myspring.gijonevents.domain.exception;

public class ImageStorageException extends RuntimeException {

    public ImageStorageException(String message) {
        super("Error guardando imagen: " + message);
    }
}
