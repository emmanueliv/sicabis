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
@Table(name = "clasificacion")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Clasificacion.findAll", query = "SELECT c FROM Clasificacion c WHERE c.activo = 1"),
    @NamedQuery(name = "Clasificacion.findByIdClasificacion", query = "SELECT c FROM Clasificacion c WHERE c.idClasificacion = :idClasificacion"),
    @NamedQuery(name = "Clasificacion.findByActivo", query = "SELECT c FROM Clasificacion c WHERE c.activo = :activo"),
    @NamedQuery(name = "Clasificacion.findByDescripcion", query = "SELECT c FROM Clasificacion c WHERE c.descripcion = :descripcion AND c.activo = 1"),
    @NamedQuery(name = "Clasificacion.findByUsuarioAlta", query = "SELECT c FROM Clasificacion c WHERE c.usuarioAlta = :usuarioAlta"),
    @NamedQuery(name = "Clasificacion.findByUsuarioBaja", query = "SELECT c FROM Clasificacion c WHERE c.usuarioBaja = :usuarioBaja"),
    @NamedQuery(name = "Clasificacion.findByUsuarioModificacion", query = "SELECT c FROM Clasificacion c WHERE c.usuarioModificacion = :usuarioModificacion"),
    @NamedQuery(name = "Clasificacion.findByFechaAlta", query = "SELECT c FROM Clasificacion c WHERE c.fechaAlta = :fechaAlta"),
    @NamedQuery(name = "Clasificacion.findByFechaBaja", query = "SELECT c FROM Clasificacion c WHERE c.fechaBaja = :fechaBaja"),
    @NamedQuery(name = "Clasificacion.findByFechaModificiacion", query = "SELECT c FROM Clasificacion c WHERE c.fechaModificiacion = :fechaModificiacion")})
public class Clasificacion implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id_clasificacion")
    private Integer idClasificacion;
    @Column(name = "activo")
    private Integer activo;
    @Basic(optional = false)
    @NotNull
    //@Size(min = 1, max = 50)
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
    @Column(name = "fecha_modificiacion")
    @Temporal(TemporalType.DATE)
    private Date fechaModificiacion;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idClasificacion")
    private List<Insumos> insumosList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idClasificacion")
    private List<RcbInsumos> rcbInsumosList;
    

    public Clasificacion() {
    }

    public Clasificacion(Integer idClasificacion) {
        this.idClasificacion = idClasificacion;
    }

    public Clasificacion(Integer idClasificacion, String descripcion) {
        this.idClasificacion = idClasificacion;
        this.descripcion = descripcion;
    }

    public Integer getIdClasificacion() {
        return idClasificacion;
    }

    public void setIdClasificacion(Integer idClasificacion) {
        this.idClasificacion = idClasificacion;
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

    public Date getFechaModificiacion() {
        return fechaModificiacion;
    }

    public void setFechaModificiacion(Date fechaModificiacion) {
        this.fechaModificiacion = fechaModificiacion;
    }

    public List<RcbInsumos> getRcbInsumosList() {
        return rcbInsumosList;
    }

    public void setRcbInsumosList(List<RcbInsumos> rcbInsumosList) {
        this.rcbInsumosList = rcbInsumosList;
    }
    
    

    @XmlTransient
    public List<Insumos> getInsumosList() {
        return insumosList;
    }

    public void setInsumosList(List<Insumos> insumosList) {
        this.insumosList = insumosList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idClasificacion != null ? idClasificacion.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Clasificacion)) {
            return false;
        }
        Clasificacion other = (Clasificacion) object;
        if ((this.idClasificacion == null && other.idClasificacion != null) || (this.idClasificacion != null && !this.idClasificacion.equals(other.idClasificacion))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.issste.sicabis.ejb.modelo.Clasificacion[ idClasificacion=" + idClasificacion + " ]";
    }
    
}
