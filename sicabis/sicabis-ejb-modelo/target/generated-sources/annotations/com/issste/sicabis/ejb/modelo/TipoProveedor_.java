package com.issste.sicabis.ejb.modelo;

import com.issste.sicabis.ejb.modelo.Proveedores;
import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2018-06-22T12:18:02")
@StaticMetamodel(TipoProveedor.class)
public class TipoProveedor_ { 

    public static volatile SingularAttribute<TipoProveedor, Integer> idTipoProveedor;
    public static volatile ListAttribute<TipoProveedor, Proveedores> proveedoresList;
    public static volatile SingularAttribute<TipoProveedor, String> usuarioModificacion;
    public static volatile SingularAttribute<TipoProveedor, Date> fechaBaja;
    public static volatile SingularAttribute<TipoProveedor, String> tipo;
    public static volatile SingularAttribute<TipoProveedor, String> usuarioBaja;
    public static volatile SingularAttribute<TipoProveedor, String> descripcion;
    public static volatile SingularAttribute<TipoProveedor, Integer> idPadre;
    public static volatile SingularAttribute<TipoProveedor, Date> fechaAlta;
    public static volatile SingularAttribute<TipoProveedor, Date> fechaModificacion;
    public static volatile SingularAttribute<TipoProveedor, String> usuarioAlta;
    public static volatile SingularAttribute<TipoProveedor, Integer> activo;

}