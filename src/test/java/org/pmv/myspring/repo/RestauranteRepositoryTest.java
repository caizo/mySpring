package org.pmv.myspring.repo;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.pmv.myspring.entities.Restaurante;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.List;
import java.util.NoSuchElementException;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
@ExtendWith(SpringExtension.class)
public class RestauranteRepositoryTest {

    @Autowired
    private RestauranteRepository restauranteRepository;

    @Test
    public void testFindByNombreContainingIgnoreCase() {
        List<Restaurante> found = restauranteRepository.findByNombreContainingIgnoreCase("empanadas");

        assertThat(found).isNotEmpty();
        assertThat(found.get(0).getNombre()).isEqualTo("La Casa de las Empanadas");
    }

    @Test
    public void editarInformacionRestauranteTest(){
        Restaurante restaurante = restaurante1();

        String nuevoNombre = "Monte Naranco";
        String nuevaDireccion = "Menéndez Pelayo, 15";
        String nuevoTelefono = "985591258";
        String nuevoEmail = "rnaranco@gmail.com";

        restaurante.setNombre(nuevoNombre);
        restaurante.setDireccion(nuevaDireccion);
        restaurante.setTelefono(nuevoTelefono);
        restaurante.setEmail(nuevoEmail);

        assertNotNull(restaurante);

        restauranteRepository.save(restaurante);

        assertThat(restaurante1().getNombre()).isEqualTo(nuevoNombre);
        assertThat(restaurante1().getDireccion()).isEqualTo(nuevaDireccion);
        assertThat(restaurante1().getTelefono()).isEqualTo(nuevoTelefono);
        assertThat(restaurante1().getEmail()).isEqualTo(nuevoEmail);
    }

    private Restaurante restaurante1() {
        return restauranteRepository.findById(1L).orElseThrow(() -> new NoSuchElementException("Restaurante 1 no encontrado"));
    }

}