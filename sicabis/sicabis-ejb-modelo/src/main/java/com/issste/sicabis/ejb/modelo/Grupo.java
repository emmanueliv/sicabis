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

/**
 *
 * @author erik
 */
@Entity
@Table(name = "grupo")
@NamedQueries({
    @NamedQuery(name = "Grupo.findAll", query = "SELECT g FROM Grupo g"),
    @NamedQuery(name = "Grupo.findByIdGrupo", query = "SELECT g FROM Grupo g WHERE g.idGrupo = :idGrupo"),
    @NamedQuery(name = "Grupo.findByActivo", query = "SELECT g FROM Grupo g WHERE g.activo = :activo"),
    @NamedQuery(name = "Grupo.findByGrupo", query = "SELECT g FROM Grupo g WHERE g.grupo = :grupo"),
    @NamedQuery(name = "Grupo.findByDescripcion", query = "SELECT g FROM Grupo g WHERE g.descripcion = :descripcion"),
    @NamedQuery(name = "Grupo.findByUsuarioAlta", query = "SELECT g FROM Grupo g WHERE g.usuarioAlta = :usuarioAlta"),
    @NamedQuery(name = "Grupo.findByUsuarioBaja", query = "SELECT g FROM Grupo g WHERE g.usuarioBaja = :usuarioBaja"),
    @NamedQuery(name = "Grupo.findByUsuarioModificacion", query = "SELECT g FROM Grupo g WHERE g.usuarioModificacion = :usuarioModificacion"),
    @NamedQuery(name = "Grupo.findByFechaAlta", query = "SELECT g FROM Grupo g WHERE g.fechaAlta = :fechaAlta"),
    @NamedQuery(name = "Grupo.findByFechaBaja", query = "SELECT g FROM Grupo g WHERE g.fechaBaja = :fechaBaja"),
    @NamedQuery(name = "Grupo.findByFechaModificacion", query = "SELECT g FROM Grupo g WHERE g.fechaModificacion = :fechaModificacion")})
public class Grupo implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id_grupo")
    private Integer idGrupo;
    @Column(name = "activo")
    private Integer activo;
    @Basic(optional = false)
    @NotNull    
    @Column(name = "grupo")
    private String grupo;
    @Basic(optional = false)
    @NotNull   
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
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idGrupo")
    private List<Insumos> insumosList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idGrupo")
    private List<RcbInsumos> rcbInsumosList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idGrupo")
    private List<RcbGrupo> rcbGrupoList;

    public Grupo() {
    }

    public Grupo(Integer idGrupo) {
        this.idGrupo = idGrupo;
    }

    public Grupo(Integer idGrupo, String grupo, String descripcion) {
        this.idGrupo = idGrupo;
        this.grupo = grupo;
        this.descripcion = descripcion;
    }

    public Integer getIdGrupo() {
        return idGrupo;
    }

    public void setIdGrupo(Integer idGrupo) {
        this.idGrupo = idGrupo;
    }

    public Integer getActivo() {
        return activo;
    }

    public void setActivo(Integer activo) {
        this.activo = activo;
    }

    public String getGrupo() {
        return grupo;
    }

    public void setGrupo(String grupo) {
        this.grupo = grupo;
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

    public List<Insumos> getInsumosList() {
        return insumosList;
    }

    public void setInsumosList(List<Insumos> insumosList) {
        this.insumosList = insumosList;
    }

    public List<RcbInsumos> getRcbInsumosList() {
        return rcbInsumosList;
    }

    public void setRcbInsumosList(List<RcbInsumos> rcbInsumosList) {
        this.rcbInsumosList = rcbInsumosList;
    }

    public List<RcbGrupo> getRcbGrupoList() {
        return rcbGrupoList;
    }

    public void setRcbGrupoList(List<RcbGrupo> rcbGrupoList) {
        this.rcbGrupoList = rcbGrupoList;
    }
    
    

    
    
    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idGrupo != null ? idGrupo.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Grupo)) {
            return false;
        }
        Grupo other = (Grupo) object;
        if ((this.idGrupo == null && other.idGrupo != null) || (this.idGrupo != null && !this.idGrupo.equals(other.idGrupo))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.issste.sicabis.ejb.modelo.Grupo[ idGrupo=" + idGrupo + " ]";
    }
    
}
