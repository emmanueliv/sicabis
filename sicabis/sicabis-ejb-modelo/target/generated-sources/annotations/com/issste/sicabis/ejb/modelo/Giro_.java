package com.issste.sicabis.ejb.modelo;

import com.issste.sicabis.ejb.modelo.Proveedores;
import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2018-06-22T12:18:02")
@StaticMetamodel(Giro.class)
public class Giro_ { 

    public static volatile ListAttribute<Giro, Proveedores> proveedoresList;
    public static volatile SingularAttribute<Giro, String> usuarioModificacion;
    public static volatile SingularAttribute<Giro, Date> fechaBaja;
    public static volatile SingularAttribute<Giro, String> usuarioBaja;
    public static volatile SingularAttribute<Giro, Integer> idGiro;
    public static volatile SingularAttribute<Giro, String> descripcion;
    public static volatile SingularAttribute<Giro, Date> fechaAlta;
    public static volatile SingularAttribute<Giro, Date> fechaModificacion;
    public static volatile SingularAttribute<Giro, String> usuarioAlta;

}