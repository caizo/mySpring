package org.pmv.myspring.controller;

import lombok.RequiredArgsConstructor;
import org.pmv.myspring.exception.errors.UsuarioNotFoundException;
import org.pmv.myspring.request.LoginRequest;
import org.pmv.myspring.request.RegistroRequest;
import org.pmv.myspring.response.AuthResponse;
import org.pmv.myspring.service.AuthService;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth")
@CrossOrigin(origins = "http://localhost:4200")
@RequiredArgsConstructor
public class AuthController {

    private final AuthService authService;
    private final AuthenticationManager authenticationManager;

    @PostMapping("/registro")
    public ResponseEntity<?> registroDeUsuario(@RequestBody RegistroRequest registroRequest) {
        this.authService.registroDeUsuario(registroRequest);
        return ResponseEntity.ok().build();
    }


    @PostMapping("/logout")
    public ResponseEntity<?> logout(@RequestHeader("Authorization") String token) {
        this.authService.logout(token);
        return ResponseEntity.ok().build();
    }


    @PostMapping("/login")
    public ResponseEntity<AuthResponse> login(@RequestBody LoginRequest loginRequest) throws UsuarioNotFoundException {

        autenticarUsuario(loginRequest);

        AuthResponse login = this.authService.login(loginRequest);
        return ResponseEntity.ok(login);
    }

    private void autenticarUsuario(LoginRequest registroRequest) {
        UsernamePasswordAuthenticationToken authentication =
                new UsernamePasswordAuthenticationToken(registroRequest.getUsername(), registroRequest.getPassword());
        this.authenticationManager.authenticate(authentication);
    }
}