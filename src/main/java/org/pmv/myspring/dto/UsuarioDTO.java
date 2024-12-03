package org.pmv.myspring.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.pmv.myspring.entities.Role;
import org.pmv.myspring.entities.Usuario;
import org.pmv.myspring.validations.ValidarTipoUsuario;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class UsuarioDTO {

    private Long id;

    @NotBlank
    private String username;

    @Email
    private String email;

    @NotBlank
    private String telefono;

    @ValidarTipoUsuario
    private Role role;

    private String password;

    public UsuarioDTO(Long id, String username, String email, String telefono, Role role) {
        this.id = id;
        this.username = username;
        this.email = email;
        this.telefono = telefono;
        this.role = role;
    }

    public static UsuarioDTO from(Usuario usuarioGuardado) {
        return UsuarioDTO.builder()
                .id(usuarioGuardado.getId())
                .email(usuarioGuardado.getEmail())
                .telefono(usuarioGuardado.getTelefono())
                .username(usuarioGuardado.getUsername())
                .role(usuarioGuardado.getRole())
                .build();
    }
}