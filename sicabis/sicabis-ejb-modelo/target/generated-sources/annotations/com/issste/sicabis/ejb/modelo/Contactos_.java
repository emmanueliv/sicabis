package com.issste.sicabis.ejb.modelo;

import com.issste.sicabis.ejb.modelo.ContactosProveedores;
import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2018-06-22T12:18:02")
@StaticMetamodel(Contactos.class)
public class Contactos_ { 

    public static volatile SingularAttribute<Contactos, String> nombre;
    public static volatile SingularAttribute<Contactos, String> usuarioModificacion;
    public static volatile SingularAttribute<Contactos, Date> fechaBaja;
    public static volatile SingularAttribute<Contactos, Integer> idContacto;
    public static volatile SingularAttribute<Contactos, String> usuarioBaja;
    public static volatile SingularAttribute<Contactos, String> apellidoMaterno;
    public static volatile SingularAttribute<Contactos, String> apellidoPaterno;
    public static volatile SingularAttribute<Contactos, Date> fechaAlta;
    public static volatile SingularAttribute<Contactos, Date> fechaModificacion;
    public static volatile SingularAttribute<Contactos, String> usuarioAlta;
    public static volatile ListAttribute<Contactos, ContactosProveedores> contactosProveedoresList;
    public static volatile SingularAttribute<Contactos, Integer> activo;

}