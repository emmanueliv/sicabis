package com.issste.sicabis.ejb.modelo;

import com.issste.sicabis.ejb.modelo.CrInsumos;
import com.issste.sicabis.ejb.modelo.Procedimientos;
import com.issste.sicabis.ejb.modelo.Rcb;
import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2018-06-22T12:18:02")
@StaticMetamodel(TipoCompra.class)
public class TipoCompra_ { 

    public static volatile SingularAttribute<TipoCompra, String> nombre;
    public static volatile SingularAttribute<TipoCompra, Integer> idTipoCompra;
    public static volatile SingularAttribute<TipoCompra, String> usuarioModificacion;
    public static volatile SingularAttribute<TipoCompra, Date> fechaBaja;
    public static volatile SingularAttribute<TipoCompra, String> usuarioBaja;
    public static volatile SingularAttribute<TipoCompra, Date> fechaAlta;
    public static volatile SingularAttribute<TipoCompra, Date> fechaModificacion;
    public static volatile SingularAttribute<TipoCompra, String> usuarioAlta;
    public static volatile ListAttribute<TipoCompra, Procedimientos> procedimientosList;
    public static volatile SingularAttribute<TipoCompra, Integer> activo;
    public static volatile ListAttribute<TipoCompra, Rcb> rcbList;
    public static volatile ListAttribute<TipoCompra, CrInsumos> crInsumosList;

}