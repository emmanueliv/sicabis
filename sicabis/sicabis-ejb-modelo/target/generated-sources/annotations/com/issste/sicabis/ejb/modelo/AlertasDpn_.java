package com.issste.sicabis.ejb.modelo;

import com.issste.sicabis.ejb.modelo.AlertasEnvio;
import com.issste.sicabis.ejb.modelo.Estatus;
import com.issste.sicabis.ejb.modelo.Ur;
import com.issste.sicabis.ejb.modelo.Usuarios;
import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2018-06-22T12:18:02")
@StaticMetamodel(AlertasDpn.class)
public class AlertasDpn_ { 

    public static volatile SingularAttribute<AlertasDpn, Integer> anio;
    public static volatile SingularAttribute<AlertasDpn, Usuarios> idUsuario;
    public static volatile SingularAttribute<AlertasDpn, Integer> mes;
    public static volatile SingularAttribute<AlertasDpn, Integer> idAlertasDpn;
    public static volatile SingularAttribute<AlertasDpn, Ur> ur;
    public static volatile ListAttribute<AlertasDpn, AlertasEnvio> alertasEnvioList;
    public static volatile SingularAttribute<AlertasDpn, Estatus> idEstatus;
    public static volatile SingularAttribute<AlertasDpn, Integer> activo;

}