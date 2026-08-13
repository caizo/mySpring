package org.pmv.myspring.gijonevents.infra.adapter.out.persistence.mapper;

import org.pmv.myspring.gijonevents.domain.usuario.Usuario;
import org.pmv.myspring.gijonevents.infra.adapter.out.persistence.entity.UsuarioEntity;
import org.springframework.stereotype.Component;

@Component
public class UsuarioPersistenceMapper {

    public UsuarioEntity toEntity(Usuario usuario) {

        return new UsuarioEntity();
    }

    public Usuario toDomain(UsuarioEntity entity) {

        return new Usuario();
    }
}