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
@Table(name = "planeacion")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Planeacion.findAll", query = "SELECT p FROM Planeacion p"),
    @NamedQuery(name = "Planeacion.findByIdPlaneacion", query = "SELECT p FROM Planeacion p WHERE p.idPlaneacion = :idPlaneacion"),
    @NamedQuery(name = "Planeacion.findByActivo", query = "SELECT p FROM Planeacion p WHERE p.activo = :activo"),
    @NamedQuery(name = "Planeacion.findByNumeroPlaneacion", query = "SELECT p FROM Planeacion p WHERE p.numeroPlaneacion = :numeroPlaneacion"),
    @NamedQuery(name = "Planeacion.findByFechaInicial", query = "SELECT p FROM Planeacion p WHERE p.fechaInicial = :fechaInicial"),
    @NamedQuery(name = "Planeacion.findByFechaFinal", query = "SELECT p FROM Planeacion p WHERE p.fechaFinal = :fechaFinal"),
    @NamedQuery(name = "Planeacion.findByPeriodoProyeccion", query = "SELECT p FROM Planeacion p WHERE p.periodoProyeccion = :periodoProyeccion"),
    @NamedQuery(name = "Planeacion.findByMesesProyeccion", query = "SELECT p FROM Planeacion p WHERE p.mesesProyeccion = :mesesProyeccion"),
    @NamedQuery(name = "Planeacion.findByUsuarioAlta", query = "SELECT p FROM Planeacion p WHERE p.usuarioAlta = :usuarioAlta"),
    @NamedQuery(name = "Planeacion.findByUsuarioBaja", query = "SELECT p FROM Planeacion p WHERE p.usuarioBaja = :usuarioBaja"),
    @NamedQuery(name = "Planeacion.findByUsuarioModificacion", query = "SELECT p FROM Planeacion p WHERE p.usuarioModificacion = :usuarioModificacion"),
    @NamedQuery(name = "Planeacion.findByFechaAlta", query = "SELECT p FROM Planeacion p WHERE p.fechaAlta = :fechaAlta"),
    @NamedQuery(name = "Planeacion.findByFechaBaja", query = "SELECT p FROM Planeacion p WHERE p.fechaBaja = :fechaBaja"),
    @NamedQuery(name = "Planeacion.findByFechaModificacion", query = "SELECT p FROM Planeacion p WHERE p.fechaModificacion = :fechaModificacion")})
public class Planeacion implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id_planeacion")
    private Integer idPlaneacion;
    @Column(name = "activo")
    private Integer activo;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 100)
    @Column(name = "numero_planeacion")
    private String numeroPlaneacion;
    @Column(name = "fecha_inicial")
    @Temporal(TemporalType.DATE)
    private Date fechaInicial;
    @Column(name = "fecha_final")
    @Temporal(TemporalType.DATE)
    private Date fechaFinal;
    @Size(max = 45)
    @Column(name = "periodo_proyeccion")
    private String periodoProyeccion;
    @Column(name = "meses_proyeccion")
    private Integer mesesProyeccion;
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
    @JoinColumn(name = "id_area", referencedColumnName = "id_area")
    @ManyToOne(optional = false)
    private Area idArea;
    @JoinColumn(name = "id_tipo_solicitud", referencedColumnName = "id_tipo_solicitud")
    @ManyToOne(optional = false)
    private TipoSolicitud idTipoSolicitud;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idPlaneacion")
    private List<PlaneacionDetalle> planeacionDetalleList;

    public Planeacion() {
    }

    public Planeacion(Integer idPlaneacion) {
        this.idPlaneacion = idPlaneacion;
    }

    public Planeacion(Integer idPlaneacion, String numeroPlaneacion) {
        this.idPlaneacion = idPlaneacion;
        this.numeroPlaneacion = numeroPlaneacion;
    }

    public Integer getIdPlaneacion() {
        return idPlaneacion;
    }

    public void setIdPlaneacion(Integer idPlaneacion) {
        this.idPlaneacion = idPlaneacion;
    }

    public Integer getActivo() {
        return activo;
    }

    public void setActivo(Integer activo) {
        this.activo = activo;
    }

    public String getNumeroPlaneacion() {
        return numeroPlaneacion;
    }

    public void setNumeroPlaneacion(String numeroPlaneacion) {
        this.numeroPlaneacion = numeroPlaneacion;
    }

    public Date getFechaInicial() {
        return fechaInicial;
    }

    public void setFechaInicial(Date fechaInicial) {
        this.fechaInicial = fechaInicial;
    }

    public Date getFechaFinal() {
        return fechaFinal;
    }

    public void setFechaFinal(Date fechaFinal) {
        this.fechaFinal = fechaFinal;
    }

    public String getPeriodoProyeccion() {
        return periodoProyeccion;
    }

    public void setPeriodoProyeccion(String periodoProyeccion) {
        this.periodoProyeccion = periodoProyeccion;
    }

    public Integer getMesesProyeccion() {
        return mesesProyeccion;
    }

    public void setMesesProyeccion(Integer mesesProyeccion) {
        this.mesesProyeccion = mesesProyeccion;
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

    public Area getIdArea() {
        return idArea;
    }

    public void setIdArea(Area idArea) {
        this.idArea = idArea;
    }

    public TipoSolicitud getIdTipoSolicitud() {
        return idTipoSolicitud;
    }

    public void setIdTipoSolicitud(TipoSolicitud idTipoSolicitud) {
        this.idTipoSolicitud = idTipoSolicitud;
    }
    
    



    @XmlTransient
    public List<PlaneacionDetalle> getPlaneacionDetalleList() {
        return planeacionDetalleList;
    }

    public void setPlaneacionDetalleList(List<PlaneacionDetalle> planeacionDetalleList) {
        this.planeacionDetalleList = planeacionDetalleList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idPlaneacion != null ? idPlaneacion.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Planeacion)) {
            return false;
        }
        Planeacion other = (Planeacion) object;
        if ((this.idPlaneacion == null && other.idPlaneacion != null) || (this.idPlaneacion != null && !this.idPlaneacion.equals(other.idPlaneacion))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.issste.sicabis.ejb.modelo.Planeacion[ idPlaneacion=" + idPlaneacion + " ]";
    }
    
}
