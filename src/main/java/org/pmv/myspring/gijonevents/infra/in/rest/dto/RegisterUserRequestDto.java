package org.pmv.myspring.gijonevents.infra.in.rest.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.pmv.myspring.gijonevents.domain.enums.Role;
import org.pmv.myspring.validations.ValidarTipoUsuario;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class RegisterUserRequestDto {

    @NotBlank(message = "El nombre de usuario es requerido")
    private String username;

    @NotBlank(message = "El email es requerido")
    private String email;

    @NotBlank(message = "La contraseña es requerida")
    private String password;

    @ValidarTipoUsuario
    @NotBlank(message = "El rol es requerido")
    private Role role;

}