package com.issste.sicabis.ejb.modelo;

import com.issste.sicabis.ejb.modelo.Contratos;
import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2018-06-22T12:18:02")
@StaticMetamodel(Almacen.class)
public class Almacen_ { 

    public static volatile SingularAttribute<Almacen, String> usuarioModificacion;
    public static volatile SingularAttribute<Almacen, Date> fechaBaja;
    public static volatile SingularAttribute<Almacen, String> usuarioBaja;
    public static volatile SingularAttribute<Almacen, Integer> idPadre;
    public static volatile SingularAttribute<Almacen, String> nombreAlmacen;
    public static volatile SingularAttribute<Almacen, Date> fechaAlta;
    public static volatile SingularAttribute<Almacen, Date> fechaModificacion;
    public static volatile SingularAttribute<Almacen, String> usuarioAlta;
    public static volatile SingularAttribute<Almacen, Integer> idAlmacen;
    public static volatile SingularAttribute<Almacen, Integer> activo;
    public static volatile ListAttribute<Almacen, Contratos> contratosList;

}