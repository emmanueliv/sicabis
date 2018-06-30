package com.issste.sicabis.ejb.modelo;

import com.issste.sicabis.ejb.modelo.Planeacion;
import com.issste.sicabis.ejb.modelo.Solicitudes;
import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2018-06-22T12:18:02")
@StaticMetamodel(TipoSolicitud.class)
public class TipoSolicitud_ { 

    public static volatile SingularAttribute<TipoSolicitud, String> usuarioModificacion;
    public static volatile SingularAttribute<TipoSolicitud, Date> fechaBaja;
    public static volatile SingularAttribute<TipoSolicitud, String> usuarioBaja;
    public static volatile SingularAttribute<TipoSolicitud, String> descripcion;
    public static volatile SingularAttribute<TipoSolicitud, Date> fechaAlta;
    public static volatile ListAttribute<TipoSolicitud, Solicitudes> solicitudesList;
    public static volatile SingularAttribute<TipoSolicitud, Date> fechaModificacion;
    public static volatile SingularAttribute<TipoSolicitud, Integer> idTipoSolicitud;
    public static volatile SingularAttribute<TipoSolicitud, String> usuarioAlta;
    public static volatile SingularAttribute<TipoSolicitud, Integer> activo;
    public static volatile ListAttribute<TipoSolicitud, Planeacion> planeacionList;

}