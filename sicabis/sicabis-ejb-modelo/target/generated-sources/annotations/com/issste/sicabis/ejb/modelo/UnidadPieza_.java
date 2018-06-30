package com.issste.sicabis.ejb.modelo;

import com.issste.sicabis.ejb.modelo.Insumos;
import com.issste.sicabis.ejb.modelo.RcbInsumos;
import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2018-06-22T12:18:02")
@StaticMetamodel(UnidadPieza.class)
public class UnidadPieza_ { 

    public static volatile SingularAttribute<UnidadPieza, Integer> idUnidadPieza;
    public static volatile SingularAttribute<UnidadPieza, String> usuarioModificacion;
    public static volatile ListAttribute<UnidadPieza, Insumos> insumosList;
    public static volatile SingularAttribute<UnidadPieza, Date> fechaBaja;
    public static volatile SingularAttribute<UnidadPieza, String> usuarioBaja;
    public static volatile SingularAttribute<UnidadPieza, String> descripcion;
    public static volatile ListAttribute<UnidadPieza, RcbInsumos> rcbInsumosList;
    public static volatile SingularAttribute<UnidadPieza, Date> fechaAlta;
    public static volatile SingularAttribute<UnidadPieza, Date> fechaModificacion;
    public static volatile SingularAttribute<UnidadPieza, String> usuarioAlta;
    public static volatile SingularAttribute<UnidadPieza, Integer> activo;

}