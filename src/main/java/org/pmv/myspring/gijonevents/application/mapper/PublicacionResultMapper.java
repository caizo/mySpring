package org.pmv.myspring.gijonevents.application.mapper;


import org.mapstruct.Mapper;
import org.pmv.myspring.gijonevents.application.port.in.result.PublicacionResult;
import org.pmv.myspring.gijonevents.domain.evento.Publicacion;

@Mapper(componentModel = "spring")
public interface PublicacionResultMapper {

    PublicacionResult toResult(Publicacion publicacion);
}
