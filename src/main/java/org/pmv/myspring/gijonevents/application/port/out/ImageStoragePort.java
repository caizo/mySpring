package org.pmv.myspring.gijonevents.application.port.out;


import org.pmv.myspring.gijonevents.application.port.in.command.ModificarImageInput;

import java.io.InputStream;
import java.util.List;

public interface ImageStoragePort {

    String save(
            InputStream inputStream,
            String fileName,
            String contentType
    );

    void delete(String imageUrl);

    List<String> guardar(Long id, List<ModificarImageInput> nuevasImagenes);
}