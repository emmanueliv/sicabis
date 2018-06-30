package com.issste.sicabis.ejb.service.silodisa.modelo;

import com.issste.sicabis.ejb.modelo.Delegaciones;
import com.issste.sicabis.ejb.modelo.Indicador;
import java.math.BigDecimal;
import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2018-06-22T12:18:02")
@StaticMetamodel(MapaEjecutivoDispG40.class)
public class MapaEjecutivoDispG40_ { 

    public static volatile SingularAttribute<MapaEjecutivoDispG40, String> nombre;
    public static volatile SingularAttribute<MapaEjecutivoDispG40, String> estado;
    public static volatile SingularAttribute<MapaEjecutivoDispG40, Integer> idMapaEjecutivoDispG40;
    public static volatile SingularAttribute<MapaEjecutivoDispG40, String> umu;
    public static volatile SingularAttribute<MapaEjecutivoDispG40, Date> fecha;
    public static volatile SingularAttribute<MapaEjecutivoDispG40, Delegaciones> idDelegacion;
    public static volatile SingularAttribute<MapaEjecutivoDispG40, BigDecimal> disponibilidad;
    public static volatile SingularAttribute<MapaEjecutivoDispG40, Indicador> idIndicador;
    public static volatile SingularAttribute<MapaEjecutivoDispG40, Integer> clavesAutorizadas;
    public static volatile SingularAttribute<MapaEjecutivoDispG40, Integer> clavesDisponibles;
    public static volatile SingularAttribute<MapaEjecutivoDispG40, Date> fechaIngreso;
    public static volatile SingularAttribute<MapaEjecutivoDispG40, Integer> activo;

}