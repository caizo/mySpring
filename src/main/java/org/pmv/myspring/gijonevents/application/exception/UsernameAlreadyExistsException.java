package org.pmv.myspring.gijonevents.application.exception;

public class UsernameAlreadyExistsException extends RuntimeException {

    public UsernameAlreadyExistsException(String username) {
        super("El nombre de usuario '" + username + "' ya está registrado");
    }
}
