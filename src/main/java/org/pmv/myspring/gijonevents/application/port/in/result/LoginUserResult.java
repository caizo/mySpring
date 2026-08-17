package org.pmv.myspring.gijonevents.application.port.in.result;

import lombok.*;
import org.pmv.myspring.gijonevents.domain.enums.Role;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class LoginUserResult {
    private String jwt;
    private String username;
    private Role role;
}
