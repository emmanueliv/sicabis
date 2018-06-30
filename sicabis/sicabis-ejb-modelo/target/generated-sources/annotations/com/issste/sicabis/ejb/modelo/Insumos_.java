package com.issste.sicabis.ejb.modelo;

import com.issste.sicabis.ejb.modelo.AsignacionInsumos;
import com.issste.sicabis.ejb.modelo.CanjePermuta;
import com.issste.sicabis.ejb.modelo.Clasificacion;
import com.issste.sicabis.ejb.modelo.ClasificacionImportancia;
import com.issste.sicabis.ejb.modelo.CrInsumos;
import com.issste.sicabis.ejb.modelo.DescripcionInsumosOpcional;
import com.issste.sicabis.ejb.modelo.DetalleRecoleccion;
import com.issste.sicabis.ejb.modelo.Grupo;
import com.issste.sicabis.ejb.modelo.GrupoTerapeutico;
import com.issste.sicabis.ejb.modelo.InsumoDpn;
import com.issste.sicabis.ejb.modelo.Nivel;
import com.issste.sicabis.ejb.modelo.PlaneacionDetalle;
import com.issste.sicabis.ejb.modelo.RcbInsumos;
import com.issste.sicabis.ejb.modelo.SolicitudesInsumosPacientes;
import com.issste.sicabis.ejb.modelo.TipoInsumos;
import com.issste.sicabis.ejb.modelo.UnidadPieza;
import java.math.BigDecimal;
import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2018-06-22T12:18:02")
@StaticMetamodel(Insumos.class)
public class Insumos_ { 

    public static volatile SingularAttribute<Insumos, Nivel> idNivel;
    public static volatile SingularAttribute<Insumos, String> indicaciones;
    public static volatile SingularAttribute<Insumos, Integer> idInsumo;
    public static volatile ListAttribute<Insumos, SolicitudesInsumosPacientes> solicitudesInsumosPacientesList;
    public static volatile SingularAttribute<Insumos, String> partidaPresupuestal;
    public static volatile SingularAttribute<Insumos, Clasificacion> idClasificacion;
    public static volatile SingularAttribute<Insumos, String> viaAdministracionDosis;
    public static volatile SingularAttribute<Insumos, Date> fechaBaja;
    public static volatile ListAttribute<Insumos, AsignacionInsumos> asignacionInsumosList;
    public static volatile SingularAttribute<Insumos, String> usuarioBaja;
    public static volatile SingularAttribute<Insumos, Date> fechaModificacion;
    public static volatile SingularAttribute<Insumos, String> clave;
    public static volatile ListAttribute<Insumos, DetalleRecoleccion> detalleRecoleccionList;
    public static volatile ListAttribute<Insumos, InsumoDpn> insumoDpnList;
    public static volatile SingularAttribute<Insumos, String> usuarioModificacion;
    public static volatile SingularAttribute<Insumos, Grupo> idGrupo;
    public static volatile SingularAttribute<Insumos, String> descripcion;
    public static volatile SingularAttribute<Insumos, TipoInsumos> idTipoInsumos;
    public static volatile SingularAttribute<Insumos, GrupoTerapeutico> idGrupoTerapeutico;
    public static volatile ListAttribute<Insumos, DescripcionInsumosOpcional> descripcionInsumosOpcionalList;
    public static volatile SingularAttribute<Insumos, Integer> activo;
    public static volatile ListAttribute<Insumos, CrInsumos> crInsumosList;
    public static volatile SingularAttribute<Insumos, BigDecimal> precioUnitario;
    public static volatile SingularAttribute<Insumos, UnidadPieza> idUnidadPieza;
    public static volatile ListAttribute<Insumos, PlaneacionDetalle> planeacionDetalleList;
    public static volatile ListAttribute<Insumos, RcbInsumos> rcbInsumosList;
    public static volatile SingularAttribute<Insumos, Date> fechaAlta;
    public static volatile ListAttribute<Insumos, CanjePermuta> canjePermutaList;
    public static volatile ListAttribute<Insumos, CanjePermuta> canjePermutaList1;
    public static volatile SingularAttribute<Insumos, String> usuarioAlta;
    public static volatile SingularAttribute<Insumos, ClasificacionImportancia> idClasificacionImportancia;

}