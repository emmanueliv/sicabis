package com.issste.sicabis.ejb.modelo;

import com.issste.sicabis.ejb.modelo.Destinos;
import com.issste.sicabis.ejb.modelo.ProcedimientoRcb;
import com.issste.sicabis.ejb.modelo.ProcedimientoRcbDestinosOrden;
import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2018-06-22T12:18:02")
@StaticMetamodel(ProcedimientoRcbDestinos.class)
public class ProcedimientoRcbDestinos_ { 

    public static volatile SingularAttribute<ProcedimientoRcbDestinos, Destinos> idDestino;
    public static volatile SingularAttribute<ProcedimientoRcbDestinos, String> usuarioModificacion;
    public static volatile SingularAttribute<ProcedimientoRcbDestinos, ProcedimientoRcb> idProcedimientoRcb;
    public static volatile SingularAttribute<ProcedimientoRcbDestinos, Date> fechaBaja;
    public static volatile ListAttribute<ProcedimientoRcbDestinos, ProcedimientoRcbDestinosOrden> procedimientoRcbDestinosOrdenList;
    public static volatile SingularAttribute<ProcedimientoRcbDestinos, String> usuarioBaja;
    public static volatile SingularAttribute<ProcedimientoRcbDestinos, Date> fechaAlta;
    public static volatile SingularAttribute<ProcedimientoRcbDestinos, Integer> idProcedimientoRcbDestinos;
    public static volatile SingularAttribute<ProcedimientoRcbDestinos, Date> fechaModificacion;
    public static volatile SingularAttribute<ProcedimientoRcbDestinos, String> usuarioAlta;
    public static volatile SingularAttribute<ProcedimientoRcbDestinos, Integer> activo;

}