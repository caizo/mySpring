package org.pmv.myspring.gijonevents.infra.in.rest.exception;

import lombok.NoArgsConstructor;

@NoArgsConstructor
public class UsuarioNotFoundException extends RuntimeException {
    public UsuarioNotFoundException(String message) {
        super(message);
    }
}