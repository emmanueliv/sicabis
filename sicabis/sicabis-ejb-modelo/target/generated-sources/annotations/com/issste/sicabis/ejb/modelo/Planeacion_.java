package com.issste.sicabis.ejb.modelo;

import com.issste.sicabis.ejb.modelo.Area;
import com.issste.sicabis.ejb.modelo.PlaneacionDetalle;
import com.issste.sicabis.ejb.modelo.TipoSolicitud;
import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2018-06-22T12:18:02")
@StaticMetamodel(Planeacion.class)
public class Planeacion_ { 

    public static volatile SingularAttribute<Planeacion, Date> fechaFinal;
    public static volatile SingularAttribute<Planeacion, String> usuarioModificacion;
    public static volatile SingularAttribute<Planeacion, Integer> idPlaneacion;
    public static volatile SingularAttribute<Planeacion, TipoSolicitud> idTipoSolicitud;
    public static volatile SingularAttribute<Planeacion, Integer> activo;
    public static volatile SingularAttribute<Planeacion, String> numeroPlaneacion;
    public static volatile SingularAttribute<Planeacion, Date> fechaBaja;
    public static volatile SingularAttribute<Planeacion, Date> fechaInicial;
    public static volatile ListAttribute<Planeacion, PlaneacionDetalle> planeacionDetalleList;
    public static volatile SingularAttribute<Planeacion, Area> idArea;
    public static volatile SingularAttribute<Planeacion, String> usuarioBaja;
    public static volatile SingularAttribute<Planeacion, Date> fechaAlta;
    public static volatile SingularAttribute<Planeacion, Integer> mesesProyeccion;
    public static volatile SingularAttribute<Planeacion, String> periodoProyeccion;
    public static volatile SingularAttribute<Planeacion, Date> fechaModificacion;
    public static volatile SingularAttribute<Planeacion, String> usuarioAlta;

}