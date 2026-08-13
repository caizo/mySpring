package org.pmv.myspring.gijonevents.domain.usuario;

import java.util.UUID;

public record UsuarioId(UUID value) {

    public UsuarioId {
        if (value == null) {
            throw new IllegalArgumentException("UsuarioId cannot be null");
        }
    }

    public static UsuarioId generate() {
        return new UsuarioId(UUID.randomUUID());
    }
}