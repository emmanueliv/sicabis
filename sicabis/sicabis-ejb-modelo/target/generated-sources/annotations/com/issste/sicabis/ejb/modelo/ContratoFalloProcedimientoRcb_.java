package com.issste.sicabis.ejb.modelo;

import com.issste.sicabis.ejb.modelo.Contratos;
import com.issste.sicabis.ejb.modelo.FalloProcedimientoRcb;
import java.math.BigDecimal;
import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2018-06-22T12:18:02")
@StaticMetamodel(ContratoFalloProcedimientoRcb.class)
public class ContratoFalloProcedimientoRcb_ { 

    public static volatile SingularAttribute<ContratoFalloProcedimientoRcb, BigDecimal> precioUnitario;
    public static volatile SingularAttribute<ContratoFalloProcedimientoRcb, String> usuarioModificacion;
    public static volatile SingularAttribute<ContratoFalloProcedimientoRcb, String> nota;
    public static volatile SingularAttribute<ContratoFalloProcedimientoRcb, Date> fechaBaja;
    public static volatile SingularAttribute<ContratoFalloProcedimientoRcb, String> usuarioBaja;
    public static volatile SingularAttribute<ContratoFalloProcedimientoRcb, Integer> idContratoFalloProcedimientoRcb;
    public static volatile SingularAttribute<ContratoFalloProcedimientoRcb, Date> fechaAlta;
    public static volatile SingularAttribute<ContratoFalloProcedimientoRcb, Date> fechaModificacion;
    public static volatile SingularAttribute<ContratoFalloProcedimientoRcb, String> descripcionAmplia;
    public static volatile SingularAttribute<ContratoFalloProcedimientoRcb, FalloProcedimientoRcb> idFalloProcedimientoRcb;
    public static volatile SingularAttribute<ContratoFalloProcedimientoRcb, Contratos> idContrato;
    public static volatile SingularAttribute<ContratoFalloProcedimientoRcb, Integer> cantidadAgregadaConvenio;
    public static volatile SingularAttribute<ContratoFalloProcedimientoRcb, String> usuarioAlta;
    public static volatile SingularAttribute<ContratoFalloProcedimientoRcb, Integer> activo;
    public static volatile SingularAttribute<ContratoFalloProcedimientoRcb, String> unidadPiezaConvenio;

}