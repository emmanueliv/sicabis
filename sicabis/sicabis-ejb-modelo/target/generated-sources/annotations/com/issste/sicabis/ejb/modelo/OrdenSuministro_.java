package com.issste.sicabis.ejb.modelo;

import com.issste.sicabis.ejb.modelo.Contratos;
import com.issste.sicabis.ejb.modelo.DetalleOrdenSuministro;
import com.issste.sicabis.ejb.modelo.Estatus;
import com.issste.sicabis.ejb.modelo.ProcedimientoRcbDestinosOrden;
import java.math.BigDecimal;
import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2018-06-22T12:18:02")
@StaticMetamodel(OrdenSuministro.class)
public class OrdenSuministro_ { 

    public static volatile ListAttribute<OrdenSuministro, DetalleOrdenSuministro> detalleOrdenSuministroList;
    public static volatile SingularAttribute<OrdenSuministro, BigDecimal> importe;
    public static volatile SingularAttribute<OrdenSuministro, String> usuarioModificacion;
    public static volatile SingularAttribute<OrdenSuministro, Contratos> idContrato;
    public static volatile SingularAttribute<OrdenSuministro, String> observaciones;
    public static volatile SingularAttribute<OrdenSuministro, Integer> idOrdenSuministro;
    public static volatile SingularAttribute<OrdenSuministro, Integer> activo;
    public static volatile SingularAttribute<OrdenSuministro, Integer> preOrden;
    public static volatile SingularAttribute<OrdenSuministro, Date> fechaOrden;
    public static volatile SingularAttribute<OrdenSuministro, Date> fechaBaja;
    public static volatile SingularAttribute<OrdenSuministro, Integer> cantidadSuministrar;
    public static volatile ListAttribute<OrdenSuministro, ProcedimientoRcbDestinosOrden> procedimientoRcbDestinosOrdenList;
    public static volatile SingularAttribute<OrdenSuministro, String> usuarioBaja;
    public static volatile SingularAttribute<OrdenSuministro, Date> fechaAlta;
    public static volatile SingularAttribute<OrdenSuministro, Date> fechaModificacion;
    public static volatile SingularAttribute<OrdenSuministro, String> usuarioAlta;
    public static volatile SingularAttribute<OrdenSuministro, Estatus> idEstatus;
    public static volatile SingularAttribute<OrdenSuministro, String> numeroOrden;

}