package org.pmv.myspring.controller;

import org.pmv.myspring.exception.errors.UsuarioNotFoundException;
import org.pmv.myspring.request.LoginRequest;
import org.pmv.myspring.request.RegistroRequest;
import org.pmv.myspring.response.AuthResponse;
import org.pmv.myspring.service.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
public class AuthController {

    @Autowired
    private UsuarioService usuarioService;

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @PostMapping("/registro")
    public ResponseEntity<AuthResponse> register(@RequestBody RegistroRequest registroRequest) {

        AuthResponse registro = this.usuarioService.registro(registroRequest);
        return ResponseEntity.ok(registro);
    }

    @PostMapping("/login")
    public ResponseEntity<AuthResponse> login(@RequestBody LoginRequest loginRequest) throws UsuarioNotFoundException {

        autenticarUsuario(loginRequest);

        AuthResponse login = this.usuarioService.login(loginRequest);
        return ResponseEntity.ok(login);
    }

    private void autenticarUsuario(LoginRequest registroRequest) {
        UsernamePasswordAuthenticationToken authentication =
                new UsernamePasswordAuthenticationToken(registroRequest.getUsername(), registroRequest.getPassword());
        this.authenticationManager.authenticate(authentication);
    }
}