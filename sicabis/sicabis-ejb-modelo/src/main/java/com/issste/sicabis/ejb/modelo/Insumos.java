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
@Table(name = "insumos")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Insumos.findAll", query = "SELECT i FROM Insumos i"),
    @NamedQuery(name = "Insumos.findByIdInsumo", query = "SELECT i FROM Insumos i WHERE i.idInsumo = :idInsumo"),
    @NamedQuery(name = "Insumos.findByActivo", query = "SELECT i FROM Insumos i WHERE i.activo = :activo"),
    @NamedQuery(name = "Insumos.findByClave", query = "SELECT i FROM Insumos i WHERE i.clave = :clave"),
    @NamedQuery(name = "Insumos.findByDescripcion", query = "SELECT i FROM Insumos i WHERE i.descripcion = :descripcion"),
    @NamedQuery(name = "Insumos.findByIndicaciones", query = "SELECT i FROM Insumos i WHERE i.indicaciones = :indicaciones"),
    @NamedQuery(name = "Insumos.findByPartidaPresupuestal", query = "SELECT i FROM Insumos i WHERE i.partidaPresupuestal = :partidaPresupuestal"),
    @NamedQuery(name = "Insumos.findByViaAdministracionDosis", query = "SELECT i FROM Insumos i WHERE i.viaAdministracionDosis = :viaAdministracionDosis"),
    @NamedQuery(name = "Insumos.findByUsuarioAlta", query = "SELECT i FROM Insumos i WHERE i.usuarioAlta = :usuarioAlta"),
    @NamedQuery(name = "Insumos.findByUsuarioBaja", query = "SELECT i FROM Insumos i WHERE i.usuarioBaja = :usuarioBaja"),
    @NamedQuery(name = "Insumos.findByUsuarioModificacion", query = "SELECT i FROM Insumos i WHERE i.usuarioModificacion = :usuarioModificacion"),
    @NamedQuery(name = "Insumos.findByFechaAlta", query = "SELECT i FROM Insumos i WHERE i.fechaAlta = :fechaAlta"),
    @NamedQuery(name = "Insumos.findByFechaBaja", query = "SELECT i FROM Insumos i WHERE i.fechaBaja = :fechaBaja"),
    @NamedQuery(name = "Insumos.findByFechaModificacion", query = "SELECT i FROM Insumos i WHERE i.fechaModificacion = :fechaModificacion")})
public class Insumos implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id_insumo")
    private Integer idInsumo;
    @Column(name = "activo")
    private Integer activo;
    @Basic(optional = false)
    @NotNull
    @Column(name = "clave")
    private String clave;
    @Basic(optional = false)
    @NotNull
//    @Size(min = 1, max = 5000)
    @Column(name = "descripcion")
    private String descripcion;
    @Basic(optional = false)
    @NotNull
//  @Size(min = 1, max = 5000)
    @Column(name = "indicaciones")
    private String indicaciones;
    @Basic(optional = false)
    @NotNull
//  @Size(min = 1, max = 45)
    @Column(name = "partida_presupuestal")
    private String partidaPresupuestal;
    @Basic(optional = false)
    @NotNull
