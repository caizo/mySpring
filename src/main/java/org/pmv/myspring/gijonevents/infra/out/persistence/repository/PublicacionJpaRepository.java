package org.pmv.myspring.gijonevents.infra.out.persistence.repository;


import org.pmv.myspring.gijonevents.infra.out.persistence.entity.PublicacionEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface PublicacionJpaRepository extends JpaRepository<PublicacionEntity, Long>, JpaSpecificationExecutor<PublicacionEntity> {
}
