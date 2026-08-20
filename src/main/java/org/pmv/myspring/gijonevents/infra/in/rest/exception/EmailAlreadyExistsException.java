package org.pmv.myspring.gijonevents.infra.in.rest.exception;

public class EmailAlreadyExistsException extends RuntimeException {

    public EmailAlreadyExistsException(String email) {
        super("El email '" + email + "' ya está registrado");
    }
}
