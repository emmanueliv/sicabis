package com.issste.sicabis.ejb.modelo;

import com.issste.sicabis.ejb.modelo.Clasificacion;
import com.issste.sicabis.ejb.modelo.Grupo;
import com.issste.sicabis.ejb.modelo.GrupoTerapeutico;
import com.issste.sicabis.ejb.modelo.Insumos;
import com.issste.sicabis.ejb.modelo.InsumosRcbSolicitudInvestigacionMercado;
import com.issste.sicabis.ejb.modelo.Nivel;
import com.issste.sicabis.ejb.modelo.ProcedimientoRcb;
import com.issste.sicabis.ejb.modelo.Rcb;
import com.issste.sicabis.ejb.modelo.UnidadPieza;
import java.math.BigDecimal;
import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2018-06-22T12:18:02")
@StaticMetamodel(RcbInsumos.class)
public class RcbInsumos_ { 

    public static volatile SingularAttribute<RcbInsumos, Nivel> idNivel;
    public static volatile SingularAttribute<RcbInsumos, Integer> renglon;
    public static volatile SingularAttribute<RcbInsumos, Insumos> idInsumo;
    public static volatile SingularAttribute<RcbInsumos, Clasificacion> idClasificacion;
    public static volatile SingularAttribute<RcbInsumos, String> viaAdministracionDosisInsumo;
    public static volatile SingularAttribute<RcbInsumos, String> partidaPresupuestalInsumo;
    public static volatile SingularAttribute<RcbInsumos, Integer> cantidadPiezas;
    public static volatile SingularAttribute<RcbInsumos, Date> fechaBaja;
    public static volatile SingularAttribute<RcbInsumos, String> usuarioBaja;
    public static volatile SingularAttribute<RcbInsumos, Integer> existencias;
    public static volatile SingularAttribute<RcbInsumos, Integer> cantidadPiezasModificada;
    public static volatile SingularAttribute<RcbInsumos, Integer> mesesCobertura;
    public static volatile SingularAttribute<RcbInsumos, Integer> idRcbInsumos;
    public static volatile SingularAttribute<RcbInsumos, Integer> consumoPromedio;
    public static volatile SingularAttribute<RcbInsumos, BigDecimal> importeModificado;
    public static volatile SingularAttribute<RcbInsumos, Date> fechaModificacion;
    public static volatile SingularAttribute<RcbInsumos, Rcb> idRcb;
    public static volatile ListAttribute<RcbInsumos, InsumosRcbSolicitudInvestigacionMercado> insumosRcbSolicitudInvestigacionMercadoList;
    public static volatile SingularAttribute<RcbInsumos, BigDecimal> importe;
    public static volatile SingularAttribute<RcbInsumos, String> usuarioModificacion;
    public static volatile SingularAttribute<RcbInsumos, Grupo> idGrupo;
    public static volatile SingularAttribute<RcbInsumos, BigDecimal> precioUnitarioModificado;
    public static volatile SingularAttribute<RcbInsumos, String> claveInsumo;
    public static volatile SingularAttribute<RcbInsumos, GrupoTerapeutico> idGrupoTerapeutico;
    public static volatile SingularAttribute<RcbInsumos, String> indicacionesInsumo;
    public static volatile SingularAttribute<RcbInsumos, Integer> activo;
    public static volatile SingularAttribute<RcbInsumos, BigDecimal> iva;
    public static volatile SingularAttribute<RcbInsumos, BigDecimal> precioUnitario;
    public static volatile SingularAttribute<RcbInsumos, UnidadPieza> idUnidadPieza;
    public static volatile SingularAttribute<RcbInsumos, String> descripcionInsumo;
    public static volatile ListAttribute<RcbInsumos, ProcedimientoRcb> procedimientoRcbList;
    public static volatile SingularAttribute<RcbInsumos, Date> fechaAlta;
    public static volatile SingularAttribute<RcbInsumos, String> usuarioAlta;

}