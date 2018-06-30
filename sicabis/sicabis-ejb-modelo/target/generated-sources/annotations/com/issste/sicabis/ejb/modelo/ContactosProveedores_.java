package com.issste.sicabis.ejb.modelo;

import com.issste.sicabis.ejb.modelo.Contactos;
import com.issste.sicabis.ejb.modelo.Proveedores;
import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2018-06-22T12:18:02")
@StaticMetamodel(ContactosProveedores.class)
public class ContactosProveedores_ { 

    public static volatile SingularAttribute<ContactosProveedores, String> usuarioModificacion;
    public static volatile SingularAttribute<ContactosProveedores, Date> fechaBaja;
    public static volatile SingularAttribute<ContactosProveedores, Proveedores> idProveedor;
    public static volatile SingularAttribute<ContactosProveedores, Contactos> idContacto;
    public static volatile SingularAttribute<ContactosProveedores, String> usuarioBaja;
    public static volatile SingularAttribute<ContactosProveedores, Integer> idContactosProveedores;
    public static volatile SingularAttribute<ContactosProveedores, Date> fechaAlta;
    public static volatile SingularAttribute<ContactosProveedores, Date> fechaModificacion;
    public static volatile SingularAttribute<ContactosProveedores, String> usuarioAlta;
    public static volatile SingularAttribute<ContactosProveedores, Integer> activo;

}