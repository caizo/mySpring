package org.pmv.myspring.gijonevents.application.port.out;

import org.pmv.myspring.gijonevents.domain.empresa.Empresa;

public interface EmpresaRepository {
    Empresa save(Empresa empresa);
}
