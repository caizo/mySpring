package org.pmv.myspring.gijonevents.infra.in.rest.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.pmv.myspring.gijonevents.domain.enums.Role;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class LoginUserResponseDto {
    private String jwt;
    private String username;
    private Role role;
}
