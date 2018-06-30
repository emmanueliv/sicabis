package com.issste.sicabis.ejb.modelo;

import com.issste.sicabis.ejb.modelo.Area;
import com.issste.sicabis.ejb.modelo.Insumos;
import com.issste.sicabis.ejb.modelo.Programas;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2018-06-22T12:18:02")
@StaticMetamodel(AsignacionInsumos.class)
public class AsignacionInsumos_ { 

    public static volatile SingularAttribute<AsignacionInsumos, Integer> idAsignacionInsumos;
    public static volatile SingularAttribute<AsignacionInsumos, Area> idArea;
    public static volatile SingularAttribute<AsignacionInsumos, Insumos> idInsumo;
    public static volatile SingularAttribute<AsignacionInsumos, Programas> idPrograma;

}