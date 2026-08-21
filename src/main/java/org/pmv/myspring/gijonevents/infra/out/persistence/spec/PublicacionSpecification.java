package org.pmv.myspring.gijonevents.infra.out.persistence.spec;


import jakarta.persistence.criteria.Predicate;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import org.pmv.myspring.gijonevents.domain.evento.PublicacionFiltro;
import org.pmv.myspring.gijonevents.infra.out.persistence.entity.PublicacionEntity;
import org.springframework.data.jpa.domain.Specification;

import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public final class PublicacionSpecification {

    public static Specification<PublicacionEntity> filtrar(PublicacionFiltro filtro) {

        return (root, query, criteriaBuilder) -> {

            List<Predicate> predicates = new ArrayList<>();

            if (filtro.titulo() != null && !filtro.titulo().isBlank()) {
                predicates.add(criteriaBuilder.like(criteriaBuilder.lower(root.get("titulo")), "%" + filtro.titulo().toLowerCase() + "%"));
            }

            if (filtro.tipo() != null) {
                predicates.add(criteriaBuilder.equal(root.get("tipo"), filtro.tipo()));
            }

            if (filtro.estado() != null) {
                predicates.add(criteriaBuilder.equal(root.get("estado"), filtro.estado()));
            }

            if (filtro.fechaDesde() != null) {
                LocalDateTime inicio = filtro.fechaDesde().atStartOfDay();
                predicates.add(criteriaBuilder.greaterThanOrEqualTo(root.get("fechaCreacion"), inicio));
            }

            if (filtro.fechaHasta() != null) {
                LocalDateTime fin = filtro.fechaHasta().atTime(LocalTime.MAX);
                predicates.add(criteriaBuilder.lessThanOrEqualTo(root.get("fechaCreacion"), fin));
            }

            return criteriaBuilder.and(predicates.toArray(new Predicate[0]));
        };
    }
}
