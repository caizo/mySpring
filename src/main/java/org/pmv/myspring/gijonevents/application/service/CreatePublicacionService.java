package org.pmv.myspring.gijonevents.application.service;


import lombok.RequiredArgsConstructor;
import org.pmv.myspring.gijonevents.application.mapper.CreatePublicacionApplicationMapper;
import org.pmv.myspring.gijonevents.application.port.in.CreatePublicacionUseCase;
import org.pmv.myspring.gijonevents.application.port.in.command.CreatePublicacionCommand;
import org.pmv.myspring.gijonevents.application.port.in.command.ImageInput;
import org.pmv.myspring.gijonevents.application.port.in.result.CreatePublicacionResult;
import org.pmv.myspring.gijonevents.application.port.out.ImageStorage;
import org.pmv.myspring.gijonevents.application.port.out.persistence.PublicacionPort;
import org.pmv.myspring.gijonevents.domain.enums.EstadoPublicacion;
import org.pmv.myspring.gijonevents.domain.evento.Publicacion;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class CreatePublicacionService implements CreatePublicacionUseCase {

    private final PublicacionPort publicacionRepository;

    private final ImageStorage imageStorage;

    private final CreatePublicacionApplicationMapper mapper;

    @Override
    public CreatePublicacionResult create(
            CreatePublicacionCommand command
    ) {

        List<String> imagenesGuardadas =
                new ArrayList<>();

        try {

            if (command.getImagenes() != null) {

                for (
                        ImageInput imagen :
                        command.getImagenes()
                ) {

                    String imagenUrl =
                            imageStorage.save(
                                    imagen.getInputStream(),
                                    imagen.getFileName(),
                                    imagen.getContentType()
                            );

                    imagenesGuardadas.add(
                            imagenUrl
                    );
                }
            }

            Instant now = Instant.now();

            Publicacion publicacion =
                    Publicacion.builder()
                            .titulo(command.getTitulo())
                            .descripcion(command.getDescripcion())
                            .fechaInicio(command.getFechaInicio())
                            .fechaFin(command.getFechaFin())
                            .empresaId(command.getEmpresaId())
                            .tipo(command.getTipo())
                            .estado(
                                    EstadoPublicacion.PUBLICADA
                            )
                            .imagenes(
                                    imagenesGuardadas
                            )
                            .fechaCreacion(now)
                            .fechaModificacion(now)
                            .build();

            Publicacion saved =
                    publicacionRepository.save(
                            publicacion
                    );

            return mapper.toResult(saved);

        } catch (RuntimeException exception) {

            eliminarImagenes(
                    imagenesGuardadas
            );

            throw exception;
        }
    }

    private void eliminarImagenes(
            List<String> imagenes
    ) {

        for (String imagen : imagenes) {

            try {

                imageStorage.delete(imagen);

            } catch (RuntimeException ignored) {
                /*
                 * La excepción original del caso de uso
                 * es la que debemos propagar.
                 */
            }
        }
    }
}