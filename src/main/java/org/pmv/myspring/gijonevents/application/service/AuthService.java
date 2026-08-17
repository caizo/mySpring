package org.pmv.myspring.gijonevents.application.service;

import lombok.RequiredArgsConstructor;
import org.pmv.myspring.gijonevents.application.exception.CredencialesInvalidasException;
import org.pmv.myspring.gijonevents.application.exception.EmailAlreadyExistsException;
import org.pmv.myspring.gijonevents.application.exception.UsernameAlreadyExistsException;
import org.pmv.myspring.gijonevents.application.exception.UsuarioInactivoException;
import org.pmv.myspring.gijonevents.application.mapper.UserResultMapper;
import org.pmv.myspring.gijonevents.application.port.in.LoginUserUseCase;
import org.pmv.myspring.gijonevents.application.port.in.RegisterUserUseCase;
import org.pmv.myspring.gijonevents.application.port.in.command.LoginUserCommand;
import org.pmv.myspring.gijonevents.application.port.in.command.RegisterUserCommand;
import org.pmv.myspring.gijonevents.application.port.in.result.LoginUserResult;
import org.pmv.myspring.gijonevents.application.port.in.result.RegisterUserResult;
import org.pmv.myspring.gijonevents.application.port.out.PasswordEncoderPort;
import org.pmv.myspring.gijonevents.application.port.out.TokenGeneratorPort;
import org.pmv.myspring.gijonevents.application.port.out.persistence.UserPort;
import org.pmv.myspring.gijonevents.domain.usuario.Usuario;
import org.pmv.myspring.gijonevents.infra.out.persistence.repository.UsuarioRepositoryJpa;
import org.pmv.myspring.gijonevents.infra.out.security.jwt.JwtUtil;
import org.pmv.myspring.service.EmailService;
import org.pmv.myspring.service.TokenService;
import org.springframework.stereotype.Service;

import java.time.Instant;

@Service
@RequiredArgsConstructor
public class AuthService implements RegisterUserUseCase, LoginUserUseCase {

    private final UsuarioRepositoryJpa usuarioRepository;
    private final UserPort userPort;
    private final PasswordEncoderPort passwordEncoder;
    private final TokenService tokenService;
    private final EmailService emailService;
    private final JwtUtil jwtUtil;
    private final TokenGeneratorPort tokenGenerator;
    private final UserResultMapper mapper;
    //private final KafkaTemplate<String, String> kafkaTemplate;

//    public LoginUserResult login(LoginRequestDto loginRequest) throws UsuarioNotFoundException {
//        UsuarioEntity usuario = this.usuarioRepository.findByUsername(loginRequest.getUsername())
//                .orElseThrow(() -> new UsuarioNotFoundException("Usuario no encontrado"));
//
//        return LoginUserResult.builder().jwt(this.jwtUtil.generateToken(usuario)).build();
//
//    }

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

        if (this.userPort.existsByUsername(command.getUsername())) {
            throw new UsernameAlreadyExistsException(command.getUsername());
        }

        if (this.userPort.existsByEmail(command.getEmail())) {
            throw new EmailAlreadyExistsException(command.getEmail());
        }

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

        return this.mapper.toResult(save);

    }

    @Override
    public LoginUserResult login(LoginUserCommand command) {
        Usuario usuario = userPort.findByUsername(command.username());

        if (!usuario.isActivo()) {
            throw new UsuarioInactivoException();
        }

        if (!passwordEncoder.matches(command.password(), usuario.getPassword())) {
            throw new CredencialesInvalidasException();
        }

        String token = tokenGenerator.generateToken(usuario);

        return LoginUserResult.builder()
                .jwt(token)
                .username(usuario.getUsername())
                .role(usuario.getRole())
                .build();
    }
}
