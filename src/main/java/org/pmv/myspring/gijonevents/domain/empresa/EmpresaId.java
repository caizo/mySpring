package org.pmv.myspring.gijonevents.domain.empresa;

import java.util.UUID;

public record EmpresaId(UUID value) {

    public EmpresaId {
        if (value == null) {
            throw new IllegalArgumentException("EmpresaId cannot be null");
        }
    }

    public static EmpresaId generate() {
        return new EmpresaId(UUID.randomUUID());
    }
}