package org.pmv.myspring.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.pmv.myspring.entities.Role;
import org.pmv.myspring.entities.Usuario;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class UsuarioDTO {
    private Long id;
    private String username;
    private String email;
    private String telefono;
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