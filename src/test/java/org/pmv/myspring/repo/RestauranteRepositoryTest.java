package org.pmv.myspring.repo;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.pmv.myspring.entities.Restaurante;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
@ExtendWith(SpringExtension.class)
public class RestauranteRepositoryTest {

    @Autowired
    private RestauranteRepository restauranteRepository;

    @Test
    public void testFindByNombreContainingIgnoreCase() {


        // When
        List<Restaurante> found = restauranteRepository.findByNombreContainingIgnoreCase("empanadas");

        // Then
        assertThat(found).isNotEmpty();
        assertThat(found.get(0).getNombre()).isEqualTo("La Casa de las Empanadas");
    }

}