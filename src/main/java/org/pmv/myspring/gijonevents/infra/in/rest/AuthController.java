package org.pmv.myspring.gijonevents.infra.in.rest;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.pmv.myspring.gijonevents.infra.in.rest.exception.UsuarioNotFoundException;
import org.pmv.myspring.gijonevents.application.port.in.LoginUserUseCase;
import org.pmv.myspring.gijonevents.application.port.in.RegisterUserUseCase;
import org.pmv.myspring.gijonevents.application.port.in.command.LoginUserCommand;
import org.pmv.myspring.gijonevents.application.port.in.command.RegisterUserCommand;
import org.pmv.myspring.gijonevents.application.port.in.result.LoginUserResult;
import org.pmv.myspring.gijonevents.application.port.in.result.RegisterUserResult;
import org.pmv.myspring.gijonevents.infra.in.rest.dto.LoginRequestDto;
import org.pmv.myspring.gijonevents.infra.in.rest.dto.LoginUserResponseDto;
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

    private final UserWebMapper mapper;
    private final RegisterUserUseCase registerUserUseCase;
    private final LoginUserUseCase loginUserUseCase;

    @PostMapping("/register")
    public ResponseEntity<RegisterUserResponseDto> register(@RequestBody @Valid RegisterUserRequestDto requestDto) {
        RegisterUserCommand command = mapper.mapCommand(requestDto);
        RegisterUserResult result = registerUserUseCase.register(command);
        RegisterUserResponseDto responseDto = mapper.toResponse(result);
        return ResponseEntity.ok(responseDto);
    }

    @PostMapping("/login")
    public ResponseEntity<LoginUserResponseDto> login(@RequestBody @Valid LoginRequestDto requestDto) throws UsuarioNotFoundException {
        LoginUserResult result = loginUserUseCase.login(new LoginUserCommand(requestDto.getUsername(), requestDto.getPassword()));
        LoginUserResponseDto responseDto = mapper.toLoginResponseDto(result);
        return ResponseEntity.ok(responseDto);
    }


}