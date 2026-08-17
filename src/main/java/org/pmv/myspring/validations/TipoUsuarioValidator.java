package org.pmv.myspring.validations;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import org.pmv.myspring.gijonevents.domain.enums.Role;

public class TipoUsuarioValidator implements ConstraintValidator<ValidarTipoUsuario, Role> {

    @Override
    public void initialize(ValidarTipoUsuario constraintAnnotation) {
    }

    @Override
    public boolean isValid(Role value, ConstraintValidatorContext context) {
        return value != null && (value.equals(Role.PERSONA) || value.equals(Role.EMPRESA));
    }
}