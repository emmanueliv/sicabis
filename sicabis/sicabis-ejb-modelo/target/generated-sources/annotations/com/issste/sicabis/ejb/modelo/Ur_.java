package com.issste.sicabis.ejb.modelo;

import com.issste.sicabis.ejb.modelo.AlertasDpn;
import com.issste.sicabis.ejb.modelo.Delegaciones;
import com.issste.sicabis.ejb.modelo.TipoUr;
import com.issste.sicabis.ejb.modelo.Usuarios;
import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2018-06-22T12:18:02")
@StaticMetamodel(Ur.class)
public class Ur_ { 

    public static volatile SingularAttribute<Ur, String> nombre;
    public static volatile SingularAttribute<Ur, Delegaciones> idDelegacion;
    public static volatile SingularAttribute<Ur, String> numUr;
    public static volatile SingularAttribute<Ur, Integer> ur;
    public static volatile SingularAttribute<Ur, TipoUr> idTipoUr;
    public static volatile ListAttribute<Ur, Usuarios> usuariosList;
    public static volatile ListAttribute<Ur, AlertasDpn> alertasDpnList;
    public static volatile SingularAttribute<Ur, Integer> activo;

}