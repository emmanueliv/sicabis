/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.issste.sicabis.ejb.modelo;

import java.io.Serializable;
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
@Table(name = "lote")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Lote.findAll", query = "SELECT l FROM Lote l WHERE l.activo = 1"),
    @NamedQuery(name = "Lote.findByLote", query = "SELECT l FROM Lote l WHERE l.activo = 1 AND l.lote = :lote"),
    @NamedQuery(name = "Lote.findByIdLote", query = "SELECT l FROM Lote l WHERE l.idLote = :idLote"),
    @NamedQuery(name = "Lote.findByActivo", query = "SELECT l FROM Lote l WHERE l.activo = :activo"),
    @NamedQuery(name = "Lote.findByCodigoBarrasLote", query = "SELECT l FROM Lote l WHERE l.codigoBarrasLote = :codigoBarrasLote"),
    @NamedQuery(name = "Lote.findByCantidadLote", query = "SELECT l FROM Lote l WHERE l.cantidadLote = :cantidadLote"),
    @NamedQuery(name = "Lote.findByUsuarioAlta", query = "SELECT l FROM Lote l WHERE l.usuarioAlta = :usuarioAlta"),
    @NamedQuery(name = "Lote.findByUsuarioBaja", query = "SELECT l FROM Lote l WHERE l.usuarioBaja = :usuarioBaja"),
    @NamedQuery(name = "Lote.findByUsuarioModificacion", query = "SELECT l FROM Lote l WHERE l.usuarioModificacion = :usuarioModificacion"),
    @NamedQuery(name = "Lote.findByFechaAlta", query = "SELECT l FROM Lote l WHERE l.fechaAlta = :fechaAlta"),
    @NamedQuery(name = "Lote.findByFechaBaja", query = "SELECT l FROM Lote l WHERE l.fechaBaja = :fechaBaja"),
    @NamedQuery(name = "Lote.findByFechaModificaciones", query = "SELECT l FROM Lote l WHERE l.fechaModificaciones = :fechaModificaciones"),
    @NamedQuery(name = "Lote.findByFolioRemision", query = "SELECT l FROM Lote l WHERE l.folioRemision = :folioRemision")})
public class Lote implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id_lote")
    private Integer idLote;
    @Column(name = "activo")
    private Integer activo;
    @Size(max = 45)
    @Column(name = "lote")
    private String lote;
    @Column(name = "id_lote_padre")
    private Integer idLotePadre;
    @Size(max = 100)
    @Column(name = "codigo_barras_lote")
    private String codigoBarrasLote;
    @Column(name = "cantidad_lote")
    private Integer cantidadLote;
    @Column(name = "cantidad_recibida_control_calidad")
    private Integer cantidadRecibidaControlCalidad;
    @Column(name = "fecha_fabricacion")
    @Temporal(TemporalType.DATE)
    private Date fechaFabricacion;
    @Column(name = "fecha_caducidad")
    @Temporal(TemporalType.DATE)
    private Date fechaCaducidad;
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
    @JoinColumn(name = "id_remision", referencedColumnName = "id_remision")
    @ManyToOne(optional = false)
    private Remisiones idRemision;

    public Lote() {
    }

    public Lote(Integer idLote) {
        this.idLote = idLote;
    }

    public Integer getIdLote() {
        return idLote;
    }

    public void setIdLote(Integer idLote) {
        this.idLote = idLote;
    }

    public Integer getActivo() {
        return activo;
    }

    public void setActivo(Integer activo) {
        this.activo = activo;
    }

    public String getLote() {
        return lote;
    }

    public void setLote(String lote) {
        this.lote = lote;
    }

    public Integer getIdLotePadre() {
        return idLotePadre;
    }

    public void setIdLotePadre(Integer idLotePadre) {
        this.idLotePadre = idLotePadre;
    }

    public String getCodigoBarrasLote() {
        return codigoBarrasLote;
    }

    public void setCodigoBarrasLote(String codigoBarrasLote) {
        this.codigoBarrasLote = codigoBarrasLote;
    }

    public Integer getCantidadLote() {
        return cantidadLote;
    }

    public void setCantidadLote(Integer cantidadLote) {
        this.cantidadLote = cantidadLote;
    }

    public Date getFechaFabricacion() {
        return fechaFabricacion;
    }

    public void setFechaFabricacion(Date fechaFabricacion) {
        this.fechaFabricacion = fechaFabricacion;
    }

    public Integer getCantidadRecibidaControlCalidad() {
        return cantidadRecibidaControlCalidad;
    }

    public void setCantidadRecibidaControlCalidad(Integer cantidadRecibidaControlCalidad) {
        this.cantidadRecibidaControlCalidad = cantidadRecibidaControlCalidad;
    }

    public Date getFechaCaducidad() {
        return fechaCaducidad;
    }

    public void setFechaCaducidad(Date fechaCaducidad) {
        this.fechaCaducidad = fechaCaducidad;
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

    public Remisiones getIdRemision() {
        return idRemision;
    }

    public void setIdRemision(Remisiones idRemision) {
        this.idRemision = idRemision;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idLote != null ? idLote.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Lote)) {
            return false;
        }
        Lote other = (Lote) object;
        if ((this.idLote == null && other.idLote != null) || (this.idLote != null && !this.idLote.equals(other.idLote))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.issste.sicabis.ejb.modelo.Lote[ idLote=" + idLote + " ]";
    }

}
