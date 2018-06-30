package com.issste.sicabis.ejb.modelo;

import com.issste.sicabis.ejb.modelo.Delegaciones;
import com.issste.sicabis.ejb.modelo.PorcentajeDelegacion;
import java.math.BigDecimal;
import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2018-06-22T12:18:02")
@StaticMetamodel(DetalleDelegacion.class)
public class DetalleDelegacion_ { 

    public static volatile SingularAttribute<DetalleDelegacion, BigDecimal> porcentaje;
    public static volatile SingularAttribute<DetalleDelegacion, Delegaciones> idDelegacion;
    public static volatile SingularAttribute<DetalleDelegacion, PorcentajeDelegacion> idPorcentajeDelegacion;
    public static volatile SingularAttribute<DetalleDelegacion, String> estadoUmuDelegacion;
    public static volatile SingularAttribute<DetalleDelegacion, Integer> idDetalleDelegacion;
    public static volatile SingularAttribute<DetalleDelegacion, Date> fechaActualizacion;

}