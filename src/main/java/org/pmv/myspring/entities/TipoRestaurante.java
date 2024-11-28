package org.pmv.myspring.entities;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.*;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "tipo_restaurante")
public class TipoRestaurante {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @NotBlank(message = "El nombre del tipo es obligatorio")
    @Size(min = 2, max = 100, message = "El nombre del tipo debe tener entre 2 y 100 caracteres")
    @Column(name = "nombre")
    private String nombre;
}