package org.pmv.myspring.gijonevents.infra.in.rest;

import lombok.RequiredArgsConstructor;
import org.pmv.myspring.gijonevents.application.port.in.CreatePublicacionUseCase;
import org.pmv.myspring.gijonevents.application.port.in.ListPublicacionesUseCase;
import org.pmv.myspring.gijonevents.application.port.in.command.CreatePublicacionCommand;
import org.pmv.myspring.gijonevents.application.port.in.query.ListPublicacionesQuery;
import org.pmv.myspring.gijonevents.application.port.in.result.CreatePublicacionResult;
import org.pmv.myspring.gijonevents.application.port.in.result.ListPublicacionesResult;
import org.pmv.myspring.gijonevents.infra.in.rest.dto.CreatePublicacionRequestDto;
import org.pmv.myspring.gijonevents.infra.in.rest.dto.CreatePublicacionResponse;
import org.pmv.myspring.gijonevents.infra.in.rest.dto.PageResponseDto;
import org.pmv.myspring.gijonevents.infra.in.rest.dto.PublicacionResponseDto;
import org.pmv.myspring.gijonevents.infra.in.rest.mapper.PublicacionResponseMapper;
import org.pmv.myspring.gijonevents.infra.in.rest.mapper.PublicacionRestMapper;
import org.pmv.myspring.gijonevents.infra.out.persistence.entity.UsuarioEntity;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@RestController
@RequestMapping("/api/publicaciones")
@RequiredArgsConstructor
public class PublicacionController {

    private final CreatePublicacionUseCase createPublicacionUseCase;
    private final ListPublicacionesUseCase listPublicacionesUseCase;
    private final PublicacionRestMapper restMapper;
    private final PublicacionResponseMapper responseMapper;


    @PostMapping(consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<CreatePublicacionResponse> create(
            @RequestPart("publicacion") CreatePublicacionRequestDto request,
            @RequestPart(value = "imagenes", required = false) MultipartFile[] imagenes,
            Authentication authentication
    ) {
        Long empresaId = obtenerEmpresaId(authentication);
        CreatePublicacionCommand command = restMapper.toCommand(request, empresaId, imagenes);
        CreatePublicacionResult result = createPublicacionUseCase.create(command);
        CreatePublicacionResponse response = restMapper.toResponse(result);
        return ResponseEntity.ok(response);
    }

    private Long obtenerEmpresaId(Authentication authentication) {
        UsuarioEntity principal = (UsuarioEntity) authentication.getPrincipal();
        return principal.getId();

    }

    @GetMapping
    public ResponseEntity<PageResponseDto<PublicacionResponseDto>> listar(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "20") int size
    ) {

        ListPublicacionesResult result = listPublicacionesUseCase.list(
                ListPublicacionesQuery.builder()
                        .page(page)
                        .size(size)
                        .build()
        );

        List<PublicacionResponseDto> content = result.getContent()
                .stream()
                .map(responseMapper::toResponse)
                .toList();

        PageResponseDto<PublicacionResponseDto> response = PageResponseDto.<PublicacionResponseDto>builder()
                .content(content)
                .page(result.getPage())
                .size(result.getSize())
                .totalElements(result.getTotalElements())
                .totalPages(result.getTotalPages())
                .build();

        return ResponseEntity.ok(response);
    }
}
