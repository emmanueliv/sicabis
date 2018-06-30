package com.issste.sicabis.ejb.modelo;

import com.issste.sicabis.ejb.modelo.Delegaciones;
import com.issste.sicabis.ejb.modelo.UnidadesMedicas;
import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2018-06-22T12:18:02")
@StaticMetamodel(ContactosAlertasDpn.class)
public class ContactosAlertasDpn_ { 

    public static volatile SingularAttribute<ContactosAlertasDpn, String> usuarioModificacion;
    public static volatile SingularAttribute<ContactosAlertasDpn, Integer> mapas;
    public static volatile SingularAttribute<ContactosAlertasDpn, String> apellidoPaterno;
    public static volatile SingularAttribute<ContactosAlertasDpn, Integer> estados;
    public static volatile SingularAttribute<ContactosAlertasDpn, Integer> activo;
    public static volatile SingularAttribute<ContactosAlertasDpn, String> nombre;
    public static volatile SingularAttribute<ContactosAlertasDpn, String> red;
    public static volatile SingularAttribute<ContactosAlertasDpn, Delegaciones> idDelegacion;
    public static volatile SingularAttribute<ContactosAlertasDpn, Date> fechaBaja;
    public static volatile SingularAttribute<ContactosAlertasDpn, String> usuarioBaja;
    public static volatile SingularAttribute<ContactosAlertasDpn, String> apellidoMaterno;
    public static volatile SingularAttribute<ContactosAlertasDpn, Integer> g40;
    public static volatile SingularAttribute<ContactosAlertasDpn, Date> fechaAlta;
    public static volatile SingularAttribute<ContactosAlertasDpn, Date> fechaModificacion;
    public static volatile SingularAttribute<ContactosAlertasDpn, Integer> idContactosAlertasDpn;
    public static volatile SingularAttribute<ContactosAlertasDpn, String> usuarioAlta;
    public static volatile SingularAttribute<ContactosAlertasDpn, String> correo;
    public static volatile SingularAttribute<ContactosAlertasDpn, Integer> delegaciones;
    public static volatile SingularAttribute<ContactosAlertasDpn, UnidadesMedicas> idUnidadMedica;

}