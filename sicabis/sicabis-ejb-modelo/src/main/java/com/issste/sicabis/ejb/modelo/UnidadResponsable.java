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
@Table(name = "unidad_responsable")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "UnidadResponsable.findAll", query = "SELECT u FROM UnidadResponsable u"),
    @NamedQuery(name = "UnidadResponsable.findByIdUnidadResponsable", query = "SELECT u FROM UnidadResponsable u WHERE u.idUnidadResponsable = :idUnidadResponsable"),
    @NamedQuery(name = "UnidadResponsable.findByActivo", query = "SELECT u FROM UnidadResponsable u WHERE u.activo = :activo"),
    @NamedQuery(name = "UnidadResponsable.findByNombre", query = "SELECT u FROM UnidadResponsable u WHERE u.nombre = :nombre"),
    @NamedQuery(name = "UnidadResponsable.findByUsuarioAlta", query = "SELECT u FROM UnidadResponsable u WHERE u.usuarioAlta = :usuarioAlta"),
    @NamedQuery(name = "UnidadResponsable.findByUsuarioBaja", query = "SELECT u FROM UnidadResponsable u WHERE u.usuarioBaja = :usuarioBaja"),
    @NamedQuery(name = "UnidadResponsable.findByUsuarioModificacion", query = "SELECT u FROM UnidadResponsable u WHERE u.usuarioModificacion = :usuarioModificacion"),
    @NamedQuery(name = "UnidadResponsable.findByFechaAlta", query = "SELECT u FROM UnidadResponsable u WHERE u.fechaAlta = :fechaAlta"),
    @NamedQuery(name = "UnidadResponsable.findByFechaBaja", query = "SELECT u FROM UnidadResponsable u WHERE u.fechaBaja = :fechaBaja"),
    @NamedQuery(name = "UnidadResponsable.findByFechaModificacion", query = "SELECT u FROM UnidadResponsable u WHERE u.fechaModificacion = :fechaModificacion")})
public class UnidadResponsable implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id_unidad_responsable")
    private Integer idUnidadResponsable;
    @Column(name = "activo")
    private Integer activo;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 45)
    @Column(name = "nombre")
    private String nombre;
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
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idUnidadResponsable")
    private List<Cr> crList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idUnidadResponsable")
    private List<Rcb> rcbList;

    public UnidadResponsable() {
    }

    public UnidadResponsable(Integer idUnidadResponsable) {
        this.idUnidadResponsable = idUnidadResponsable;
    }

    public UnidadResponsable(Integer idUnidadResponsable, String nombre) {
        this.idUnidadResponsable = idUnidadResponsable;
        this.nombre = nombre;
    }

    public Integer getIdUnidadResponsable() {
        return idUnidadResponsable;
    }

    public void setIdUnidadResponsable(Integer idUnidadResponsable) {
        this.idUnidadResponsable = idUnidadResponsable;
    }

    public Integer getActivo() {
        return activo;
    }

    public void setActivo(Integer activo) {
        this.activo = activo;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
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
    public List<Cr> getCrList() {
        return crList;
    }

    public void setCrList(List<Cr> crList) {
        this.crList = crList;
    }

    @XmlTransient
    public List<Rcb> getRcbList() {
        return rcbList;
    }

    public void setRcbList(List<Rcb> rcbList) {
        this.rcbList = rcbList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idUnidadResponsable != null ? idUnidadResponsable.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof UnidadResponsable)) {
            return false;
        }
        UnidadResponsable other = (UnidadResponsable) object;
        if ((this.idUnidadResponsable == null && other.idUnidadResponsable != null) || (this.idUnidadResponsable != null && !this.idUnidadResponsable.equals(other.idUnidadResponsable))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.issste.sicabis.ejb.modelo.UnidadResponsable[ idUnidadResponsable=" + idUnidadResponsable + " ]";
    }
    
}
