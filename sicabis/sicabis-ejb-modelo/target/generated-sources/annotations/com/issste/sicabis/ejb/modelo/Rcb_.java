package com.issste.sicabis.ejb.modelo;

import com.issste.sicabis.ejb.modelo.Area;
import com.issste.sicabis.ejb.modelo.Estatus;
import com.issste.sicabis.ejb.modelo.Jefatura;
import com.issste.sicabis.ejb.modelo.RcbGrupo;
import com.issste.sicabis.ejb.modelo.RcbInsumos;
import com.issste.sicabis.ejb.modelo.TipoCompra;
import com.issste.sicabis.ejb.modelo.UnidadResponsable;
import java.math.BigDecimal;
import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2018-06-22T12:18:02")
@StaticMetamodel(Rcb.class)
public class Rcb_ { 

    public static volatile SingularAttribute<Rcb, String> nep;
    public static volatile SingularAttribute<Rcb, UnidadResponsable> idUnidadResponsable;
    public static volatile SingularAttribute<Rcb, BigDecimal> importeTotal;
    public static volatile SingularAttribute<Rcb, Date> fechaElaboracion;
    public static volatile SingularAttribute<Rcb, Date> fechaBaja;
    public static volatile SingularAttribute<Rcb, String> usuarioBaja;
    public static volatile SingularAttribute<Rcb, String> numeroOficio;
    public static volatile SingularAttribute<Rcb, Date> fechaModificacion;
    public static volatile SingularAttribute<Rcb, String> clave;
    public static volatile SingularAttribute<Rcb, Integer> periodo;
    public static volatile SingularAttribute<Rcb, Estatus> idEstatus;
    public static volatile SingularAttribute<Rcb, Integer> idRcb;
    public static volatile SingularAttribute<Rcb, TipoCompra> idTipoCompra;
    public static volatile SingularAttribute<Rcb, String> oficioSuficienciaPresupuestal;
    public static volatile SingularAttribute<Rcb, String> usuarioModificacion;
    public static volatile SingularAttribute<Rcb, Integer> activo;
    public static volatile SingularAttribute<Rcb, Jefatura> idJefatura;
    public static volatile SingularAttribute<Rcb, String> numero;
    public static volatile SingularAttribute<Rcb, String> destino;
    public static volatile ListAttribute<Rcb, RcbGrupo> rcbGrupoList;
    public static volatile SingularAttribute<Rcb, Date> fechaOficioSuficiencia;
    public static volatile SingularAttribute<Rcb, Area> idArea;
    public static volatile ListAttribute<Rcb, RcbInsumos> rcbInsumosList;
    public static volatile SingularAttribute<Rcb, Date> fechaAlta;
    public static volatile SingularAttribute<Rcb, String> usuarioAlta;
    public static volatile SingularAttribute<Rcb, Integer> diasOficio;

}