package com.issste.sicabis.ejb.modelo;

import com.issste.sicabis.ejb.modelo.OrdenSuministro;
import com.issste.sicabis.ejb.modelo.ProcedimientoRcbDestinos;
import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2018-06-22T12:18:02")
@StaticMetamodel(ProcedimientoRcbDestinosOrden.class)
public class ProcedimientoRcbDestinosOrden_ { 

    public static volatile SingularAttribute<ProcedimientoRcbDestinosOrden, String> usuarioModificacion;
    public static volatile SingularAttribute<ProcedimientoRcbDestinosOrden, Date> fechaBaja;
    public static volatile SingularAttribute<ProcedimientoRcbDestinosOrden, String> usuarioBaja;
    public static volatile SingularAttribute<ProcedimientoRcbDestinosOrden, Date> fechaAlta;
    public static volatile SingularAttribute<ProcedimientoRcbDestinosOrden, Date> fechaModificacion;
    public static volatile SingularAttribute<ProcedimientoRcbDestinosOrden, ProcedimientoRcbDestinos> idProcedimientoRcbDestinos;
    public static volatile SingularAttribute<ProcedimientoRcbDestinosOrden, String> usuarioAlta;
    public static volatile SingularAttribute<ProcedimientoRcbDestinosOrden, OrdenSuministro> idOrdenSuministro;
    public static volatile SingularAttribute<ProcedimientoRcbDestinosOrden, Integer> activo;
    public static volatile SingularAttribute<ProcedimientoRcbDestinosOrden, Integer> idProcedimientoRcbDestinosOrden;

}