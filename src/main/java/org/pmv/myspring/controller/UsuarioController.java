package org.pmv.myspring.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.pmv.myspring.dto.UsuarioDTO;
import org.pmv.myspring.exception.errors.UsuarioNotFoundException;
import org.pmv.myspring.service.UsuarioService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/usuarios")
@RequiredArgsConstructor
@CrossOrigin(origins = "http://localhost:4200")
public class UsuarioController {


    private final UsuarioService usuarioService;

    @PostMapping
    public ResponseEntity<UsuarioDTO> crearUsuario(@Valid @RequestBody UsuarioDTO usuarioDTO) {
        UsuarioDTO savedUsuario = usuarioService.guardarUsuario(usuarioDTO);
        return ResponseEntity.ok(savedUsuario);
    }


    @Operation(summary = "Obtener un usuario por ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Usuario encontrado"),
            @ApiResponse(responseCode = "404", description = "Usuario no encontrado")
    })
    @GetMapping("/{id}")
    public ResponseEntity<UsuarioDTO> obtenerUsuario(@PathVariable Long id) throws UsuarioNotFoundException {
        UsuarioDTO usuarioDTO = usuarioService.buscarUsuarioPorId(id);
        return ResponseEntity.ok(usuarioDTO);

    }

    @Operation(summary = "Obtener un usuario por nombre")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Usuario encontrado"),
            @ApiResponse(responseCode = "204", description = "Usuario no encontrado")
    })
    @GetMapping("/nombre/{nombre}")
    public ResponseEntity<List<UsuarioDTO>> obtenerUsuarioPorNombre(@PathVariable String nombre) throws UsuarioNotFoundException {
        List<UsuarioDTO> usuarioDTOS = usuarioService.buscarUsuarioPorNombre(nombre);
        if (usuarioDTOS.isEmpty()) {
            return ResponseEntity.noContent().build();
        }

        return ResponseEntity.ok(usuarioDTOS);
    }

    @GetMapping
    public ResponseEntity<List<UsuarioDTO>> buscarClientes() throws UsuarioNotFoundException {
        List<UsuarioDTO> clientes = usuarioService.buscarClientes();
        if (clientes.isEmpty()) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(clientes);
    }

    @PutMapping()
    public ResponseEntity<UsuarioDTO> actualizarUsuario(@RequestBody UsuarioDTO usuarioDTO) throws UsuarioNotFoundException {
        return ResponseEntity.ok(usuarioService.actualizarUsuario(usuarioDTO));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminarUsuario(@PathVariable Long id) {
        usuarioService.eliminarUsuario(id);
        return ResponseEntity.ok().build();
    }
}