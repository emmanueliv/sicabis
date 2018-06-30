package com.issste.sicabis.ejb.modelo;

import com.issste.sicabis.ejb.modelo.ContratoFalloProcedimientoRcb;
import com.issste.sicabis.ejb.modelo.DetalleOrdenSuministro;
import com.issste.sicabis.ejb.modelo.Fallos;
import com.issste.sicabis.ejb.modelo.ProcedimientoRcb;
import com.issste.sicabis.ejb.modelo.Proveedores;
import java.math.BigDecimal;
import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2018-06-22T12:18:02")
@StaticMetamodel(FalloProcedimientoRcb.class)
public class FalloProcedimientoRcb_ { 

    public static volatile ListAttribute<FalloProcedimientoRcb, DetalleOrdenSuministro> detalleOrdenSuministroList;
    public static volatile ListAttribute<FalloProcedimientoRcb, ContratoFalloProcedimientoRcb> contratoFalloProcedimientoRcbList;
    public static volatile SingularAttribute<FalloProcedimientoRcb, Integer> completadoContrato;
    public static volatile SingularAttribute<FalloProcedimientoRcb, BigDecimal> importe;
    public static volatile SingularAttribute<FalloProcedimientoRcb, String> usuarioModificacion;
    public static volatile SingularAttribute<FalloProcedimientoRcb, BigDecimal> precioUnitarioOriginal;
    public static volatile SingularAttribute<FalloProcedimientoRcb, Integer> idFalloProcedimientoRcb;
    public static volatile SingularAttribute<FalloProcedimientoRcb, Integer> activo;
    public static volatile SingularAttribute<FalloProcedimientoRcb, Integer> porcentajeAdjudicacion;
    public static volatile SingularAttribute<FalloProcedimientoRcb, Integer> suministradoOrden;
    public static volatile SingularAttribute<FalloProcedimientoRcb, Integer> cantidadPiezas;
    public static volatile SingularAttribute<FalloProcedimientoRcb, BigDecimal> precioUnitario;
    public static volatile SingularAttribute<FalloProcedimientoRcb, ProcedimientoRcb> idProcedimientoRcb;
    public static volatile SingularAttribute<FalloProcedimientoRcb, Fallos> idFallo;
    public static volatile SingularAttribute<FalloProcedimientoRcb, Date> fechaBaja;
    public static volatile SingularAttribute<FalloProcedimientoRcb, Proveedores> idProveedor;
    public static volatile SingularAttribute<FalloProcedimientoRcb, String> usuarioBaja;
    public static volatile SingularAttribute<FalloProcedimientoRcb, Date> fechaAlta;
    public static volatile SingularAttribute<FalloProcedimientoRcb, Date> fechaModificacion;
    public static volatile SingularAttribute<FalloProcedimientoRcb, Integer> cantidadAgregadaConvenio;
    public static volatile SingularAttribute<FalloProcedimientoRcb, String> usuarioAlta;
    public static volatile SingularAttribute<FalloProcedimientoRcb, Integer> cantidadModificada;
    public static volatile SingularAttribute<FalloProcedimientoRcb, String> unidadPiezaConvenio;

}