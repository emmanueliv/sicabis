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
@Table(name = "perfiles")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Perfiles.findAll", query = "SELECT p FROM Perfiles p"),
    @NamedQuery(name = "Perfiles.findByIdPerfil", query = "SELECT p FROM Perfiles p WHERE p.idPerfil = :idPerfil"),
    @NamedQuery(name = "Perfiles.findByActivo", query = "SELECT p FROM Perfiles p WHERE p.activo = :activo"),
    @NamedQuery(name = "Perfiles.findByNombre", query = "SELECT p FROM Perfiles p WHERE p.nombre = :nombre"),
    @NamedQuery(name = "Perfiles.findByEdita", query = "SELECT p FROM Perfiles p WHERE p.edita = :edita"),
    @NamedQuery(name = "Perfiles.findByConsulta", query = "SELECT p FROM Perfiles p WHERE p.consulta = :consulta"),
    @NamedQuery(name = "Perfiles.findByUsuarioAlta", query = "SELECT p FROM Perfiles p WHERE p.usuarioAlta = :usuarioAlta"),
    @NamedQuery(name = "Perfiles.findByUsuarioBaja", query = "SELECT p FROM Perfiles p WHERE p.usuarioBaja = :usuarioBaja"),
    @NamedQuery(name = "Perfiles.findByUsuarioModificacion", query = "SELECT p FROM Perfiles p WHERE p.usuarioModificacion = :usuarioModificacion"),
    @NamedQuery(name = "Perfiles.findByFechaAlta", query = "SELECT p FROM Perfiles p WHERE p.fechaAlta = :fechaAlta"),
    @NamedQuery(name = "Perfiles.findByFechaBaja", query = "SELECT p FROM Perfiles p WHERE p.fechaBaja = :fechaBaja"),
    @NamedQuery(name = "Perfiles.findByFechaModificacion", query = "SELECT p FROM Perfiles p WHERE p.fechaModificacion = :fechaModificacion")})
public class Perfiles implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id_perfil")
    private Integer idPerfil;
    @Basic(optional = false)
    @NotNull
    @Column(name = "activo")
    private int activo;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 45)
    @Column(name = "nombre")
    private String nombre;
    @Basic(optional = false)
    @NotNull
    @Column(name = "edita")
    private int edita;
    @Basic(optional = false)
    @NotNull
    @Column(name = "consulta")
    private int consulta;
    @Column(name = "administrador")
    private Integer administrador;
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
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idPerfil")
    private List<MenuPerfil> menuPerfilList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idPerfil")
    private List<UsuarioPerfil> usuarioPerfilList;

    public Perfiles() {
    }

    public Perfiles(Integer idPerfil) {
        this.idPerfil = idPerfil;
    }

    public Perfiles(Integer idPerfil, int activo, String nombre, int edita, int consulta) {
        this.idPerfil = idPerfil;
        this.activo = activo;
        this.nombre = nombre;
        this.edita = edita;
        this.consulta = consulta;
    }

    public Integer getIdPerfil() {
        return idPerfil;
    }

    public void setIdPerfil(Integer idPerfil) {
        this.idPerfil = idPerfil;
    }

    public int getActivo() {
        return activo;
    }

    public void setActivo(int activo) {
        this.activo = activo;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public int getEdita() {
        return edita;
    }

    public void setEdita(int edita) {
        this.edita = edita;
    }

    public int getConsulta() {
        return consulta;
    }

    public void setConsulta(int consulta) {
        this.consulta = consulta;
    }

    public Integer getAdministrador() {
        return administrador;
    }

    public void setAdministrador(Integer administrador) {
        this.administrador = administrador;
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
    public List<MenuPerfil> getMenuPerfilList() {
        return menuPerfilList;
    }

    public void setMenuPerfilList(List<MenuPerfil> menuPerfilList) {
        this.menuPerfilList = menuPerfilList;
    }

    @XmlTransient
    public List<UsuarioPerfil> getUsuarioPerfilList() {
        return usuarioPerfilList;
    }

    public void setUsuarioPerfilList(List<UsuarioPerfil> usuarioPerfilList) {
        this.usuarioPerfilList = usuarioPerfilList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idPerfil != null ? idPerfil.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Perfiles)) {
            return false;
        }
        Perfiles other = (Perfiles) object;
        if ((this.idPerfil == null && other.idPerfil != null) || (this.idPerfil != null && !this.idPerfil.equals(other.idPerfil))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.issste.sicabis.ejb.modelo.Perfiles[ idPerfil=" + idPerfil + " ]";
    }
    
}
