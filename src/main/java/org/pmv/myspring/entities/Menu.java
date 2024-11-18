package org.pmv.myspring.entities;

import jakarta.persistence.*;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.stream.Collectors;

@Getter
@Setter
@Builder
@AllArgsConstructor
@Entity
public class Menu {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @NotNull(message = "El nombre es obligatorio")
    @Column(name = "nombre")
    private String nombre;

    @NotNull(message = "La descripcion es obligatoria")
    @Column(name = "descripcion")
    private String descripcion;

    @NotNull(message = "La fecha es obligatoria")
    @Column(name = "fecha")
    private LocalDate fecha = LocalDate.now();

    @NotNull(message = "Los platos son obligatorios")
    @OneToMany
    @JoinColumn(name = "plato_id")
    private List<Plato> platos;

    @NotNull(message = "El precio es obligatorio")
    @DecimalMin(value = "0.0", inclusive = false, message = "El precio debe ser mayor que 0")
    @Column(name = "precio")
    private Double precio;

    @ManyToOne
    @JoinColumn(name = "restaurante_id", nullable = false)
    private Restaurante restaurante;

    public Menu() {
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern(" eeee dd 'de' MMMM 'de' yyyy");
        setNombre("Menú".concat(fecha.format(dtf)));
    }

    public void setPlatos(@NotNull(message = "Los platos son obligatorios") List<Plato> platos) {
        this.platos = platos;
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern(" eeee dd 'de' MMMM 'de' yyyy");
        String nombresPlatos = platos.stream()
                .map(Plato::getNombre)
                .collect(Collectors.joining(", "));
        setDescripcion("Menú del " + dtf.format(getFecha()) + ": " + nombresPlatos);
    }


}

