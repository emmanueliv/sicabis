package com.issste.sicabis.ejb.modelo;

import com.issste.sicabis.ejb.modelo.Contratos;
import com.issste.sicabis.ejb.modelo.ProcedimientoRcbDestinos;
import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2018-06-22T12:18:02")
@StaticMetamodel(Destinos.class)
public class Destinos_ { 

    public static volatile SingularAttribute<Destinos, Integer> idDestino;
    public static volatile SingularAttribute<Destinos, String> domicilio;
    public static volatile SingularAttribute<Destinos, Integer> idPadre;
    public static volatile SingularAttribute<Destinos, Integer> activo;
    public static volatile SingularAttribute<Destinos, String> nombre;
    public static volatile SingularAttribute<Destinos, String> usuarioModifcacion;
    public static volatile SingularAttribute<Destinos, Date> fechaBaja;
    public static volatile SingularAttribute<Destinos, String> usuarioBaja;
    public static volatile SingularAttribute<Destinos, Date> fechaAlta;
    public static volatile ListAttribute<Destinos, ProcedimientoRcbDestinos> procedimientoRcbDestinosList;
    public static volatile SingularAttribute<Destinos, Date> fechaModificacion;
    public static volatile SingularAttribute<Destinos, String> clave;
    public static volatile SingularAttribute<Destinos, String> usuarioAlta;
    public static volatile ListAttribute<Destinos, Contratos> contratosList;

}