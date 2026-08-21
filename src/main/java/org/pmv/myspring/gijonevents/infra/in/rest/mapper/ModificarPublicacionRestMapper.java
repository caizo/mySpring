package org.pmv.myspring.gijonevents.infra.in.rest.mapper;

import org.mapstruct.Mapper;
import org.pmv.myspring.gijonevents.domain.evento.Publicacion;
import org.pmv.myspring.gijonevents.infra.in.rest.dto.PublicacionRequestDto;
import org.pmv.myspring.gijonevents.infra.in.rest.dto.PublicacionResponseDto;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;

@Mapper(componentModel = "spring")
public interface ModificarPublicacionRestMapper {
    Publicacion toDomain(PublicacionRequestDto request);

    PublicacionResponseDto toResponseDto(Publicacion publicacion);

    default Instant localDateTimeToInstant(final LocalDateTime value) {
        return value == null
                ? null
                : value.atZone(ZoneId.of("Europe/Madrid")).toInstant();
    }
}
