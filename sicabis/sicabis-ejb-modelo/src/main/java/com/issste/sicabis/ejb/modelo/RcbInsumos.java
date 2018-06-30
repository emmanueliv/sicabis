/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.issste.sicabis.ejb.modelo;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author kriosoft
 */
@Entity
@Table(name = "rcb_insumos")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "RcbInsumos.findAll", query = "SELECT r FROM RcbInsumos r"),
    @NamedQuery(name = "RcbInsumos.findByIdRcbInsumos", query = "SELECT r FROM RcbInsumos r WHERE r.idRcbInsumos = :idRcbInsumos"),
    @NamedQuery(name = "RcbInsumos.findByActivo", query = "SELECT r FROM RcbInsumos r WHERE r.activo = :activo"),
    @NamedQuery(name = "RcbInsumos.findByCantidadPiezas", query = "SELECT r FROM RcbInsumos r WHERE r.cantidadPiezas = :cantidadPiezas"),
    @NamedQuery(name = "RcbInsumos.findByPrecioUnitario", query = "SELECT r FROM RcbInsumos r WHERE r.precioUnitario = :precioUnitario"),
    @NamedQuery(name = "RcbInsumos.findByImporte", query = "SELECT r FROM RcbInsumos r WHERE r.importe = :importe"),
    @NamedQuery(name = "RcbInsumos.findByExistencias", query = "SELECT r FROM RcbInsumos r WHERE r.existencias = :existencias"),
    @NamedQuery(name = "RcbInsumos.findByConsumoPromedio", query = "SELECT r FROM RcbInsumos r WHERE r.consumoPromedio = :consumoPromedio"),
    @NamedQuery(name = "RcbInsumos.findByMesesCobertura", query = "SELECT r FROM RcbInsumos r WHERE r.mesesCobertura = :mesesCobertura"),
    @NamedQuery(name = "RcbInsumos.findByUsuarioAlta", query = "SELECT r FROM RcbInsumos r WHERE r.usuarioAlta = :usuarioAlta"),
    @NamedQuery(name = "RcbInsumos.findByUsuarioBaja", query = "SELECT r FROM RcbInsumos r WHERE r.usuarioBaja = :usuarioBaja"),
    @NamedQuery(name = "RcbInsumos.findByUsuarioModificacion", query = "SELECT r FROM RcbInsumos r WHERE r.usuarioModificacion = :usuarioModificacion"),
    @NamedQuery(name = "RcbInsumos.findByFechaAlta", query = "SELECT r FROM RcbInsumos r WHERE r.fechaAlta = :fechaAlta"),
    @NamedQuery(name = "RcbInsumos.findByFechaBaja", query = "SELECT r FROM RcbInsumos r WHERE r.fechaBaja = :fechaBaja"),
    @NamedQuery(name = "RcbInsumos.findByFechaModificacion", query = "SELECT r FROM RcbInsumos r WHERE r.fechaModificacion = :fechaModificacion")})
