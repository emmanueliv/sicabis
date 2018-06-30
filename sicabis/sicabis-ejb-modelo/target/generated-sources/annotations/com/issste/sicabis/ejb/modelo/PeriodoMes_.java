package com.issste.sicabis.ejb.modelo;

import com.issste.sicabis.ejb.modelo.Area;
import com.issste.sicabis.ejb.modelo.Dpn;
import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2018-06-22T12:18:02")
@StaticMetamodel(PeriodoMes.class)
public class PeriodoMes_ { 

    public static volatile SingularAttribute<PeriodoMes, Date> fechaFinal;
    public static volatile SingularAttribute<PeriodoMes, String> usuarioModificacion;
    public static volatile SingularAttribute<PeriodoMes, Integer> activo;
    public static volatile SingularAttribute<PeriodoMes, Date> fechaCorte;
    public static volatile SingularAttribute<PeriodoMes, Integer> idPeriodoMes;
    public static volatile SingularAttribute<PeriodoMes, Date> fechaInicial;
    public static volatile SingularAttribute<PeriodoMes, Date> fechaBaja;
    public static volatile SingularAttribute<PeriodoMes, Area> idArea;
    public static volatile SingularAttribute<PeriodoMes, String> usuarioBaja;
    public static volatile SingularAttribute<PeriodoMes, Date> fechaAlta;
    public static volatile SingularAttribute<PeriodoMes, Date> fechaModificacion;
    public static volatile ListAttribute<PeriodoMes, Dpn> dpnList;
    public static volatile SingularAttribute<PeriodoMes, String> usuarioAlta;
    public static volatile SingularAttribute<PeriodoMes, String> mesLetra;

}