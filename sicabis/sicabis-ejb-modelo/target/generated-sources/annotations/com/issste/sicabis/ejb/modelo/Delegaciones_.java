package com.issste.sicabis.ejb.modelo;

import com.issste.sicabis.ejb.modelo.ContactosAlertasDpn;
import com.issste.sicabis.ejb.modelo.DetalleDelegacion;
import com.issste.sicabis.ejb.modelo.PorcentajeDelegacion;
import com.issste.sicabis.ejb.modelo.PorcentajeDelegacionHistorico;
import com.issste.sicabis.ejb.modelo.UnidadesMedicas;
import com.issste.sicabis.ejb.modelo.Ur;
import com.issste.sicabis.ejb.modelo.Usuarios;
import com.issste.sicabis.ejb.service.silodisa.modelo.MapaEjecutivoDispDelegaciones;
import com.issste.sicabis.ejb.service.silodisa.modelo.MapaEjecutivoDispG40;
import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2018-06-22T12:18:02")
@StaticMetamodel(Delegaciones.class)
public class Delegaciones_ { 

    public static volatile SingularAttribute<Delegaciones, Date> fechaModificaciones;
    public static volatile SingularAttribute<Delegaciones, String> usuarioModificacion;
    public static volatile ListAttribute<Delegaciones, DetalleDelegacion> detalleDelegacionList;
    public static volatile SingularAttribute<Delegaciones, String> nombreDelegacion;
    public static volatile ListAttribute<Delegaciones, UnidadesMedicas> unidadesMedicasList;
    public static volatile SingularAttribute<Delegaciones, Integer> activo;
    public static volatile ListAttribute<Delegaciones, MapaEjecutivoDispDelegaciones> mapaEjecutivoDispDelegacionesList;
    public static volatile ListAttribute<Delegaciones, Ur> urList;
    public static volatile SingularAttribute<Delegaciones, Integer> idDelegacion;
    public static volatile SingularAttribute<Delegaciones, Date> fechaBaja;
    public static volatile ListAttribute<Delegaciones, PorcentajeDelegacion> porcentajeDelegacionList;
    public static volatile SingularAttribute<Delegaciones, String> usuarioBaja;
    public static volatile SingularAttribute<Delegaciones, Date> fechaAlta;
    public static volatile ListAttribute<Delegaciones, MapaEjecutivoDispG40> mapaEjecutivoDispG40List;
    public static volatile ListAttribute<Delegaciones, Usuarios> usuariosList;
    public static volatile ListAttribute<Delegaciones, ContactosAlertasDpn> contactosAlertasDpnList;
    public static volatile ListAttribute<Delegaciones, PorcentajeDelegacionHistorico> porcentajeDelegacionHistoricoList;
    public static volatile SingularAttribute<Delegaciones, String> usuarioAlta;

}