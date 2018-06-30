package com.issste.sicabis.ejb.modelo;

import com.issste.sicabis.ejb.modelo.Insumos;
import com.issste.sicabis.ejb.modelo.Pacientes;
import com.issste.sicabis.ejb.modelo.Solicitudes;
import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2018-06-22T12:18:02")
@StaticMetamodel(SolicitudesInsumosPacientes.class)
public class SolicitudesInsumosPacientes_ { 

    public static volatile SingularAttribute<SolicitudesInsumosPacientes, Solicitudes> idSolicitud;
    public static volatile SingularAttribute<SolicitudesInsumosPacientes, Pacientes> idPaciente;
    public static volatile SingularAttribute<SolicitudesInsumosPacientes, String> usuarioModificacion;
    public static volatile SingularAttribute<SolicitudesInsumosPacientes, Date> fechaBaja;
    public static volatile SingularAttribute<SolicitudesInsumosPacientes, Integer> cantidad;
    public static volatile SingularAttribute<SolicitudesInsumosPacientes, String> usuarioBaja;
    public static volatile SingularAttribute<SolicitudesInsumosPacientes, Insumos> idInsumo;
    public static volatile SingularAttribute<SolicitudesInsumosPacientes, Date> fechaAlta;
    public static volatile SingularAttribute<SolicitudesInsumosPacientes, Date> fechaModificacion;
    public static volatile SingularAttribute<SolicitudesInsumosPacientes, Integer> idSolicitudesInsumosPacientes;
    public static volatile SingularAttribute<SolicitudesInsumosPacientes, String> usuarioAlta;
    public static volatile SingularAttribute<SolicitudesInsumosPacientes, Integer> activo;

}