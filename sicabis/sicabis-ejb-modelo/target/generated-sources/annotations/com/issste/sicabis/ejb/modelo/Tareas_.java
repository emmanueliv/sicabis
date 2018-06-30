package com.issste.sicabis.ejb.modelo;

import com.issste.sicabis.ejb.modelo.Area;
import com.issste.sicabis.ejb.modelo.Estatus;
import com.issste.sicabis.ejb.modelo.Menu;
import com.issste.sicabis.ejb.modelo.TipoDocumentos;
import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2018-06-22T12:18:02")
@StaticMetamodel(Tareas.class)
public class Tareas_ { 

    public static volatile SingularAttribute<Tareas, Integer> idTarea;
    public static volatile ListAttribute<Tareas, Estatus> estatusList;
    public static volatile SingularAttribute<Tareas, String> usuarioModificacion;
    public static volatile SingularAttribute<Tareas, Date> fechaBaja;
    public static volatile SingularAttribute<Tareas, String> usuarioBaja;
    public static volatile ListAttribute<Tareas, TipoDocumentos> tipoDocumentosList;
    public static volatile ListAttribute<Tareas, Area> areaList;
    public static volatile SingularAttribute<Tareas, String> descripcion;
    public static volatile ListAttribute<Tareas, Menu> menuList;
    public static volatile SingularAttribute<Tareas, Date> fechaAlta;
    public static volatile SingularAttribute<Tareas, Date> fechaModificacion;
    public static volatile SingularAttribute<Tareas, String> usuarioAlta;

}