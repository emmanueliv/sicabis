package com.issste.sicabis.ejb.modelo;

import com.issste.sicabis.ejb.modelo.DetalleRecoleccion;
import com.issste.sicabis.ejb.modelo.UnidadesMedicas;
import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2018-06-22T12:18:02")
@StaticMetamodel(Recoleccion.class)
public class Recoleccion_ { 

    public static volatile SingularAttribute<Recoleccion, Date> fechaModificaciones;
    public static volatile SingularAttribute<Recoleccion, String> usuarioModificacion;
    public static volatile SingularAttribute<Recoleccion, String> oficioRecoleccion;
    public static volatile SingularAttribute<Recoleccion, Date> fechaBaja;
    public static volatile SingularAttribute<Recoleccion, UnidadesMedicas> idUnidadesMedicas;
    public static volatile SingularAttribute<Recoleccion, Integer> idRecoleccion;
    public static volatile SingularAttribute<Recoleccion, String> usuarioBaja;
    public static volatile SingularAttribute<Recoleccion, Date> fechaAlta;
    public static volatile SingularAttribute<Recoleccion, String> folioRecoleccion;
    public static volatile SingularAttribute<Recoleccion, String> usuarioAlta;
    public static volatile SingularAttribute<Recoleccion, Integer> activo;
    public static volatile ListAttribute<Recoleccion, DetalleRecoleccion> detalleRecoleccionList;

}