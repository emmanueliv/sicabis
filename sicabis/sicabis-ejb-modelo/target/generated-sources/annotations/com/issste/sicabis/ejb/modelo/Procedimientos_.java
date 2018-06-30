package com.issste.sicabis.ejb.modelo;

import com.issste.sicabis.ejb.modelo.CaracterProcedimiento;
import com.issste.sicabis.ejb.modelo.ClasificacionProcedimiento;
import com.issste.sicabis.ejb.modelo.Estatus;
import com.issste.sicabis.ejb.modelo.ProcedimientoRcb;
import com.issste.sicabis.ejb.modelo.TipoCompra;
import com.issste.sicabis.ejb.modelo.TipoProcedimiento;
import java.math.BigDecimal;
import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2018-06-22T12:18:02")
@StaticMetamodel(Procedimientos.class)
public class Procedimientos_ { 

    public static volatile SingularAttribute<Procedimientos, Date> publicacionConvocatoria;
    public static volatile SingularAttribute<Procedimientos, Date> invitacion3Per;
    public static volatile SingularAttribute<Procedimientos, TipoCompra> idTipoCompra;
    public static volatile SingularAttribute<Procedimientos, String> usuarioModificacion;
    public static volatile SingularAttribute<Procedimientos, String> comentarios;
    public static volatile SingularAttribute<Procedimientos, Date> elaboracionConvocatoria;
    public static volatile SingularAttribute<Procedimientos, CaracterProcedimiento> idCaracterProcedimiento;
    public static volatile SingularAttribute<Procedimientos, TipoProcedimiento> idTipoProcedimiento;
    public static volatile SingularAttribute<Procedimientos, ClasificacionProcedimiento> idClasificacionProcedimiento;
    public static volatile SingularAttribute<Procedimientos, Integer> idProcedimiento;
    public static volatile SingularAttribute<Procedimientos, Integer> activo;
    public static volatile SingularAttribute<Procedimientos, BigDecimal> importeTotal;
    public static volatile SingularAttribute<Procedimientos, String> numeroProcedimiento;
    public static volatile SingularAttribute<Procedimientos, Date> juntaAclaracionesConvocatoria;
    public static volatile SingularAttribute<Procedimientos, Date> fechaBaja;
    public static volatile SingularAttribute<Procedimientos, Date> juntaConclusionAclaracionesConvocatoria;
    public static volatile SingularAttribute<Procedimientos, String> usuarioBaja;
    public static volatile ListAttribute<Procedimientos, ProcedimientoRcb> procedimientoRcbList;
    public static volatile SingularAttribute<Procedimientos, Date> fechaAlta;
    public static volatile SingularAttribute<Procedimientos, Date> fechaModificacion;
    public static volatile SingularAttribute<Procedimientos, Date> fechaProcedimiento;
    public static volatile SingularAttribute<Procedimientos, String> usuarioAlta;
    public static volatile SingularAttribute<Procedimientos, Estatus> idEstatus;
    public static volatile SingularAttribute<Procedimientos, Integer> verificaSansiones;

}