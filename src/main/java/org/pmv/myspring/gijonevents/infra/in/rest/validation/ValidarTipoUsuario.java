package org.pmv.myspring.gijonevents.infra.in.rest.validation;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Constraint(validatedBy = TipoUsuarioValidator.class)
@Target({ ElementType.METHOD, ElementType.FIELD, ElementType.ANNOTATION_TYPE, ElementType.PARAMETER })
@Retention(RetentionPolicy.RUNTIME)
public @interface ValidarTipoUsuario {
    String message() default "Tipo usuario inválido. Los valores permitidos son PERSONA o EMPRESA";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}