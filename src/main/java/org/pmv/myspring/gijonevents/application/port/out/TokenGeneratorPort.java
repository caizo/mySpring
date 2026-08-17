package org.pmv.myspring.gijonevents.application.port.out;

import org.pmv.myspring.gijonevents.domain.usuario.Usuario;

public interface TokenGeneratorPort {
    String generateToken(Usuario usuario);
}
