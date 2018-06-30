package com.issste.sicabis.ejb.modelo;

import com.issste.sicabis.ejb.modelo.Remisiones;
import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2018-06-22T12:18:02")
@StaticMetamodel(Presentacion.class)
public class Presentacion_ { 

    public static volatile SingularAttribute<Presentacion, String> usuarioModificacion;
    public static volatile SingularAttribute<Presentacion, Date> fechaBaja;
    public static volatile ListAttribute<Presentacion, Remisiones> remisionesList;
    public static volatile SingularAttribute<Presentacion, Integer> idPresentacion;
    public static volatile SingularAttribute<Presentacion, String> usuarioBaja;
    public static volatile SingularAttribute<Presentacion, Date> fechaAlta;
    public static volatile SingularAttribute<Presentacion, Date> fechaModificacion;
    public static volatile SingularAttribute<Presentacion, String> presentacion;
    public static volatile SingularAttribute<Presentacion, String> usuarioAlta;
    public static volatile SingularAttribute<Presentacion, Integer> activo;

}