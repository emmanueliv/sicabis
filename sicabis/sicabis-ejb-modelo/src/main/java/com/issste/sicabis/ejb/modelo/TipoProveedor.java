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
@Table(name = "tipo_proveedor")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "TipoProveedor.findAll", query = "SELECT t FROM TipoProveedor t WHERE t.activo = 1"),
    @NamedQuery(name = "TipoProveedor.findByIdTipoProveedor", query = "SELECT t FROM TipoProveedor t WHERE t.idTipoProveedor = :idTipoProveedor"),
    @NamedQuery(name = "TipoProveedor.findByActivo", query = "SELECT t FROM TipoProveedor t WHERE t.activo = :activo"),
    @NamedQuery(name = "TipoProveedor.findByDescripcion", query = "SELECT t FROM TipoProveedor t WHERE t.descripcion = :descripcion AND t.activo = 1"),
    @NamedQuery(name = "TipoProveedor.findByUsuarioAlta", query = "SELECT t FROM TipoProveedor t WHERE t.usuarioAlta = :usuarioAlta"),
    @NamedQuery(name = "TipoProveedor.findByUsuarioBaja", query = "SELECT t FROM TipoProveedor t WHERE t.usuarioBaja = :usuarioBaja"),
    @NamedQuery(name = "TipoProveedor.findByUsuarioModificacion", query = "SELECT t FROM TipoProveedor t WHERE t.usuarioModificacion = :usuarioModificacion"),
    @NamedQuery(name = "TipoProveedor.findByFechaAlta", query = "SELECT t FROM TipoProveedor t WHERE t.fechaAlta = :fechaAlta"),
    @NamedQuery(name = "TipoProveedor.findByFechaBaja", query = "SELECT t FROM TipoProveedor t WHERE t.fechaBaja = :fechaBaja"),
    @NamedQuery(name = "TipoProveedor.findByFechaModificacion", query = "SELECT t FROM TipoProveedor t WHERE t.fechaModificacion = :fechaModificacion")})
public class TipoProveedor implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id_tipo_proveedor")
    private Integer idTipoProveedor;
    @Column(name = "activo")
    private Integer activo;
    @Basic(optional = false)
    @NotNull
    //@Size(min = 1, max = 45)
    @Column(name = "descripcion")
    private String descripcion;
    @Column(name = "tipo")
    private String tipo;
    @Column(name = "id_padre")
    private Integer idPadre;
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
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idTipoProveedor")
    private List<Proveedores> proveedoresList;

    public TipoProveedor() {
    }

    public TipoProveedor(Integer idTipoProveedor) {
        this.idTipoProveedor = idTipoProveedor;
    }

    public TipoProveedor(Integer idTipoProveedor, String descripcion) {
        this.idTipoProveedor = idTipoProveedor;
        this.descripcion = descripcion;
    }

    public Integer getIdTipoProveedor() {
        return idTipoProveedor;
    }

    public void setIdTipoProveedor(Integer idTipoProveedor) {
        this.idTipoProveedor = idTipoProveedor;
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

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public Integer getIdPadre() {
        return idPadre;
    }

    public void setIdPadre(Integer idPadre) {
        this.idPadre = idPadre;
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
    public List<Proveedores> getProveedoresList() {
        return proveedoresList;
    }

    public void setProveedoresList(List<Proveedores> proveedoresList) {
        this.proveedoresList = proveedoresList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idTipoProveedor != null ? idTipoProveedor.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof TipoProveedor)) {
            return false;
        }
        TipoProveedor other = (TipoProveedor) object;
        if ((this.idTipoProveedor == null && other.idTipoProveedor != null) || (this.idTipoProveedor != null && !this.idTipoProveedor.equals(other.idTipoProveedor))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.issste.sicabis.ejb.modelo.TipoProveedor[ idTipoProveedor=" + idTipoProveedor + " ]";
    }

}
