package org.pmv.myspring.gijonevents.infra.adapter.out.persistence.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.pmv.myspring.gijonevents.domain.enums.TipoUsuario;

import java.time.Instant;
import java.util.UUID;

@Getter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(
        name = "usuarios",
        uniqueConstraints = {
                @UniqueConstraint(name = "uk_usuario_username", columnNames = "username"),
                @UniqueConstraint(name = "uk_usuario_email", columnNames = "email")
        }
)
public class UsuarioEntity {

    @Id
    @Column(nullable = false, updatable = false)
    private UUID id;

    @Column(nullable = false, length = 50)
    private String username;

    @Column(nullable = false, length = 150)
    private String email;

    @Column(nullable = false)
    private String password;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false, length = 20)
    private TipoUsuario tipo;

    @Column(nullable = false)
    private boolean activo;

    @Column(nullable = false)
    private Instant fechaCreacion;


    public UsuarioEntity(String username, String email, String password, TipoUsuario tipo, boolean activo, Instant fechaCreacion) {
        this.username = username;
        this.email = email;
        this.password = password;
        this.tipo = tipo;
        this.activo = activo;
        this.fechaCreacion = fechaCreacion;
    }
}
