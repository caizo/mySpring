package org.pmv.myspring.gijonevents.infra.out.persistence.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.pmv.myspring.gijonevents.domain.usuario.Usuario;
import org.pmv.myspring.gijonevents.infra.out.persistence.entity.UsuarioEntity;

@Mapper(componentModel = "spring")
public interface UserPersistenceMapping {

    @Mapping(target = "id", ignore = true)
    UsuarioEntity toEntity(Usuario usuario);

    Usuario toDomain(UsuarioEntity saved);
}
