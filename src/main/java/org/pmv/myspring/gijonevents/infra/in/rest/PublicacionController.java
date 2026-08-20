package org.pmv.myspring.gijonevents.infra.in.rest;

import lombok.RequiredArgsConstructor;
import org.pmv.myspring.gijonevents.application.port.in.CreatePublicacionUseCase;
import org.pmv.myspring.gijonevents.application.port.in.command.CreatePublicacionCommand;
import org.pmv.myspring.gijonevents.application.port.in.result.CreatePublicacionResult;
import org.pmv.myspring.gijonevents.infra.in.rest.dto.CreatePublicacionRequestDto;
import org.pmv.myspring.gijonevents.infra.in.rest.dto.CreatePublicacionResponse;
import org.pmv.myspring.gijonevents.infra.in.rest.mapper.PublicacionRestMapper;
import org.pmv.myspring.gijonevents.infra.out.persistence.entity.UsuarioEntity;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("/api/publicaciones")
@RequiredArgsConstructor
public class PublicacionController {

    private final CreatePublicacionUseCase createPublicacionUseCase;
    private final PublicacionRestMapper restMapper;

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

        /*
         * Aquí debemos obtener el ID del usuario
         * autenticado según nuestro principal JWT.
         *
         * Si nuestro UserDetails actual contiene el
         * Long id, podemos obtenerlo desde él.
         */
        UsuarioEntity principal = (UsuarioEntity) authentication.getPrincipal();
        return principal.getId();

    }
}
