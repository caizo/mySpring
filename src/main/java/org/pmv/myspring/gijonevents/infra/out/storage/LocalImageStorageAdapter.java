package org.pmv.myspring.gijonevents.infra.out.storage;

import org.pmv.myspring.gijonevents.application.port.out.ImageStorage;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.UUID;

@Component
public class LocalImageStorageAdapter
        implements ImageStorage {

    private final Path uploadDirectory;

    public LocalImageStorageAdapter(@Value("${app.storage.upload-dir}") String uploadDir) {

        this.uploadDirectory = Paths.get(uploadDir).toAbsolutePath().normalize();

        try {

            Files.createDirectories(
                    this.uploadDirectory
            );

        } catch (IOException exception) {

            throw new IllegalStateException(
                    "No se pudo crear el directorio de imágenes",
                    exception
            );
        }
    }

    @Override
    public String save(
            InputStream inputStream,
            String fileName,
            String contentType
    ) {

        String extension =
                getExtension(fileName);

        String storedFileName =
                UUID.randomUUID() + extension;

        Path target =
                uploadDirectory.resolve(storedFileName);

        try {

            Files.copy(
                    inputStream,
                    target,
                    StandardCopyOption.REPLACE_EXISTING
            );

            return storedFileName;

        } catch (IOException exception) {

            throw new IllegalStateException(
                    "No se pudo guardar la imagen",
                    exception
            );
        }
    }

    @Override
    public void delete(
            String imageUrl
    ) {

        if (imageUrl == null) {
            return;
        }

        Path file =
                uploadDirectory.resolve(imageUrl)
                        .normalize();

        /*
         * Evitamos que una ruta manipulada pueda
         * salir del directorio de uploads.
         */
        if (!file.startsWith(uploadDirectory)) {
            throw new IllegalArgumentException(
                    "Ruta de imagen no válida"
            );
        }

        try {

            Files.deleteIfExists(file);

        } catch (IOException exception) {

            throw new IllegalStateException(
                    "No se pudo eliminar la imagen",
                    exception
            );
        }
    }

    private String getExtension(
            String fileName
    ) {

        if (
                fileName == null
                        || !fileName.contains(".")
        ) {
            return "";
        }

        return fileName.substring(
                fileName.lastIndexOf(".")
        );
    }
}