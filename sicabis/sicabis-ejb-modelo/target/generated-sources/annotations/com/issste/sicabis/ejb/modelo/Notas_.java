package com.issste.sicabis.ejb.modelo;

import com.issste.sicabis.ejb.modelo.ProcedimientoRcb;
import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2018-06-22T12:18:02")
@StaticMetamodel(Notas.class)
public class Notas_ { 

    public static volatile SingularAttribute<Notas, Date> fechaModificaciones;
    public static volatile SingularAttribute<Notas, String> usuarioModificacion;
    public static volatile SingularAttribute<Notas, ProcedimientoRcb> idProcedimientoRcb;
    public static volatile SingularAttribute<Notas, Date> fechaBaja;
    public static volatile SingularAttribute<Notas, Integer> idNotas;
    public static volatile SingularAttribute<Notas, String> usuarioBaja;
    public static volatile SingularAttribute<Notas, String> descripcion;
    public static volatile SingularAttribute<Notas, Date> fechaAlta;
    public static volatile SingularAttribute<Notas, String> usuarioAlta;
    public static volatile SingularAttribute<Notas, Integer> activo;

}