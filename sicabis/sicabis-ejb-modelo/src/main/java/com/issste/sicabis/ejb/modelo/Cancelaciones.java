/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.issste.sicabis.ejb.modelo;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author kriosoft
 */
@Entity
@Table(name = "cancelaciones")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Cancelaciones.findAll", query = "SELECT c FROM Cancelaciones c"),
    @NamedQuery(name = "Cancelaciones.findByIdCancelacion", query = "SELECT c FROM Cancelaciones c WHERE c.idCancelacion = :idCancelacion"),
    @NamedQuery(name = "Cancelaciones.findByActivo", query = "SELECT c FROM Cancelaciones c WHERE c.activo = :activo"),
    @NamedQuery(name = "Cancelaciones.findByProcentajeIncumplimiento", query = "SELECT c FROM Cancelaciones c WHERE c.procentajeIncumplimiento = :procentajeIncumplimiento"),
    @NamedQuery(name = "Cancelaciones.findByNumeroCancelacion", query = "SELECT c FROM Cancelaciones c WHERE c.numeroCancelacion = :numeroCancelacion"),
    @NamedQuery(name = "Cancelaciones.findByUsuarioAlta", query = "SELECT c FROM Cancelaciones c WHERE c.usuarioAlta = :usuarioAlta"),
    @NamedQuery(name = "Cancelaciones.findByUsuarioBaja", query = "SELECT c FROM Cancelaciones c WHERE c.usuarioBaja = :usuarioBaja"),
    @NamedQuery(name = "Cancelaciones.findByUsuarioModificacion", query = "SELECT c FROM Cancelaciones c WHERE c.usuarioModificacion = :usuarioModificacion"),
    @NamedQuery(name = "Cancelaciones.findByFechaAlta", query = "SELECT c FROM Cancelaciones c WHERE c.fechaAlta = :fechaAlta"),
    @NamedQuery(name = "Cancelaciones.findByFechaBaja", query = "SELECT c FROM Cancelaciones c WHERE c.fechaBaja = :fechaBaja"),
    @NamedQuery(name = "Cancelaciones.findByFechaModificacion", query = "SELECT c FROM Cancelaciones c WHERE c.fechaModificacion = :fechaModificacion")})
public class Cancelaciones implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id_cancelacion")
    private Integer idCancelacion;
    @Column(name = "activo")
    private Integer activo;
    @Basic(optional = false)
    @NotNull
    @Column(name = "procentaje_incumplimiento")
    private Integer procentajeIncumplimiento;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 45)
    @Column(name = "numero_cancelacion")
    private String numeroCancelacion;
    @Basic(optional = false)
    @Column(name = "pena")
    private BigDecimal pena;
    @Basic(optional = false)
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
    @JoinColumn(name = "id_estatus", referencedColumnName = "id_estatus")
    @ManyToOne(optional = false)
    private Estatus idEstatus;
    @JoinColumn(name = "id_detalle_orden_suministro", referencedColumnName = "id_detalle_orden_suministro")
    @ManyToOne(optional = false)
    private DetalleOrdenSuministro idDetalleOrdenSuministro;

    public Cancelaciones() {
    }

    public Cancelaciones(Integer idCancelacion) {
        this.idCancelacion = idCancelacion;
    }

    public Cancelaciones(Integer idCancelacion, int procentajeIncumplimiento, String numeroCancelacion) {
        this.idCancelacion = idCancelacion;
        this.procentajeIncumplimiento = procentajeIncumplimiento;
        this.numeroCancelacion = numeroCancelacion;
    }

    public Integer getIdCancelacion() {
        return idCancelacion;
    }

    public void setIdCancelacion(Integer idCancelacion) {
        this.idCancelacion = idCancelacion;
    }

    public Integer getActivo() {
        return activo;
    }

    public void setActivo(Integer activo) {
        this.activo = activo;
    }

    public Integer getProcentajeIncumplimiento() {
        return procentajeIncumplimiento;
    }

    public void setProcentajeIncumplimiento(Integer procentajeIncumplimiento) {
        this.procentajeIncumplimiento = procentajeIncumplimiento;
    }

    public String getNumeroCancelacion() {
        return numeroCancelacion;
    }

    public void setNumeroCancelacion(String numeroCancelacion) {
        this.numeroCancelacion = numeroCancelacion;
    }

    public BigDecimal getPena() {
        return pena;
    }

    public void setPena(BigDecimal pena) {
        this.pena = pena;
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

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idCancelacion != null ? idCancelacion.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Cancelaciones)) {
            return false;
        }
        Cancelaciones other = (Cancelaciones) object;
        if ((this.idCancelacion == null && other.idCancelacion != null) || (this.idCancelacion != null && !this.idCancelacion.equals(other.idCancelacion))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.issste.sicabis.ejb.modelo.Cancelaciones[ idCancelacion=" + idCancelacion + " ]";
    }

}
