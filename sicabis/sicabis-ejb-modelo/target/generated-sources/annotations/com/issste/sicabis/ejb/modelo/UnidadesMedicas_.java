package com.issste.sicabis.ejb.modelo;

import com.issste.sicabis.ejb.modelo.ContactosAlertasDpn;
import com.issste.sicabis.ejb.modelo.Contratos;
import com.issste.sicabis.ejb.modelo.Delegaciones;
import com.issste.sicabis.ejb.modelo.DpnInsumos;
import com.issste.sicabis.ejb.modelo.Nivel;
import com.issste.sicabis.ejb.modelo.UnidadInsumosDpn;
import com.issste.sicabis.ejb.modelo.Usuarios;
import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2018-06-22T12:18:02")
@StaticMetamodel(UnidadesMedicas.class)
public class UnidadesMedicas_ { 

    public static volatile SingularAttribute<UnidadesMedicas, Nivel> idNivel;
    public static volatile SingularAttribute<UnidadesMedicas, Date> fechaModificaciones;
    public static volatile SingularAttribute<UnidadesMedicas, String> usuarioModificacion;
    public static volatile SingularAttribute<UnidadesMedicas, Integer> idPadre;
    public static volatile SingularAttribute<UnidadesMedicas, Integer> activo;
    public static volatile ListAttribute<UnidadesMedicas, DpnInsumos> dpnInsumosList;
    public static volatile SingularAttribute<UnidadesMedicas, String> nombre;
    public static volatile SingularAttribute<UnidadesMedicas, String> clavePresupuestal;
    public static volatile SingularAttribute<UnidadesMedicas, Integer> hospitalRegional;
    public static volatile SingularAttribute<UnidadesMedicas, String> claveUmu;
    public static volatile SingularAttribute<UnidadesMedicas, Delegaciones> idDelegacion;
    public static volatile SingularAttribute<UnidadesMedicas, Date> fechaBaja;
    public static volatile SingularAttribute<UnidadesMedicas, Integer> idUnidadesMedicas;
    public static volatile SingularAttribute<UnidadesMedicas, String> usuarioBaja;
    public static volatile SingularAttribute<UnidadesMedicas, Date> fechaAlta;
    public static volatile ListAttribute<UnidadesMedicas, UnidadInsumosDpn> unidadInsumoDpnList;
    public static volatile ListAttribute<UnidadesMedicas, Usuarios> usuariosList;
    public static volatile ListAttribute<UnidadesMedicas, ContactosAlertasDpn> contactosAlertasDpnList;
    public static volatile SingularAttribute<UnidadesMedicas, Integer> concentradora;
    public static volatile SingularAttribute<UnidadesMedicas, String> usuarioAlta;
    public static volatile ListAttribute<UnidadesMedicas, Contratos> contratosList;

}