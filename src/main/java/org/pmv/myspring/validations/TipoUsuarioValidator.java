package org.pmv.myspring.validations;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class TipoUsuarioValidator implements ConstraintValidator<ValidarTipoUsuario, String> {

    @Override
    public void initialize(ValidarTipoUsuario constraintAnnotation) {
    }

    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {
        return value != null && (value.equals("CLIENTE") || value.equals("RESTAURANTE"));
    }
}