package org.pmv.myspring.gijonevents.infra.in.rest.dto;

import lombok.Builder;
import lombok.Getter;

import java.util.List;

@Getter
@Builder
public class PageResponseDto<T> {

    private List<T> content;

    private int page;
    private int size;

    private long totalElements;
    private int totalPages;
}
