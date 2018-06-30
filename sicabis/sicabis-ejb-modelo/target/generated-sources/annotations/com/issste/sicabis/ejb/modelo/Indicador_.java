package com.issste.sicabis.ejb.modelo;

import com.issste.sicabis.ejb.modelo.PorcentajeDelegacion;
import com.issste.sicabis.ejb.service.silodisa.modelo.MapaEjecutivoDispDelegaciones;
import com.issste.sicabis.ejb.service.silodisa.modelo.MapaEjecutivoDispG40;
import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2018-06-22T12:18:02")
@StaticMetamodel(Indicador.class)
public class Indicador_ { 

    public static volatile SingularAttribute<Indicador, String> nombre;
    public static volatile ListAttribute<Indicador, MapaEjecutivoDispDelegaciones> mapaEjecutivoDispDelegacionesList;
    public static volatile ListAttribute<Indicador, PorcentajeDelegacion> porcentajeDelegacionList;
    public static volatile SingularAttribute<Indicador, Integer> idIndicador;
    public static volatile ListAttribute<Indicador, MapaEjecutivoDispG40> mapaEjecutivoDispG40List;
    public static volatile SingularAttribute<Indicador, Integer> activo;

}