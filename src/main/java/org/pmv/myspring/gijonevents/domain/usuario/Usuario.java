package org.pmv.myspring.gijonevents.domain.usuario;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.pmv.myspring.gijonevents.domain.enums.TipoUsuario;

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
    private TipoUsuario tipo;
    private boolean activo;
    private Instant fechaCreacion;

    public static Usuario create(String username, String email, String password, TipoUsuario tipo) {
        return new Usuario(UsuarioId.generate(), username, email, password, tipo, true, Instant.now()
        );
    }

    public static Usuario reconstitute(
            UsuarioId id,
            String username,
            String email,
            String password,
            TipoUsuario tipo,
            boolean activo,
            Instant fechaCreacion
    ) {
        return new Usuario(id, username, email, password, tipo, activo, fechaCreacion);
    }
}