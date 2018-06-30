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
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author kriosoft
 */
@Entity
@Table(name = "correo_electronico")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "CorreoElectronico.findAll", query = "SELECT c FROM CorreoElectronico c"),
    @NamedQuery(name = "CorreoElectronico.findByIdCorreoElectronico", query = "SELECT c FROM CorreoElectronico c WHERE c.idCorreoElectronico = :idCorreoElectronico"),
    @NamedQuery(name = "CorreoElectronico.findByActivo", query = "SELECT c FROM CorreoElectronico c WHERE c.activo = :activo"),
    @NamedQuery(name = "CorreoElectronico.findByTipo", query = "SELECT c FROM CorreoElectronico c WHERE c.tipo = :tipo"),
    @NamedQuery(name = "CorreoElectronico.findByUsuarioAlta", query = "SELECT c FROM CorreoElectronico c WHERE c.usuarioAlta = :usuarioAlta"),
    @NamedQuery(name = "CorreoElectronico.findByUsuarioBaja", query = "SELECT c FROM CorreoElectronico c WHERE c.usuarioBaja = :usuarioBaja"),
    @NamedQuery(name = "CorreoElectronico.findByUsuarioModificacion", query = "SELECT c FROM CorreoElectronico c WHERE c.usuarioModificacion = :usuarioModificacion"),
    @NamedQuery(name = "CorreoElectronico.findByFechaAlta", query = "SELECT c FROM CorreoElectronico c WHERE c.fechaAlta = :fechaAlta"),
    @NamedQuery(name = "CorreoElectronico.findByFechaBaja", query = "SELECT c FROM CorreoElectronico c WHERE c.fechaBaja = :fechaBaja"),
    @NamedQuery(name = "CorreoElectronico.findByFechaModificacion", query = "SELECT c FROM CorreoElectronico c WHERE c.fechaModificacion = :fechaModificacion")})
public class CorreoElectronico implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id_correo_electronico")
    private Integer idCorreoElectronico;
    @Column(name = "activo")
    private Integer activo;
    @Size(max = 45)
    @Column(name = "nombre")
    private String nombre;
    @Size(max = 45)
    @Column(name = "correo")
    private String correo;
    @Size(max = 45)
    @Column(name = "tipo")
    private String tipo;
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

    public CorreoElectronico() {
    }

    public CorreoElectronico(Integer idCorreoElectronico) {
        this.idCorreoElectronico = idCorreoElectronico;
    }

    public Integer getIdCorreoElectronico() {
        return idCorreoElectronico;
    }

    public void setIdCorreoElectronico(Integer idCorreoElectronico) {
        this.idCorreoElectronico = idCorreoElectronico;
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

    public String getCorreo() {
        return correo;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
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

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idCorreoElectronico != null ? idCorreoElectronico.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof CorreoElectronico)) {
            return false;
        }
        CorreoElectronico other = (CorreoElectronico) object;
        if ((this.idCorreoElectronico == null && other.idCorreoElectronico != null) || (this.idCorreoElectronico != null && !this.idCorreoElectronico.equals(other.idCorreoElectronico))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.issste.sicabis.ejb.modelo.CorreoElectronico[ idCorreoElectronico=" + idCorreoElectronico + " ]";
    }

}
