package com.issste.sicabis.ejb.modelo;

import com.issste.sicabis.ejb.modelo.Usuarios;
import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2018-06-22T12:18:02")
@StaticMetamodel(AlertasCorreo.class)
public class AlertasCorreo_ { 

    public static volatile SingularAttribute<AlertasCorreo, String> usuarioModificacion;
    public static volatile SingularAttribute<AlertasCorreo, Usuarios> idUsuario;
    public static volatile SingularAttribute<AlertasCorreo, Date> fechaBaja;
    public static volatile SingularAttribute<AlertasCorreo, Integer> idAlertasCorreo;
    public static volatile SingularAttribute<AlertasCorreo, String> usuarioBaja;
    public static volatile SingularAttribute<AlertasCorreo, Date> fechaAlta;
    public static volatile SingularAttribute<AlertasCorreo, Date> fechaModificacion;
    public static volatile SingularAttribute<AlertasCorreo, String> usuarioAlta;
    public static volatile SingularAttribute<AlertasCorreo, Integer> activo;

}