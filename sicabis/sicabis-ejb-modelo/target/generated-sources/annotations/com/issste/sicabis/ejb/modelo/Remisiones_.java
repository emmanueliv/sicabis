package com.issste.sicabis.ejb.modelo;

import com.issste.sicabis.ejb.modelo.CanjePermuta;
import com.issste.sicabis.ejb.modelo.DetalleOrdenSuministro;
import com.issste.sicabis.ejb.modelo.Estatus;
import com.issste.sicabis.ejb.modelo.Fabricante;
import com.issste.sicabis.ejb.modelo.Lote;
import com.issste.sicabis.ejb.modelo.Presentacion;
import java.math.BigDecimal;
import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2018-06-22T12:18:02")
@StaticMetamodel(Remisiones.class)
public class Remisiones_ { 

    public static volatile ListAttribute<Remisiones, Lote> loteList;
    public static volatile SingularAttribute<Remisiones, String> inspeccion;
    public static volatile SingularAttribute<Remisiones, String> denominacion;
    public static volatile SingularAttribute<Remisiones, BigDecimal> nivelCalidadAceptable;
    public static volatile SingularAttribute<Remisiones, Date> fechaBaja;
    public static volatile SingularAttribute<Remisiones, String> usuarioBaja;
    public static volatile SingularAttribute<Remisiones, Integer> cantidadRecibidaControlCalidad;
    public static volatile SingularAttribute<Remisiones, Date> fechaRemision;
    public static volatile SingularAttribute<Remisiones, Estatus> idEstatus;
    public static volatile SingularAttribute<Remisiones, String> unidadPiezaConvenio;
    public static volatile SingularAttribute<Remisiones, String> descripcionDefecto;
    public static volatile SingularAttribute<Remisiones, Date> fechaModificaciones;
    public static volatile SingularAttribute<Remisiones, String> usuarioModificacion;
    public static volatile SingularAttribute<Remisiones, DetalleOrdenSuministro> idDetalleOrdenSuministro;
    public static volatile SingularAttribute<Remisiones, CanjePermuta> idCanjePermuta;
    public static volatile SingularAttribute<Remisiones, Presentacion> idPresentacion;
    public static volatile SingularAttribute<Remisiones, String> tipoEntrada;
    public static volatile SingularAttribute<Remisiones, Integer> activo;
    public static volatile SingularAttribute<Remisiones, Fabricante> idFabricante;
    public static volatile SingularAttribute<Remisiones, Integer> idRemision;
    public static volatile SingularAttribute<Remisiones, String> folioRemision;
    public static volatile SingularAttribute<Remisiones, Date> fechaAlta;
    public static volatile SingularAttribute<Remisiones, Integer> cantidadRecibida;
    public static volatile SingularAttribute<Remisiones, String> usuarioAlta;
    public static volatile SingularAttribute<Remisiones, String> registroControl;

}