package com.issste.sicabis.ejb.modelo;

import com.issste.sicabis.ejb.modelo.Remisiones;
import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2018-06-22T12:18:02")
@StaticMetamodel(Lote.class)
public class Lote_ { 

    public static volatile SingularAttribute<Lote, Date> fechaModificaciones;
    public static volatile SingularAttribute<Lote, String> usuarioModificacion;
    public static volatile SingularAttribute<Lote, Integer> idLote;
    public static volatile SingularAttribute<Lote, Integer> activo;
    public static volatile SingularAttribute<Lote, Integer> idLotePadre;
    public static volatile SingularAttribute<Lote, String> codigoBarrasLote;
    public static volatile SingularAttribute<Lote, String> lote;
    public static volatile SingularAttribute<Lote, Date> fechaBaja;
    public static volatile SingularAttribute<Lote, Remisiones> idRemision;
    public static volatile SingularAttribute<Lote, Integer> cantidadLote;
    public static volatile SingularAttribute<Lote, String> usuarioBaja;
    public static volatile SingularAttribute<Lote, String> folioRemision;
    public static volatile SingularAttribute<Lote, Integer> cantidadRecibidaControlCalidad;
    public static volatile SingularAttribute<Lote, Date> fechaAlta;
    public static volatile SingularAttribute<Lote, Date> fechaFabricacion;
    public static volatile SingularAttribute<Lote, Date> fechaCaducidad;
    public static volatile SingularAttribute<Lote, String> usuarioAlta;

}