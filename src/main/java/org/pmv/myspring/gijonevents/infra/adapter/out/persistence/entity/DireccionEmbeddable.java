package org.pmv.myspring.gijonevents.infra.adapter.out.persistence.entity;

import jakarta.persistence.Embeddable;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@AllArgsConstructor
@NoArgsConstructor
@Embeddable
public class DireccionEmbeddable {

    private String calle;
    private String numero;
    private String codigoPostal;
    private String ciudad;

}
