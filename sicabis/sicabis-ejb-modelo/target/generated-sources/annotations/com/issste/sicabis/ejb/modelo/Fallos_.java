package com.issste.sicabis.ejb.modelo;

import com.issste.sicabis.ejb.modelo.Estatus;
import com.issste.sicabis.ejb.modelo.FalloProcedimientoRcb;
import java.math.BigDecimal;
import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2018-06-22T12:18:02")
@StaticMetamodel(Fallos.class)
public class Fallos_ { 

    public static volatile SingularAttribute<Fallos, String> usuarioModificacion;
    public static volatile SingularAttribute<Fallos, String> numeroFallo;
    public static volatile ListAttribute<Fallos, FalloProcedimientoRcb> falloProcedimientoRcbList;
    public static volatile SingularAttribute<Fallos, String> comentarios;
    public static volatile SingularAttribute<Fallos, String> listaFallosDtoSelect;
    public static volatile SingularAttribute<Fallos, Integer> anioAfectacion;
    public static volatile SingularAttribute<Fallos, Integer> incomformidad;
    public static volatile SingularAttribute<Fallos, Integer> activo;
    public static volatile SingularAttribute<Fallos, BigDecimal> importeTotal;
    public static volatile SingularAttribute<Fallos, String> numeroProcedimiento;
    public static volatile SingularAttribute<Fallos, Integer> idFallo;
    public static volatile SingularAttribute<Fallos, Date> fechaBaja;
    public static volatile SingularAttribute<Fallos, String> listaFallosDto;
    public static volatile SingularAttribute<Fallos, String> usuarioBaja;
    public static volatile SingularAttribute<Fallos, Date> fechaFallo;
    public static volatile SingularAttribute<Fallos, Date> fechaAlta;
    public static volatile SingularAttribute<Fallos, Date> fechaModificacion;
    public static volatile SingularAttribute<Fallos, String> usuarioAlta;
    public static volatile SingularAttribute<Fallos, Estatus> idEstatus;

}