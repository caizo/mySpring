package org.pmv.myspring.gijonevents.application.port.out.persistence;


import lombok.Builder;
import lombok.Getter;
import org.pmv.myspring.gijonevents.domain.evento.Publicacion;

import java.util.List;

public interface PublicacionQueryPort {

    PageResult<Publicacion> findAll(int page, int size);

    @Getter
    @Builder
    class PageResult<T> {

        private List<T> content;
        private int page;
        private int size;
        private long totalElements;
        private int totalPages;
    }
}
