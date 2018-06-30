package com.issste.sicabis.ejb.modelo;

import com.issste.sicabis.ejb.modelo.CompradorContrato;
import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2018-06-22T12:18:02")
@StaticMetamodel(Compradores.class)
public class Compradores_ { 

    public static volatile SingularAttribute<Compradores, String> nombre;
    public static volatile SingularAttribute<Compradores, String> amaterno;
    public static volatile ListAttribute<Compradores, CompradorContrato> compradorContratoList;
    public static volatile SingularAttribute<Compradores, String> usuarioModificacion;
    public static volatile SingularAttribute<Compradores, Date> fechaBaja;
    public static volatile SingularAttribute<Compradores, Integer> idComprador;
    public static volatile SingularAttribute<Compradores, String> apaterno;
    public static volatile SingularAttribute<Compradores, String> usuarioBaja;
    public static volatile SingularAttribute<Compradores, Integer> idPadre;
    public static volatile SingularAttribute<Compradores, Date> fechaAlta;
    public static volatile SingularAttribute<Compradores, Date> fechaModificacion;
    public static volatile SingularAttribute<Compradores, String> usuarioAlta;
    public static volatile SingularAttribute<Compradores, Integer> activo;

}