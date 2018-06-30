package com.issste.sicabis.ejb.modelo;

import com.issste.sicabis.ejb.modelo.Menu;
import com.issste.sicabis.ejb.modelo.Perfiles;
import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2018-06-22T12:18:02")
@StaticMetamodel(MenuPerfil.class)
public class MenuPerfil_ { 

    public static volatile SingularAttribute<MenuPerfil, Perfiles> idPerfil;
    public static volatile SingularAttribute<MenuPerfil, Integer> idMenuPerfil;
    public static volatile SingularAttribute<MenuPerfil, String> usuarioModificacion;
    public static volatile SingularAttribute<MenuPerfil, Date> fechaBaja;
    public static volatile SingularAttribute<MenuPerfil, String> usuarioBaja;
    public static volatile SingularAttribute<MenuPerfil, Date> fechaAlta;
    public static volatile SingularAttribute<MenuPerfil, Menu> idMenu;
    public static volatile SingularAttribute<MenuPerfil, Integer> consulta;
    public static volatile SingularAttribute<MenuPerfil, Date> fechaModificacion;
    public static volatile SingularAttribute<MenuPerfil, String> usuarioAlta;
    public static volatile SingularAttribute<MenuPerfil, Integer> activo;

}