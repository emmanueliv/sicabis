package com.issste.sicabis.ejb.modelo;

import com.issste.sicabis.ejb.modelo.FalloProcedimientoRcb;
import com.issste.sicabis.ejb.modelo.Notas;
import com.issste.sicabis.ejb.modelo.ProcedimientoRcbDestinos;
import com.issste.sicabis.ejb.modelo.Procedimientos;
import com.issste.sicabis.ejb.modelo.Propuestas;
import com.issste.sicabis.ejb.modelo.RcbInsumos;
import java.math.BigDecimal;
import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2018-06-22T12:18:02")
@StaticMetamodel(ProcedimientoRcb.class)
public class ProcedimientoRcb_ { 

    public static volatile SingularAttribute<ProcedimientoRcb, Integer> desierta;
    public static volatile SingularAttribute<ProcedimientoRcb, BigDecimal> importe;
    public static volatile SingularAttribute<ProcedimientoRcb, String> usuarioModificacion;
    public static volatile ListAttribute<ProcedimientoRcb, FalloProcedimientoRcb> falloProcedimientoRcbList;
    public static volatile ListAttribute<ProcedimientoRcb, Propuestas> propuestasList;
    public static volatile SingularAttribute<ProcedimientoRcb, Integer> cantidadPiezasOriginal;
    public static volatile SingularAttribute<ProcedimientoRcb, Procedimientos> idProcedimiento;
    public static volatile SingularAttribute<ProcedimientoRcb, Integer> activo;
    public static volatile SingularAttribute<ProcedimientoRcb, Integer> cantidadPiezas;
    public static volatile SingularAttribute<ProcedimientoRcb, BigDecimal> precioUnitario;
    public static volatile SingularAttribute<ProcedimientoRcb, Integer> idProcedimientoRcb;
    public static volatile SingularAttribute<ProcedimientoRcb, Date> fechaBaja;
    public static volatile SingularAttribute<ProcedimientoRcb, String> usuarioBaja;
    public static volatile SingularAttribute<ProcedimientoRcb, Integer> existencias;
    public static volatile SingularAttribute<ProcedimientoRcb, Integer> desiertaParcial;
    public static volatile SingularAttribute<ProcedimientoRcb, Integer> mesesCobertura;
    public static volatile SingularAttribute<ProcedimientoRcb, Date> fechaAlta;
    public static volatile SingularAttribute<ProcedimientoRcb, RcbInsumos> idRcbInsumos;
    public static volatile SingularAttribute<ProcedimientoRcb, Integer> consumoPromedio;
    public static volatile SingularAttribute<ProcedimientoRcb, Date> fechaModificacion;
    public static volatile ListAttribute<ProcedimientoRcb, ProcedimientoRcbDestinos> procedimientoRcbDestinosList;
    public static volatile ListAttribute<ProcedimientoRcb, Notas> notasList;
    public static volatile SingularAttribute<ProcedimientoRcb, String> usuarioAlta;

}