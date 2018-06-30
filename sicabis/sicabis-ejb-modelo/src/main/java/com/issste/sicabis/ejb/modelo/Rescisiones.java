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
@Table(name = "rescisiones")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Rescisiones.findAll", query = "SELECT r FROM Rescisiones r"),
    @NamedQuery(name = "Rescisiones.findByIdRescision", query = "SELECT r FROM Rescisiones r WHERE r.idRescision = :idRescision"),
    @NamedQuery(name = "Rescisiones.findByActivo", query = "SELECT r FROM Rescisiones r WHERE r.activo = :activo"),
    @NamedQuery(name = "Rescisiones.findByNumeroRecision", query = "SELECT r FROM Rescisiones r WHERE r.numeroRecision = :numeroRecision"),
    @NamedQuery(name = "Rescisiones.findByUsuarioAlta", query = "SELECT r FROM Rescisiones r WHERE r.usuarioAlta = :usuarioAlta"),
    @NamedQuery(name = "Rescisiones.findByUsuarioBaja", query = "SELECT r FROM Rescisiones r WHERE r.usuarioBaja = :usuarioBaja"),
    @NamedQuery(name = "Rescisiones.findByUsuarioModificacion", query = "SELECT r FROM Rescisiones r WHERE r.usuarioModificacion = :usuarioModificacion"),
    @NamedQuery(name = "Rescisiones.findByFechaAlta", query = "SELECT r FROM Rescisiones r WHERE r.fechaAlta = :fechaAlta"),
    @NamedQuery(name = "Rescisiones.findByFechaBaja", query = "SELECT r FROM Rescisiones r WHERE r.fechaBaja = :fechaBaja"),
    @NamedQuery(name = "Rescisiones.findByFechaModificacion", query = "SELECT r FROM Rescisiones r WHERE r.fechaModificacion = :fechaModificacion")})
public class Rescisiones implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id_rescision")
    private Integer idRescision;
    @Column(name = "activo")
    private Integer activo;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 45)
    @Column(name = "numero_recision")
    private String numeroRecision;
    @Basic(optional = false)
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
    @JoinColumn(name = "id_estatus", referencedColumnName = "id_estatus")
    @ManyToOne(optional = false)
    private Estatus idEstatus;
    @JoinColumn(name = "id_detalle_orden_suministro", referencedColumnName = "id_detalle_orden_suministro")
    @ManyToOne(optional = false)
    private DetalleOrdenSuministro idDetalleOrdenSuministro;

    public Rescisiones() {
    }

    public Rescisiones(Integer idRescision) {
        this.idRescision = idRescision;
    }

    public Rescisiones(Integer idRescision, String numeroRecision) {
        this.idRescision = idRescision;
        this.numeroRecision = numeroRecision;
    }

    public Integer getIdRescision() {
        return idRescision;
    }

    public void setIdRescision(Integer idRescision) {
        this.idRescision = idRescision;
    }

    public Integer getActivo() {
        return activo;
    }

    public void setActivo(Integer activo) {
        this.activo = activo;
    }

    public String getNumeroRecision() {
        return numeroRecision;
    }

    public void setNumeroRecision(String numeroRecision) {
        this.numeroRecision = numeroRecision;
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
        hash += (idRescision != null ? idRescision.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Rescisiones)) {
            return false;
        }
        Rescisiones other = (Rescisiones) object;
        if ((this.idRescision == null && other.idRescision != null) || (this.idRescision != null && !this.idRescision.equals(other.idRescision))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.issste.sicabis.ejb.modelo.Rescisiones[ idRescision=" + idRescision + " ]";
    }
    
}
