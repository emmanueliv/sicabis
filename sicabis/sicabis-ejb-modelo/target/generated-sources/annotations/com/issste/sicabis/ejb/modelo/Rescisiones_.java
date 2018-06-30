package com.issste.sicabis.ejb.modelo;

import com.issste.sicabis.ejb.modelo.DetalleOrdenSuministro;
import com.issste.sicabis.ejb.modelo.Estatus;
import java.math.BigDecimal;
import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2018-06-22T12:18:02")
@StaticMetamodel(Rescisiones.class)
public class Rescisiones_ { 

    public static volatile SingularAttribute<Rescisiones, BigDecimal> importe;
    public static volatile SingularAttribute<Rescisiones, Integer> idRescision;
    public static volatile SingularAttribute<Rescisiones, String> usuarioModificacion;
    public static volatile SingularAttribute<Rescisiones, DetalleOrdenSuministro> idDetalleOrdenSuministro;
    public static volatile SingularAttribute<Rescisiones, Date> fechaBaja;
    public static volatile SingularAttribute<Rescisiones, String> usuarioBaja;
    public static volatile SingularAttribute<Rescisiones, Date> fechaAlta;
    public static volatile SingularAttribute<Rescisiones, Date> fechaModificacion;
    public static volatile SingularAttribute<Rescisiones, Estatus> idEstatus;
    public static volatile SingularAttribute<Rescisiones, String> usuarioAlta;
    public static volatile SingularAttribute<Rescisiones, String> numeroRecision;
    public static volatile SingularAttribute<Rescisiones, Integer> activo;

}