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
@Table(name = "descripcion_insumos_opcional")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "DescripcionInsumosOpcional.findAll", query = "SELECT d FROM DescripcionInsumosOpcional d"),
    @NamedQuery(name = "DescripcionInsumosOpcional.findByIdDescripcionInsumosOpcional", query = "SELECT d FROM DescripcionInsumosOpcional d WHERE d.idDescripcionInsumosOpcional = :idDescripcionInsumosOpcional"),
    @NamedQuery(name = "DescripcionInsumosOpcional.findByActivo", query = "SELECT d FROM DescripcionInsumosOpcional d WHERE d.activo = :activo"),
    @NamedQuery(name = "DescripcionInsumosOpcional.findByDescripcion", query = "SELECT d FROM DescripcionInsumosOpcional d WHERE d.descripcion = :descripcion"),
    @NamedQuery(name = "DescripcionInsumosOpcional.findByUsuarioAlta", query = "SELECT d FROM DescripcionInsumosOpcional d WHERE d.usuarioAlta = :usuarioAlta"),
    @NamedQuery(name = "DescripcionInsumosOpcional.findByUsuarioBaja", query = "SELECT d FROM DescripcionInsumosOpcional d WHERE d.usuarioBaja = :usuarioBaja"),
    @NamedQuery(name = "DescripcionInsumosOpcional.findByUsuarioModificacion", query = "SELECT d FROM DescripcionInsumosOpcional d WHERE d.usuarioModificacion = :usuarioModificacion"),
    @NamedQuery(name = "DescripcionInsumosOpcional.findByFechaAlta", query = "SELECT d FROM DescripcionInsumosOpcional d WHERE d.fechaAlta = :fechaAlta"),
    @NamedQuery(name = "DescripcionInsumosOpcional.findByFechaBaja", query = "SELECT d FROM DescripcionInsumosOpcional d WHERE d.fechaBaja = :fechaBaja"),
    @NamedQuery(name = "DescripcionInsumosOpcional.findByFechaModificacion", query = "SELECT d FROM DescripcionInsumosOpcional d WHERE d.fechaModificacion = :fechaModificacion")})
public class DescripcionInsumosOpcional implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id_descripcion_insumos_opcional")
    private Integer idDescripcionInsumosOpcional;
    @Column(name = "activo")
    private Integer activo;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 10000)
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
    @JoinColumn(name = "id_insumo", referencedColumnName = "id_insumo")
    @ManyToOne(optional = false)
    private Insumos idInsumo;

    public DescripcionInsumosOpcional() {
    }

    public DescripcionInsumosOpcional(Integer idDescripcionInsumosOpcional) {
        this.idDescripcionInsumosOpcional = idDescripcionInsumosOpcional;
    }

    public DescripcionInsumosOpcional(Integer idDescripcionInsumosOpcional, String descripcion) {
        this.idDescripcionInsumosOpcional = idDescripcionInsumosOpcional;
        this.descripcion = descripcion;
    }

    public Integer getIdDescripcionInsumosOpcional() {
        return idDescripcionInsumosOpcional;
    }

    public void setIdDescripcionInsumosOpcional(Integer idDescripcionInsumosOpcional) {
        this.idDescripcionInsumosOpcional = idDescripcionInsumosOpcional;
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

    public Insumos getIdInsumo() {
        return idInsumo;
    }

    public void setIdInsumo(Insumos idInsumo) {
        this.idInsumo = idInsumo;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idDescripcionInsumosOpcional != null ? idDescripcionInsumosOpcional.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof DescripcionInsumosOpcional)) {
            return false;
        }
        DescripcionInsumosOpcional other = (DescripcionInsumosOpcional) object;
        if ((this.idDescripcionInsumosOpcional == null && other.idDescripcionInsumosOpcional != null) || (this.idDescripcionInsumosOpcional != null && !this.idDescripcionInsumosOpcional.equals(other.idDescripcionInsumosOpcional))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.issste.sicabis.ejb.modelo.DescripcionInsumosOpcional[ idDescripcionInsumosOpcional=" + idDescripcionInsumosOpcional + " ]";
    }
    
}
