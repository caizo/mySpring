package org.pmv.myspring.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.pmv.myspring.entities.Role;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UsuarioDTO {
    private Long id;
    private String username;
    private String email;
    private Role role;

}