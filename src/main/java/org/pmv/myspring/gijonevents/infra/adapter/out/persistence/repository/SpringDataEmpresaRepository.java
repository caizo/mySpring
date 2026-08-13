package org.pmv.myspring.gijonevents.infra.adapter.out.persistence.repository;

import org.pmv.myspring.gijonevents.infra.adapter.out.persistence.entity.EmpresaEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface SpringDataEmpresaRepository
        extends JpaRepository<EmpresaEntity, Long> {

    Optional<EmpresaEntity> findByUsuarioId(Long usuarioId);
}
