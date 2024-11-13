package org.pmv.myspring.controller;

import org.pmv.myspring.request.RegistroRequest;
import org.pmv.myspring.response.AuthResponse;
import org.pmv.myspring.service.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
public class AuthController {

    @Autowired
    private UsuarioService usuarioService;

    @PostMapping("/registro")
    public ResponseEntity<AuthResponse> register(@RequestBody RegistroRequest registroRequest) {

        AuthResponse registro = this.usuarioService.registro(registroRequest);
        return ResponseEntity.ok(registro);
    }
}