package org.pmv.myspring.gijonevents.infra.in.rest.exception;

public class CredencialesInvalidasException extends RuntimeException {

    public CredencialesInvalidasException() {
        super("El nombre de usuario y/o la contraseña no son válidos.");
    }
}
