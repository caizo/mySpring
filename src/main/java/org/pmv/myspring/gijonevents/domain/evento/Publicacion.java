package org.pmv.myspring.gijonevents.domain.evento;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.pmv.myspring.gijonevents.domain.empresa.EmpresaId;
import org.pmv.myspring.gijonevents.domain.enums.EstadoPublicacion;
import org.pmv.myspring.gijonevents.domain.enums.TipoPublicacion;

import java.time.Instant;
import java.util.UUID;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Publicacion {
    private EmpresaId empresaId;
    private String titulo;
    private String descripcion;
    private TipoPublicacion tipo;
    private Instant fechaInicio;
    private Instant fechaFin;
    private Instant fechaModificacion;
    private EstadoPublicacion estado;
    private String imagenUrl;
}
