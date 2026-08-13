package org.pmv.myspring.gijonevents.domain.empresa;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.pmv.myspring.gijonevents.domain.enums.CategoriaEmpresa;
import org.pmv.myspring.gijonevents.domain.Direccion;
import org.pmv.myspring.gijonevents.domain.usuario.UsuarioId;

import java.util.UUID;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Empresa {

    private EmpresaId id;
    private UsuarioId usuarioId;
    private String nombre;
    private String descripcion;
    private CategoriaEmpresa categoria;
    private Direccion direccion;
    private String logo;
    private boolean activa;

    public static Empresa create(
            UsuarioId usuarioId,
            String nombre,
            String descripcion,
            CategoriaEmpresa categoria,
            Direccion direccion,
            String logo
    ) {
        return new Empresa(
                EmpresaId.generate(),
                usuarioId,
                nombre,
                descripcion,
                categoria,
                direccion,
                logo,
                true
        );
    }

    public static Empresa reconstitute(
            EmpresaId id,
            UsuarioId usuarioId,
            String nombre,
            String descripcion,
            CategoriaEmpresa categoria,
            Direccion direccion,
            String logo,
            boolean activa
    ) {
        return new Empresa(
                id,
                usuarioId,
                nombre,
                descripcion,
                categoria,
                direccion,
                logo,
                activa
        );
    }
}