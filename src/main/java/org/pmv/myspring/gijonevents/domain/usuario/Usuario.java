package org.pmv.myspring.gijonevents.domain.usuario;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.pmv.myspring.gijonevents.infra.out.persistence.entity.Role;

import java.time.Instant;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Usuario {

    private UsuarioId id;
    private String nombre;
    private String email;
    private String password;
    private Role tipo;
    private boolean activo;
    private Instant fechaCreacion;

    public static Usuario create(String username, String email, String password, Role role) {
        return new Usuario(UsuarioId.generate(), username, email, password, role, true, Instant.now()
        );
    }

}