public class RcbInsumos implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id_rcb_insumos")
    private Integer idRcbInsumos;
    @Basic(optional = false)
    @NotNull
    @Column(name = "activo")
    private int activo;
    @Basic(optional = false)
    @NotNull
    @Column(name = "cantidad_piezas")
    private int cantidadPiezas;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Basic(optional = false)
    //@NotNull
    @Column(name = "precio_unitario")
    private BigDecimal precioUnitario;
    @Basic(optional = false)
    @NotNull
    @Column(name = "importe")
    private BigDecimal importe;
    @Basic(optional = false)
    @Column(name = "cantidad_piezas_modificada")
    private int cantidadPiezasModificada;
    @Basic(optional = false)
    @Column(name = "precio_unitario_modificado")
    private BigDecimal precioUnitarioModificado;
    @Basic(optional = false)
    @Column(name = "importe_modificado")
    private BigDecimal importeModificado;
    @Basic(optional = false)
    //@NotNull
    @Column(name = "existencias")
    private int existencias;
    @Basic(optional = false)
    //@NotNull
    @Column(name = "consumo_promedio")
    private int consumoPromedio;
    @Basic(optional = false)
    //@NotNull
    @Column(name = "meses_cobertura")
    private int mesesCobertura;
    @Column(name = "clave_insumo")
    private String claveInsumo;
    @Column(name = "descripcion_insumo")
    private String descripcionInsumo;
    @Column(name = "indicaciones_insumo")
    private String indicacionesInsumo;
    @Column(name = "partida_presupuestal_insumo")
    private String partidaPresupuestalInsumo;
    @Column(name = "via_administracion_dosis_insumo")
    private String viaAdministracionDosisInsumo;
    @Basic(optional = false)
    //@NotNull
    @Column(name = "renglon")
    private int renglon;
    @Size(max = 45)
    @Column(name = "usuario_alta")
    private String usuarioAlta;
    @Size(max = 45)
    @Column(name = "usuario_baja")
    private String usuarioBaja;
    @Size(max = 45)
    @Column(name = "usuario_modificacion")
    private String usuarioModificacion;
    @Column(name = "fecha_alta")
    @Temporal(TemporalType.DATE)
    private Date fechaAlta;
    @Column(name = "fecha_baja")
    @Temporal(TemporalType.DATE)
    private Date fechaBaja;
    @Column(name = "fecha_modificacion")
    @Temporal(TemporalType.DATE)
    private Date fechaModificacion;
    @Basic(optional = false)
    @Column(name = "iva")
    private BigDecimal iva;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idRcbInsumos")
    private List<InsumosRcbSolicitudInvestigacionMercado> insumosRcbSolicitudInvestigacionMercadoList;
    @JoinColumn(name = "id_rcb", referencedColumnName = "id_rcb")
    @ManyToOne(optional = false)
    private Rcb idRcb;
    @JoinColumn(name = "id_insumo", referencedColumnName = "id_insumo")
    @ManyToOne(optional = false)
    private Insumos idInsumo;
    @JoinColumn(name = "id_unidad_pieza", referencedColumnName = "id_unidad_pieza")
    @ManyToOne(optional = false)
    private UnidadPieza idUnidadPieza;
    @JoinColumn(name = "id_grupo", referencedColumnName = "id_grupo")
    @ManyToOne(optional = false)
    private Grupo idGrupo;
    @JoinColumn(name = "id_grupo_terapeutico", referencedColumnName = "id_grupo_terapeutico")
    @ManyToOne(optional = false)
    private GrupoTerapeutico idGrupoTerapeutico;
    @JoinColumn(name = "id_nivel", referencedColumnName = "id_nivel")
    @ManyToOne(optional = false)
    private Nivel idNivel;
    @JoinColumn(name = "id_clasificacion", referencedColumnName = "id_clasificacion")
    @ManyToOne(optional = false)
    private Clasificacion idClasificacion;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idRcbInsumos")
    private List<ProcedimientoRcb> procedimientoRcbList;

    public RcbInsumos() {
    }

    public RcbInsumos(Integer idRcbInsumos) {
        this.idRcbInsumos = idRcbInsumos;
    }

    public RcbInsumos(Integer idRcbInsumos, int activo, int cantidadPiezas, BigDecimal precioUnitario, BigDecimal importe, int existencias, int consumoPromedio, int mesesCobertura) {
        this.idRcbInsumos = idRcbInsumos;
        this.activo = activo;
        this.cantidadPiezas = cantidadPiezas;
        this.precioUnitario = precioUnitario;
        this.importe = importe;
        this.existencias = existencias;
        this.consumoPromedio = consumoPromedio;
        this.mesesCobertura = mesesCobertura;
    }

    public Integer getIdRcbInsumos() {
        return idRcbInsumos;
    }

    public void setIdRcbInsumos(Integer idRcbInsumos) {
        this.idRcbInsumos = idRcbInsumos;
    }

    public int getActivo() {
        return activo;
    }

    public void setActivo(int activo) {
        this.activo = activo;
    }

    public int getCantidadPiezas() {
        return cantidadPiezas;
    }

    public void setCantidadPiezas(int cantidadPiezas) {
        this.cantidadPiezas = cantidadPiezas;
    }

    public BigDecimal getPrecioUnitario() {
        return precioUnitario;
    }

    public void setPrecioUnitario(BigDecimal precioUnitario) {
        this.precioUnitario = precioUnitario;
    }

    public BigDecimal getImporte() {
        return importe;
    }

    public void setImporte(BigDecimal importe) {
        this.importe = importe;
    }

    public int getExistencias() {
        return existencias;
    }

    public void setExistencias(int existencias) {
        this.existencias = existencias;
    }

    public int getConsumoPromedio() {
        return consumoPromedio;
    }

    public void setConsumoPromedio(int consumoPromedio) {
        this.consumoPromedio = consumoPromedio;
    }

    public int getMesesCobertura() {
        return mesesCobertura;
    }

    public void setMesesCobertura(int mesesCobertura) {
        this.mesesCobertura = mesesCobertura;
    }

    public String getUsuarioAlta() {
        return usuarioAlta;
    }

    public void setUsuarioAlta(String usuarioAlta) {
        this.usuarioAlta = usuarioAlta;
    }

    public String getUsuarioBaja() {
        return usuarioBaja;
    }

    public void setUsuarioBaja(String usuarioBaja) {
        this.usuarioBaja = usuarioBaja;
    }

    public String getUsuarioModificacion() {
        return usuarioModificacion;
    }

    public void setUsuarioModificacion(String usuarioModificacion) {
        this.usuarioModificacion = usuarioModificacion;
    }

    public Date getFechaAlta() {
        return fechaAlta;
    }

    public void setFechaAlta(Date fechaAlta) {
        this.fechaAlta = fechaAlta;
    }

    public Date getFechaBaja() {
        return fechaBaja;
    }

    public void setFechaBaja(Date fechaBaja) {
        this.fechaBaja = fechaBaja;
    }

    public Date getFechaModificacion() {
        return fechaModificacion;
    }

    public void setFechaModificacion(Date fechaModificacion) {
        this.fechaModificacion = fechaModificacion;
    }

    public BigDecimal getIva() {
        return iva;
    }

    public void setIva(BigDecimal iva) {
        this.iva = iva;
    }
    
    @XmlTransient
    public List<InsumosRcbSolicitudInvestigacionMercado> getInsumosRcbSolicitudInvestigacionMercadoList() {
        return insumosRcbSolicitudInvestigacionMercadoList;
    }

    public void setInsumosRcbSolicitudInvestigacionMercadoList(List<InsumosRcbSolicitudInvestigacionMercado> insumosRcbSolicitudInvestigacionMercadoList) {
        this.insumosRcbSolicitudInvestigacionMercadoList = insumosRcbSolicitudInvestigacionMercadoList;
    }

    public Rcb getIdRcb() {
        return idRcb;
    }

    public void setIdRcb(Rcb idRcb) {
        this.idRcb = idRcb;
    }

    public Insumos getIdInsumo() {
        return idInsumo;
    }

    public void setIdInsumo(Insumos idInsumo) {
        this.idInsumo = idInsumo;
    }

    public String getClaveInsumo() {
        return claveInsumo;
    }

    public void setClaveInsumo(String claveInsumo) {
        this.claveInsumo = claveInsumo;
    }

    public String getDescripcionInsumo() {
        return descripcionInsumo;
    }

    public void setDescripcionInsumo(String descripcionInsumo) {
        this.descripcionInsumo = descripcionInsumo;
    }

    public String getIndicacionesInsumo() {
        return indicacionesInsumo;
    }

    public void setIndicacionesInsumo(String indicacionesInsumo) {
        this.indicacionesInsumo = indicacionesInsumo;
    }

    public String getPartidaPresupuestalInsumo() {
        return partidaPresupuestalInsumo;
    }

    public void setPartidaPresupuestalInsumo(String partidaPresupuestalInsumo) {
        this.partidaPresupuestalInsumo = partidaPresupuestalInsumo;
    }

    public String getViaAdministracionDosisInsumo() {
        return viaAdministracionDosisInsumo;
    }

    public void setViaAdministracionDosisInsumo(String viaAdministracionDosisInsumo) {
        this.viaAdministracionDosisInsumo = viaAdministracionDosisInsumo;
    }

    public UnidadPieza getIdUnidadPieza() {
        return idUnidadPieza;
    }

    public void setIdUnidadPieza(UnidadPieza idUnidadPieza) {
        this.idUnidadPieza = idUnidadPieza;
    }

    public Grupo getIdGrupo() {
        return idGrupo;
    }

    public void setIdGrupo(Grupo idGrupo) {
        this.idGrupo = idGrupo;
    }

    public GrupoTerapeutico getIdGrupoTerapeutico() {
        return idGrupoTerapeutico;
    }

    public void setIdGrupoTerapeutico(GrupoTerapeutico idGrupoTerapeutico) {
        this.idGrupoTerapeutico = idGrupoTerapeutico;
    }

    public Nivel getIdNivel() {
        return idNivel;
    }

    public void setIdNivel(Nivel idNivel) {
        this.idNivel = idNivel;
    }

    public Clasificacion getIdClasificacion() {
        return idClasificacion;
    }

    public void setIdClasificacion(Clasificacion idClasificacion) {
        this.idClasificacion = idClasificacion;
    }

    public int getCantidadPiezasModificada() {
        return cantidadPiezasModificada;
    }

    public void setCantidadPiezasModificada(int cantidadPiezasModificada) {
        this.cantidadPiezasModificada = cantidadPiezasModificada;
    }

    public BigDecimal getPrecioUnitarioModificado() {
        return precioUnitarioModificado;
    }

    public void setPrecioUnitarioModificado(BigDecimal precioUnitarioModificado) {
        this.precioUnitarioModificado = precioUnitarioModificado;
    }

    public BigDecimal getImporteModificado() {
        return importeModificado;
    }

    public void setImporteModificado(BigDecimal importeModificado) {
        this.importeModificado = importeModificado;
    }

    @XmlTransient
    public List<ProcedimientoRcb> getProcedimientoRcbList() {
        return procedimientoRcbList;
    }

    public void setProcedimientoRcbList(List<ProcedimientoRcb> procedimientoRcbList) {
        this.procedimientoRcbList = procedimientoRcbList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idRcbInsumos != null ? idRcbInsumos.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof RcbInsumos)) {
            return false;
        }
        RcbInsumos other = (RcbInsumos) object;
        if ((this.idRcbInsumos == null && other.idRcbInsumos != null) || (this.idRcbInsumos != null && !this.idRcbInsumos.equals(other.idRcbInsumos))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.issste.sicabis.ejb.modelo.RcbInsumos[ idRcbInsumos=" + idRcbInsumos + " ]";
    }

}
