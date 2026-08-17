package org.pmv.myspring.gijonevents.application.port.in.result;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.pmv.myspring.gijonevents.domain.enums.Role;

import java.time.Instant;


@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class RegisterUserResult {
    private String username;
    private String email;
    private Role role;
    private boolean activo;
    private Instant fechaCreacion;
    private Instant fechaModificacion;
}
