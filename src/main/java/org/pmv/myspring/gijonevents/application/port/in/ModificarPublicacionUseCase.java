package org.pmv.myspring.gijonevents.application.port.in;

import org.pmv.myspring.gijonevents.application.port.in.command.ModificarImageInput;
import org.pmv.myspring.gijonevents.domain.evento.Publicacion;

import java.io.IOException;
import java.util.List;

public interface ModificarPublicacionUseCase {

    Publicacion modificar(Long id, Publicacion publicacion, List<ModificarImageInput> nuevasImagenes) throws IOException;
}
