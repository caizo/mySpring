package org.pmv.myspring.gijonevents.application.mapper;


import org.mapstruct.Mapper;
import org.pmv.myspring.gijonevents.application.port.in.result.CreatePublicacionResult;
import org.pmv.myspring.gijonevents.domain.evento.Publicacion;

@Mapper(componentModel = "spring")
public interface CreatePublicacionApplicationMapper {

    CreatePublicacionResult toResult(
            Publicacion publicacion
    );
}
