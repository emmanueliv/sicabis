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
@Table(name = "unidad_pieza")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "UnidadPieza.findAll", query = "SELECT u FROM UnidadPieza u"),
    @NamedQuery(name = "UnidadPieza.findByIdUnidadPieza", query = "SELECT u FROM UnidadPieza u WHERE u.idUnidadPieza = :idUnidadPieza"),
    @NamedQuery(name = "UnidadPieza.findByActivo", query = "SELECT u FROM UnidadPieza u WHERE u.activo = :activo"),
    @NamedQuery(name = "UnidadPieza.findByDescripcion", query = "SELECT u FROM UnidadPieza u WHERE u.descripcion = :descripcion"),
    @NamedQuery(name = "UnidadPieza.findByUsuarioAlta", query = "SELECT u FROM UnidadPieza u WHERE u.usuarioAlta = :usuarioAlta"),
    @NamedQuery(name = "UnidadPieza.findByUsuarioBaja", query = "SELECT u FROM UnidadPieza u WHERE u.usuarioBaja = :usuarioBaja"),
    @NamedQuery(name = "UnidadPieza.findByUsuarioModificacion", query = "SELECT u FROM UnidadPieza u WHERE u.usuarioModificacion = :usuarioModificacion"),
    @NamedQuery(name = "UnidadPieza.findByFechaAlta", query = "SELECT u FROM UnidadPieza u WHERE u.fechaAlta = :fechaAlta"),
    @NamedQuery(name = "UnidadPieza.findByFechaBaja", query = "SELECT u FROM UnidadPieza u WHERE u.fechaBaja = :fechaBaja"),
    @NamedQuery(name = "UnidadPieza.findByFechaModificacion", query = "SELECT u FROM UnidadPieza u WHERE u.fechaModificacion = :fechaModificacion")})
public class UnidadPieza implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id_unidad_pieza")
    private Integer idUnidadPieza;
    @Column(name = "activo")
    private Integer activo;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 45)
    @Column(name = "descripcion")
    private String descripcion;
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
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idUnidadPieza")
    private List<Insumos> insumosList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idUnidadPieza")
    private List<RcbInsumos> rcbInsumosList;

    public UnidadPieza() {
    }

    public UnidadPieza(Integer idUnidadPieza) {
        this.idUnidadPieza = idUnidadPieza;
    }

    public UnidadPieza(Integer idUnidadPieza, String descripcion) {
        this.idUnidadPieza = idUnidadPieza;
        this.descripcion = descripcion;
    }

    public Integer getIdUnidadPieza() {
        return idUnidadPieza;
    }

    public void setIdUnidadPieza(Integer idUnidadPieza) {
        this.idUnidadPieza = idUnidadPieza;
    }

    public Integer getActivo() {
        return activo;
    }

    public void setActivo(Integer activo) {
        this.activo = activo;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
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
    public List<Insumos> getInsumosList() {
        return insumosList;
    }

    public void setInsumosList(List<Insumos> insumosList) {
        this.insumosList = insumosList;
    }

    public List<RcbInsumos> getRcbInsumosList() {
        return rcbInsumosList;
    }

    public void setRcbInsumosList(List<RcbInsumos> rcbInsumosList) {
        this.rcbInsumosList = rcbInsumosList;
    }
    
    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idUnidadPieza != null ? idUnidadPieza.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof UnidadPieza)) {
            return false;
        }
        UnidadPieza other = (UnidadPieza) object;
        if ((this.idUnidadPieza == null && other.idUnidadPieza != null) || (this.idUnidadPieza != null && !this.idUnidadPieza.equals(other.idUnidadPieza))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.issste.sicabis.ejb.modelo.UnidadPieza[ idUnidadPieza=" + idUnidadPieza + " ]";
    }
    
}
