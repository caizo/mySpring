package org.pmv.myspring.gijonevents.application.port.out;


import java.io.InputStream;

public interface ImageStorage {

    String save(
            InputStream inputStream,
            String fileName,
            String contentType
    );

    void delete(String imageUrl);
}