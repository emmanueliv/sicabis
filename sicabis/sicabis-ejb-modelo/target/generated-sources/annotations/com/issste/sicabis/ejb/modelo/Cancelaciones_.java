package com.issste.sicabis.ejb.modelo;

import com.issste.sicabis.ejb.modelo.DetalleOrdenSuministro;
import com.issste.sicabis.ejb.modelo.Estatus;
import java.math.BigDecimal;
import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2018-06-22T12:18:02")
@StaticMetamodel(Cancelaciones.class)
public class Cancelaciones_ { 

    public static volatile SingularAttribute<Cancelaciones, DetalleOrdenSuministro> idDetalleOrdenSuministro;
    public static volatile SingularAttribute<Cancelaciones, String> usuarioModificacion;
    public static volatile SingularAttribute<Cancelaciones, String> numeroCancelacion;
    public static volatile SingularAttribute<Cancelaciones, Integer> activo;
    public static volatile SingularAttribute<Cancelaciones, BigDecimal> importeTotal;
    public static volatile SingularAttribute<Cancelaciones, Integer> idCancelacion;
    public static volatile SingularAttribute<Cancelaciones, Date> fechaBaja;
    public static volatile SingularAttribute<Cancelaciones, String> usuarioBaja;
    public static volatile SingularAttribute<Cancelaciones, Date> fechaAlta;
    public static volatile SingularAttribute<Cancelaciones, Date> fechaModificacion;
    public static volatile SingularAttribute<Cancelaciones, Integer> procentajeIncumplimiento;
    public static volatile SingularAttribute<Cancelaciones, BigDecimal> pena;
    public static volatile SingularAttribute<Cancelaciones, String> usuarioAlta;
    public static volatile SingularAttribute<Cancelaciones, Estatus> idEstatus;

}