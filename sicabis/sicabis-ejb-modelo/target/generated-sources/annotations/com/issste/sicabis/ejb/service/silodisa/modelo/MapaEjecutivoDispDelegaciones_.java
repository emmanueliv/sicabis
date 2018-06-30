package com.issste.sicabis.ejb.service.silodisa.modelo;

import com.issste.sicabis.ejb.modelo.Delegaciones;
import com.issste.sicabis.ejb.modelo.Indicador;
import java.math.BigDecimal;
import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2018-06-22T12:18:02")
@StaticMetamodel(MapaEjecutivoDispDelegaciones.class)
public class MapaEjecutivoDispDelegaciones_ { 

    public static volatile SingularAttribute<MapaEjecutivoDispDelegaciones, String> estado;
    public static volatile SingularAttribute<MapaEjecutivoDispDelegaciones, Integer> idMapaEjecutivoDispDelegaciones;
    public static volatile SingularAttribute<MapaEjecutivoDispDelegaciones, Date> fecha;
    public static volatile SingularAttribute<MapaEjecutivoDispDelegaciones, Delegaciones> idDelegacion;
    public static volatile SingularAttribute<MapaEjecutivoDispDelegaciones, BigDecimal> disponibilidad;
    public static volatile SingularAttribute<MapaEjecutivoDispDelegaciones, Indicador> idIndicador;
    public static volatile SingularAttribute<MapaEjecutivoDispDelegaciones, Integer> clavesAutorizadas;
    public static volatile SingularAttribute<MapaEjecutivoDispDelegaciones, Integer> clavesDisponibles;
    public static volatile SingularAttribute<MapaEjecutivoDispDelegaciones, Date> fechaIngreso;
    public static volatile SingularAttribute<MapaEjecutivoDispDelegaciones, Integer> activo;
    public static volatile SingularAttribute<MapaEjecutivoDispDelegaciones, String> delegacion;

}