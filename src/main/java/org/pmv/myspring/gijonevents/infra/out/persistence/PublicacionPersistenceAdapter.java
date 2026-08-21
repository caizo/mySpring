package org.pmv.myspring.gijonevents.infra.out.persistence;


import lombok.RequiredArgsConstructor;
import org.pmv.myspring.gijonevents.application.port.out.persistence.BuscarPublicacionesPort;
import org.pmv.myspring.gijonevents.application.port.out.persistence.ModificarPublicacionPort;
import org.pmv.myspring.gijonevents.application.port.out.persistence.ObtenerPublicacionPort;
import org.pmv.myspring.gijonevents.application.port.out.persistence.PublicacionPort;
import org.pmv.myspring.gijonevents.domain.evento.Publicacion;
import org.pmv.myspring.gijonevents.domain.evento.PublicacionFiltro;
import org.pmv.myspring.gijonevents.infra.out.persistence.entity.PublicacionEntity;
import org.pmv.myspring.gijonevents.infra.out.persistence.mapper.PublicacionPersistenceMapper;
import org.pmv.myspring.gijonevents.infra.out.persistence.repository.PublicacionJpaRepository;
import org.pmv.myspring.gijonevents.infra.out.persistence.spec.PublicacionSpecification;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
@RequiredArgsConstructor
public class PublicacionPersistenceAdapter implements PublicacionPort, BuscarPublicacionesPort, ObtenerPublicacionPort, ModificarPublicacionPort {

    private final PublicacionJpaRepository repository;
    private final PublicacionPersistenceMapper mapper;

    @Override
    public Publicacion save(Publicacion publicacion) {
        PublicacionEntity entity = mapper.toEntity(publicacion);
        PublicacionEntity saved = repository.save(entity);
        return mapper.toDomain(saved);
    }

    @Override
    public Page<Publicacion> buscar(PublicacionFiltro filtro, Pageable pageable) {
        Specification<PublicacionEntity> specification = PublicacionSpecification.filtrar(filtro);
        return repository.findAll(specification, pageable).map(mapper::toDomain);
    }

    @Override
    public Optional<Publicacion> obtenerPorId(Long id) {
        return repository.findById(id).map(mapper::toDomain);
    }

    @Override
    public Publicacion modificar(Publicacion publicacion) {
        PublicacionEntity entity = mapper.toEntity(publicacion);
        PublicacionEntity guardada = repository.save(entity);
        return mapper.toDomain(guardada);
    }
}
