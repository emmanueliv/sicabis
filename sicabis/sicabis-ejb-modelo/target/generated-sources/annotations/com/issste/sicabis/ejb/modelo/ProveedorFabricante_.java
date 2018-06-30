package com.issste.sicabis.ejb.modelo;

import com.issste.sicabis.ejb.modelo.Fabricante;
import com.issste.sicabis.ejb.modelo.Proveedores;
import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2018-06-22T12:18:02")
@StaticMetamodel(ProveedorFabricante.class)
public class ProveedorFabricante_ { 

    public static volatile SingularAttribute<ProveedorFabricante, Fabricante> idFabricante;
    public static volatile SingularAttribute<ProveedorFabricante, Integer> idProcedimientoRcb;
    public static volatile SingularAttribute<ProveedorFabricante, String> usuarioModificacion;
    public static volatile SingularAttribute<ProveedorFabricante, Integer> idProveedorFabricante;
    public static volatile SingularAttribute<ProveedorFabricante, Date> fechaBaja;
    public static volatile SingularAttribute<ProveedorFabricante, Proveedores> idProveedor;
    public static volatile SingularAttribute<ProveedorFabricante, String> usuarioBaja;
    public static volatile SingularAttribute<ProveedorFabricante, Date> fechaAlta;
    public static volatile SingularAttribute<ProveedorFabricante, Date> fechaModificacion;
    public static volatile SingularAttribute<ProveedorFabricante, String> usuarioAlta;
    public static volatile SingularAttribute<ProveedorFabricante, Integer> activo;

}