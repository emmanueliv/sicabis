package com.issste.sicabis.ejb.modelo;

import com.issste.sicabis.ejb.modelo.Insumos;
import com.issste.sicabis.ejb.modelo.Recoleccion;
import java.math.BigDecimal;
import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2018-06-22T12:18:02")
@StaticMetamodel(DetalleRecoleccion.class)
public class DetalleRecoleccion_ { 

    public static volatile SingularAttribute<DetalleRecoleccion, Date> fechaModificaciones;
    public static volatile SingularAttribute<DetalleRecoleccion, String> lote;
    public static volatile SingularAttribute<DetalleRecoleccion, String> usuarioModificacion;
    public static volatile SingularAttribute<DetalleRecoleccion, Date> fechaBaja;
    public static volatile SingularAttribute<DetalleRecoleccion, Recoleccion> idRecoleccion;
    public static volatile SingularAttribute<DetalleRecoleccion, BigDecimal> precioPromedio;
    public static volatile SingularAttribute<DetalleRecoleccion, String> usuarioBaja;
    public static volatile SingularAttribute<DetalleRecoleccion, Integer> cantidad;
    public static volatile SingularAttribute<DetalleRecoleccion, Integer> idDetalleRecoleccion;
    public static volatile SingularAttribute<DetalleRecoleccion, Insumos> idInsumo;
    public static volatile SingularAttribute<DetalleRecoleccion, Date> fechaAlta;
    public static volatile SingularAttribute<DetalleRecoleccion, Date> fechaCaducidad;
    public static volatile SingularAttribute<DetalleRecoleccion, String> usuarioAlta;
    public static volatile SingularAttribute<DetalleRecoleccion, Integer> activo;

}