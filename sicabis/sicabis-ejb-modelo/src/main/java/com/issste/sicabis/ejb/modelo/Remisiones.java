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
import javax.validation.constraints.Max;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author kriosoft
 */
@Entity
@Table(name = "remisiones")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Remisiones.findAll", query = "SELECT r FROM Remisiones r"),
    @NamedQuery(name = "Remisiones.findByIdRemision", query = "SELECT r FROM Remisiones r WHERE r.idRemision = :idRemision"),
    @NamedQuery(name = "Remisiones.findByActivo", query = "SELECT r FROM Remisiones r WHERE r.activo = :activo"),
    @NamedQuery(name = "Remisiones.findByRegistroControl", query = "SELECT r FROM Remisiones r WHERE r.registroControl = :registroControl"),
    @NamedQuery(name = "Remisiones.findByCantidadRecibidaControlCalidad", query = "SELECT r FROM Remisiones r WHERE r.cantidadRecibidaControlCalidad = :cantidadRecibidaControlCalidad"),
    @NamedQuery(name = "Remisiones.findByNivelCalidadAceptable", query = "SELECT r FROM Remisiones r WHERE r.nivelCalidadAceptable = :nivelCalidadAceptable"),
    @NamedQuery(name = "Remisiones.findByInspeccion", query = "SELECT r FROM Remisiones r WHERE r.inspeccion = :inspeccion"),
    @NamedQuery(name = "Remisiones.findByDescripcionDefecto", query = "SELECT r FROM Remisiones r WHERE r.descripcionDefecto = :descripcionDefecto"),
    @NamedQuery(name = "Remisiones.findByCantidadRecibida", query = "SELECT r FROM Remisiones r WHERE r.cantidadRecibida = :cantidadRecibida"),
    @NamedQuery(name = "Remisiones.findByFechaRemision", query = "SELECT r FROM Remisiones r WHERE r.fechaRemision = :fechaRemision"),
    @NamedQuery(name = "Remisiones.findByTipoEntrada", query = "SELECT r FROM Remisiones r WHERE r.tipoEntrada = :tipoEntrada"),
    @NamedQuery(name = "Remisiones.findByUsuarioAlta", query = "SELECT r FROM Remisiones r WHERE r.usuarioAlta = :usuarioAlta"),
    @NamedQuery(name = "Remisiones.findByUsuarioBaja", query = "SELECT r FROM Remisiones r WHERE r.usuarioBaja = :usuarioBaja"),
    @NamedQuery(name = "Remisiones.findByUsuarioModificacion", query = "SELECT r FROM Remisiones r WHERE r.usuarioModificacion = :usuarioModificacion"),
    @NamedQuery(name = "Remisiones.findByFechaAlta", query = "SELECT r FROM Remisiones r WHERE r.fechaAlta = :fechaAlta"),
    @NamedQuery(name = "Remisiones.findByFechaBaja", query = "SELECT r FROM Remisiones r WHERE r.fechaBaja = :fechaBaja"),
    @NamedQuery(name = "Remisiones.findByFechaModificaciones", query = "SELECT r FROM Remisiones r WHERE r.fechaModificaciones = :fechaModificaciones"),
    @NamedQuery(name = "Remisiones.findByFolioRemision", query = "SELECT r FROM Remisiones r WHERE r.folioRemision = :folioRemision")})
public class Remisiones implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id_remision")
    private Integer idRemision;
    @Column(name = "activo")
    private Integer activo;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 45)
    @Column(name = "registro_control")
    private String registroControl;
    @Column(name = "cantidad_recibida_control_calidad")
    private Integer cantidadRecibidaControlCalidad;
