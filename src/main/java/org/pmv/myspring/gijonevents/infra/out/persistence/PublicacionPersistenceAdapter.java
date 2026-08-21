package org.pmv.myspring.gijonevents.infra.out.persistence;


import lombok.RequiredArgsConstructor;
import org.pmv.myspring.gijonevents.application.port.out.persistence.PublicacionPort;
import org.pmv.myspring.gijonevents.application.port.out.persistence.PublicacionQueryPort;
import org.pmv.myspring.gijonevents.domain.evento.Publicacion;
import org.pmv.myspring.gijonevents.infra.out.persistence.entity.PublicacionEntity;
import org.pmv.myspring.gijonevents.infra.out.persistence.mapper.PublicacionPersistenceMapper;
import org.pmv.myspring.gijonevents.infra.out.persistence.repository.PublicacionJpaRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class PublicacionPersistenceAdapter implements PublicacionPort, PublicacionQueryPort {

    private final PublicacionJpaRepository repository;
    private final PublicacionPersistenceMapper mapper;

    @Override
    public Publicacion save(Publicacion publicacion) {
        PublicacionEntity entity = mapper.toEntity(publicacion);
        PublicacionEntity saved = repository.save(entity);
        return mapper.toDomain(saved);
    }

    @Override
    public PageResult<Publicacion> findAll(int page, int size) {

        Page<PublicacionEntity> result = repository.findAll(PageRequest.of(page, size));

        return PageResult.<Publicacion>builder()
                .content(
                        result.getContent()
                                .stream()
                                .map(mapper::toDomain)
                                .toList()
                )
                .page(result.getNumber())
                .size(result.getSize())
                .totalElements(result.getTotalElements())
                .totalPages(result.getTotalPages())
                .build();
    }
}
