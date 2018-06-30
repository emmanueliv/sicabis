package com.issste.sicabis.ejb.modelo;

import com.issste.sicabis.ejb.modelo.Procedimientos;
import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2018-06-22T12:18:02")
@StaticMetamodel(CaracterProcedimiento.class)
public class CaracterProcedimiento_ { 

    public static volatile SingularAttribute<CaracterProcedimiento, String> usuarioModificacion;
    public static volatile SingularAttribute<CaracterProcedimiento, Date> fechaBaja;
    public static volatile SingularAttribute<CaracterProcedimiento, String> usuarioBaja;
    public static volatile SingularAttribute<CaracterProcedimiento, String> descripcion;
    public static volatile SingularAttribute<CaracterProcedimiento, Date> fechaAlta;
    public static volatile SingularAttribute<CaracterProcedimiento, Integer> idCaracterProcedimiento;
    public static volatile SingularAttribute<CaracterProcedimiento, Date> fechaModificacion;
    public static volatile SingularAttribute<CaracterProcedimiento, String> usuarioAlta;
    public static volatile ListAttribute<CaracterProcedimiento, Procedimientos> procedimientosList;
    public static volatile SingularAttribute<CaracterProcedimiento, Integer> activo;

}