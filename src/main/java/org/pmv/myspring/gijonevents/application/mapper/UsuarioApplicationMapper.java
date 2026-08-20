package org.pmv.myspring.gijonevents.application.mapper;

import org.mapstruct.Mapper;
import org.pmv.myspring.gijonevents.application.port.in.result.RegisterUserResult;
import org.pmv.myspring.gijonevents.domain.usuario.Usuario;

@Mapper(componentModel = "spring")
public interface UsuarioApplicationMapper {
    RegisterUserResult toResult(Usuario save);
}
