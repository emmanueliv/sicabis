package com.issste.sicabis.ejb.modelo;

import com.issste.sicabis.ejb.modelo.AlertasDpn;
import com.issste.sicabis.ejb.modelo.AlertasEnvio;
import com.issste.sicabis.ejb.modelo.Cancelaciones;
import com.issste.sicabis.ejb.modelo.Contratos;
import com.issste.sicabis.ejb.modelo.Cr;
import com.issste.sicabis.ejb.modelo.Dpn;
import com.issste.sicabis.ejb.modelo.EstatusSiguiente;
import com.issste.sicabis.ejb.modelo.Fallos;
import com.issste.sicabis.ejb.modelo.OrdenSuministro;
import com.issste.sicabis.ejb.modelo.Procedimientos;
import com.issste.sicabis.ejb.modelo.Rcb;
import com.issste.sicabis.ejb.modelo.Remisiones;
import com.issste.sicabis.ejb.modelo.Rescisiones;
import com.issste.sicabis.ejb.modelo.Solicitudes;
import com.issste.sicabis.ejb.modelo.Tareas;
import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2018-06-22T12:18:02")
@StaticMetamodel(Estatus.class)
public class Estatus_ { 

    public static volatile SingularAttribute<Estatus, Tareas> idTarea;
    public static volatile ListAttribute<Estatus, Fallos> fallosList;
    public static volatile ListAttribute<Estatus, Remisiones> remisionesList;
    public static volatile ListAttribute<Estatus, EstatusSiguiente> estatusSiguienteList;
    public static volatile ListAttribute<Estatus, OrdenSuministro> ordenSuministroList;
    public static volatile SingularAttribute<Estatus, String> nombre;
    public static volatile SingularAttribute<Estatus, Date> fechaBaja;
    public static volatile SingularAttribute<Estatus, String> usuarioBaja;
    public static volatile SingularAttribute<Estatus, Date> fechaModificacion;
    public static volatile ListAttribute<Estatus, Dpn> dpnList;
    public static volatile SingularAttribute<Estatus, Integer> idEstatus;
    public static volatile SingularAttribute<Estatus, String> usuarioModificacion;
    public static volatile ListAttribute<Estatus, Cancelaciones> cancelacionesList;
    public static volatile ListAttribute<Estatus, AlertasEnvio> alertasEnvioList;
    public static volatile ListAttribute<Estatus, Solicitudes> solicitudesList;
    public static volatile ListAttribute<Estatus, Cr> crList;
    public static volatile ListAttribute<Estatus, Procedimientos> procedimientosList;
    public static volatile SingularAttribute<Estatus, Integer> activo;
    public static volatile ListAttribute<Estatus, EstatusSiguiente> estatusSiguienteList1;
    public static volatile ListAttribute<Estatus, Rescisiones> rescisionesList;
    public static volatile SingularAttribute<Estatus, Date> fechaAlta;
    public static volatile ListAttribute<Estatus, AlertasDpn> alertasDpnList;
    public static volatile SingularAttribute<Estatus, String> usuarioAlta;
    public static volatile ListAttribute<Estatus, Rcb> rcbList;
    public static volatile ListAttribute<Estatus, Contratos> contratosList;

}