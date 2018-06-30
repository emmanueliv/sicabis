package com.issste.sicabis.ejb.modelo;

import com.issste.sicabis.ejb.modelo.Delegaciones;
import com.issste.sicabis.ejb.modelo.Indicador;
import java.math.BigDecimal;
import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2018-06-22T12:18:02")
@StaticMetamodel(PorcentajeDelegacionHistorico.class)
public class PorcentajeDelegacionHistorico_ { 

    public static volatile SingularAttribute<PorcentajeDelegacionHistorico, BigDecimal> porcentaje;
    public static volatile SingularAttribute<PorcentajeDelegacionHistorico, Integer> idPorcentajeDelegacionHistorico;
    public static volatile SingularAttribute<PorcentajeDelegacionHistorico, Delegaciones> idDelegacion;
    public static volatile SingularAttribute<PorcentajeDelegacionHistorico, Indicador> idIndicador;
    public static volatile SingularAttribute<PorcentajeDelegacionHistorico, String> clavesDpn;
    public static volatile SingularAttribute<PorcentajeDelegacionHistorico, Date> fechaActualizacion;
    public static volatile SingularAttribute<PorcentajeDelegacionHistorico, String> clavesUmu;

}