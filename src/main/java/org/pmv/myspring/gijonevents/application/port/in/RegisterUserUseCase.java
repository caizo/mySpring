package org.pmv.myspring.gijonevents.application.port.in;

import org.pmv.myspring.gijonevents.application.port.in.result.RegisterUserResult;
import org.pmv.myspring.gijonevents.application.port.in.command.RegisterUserCommand;

public interface RegisterUserUseCase {

    RegisterUserResult register(RegisterUserCommand command);
}
