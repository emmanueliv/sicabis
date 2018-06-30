package com.issste.sicabis.ejb.modelo;

import com.issste.sicabis.ejb.modelo.Insumos;
import com.issste.sicabis.ejb.modelo.Proveedores;
import com.issste.sicabis.ejb.modelo.Remisiones;
import java.math.BigDecimal;
import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2018-06-22T12:18:02")
@StaticMetamodel(CanjePermuta.class)
public class CanjePermuta_ { 

    public static volatile SingularAttribute<CanjePermuta, Insumos> idInsumoCanje;
    public static volatile SingularAttribute<CanjePermuta, Date> fechaModificaciones;
    public static volatile SingularAttribute<CanjePermuta, String> usuarioModificacion;
    public static volatile ListAttribute<CanjePermuta, Remisiones> remisionesList;
    public static volatile SingularAttribute<CanjePermuta, Integer> idCanjePermuta;
    public static volatile SingularAttribute<CanjePermuta, Insumos> idInsumo;
    public static volatile SingularAttribute<CanjePermuta, Date> fechaFabricacionRecibido;
    public static volatile SingularAttribute<CanjePermuta, String> loteEntregado;
    public static volatile SingularAttribute<CanjePermuta, Integer> activo;
    public static volatile SingularAttribute<CanjePermuta, String> lote;
    public static volatile SingularAttribute<CanjePermuta, BigDecimal> precioCanjePermuta;
    public static volatile SingularAttribute<CanjePermuta, BigDecimal> precio;
    public static volatile SingularAttribute<CanjePermuta, Integer> cantidadInsumoOriginal;
    public static volatile SingularAttribute<CanjePermuta, String> folio;
    public static volatile SingularAttribute<CanjePermuta, Date> fechaBaja;
    public static volatile SingularAttribute<CanjePermuta, String> usuarioBaja;
    public static volatile SingularAttribute<CanjePermuta, String> tipoCanje;
    public static volatile SingularAttribute<CanjePermuta, Date> fechaCaducidadCanje;
    public static volatile SingularAttribute<CanjePermuta, Date> fechaAlta;
    public static volatile SingularAttribute<CanjePermuta, Integer> cantidadInsumoCanje;
    public static volatile SingularAttribute<CanjePermuta, Date> fechaCaducidad;
    public static volatile SingularAttribute<CanjePermuta, Proveedores> proveedor;
    public static volatile SingularAttribute<CanjePermuta, String> usuarioAlta;

}