package org.pmv.myspring.gijonevents.infra.out.persistence.entity;

import jakarta.persistence.*;
import lombok.*;
import org.pmv.myspring.gijonevents.infra.in.rest.dto.UsuarioDTO;
import org.pmv.myspring.gijonevents.domain.enums.Role;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.time.Instant;
import java.util.Collection;
import java.util.List;

@Entity
@Table(
        schema = "myspring",
        name = "usuarios",
        uniqueConstraints = {
                @UniqueConstraint(
                        name = "uk_usuarios_email",
                        columnNames = "email"
                ),
                @UniqueConstraint(
                        name = "uk_usuarios_username",
                        columnNames = "username"
                )
        }
)
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UsuarioEntity implements UserDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true, nullable = false)
    private String username;

    @Column(unique = true, nullable = false)
    private String email;

    @Column(nullable = false)
    private String password;

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private Role role;

    @Column(nullable = false)
    private boolean activo;

    @Column(nullable = false)
    private Instant fechaCreacion;

    @Column(nullable = false)
    private Instant fechaModificacion;


    public static UsuarioEntity from(UsuarioDTO usuarioDTO) {
        return UsuarioEntity.builder()
                .id(usuarioDTO.getId())
                .email(usuarioDTO.getEmail())
                .username(usuarioDTO.getUsername())
                .role(usuarioDTO.getRole()).build();
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return List.of(new SimpleGrantedAuthority(role.name()));
    }


}