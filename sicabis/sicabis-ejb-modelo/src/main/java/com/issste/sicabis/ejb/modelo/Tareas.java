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
@Table(name = "tareas")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Tareas.findAll", query = "SELECT t FROM Tareas t WHERE t.fechaBaja is null"),
    @NamedQuery(name = "Tareas.findByIdTarea", query = "SELECT t FROM Tareas t WHERE t.idTarea = :idTarea"),
    @NamedQuery(name = "Tareas.findByDescripcion", query = "SELECT t FROM Tareas t WHERE t.descripcion = :descripcion"),
    @NamedQuery(name = "Tareas.findByUsuarioAlta", query = "SELECT t FROM Tareas t WHERE t.usuarioAlta = :usuarioAlta"),
    @NamedQuery(name = "Tareas.findByUsuarioBaja", query = "SELECT t FROM Tareas t WHERE t.usuarioBaja = :usuarioBaja"),
    @NamedQuery(name = "Tareas.findByUsuarioModificacion", query = "SELECT t FROM Tareas t WHERE t.usuarioModificacion = :usuarioModificacion"),
    @NamedQuery(name = "Tareas.findByFechaAlta", query = "SELECT t FROM Tareas t WHERE t.fechaAlta = :fechaAlta"),
    @NamedQuery(name = "Tareas.findByFechaBaja", query = "SELECT t FROM Tareas t WHERE t.fechaBaja = :fechaBaja"),
    @NamedQuery(name = "Tareas.findByFechaModificacion", query = "SELECT t FROM Tareas t WHERE t.fechaModificacion = :fechaModificacion")})
public class Tareas implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id_tarea")
    private Integer idTarea;
    @Basic(optional = false)
    @NotNull
    //@Size(min = 1, max = 45)
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
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idTarea")
    private List<Menu> menuList;
    @OneToMany(mappedBy = "idTarea")
    private List<Area> areaList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idTarea")
    private List<Estatus> estatusList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idTarea")
    private List<TipoDocumentos> tipoDocumentosList;

    public Tareas() {
    }

    public Tareas(Integer idTarea) {
        this.idTarea = idTarea;
    }

    public Tareas(Integer idTarea, String descripcion) {
        this.idTarea = idTarea;
        this.descripcion = descripcion;
    }

    public Integer getIdTarea() {
        return idTarea;
    }

    public void setIdTarea(Integer idTarea) {
        this.idTarea = idTarea;
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

    @XmlTransient
    public List<Menu> getMenuList() {
        return menuList;
    }

    public void setMenuList(List<Menu> menuList) {
        this.menuList = menuList;
    }

    @XmlTransient
    public List<Area> getAreaList() {
        return areaList;
    }

    public void setAreaList(List<Area> areaList) {
        this.areaList = areaList;
    }

    @XmlTransient
    public List<Estatus> getEstatusList() {
        return estatusList;
    }

    public void setEstatusList(List<Estatus> estatusList) {
        this.estatusList = estatusList;
    }

    @XmlTransient
    public List<TipoDocumentos> getTipoDocumentosList() {
        return tipoDocumentosList;
    }

    public void setTipoDocumentosList(List<TipoDocumentos> tipoDocumentosList) {
        this.tipoDocumentosList = tipoDocumentosList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idTarea != null ? idTarea.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Tareas)) {
            return false;
        }
        Tareas other = (Tareas) object;
        if ((this.idTarea == null && other.idTarea != null) || (this.idTarea != null && !this.idTarea.equals(other.idTarea))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.issste.sicabis.ejb.modelo.Tareas[ idTarea=" + idTarea + " ]";
    }
    
}
