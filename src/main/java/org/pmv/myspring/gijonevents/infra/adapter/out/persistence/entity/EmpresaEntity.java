package org.pmv.myspring.gijonevents.infra.adapter.out.persistence.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.pmv.myspring.gijonevents.domain.enums.CategoriaEmpresa;

import java.util.UUID;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "empresas")
public class EmpresaEntity {

    @Id
    @Column(nullable = false, updatable = false)
    private UUID id;

    @Column(name = "usuario_id", nullable = false, unique = true)
    private UUID usuarioId;

    @Column(nullable = false, length = 150)
    private String nombre;

    @Column(length = 500)
    private String descripcion;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false, length = 30)
    private CategoriaEmpresa categoria;

    @Embedded
    private DireccionEmbeddable direccion;

    @Column(length = 500)
    private String logo;

    @Column(nullable = false)
    private boolean activa;

}