package org.pmv.myspring.controller;

import lombok.RequiredArgsConstructor;
import org.pmv.myspring.exception.errors.ApiError;
import org.pmv.myspring.exception.errors.UsuarioNotFoundException;
import org.pmv.myspring.request.LoginRequest;
import org.pmv.myspring.request.RegistroRequest;
import org.pmv.myspring.response.AuthResponse;
import org.pmv.myspring.service.UsuarioService;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/auth")
@CrossOrigin(origins = "http://localhost:4200")
@RequiredArgsConstructor
public class AuthController {

    public static final String EMAIL_INTRODUCIDO_YA_EXISTE = "El email introducido ya existe";

    private final UsuarioService usuarioService;
    private final AuthenticationManager authenticationManager;

    @PostMapping("/registro")
    public ResponseEntity<?> register(@RequestBody RegistroRequest registroRequest) {
        AuthResponse response;
        try {
            response = this.usuarioService.registro(registroRequest);
        } catch (Exception e) {
            if (e instanceof DataIntegrityViolationException) {
                ApiError apiError =
                        new ApiError(HttpStatus.INTERNAL_SERVER_ERROR, EMAIL_INTRODUCIDO_YA_EXISTE, List.of(EMAIL_INTRODUCIDO_YA_EXISTE));
                return new ResponseEntity<>(apiError, HttpStatus.INTERNAL_SERVER_ERROR);
            }

            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return ResponseEntity.ok(response);

    }

    @PostMapping("/logout")
    public ResponseEntity<?> logout(@RequestHeader("Authorization") String token) {
        this.usuarioService.logout(token);
        return ResponseEntity.ok().build();
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