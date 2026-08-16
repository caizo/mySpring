package org.pmv.myspring.service;

import lombok.RequiredArgsConstructor;
import org.pmv.myspring.dto.UsuarioDTO;
import org.pmv.myspring.gijonevents.infra.out.persistence.entity.UsuarioEntity;
import org.pmv.myspring.exception.errors.UsuarioNotFoundException;
import org.pmv.myspring.jwt.JwtUtil;
import org.pmv.myspring.repo.UsuarioRepository;
import org.pmv.myspring.request.LoginRequest;
import org.pmv.myspring.request.RegistroRequest;
import org.pmv.myspring.response.AuthResponse;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.Instant;

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
        UsuarioEntity usuario = this.usuarioRepository.findByUsername(loginRequest.getUsername())
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
        UsuarioEntity usuario = guardarUsuario(request);
//        CompletableFuture<SendResult<String, String>> topicUno = kafkaTemplate.send("topicUno", "Usuario registrado: " + usuario.getUsername());
//        CompletableFuture<SendResult<String, String>> topicDos = kafkaTemplate.send("topicDos", "Usuario registrado: " + usuario.getUsername());


        return UsuarioDTO.builder()
                .id(usuario.getId())
                .email(usuario.getEmail())
                .username(usuario.getUsername())
                .role(usuario.getRole())
                .build();
    }

    private UsuarioEntity guardarUsuario(RegistroRequest registroRequest) {
        UsuarioEntity usuario = UsuarioEntity.builder()
                .email(registroRequest.getEmail())
                .username(registroRequest.getUsername())
                .role(registroRequest.getRole())
                .fechaCreacion(Instant.now())
                .fechaModificacion(Instant.now())
                .activo(Boolean.TRUE)
                .password(passwordEncoder.encode(registroRequest.getPassword()))
                .build();

        this.usuarioRepository.save(usuario);
        return usuario;

    }
}
