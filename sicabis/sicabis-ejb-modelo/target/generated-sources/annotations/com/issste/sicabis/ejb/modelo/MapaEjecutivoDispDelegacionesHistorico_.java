package com.issste.sicabis.ejb.modelo;

import java.math.BigDecimal;
import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2018-06-22T12:18:02")
@StaticMetamodel(MapaEjecutivoDispDelegacionesHistorico.class)
public class MapaEjecutivoDispDelegacionesHistorico_ { 

    public static volatile SingularAttribute<MapaEjecutivoDispDelegacionesHistorico, String> estado;
    public static volatile SingularAttribute<MapaEjecutivoDispDelegacionesHistorico, Date> fecha;
    public static volatile SingularAttribute<MapaEjecutivoDispDelegacionesHistorico, Integer> idDelegacion;
    public static volatile SingularAttribute<MapaEjecutivoDispDelegacionesHistorico, BigDecimal> disponibilidad;
    public static volatile SingularAttribute<MapaEjecutivoDispDelegacionesHistorico, Integer> idIndicador;
    public static volatile SingularAttribute<MapaEjecutivoDispDelegacionesHistorico, Integer> clavesAutorizadas;
    public static volatile SingularAttribute<MapaEjecutivoDispDelegacionesHistorico, Integer> clavesDisponibles;
    public static volatile SingularAttribute<MapaEjecutivoDispDelegacionesHistorico, Date> fechaIngreso;
    public static volatile SingularAttribute<MapaEjecutivoDispDelegacionesHistorico, Integer> activo;
    public static volatile SingularAttribute<MapaEjecutivoDispDelegacionesHistorico, Integer> idMapaEjecutivoDispDelegacionesHistorico;
    public static volatile SingularAttribute<MapaEjecutivoDispDelegacionesHistorico, String> delegacion;

}