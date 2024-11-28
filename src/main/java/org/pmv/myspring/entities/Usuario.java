package org.pmv.myspring.entities;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.*;
import org.pmv.myspring.dto.UsuarioDTO;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.List;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Usuario implements UserDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "El nombre es obligatorio")
    @Size(min = 2, max = 50, message = "El nombre debe tener entre 2 y 50 caracteres")
    private String username;

    @NotBlank(message = "El email es obligatorio")
    @Email(message = "El email debe ser válido")
    @Column(unique = true)
    private String email;

    @NotBlank(message = "El telefono es obligatorio")
    @Column(unique = true)
    private String telefono;

    private String password;

    @Enumerated(EnumType.STRING)
    private Role role;

    public static Usuario from(UsuarioDTO usuarioDTO, String encodedPassword) {
        return Usuario.builder()
                .email(usuarioDTO.getEmail())
                .telefono(usuarioDTO.getTelefono())
                .username(usuarioDTO.getUsername())
                .role(usuarioDTO.getRole())
                .password(encodedPassword).build();
    }

    public static Usuario from(UsuarioDTO usuarioDTO) {
        return Usuario.builder()
                .id(usuarioDTO.getId())
                .email(usuarioDTO.getEmail())
                .telefono(usuarioDTO.getTelefono())
                .username(usuarioDTO.getUsername())
                .role(usuarioDTO.getRole()).build();
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return List.of(new SimpleGrantedAuthority(role.name()));
    }


}