package org.pmv.myspring.deserializers;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.pmv.myspring.dto.RestauranteDTO;

import java.util.List;

@JsonDeserialize(using = RestaurantePageDeserializer.class)
public class RestaurantePage extends PageImpl<RestauranteDTO> {
    public RestaurantePage(List<RestauranteDTO> content, Pageable pageable, long total) {
        super(content, pageable, total);
    }

    public RestaurantePage(List<RestauranteDTO> content) {
        super(content);
    }
}
