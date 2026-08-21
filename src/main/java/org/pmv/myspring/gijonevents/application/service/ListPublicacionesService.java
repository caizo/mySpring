package org.pmv.myspring.gijonevents.application.service;


import lombok.RequiredArgsConstructor;
import org.pmv.myspring.gijonevents.application.mapper.PublicacionResultMapper;
import org.pmv.myspring.gijonevents.application.port.in.ListPublicacionesUseCase;
import org.pmv.myspring.gijonevents.application.port.in.query.ListPublicacionesQuery;
import org.pmv.myspring.gijonevents.application.port.in.result.ListPublicacionesResult;
import org.pmv.myspring.gijonevents.application.port.out.persistence.PublicacionQueryPort;
import org.pmv.myspring.gijonevents.domain.evento.Publicacion;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ListPublicacionesService
        implements ListPublicacionesUseCase {

    private final PublicacionQueryPort publicacionQueryPort;
    private final PublicacionResultMapper resultMapper;

    @Override
    public ListPublicacionesResult list(ListPublicacionesQuery query) {

        PublicacionQueryPort.PageResult<Publicacion> page = publicacionQueryPort.findAll(query.getPage(), query.getSize());

        return ListPublicacionesResult.builder()
                .content(
                        page.getContent()
                                .stream()
                                .map(resultMapper::toResult)
                                .toList()
                )
                .page(page.getPage())
                .size(page.getSize())
                .totalElements(page.getTotalElements())
                .totalPages(page.getTotalPages())
                .build();
    }

}
