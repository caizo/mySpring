package org.pmv.myspring.repo;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.pmv.myspring.dto.RestauranteDTO;
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
    public void testBuscarRestaurantesTest() {
        List<RestauranteDTO> found = restauranteRepository.buscarRestaurantes("empanadas");

        Assertions.assertNotNull(found);
        Assertions.assertEquals(1, found.size());

        RestauranteDTO result = found.get(0);

        Assertions.assertEquals("La Casa de las Empanadas", result.getNombre());
        Assertions.assertEquals("Avenida Siempre Viva 742", result.getDireccion());
        Assertions.assertEquals("0987654321", result.getTelefono());
        Assertions.assertEquals("info@empanadas.com", result.getEmail());
        Assertions.assertEquals("Mexicana", result.getTipoRestaurante());


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