package org.pmv.myspring.gijonevents.infra.out.persistence.entity;

import jakarta.persistence.CollectionTable;
import jakarta.persistence.Column;
import jakarta.persistence.ElementCollection;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.pmv.myspring.gijonevents.domain.enums.EstadoPublicacion;
import org.pmv.myspring.gijonevents.domain.enums.TipoPublicacion;

import java.time.Instant;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(
        name = "publicaciones",
        schema = "myspring"
)
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class PublicacionEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String titulo;

    @Column(nullable = false, columnDefinition = "TEXT")
    private String descripcion;

    @Column(nullable = false)
    private Instant fechaInicio;

    @Column(nullable = false)
    private Instant fechaFin;

    @Column(nullable = false)
    private Long empresaId;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private TipoPublicacion tipo;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private EstadoPublicacion estado;

    @ElementCollection
    @CollectionTable(
            name = "publicacion_imagenes",
            schema = "myspring",
            joinColumns = @JoinColumn(
                    name = "publicacion_id"
            )
    )
    @Column(name = "imagen_url")
    @Builder.Default
    private List<String> imagenes = new ArrayList<>();

    @Column(nullable = false)
    private Instant fechaCreacion;

    @Column(nullable = false)
    private Instant fechaModificacion;
}
