package org.pmv.myspring.gijonevents.infra.in.rest.mapper;


import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.pmv.myspring.gijonevents.application.port.in.command.RegisterUserCommand;
import org.pmv.myspring.gijonevents.application.port.in.result.LoginUserResult;
import org.pmv.myspring.gijonevents.application.port.in.result.RegisterUserResult;
import org.pmv.myspring.gijonevents.infra.in.rest.dto.LoginUserResponseDto;
import org.pmv.myspring.gijonevents.infra.in.rest.dto.RegisterUserRequestDto;
import org.pmv.myspring.gijonevents.infra.in.rest.dto.RegisterUserResponseDto;

import java.time.ZoneId;
import java.time.format.DateTimeFormatter;

@Mapper(componentModel = "spring", imports = {DateTimeFormatter.class, ZoneId.class})
public interface UserWebMapper {



    RegisterUserCommand mapCommand(RegisterUserRequestDto requestDto);

    @Mapping(
            target = "fechaCreacion",
            expression = """
            java(result.getFechaCreacion()
                .atZone(ZoneId.of("Europe/Madrid"))
                .toOffsetDateTime())
        """
    )
    @Mapping(
            target = "fechaModificacion",
            expression = """
            java(result.getFechaModificacion()
                .atZone(ZoneId.of("Europe/Madrid"))
                .toOffsetDateTime())
        """
    )
    RegisterUserResponseDto toResponse(RegisterUserResult result);

    LoginUserResponseDto toLoginResponseDto(LoginUserResult login);
}
