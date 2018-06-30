package com.issste.sicabis.ejb.modelo;

import com.issste.sicabis.ejb.modelo.Area;
import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2018-06-22T12:18:02")
@StaticMetamodel(PeriodoArea.class)
public class PeriodoArea_ { 

    public static volatile SingularAttribute<PeriodoArea, Integer> diaInicial;
    public static volatile SingularAttribute<PeriodoArea, String> usuarioModificacion;
    public static volatile SingularAttribute<PeriodoArea, Integer> idPeriodoArea;
    public static volatile SingularAttribute<PeriodoArea, Date> fechaBaja;
    public static volatile SingularAttribute<PeriodoArea, Integer> diaFinal;
    public static volatile SingularAttribute<PeriodoArea, String> usuarioBaja;
    public static volatile SingularAttribute<PeriodoArea, Area> idArea;
    public static volatile SingularAttribute<PeriodoArea, Date> fechaAlta;
    public static volatile SingularAttribute<PeriodoArea, Date> fechaModificacion;
    public static volatile SingularAttribute<PeriodoArea, String> usuarioAlta;
    public static volatile SingularAttribute<PeriodoArea, Integer> activo;

}