package org.pmv.myspring.gijonevents.application.port.in.command;

import org.pmv.myspring.gijonevents.domain.Direccion;
import org.pmv.myspring.gijonevents.domain.enums.CategoriaEmpresa;
import org.pmv.myspring.gijonevents.domain.enums.TipoUsuario;

public record RegisterUserCommand(
        String username,
        String email,
        String password,
        TipoUsuario tipo,
        EmpresaData empresa
) {

    public record EmpresaData(
            String nombre,
            String descripcion,
            CategoriaEmpresa categoria,
            Direccion direccion,
            String logo
    ) {
    }
}