//    @Max(value =  ?)
//    @Min(value =  ?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Column(name = "nivel_calidad_aceptable")
    private BigDecimal nivelCalidadAceptable;
    @Size(max = 45)
    @Column(name = "inspeccion")
    private String inspeccion;
    @Size(max = 200)
    @Column(name = "descripcion_defecto")
    private String descripcionDefecto;
    @Column(name = "cantidad_recibida")
    private Integer cantidadRecibida;
    @Column(name = "fecha_remision")
    @Temporal(TemporalType.DATE)
    private Date fechaRemision;
    @Size(max = 50)
    @Column(name = "tipo_entrada")
    private String tipoEntrada;
    //@Size(max = 45)
    @Column(name = "unidad_pieza_convenio")
    private String unidadPiezaConvenio;
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
    @Column(name = "fecha_modificaciones")
    @Temporal(TemporalType.DATE)
    private Date fechaModificaciones;
    @Size(max = 100)
    @Column(name = "folio_remision")
    private String folioRemision;
    @Size(max = 45)
    @Column(name = "denominacion")
    private String denominacion;
    @JoinColumn(name = "id_fabricante", referencedColumnName = "id_fabricante")
    @ManyToOne
    private Fabricante idFabricante;
    @JoinColumn(name = "id_estatus", referencedColumnName = "id_estatus")
    @ManyToOne(optional = false)
    private Estatus idEstatus;
    @JoinColumn(name = "id_detalle_orden_suministro", referencedColumnName = "id_detalle_orden_suministro")
    @ManyToOne(optional = false)
    private DetalleOrdenSuministro idDetalleOrdenSuministro;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idRemision")
    private List<Lote> loteList;
    @JoinColumn(name = "id_presentacion", referencedColumnName = "id_presentacion")
    @ManyToOne
    private Presentacion idPresentacion;
    @JoinColumn(name = "id_canje_permuta", referencedColumnName = "id_canje_permuta")
    @ManyToOne(optional = false)
    private CanjePermuta idCanjePermuta;

    public Remisiones() {
    }

    public Remisiones(Integer idRemision) {
        this.idRemision = idRemision;
    }

    public Remisiones(Integer idRemision, String registroControl) {
        this.idRemision = idRemision;
        this.registroControl = registroControl;
    }

    public Integer getIdRemision() {
        return idRemision;
    }

    public void setIdRemision(Integer idRemision) {
        this.idRemision = idRemision;
    }

    public Integer getActivo() {
        return activo;
    }

    public void setActivo(Integer activo) {
        this.activo = activo;
    }

    public String getRegistroControl() {
        return registroControl;
    }

    public void setRegistroControl(String registroControl) {
        this.registroControl = registroControl;
    }

    public Integer getCantidadRecibidaControlCalidad() {
        return cantidadRecibidaControlCalidad;
    }

    public void setCantidadRecibidaControlCalidad(Integer cantidadRecibidaControlCalidad) {
        this.cantidadRecibidaControlCalidad = cantidadRecibidaControlCalidad;
    }

    public BigDecimal getNivelCalidadAceptable() {
        return nivelCalidadAceptable;
    }

    public void setNivelCalidadAceptable(BigDecimal nivelCalidadAceptable) {
        this.nivelCalidadAceptable = nivelCalidadAceptable;
    }

    public String getInspeccion() {
        return inspeccion;
    }

    public void setInspeccion(String inspeccion) {
        this.inspeccion = inspeccion;
    }

    public String getDescripcionDefecto() {
        return descripcionDefecto;
    }

    public void setDescripcionDefecto(String descripcionDefecto) {
        this.descripcionDefecto = descripcionDefecto;
    }

    public Integer getCantidadRecibida() {
        return cantidadRecibida;
    }

    public void setCantidadRecibida(Integer cantidadRecibida) {
        this.cantidadRecibida = cantidadRecibida;
    }

    public Date getFechaRemision() {
        return fechaRemision;
    }

    public void setFechaRemision(Date fechaRemision) {
        this.fechaRemision = fechaRemision;
    }

    public String getTipoEntrada() {
        return tipoEntrada;
    }

    public void setTipoEntrada(String tipoEntrada) {
        this.tipoEntrada = tipoEntrada;
    }

    public String getUnidadPiezaConvenio() {
        return unidadPiezaConvenio;
    }

    public void setUnidadPiezaConvenio(String unidadPiezaConvenio) {
        this.unidadPiezaConvenio = unidadPiezaConvenio;
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

    public Date getFechaModificaciones() {
        return fechaModificaciones;
    }

    public void setFechaModificaciones(Date fechaModificaciones) {
        this.fechaModificaciones = fechaModificaciones;
    }

    public String getFolioRemision() {
        return folioRemision;
    }

    public void setFolioRemision(String folioRemision) {
        this.folioRemision = folioRemision;
    }

    public String getDenominacion() {
        return denominacion;
    }

    public void setDenominacion(String denominacion) {
        this.denominacion = denominacion;
    }

    public Fabricante getIdFabricante() {
        return idFabricante;
    }

    public void setIdFabricante(Fabricante idFabricante) {
        this.idFabricante = idFabricante;
    }

    public Estatus getIdEstatus() {
        return idEstatus;
    }

    public void setIdEstatus(Estatus idEstatus) {
        this.idEstatus = idEstatus;
    }

    public DetalleOrdenSuministro getIdDetalleOrdenSuministro() {
        return idDetalleOrdenSuministro;
    }

    public void setIdDetalleOrdenSuministro(DetalleOrdenSuministro idDetalleOrdenSuministro) {
        this.idDetalleOrdenSuministro = idDetalleOrdenSuministro;
    }

    @XmlTransient
    public List<Lote> getLoteList() {
        return loteList;
    }

    public void setLoteList(List<Lote> loteList) {
        this.loteList = loteList;
    }

    public Presentacion getIdPresentacion() {
        return idPresentacion;
    }

    public void setIdPresentacion(Presentacion idPresentacion) {
        this.idPresentacion = idPresentacion;
    }

    public CanjePermuta getIdCanjePermuta() {
        return idCanjePermuta;
    }

    public void setIdCanjePermuta(CanjePermuta idCanjePermuta) {
        this.idCanjePermuta = idCanjePermuta;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idRemision != null ? idRemision.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Remisiones)) {
            return false;
        }
        Remisiones other = (Remisiones) object;
        if ((this.idRemision == null && other.idRemision != null) || (this.idRemision != null && !this.idRemision.equals(other.idRemision))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.issste.sicabis.ejb.modelo.Remisiones[ idRemision=" + idRemision + " ]";
    }

}
