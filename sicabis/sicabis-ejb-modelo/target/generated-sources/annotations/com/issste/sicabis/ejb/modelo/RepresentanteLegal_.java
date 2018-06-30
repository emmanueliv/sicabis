package com.issste.sicabis.ejb.modelo;

import com.issste.sicabis.ejb.modelo.Proveedores;
import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2018-06-22T12:18:02")
@StaticMetamodel(RepresentanteLegal.class)
public class RepresentanteLegal_ { 

    public static volatile SingularAttribute<RepresentanteLegal, String> representante;
    public static volatile ListAttribute<RepresentanteLegal, Proveedores> proveedoresList;
    public static volatile SingularAttribute<RepresentanteLegal, String> correoElectronico;
    public static volatile SingularAttribute<RepresentanteLegal, Date> fechaActa;
    public static volatile SingularAttribute<RepresentanteLegal, String> usuarioModificacion;
    public static volatile SingularAttribute<RepresentanteLegal, String> nombreNotario;
    public static volatile SingularAttribute<RepresentanteLegal, Date> fechaBaja;
    public static volatile SingularAttribute<RepresentanteLegal, String> numeroNotaria;
    public static volatile SingularAttribute<RepresentanteLegal, Integer> idRepresentanteLegal;
    public static volatile SingularAttribute<RepresentanteLegal, String> usuarioBaja;
    public static volatile SingularAttribute<RepresentanteLegal, Date> fechaAlta;
    public static volatile SingularAttribute<RepresentanteLegal, String> numeroActa;
    public static volatile SingularAttribute<RepresentanteLegal, Date> fechaModificacion;
    public static volatile SingularAttribute<RepresentanteLegal, String> usuarioAlta;
    public static volatile SingularAttribute<RepresentanteLegal, String> sintesisActa;

}