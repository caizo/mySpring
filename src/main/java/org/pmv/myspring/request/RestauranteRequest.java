package org.pmv.myspring.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class RestauranteRequest {
    private Long id;
    private String nombre;
    private String direccion;
    private String telefono;
    private String email;

}
