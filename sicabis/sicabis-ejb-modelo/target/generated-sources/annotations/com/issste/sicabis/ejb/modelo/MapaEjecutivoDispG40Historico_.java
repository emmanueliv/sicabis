package com.issste.sicabis.ejb.modelo;

import java.math.BigDecimal;
import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2018-06-22T12:18:02")
@StaticMetamodel(MapaEjecutivoDispG40Historico.class)
public class MapaEjecutivoDispG40Historico_ { 

    public static volatile SingularAttribute<MapaEjecutivoDispG40Historico, Integer> idMapaEjecutivoDispG40Historico;
    public static volatile SingularAttribute<MapaEjecutivoDispG40Historico, String> nombre;
    public static volatile SingularAttribute<MapaEjecutivoDispG40Historico, String> estado;
    public static volatile SingularAttribute<MapaEjecutivoDispG40Historico, String> umu;
    public static volatile SingularAttribute<MapaEjecutivoDispG40Historico, Date> fecha;
    public static volatile SingularAttribute<MapaEjecutivoDispG40Historico, Integer> idDelegacion;
    public static volatile SingularAttribute<MapaEjecutivoDispG40Historico, BigDecimal> disponibilidad;
    public static volatile SingularAttribute<MapaEjecutivoDispG40Historico, Integer> idIndicador;
    public static volatile SingularAttribute<MapaEjecutivoDispG40Historico, Integer> clavesAutorizadas;
    public static volatile SingularAttribute<MapaEjecutivoDispG40Historico, Integer> clavesDisponibles;
    public static volatile SingularAttribute<MapaEjecutivoDispG40Historico, Date> fechaIngreso;
    public static volatile SingularAttribute<MapaEjecutivoDispG40Historico, Integer> activo;

}