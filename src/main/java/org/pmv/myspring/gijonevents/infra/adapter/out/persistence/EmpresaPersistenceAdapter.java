package org.pmv.myspring.gijonevents.infra.adapter.out.persistence;


import org.pmv.myspring.gijonevents.application.port.out.EmpresaRepository;
import org.pmv.myspring.gijonevents.domain.empresa.Empresa;
import org.pmv.myspring.gijonevents.infra.adapter.out.persistence.mapper.EmpresaPersistenceMapper;
import org.pmv.myspring.gijonevents.infra.adapter.out.persistence.repository.SpringDataEmpresaRepository;
import org.springframework.stereotype.Component;

@Component
public class EmpresaPersistenceAdapter implements EmpresaRepository {

    private final SpringDataEmpresaRepository repository;
    private final EmpresaPersistenceMapper mapper;

    public EmpresaPersistenceAdapter(
            SpringDataEmpresaRepository repository,
            EmpresaPersistenceMapper mapper
    ) {
        this.repository = repository;
        this.mapper = mapper;
    }

    @Override
    public Empresa save(Empresa empresa) {

        var entity = mapper.toEntity(empresa);

        var savedEntity = repository.save(entity);

        return mapper.toDomain(savedEntity);
    }
}