package org.pmv.myspring.gijonevents.application.exception;

public class CredencialesInvalidasException extends RuntimeException {

    public CredencialesInvalidasException() {
        super("El nombre de usuario y/o la contraseña no son válidos.");
    }
}
