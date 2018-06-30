package com.issste.sicabis.ejb.modelo;

import com.issste.sicabis.ejb.modelo.Area;
import com.issste.sicabis.ejb.modelo.CatDetalleIm;
import com.issste.sicabis.ejb.modelo.Rcb;
import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2018-06-22T12:18:02")
@StaticMetamodel(Jefatura.class)
public class Jefatura_ { 

    public static volatile SingularAttribute<Jefatura, String> nombre;
    public static volatile SingularAttribute<Jefatura, Area> idArea;
    public static volatile ListAttribute<Jefatura, CatDetalleIm> catDetalleImList;
    public static volatile SingularAttribute<Jefatura, Integer> activo;
    public static volatile ListAttribute<Jefatura, Rcb> rcbList;
    public static volatile SingularAttribute<Jefatura, Integer> idJefatura;

}