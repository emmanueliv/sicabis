package com.issste.sicabis.ejb.modelo;

import com.issste.sicabis.ejb.modelo.AlertasDpn;
import com.issste.sicabis.ejb.modelo.DpnInsumos;
import com.issste.sicabis.ejb.modelo.Estatus;
import java.math.BigDecimal;
import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2018-06-22T12:18:02")
@StaticMetamodel(AlertasEnvio.class)
public class AlertasEnvio_ { 

    public static volatile SingularAttribute<AlertasEnvio, String> usuarioModificacion;
    public static volatile SingularAttribute<AlertasEnvio, BigDecimal> consumo;
    public static volatile SingularAttribute<AlertasEnvio, DpnInsumos> idDpnInsumo;
    public static volatile SingularAttribute<AlertasEnvio, Integer> idAlertasEnvio;
    public static volatile SingularAttribute<AlertasEnvio, Integer> activo;
    public static volatile SingularAttribute<AlertasEnvio, Date> fechaBaja;
    public static volatile SingularAttribute<AlertasEnvio, String> usuarioBaja;
    public static volatile SingularAttribute<AlertasEnvio, AlertasDpn> idAlertasDpn;
    public static volatile SingularAttribute<AlertasEnvio, Date> fechaAlta;
    public static volatile SingularAttribute<AlertasEnvio, Date> fechaModificacion;
    public static volatile SingularAttribute<AlertasEnvio, Estatus> idEstatus;
    public static volatile SingularAttribute<AlertasEnvio, String> usuarioAlta;
    public static volatile SingularAttribute<AlertasEnvio, Integer> dpnSugeridaUmu;

}