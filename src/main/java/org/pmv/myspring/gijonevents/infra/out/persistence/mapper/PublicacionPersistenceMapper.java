package org.pmv.myspring.gijonevents.infra.out.persistence.mapper;


import org.mapstruct.Mapper;
import org.pmv.myspring.gijonevents.domain.evento.Publicacion;
import org.pmv.myspring.gijonevents.infra.out.persistence.entity.PublicacionEntity;

@Mapper(componentModel = "spring")
public interface PublicacionPersistenceMapper {

    PublicacionEntity toEntity(Publicacion publicacion);

    Publicacion toDomain(PublicacionEntity entity);
}
