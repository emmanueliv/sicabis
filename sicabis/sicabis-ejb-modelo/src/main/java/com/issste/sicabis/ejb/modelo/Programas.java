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
@Table(name = "programas")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Programas.findAll", query = "SELECT p FROM Programas p"),
    @NamedQuery(name = "Programas.findByIdPrograma", query = "SELECT p FROM Programas p WHERE p.idPrograma = :idPrograma"),
    @NamedQuery(name = "Programas.findByActivo", query = "SELECT p FROM Programas p WHERE p.activo = :activo"),
    @NamedQuery(name = "Programas.findByDescripcion", query = "SELECT p FROM Programas p WHERE p.descripcion = :descripcion"),
    @NamedQuery(name = "Programas.findByUsuarioAlta", query = "SELECT p FROM Programas p WHERE p.usuarioAlta = :usuarioAlta"),
    @NamedQuery(name = "Programas.findByUsuarioBaja", query = "SELECT p FROM Programas p WHERE p.usuarioBaja = :usuarioBaja"),
    @NamedQuery(name = "Programas.findByUsuarioModificacion", query = "SELECT p FROM Programas p WHERE p.usuarioModificacion = :usuarioModificacion"),
    @NamedQuery(name = "Programas.findByFechaAlta", query = "SELECT p FROM Programas p WHERE p.fechaAlta = :fechaAlta"),
    @NamedQuery(name = "Programas.findByFechaBaja", query = "SELECT p FROM Programas p WHERE p.fechaBaja = :fechaBaja"),
    @NamedQuery(name = "Programas.findByFechaModificacion", query = "SELECT p FROM Programas p WHERE p.fechaModificacion = :fechaModificacion")})
public class Programas implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id_programa")
    private Integer idPrograma;
    @Column(name = "activo")
    private Integer activo;
    @Size(max = 200)
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
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idPrograma")
    private List<AsignacionInsumos> asignacionInsumosList;  

    
    public Programas() {
    }

    public Programas(Integer idPrograma) {
        this.idPrograma = idPrograma;
    }

    public Integer getIdPrograma() {
        return idPrograma;
    }

    public void setIdPrograma(Integer idPrograma) {
        this.idPrograma = idPrograma;
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

    public List<AsignacionInsumos> getAsignacionInsumosList() {
        return asignacionInsumosList;
    }

    public void setAsignacionInsumosList(List<AsignacionInsumos> asignacionInsumosList) {
        this.asignacionInsumosList = asignacionInsumosList;
    }

    
    
    


    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idPrograma != null ? idPrograma.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Programas)) {
            return false;
        }
        Programas other = (Programas) object;
        if ((this.idPrograma == null && other.idPrograma != null) || (this.idPrograma != null && !this.idPrograma.equals(other.idPrograma))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.issste.sicabis.ejb.modelo.Programas[ idPrograma=" + idPrograma + " ]";
    }
    
}
