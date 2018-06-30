package com.issste.sicabis.ejb.modelo;

import com.issste.sicabis.ejb.modelo.DpnInsumos;
import com.issste.sicabis.ejb.modelo.Insumos;
import com.issste.sicabis.ejb.modelo.TipoInsumoDpn;
import com.issste.sicabis.ejb.modelo.UnidadInsumosDpn;
import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2018-06-22T12:18:02")
@StaticMetamodel(InsumoDpn.class)
public class InsumoDpn_ { 

    public static volatile ListAttribute<InsumoDpn, DpnInsumos> dpnInsumosList;
    public static volatile SingularAttribute<InsumoDpn, TipoInsumoDpn> idTipoInsumoDpn;
    public static volatile SingularAttribute<InsumoDpn, String> usuarioModificacion;
    public static volatile SingularAttribute<InsumoDpn, Integer> idInsumoDpn;
    public static volatile SingularAttribute<InsumoDpn, Date> fechaBaja;
    public static volatile SingularAttribute<InsumoDpn, String> usuarioBaja;
    public static volatile SingularAttribute<InsumoDpn, Insumos> idInsumo;
    public static volatile SingularAttribute<InsumoDpn, Date> fechaAlta;
    public static volatile ListAttribute<InsumoDpn, UnidadInsumosDpn> unidadInsumoDpnList;
    public static volatile SingularAttribute<InsumoDpn, Date> fechaModificacion;
    public static volatile SingularAttribute<InsumoDpn, String> usuarioAlta;
    public static volatile SingularAttribute<InsumoDpn, Integer> estatusInsumoDpn;

}