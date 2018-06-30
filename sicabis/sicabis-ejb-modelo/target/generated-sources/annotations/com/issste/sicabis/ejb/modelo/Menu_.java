package com.issste.sicabis.ejb.modelo;

import com.issste.sicabis.ejb.modelo.MenuPerfil;
import com.issste.sicabis.ejb.modelo.Tareas;
import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2018-06-22T12:18:02")
@StaticMetamodel(Menu.class)
public class Menu_ { 

    public static volatile SingularAttribute<Menu, Tareas> idTarea;
    public static volatile SingularAttribute<Menu, String> usuarioModificacion;
    public static volatile SingularAttribute<Menu, String> descripcion;
    public static volatile SingularAttribute<Menu, Integer> idMenu;
    public static volatile SingularAttribute<Menu, Integer> final1;
    public static volatile SingularAttribute<Menu, Integer> activo;
    public static volatile SingularAttribute<Menu, String> nombreArchivo;
    public static volatile SingularAttribute<Menu, Date> fechaBaja;
    public static volatile SingularAttribute<Menu, String> usuarioBaja;
    public static volatile SingularAttribute<Menu, Date> fechaAlta;
    public static volatile SingularAttribute<Menu, Date> fechaModificacion;
    public static volatile SingularAttribute<Menu, String> usuarioAlta;
    public static volatile ListAttribute<Menu, MenuPerfil> menuPerfilList;
    public static volatile SingularAttribute<Menu, Integer> idMenuPadre;

}