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
@Table(name = "usuario_perfil")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "UsuarioPerfil.findAll", query = "SELECT u FROM UsuarioPerfil u"),
    @NamedQuery(name = "UsuarioPerfil.findByIdPerfilUsuarios", query = "SELECT u FROM UsuarioPerfil u WHERE u.idPerfilUsuarios = :idPerfilUsuarios"),
    @NamedQuery(name = "UsuarioPerfil.findByActivo", query = "SELECT u FROM UsuarioPerfil u WHERE u.activo = :activo"),
    @NamedQuery(name = "UsuarioPerfil.findByUsuarioAlta", query = "SELECT u FROM UsuarioPerfil u WHERE u.usuarioAlta = :usuarioAlta"),
    @NamedQuery(name = "UsuarioPerfil.findByUsuarioBaja", query = "SELECT u FROM UsuarioPerfil u WHERE u.usuarioBaja = :usuarioBaja"),
    @NamedQuery(name = "UsuarioPerfil.findByUsuarioModificacion", query = "SELECT u FROM UsuarioPerfil u WHERE u.usuarioModificacion = :usuarioModificacion"),
    @NamedQuery(name = "UsuarioPerfil.findByFechaAlta", query = "SELECT u FROM UsuarioPerfil u WHERE u.fechaAlta = :fechaAlta"),
    @NamedQuery(name = "UsuarioPerfil.findByFechaBaja", query = "SELECT u FROM UsuarioPerfil u WHERE u.fechaBaja = :fechaBaja"),
    @NamedQuery(name = "UsuarioPerfil.findByFechaModificacion", query = "SELECT u FROM UsuarioPerfil u WHERE u.fechaModificacion = :fechaModificacion")})
public class UsuarioPerfil implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id_perfil_usuarios")
    private Integer idPerfilUsuarios;
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
    @JoinColumn(name = "id_usuario", referencedColumnName = "id_usuario")
    @ManyToOne(optional = false)
    private Usuarios idUsuario;
    @JoinColumn(name = "id_perfil", referencedColumnName = "id_perfil")
    @ManyToOne(optional = false)
    private Perfiles idPerfil;

    public UsuarioPerfil() {
    }

    public UsuarioPerfil(Integer idPerfilUsuarios) {
        this.idPerfilUsuarios = idPerfilUsuarios;
    }

    public Integer getIdPerfilUsuarios() {
        return idPerfilUsuarios;
    }

    public void setIdPerfilUsuarios(Integer idPerfilUsuarios) {
        this.idPerfilUsuarios = idPerfilUsuarios;
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

    public Usuarios getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(Usuarios idUsuario) {
        this.idUsuario = idUsuario;
    }

    public Perfiles getIdPerfil() {
        return idPerfil;
    }

    public void setIdPerfil(Perfiles idPerfil) {
        this.idPerfil = idPerfil;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idPerfilUsuarios != null ? idPerfilUsuarios.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof UsuarioPerfil)) {
            return false;
        }
        UsuarioPerfil other = (UsuarioPerfil) object;
        if ((this.idPerfilUsuarios == null && other.idPerfilUsuarios != null) || (this.idPerfilUsuarios != null && !this.idPerfilUsuarios.equals(other.idPerfilUsuarios))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.issste.sicabis.ejb.modelo.UsuarioPerfil[ idPerfilUsuarios=" + idPerfilUsuarios + " ]";
    }
    
}
