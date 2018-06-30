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
@Table(name = "rcb")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Rcb.findAll", query = "SELECT r FROM Rcb r"),
    @NamedQuery(name = "Rcb.findByIdRcb", query = "SELECT r FROM Rcb r WHERE r.idRcb = :idRcb"),
    @NamedQuery(name = "Rcb.findByActivo", query = "SELECT r FROM Rcb r WHERE r.activo = :activo"),
    @NamedQuery(name = "Rcb.findByNumero", query = "SELECT r FROM Rcb r WHERE r.numero = :numero"),
    @NamedQuery(name = "Rcb.findByClave", query = "SELECT r FROM Rcb r WHERE r.clave = :clave"),
    @NamedQuery(name = "Rcb.findByFechaElaboracion", query = "SELECT r FROM Rcb r WHERE r.fechaElaboracion = :fechaElaboracion"),
    @NamedQuery(name = "Rcb.findByPeriodo", query = "SELECT r FROM Rcb r WHERE r.periodo = :periodo"),
    @NamedQuery(name = "Rcb.findByOficioSuficienciaPresupuestal", query = "SELECT r FROM Rcb r WHERE r.oficioSuficienciaPresupuestal = :oficioSuficienciaPresupuestal"),
    @NamedQuery(name = "Rcb.findByNep", query = "SELECT r FROM Rcb r WHERE r.nep = :nep"),
    @NamedQuery(name = "Rcb.findByDestino", query = "SELECT r FROM Rcb r WHERE r.destino = :destino"),
    @NamedQuery(name = "Rcb.findByImporteTotal", query = "SELECT r FROM Rcb r WHERE r.importeTotal = :importeTotal"),
    @NamedQuery(name = "Rcb.findByUsuarioAlta", query = "SELECT r FROM Rcb r WHERE r.usuarioAlta = :usuarioAlta"),
    @NamedQuery(name = "Rcb.findByUsuarioBaja", query = "SELECT r FROM Rcb r WHERE r.usuarioBaja = :usuarioBaja"),
    @NamedQuery(name = "Rcb.findByUsuarioModificacion", query = "SELECT r FROM Rcb r WHERE r.usuarioModificacion = :usuarioModificacion"),
    @NamedQuery(name = "Rcb.findByFechaAlta", query = "SELECT r FROM Rcb r WHERE r.fechaAlta = :fechaAlta"),
    @NamedQuery(name = "Rcb.findByFechaBaja", query = "SELECT r FROM Rcb r WHERE r.fechaBaja = :fechaBaja"),
    @NamedQuery(name = "Rcb.findByFechaModificacion", query = "SELECT r FROM Rcb r WHERE r.fechaModificacion = :fechaModificacion")})
