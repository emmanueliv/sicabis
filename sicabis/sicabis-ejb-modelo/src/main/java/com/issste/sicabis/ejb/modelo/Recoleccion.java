/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.issste.sicabis.ejb.modelo;

import java.io.Serializable;
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
@Table(name = "recoleccion")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Recoleccion.findAll", query = "SELECT r FROM Recoleccion r"),
    @NamedQuery(name = "Recoleccion.findByIdRecoleccion", query = "SELECT r FROM Recoleccion r WHERE r.idRecoleccion = :idRecoleccion"),
    @NamedQuery(name = "Recoleccion.findByActivo", query = "SELECT r FROM Recoleccion r WHERE r.activo = :activo"),
    @NamedQuery(name = "Recoleccion.findByOficioRecoleccion", query = "SELECT r FROM Recoleccion r WHERE r.oficioRecoleccion = :oficioRecoleccion"),
    @NamedQuery(name = "Recoleccion.findByUsuarioAlta", query = "SELECT r FROM Recoleccion r WHERE r.usuarioAlta = :usuarioAlta"),
    @NamedQuery(name = "Recoleccion.findByUsuarioBaja", query = "SELECT r FROM Recoleccion r WHERE r.usuarioBaja = :usuarioBaja"),
    @NamedQuery(name = "Recoleccion.findByUsuarioModificacion", query = "SELECT r FROM Recoleccion r WHERE r.usuarioModificacion = :usuarioModificacion"),
    @NamedQuery(name = "Recoleccion.findByFechaAlta", query = "SELECT r FROM Recoleccion r WHERE r.fechaAlta = :fechaAlta"),
    @NamedQuery(name = "Recoleccion.findByFechaBaja", query = "SELECT r FROM Recoleccion r WHERE r.fechaBaja = :fechaBaja"),
    @NamedQuery(name = "Recoleccion.findByFechaModificaciones", query = "SELECT r FROM Recoleccion r WHERE r.fechaModificaciones = :fechaModificaciones")})
public class Recoleccion implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id_recoleccion")
    private Integer idRecoleccion;
    @Column(name = "activo")
    private Integer activo;
    @Basic(optional = false)
    @Size(min = 1, max = 45)
    @Column(name = "oficio_recoleccion")
    private String oficioRecoleccion;
    @Size(min = 1, max = 45)
    @Column(name = "folio_recoleccion")
    private String folioRecoleccion;
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
    @JoinColumn(name = "id_unidades_medicas", referencedColumnName = "id_unidades_medicas")
    @ManyToOne(optional = false)
    private UnidadesMedicas idUnidadesMedicas;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idRecoleccion")
    private List<DetalleRecoleccion> detalleRecoleccionList;

    public Recoleccion() {
    }

    public Recoleccion(Integer idRecoleccion) {
        this.idRecoleccion = idRecoleccion;
    }

    public Recoleccion(Integer idRecoleccion, String oficioRecoleccion) {
        this.idRecoleccion = idRecoleccion;
        this.oficioRecoleccion = oficioRecoleccion;
    }

    public Integer getIdRecoleccion() {
        return idRecoleccion;
    }

    public void setIdRecoleccion(Integer idRecoleccion) {
        this.idRecoleccion = idRecoleccion;
    }

    public Integer getActivo() {
        return activo;
    }

    public void setActivo(Integer activo) {
        this.activo = activo;
    }

    public String getOficioRecoleccion() {
        return oficioRecoleccion;
    }

    public void setOficioRecoleccion(String oficioRecoleccion) {
        this.oficioRecoleccion = oficioRecoleccion;
    }

    public String getFolioRecoleccion() {
        return folioRecoleccion;
    }

    public void setFolioRecoleccion(String folioRecoleccion) {
        this.folioRecoleccion = folioRecoleccion;
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

    public UnidadesMedicas getIdUnidadesMedicas() {
        return idUnidadesMedicas;
    }

    public void setIdUnidadesMedicas(UnidadesMedicas idUnidadesMedicas) {
        this.idUnidadesMedicas = idUnidadesMedicas;
    }

    @XmlTransient
    public List<DetalleRecoleccion> getDetalleRecoleccionList() {
        return detalleRecoleccionList;
    }

    public void setDetalleRecoleccionList(List<DetalleRecoleccion> detalleRecoleccionList) {
        this.detalleRecoleccionList = detalleRecoleccionList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idRecoleccion != null ? idRecoleccion.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Recoleccion)) {
            return false;
        }
        Recoleccion other = (Recoleccion) object;
        if ((this.idRecoleccion == null && other.idRecoleccion != null) || (this.idRecoleccion != null && !this.idRecoleccion.equals(other.idRecoleccion))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.issste.sicabis.ejb.modelo.Recoleccion[ idRecoleccion=" + idRecoleccion + " ]";
    }

}
