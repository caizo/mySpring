package org.pmv.myspring.gijonevents.application.port.in.result;

import lombok.Builder;
import lombok.Getter;

import java.util.List;

@Getter
@Builder
public class ListPublicacionesResult {

    private List<PublicacionResult> content;

    private int page;
    private int size;

    private long totalElements;
    private int totalPages;
}