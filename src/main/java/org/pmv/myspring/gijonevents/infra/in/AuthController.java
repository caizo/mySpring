package org.pmv.myspring.gijonevents.infra.in;

import lombok.RequiredArgsConstructor;
import org.pmv.myspring.gijonevents.application.exception.UsuarioNotFoundException;
import org.pmv.myspring.gijonevents.application.port.in.RegisterUserUseCase;
import org.pmv.myspring.gijonevents.application.port.in.command.RegisterUserCommand;
import org.pmv.myspring.gijonevents.application.port.in.result.RegisterUserResult;
import org.pmv.myspring.gijonevents.infra.in.rest.dto.RegisterUserRequestDto;
import org.pmv.myspring.gijonevents.infra.in.rest.dto.RegisterUserResponseDto;
import org.pmv.myspring.gijonevents.infra.in.rest.mapper.UserWebMapper;
import org.pmv.myspring.request.LoginRequest;
import org.pmv.myspring.response.AuthResponse;
import org.pmv.myspring.gijonevents.application.service.AuthService;
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
    private final UserWebMapper mapper;
    private final RegisterUserUseCase useCase;

    @PostMapping("/register")
    public ResponseEntity<RegisterUserResponseDto> register(@RequestBody RegisterUserRequestDto requestDto) {
        RegisterUserCommand command = this.mapper.mapCommand(requestDto);
        RegisterUserResult result = this.useCase.register(command);
        RegisterUserResponseDto responseDto = this.mapper.toResponse(result);
        return ResponseEntity.ok(responseDto);
    }


    @PostMapping("/logout")
    public ResponseEntity<?> logout(@RequestHeader("Authorization") String token) {
        this.authService.logout(token);
        return ResponseEntity.ok().build();
    }


    @PostMapping("/login")
    public ResponseEntity<AuthResponse> login(@RequestBody LoginRequest loginRequest) throws UsuarioNotFoundException {
        UsernamePasswordAuthenticationToken authentication =
                new UsernamePasswordAuthenticationToken(
                        loginRequest.getUsername(),
                        loginRequest.getPassword()
                );
        this.authenticationManager.authenticate(authentication);
        AuthResponse login = this.authService.login(loginRequest);
        return ResponseEntity.ok(login);
    }


}