package com.issste.sicabis.ejb.modelo;

import com.issste.sicabis.ejb.modelo.Procedimientos;
import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2018-06-22T12:18:02")
@StaticMetamodel(ClasificacionProcedimiento.class)
public class ClasificacionProcedimiento_ { 

    public static volatile SingularAttribute<ClasificacionProcedimiento, String> usuarioModificacion;
    public static volatile SingularAttribute<ClasificacionProcedimiento, Date> fechaBaja;
    public static volatile SingularAttribute<ClasificacionProcedimiento, String> usuarioBaja;
    public static volatile SingularAttribute<ClasificacionProcedimiento, String> descripcion;
    public static volatile SingularAttribute<ClasificacionProcedimiento, Date> fechaAlta;
    public static volatile SingularAttribute<ClasificacionProcedimiento, Date> fechaModificacion;
    public static volatile SingularAttribute<ClasificacionProcedimiento, Integer> idClasificacionProcedimiento;
    public static volatile SingularAttribute<ClasificacionProcedimiento, String> usuarioAlta;
    public static volatile ListAttribute<ClasificacionProcedimiento, Procedimientos> procedimientosList;
    public static volatile SingularAttribute<ClasificacionProcedimiento, Integer> activo;

}