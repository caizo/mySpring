package org.pmv.myspring.gijonevents.infra.in.rest.mapper;


import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.pmv.myspring.gijonevents.application.port.in.result.PublicacionResult;
import org.pmv.myspring.gijonevents.domain.evento.Publicacion;
import org.pmv.myspring.gijonevents.infra.in.rest.dto.PublicacionResponseDto;

import java.time.Instant;
import java.time.ZoneOffset;
import java.time.format.DateTimeFormatter;

@Mapper(componentModel = "spring")
public interface FindPublicacionRestMapper {

    DateTimeFormatter FORMATTER = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm").withZone(ZoneOffset.UTC);

    @Mapping(target = "fechaInicio", expression = "java(format(publicacion.getFechaInicio()))")
    @Mapping(target = "fechaFin", expression = "java(format(publicacion.getFechaFin()))")
    PublicacionResponseDto toResponse(PublicacionResult publicacion);

    PublicacionResponseDto toResponseDto(
            Publicacion publicacion
    );
    default String format(Instant instant) {

        if (instant == null) {
            return null;
        }

        return FORMATTER.format(instant);
    }
}
