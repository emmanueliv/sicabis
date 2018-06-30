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
@Table(name = "contactos_proveedores")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "ContactosProveedores.findAll", query = "SELECT c FROM ContactosProveedores c"),
    @NamedQuery(name = "ContactosProveedores.findByIdContactosProveedores", query = "SELECT c FROM ContactosProveedores c WHERE c.idContactosProveedores = :idContactosProveedores"),
    @NamedQuery(name = "ContactosProveedores.findByActivo", query = "SELECT c FROM ContactosProveedores c WHERE c.activo = :activo"),
    @NamedQuery(name = "ContactosProveedores.findByUsuarioAlta", query = "SELECT c FROM ContactosProveedores c WHERE c.usuarioAlta = :usuarioAlta"),
    @NamedQuery(name = "ContactosProveedores.findByUsuarioBaja", query = "SELECT c FROM ContactosProveedores c WHERE c.usuarioBaja = :usuarioBaja"),
    @NamedQuery(name = "ContactosProveedores.findByUsuarioModificacion", query = "SELECT c FROM ContactosProveedores c WHERE c.usuarioModificacion = :usuarioModificacion"),
    @NamedQuery(name = "ContactosProveedores.findByFechaAlta", query = "SELECT c FROM ContactosProveedores c WHERE c.fechaAlta = :fechaAlta"),
    @NamedQuery(name = "ContactosProveedores.findByFechaBaja", query = "SELECT c FROM ContactosProveedores c WHERE c.fechaBaja = :fechaBaja"),
    @NamedQuery(name = "ContactosProveedores.findByFechaModificacion", query = "SELECT c FROM ContactosProveedores c WHERE c.fechaModificacion = :fechaModificacion")})
public class ContactosProveedores implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id_contactos_proveedores")
    private Integer idContactosProveedores;
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
    @JoinColumn(name = "id_contacto", referencedColumnName = "id_contacto")
    @ManyToOne(optional = false)
    private Contactos idContacto;

    public ContactosProveedores() {
    }

    public ContactosProveedores(Integer idContactosProveedores) {
        this.idContactosProveedores = idContactosProveedores;
    }

    public Integer getIdContactosProveedores() {
        return idContactosProveedores;
    }

    public void setIdContactosProveedores(Integer idContactosProveedores) {
        this.idContactosProveedores = idContactosProveedores;
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

    public Contactos getIdContacto() {
        return idContacto;
    }

    public void setIdContacto(Contactos idContacto) {
        this.idContacto = idContacto;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idContactosProveedores != null ? idContactosProveedores.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof ContactosProveedores)) {
            return false;
        }
        ContactosProveedores other = (ContactosProveedores) object;
        if ((this.idContactosProveedores == null && other.idContactosProveedores != null) || (this.idContactosProveedores != null && !this.idContactosProveedores.equals(other.idContactosProveedores))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.issste.sicabis.ejb.modelo.ContactosProveedores[ idContactosProveedores=" + idContactosProveedores + " ]";
    }
    
}
