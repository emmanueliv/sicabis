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
@Table(name = "menu_perfil")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "MenuPerfil.findAll", query = "SELECT m FROM MenuPerfil m"),
    @NamedQuery(name = "MenuPerfil.findByIdMenuPerfil", query = "SELECT m FROM MenuPerfil m WHERE m.idMenuPerfil = :idMenuPerfil"),
    @NamedQuery(name = "MenuPerfil.findByActivo", query = "SELECT m FROM MenuPerfil m WHERE m.activo = :activo"),
    @NamedQuery(name = "MenuPerfil.findByUsuarioAlta", query = "SELECT m FROM MenuPerfil m WHERE m.usuarioAlta = :usuarioAlta"),
    @NamedQuery(name = "MenuPerfil.findByUsuarioBaja", query = "SELECT m FROM MenuPerfil m WHERE m.usuarioBaja = :usuarioBaja"),
    @NamedQuery(name = "MenuPerfil.findByUsuarioModificacion", query = "SELECT m FROM MenuPerfil m WHERE m.usuarioModificacion = :usuarioModificacion"),
    @NamedQuery(name = "MenuPerfil.findByFechaAlta", query = "SELECT m FROM MenuPerfil m WHERE m.fechaAlta = :fechaAlta"),
    @NamedQuery(name = "MenuPerfil.findByFechaBaja", query = "SELECT m FROM MenuPerfil m WHERE m.fechaBaja = :fechaBaja"),
    @NamedQuery(name = "MenuPerfil.findByFechaModificacion", query = "SELECT m FROM MenuPerfil m WHERE m.fechaModificacion = :fechaModificacion")})
public class MenuPerfil implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id_menu_perfil")
    private Integer idMenuPerfil;
    @Column(name = "activo")
    private Integer activo;
    @Column(name = "consulta")
    private Integer consulta;
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
    @JoinColumn(name = "id_perfil", referencedColumnName = "id_perfil")
    @ManyToOne(optional = false)
    private Perfiles idPerfil;
    @JoinColumn(name = "id_menu", referencedColumnName = "id_menu")
    @ManyToOne(optional = false)
    private Menu idMenu;

    public MenuPerfil() {
    }

    public MenuPerfil(Integer idMenuPerfil) {
        this.idMenuPerfil = idMenuPerfil;
    }

    public Integer getIdMenuPerfil() {
        return idMenuPerfil;
    }

    public void setIdMenuPerfil(Integer idMenuPerfil) {
        this.idMenuPerfil = idMenuPerfil;
    }

    public Integer getActivo() {
        return activo;
    }

    public void setActivo(Integer activo) {
        this.activo = activo;
    }

    public Integer getConsulta() {
        return consulta;
    }

    public void setConsulta(Integer consulta) {
        this.consulta = consulta;
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

    public Perfiles getIdPerfil() {
        return idPerfil;
    }

    public void setIdPerfil(Perfiles idPerfil) {
        this.idPerfil = idPerfil;
    }

    public Menu getIdMenu() {
        return idMenu;
    }

    public void setIdMenu(Menu idMenu) {
        this.idMenu = idMenu;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idMenuPerfil != null ? idMenuPerfil.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof MenuPerfil)) {
            return false;
        }
        MenuPerfil other = (MenuPerfil) object;
        if ((this.idMenuPerfil == null && other.idMenuPerfil != null) || (this.idMenuPerfil != null && !this.idMenuPerfil.equals(other.idMenuPerfil))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.issste.sicabis.ejb.modelo.MenuPerfil[ idMenuPerfil=" + idMenuPerfil + " ]";
    }
    
}
