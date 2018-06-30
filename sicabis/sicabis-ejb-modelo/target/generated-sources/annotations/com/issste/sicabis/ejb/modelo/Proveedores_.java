package com.issste.sicabis.ejb.modelo;

import com.issste.sicabis.ejb.modelo.ContactosProveedores;
import com.issste.sicabis.ejb.modelo.FalloProcedimientoRcb;
import com.issste.sicabis.ejb.modelo.Giro;
import com.issste.sicabis.ejb.modelo.Propuestas;
import com.issste.sicabis.ejb.modelo.ProveedorFabricante;
import com.issste.sicabis.ejb.modelo.RepresentanteLegal;
import com.issste.sicabis.ejb.modelo.TipoProveedor;
import java.math.BigDecimal;
import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2018-06-22T12:18:02")
@StaticMetamodel(Proveedores.class)
public class Proveedores_ { 

    public static volatile SingularAttribute<Proveedores, String> direccion;
    public static volatile SingularAttribute<Proveedores, String> reformaNombreNotario;
    public static volatile SingularAttribute<Proveedores, String> telefono2;
    public static volatile SingularAttribute<Proveedores, String> telefono3;
    public static volatile SingularAttribute<Proveedores, String> observaciones;
    public static volatile SingularAttribute<Proveedores, String> reformaNumNotaria;
    public static volatile SingularAttribute<Proveedores, String> nombreNotario;
    public static volatile SingularAttribute<Proveedores, String> notificaciones;
    public static volatile SingularAttribute<Proveedores, String> usuarioBaja;
    public static volatile SingularAttribute<Proveedores, String> numActaEscritura;
    public static volatile SingularAttribute<Proveedores, String> objetoEmpresa;
    public static volatile SingularAttribute<Proveedores, String> localidad;
    public static volatile SingularAttribute<Proveedores, Date> fechaActa;
    public static volatile SingularAttribute<Proveedores, String> ciudad;
    public static volatile SingularAttribute<Proveedores, String> nombreProveedor;
    public static volatile SingularAttribute<Proveedores, String> usuarioModificacion;
    public static volatile SingularAttribute<Proveedores, String> razonSocial;
    public static volatile SingularAttribute<Proveedores, String> reformaSintesisActa;
    public static volatile SingularAttribute<Proveedores, Integer> activo;
    public static volatile SingularAttribute<Proveedores, Integer> numero;
    public static volatile SingularAttribute<Proveedores, String> representante;
    public static volatile SingularAttribute<Proveedores, String> cp;
    public static volatile SingularAttribute<Proveedores, String> repNombreNotario;
    public static volatile SingularAttribute<Proveedores, String> reformaNumActa;
    public static volatile SingularAttribute<Proveedores, String> calle;
    public static volatile SingularAttribute<Proveedores, Integer> numeroProveedor;
    public static volatile SingularAttribute<Proveedores, String> correo;
    public static volatile SingularAttribute<Proveedores, String> delegacion;
    public static volatile SingularAttribute<Proveedores, String> repCorreoElectronico;
    public static volatile ListAttribute<Proveedores, FalloProcedimientoRcb> falloProcedimientoRcbList;
    public static volatile SingularAttribute<Proveedores, Integer> autorizado;
    public static volatile ListAttribute<Proveedores, Propuestas> propuestasList;
    public static volatile SingularAttribute<Proveedores, String> colonia;
    public static volatile SingularAttribute<Proveedores, String> rfc;
    public static volatile SingularAttribute<Proveedores, String> estatus_sii_plus;
    public static volatile SingularAttribute<Proveedores, String> numNotaria;
    public static volatile SingularAttribute<Proveedores, String> giro;
    public static volatile SingularAttribute<Proveedores, Date> reformaFechaActa;
    public static volatile SingularAttribute<Proveedores, Integer> micro;
    public static volatile SingularAttribute<Proveedores, Date> fechaBaja;
    public static volatile SingularAttribute<Proveedores, String> repNumActaEscritura;
    public static volatile SingularAttribute<Proveedores, Date> repFechaActa;
    public static volatile ListAttribute<Proveedores, ProveedorFabricante> proveedorFabricanteList;
    public static volatile SingularAttribute<Proveedores, String> fax2;
    public static volatile SingularAttribute<Proveedores, Date> fechaModificacion;
    public static volatile SingularAttribute<Proveedores, String> fax1;
    public static volatile SingularAttribute<Proveedores, String> vigenciaSociedad;
    public static volatile SingularAttribute<Proveedores, String> repSintesisActa;
    public static volatile SingularAttribute<Proveedores, BigDecimal> topeMaximo;
    public static volatile SingularAttribute<Proveedores, RepresentanteLegal> idRepresentanteLegal;
    public static volatile SingularAttribute<Proveedores, String> giroDescripcion;
    public static volatile SingularAttribute<Proveedores, Integer> idPadre;
    public static volatile SingularAttribute<Proveedores, Integer> mediana;
    public static volatile ListAttribute<Proveedores, ContactosProveedores> contactosProveedoresList;
    public static volatile SingularAttribute<Proveedores, TipoProveedor> idTipoProveedor;
    public static volatile SingularAttribute<Proveedores, String> repNumNotaria;
    public static volatile SingularAttribute<Proveedores, Integer> mipymes;
    public static volatile SingularAttribute<Proveedores, Integer> idProveedor;
    public static volatile SingularAttribute<Proveedores, Giro> idGiro;
    public static volatile SingularAttribute<Proveedores, Date> fechaAlta;
    public static volatile SingularAttribute<Proveedores, Integer> pequena;
    public static volatile SingularAttribute<Proveedores, Integer> noRupa;
    public static volatile SingularAttribute<Proveedores, String> usuarioAlta;
    public static volatile SingularAttribute<Proveedores, String> sintesisActa;

}