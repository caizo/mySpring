package org.pmv.myspring.gijonevents.infra.in.rest;

import lombok.RequiredArgsConstructor;
import org.pmv.myspring.gijonevents.application.port.in.FindPublicacionesUseCase;
import org.pmv.myspring.gijonevents.application.port.in.CreatePublicacionUseCase;
import org.pmv.myspring.gijonevents.application.port.in.ObtenerPublicacionUseCase;
import org.pmv.myspring.gijonevents.application.port.in.command.CreatePublicacionCommand;
import org.pmv.myspring.gijonevents.application.port.in.result.CreatePublicacionResult;
import org.pmv.myspring.gijonevents.domain.enums.EstadoPublicacion;
import org.pmv.myspring.gijonevents.domain.enums.TipoPublicacion;
import org.pmv.myspring.gijonevents.domain.evento.Publicacion;
import org.pmv.myspring.gijonevents.domain.evento.PublicacionFiltro;
import org.pmv.myspring.gijonevents.infra.in.rest.dto.CreatePublicacionRequestDto;
import org.pmv.myspring.gijonevents.infra.in.rest.dto.CreatePublicacionResponse;
import org.pmv.myspring.gijonevents.infra.in.rest.dto.PublicacionResponseDto;
import org.pmv.myspring.gijonevents.infra.in.rest.mapper.FindPublicacionRestMapper;
import org.pmv.myspring.gijonevents.infra.in.rest.mapper.CreatePublicacionRestMapper;
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

import java.time.LocalDate;

@RestController
@RequestMapping("/api/publicaciones")
@RequiredArgsConstructor
public class PublicacionController {

    private final CreatePublicacionUseCase createPublicacionUseCase;
    private final FindPublicacionesUseCase findPublicacionesUseCase;
    private final CreatePublicacionRestMapper createPublicacionRestMapper;
    private final FindPublicacionRestMapper findPublicacionRestMapper;
    private final ObtenerPublicacionUseCase obtenerPublicacionUseCase;


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
}
