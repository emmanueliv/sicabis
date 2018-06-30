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
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
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
@Table(name = "menu")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Menu.findAll", query = "SELECT m FROM Menu m"),
    @NamedQuery(name = "Menu.findByIdMenu", query = "SELECT m FROM Menu m WHERE m.idMenu = :idMenu"),
    @NamedQuery(name = "Menu.findByActivo", query = "SELECT m FROM Menu m WHERE m.activo = :activo"),
    @NamedQuery(name = "Menu.findByDescripcion", query = "SELECT m FROM Menu m WHERE m.descripcion = :descripcion"),
    @NamedQuery(name = "Menu.findByIdMenuPadre", query = "SELECT m FROM Menu m WHERE m.idMenuPadre = :idMenuPadre and m.activo = :activo"),
    @NamedQuery(name = "Menu.findByFinal1", query = "SELECT m FROM Menu m WHERE m.final1 = :final1"),
    @NamedQuery(name = "Menu.findByNombreArchivo", query = "SELECT m FROM Menu m WHERE m.nombreArchivo = :nombreArchivo"),
    @NamedQuery(name = "Menu.findByUsuarioAlta", query = "SELECT m FROM Menu m WHERE m.usuarioAlta = :usuarioAlta"),
    @NamedQuery(name = "Menu.findByUsuarioBaja", query = "SELECT m FROM Menu m WHERE m.usuarioBaja = :usuarioBaja"),
    @NamedQuery(name = "Menu.findByUsuarioModificacion", query = "SELECT m FROM Menu m WHERE m.usuarioModificacion = :usuarioModificacion"),
    @NamedQuery(name = "Menu.findByFechaAlta", query = "SELECT m FROM Menu m WHERE m.fechaAlta = :fechaAlta"),
    @NamedQuery(name = "Menu.findByFechaBaja", query = "SELECT m FROM Menu m WHERE m.fechaBaja = :fechaBaja"),
    @NamedQuery(name = "Menu.findByFechaModificacion", query = "SELECT m FROM Menu m WHERE m.fechaModificacion = :fechaModificacion")})
public class Menu implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id_menu")
    private Integer idMenu;
    @Column(name = "activo")
    private Integer activo;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 100)
    @Column(name = "descripcion")
    private String descripcion;
    @Column(name = "id_menu_padre")
    private Integer idMenuPadre;
    @Column(name = "final")
    private Integer final1;
    @Size(max = 45)
    @Column(name = "nombre_archivo")
    private String nombreArchivo;
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
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idMenu")
    private List<MenuPerfil> menuPerfilList;
    @JoinColumn(name = "id_tarea", referencedColumnName = "id_tarea")
    @ManyToOne(optional = false)
    private Tareas idTarea;

    public Menu() {
    }

    public Menu(Integer idMenu) {
        this.idMenu = idMenu;
    }

    public Menu(Integer idMenu, String descripcion) {
        this.idMenu = idMenu;
        this.descripcion = descripcion;
    }

    public Integer getIdMenu() {
        return idMenu;
    }

    public void setIdMenu(Integer idMenu) {
        this.idMenu = idMenu;
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

    public Integer getIdMenuPadre() {
        return idMenuPadre;
    }

    public void setIdMenuPadre(Integer idMenuPadre) {
        this.idMenuPadre = idMenuPadre;
    }

    public Integer getFinal1() {
        return final1;
    }

    public void setFinal1(Integer final1) {
        this.final1 = final1;
    }

    public String getNombreArchivo() {
        return nombreArchivo;
    }

    public void setNombreArchivo(String nombreArchivo) {
        this.nombreArchivo = nombreArchivo;
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

    public Tareas getIdTarea() {
        return idTarea;
    }

    public void setIdTarea(Tareas idTarea) {
        this.idTarea = idTarea;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idMenu != null ? idMenu.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Menu)) {
            return false;
        }
        Menu other = (Menu) object;
        if ((this.idMenu == null && other.idMenu != null) || (this.idMenu != null && !this.idMenu.equals(other.idMenu))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.issste.sicabis.ejb.modelo.Menu[ idMenu=" + idMenu + " ]";
    }
    
}
