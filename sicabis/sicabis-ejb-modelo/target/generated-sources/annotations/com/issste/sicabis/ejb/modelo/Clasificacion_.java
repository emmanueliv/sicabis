package com.issste.sicabis.ejb.modelo;

import com.issste.sicabis.ejb.modelo.Insumos;
import com.issste.sicabis.ejb.modelo.RcbInsumos;
import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2018-06-22T12:18:02")
@StaticMetamodel(Clasificacion.class)
public class Clasificacion_ { 

    public static volatile SingularAttribute<Clasificacion, String> usuarioModificacion;
    public static volatile ListAttribute<Clasificacion, Insumos> insumosList;
    public static volatile SingularAttribute<Clasificacion, Date> fechaBaja;
    public static volatile SingularAttribute<Clasificacion, String> usuarioBaja;
    public static volatile SingularAttribute<Clasificacion, String> descripcion;
    public static volatile ListAttribute<Clasificacion, RcbInsumos> rcbInsumosList;
    public static volatile SingularAttribute<Clasificacion, Date> fechaAlta;
    public static volatile SingularAttribute<Clasificacion, Date> fechaModificiacion;
    public static volatile SingularAttribute<Clasificacion, Integer> idClasificacion;
    public static volatile SingularAttribute<Clasificacion, String> usuarioAlta;
    public static volatile SingularAttribute<Clasificacion, Integer> activo;

}