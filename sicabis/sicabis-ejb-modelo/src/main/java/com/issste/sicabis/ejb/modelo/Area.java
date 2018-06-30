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
@Table(name = "area")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Area.findAll", query = "SELECT a FROM Area a WHERE a.activo = 1"),
    @NamedQuery(name = "Area.findByIdArea", query = "SELECT a FROM Area a WHERE a.idArea = :idArea"),
    @NamedQuery(name = "Area.findByActivo", query = "SELECT a FROM Area a WHERE a.activo = :activo"),
    @NamedQuery(name = "Area.findByNombreArea", query = "SELECT a FROM Area a WHERE a.nombreArea = :nombreArea AND a.activo = 1"),
    @NamedQuery(name = "Area.findByUsuarioAlta", query = "SELECT a FROM Area a WHERE a.usuarioAlta = :usuarioAlta"),
    @NamedQuery(name = "Area.findByUsuarioBaja", query = "SELECT a FROM Area a WHERE a.usuarioBaja = :usuarioBaja"),
    @NamedQuery(name = "Area.findByUsuarioModificacion", query = "SELECT a FROM Area a WHERE a.usuarioModificacion = :usuarioModificacion"),
    @NamedQuery(name = "Area.findByFechaAlta", query = "SELECT a FROM Area a WHERE a.fechaAlta = :fechaAlta"),
    @NamedQuery(name = "Area.findByFechaBaja", query = "SELECT a FROM Area a WHERE a.fechaBaja = :fechaBaja"),
    @NamedQuery(name = "Area.findByFechaModificacion", query = "SELECT a FROM Area a WHERE a.fechaModificacion = :fechaModificacion")})
public class Area implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id_area")
    private Integer idArea;
    @Column(name = "activo")
    private Integer activo;
    @Basic(optional = false)
    @NotNull
    //@Size(min = 1, max = 45)
    @Column(name = "nombre_area")
    private String nombreArea;
    @Column(name = "maestra")
    private Integer maestra;
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
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idArea")
    private List<Usuarios> usuariosList;
    @JoinColumn(name = "id_tarea", referencedColumnName = "id_tarea")
    @ManyToOne
    private Tareas idTarea;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idArea")
    private List<Solicitudes> solicitudesList;    
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idArea")
    private List<PeriodoArea> periodoAreaList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idArea")
    private List<Planeacion> planeacionList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idArea")
    private List<AsignacionInsumos> asignacionInsumosList;  
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idArea")
    private List<Rcb> rcbList;     
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idArea")
    private List<Cr> crList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idArea")
    private List<PeriodoMes> periodoMesList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idArea")
    private List<Jefatura> jefaturaList;
    
    public Area() {
    }

    public Area(Integer idArea) {
        this.idArea = idArea;
    }

    public Area(Integer idArea, String nombreArea) {
        this.idArea = idArea;
        this.nombreArea = nombreArea;
    }

    public Integer getIdArea() {
        return idArea;
    }

    public void setIdArea(Integer idArea) {
        this.idArea = idArea;
    }

    public Integer getActivo() {
        return activo;
    }

    public void setActivo(Integer activo) {
        this.activo = activo;
    }

    public String getNombreArea() {
        return nombreArea;
    }

    public void setNombreArea(String nombreArea) {
        this.nombreArea = nombreArea;
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
    public List<Usuarios> getUsuariosList() {
        return usuariosList;
    }

    public void setUsuariosList(List<Usuarios> usuariosList) {
        this.usuariosList = usuariosList;
    }

    public Tareas getIdTarea() {
        return idTarea;
    }

    public void setIdTarea(Tareas idTarea) {
        this.idTarea = idTarea;
    }


    public List<Solicitudes> getSolicitudesList() {
        return solicitudesList;
    }

    public void setSolicitudesList(List<Solicitudes> solicitudesList) {
        this.solicitudesList = solicitudesList;
    }


    public List<PeriodoArea> getPeriodoAreaList() {
        return periodoAreaList;
    }

    public void setPeriodoAreaList(List<PeriodoArea> periodoAreaList) {
        this.periodoAreaList = periodoAreaList;
    }

    public List<Planeacion> getPlaneacionList() {
        return planeacionList;
    }

    public void setPlaneacionList(List<Planeacion> planeacionList) {
        this.planeacionList = planeacionList;
    }

    public Integer getMaestra() {
        return maestra;
    }

    public void setMaestra(Integer maestra) {
        this.maestra = maestra;
    }

    public Integer getIdPadre() {
        return idPadre;
    }

    public void setIdPadre(Integer idPadre) {
        this.idPadre = idPadre;
    }

    public List<AsignacionInsumos> getAsignacionInsumosList() {
        return asignacionInsumosList;
    }

    public void setAsignacionInsumosList(List<AsignacionInsumos> asignacionInsumosList) {
        this.asignacionInsumosList = asignacionInsumosList;
    }

    public List<Rcb> getRcbList() {
        return rcbList;
    }

    public void setRcbList(List<Rcb> rcbList) {
        this.rcbList = rcbList;
    }

    public List<Cr> getCrList() {
        return crList;
    }

    public void setCrList(List<Cr> crList) {
        this.crList = crList;
    }

    @XmlTransient
    public List<PeriodoMes> getPeriodoMesList() {
        return periodoMesList;
    }

    public void setPeriodoMesList(List<PeriodoMes> periodoMesList) {
        this.periodoMesList = periodoMesList;
    }

    @XmlTransient
    public List<Jefatura> getJefaturaList() {
        return jefaturaList;
    }

    public void setJefaturaList(List<Jefatura> jefaturaList) {
        this.jefaturaList = jefaturaList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idArea != null ? idArea.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Area)) {
            return false;
        }
        Area other = (Area) object;
        if ((this.idArea == null && other.idArea != null) || (this.idArea != null && !this.idArea.equals(other.idArea))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.issste.sicabis.ejb.modelo.Area[ idArea=" + idArea + " ]";
    }
    
}
