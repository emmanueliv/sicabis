package com.issste.sicabis.ejb.modelo;

import com.issste.sicabis.ejb.modelo.UsuariosTipoUsuarios;
import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2018-06-22T12:18:02")
@StaticMetamodel(TipoUsuarios.class)
public class TipoUsuarios_ { 

    public static volatile SingularAttribute<TipoUsuarios, String> nombre;
    public static volatile SingularAttribute<TipoUsuarios, String> usuarioModificacion;
    public static volatile SingularAttribute<TipoUsuarios, Date> fechaBaja;
    public static volatile SingularAttribute<TipoUsuarios, String> usuarioBaja;
    public static volatile SingularAttribute<TipoUsuarios, Integer> idTipoUsuario;
    public static volatile SingularAttribute<TipoUsuarios, Date> fechaAlta;
    public static volatile SingularAttribute<TipoUsuarios, Date> fechaModificacion;
    public static volatile ListAttribute<TipoUsuarios, UsuariosTipoUsuarios> usuariosTipoUsuariosList;
    public static volatile SingularAttribute<TipoUsuarios, String> usuarioAlta;
    public static volatile SingularAttribute<TipoUsuarios, Integer> activo;

}