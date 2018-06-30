package com.issste.sicabis.ejb.modelo;

import com.issste.sicabis.ejb.modelo.Contratos;
import com.issste.sicabis.ejb.modelo.Insumos;
import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2018-06-22T12:18:02")
@StaticMetamodel(TipoInsumos.class)
public class TipoInsumos_ { 

    public static volatile SingularAttribute<TipoInsumos, String> nombre;
    public static volatile ListAttribute<TipoInsumos, Insumos> insumosList;
    public static volatile SingularAttribute<TipoInsumos, String> sigla;
    public static volatile SingularAttribute<TipoInsumos, Integer> idTipoInsumos;
    public static volatile SingularAttribute<TipoInsumos, Integer> ivaActivo;
    public static volatile SingularAttribute<TipoInsumos, Integer> activo;
    public static volatile ListAttribute<TipoInsumos, Contratos> contratosList;

}