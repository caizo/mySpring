package org.pmv.myspring.gijonevents.application.service;

import lombok.RequiredArgsConstructor;
import org.pmv.myspring.gijonevents.application.port.in.ModificarPublicacionUseCase;
import org.pmv.myspring.gijonevents.application.port.in.command.ModificarImageInput;
import org.pmv.myspring.gijonevents.application.port.out.ImageStoragePort;
import org.pmv.myspring.gijonevents.application.port.out.persistence.ModificarPublicacionPort;
import org.pmv.myspring.gijonevents.application.port.out.persistence.ObtenerPublicacionPort;
import org.pmv.myspring.gijonevents.domain.evento.Publicacion;
import org.pmv.myspring.gijonevents.domain.exception.PublicacionNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ModificarPublicacionService
        implements ModificarPublicacionUseCase {

    private final ObtenerPublicacionPort obtenerPublicacionPort;
    private final ModificarPublicacionPort modificarPublicacionPort;
    private final ImageStoragePort imagenStoragePort;

    @Override
    public Publicacion modificar(Long id, Publicacion datosActualizados, List<ModificarImageInput> nuevasImagenes
    ) {

        Publicacion existente =
                obtenerPublicacionPort
                        .obtenerPorId(id)
                        .orElseThrow(
                                () -> new PublicacionNotFoundException(id)
                        );

        existente.actualizar(
                datosActualizados.getTitulo(),
                datosActualizados.getDescripcion(),
                datosActualizados.getFechaCreacion(),
                datosActualizados.getTipo(),
                datosActualizados.getEstado()
        );

        if (nuevasImagenes != null && !nuevasImagenes.isEmpty()) {

            List<String> nuevasUrls =
                    imagenStoragePort.guardar(
                            id,
                            nuevasImagenes
                    );

            existente.reemplazarImagenes(nuevasUrls);
        }

        return modificarPublicacionPort.modificar(existente);
    }
}
