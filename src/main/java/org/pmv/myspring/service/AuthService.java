package org.pmv.myspring.service;

import lombok.RequiredArgsConstructor;
import org.pmv.myspring.dto.UsuarioDTO;
import org.pmv.myspring.entities.Usuario;
import org.pmv.myspring.exception.errors.UsuarioNotFoundException;
import org.pmv.myspring.jwt.JwtUtil;
import org.pmv.myspring.repo.UsuarioRepository;
import org.pmv.myspring.request.LoginRequest;
import org.pmv.myspring.request.RegistroRequest;
import org.pmv.myspring.response.AuthResponse;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.SendResult;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.concurrent.CompletableFuture;

@Service
@RequiredArgsConstructor
public class AuthService {

    private final UsuarioRepository usuarioRepository;
    private final PasswordEncoder passwordEncoder;
    private final TokenService tokenService;
    private final EmailService emailService;
    private final JwtUtil jwtUtil;
    //private final KafkaTemplate<String, String> kafkaTemplate;

    public AuthResponse login(LoginRequest loginRequest) throws UsuarioNotFoundException {
        Usuario usuario = this.usuarioRepository.findByUsername(loginRequest.getUsername())
                .orElseThrow(() -> new UsuarioNotFoundException("Usuario no encontrado"));

        return AuthResponse.builder().jwt(this.jwtUtil.generateToken(usuario)).build();

    }

    public void logout(String token) {
        this.tokenService.invalidateToken(token);
    }

//    @KafkaListener(topics = "topicUno", groupId = "grupo-unmenu")
//    public void emailListener(String usuario) {
//        System.out.println("Enviar email: " + usuario);
//        this.emailService.enviarEmailConfirmacion(null, this.jwtUtil.generateToken(null));
//    }
//
//    @KafkaListener(topics = "topicDos", groupId = "grupo-unmenu")
//    public void marketingListener(String usuario) {
//        System.out.println("Enviar email de marketing: " + usuario);
//        throw new RuntimeException("Error en la simulación de la operación");
//
//    }


    public UsuarioDTO registroDeUsuario(RegistroRequest request) {
        Usuario usuario = guardarUsuario(request);
//        CompletableFuture<SendResult<String, String>> topicUno = kafkaTemplate.send("topicUno", "Usuario registrado: " + usuario.getUsername());
//        CompletableFuture<SendResult<String, String>> topicDos = kafkaTemplate.send("topicDos", "Usuario registrado: " + usuario.getUsername());




        return UsuarioDTO.builder()
                .id(usuario.getId())
                .email(usuario.getEmail())
                .telefono(usuario.getTelefono())
                .username(usuario.getUsername())
                .role(usuario.getRole())
                .build();
    }

    private Usuario guardarUsuario(RegistroRequest registroRequest) {
        Usuario usuario = Usuario.builder()
                .email(registroRequest.getEmail())
                .username(registroRequest.getUsername())
                .role(registroRequest.getRole())
                .telefono(registroRequest.getTelefono())
                .password(passwordEncoder.encode(registroRequest.getPassword())).build();

        this.usuarioRepository.save(usuario);
        return usuario;

    }
}
