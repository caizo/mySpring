package org.pmv.myspring.gijonevents.application.port.in;

import org.pmv.myspring.gijonevents.application.port.in.command.LoginUserCommand;
import org.pmv.myspring.gijonevents.application.port.in.result.LoginUserResult;

public interface LoginUserUseCase {

    LoginUserResult login(LoginUserCommand command);
}
