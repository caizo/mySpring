package org.pmv.myspring.gijonevents.infra.out.security;

import lombok.RequiredArgsConstructor;
import org.pmv.myspring.gijonevents.application.port.out.PasswordEncoderPort;
import org.springframework.stereotype.Component;


@Component
@RequiredArgsConstructor
public class BCryptPasswordEncoderAdapter implements PasswordEncoderPort {

    private final org.springframework.security.crypto.password.PasswordEncoder delegate;

    @Override
    public String encode(String password) {
        return delegate.encode(password);
    }

    @Override
    public boolean matches(String rawPassword, String encodedPassword) {
        return delegate.matches(rawPassword, encodedPassword);
    }
}
