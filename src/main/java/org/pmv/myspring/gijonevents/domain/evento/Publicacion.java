package org.pmv.myspring.gijonevents.domain.evento;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import org.pmv.myspring.gijonevents.domain.enums.EstadoPublicacion;
import org.pmv.myspring.gijonevents.domain.enums.TipoPublicacion;

import java.time.Instant;
import java.util.ArrayList;
import java.util.List;

@Data
@Builder
@AllArgsConstructor
public class Publicacion {
    private Long id;
    private String titulo;
    private String descripcion;
    private Instant fechaInicio;
    private Instant fechaFin;
    private Long empresaId;
    private TipoPublicacion tipo;
    private EstadoPublicacion estado;
    private List<String> imagenes;
    private Instant fechaCreacion;
    private Instant fechaModificacion;

    public Publicacion() {
        this.imagenes = new ArrayList<>();
    }

    public void actualizar(
            String titulo,
            String descripcion,
            Instant fechaEvento,
            TipoPublicacion tipo,
            EstadoPublicacion estado
    ) {
        this.titulo = titulo;
        this.descripcion = descripcion;
        this.fechaCreacion = fechaEvento;
        this.tipo = tipo;
        this.estado = estado;
    }

    public void reemplazarImagenes(List<String> nuevasImagenes) {

        if (nuevasImagenes == null) {
            this.imagenes = new ArrayList<>();
            return;
        }

        this.imagenes = new ArrayList<>(nuevasImagenes);
    }
}
