package org.pmv.myspring.gijonevents.application.service;

import lombok.RequiredArgsConstructor;
import org.pmv.myspring.gijonevents.application.mapper.UserResultMapper;
import org.pmv.myspring.gijonevents.application.port.in.RegisterUserUseCase;
import org.pmv.myspring.gijonevents.application.port.in.result.RegisterUserResult;
import org.pmv.myspring.gijonevents.application.port.out.UserPort;
import org.pmv.myspring.gijonevents.domain.usuario.Usuario;
import org.pmv.myspring.gijonevents.infra.out.persistence.entity.UsuarioEntity;
import org.pmv.myspring.exception.errors.UsuarioNotFoundException;
import org.pmv.myspring.jwt.JwtUtil;
import org.pmv.myspring.gijonevents.infra.out.persistence.repository.UsuarioRepositoryJpa;
import org.pmv.myspring.request.LoginRequest;
import org.pmv.myspring.gijonevents.application.port.in.command.RegisterUserCommand;
import org.pmv.myspring.response.AuthResponse;
import org.pmv.myspring.service.EmailService;
import org.pmv.myspring.service.TokenService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.Instant;

@Service
@RequiredArgsConstructor
public class AuthService implements RegisterUserUseCase {

    private final UsuarioRepositoryJpa usuarioRepository;
    private final UserPort userPort;
    private final PasswordEncoder passwordEncoder;
    private final TokenService tokenService;
    private final EmailService emailService;
    private final JwtUtil jwtUtil;
    private final UserResultMapper mapper;
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



    @Override
    public RegisterUserResult register(RegisterUserCommand command) {
        Usuario usuario = Usuario.builder()
                .email(command.getEmail())
                .username(command.getUsername())
                .role(command.getRole())
                .fechaCreacion(Instant.now())
                .fechaModificacion(Instant.now())
                .activo(Boolean.TRUE)
                .password(passwordEncoder.encode(command.getPassword()))
                .build();
        Usuario save = this.userPort.save(usuario);

        RegisterUserResult result = this.mapper.toResult(save);



        return result;

    }
}
