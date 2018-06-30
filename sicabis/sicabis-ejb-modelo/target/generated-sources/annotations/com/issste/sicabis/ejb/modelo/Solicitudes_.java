package com.issste.sicabis.ejb.modelo;

import com.issste.sicabis.ejb.modelo.Area;
import com.issste.sicabis.ejb.modelo.Estatus;
import com.issste.sicabis.ejb.modelo.SolicitudesInsumosPacientes;
import com.issste.sicabis.ejb.modelo.TipoSolicitud;
import com.issste.sicabis.ejb.modelo.UnidadesMedicas;
import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2018-06-22T12:18:02")
@StaticMetamodel(Solicitudes.class)
public class Solicitudes_ { 

    public static volatile SingularAttribute<Solicitudes, Integer> idSolicitud;
    public static volatile SingularAttribute<Solicitudes, String> periodoSolicitar;
    public static volatile SingularAttribute<Solicitudes, String> usuarioModificacion;
    public static volatile SingularAttribute<Solicitudes, String> numeroSolicitud;
    public static volatile ListAttribute<Solicitudes, SolicitudesInsumosPacientes> solicitudesInsumosPacientesList;
    public static volatile SingularAttribute<Solicitudes, TipoSolicitud> idTipoSolicitud;
    public static volatile SingularAttribute<Solicitudes, Date> fechaSolicitud;
    public static volatile SingularAttribute<Solicitudes, Integer> activo;
    public static volatile SingularAttribute<Solicitudes, UnidadesMedicas> idUnidadesMedicas;
    public static volatile SingularAttribute<Solicitudes, Date> fechaBaja;
    public static volatile SingularAttribute<Solicitudes, Area> idArea;
    public static volatile SingularAttribute<Solicitudes, String> usuarioBaja;
    public static volatile SingularAttribute<Solicitudes, Date> fechaAlta;
    public static volatile SingularAttribute<Solicitudes, Date> fechaModificacion;
    public static volatile SingularAttribute<Solicitudes, String> usuarioAlta;
    public static volatile SingularAttribute<Solicitudes, Estatus> idEstatus;

}