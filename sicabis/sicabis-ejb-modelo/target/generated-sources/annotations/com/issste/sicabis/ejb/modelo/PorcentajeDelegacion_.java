package com.issste.sicabis.ejb.modelo;

import com.issste.sicabis.ejb.modelo.Delegaciones;
import com.issste.sicabis.ejb.modelo.DetalleDelegacion;
import com.issste.sicabis.ejb.modelo.Indicador;
import java.math.BigDecimal;
import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2018-06-22T12:18:02")
@StaticMetamodel(PorcentajeDelegacion.class)
public class PorcentajeDelegacion_ { 

    public static volatile SingularAttribute<PorcentajeDelegacion, BigDecimal> porcentaje;
    public static volatile SingularAttribute<PorcentajeDelegacion, String> clavesDPN;
    public static volatile ListAttribute<PorcentajeDelegacion, DetalleDelegacion> detalleDelegacionList;
    public static volatile SingularAttribute<PorcentajeDelegacion, Delegaciones> idDelegacion;
    public static volatile SingularAttribute<PorcentajeDelegacion, Integer> idPorcentajeDelegacion;
    public static volatile SingularAttribute<PorcentajeDelegacion, Indicador> idIndicador;
    public static volatile SingularAttribute<PorcentajeDelegacion, Date> fechaActualizacion;
    public static volatile SingularAttribute<PorcentajeDelegacion, String> clavesEnUMU;

}