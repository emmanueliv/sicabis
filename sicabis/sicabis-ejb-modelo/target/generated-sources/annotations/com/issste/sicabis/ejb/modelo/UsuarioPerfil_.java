package com.issste.sicabis.ejb.modelo;

import com.issste.sicabis.ejb.modelo.Perfiles;
import com.issste.sicabis.ejb.modelo.Usuarios;
import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2018-06-22T12:18:02")
@StaticMetamodel(UsuarioPerfil.class)
public class UsuarioPerfil_ { 

    public static volatile SingularAttribute<UsuarioPerfil, Perfiles> idPerfil;
    public static volatile SingularAttribute<UsuarioPerfil, String> usuarioModificacion;
    public static volatile SingularAttribute<UsuarioPerfil, Usuarios> idUsuario;
    public static volatile SingularAttribute<UsuarioPerfil, Date> fechaBaja;
    public static volatile SingularAttribute<UsuarioPerfil, Integer> idPerfilUsuarios;
    public static volatile SingularAttribute<UsuarioPerfil, String> usuarioBaja;
    public static volatile SingularAttribute<UsuarioPerfil, Date> fechaAlta;
    public static volatile SingularAttribute<UsuarioPerfil, Date> fechaModificacion;
    public static volatile SingularAttribute<UsuarioPerfil, String> usuarioAlta;
    public static volatile SingularAttribute<UsuarioPerfil, Integer> activo;

}