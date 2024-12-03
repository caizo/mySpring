package org.pmv.myspring.validations;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import org.pmv.myspring.entities.Role;

public class TipoUsuarioValidator implements ConstraintValidator<ValidarTipoUsuario, Role> {

    @Override
    public void initialize(ValidarTipoUsuario constraintAnnotation) {
    }

    @Override
    public boolean isValid(Role value, ConstraintValidatorContext context) {
        return value != null && (value.equals(Role.CLIENTE) || value.equals(Role.RESTAURANTE));
    }
}