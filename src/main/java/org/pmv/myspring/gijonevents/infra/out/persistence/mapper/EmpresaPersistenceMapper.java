package org.pmv.myspring.gijonevents.infra.out.persistence.mapper;


import org.pmv.myspring.gijonevents.domain.Direccion;
import org.pmv.myspring.gijonevents.domain.empresa.Empresa;
import org.pmv.myspring.gijonevents.infra.out.persistence.entity.DireccionEmbeddable;
import org.pmv.myspring.gijonevents.infra.out.persistence.entity.EmpresaEntity;
import org.springframework.stereotype.Component;

@Component
public class EmpresaPersistenceMapper {

    public EmpresaEntity toEntity(Empresa empresa) {

        Direccion direccion = empresa.getDireccion();

        DireccionEmbeddable direccionEmbeddable =
                new DireccionEmbeddable(
                        direccion.getCalle(),
                        direccion.getNumero(),
                        direccion.getCodigoPostal(),
                        direccion.getCiudad()
                );

        return new EmpresaEntity();
    }

    public Empresa toDomain(EmpresaEntity entity) {

        DireccionEmbeddable direccion = entity.getDireccion();

        Direccion direccionDomain = new Direccion(
                direccion.getCalle(),
                direccion.getNumero(),
                direccion.getCodigoPostal(),
                direccion.getCiudad()
        );

        return new Empresa();
    }
}