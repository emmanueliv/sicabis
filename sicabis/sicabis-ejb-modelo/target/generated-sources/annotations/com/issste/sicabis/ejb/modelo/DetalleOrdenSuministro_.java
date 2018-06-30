package com.issste.sicabis.ejb.modelo;

import com.issste.sicabis.ejb.modelo.Cancelaciones;
import com.issste.sicabis.ejb.modelo.FalloProcedimientoRcb;
import com.issste.sicabis.ejb.modelo.OrdenSuministro;
import com.issste.sicabis.ejb.modelo.Remisiones;
import com.issste.sicabis.ejb.modelo.Rescisiones;
import java.math.BigDecimal;
import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2018-06-22T12:18:02")
@StaticMetamodel(DetalleOrdenSuministro.class)
public class DetalleOrdenSuministro_ { 

    public static volatile SingularAttribute<DetalleOrdenSuministro, BigDecimal> importe;
    public static volatile ListAttribute<DetalleOrdenSuministro, Cancelaciones> cancelacionesList;
    public static volatile SingularAttribute<DetalleOrdenSuministro, String> usuarioModificacion;
    public static volatile SingularAttribute<DetalleOrdenSuministro, Integer> idDetalleOrdenSuministro;
    public static volatile ListAttribute<DetalleOrdenSuministro, Remisiones> remisionesList;
    public static volatile SingularAttribute<DetalleOrdenSuministro, Integer> cantidadSuministrada;
    public static volatile SingularAttribute<DetalleOrdenSuministro, FalloProcedimientoRcb> idFalloProcedimientoRcb;
    public static volatile SingularAttribute<DetalleOrdenSuministro, OrdenSuministro> idOrdenSuministro;
    public static volatile SingularAttribute<DetalleOrdenSuministro, Integer> activo;
    public static volatile SingularAttribute<DetalleOrdenSuministro, Integer> idRepositorioDocumentos;
    public static volatile SingularAttribute<DetalleOrdenSuministro, Date> fechaEntregaInicial;
    public static volatile SingularAttribute<DetalleOrdenSuministro, Date> fechaEntregaFinal;
    public static volatile ListAttribute<DetalleOrdenSuministro, Rescisiones> rescisionesList;
    public static volatile SingularAttribute<DetalleOrdenSuministro, Integer> cantidadSuministrar;
    public static volatile SingularAttribute<DetalleOrdenSuministro, Integer> totalCancelado;
    public static volatile SingularAttribute<DetalleOrdenSuministro, Date> fechaBaja;
    public static volatile SingularAttribute<DetalleOrdenSuministro, String> usuarioBaja;
    public static volatile SingularAttribute<DetalleOrdenSuministro, Date> fechaAlta;
    public static volatile SingularAttribute<DetalleOrdenSuministro, Date> fechaModificacion;
    public static volatile SingularAttribute<DetalleOrdenSuministro, String> usuarioAlta;
    public static volatile SingularAttribute<DetalleOrdenSuministro, Integer> cancelado;

}