//   @Size(min = 1, max = 5000)
    @Column(name = "via_administracion_dosis")
    private String viaAdministracionDosis;
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
    @Column(name = "precio_unitario")
    private BigDecimal precioUnitario;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idInsumo")
    private List<DetalleRecoleccion> detalleRecoleccionList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idInsumo")
    private List<CanjePermuta> canjePermutaList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idInsumoCanje")
    private List<CanjePermuta> canjePermutaList1;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idInsumo")
    private List<RcbInsumos> rcbInsumosList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idInsumo")
    private List<DescripcionInsumosOpcional> descripcionInsumosOpcionalList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idInsumo")
    private List<CrInsumos> crInsumosList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idInsumo")
    private List<SolicitudesInsumosPacientes> solicitudesInsumosPacientesList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idInsumo")
    private List<AsignacionInsumos> asignacionInsumosList;
    @JoinColumn(name = "id_unidad_pieza", referencedColumnName = "id_unidad_pieza")
    @ManyToOne(optional = false)
    private UnidadPieza idUnidadPieza;
    @JoinColumn(name = "id_nivel", referencedColumnName = "id_nivel")
    @ManyToOne(optional = false)
    private Nivel idNivel;
    @JoinColumn(name = "id_grupo_terapeutico", referencedColumnName = "id_grupo_terapeutico")
    @ManyToOne(optional = false)
    private GrupoTerapeutico idGrupoTerapeutico;
    @JoinColumn(name = "id_clasificacion", referencedColumnName = "id_clasificacion")
    @ManyToOne(optional = false)
    private Clasificacion idClasificacion;
    @JoinColumn(name = "id_grupo", referencedColumnName = "id_grupo")
    @ManyToOne(optional = false)
    private Grupo idGrupo;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idInsumo")
    private List<PlaneacionDetalle> planeacionDetalleList;
    @JoinColumn(name = "id_tipo_insumos", referencedColumnName = "id_tipo_insumos")
    @ManyToOne(optional = false)
    private TipoInsumos idTipoInsumos;
    @JoinColumn(name = "id_clasificacion_importancia", referencedColumnName = "id_clasificacion_importancia")
    @ManyToOne(optional = false)
    private ClasificacionImportancia idClasificacionImportancia;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idInsumo")
    private List<InsumoDpn> insumoDpnList;

    public Insumos() {
    }

    public Insumos(Integer idInsumo) {
        this.idInsumo = idInsumo;
    }

    public Insumos(Integer idInsumo, String clave, String descripcion, String indicaciones, String grupo, String partidaPresupuestal, String viaAdministracionDosis) {
        this.idInsumo = idInsumo;
        this.clave = clave;
        this.descripcion = descripcion;
        this.indicaciones = indicaciones;
        this.partidaPresupuestal = partidaPresupuestal;
        this.viaAdministracionDosis = viaAdministracionDosis;
    }

    public Integer getIdInsumo() {
        return idInsumo;
    }

    public void setIdInsumo(Integer idInsumo) {
        this.idInsumo = idInsumo;
    }

    public Integer getActivo() {
        return activo;
    }

    public void setActivo(Integer activo) {
        this.activo = activo;
    }

    public String getClave() {
        return clave;
    }

    public void setClave(String clave) {
        this.clave = clave;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getIndicaciones() {
        return indicaciones;
    }

    public void setIndicaciones(String indicaciones) {
        this.indicaciones = indicaciones;
    }

    public String getPartidaPresupuestal() {
        return partidaPresupuestal;
    }

    public void setPartidaPresupuestal(String partidaPresupuestal) {
        this.partidaPresupuestal = partidaPresupuestal;
    }

    public String getViaAdministracionDosis() {
        return viaAdministracionDosis;
    }

    public void setViaAdministracionDosis(String viaAdministracionDosis) {
        this.viaAdministracionDosis = viaAdministracionDosis;
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

    public BigDecimal getPrecioUnitario() {
        return precioUnitario;
    }

    public void setPrecioUnitario(BigDecimal precioUnitario) {
        this.precioUnitario = precioUnitario;
    }

    @XmlTransient
    public List<DetalleRecoleccion> getDetalleRecoleccionList() {
        return detalleRecoleccionList;
    }

    public void setDetalleRecoleccionList(List<DetalleRecoleccion> detalleRecoleccionList) {
        this.detalleRecoleccionList = detalleRecoleccionList;
    }

    @XmlTransient
    public List<CanjePermuta> getCanjePermutaList() {
        return canjePermutaList;
    }

    public void setCanjePermutaList(List<CanjePermuta> canjePermutaList) {
        this.canjePermutaList = canjePermutaList;
    }

    @XmlTransient
    public List<CanjePermuta> getCanjePermutaList1() {
        return canjePermutaList1;
    }

    public void setCanjePermutaList1(List<CanjePermuta> canjePermutaList1) {
        this.canjePermutaList1 = canjePermutaList1;
    }

    @XmlTransient
    public List<RcbInsumos> getRcbInsumosList() {
        return rcbInsumosList;
    }

    public void setRcbInsumosList(List<RcbInsumos> rcbInsumosList) {
        this.rcbInsumosList = rcbInsumosList;
    }

    @XmlTransient
    public List<DescripcionInsumosOpcional> getDescripcionInsumosOpcionalList() {
        return descripcionInsumosOpcionalList;
    }

    public void setDescripcionInsumosOpcionalList(List<DescripcionInsumosOpcional> descripcionInsumosOpcionalList) {
        this.descripcionInsumosOpcionalList = descripcionInsumosOpcionalList;
    }

    @XmlTransient
    public List<CrInsumos> getCrInsumosList() {
        return crInsumosList;
    }

    public void setCrInsumosList(List<CrInsumos> crInsumosList) {
        this.crInsumosList = crInsumosList;
    }

    @XmlTransient
    public List<SolicitudesInsumosPacientes> getSolicitudesInsumosPacientesList() {
        return solicitudesInsumosPacientesList;
    }

    public void setSolicitudesInsumosPacientesList(List<SolicitudesInsumosPacientes> solicitudesInsumosPacientesList) {
        this.solicitudesInsumosPacientesList = solicitudesInsumosPacientesList;
    }

    public UnidadPieza getIdUnidadPieza() {
        return idUnidadPieza;
    }

    public void setIdUnidadPieza(UnidadPieza idUnidadPieza) {
        this.idUnidadPieza = idUnidadPieza;
    }

    public Nivel getIdNivel() {
        return idNivel;
    }

    public void setIdNivel(Nivel idNivel) {
        this.idNivel = idNivel;
    }

    public GrupoTerapeutico getIdGrupoTerapeutico() {
        return idGrupoTerapeutico;
    }

    public void setIdGrupoTerapeutico(GrupoTerapeutico idGrupoTerapeutico) {
        this.idGrupoTerapeutico = idGrupoTerapeutico;
    }

    public Clasificacion getIdClasificacion() {
        return idClasificacion;
    }

    public void setIdClasificacion(Clasificacion idClasificacion) {
        this.idClasificacion = idClasificacion;
    }

    public Grupo getIdGrupo() {
        return idGrupo;
    }

    public void setIdGrupo(Grupo idGrupo) {
        this.idGrupo = idGrupo;
    }

    public List<AsignacionInsumos> getAsignacionInsumosList() {
        return asignacionInsumosList;
    }

    public void setAsignacionInsumosList(List<AsignacionInsumos> asignacionInsumosList) {
        this.asignacionInsumosList = asignacionInsumosList;
    }

    public TipoInsumos getIdTipoInsumos() {
        return idTipoInsumos;
    }

    public void setIdTipoInsumos(TipoInsumos idTipoInsumos) {
        this.idTipoInsumos = idTipoInsumos;
    }

    public ClasificacionImportancia getIdClasificacionImportancia() {
        return idClasificacionImportancia;
    }

    public void setIdClasificacionImportancia(ClasificacionImportancia idClasificacionImportancia) {
        this.idClasificacionImportancia = idClasificacionImportancia;
    }

    @XmlTransient
    public List<PlaneacionDetalle> getPlaneacionDetalleList() {
        return planeacionDetalleList;
    }

    public void setPlaneacionDetalleList(List<PlaneacionDetalle> planeacionDetalleList) {
        this.planeacionDetalleList = planeacionDetalleList;
    }

    @XmlTransient
    public List<InsumoDpn> getInsumoDpnList() {
        return insumoDpnList;
    }

    public void setInsumoDpnList(List<InsumoDpn> insumoDpnList) {
        this.insumoDpnList = insumoDpnList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idInsumo != null ? idInsumo.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Insumos)) {
            return false;
        }
        Insumos other = (Insumos) object;
        if ((this.idInsumo == null && other.idInsumo != null) || (this.idInsumo != null && !this.idInsumo.equals(other.idInsumo))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.issste.sicabis.ejb.modelo.Insumos[ idInsumo=" + idInsumo + " ]";
    }

}
