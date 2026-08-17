package org.pmv.myspring.gijonevents.application.exception;

import lombok.NoArgsConstructor;

public class UsuarioInactivoException extends RuntimeException {


    public UsuarioInactivoException(){
        super("El usuario está inactivo");
    }

    public UsuarioInactivoException(String message) {
        super(message);
    }
}