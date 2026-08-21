package org.pmv.myspring.gijonevents.infra.in.rest;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir;
import org.pmv.myspring.gijonevents.domain.enums.EstadoPublicacion;
import org.pmv.myspring.gijonevents.domain.enums.Role;
import org.pmv.myspring.gijonevents.domain.enums.TipoPublicacion;
import org.pmv.myspring.gijonevents.domain.usuario.Usuario;
import org.pmv.myspring.gijonevents.infra.out.persistence.entity.PublicacionEntity;
import org.pmv.myspring.gijonevents.infra.out.persistence.entity.UsuarioEntity;
import org.pmv.myspring.gijonevents.infra.out.persistence.mapper.UserPersistenceMapping;
import org.pmv.myspring.gijonevents.infra.out.persistence.repository.PublicacionJpaRepository;
import org.pmv.myspring.gijonevents.infra.out.persistence.repository.UsuarioRepositoryJpa;
import org.pmv.myspring.gijonevents.infra.out.security.jwt.TokenGeneratorAdapter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.DynamicPropertyRegistry;
import org.springframework.test.context.DynamicPropertySource;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.transaction.annotation.Transactional;

import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.time.Instant;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.multipart;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
@ActiveProfiles("test")
@Transactional
class PublicacionControllerIntegrationTest {

    @TempDir
    static Path tempDir;
    @Autowired
    private MockMvc mockMvc;
    @Autowired
    private UsuarioRepositoryJpa usuarioRepository;
    @Autowired
    private PublicacionJpaRepository publicacionRepository;
    @Autowired
    private PasswordEncoder passwordEncoder;
    @Autowired
    private TokenGeneratorAdapter tokenGeneratorAdapter;
    @Autowired
    private UserPersistenceMapping usuarioPersistenceMapper;

    @DynamicPropertySource
    static void configureProperties(
            DynamicPropertyRegistry registry
    ) {
        registry.add(
                "app.storage.upload-dir",
                () -> tempDir.toString()
        );
    }

    @Test
    void deberiaCrearPublicacionConVariasImagenes() throws Exception {

        // =========================================================
        // GIVEN
        // =========================================================

        UsuarioEntity usuarioEntity =
                UsuarioEntity.builder()
                        .username("empresa-test")
                        .email("empresa@test.com")
                        .password(
                                passwordEncoder.encode("password")
                        )
                        .role(Role.EMPRESA)
                        .activo(true)
                        .fechaCreacion(Instant.now())
                        .fechaModificacion(Instant.now())
                        .build();

        usuarioEntity =
                usuarioRepository.save(usuarioEntity);

        Usuario usuario =
                usuarioPersistenceMapper.toDomain(usuarioEntity);

        String token =
                tokenGeneratorAdapter.generateToken(usuario);

        String publicacionJson = """
                {
                    "titulo": "Concierto de prueba",
                    "descripcion": "Concierto creado mediante test de integración",
                    "fechaInicio": "2026-09-01T20:00:00Z",
                    "fechaFin": "2026-09-01T23:00:00Z",
                    "tipo": "EVENTO"
                }
                """;

        MockMultipartFile publicacion =
                new MockMultipartFile(
                        "publicacion",
                        "",
                        MediaType.APPLICATION_JSON_VALUE,
                        publicacionJson.getBytes(StandardCharsets.UTF_8) // detalle
                );

        MockMultipartFile imagen1 =
                new MockMultipartFile(
                        "imagenes",
                        "concierto-1.jpg",
                        MediaType.IMAGE_JPEG_VALUE,
                        "imagen-1".getBytes()
                );

        MockMultipartFile imagen2 =
                new MockMultipartFile(
                        "imagenes",
                        "concierto-2.jpg",
                        MediaType.IMAGE_JPEG_VALUE,
                        "imagen-2".getBytes()
                );

        MockMultipartFile imagen3 =
                new MockMultipartFile(
                        "imagenes",
                        "concierto-3.jpg",
                        MediaType.IMAGE_JPEG_VALUE,
                        "imagen-3".getBytes()
                );

        // =========================================================
        // WHEN
        // =========================================================

        mockMvc.perform(
                        multipart("/api/publicaciones")
                                .file(publicacion)
                                .file(imagen1)
                                .file(imagen2)
                                .file(imagen3)
                                .header(
                                        "Authorization",
                                        "Bearer " + token
                                )
                                .accept(MediaType.APPLICATION_JSON)
                )

                // =================================================
                // THEN - HTTP
                // =================================================

                .andExpect(status().isOk())

                .andExpect(
                        jsonPath("$.id").exists()
                )

                .andExpect(
                        jsonPath("$.titulo")
                                .value("Concierto de prueba")
                )

                .andExpect(
                        jsonPath("$.tipo")
                                .value("EVENTO")
                )

                .andExpect(
                        jsonPath("$.estado")
                                .value("PUBLICADA")
                )

                .andExpect(
                        jsonPath("$.imagenes.length()")
                                .value(3)
                );

        // =========================================================
        // THEN - USUARIO
        // =========================================================

        assertThat(
                usuarioRepository
                        .findByUsername("empresa-test")
        )
                .isPresent();

        // =========================================================
        // THEN - BASE DE DATOS
        // =========================================================

        List<PublicacionEntity> publicaciones =
                publicacionRepository.findAll();

        assertThat(publicaciones)
                .hasSize(1);

        PublicacionEntity publicacionGuardada =
                publicaciones.get(0);

        assertThat(publicacionGuardada.getTitulo())
                .isEqualTo("Concierto de prueba");

        assertThat(publicacionGuardada.getDescripcion())
                .isEqualTo(
                        "Concierto creado mediante test de integración"
                );

        assertThat(publicacionGuardada.getTipo())
                .isEqualTo(TipoPublicacion.EVENTO);

        assertThat(publicacionGuardada.getEstado())
                .isEqualTo(EstadoPublicacion.PUBLICADA);

        // TODO revisar esto
        assertThat(publicacionGuardada.getImagenes())
                .hasSize(3);

        // =========================================================
        // THEN - FILESYSTEM
        // =========================================================

        for (String imagenUrl : publicacionGuardada.getImagenes()) {

            Path imagen = tempDir.resolve(imagenUrl);

            assertThat(Files.exists(imagen)).isTrue();

            assertThat(Files.isRegularFile(imagen)).isTrue();
        }
    }

//    @Test
//    void deberiaListarPublicacionesPaginadas() throws Exception {
//
//        crearPublicacion("Publicación 1");
//        crearPublicacion("Publicación 2");
//        crearPublicacion("Publicación 3");
//
//        mockMvc.perform(
//                        get("/api/publicaciones")
//                                .param("page", "0")
//                                .param("size", "2")
//                                .accept(MediaType.APPLICATION_JSON)
//                )
//                .andExpect(status().isOk())
//                .andExpect(jsonPath("$.content.length()").value(2))
//                .andExpect(jsonPath("$.page").value(0))
//                .andExpect(jsonPath("$.size").value(2))
//                .andExpect(jsonPath("$.totalElements").value(3))
//                .andExpect(jsonPath("$.totalPages").value(2));
//    }
}