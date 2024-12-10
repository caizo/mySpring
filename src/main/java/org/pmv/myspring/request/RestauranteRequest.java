package org.pmv.myspring.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.pmv.myspring.entities.TipoDeRestaurante;
import org.pmv.myspring.validations.Base64Image;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class RestauranteRequest {
    private Long id;
    private String nombre;
    private String direccion;
    private String telefono;
    private String email;
    private TipoDeRestaurante tipoRestaurante;
    private String descripcion;
    @Base64Image
    private String imagen;

}
