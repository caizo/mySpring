package org.pmv.myspring.gijonevents.application.exception;

import lombok.NoArgsConstructor;

@NoArgsConstructor
public class UsuarioNotFoundException extends RuntimeException {
    public UsuarioNotFoundException(String message) {
        super(message);
    }
}