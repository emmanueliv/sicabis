package com.issste.sicabis.ejb.modelo;

import com.issste.sicabis.ejb.modelo.MenuPerfil;
import com.issste.sicabis.ejb.modelo.UsuarioPerfil;
import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2018-06-22T12:18:02")
@StaticMetamodel(Perfiles.class)
public class Perfiles_ { 

    public static volatile ListAttribute<Perfiles, UsuarioPerfil> usuarioPerfilList;
    public static volatile SingularAttribute<Perfiles, String> usuarioModificacion;
    public static volatile SingularAttribute<Perfiles, Integer> consulta;
    public static volatile SingularAttribute<Perfiles, Integer> administrador;
    public static volatile SingularAttribute<Perfiles, Integer> activo;
    public static volatile SingularAttribute<Perfiles, Integer> idPerfil;
    public static volatile SingularAttribute<Perfiles, String> nombre;
    public static volatile SingularAttribute<Perfiles, Date> fechaBaja;
    public static volatile SingularAttribute<Perfiles, String> usuarioBaja;
    public static volatile SingularAttribute<Perfiles, Date> fechaAlta;
    public static volatile SingularAttribute<Perfiles, Date> fechaModificacion;
    public static volatile SingularAttribute<Perfiles, Integer> edita;
    public static volatile SingularAttribute<Perfiles, String> usuarioAlta;
    public static volatile ListAttribute<Perfiles, MenuPerfil> menuPerfilList;

}