package com.issste.sicabis.ejb.modelo;

import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2018-06-22T12:18:02")
@StaticMetamodel(ConsumoDiarioSiam.class)
public class ConsumoDiarioSiam_ { 

    public static volatile SingularAttribute<ConsumoDiarioSiam, Integer> idConsumoDiarioSiam;
    public static volatile SingularAttribute<ConsumoDiarioSiam, String> claveUnidad;
    public static volatile SingularAttribute<ConsumoDiarioSiam, Date> fecha;
    public static volatile SingularAttribute<ConsumoDiarioSiam, Integer> consumo;
    public static volatile SingularAttribute<ConsumoDiarioSiam, String> claveInsumo;

}