public class Rcb implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id_rcb")
    private Integer idRcb;
    @Basic(optional = false)
    @NotNull
    @Column(name = "activo")
    private int activo;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 10)
    @Column(name = "numero")
    private String numero;
    @Basic(optional = false)
    @Size(max = 45)
    @Column(name = "clave")
    private String clave;
    @Basic(optional = false)
    @Size(max = 45)
    @Column(name = "numero_oficio")
    private String numeroOficio;
    @Basic(optional = false)
    @NotNull
    @Column(name = "fecha_elaboracion")
    @Temporal(TemporalType.DATE)
    private Date fechaElaboracion;
    @Basic(optional = false)
    @NotNull
    @Column(name = "periodo")
    private int periodo;
    @Basic(optional = false)
    @Column(name = "oficio_suficiencia_presupuestal")
    private String oficioSuficienciaPresupuestal;
    @Basic(optional = false)
    @Column(name = "nep")
    private String nep;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 500)
    @Column(name = "destino")
    private String destino;
    @Column(name = "dias_oficio")
    private Integer diasOficio;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Basic(optional = false)
    @NotNull
    @Column(name = "importe_total")
    private BigDecimal importeTotal;
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
    @Column(name = "fecha_oficio_suficiencia")
    @Temporal(TemporalType.DATE)
    private Date fechaOficioSuficiencia;
    @JoinColumn(name = "id_unidad_responsable", referencedColumnName = "id_unidad_responsable")
    @ManyToOne 
    private UnidadResponsable idUnidadResponsable;
    @JoinColumn(name = "id_tipo_compra", referencedColumnName = "id_tipo_compra")
    @ManyToOne(optional = false)
    private TipoCompra idTipoCompra;
    @JoinColumn(name = "id_estatus", referencedColumnName = "id_estatus")
    @ManyToOne(optional = false)
    private Estatus idEstatus;
    @JoinColumn(name = "id_area", referencedColumnName = "id_area")
    @ManyToOne(optional = false)
    private Area idArea;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idRcb")
    private List<RcbInsumos> rcbInsumosList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idRcb")
    private List<RcbGrupo> rcbGrupoList;
    @JoinColumn(name = "id_jefatura", referencedColumnName = "id_jefatura")
    @ManyToOne(optional = false)
    private Jefatura idJefatura;

    public Rcb() {
    }

    public Rcb(Integer idRcb) {
        this.idRcb = idRcb;
    }

    public Rcb(Integer idRcb, int activo, String numero, String grupo, String clave, Date fechaElaboracion, int periodo, String oficioSuficienciaPresupuestal, String nep, String destino, BigDecimal importeTotal) {
        this.idRcb = idRcb;
        this.activo = activo;
        this.numero = numero;
        this.clave = clave;
        this.fechaElaboracion = fechaElaboracion;
        this.periodo = periodo;
        this.oficioSuficienciaPresupuestal = oficioSuficienciaPresupuestal;
        this.nep = nep;
        this.destino = destino;
        this.importeTotal = importeTotal;
    }

    public Integer getIdRcb() {
        return idRcb;
    }

    public void setIdRcb(Integer idRcb) {
        this.idRcb = idRcb;
    }

    public int getActivo() {
        return activo;
    }

    public void setActivo(int activo) {
        this.activo = activo;
    }

    public String getNumero() {
        return numero;
    }

    public void setNumero(String numero) {
        this.numero = numero;
    }

    public String getClave() {
        return clave;
    }

    public void setClave(String clave) {
        this.clave = clave;
    }

    public String getNumeroOficio() {
        return numeroOficio;
    }

    public void setNumeroOficio(String numeroOficio) {
        this.numeroOficio = numeroOficio;
    }

    public Date getFechaElaboracion() {
        return fechaElaboracion;
    }

    public void setFechaElaboracion(Date fechaElaboracion) {
        this.fechaElaboracion = fechaElaboracion;
    }

    public int getPeriodo() {
        return periodo;
    }

    public void setPeriodo(int periodo) {
        this.periodo = periodo;
    }

    public String getOficioSuficienciaPresupuestal() {
        return oficioSuficienciaPresupuestal;
    }

    public void setOficioSuficienciaPresupuestal(String oficioSuficienciaPresupuestal) {
        this.oficioSuficienciaPresupuestal = oficioSuficienciaPresupuestal;
    }

    public String getNep() {
        return nep;
    }

    public void setNep(String nep) {
        this.nep = nep;
    }

    public String getDestino() {
        return destino;
    }

    public void setDestino(String destino) {
        this.destino = destino;
    }

    public BigDecimal getImporteTotal() {
        return importeTotal;
    }

    public void setImporteTotal(BigDecimal importeTotal) {
        this.importeTotal = importeTotal;
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

    public UnidadResponsable getIdUnidadResponsable() {
        return idUnidadResponsable;
    }

    public void setIdUnidadResponsable(UnidadResponsable idUnidadResponsable) {
        this.idUnidadResponsable = idUnidadResponsable;
    }

    public TipoCompra getIdTipoCompra() {
        return idTipoCompra;
    }

    public void setIdTipoCompra(TipoCompra idTipoCompra) {
        this.idTipoCompra = idTipoCompra;
    }

    public Estatus getIdEstatus() {
        return idEstatus;
    }

    public void setIdEstatus(Estatus idEstatus) {
        this.idEstatus = idEstatus;
    }

    public Integer getDiasOficio() {
        return diasOficio;
    }

    public void setDiasOficio(Integer diasOficio) {
        this.diasOficio = diasOficio;
    }

    public Date getFechaOficioSuficiencia() {
        return fechaOficioSuficiencia;
    }

    public void setFechaOficioSuficiencia(Date fechaOficioSuficiencia) {
        this.fechaOficioSuficiencia = fechaOficioSuficiencia;
    }

    @XmlTransient
    public List<RcbInsumos> getRcbInsumosList() {
        return rcbInsumosList;
    }

    public void setRcbInsumosList(List<RcbInsumos> rcbInsumosList) {
        this.rcbInsumosList = rcbInsumosList;
    }

    public Area getIdArea() {
        return idArea;
    }

    public void setIdArea(Area idArea) {
        this.idArea = idArea;
    }

    public List<RcbGrupo> getRcbGrupoList() {
        return rcbGrupoList;
    }

    public void setRcbGrupoList(List<RcbGrupo> rcbGrupoList) {
        this.rcbGrupoList = rcbGrupoList;
    }

    public Jefatura getIdJefatura() {
        return idJefatura;
    }

    public void setIdJefatura(Jefatura idJefatura) {
        this.idJefatura = idJefatura;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idRcb != null ? idRcb.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Rcb)) {
            return false;
        }
        Rcb other = (Rcb) object;
        if ((this.idRcb == null && other.idRcb != null) || (this.idRcb != null && !this.idRcb.equals(other.idRcb))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.issste.sicabis.ejb.modelo.Rcb[ idRcb=" + idRcb + " ]";
    }

}
