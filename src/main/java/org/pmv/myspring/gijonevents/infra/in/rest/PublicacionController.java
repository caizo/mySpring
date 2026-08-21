package org.pmv.myspring.gijonevents.infra.in.rest;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.pmv.myspring.gijonevents.application.port.in.CreatePublicacionUseCase;
import org.pmv.myspring.gijonevents.application.port.in.FindPublicacionesUseCase;
import org.pmv.myspring.gijonevents.application.port.in.ModificarPublicacionUseCase;
import org.pmv.myspring.gijonevents.application.port.in.ObtenerPublicacionUseCase;
import org.pmv.myspring.gijonevents.application.port.in.command.CreatePublicacionCommand;
import org.pmv.myspring.gijonevents.application.port.in.command.ModificarImageInput;
import org.pmv.myspring.gijonevents.application.port.in.result.CreatePublicacionResult;
import org.pmv.myspring.gijonevents.domain.enums.EstadoPublicacion;
import org.pmv.myspring.gijonevents.domain.enums.TipoPublicacion;
import org.pmv.myspring.gijonevents.domain.evento.Publicacion;
import org.pmv.myspring.gijonevents.domain.evento.PublicacionFiltro;
import org.pmv.myspring.gijonevents.domain.exception.ImageStorageException;
import org.pmv.myspring.gijonevents.infra.in.rest.dto.CreatePublicacionRequestDto;
import org.pmv.myspring.gijonevents.infra.in.rest.dto.CreatePublicacionResponse;
import org.pmv.myspring.gijonevents.infra.in.rest.dto.PublicacionRequestDto;
import org.pmv.myspring.gijonevents.infra.in.rest.dto.PublicacionResponseDto;
import org.pmv.myspring.gijonevents.infra.in.rest.mapper.CreatePublicacionRestMapper;
import org.pmv.myspring.gijonevents.infra.in.rest.mapper.FindPublicacionRestMapper;
import org.pmv.myspring.gijonevents.infra.in.rest.mapper.ModificarPublicacionRestMapper;
import org.pmv.myspring.gijonevents.infra.out.persistence.entity.UsuarioEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/api/publicaciones")
@RequiredArgsConstructor
public class PublicacionController {

    private final CreatePublicacionUseCase createPublicacionUseCase;
    private final FindPublicacionesUseCase findPublicacionesUseCase;
    private final CreatePublicacionRestMapper createPublicacionRestMapper;
    private final FindPublicacionRestMapper findPublicacionRestMapper;
    private final ModificarPublicacionRestMapper modificarPublicacionRestMapper;
    private final ObtenerPublicacionUseCase obtenerPublicacionUseCase;
    private final ModificarPublicacionUseCase modificarPublicacionUseCase;
    private final ObjectMapper objectMapper;


    @PostMapping(consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<CreatePublicacionResponse> createPublicacion(
            @RequestPart("publicacion") CreatePublicacionRequestDto request,
            @RequestPart(value = "imagenes", required = false) MultipartFile[] imagenes,
            Authentication authentication) {

        Long empresaId = obtenerEmpresaId(authentication);
        CreatePublicacionCommand command = createPublicacionRestMapper.toCommand(request, empresaId, imagenes);
        CreatePublicacionResult result = createPublicacionUseCase.create(command);
        CreatePublicacionResponse response = createPublicacionRestMapper.toResponse(result);
        return ResponseEntity.ok(response);
    }

    private Long obtenerEmpresaId(Authentication authentication) {
        UsuarioEntity principal = (UsuarioEntity) authentication.getPrincipal();
        return principal.getId();

    }


    @GetMapping
    public ResponseEntity<Page<PublicacionResponseDto>> findPublicacion(
            @RequestParam(required = false) String titulo,
            @RequestParam(required = false) TipoPublicacion tipo,
            @RequestParam(required = false) EstadoPublicacion estado,
            @RequestParam(required = false)
            @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
            LocalDate fechaDesde,
            @RequestParam(required = false)
            @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
            LocalDate fechaHasta,
            @PageableDefault() Pageable pageable) {

        PublicacionFiltro filtro = new PublicacionFiltro(titulo, tipo, estado, fechaDesde, fechaHasta);
        Page<Publicacion> publicaciones = findPublicacionesUseCase.buscar(filtro, pageable);
        Page<PublicacionResponseDto> response = publicaciones.map(findPublicacionRestMapper::toResponseDto);
        return ResponseEntity.ok(response);
    }

    @GetMapping("/{id}")
    public ResponseEntity<PublicacionResponseDto> obtenerPorId(@PathVariable Long id) {
        Publicacion publicacion = obtenerPublicacionUseCase.obtenerPorId(id);
        return ResponseEntity.ok(findPublicacionRestMapper.toResponseDto(publicacion));
    }

    @PutMapping(value = "/{id}", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<PublicacionResponseDto> modificar(
            @PathVariable Long id,
            @RequestPart("publicacion")
            String publicacionJson,
            @RequestPart(value = "imagenes", required = false)
            List<MultipartFile> imagenes) throws IOException {

        PublicacionRequestDto requestDto = objectMapper.readValue(
                publicacionJson,
                PublicacionRequestDto.class
        );

        Publicacion publicacion = modificarPublicacionRestMapper.toDomain(requestDto);

        List<ModificarImageInput> imagenInputs =
                imagenes == null
                        ? List.of()
                        : imagenes.stream()
                        .map(this::toImagenInput)
                        .toList();

        Publicacion modificada = this.modificarPublicacionUseCase.modificar(
                id,
                publicacion,
                imagenInputs
        );

        return ResponseEntity.ok(modificarPublicacionRestMapper.toResponseDto(modificada));
    }

    private ModificarImageInput toImagenInput(MultipartFile imagen) {

        try {
            return new ModificarImageInput(
                    imagen.getOriginalFilename(),
                    imagen.getContentType(),
                    imagen.getBytes()
            );

        } catch (IOException e) {
            throw new ImageStorageException("No se pudo leer la imagen");
        }
    }
}
