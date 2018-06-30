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
@Table(name = "proveedor_fabricante")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "ProveedorFabricante.findAll", query = "SELECT p FROM ProveedorFabricante p"),
    @NamedQuery(name = "ProveedorFabricante.findByIdProveedorFabricante", query = "SELECT p FROM ProveedorFabricante p WHERE p.idProveedorFabricante = :idProveedorFabricante"),
    @NamedQuery(name = "ProveedorFabricante.findByActivo", query = "SELECT p FROM ProveedorFabricante p WHERE p.activo = :activo"),
    @NamedQuery(name = "ProveedorFabricante.findByUsuarioAlta", query = "SELECT p FROM ProveedorFabricante p WHERE p.usuarioAlta = :usuarioAlta"),
    @NamedQuery(name = "ProveedorFabricante.findByUsuarioBaja", query = "SELECT p FROM ProveedorFabricante p WHERE p.usuarioBaja = :usuarioBaja"),
    @NamedQuery(name = "ProveedorFabricante.findByUsuarioModificacion", query = "SELECT p FROM ProveedorFabricante p WHERE p.usuarioModificacion = :usuarioModificacion"),
    @NamedQuery(name = "ProveedorFabricante.findByFechaAlta", query = "SELECT p FROM ProveedorFabricante p WHERE p.fechaAlta = :fechaAlta"),
    @NamedQuery(name = "ProveedorFabricante.findByFechaBaja", query = "SELECT p FROM ProveedorFabricante p WHERE p.fechaBaja = :fechaBaja"),
    @NamedQuery(name = "ProveedorFabricante.findByFechaModificacion", query = "SELECT p FROM ProveedorFabricante p WHERE p.fechaModificacion = :fechaModificacion")})
public class ProveedorFabricante implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id_proveedor_fabricante")
    private Integer idProveedorFabricante;
    @Column(name = "id_procedimiento_rcb")
    private Integer idProcedimientoRcb;
    @Column(name = "activo")
    private Integer activo;
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
    @JoinColumn(name = "id_proveedor", referencedColumnName = "id_proveedor")
    @ManyToOne(optional = false)
    private Proveedores idProveedor;
    @JoinColumn(name = "id_fabricante", referencedColumnName = "id_fabricante")
    @ManyToOne(optional = false)
    private Fabricante idFabricante;

    public ProveedorFabricante() {
    }

    public ProveedorFabricante(Integer idProveedorFabricante) {
        this.idProveedorFabricante = idProveedorFabricante;
    }

    public Integer getIdProveedorFabricante() {
        return idProveedorFabricante;
    }

    public void setIdProveedorFabricante(Integer idProveedorFabricante) {
        this.idProveedorFabricante = idProveedorFabricante;
    }
    
    public Integer getIdProcedimientoRcb() {
        return idProcedimientoRcb;
    }

    public void setIdProcedimientoRcb(Integer idProcedimientoRcb) {
        this.idProcedimientoRcb = idProcedimientoRcb;
    }

    public Integer getActivo() {
        return activo;
    }

    public void setActivo(Integer activo) {
        this.activo = activo;
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

    public Proveedores getIdProveedor() {
        return idProveedor;
    }

    public void setIdProveedor(Proveedores idProveedor) {
        this.idProveedor = idProveedor;
    }

    public Fabricante getIdFabricante() {
        return idFabricante;
    }

    public void setIdFabricante(Fabricante idFabricante) {
        this.idFabricante = idFabricante;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idProveedorFabricante != null ? idProveedorFabricante.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof ProveedorFabricante)) {
            return false;
        }
        ProveedorFabricante other = (ProveedorFabricante) object;
        if ((this.idProveedorFabricante == null && other.idProveedorFabricante != null) || (this.idProveedorFabricante != null && !this.idProveedorFabricante.equals(other.idProveedorFabricante))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.issste.sicabis.ejb.modelo.ProveedorFabricante[ idProveedorFabricante=" + idProveedorFabricante + " ]";
    }
    
}
