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
@Table(name = "contactos")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Contactos.findAll", query = "SELECT c FROM Contactos c WHERE c.activo = 1"),
    @NamedQuery(name = "Contactos.findByIdContacto", query = "SELECT c FROM Contactos c WHERE c.idContacto = :idContacto"),
    @NamedQuery(name = "Contactos.findByActivo", query = "SELECT c FROM Contactos c WHERE c.activo = :activo"),
    @NamedQuery(name = "Contactos.findByNombre", query = "SELECT c FROM Contactos c WHERE c.nombre = :nombre AND c.activo = 1"),
    @NamedQuery(name = "Contactos.findByApellidoPaterno", query = "SELECT c FROM Contactos c WHERE c.apellidoPaterno = :apellidoPaterno"),
    @NamedQuery(name = "Contactos.findByApellidoMaterno", query = "SELECT c FROM Contactos c WHERE c.apellidoMaterno = :apellidoMaterno"),
    @NamedQuery(name = "Contactos.findByUsuarioAlta", query = "SELECT c FROM Contactos c WHERE c.usuarioAlta = :usuarioAlta"),
    @NamedQuery(name = "Contactos.findByUsuarioBaja", query = "SELECT c FROM Contactos c WHERE c.usuarioBaja = :usuarioBaja"),
    @NamedQuery(name = "Contactos.findByUsuarioModificacion", query = "SELECT c FROM Contactos c WHERE c.usuarioModificacion = :usuarioModificacion"),
    @NamedQuery(name = "Contactos.findByFechaAlta", query = "SELECT c FROM Contactos c WHERE c.fechaAlta = :fechaAlta"),
    @NamedQuery(name = "Contactos.findByFechaBaja", query = "SELECT c FROM Contactos c WHERE c.fechaBaja = :fechaBaja"),
    @NamedQuery(name = "Contactos.findByFechaModificacion", query = "SELECT c FROM Contactos c WHERE c.fechaModificacion = :fechaModificacion")})
public class Contactos implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id_contacto")
    private Integer idContacto;
    @Column(name = "activo")
    private Integer activo;
    @Basic(optional = false)
    @NotNull
    //@Size(min = 1, max = 200)
    @Column(name = "nombre")
    private String nombre;
    @Basic(optional = false)
    @NotNull
    //@Size(min = 1, max = 200)
    @Column(name = "apellido_paterno")
    private String apellidoPaterno;
    @Basic(optional = false)
    @NotNull
    //@Size(min = 1, max = 200)
    @Column(name = "apellido_materno")
    private String apellidoMaterno;
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
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idContacto")
    private List<ContactosProveedores> contactosProveedoresList;

    public Contactos() {
    }

    public Contactos(Integer idContacto) {
        this.idContacto = idContacto;
    }

    public Contactos(Integer idContacto, String nombre, String apellidoPaterno, String apellidoMaterno) {
        this.idContacto = idContacto;
        this.nombre = nombre;
        this.apellidoPaterno = apellidoPaterno;
        this.apellidoMaterno = apellidoMaterno;
    }

    public Integer getIdContacto() {
        return idContacto;
    }

    public void setIdContacto(Integer idContacto) {
        this.idContacto = idContacto;
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

    public String getApellidoPaterno() {
        return apellidoPaterno;
    }

    public void setApellidoPaterno(String apellidoPaterno) {
        this.apellidoPaterno = apellidoPaterno;
    }

    public String getApellidoMaterno() {
        return apellidoMaterno;
    }

    public void setApellidoMaterno(String apellidoMaterno) {
        this.apellidoMaterno = apellidoMaterno;
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
    public List<ContactosProveedores> getContactosProveedoresList() {
        return contactosProveedoresList;
    }

    public void setContactosProveedoresList(List<ContactosProveedores> contactosProveedoresList) {
        this.contactosProveedoresList = contactosProveedoresList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idContacto != null ? idContacto.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Contactos)) {
            return false;
        }
        Contactos other = (Contactos) object;
        if ((this.idContacto == null && other.idContacto != null) || (this.idContacto != null && !this.idContacto.equals(other.idContacto))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.issste.sicabis.ejb.modelo.Contactos[ idContacto=" + idContacto + " ]";
    }
    
}
