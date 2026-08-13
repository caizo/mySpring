package org.pmv.myspring.gijonevents.domain.evento;

import java.util.UUID;

public record PublicacionId(UUID value) {

    public PublicacionId {
        if (value == null) {
            throw new IllegalArgumentException("PublicacionId cannot be null");
        }
    }

    public static PublicacionId generate() {
        return new PublicacionId(UUID.randomUUID());
    }
}