package org.pmv.myspring.gijonevents.infra.in.rest;

import lombok.RequiredArgsConstructor;
import org.pmv.myspring.gijonevents.application.exception.UsuarioNotFoundException;
import org.pmv.myspring.gijonevents.application.port.in.LoginUserUseCase;
import org.pmv.myspring.gijonevents.application.port.in.RegisterUserUseCase;
import org.pmv.myspring.gijonevents.application.port.in.command.LoginUserCommand;
import org.pmv.myspring.gijonevents.application.port.in.command.RegisterUserCommand;
import org.pmv.myspring.gijonevents.application.port.in.result.LoginUserResult;
import org.pmv.myspring.gijonevents.application.port.in.result.RegisterUserResult;
import org.pmv.myspring.gijonevents.application.service.AuthService;
import org.pmv.myspring.gijonevents.infra.in.rest.dto.LoginRequestDto;
import org.pmv.myspring.gijonevents.infra.in.rest.dto.RegisterUserRequestDto;
import org.pmv.myspring.gijonevents.infra.in.rest.dto.RegisterUserResponseDto;
import org.pmv.myspring.gijonevents.infra.in.rest.mapper.UserWebMapper;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth")
@CrossOrigin(origins = "http://localhost:4200")
@RequiredArgsConstructor
public class AuthController {

    private final AuthService authService;
    private final UserWebMapper mapper;
    private final RegisterUserUseCase registerUserUseCase;
    private final LoginUserUseCase loginUserUseCase;

    @PostMapping("/register")
    public ResponseEntity<RegisterUserResponseDto> register(@RequestBody RegisterUserRequestDto requestDto) {
        RegisterUserCommand command = this.mapper.mapCommand(requestDto);
        RegisterUserResult result = this.registerUserUseCase.register(command);
        RegisterUserResponseDto responseDto = this.mapper.toResponse(result);
        return ResponseEntity.ok(responseDto);
    }


    @PostMapping("/logout")
    public ResponseEntity<?> logout(@RequestHeader("Authorization") String token) {
        this.authService.logout(token);
        return ResponseEntity.ok().build();
    }


    @PostMapping("/login")
    public ResponseEntity<LoginUserResult> login(@RequestBody LoginRequestDto requestDto) throws UsuarioNotFoundException {
        LoginUserResult login = this.loginUserUseCase.login(
                new LoginUserCommand(requestDto.getUsername(), requestDto.getPassword())
        );
        return ResponseEntity.ok(login);
    }


}