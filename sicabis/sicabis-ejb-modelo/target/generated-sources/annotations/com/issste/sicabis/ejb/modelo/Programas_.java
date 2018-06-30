package com.issste.sicabis.ejb.modelo;

import com.issste.sicabis.ejb.modelo.AsignacionInsumos;
import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2018-06-22T12:18:02")
@StaticMetamodel(Programas.class)
public class Programas_ { 

    public static volatile SingularAttribute<Programas, String> usuarioModificacion;
    public static volatile SingularAttribute<Programas, Date> fechaBaja;
    public static volatile SingularAttribute<Programas, String> usuarioBaja;
    public static volatile ListAttribute<Programas, AsignacionInsumos> asignacionInsumosList;
    public static volatile SingularAttribute<Programas, String> descripcion;
    public static volatile SingularAttribute<Programas, Date> fechaAlta;
    public static volatile SingularAttribute<Programas, Date> fechaModificacion;
    public static volatile SingularAttribute<Programas, Integer> idPrograma;
    public static volatile SingularAttribute<Programas, String> usuarioAlta;
    public static volatile SingularAttribute<Programas, Integer> activo;

}