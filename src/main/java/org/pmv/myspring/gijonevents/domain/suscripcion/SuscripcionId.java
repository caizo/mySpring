package org.pmv.myspring.gijonevents.domain.suscripcion;

import java.util.UUID;

public record SuscripcionId(UUID value) {

    public SuscripcionId {
        if (value == null) {
            throw new IllegalArgumentException("SuscripcionId cannot be null");
        }
    }

    public static SuscripcionId generate() {
        return new SuscripcionId(UUID.randomUUID());
    }
}