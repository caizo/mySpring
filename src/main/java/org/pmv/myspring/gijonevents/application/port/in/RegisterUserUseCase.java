package org.pmv.myspring.gijonevents.application.port.in;

import org.pmv.myspring.gijonevents.application.port.in.command.RegisterUserCommand;
import org.pmv.myspring.gijonevents.application.port.in.result.RegisterUserResult;

public interface RegisterUserUseCase {
    RegisterUserResult register(RegisterUserCommand command);
}
