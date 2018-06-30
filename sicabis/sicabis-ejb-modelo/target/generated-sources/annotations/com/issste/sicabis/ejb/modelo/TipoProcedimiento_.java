package com.issste.sicabis.ejb.modelo;

import com.issste.sicabis.ejb.modelo.Procedimientos;
import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2018-06-22T12:18:02")
@StaticMetamodel(TipoProcedimiento.class)
public class TipoProcedimiento_ { 

    public static volatile SingularAttribute<TipoProcedimiento, String> usuarioModificacion;
    public static volatile SingularAttribute<TipoProcedimiento, Date> fechaBaja;
    public static volatile SingularAttribute<TipoProcedimiento, String> usuarioBaja;
    public static volatile SingularAttribute<TipoProcedimiento, String> descripcion;
    public static volatile SingularAttribute<TipoProcedimiento, Date> fechaAlta;
    public static volatile SingularAttribute<TipoProcedimiento, Date> fechaModificiacion;
    public static volatile SingularAttribute<TipoProcedimiento, Integer> idTipoProcedimiento;
    public static volatile SingularAttribute<TipoProcedimiento, String> usuarioAlta;
    public static volatile ListAttribute<TipoProcedimiento, Procedimientos> procedimientosList;
    public static volatile SingularAttribute<TipoProcedimiento, Integer> activo;

}