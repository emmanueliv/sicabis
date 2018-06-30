/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.issste.sicabis.ejb.modelo;

import java.io.Serializable;
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
import javax.validation.constraints.NotNull;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author 9RZCBG2
 */
@Entity
@Table(name = "usuarios_tipo_usuarios")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "UsuariosTipoUsuarios.findAll", query = "SELECT u FROM UsuariosTipoUsuarios u"),
    @NamedQuery(name = "UsuariosTipoUsuarios.findByIdUsuariosTipoUsuarios", query = "SELECT u FROM UsuariosTipoUsuarios u WHERE u.idUsuariosTipoUsuarios = :idUsuariosTipoUsuarios"),
    @NamedQuery(name = "UsuariosTipoUsuarios.findByIdUsuario", query = "SELECT u FROM UsuariosTipoUsuarios u WHERE u.idUsuario = :idUsuario"),
    @NamedQuery(name = "UsuariosTipoUsuarios.findByIdTipoUsuario", query = "SELECT u FROM UsuariosTipoUsuarios u WHERE u.idTipoUsuario = :idTipoUsuario"),
    @NamedQuery(name = "UsuariosTipoUsuarios.findByActivo", query = "SELECT u FROM UsuariosTipoUsuarios u WHERE u.activo = :activo")})
public class UsuariosTipoUsuarios implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id_usuarios_tipo_usuarios")
    private int idUsuariosTipoUsuarios;
    @JoinColumn(name = "id_usuario", referencedColumnName = "id_usuario")
    @ManyToOne(optional = false)
    private Usuarios idUsuario;
    @JoinColumn(name = "id_tipo_usuario", referencedColumnName = "id_tipo_usuario")
    @ManyToOne(optional = false)
    private TipoUsuarios idTipoUsuario;
    @Column(name = "activo")
    private Integer activo;

    public UsuariosTipoUsuarios() {
    }

    public UsuariosTipoUsuarios(TipoUsuarios idTipoUsuario) {
        this.idTipoUsuario = idTipoUsuario;
    }

    public UsuariosTipoUsuarios(TipoUsuarios idTipoUsuario, int idUsuariosTipoUsuarios, Usuarios idUsuario) {
        this.idTipoUsuario = idTipoUsuario;
        this.idUsuariosTipoUsuarios = idUsuariosTipoUsuarios;
        this.idUsuario = idUsuario;
    }

    public int getIdUsuariosTipoUsuarios() {
        return idUsuariosTipoUsuarios;
    }

    public void setIdUsuariosTipoUsuarios(int idUsuariosTipoUsuarios) {
        this.idUsuariosTipoUsuarios = idUsuariosTipoUsuarios;
    }

    public Usuarios getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(Usuarios idUsuario) {
        this.idUsuario = idUsuario;
    }

    public TipoUsuarios getIdTipoUsuario() {
        return idTipoUsuario;
    }

    public void setIdTipoUsuario(TipoUsuarios idTipoUsuario) {
        this.idTipoUsuario = idTipoUsuario;
    }

    public Integer getActivo() {
        return activo;
    }

    public void setActivo(Integer activo) {
        this.activo = activo;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idTipoUsuario != null ? idTipoUsuario.hashCode() : 0);
        return hash;
    }

    @Override
    public String toString() {
        return "com.issste.sicabis.ejb.modelo.UsuariosTipoUsuarios[ idTipoUsuario=" + idTipoUsuario + " ]";
    }

}
