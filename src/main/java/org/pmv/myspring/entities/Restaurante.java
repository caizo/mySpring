package org.pmv.myspring.entities;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.*;
import org.pmv.myspring.request.RestauranteRequest;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Restaurante {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @NotBlank(message = "El nombre es obligatorio")
    @Size(min = 2, max = 100, message = "El nombre debe tener entre 2 y 100 caracteres")
    @Column(name = "nombre")
    private String nombre;

    @NotBlank(message = "La dirección es obligatoria")
    @Size(min = 5, max = 200, message = "La dirección debe tener entre 5 y 200 caracteres")
    @Column(name = "direccion")
    private String direccion;

    @NotBlank(message = "El teléfono es obligatorio")
    @Size(min = 10, max = 15, message = "El teléfono debe tener entre 10 y 15 caracteres")
    @Column(name = "telefono")
    private String telefono;

    @NotBlank(message = "El email es obligatorio")
    @Email(message = "El email debe ser válido")
    @Column(name = "email")
    private String email;

    @Column(name = "imagen")
    private byte[] imagen;

    @Enumerated(EnumType.STRING)
    @Column(name = "tipo_restaurante", nullable = false)
    private TipoDeRestaurante tipoRestaurante;

    public static Restaurante from(RestauranteRequest restauranteRequest) {
        return Restaurante.builder()
                .id(restauranteRequest.getId())
                .nombre(restauranteRequest.getNombre())
                .direccion(restauranteRequest.getDireccion())
                .telefono(restauranteRequest.getTelefono())
                .email(restauranteRequest.getEmail())
                .tipoRestaurante(restauranteRequest.getTipoRestaurante())
                .build();
    }
}