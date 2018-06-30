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
@Table(name = "condiciones_pago")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "CondicionesPago.findAll", query = "SELECT c FROM CondicionesPago c WHERE c.activo = 1 "),
    @NamedQuery(name = "CondicionesPago.findByIdCondicion", query = "SELECT c FROM CondicionesPago c WHERE c.idCondicion = :idCondicion"),
    @NamedQuery(name = "CondicionesPago.findByActivo", query = "SELECT c FROM CondicionesPago c WHERE c.activo = :activo"),
    @NamedQuery(name = "CondicionesPago.findByDescripcion", query = "SELECT c FROM CondicionesPago c WHERE c.descripcion = :descripcion AND c.activo = 1"),
    @NamedQuery(name = "CondicionesPago.findByUsuarioAlta", query = "SELECT c FROM CondicionesPago c WHERE c.usuarioAlta = :usuarioAlta"),
    @NamedQuery(name = "CondicionesPago.findByUsuarioBaja", query = "SELECT c FROM CondicionesPago c WHERE c.usuarioBaja = :usuarioBaja"),
    @NamedQuery(name = "CondicionesPago.findByUsuarioModificacion", query = "SELECT c FROM CondicionesPago c WHERE c.usuarioModificacion = :usuarioModificacion"),
    @NamedQuery(name = "CondicionesPago.findByFechaAlta", query = "SELECT c FROM CondicionesPago c WHERE c.fechaAlta = :fechaAlta"),
    @NamedQuery(name = "CondicionesPago.findByFechaBaja", query = "SELECT c FROM CondicionesPago c WHERE c.fechaBaja = :fechaBaja"),
    @NamedQuery(name = "CondicionesPago.findByFechaModificaciones", query = "SELECT c FROM CondicionesPago c WHERE c.fechaModificaciones = :fechaModificaciones")})
public class CondicionesPago implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id_condicion")
    private Integer idCondicion;
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
    @Column(name = "fecha_modificaciones")
    @Temporal(TemporalType.DATE)
    private Date fechaModificaciones;

    public CondicionesPago() {
    }

    public CondicionesPago(Integer idCondicion) {
        this.idCondicion = idCondicion;
    }

    public Integer getIdCondicion() {
        return idCondicion;
    }

    public void setIdCondicion(Integer idCondicion) {
        this.idCondicion = idCondicion;
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

    public Date getFechaModificaciones() {
        return fechaModificaciones;
    }

    public void setFechaModificaciones(Date fechaModificaciones) {
        this.fechaModificaciones = fechaModificaciones;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idCondicion != null ? idCondicion.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof CondicionesPago)) {
            return false;
        }
        CondicionesPago other = (CondicionesPago) object;
        if ((this.idCondicion == null && other.idCondicion != null) || (this.idCondicion != null && !this.idCondicion.equals(other.idCondicion))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.issste.sicabis.ejb.modelo.CondicionesPago[ idCondicion=" + idCondicion + " ]";
    }
    
}
