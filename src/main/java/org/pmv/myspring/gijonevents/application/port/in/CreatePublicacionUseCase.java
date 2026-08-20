package org.pmv.myspring.gijonevents.application.port.in;


import org.pmv.myspring.gijonevents.application.port.in.command.CreatePublicacionCommand;
import org.pmv.myspring.gijonevents.application.port.in.result.CreatePublicacionResult;

public interface CreatePublicacionUseCase {

    CreatePublicacionResult create(CreatePublicacionCommand command);
}
