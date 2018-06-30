package com.issste.sicabis.ejb.modelo;

import com.issste.sicabis.ejb.modelo.Area;
import com.issste.sicabis.ejb.modelo.CrInsumos;
import com.issste.sicabis.ejb.modelo.Estatus;
import com.issste.sicabis.ejb.modelo.UnidadResponsable;
import java.math.BigDecimal;
import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2018-06-22T12:18:02")
@StaticMetamodel(Cr.class)
public class Cr_ { 

    public static volatile SingularAttribute<Cr, Date> fechaModificaciones;
    public static volatile SingularAttribute<Cr, String> usuarioModificacion;
    public static volatile SingularAttribute<Cr, UnidadResponsable> idUnidadResponsable;
    public static volatile SingularAttribute<Cr, Integer> activo;
    public static volatile SingularAttribute<Cr, BigDecimal> importeTotal;
    public static volatile ListAttribute<Cr, CrInsumos> crInsumosList;
    public static volatile SingularAttribute<Cr, Integer> ejercicio;
    public static volatile SingularAttribute<Cr, Date> fechaBaja;
    public static volatile SingularAttribute<Cr, Integer> idCr;
    public static volatile SingularAttribute<Cr, Area> idArea;
    public static volatile SingularAttribute<Cr, String> usuarioBaja;
    public static volatile SingularAttribute<Cr, Date> fechaAlta;
    public static volatile SingularAttribute<Cr, String> numeroCr;
    public static volatile SingularAttribute<Cr, String> usuarioAlta;
    public static volatile SingularAttribute<Cr, Estatus> idEstatus;

}