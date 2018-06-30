package com.issste.sicabis.ejb.modelo;

import com.issste.sicabis.ejb.modelo.DpnInsumos;
import com.issste.sicabis.ejb.modelo.Estatus;
import com.issste.sicabis.ejb.modelo.PeriodoMes;
import java.math.BigDecimal;
import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2018-06-22T12:18:02")
@StaticMetamodel(Dpn.class)
public class Dpn_ { 

    public static volatile SingularAttribute<Dpn, Integer> anio;
    public static volatile SingularAttribute<Dpn, String> usuarioModificacion;
    public static volatile SingularAttribute<Dpn, Date> fecha;
    public static volatile SingularAttribute<Dpn, BigDecimal> totalPiezasDpn;
    public static volatile SingularAttribute<Dpn, String> observaciones;
    public static volatile SingularAttribute<Dpn, Integer> activo;
    public static volatile ListAttribute<Dpn, DpnInsumos> dpnInsumosList;
    public static volatile SingularAttribute<Dpn, PeriodoMes> idPeriodoMes;
    public static volatile SingularAttribute<Dpn, Date> fechaBaja;
    public static volatile SingularAttribute<Dpn, String> dpnMes;
    public static volatile SingularAttribute<Dpn, Integer> mes;
    public static volatile SingularAttribute<Dpn, String> usuarioBaja;
    public static volatile SingularAttribute<Dpn, Date> fechaAlta;
    public static volatile SingularAttribute<Dpn, Date> fechaModificacion;
    public static volatile SingularAttribute<Dpn, String> usuarioAlta;
    public static volatile SingularAttribute<Dpn, Estatus> idEstatus;
    public static volatile SingularAttribute<Dpn, Integer> idDpn;

}