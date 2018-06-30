package com.issste.sicabis.ejb.modelo;

import com.issste.sicabis.ejb.modelo.AlertasEnvio;
import com.issste.sicabis.ejb.modelo.Dpn;
import com.issste.sicabis.ejb.modelo.InsumoDpn;
import com.issste.sicabis.ejb.modelo.UnidadesMedicas;
import java.math.BigDecimal;
import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2018-06-22T12:18:02")
@StaticMetamodel(DpnInsumos.class)
public class DpnInsumos_ { 

    public static volatile SingularAttribute<DpnInsumos, Integer> previo;
    public static volatile SingularAttribute<DpnInsumos, String> claveUnidad;
    public static volatile SingularAttribute<DpnInsumos, Integer> salidasSiam3;
    public static volatile SingularAttribute<DpnInsumos, Integer> idDpnInsumo;
    public static volatile SingularAttribute<DpnInsumos, String> claveInsumo;
    public static volatile ListAttribute<DpnInsumos, AlertasEnvio> alertasEnvioList;
    public static volatile SingularAttribute<DpnInsumos, Integer> existenciasSiam;
    public static volatile SingularAttribute<DpnInsumos, Integer> dpnSugerida;
    public static volatile SingularAttribute<DpnInsumos, Integer> activo;
    public static volatile SingularAttribute<DpnInsumos, Integer> surtimiento;
    public static volatile SingularAttribute<DpnInsumos, String> nombreUnidad;
    public static volatile SingularAttribute<DpnInsumos, BigDecimal> precioUnitario;
    public static volatile SingularAttribute<DpnInsumos, Integer> existenciasCenadi;
    public static volatile SingularAttribute<DpnInsumos, InsumoDpn> idInsumoDpn;
    public static volatile SingularAttribute<DpnInsumos, String> descripcionInsumo;
    public static volatile SingularAttribute<DpnInsumos, UnidadesMedicas> idUnidadesMedicas;
    public static volatile SingularAttribute<DpnInsumos, Integer> coberturas;
    public static volatile SingularAttribute<DpnInsumos, String> resultado;
    public static volatile SingularAttribute<DpnInsumos, Integer> piezasPropuestasDpn;
    public static volatile SingularAttribute<DpnInsumos, Integer> salidasSiam;
    public static volatile SingularAttribute<DpnInsumos, Integer> piezasDpnAnterior;
    public static volatile SingularAttribute<DpnInsumos, Integer> piezasDpn;
    public static volatile SingularAttribute<DpnInsumos, Dpn> idDpn;

}