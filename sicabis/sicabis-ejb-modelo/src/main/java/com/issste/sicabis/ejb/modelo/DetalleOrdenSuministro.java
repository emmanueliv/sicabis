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
@Table(name = "detalle_orden_suministro")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "DetalleOrdenSuministro.findAll", query = "SELECT d FROM DetalleOrdenSuministro d"),
    @NamedQuery(name = "DetalleOrdenSuministro.findByIdDetalleOrdenSuministro", query = "SELECT d FROM DetalleOrdenSuministro d WHERE d.idDetalleOrdenSuministro = :idDetalleOrdenSuministro"),
    @NamedQuery(name = "DetalleOrdenSuministro.findByActivo", query = "SELECT d FROM DetalleOrdenSuministro d WHERE d.activo = :activo"),
    @NamedQuery(name = "DetalleOrdenSuministro.findByFechaEntregaInicial", query = "SELECT d FROM DetalleOrdenSuministro d WHERE d.fechaEntregaInicial = :fechaEntregaInicial"),
    @NamedQuery(name = "DetalleOrdenSuministro.findByFechaEntregaFinal", query = "SELECT d FROM DetalleOrdenSuministro d WHERE d.fechaEntregaFinal = :fechaEntregaFinal"),
    @NamedQuery(name = "DetalleOrdenSuministro.findByCantidadSuministrar", query = "SELECT d FROM DetalleOrdenSuministro d WHERE d.cantidadSuministrar = :cantidadSuministrar"),
    @NamedQuery(name = "DetalleOrdenSuministro.findByUsuarioAlta", query = "SELECT d FROM DetalleOrdenSuministro d WHERE d.usuarioAlta = :usuarioAlta"),
    @NamedQuery(name = "DetalleOrdenSuministro.findByUsuarioBaja", query = "SELECT d FROM DetalleOrdenSuministro d WHERE d.usuarioBaja = :usuarioBaja"),
    @NamedQuery(name = "DetalleOrdenSuministro.findByUsuarioModificacion", query = "SELECT d FROM DetalleOrdenSuministro d WHERE d.usuarioModificacion = :usuarioModificacion"),
    @NamedQuery(name = "DetalleOrdenSuministro.findByFechaAlta", query = "SELECT d FROM DetalleOrdenSuministro d WHERE d.fechaAlta = :fechaAlta"),
    @NamedQuery(name = "DetalleOrdenSuministro.findByFechaBaja", query = "SELECT d FROM DetalleOrdenSuministro d WHERE d.fechaBaja = :fechaBaja"),
    @NamedQuery(name = "DetalleOrdenSuministro.findByFechaModificacion", query = "SELECT d FROM DetalleOrdenSuministro d WHERE d.fechaModificacion = :fechaModificacion")})
