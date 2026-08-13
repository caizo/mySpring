package org.pmv.myspring.gijonevents.application.port.in.result;

import org.pmv.myspring.gijonevents.domain.enums.TipoUsuario;

public record RegisterUserResult(
        String username,
        String email,
        TipoUsuario tipo
) {
}