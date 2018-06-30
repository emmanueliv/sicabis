package com.issste.sicabis.ejb.modelo;

import com.issste.sicabis.ejb.modelo.Cr;
import com.issste.sicabis.ejb.modelo.Rcb;
import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2018-06-22T12:18:02")
@StaticMetamodel(UnidadResponsable.class)
public class UnidadResponsable_ { 

    public static volatile SingularAttribute<UnidadResponsable, String> nombre;
    public static volatile SingularAttribute<UnidadResponsable, String> usuarioModificacion;
    public static volatile SingularAttribute<UnidadResponsable, Date> fechaBaja;
    public static volatile SingularAttribute<UnidadResponsable, String> usuarioBaja;
    public static volatile SingularAttribute<UnidadResponsable, Date> fechaAlta;
    public static volatile SingularAttribute<UnidadResponsable, Date> fechaModificacion;
    public static volatile ListAttribute<UnidadResponsable, Cr> crList;
    public static volatile SingularAttribute<UnidadResponsable, String> usuarioAlta;
    public static volatile SingularAttribute<UnidadResponsable, Integer> idUnidadResponsable;
    public static volatile SingularAttribute<UnidadResponsable, Integer> activo;
    public static volatile ListAttribute<UnidadResponsable, Rcb> rcbList;

}