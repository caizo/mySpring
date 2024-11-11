package org.pmv.myspring;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.pmv.myspring.entities.Usuario;
import org.pmv.myspring.repo.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
@Transactional
@Rollback
public class UsuarioRepositoryIntegrationTest {

    @Autowired
    private UsuarioRepository usuarioRepository;


    @Test
    public void testBuscarTodosLosUsuarios(){
        List<Usuario> result = usuarioRepository.findAll();
        Assertions.assertNotNull(result);
    }


    @Test
    public void testGuardarUsuario() {
        Usuario usuario = new Usuario();
        usuario.setNombre("Juan");
        usuario.setEmail("juan@example.com");

        Usuario savedUsuario = usuarioRepository.save(usuario);

        assertThat(savedUsuario.getId()).isNotNull();
    }

    @Test
    public void testBuscarUsuarioPorId() {
        Usuario usuario = new Usuario();
        usuario.setNombre("Juan");
        usuario.setEmail("juan@example.com");

        Usuario savedUsuario = usuarioRepository.save(usuario);

        Optional<Usuario> foundUsuario = usuarioRepository.findById(savedUsuario.getId());

        assertThat(foundUsuario).isPresent();
        assertThat(foundUsuario.get().getNombre()).isEqualTo("Juan");
    }

    @Test
    public void testActualizarUsuario() {
        Usuario usuario = new Usuario();
        usuario.setNombre("Juan");
        usuario.setEmail("juan@example.com");

        Usuario savedUsuario = usuarioRepository.save(usuario);
        savedUsuario.setNombre("Juan Actualizado");

        Usuario updatedUsuario = usuarioRepository.save(savedUsuario);

        assertThat(updatedUsuario.getNombre()).isEqualTo("Juan Actualizado");
    }

    @Test
    public void testEliminarUsuario() {
        Usuario usuario = new Usuario();
        usuario.setNombre("Juan");
        usuario.setEmail("juan@example.com");

        Usuario savedUsuario = usuarioRepository.save(usuario);
        usuarioRepository.deleteById(savedUsuario.getId());

        Optional<Usuario> foundUsuario = usuarioRepository.findById(savedUsuario.getId());

        assertThat(foundUsuario).isNotPresent();
    }
}