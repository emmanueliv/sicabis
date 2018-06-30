package com.issste.sicabis.ejb.modelo;

import com.issste.sicabis.ejb.modelo.ProcedimientoRcb;
import com.issste.sicabis.ejb.modelo.Proveedores;
import java.math.BigDecimal;
import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2018-06-22T12:18:02")
@StaticMetamodel(Propuestas.class)
public class Propuestas_ { 

    public static volatile SingularAttribute<Propuestas, BigDecimal> importe;
    public static volatile SingularAttribute<Propuestas, String> usuarioModificacion;
    public static volatile SingularAttribute<Propuestas, Integer> idPropuesta;
    public static volatile SingularAttribute<Propuestas, Integer> ganador;
    public static volatile SingularAttribute<Propuestas, Date> aperturaRealizada;
    public static volatile SingularAttribute<Propuestas, BigDecimal> precioUnitarioDescuento;
    public static volatile SingularAttribute<Propuestas, Integer> activo;
    public static volatile SingularAttribute<Propuestas, BigDecimal> precioUnitario;
    public static volatile SingularAttribute<Propuestas, ProcedimientoRcb> idProcedimientoRcb;
    public static volatile SingularAttribute<Propuestas, BigDecimal> descuentoOtorgado;
    public static volatile SingularAttribute<Propuestas, Proveedores> idProveedor;
    public static volatile SingularAttribute<Propuestas, Date> fechaBaja;
    public static volatile SingularAttribute<Propuestas, String> usuarioBaja;
    public static volatile SingularAttribute<Propuestas, Date> fechaAlta;
    public static volatile SingularAttribute<Propuestas, Date> fechaModificacion;
    public static volatile SingularAttribute<Propuestas, String> usuarioAlta;
    public static volatile SingularAttribute<Propuestas, Date> aperturaProgramada;

}