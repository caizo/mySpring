package org.pmv.myspring.gijonevents.infra.out.persistence.repository;


import org.pmv.myspring.gijonevents.infra.out.persistence.entity.PublicacionEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PublicacionJpaRepository extends JpaRepository<PublicacionEntity, Long> {
}