public class DetalleOrdenSuministro implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id_detalle_orden_suministro")
    private Integer idDetalleOrdenSuministro;
    @Column(name = "activo")
    private Integer activo;
    @Column(name = "cancelado")
    private Integer cancelado;
    @Column(name = "total_cancelado")
    private Integer totalCancelado;
    @Basic(optional = false)
    //@NotNull
    @Column(name = "fecha_entrega_inicial")
    @Temporal(TemporalType.DATE)
    private Date fechaEntregaInicial;
    @Basic(optional = false)
    //@NotNull
    @Column(name = "fecha_entrega_final")
    @Temporal(TemporalType.DATE)
    private Date fechaEntregaFinal;
    @Column(name = "cantidad_suministrar")
    private Integer cantidadSuministrar;
    @Column(name = "cantidad_suministrada")
    private Integer cantidadSuministrada;
    @Column(name = "importe")
    private BigDecimal importe;
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
    @Column(name = "id_repositorio_documentos")
    private Integer idRepositorioDocumentos;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idDetalleOrdenSuministro")
    private List<Remisiones> remisionesList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idDetalleOrdenSuministro")
    private List<Cancelaciones> cancelacionesList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idDetalleOrdenSuministro")
    private List<Rescisiones> rescisionesList;
    @JoinColumn(name = "id_orden_suministro", referencedColumnName = "id_orden_suministro")
    @ManyToOne(optional = false)
    private OrdenSuministro idOrdenSuministro;
    @JoinColumn(name = "id_fallo_procedimiento_rcb", referencedColumnName = "id_fallo_procedimiento_rcb")
    @ManyToOne(optional = false)
    private FalloProcedimientoRcb idFalloProcedimientoRcb;

    public DetalleOrdenSuministro() {
    }

    public DetalleOrdenSuministro(Integer idDetalleOrdenSuministro) {
        this.idDetalleOrdenSuministro = idDetalleOrdenSuministro;
    }

    public DetalleOrdenSuministro(Integer idDetalleOrdenSuministro, Date fechaEntregaInicial, Date fechaEntregaFinal) {
        this.idDetalleOrdenSuministro = idDetalleOrdenSuministro;
        this.fechaEntregaInicial = fechaEntregaInicial;
        this.fechaEntregaFinal = fechaEntregaFinal;
    }

    public Integer getIdDetalleOrdenSuministro() {
        return idDetalleOrdenSuministro;
    }

    public void setIdDetalleOrdenSuministro(Integer idDetalleOrdenSuministro) {
        this.idDetalleOrdenSuministro = idDetalleOrdenSuministro;
    }

    public Integer getActivo() {
        return activo;
    }

    public void setActivo(Integer activo) {
        this.activo = activo;
    }

    public Integer getTotalCancelado() {
        return totalCancelado;
    }

    public void setTotalCancelado(Integer totalCancelado) {
        this.totalCancelado = totalCancelado;
    }

    public Date getFechaEntregaInicial() {
        return fechaEntregaInicial;
    }

    public void setFechaEntregaInicial(Date fechaEntregaInicial) {
        this.fechaEntregaInicial = fechaEntregaInicial;
    }

    public Date getFechaEntregaFinal() {
        return fechaEntregaFinal;
    }

    public void setFechaEntregaFinal(Date fechaEntregaFinal) {
        this.fechaEntregaFinal = fechaEntregaFinal;
    }

    public Integer getCantidadSuministrar() {
        return cantidadSuministrar;
    }

    public void setCantidadSuministrar(Integer cantidadSuministrar) {
        this.cantidadSuministrar = cantidadSuministrar;
    }

    public Integer getCantidadSuministrada() {
        return cantidadSuministrada;
    }

    public void setCantidadSuministrada(Integer cantidadSuministrada) {
        this.cantidadSuministrada = cantidadSuministrada;
    }

    public BigDecimal getImporte() {
        return importe;
    }

    public void setImporte(BigDecimal importe) {
        this.importe = importe;
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

    @XmlTransient
    public List<Remisiones> getRemisionesList() {
        return remisionesList;
    }

    public void setRemisionesList(List<Remisiones> remisionesList) {
        this.remisionesList = remisionesList;
    }

    @XmlTransient
    public List<Cancelaciones> getCancelacionesList() {
        return cancelacionesList;
    }

    public void setCancelacionesList(List<Cancelaciones> cancelacionesList) {
        this.cancelacionesList = cancelacionesList;
    }

    @XmlTransient
    public List<Rescisiones> getRescisionesList() {
        return rescisionesList;
    }

    public void setRescisionesList(List<Rescisiones> rescisionesList) {
        this.rescisionesList = rescisionesList;
    }

    public OrdenSuministro getIdOrdenSuministro() {
        return idOrdenSuministro;
    }

    public void setIdOrdenSuministro(OrdenSuministro idOrdenSuministro) {
        this.idOrdenSuministro = idOrdenSuministro;
    }

    public FalloProcedimientoRcb getIdFalloProcedimientoRcb() {
        return idFalloProcedimientoRcb;
    }

    public void setIdFalloProcedimientoRcb(FalloProcedimientoRcb idFalloProcedimientoRcb) {
        this.idFalloProcedimientoRcb = idFalloProcedimientoRcb;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idDetalleOrdenSuministro != null ? idDetalleOrdenSuministro.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof DetalleOrdenSuministro)) {
            return false;
        }
        DetalleOrdenSuministro other = (DetalleOrdenSuministro) object;
        if ((this.idDetalleOrdenSuministro == null && other.idDetalleOrdenSuministro != null) || (this.idDetalleOrdenSuministro != null && !this.idDetalleOrdenSuministro.equals(other.idDetalleOrdenSuministro))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.issste.sicabis.ejb.modelo.DetalleOrdenSuministro[ idDetalleOrdenSuministro=" + idDetalleOrdenSuministro + " ]";
    }

    public Integer getIdRepositorioDocumentos() {
        return idRepositorioDocumentos;
    }

    public void setIdRepositorioDocumentos(Integer idRepositorioDocumentos) {
        this.idRepositorioDocumentos = idRepositorioDocumentos;
    }

    public Integer getCancelado() {
        return cancelado;
    }

    public void setCancelado(Integer cancelado) {
        this.cancelado = cancelado;
    }

}
