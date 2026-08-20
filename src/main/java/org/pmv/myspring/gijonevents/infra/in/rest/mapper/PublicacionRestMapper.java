package org.pmv.myspring.gijonevents.infra.in.rest.mapper;


import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;
import org.pmv.myspring.gijonevents.application.port.in.command.CreatePublicacionCommand;
import org.pmv.myspring.gijonevents.application.port.in.command.ImageInput;
import org.pmv.myspring.gijonevents.application.port.in.result.CreatePublicacionResult;
import org.pmv.myspring.gijonevents.domain.enums.TipoPublicacion;
import org.pmv.myspring.gijonevents.infra.in.rest.dto.CreatePublicacionRequestDto;
import org.pmv.myspring.gijonevents.infra.in.rest.dto.CreatePublicacionResponse;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;

@Mapper(componentModel = "spring")
public interface PublicacionRestMapper {

    CreatePublicacionResponse toResponse(CreatePublicacionResult result);


    @Mapping(target = "empresaId", source = "empresaId")
    @Mapping(target = "tipo", source = "request.tipo", qualifiedByName = "mapTipo")
    @Mapping(target = "imagenes", source = "imagenes", qualifiedByName = "mapImagenes")
    CreatePublicacionCommand toCommand(CreatePublicacionRequestDto request, Long empresaId, MultipartFile[] imagenes);

    @Named("mapTipo")
    default TipoPublicacion mapTipo(String tipo) {

        if (tipo == null) {
            return null;
        }

        try {
            return TipoPublicacion.valueOf(
                    tipo.toUpperCase()
            );
        } catch (IllegalArgumentException exception) {
            throw new IllegalArgumentException("Tipo de publicación no válido: " + tipo);
        }
    }

    @Named("mapImagenes")
    default List<ImageInput> mapImagenes(
            MultipartFile[] imagenes
    ) {

        if (imagenes == null || imagenes.length == 0) {
            return List.of();
        }

        return Arrays.stream(imagenes)
                .filter(imagen -> !imagen.isEmpty())
                .map(this::mapImagen)
                .toList();
    }

    default ImageInput mapImagen(
            MultipartFile imagen
    ) {

        try {

            return ImageInput.builder()
                    .inputStream(imagen.getInputStream())
                    .fileName(imagen.getOriginalFilename())
                    .contentType(imagen.getContentType())
                    .build();

        } catch (IOException exception) {

            throw new IllegalStateException(
                    "No se pudo leer la imagen",
                    exception
            );
        }
    }
}
