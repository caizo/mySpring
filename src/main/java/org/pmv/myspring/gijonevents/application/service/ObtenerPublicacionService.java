package org.pmv.myspring.gijonevents.application.service;


import lombok.RequiredArgsConstructor;
import org.pmv.myspring.gijonevents.application.port.in.ObtenerPublicacionUseCase;
import org.pmv.myspring.gijonevents.application.port.out.persistence.ObtenerPublicacionPort;
import org.pmv.myspring.gijonevents.domain.evento.Publicacion;
import org.pmv.myspring.gijonevents.domain.exception.PublicacionNotFoundException;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ObtenerPublicacionService implements ObtenerPublicacionUseCase {

    private final ObtenerPublicacionPort obtenerPublicacionPort;

    @Override
    public Publicacion obtenerPorId(Long id) {
        return obtenerPublicacionPort
                .obtenerPorId(id)
                .orElseThrow(() -> new PublicacionNotFoundException(id));
    }
}
