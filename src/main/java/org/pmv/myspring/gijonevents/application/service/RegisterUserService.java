package org.pmv.myspring.gijonevents.application.service;

import lombok.RequiredArgsConstructor;
import org.pmv.myspring.gijonevents.application.port.in.RegisterUserUseCase;
import org.pmv.myspring.gijonevents.application.port.in.command.RegisterUserCommand;
import org.pmv.myspring.gijonevents.application.port.in.result.RegisterUserResult;
import org.pmv.myspring.gijonevents.application.port.out.EmpresaRepository;
import org.pmv.myspring.gijonevents.application.port.out.UsuarioRepository;
import org.pmv.myspring.gijonevents.domain.empresa.Empresa;
import org.pmv.myspring.gijonevents.domain.enums.TipoUsuario;
import org.pmv.myspring.gijonevents.domain.usuario.Usuario;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class RegisterUserService implements RegisterUserUseCase {

    private final UsuarioRepository usuarioRepository;
    private final EmpresaRepository empresaRepository;
    private final PasswordEncoder passwordEncoder;

    @Override
    public RegisterUserResult register(RegisterUserCommand command) {

        validateUsername(command.username());
        validateEmail(command.email());
        validatePassword(command.password());

        if (usuarioRepository.existsByUsername(command.username())) {
            throw new IllegalArgumentException("Username already exists");
        }

        if (usuarioRepository.existsByEmail(command.email())) {
            throw new IllegalArgumentException("Email already exists");
        }

        validateUserType(command);

        Usuario usuario = Usuario.create(
                command.username(),
                command.email(),
                passwordEncoder.encode(command.password()),
                command.tipo()

        );

        Usuario savedUser = usuarioRepository.save(usuario);

        if (command.tipo() == TipoUsuario.EMPRESA) {

            RegisterUserCommand.EmpresaData empresaData = command.empresa();

            Empresa empresa = Empresa.create(
                    savedUser.getId(),
                    empresaData.nombre(),
                    empresaData.descripcion(),
                    empresaData.categoria(),
                    empresaData.direccion(),
                    empresaData.logo()
            );

            empresaRepository.save(empresa);
        }

        return new RegisterUserResult(
                savedUser.getNombre(),
                savedUser.getEmail(),
                savedUser.getTipo()
        );
    }

    private void validateUsername(String username) {

        if (username == null || username.isBlank()) {
            throw new IllegalArgumentException("Username is required");
        }
    }

    private void validateEmail(String email) {

        if (email == null || email.isBlank()) {
            throw new IllegalArgumentException("Email is required");
        }
    }

    private void validatePassword(String password) {

        if (password == null || password.length() < 8) {
            throw new IllegalArgumentException(
                    "Password must contain at least 8 characters"
            );
        }
    }

    private void validateUserType(RegisterUserCommand command) {

        if (command.tipo() == null) {
            throw new IllegalArgumentException("User type is required");
        }

        if (command.tipo() == TipoUsuario.EMPRESA
                && command.empresa() == null) {

            throw new IllegalArgumentException(
                    "Company data is required for company users"
            );
        }

        if (command.tipo() == TipoUsuario.PERSONA
                && command.empresa() != null) {

            throw new IllegalArgumentException(
                    "Company data is not allowed for person users"
            );
        }
    }
}
