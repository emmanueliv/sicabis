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
@Table(name = "solicitudes")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Solicitudes.findAll", query = "SELECT s FROM Solicitudes s"),
    @NamedQuery(name = "Solicitudes.findByIdSolicitud", query = "SELECT s FROM Solicitudes s WHERE s.idSolicitud = :idSolicitud"),
    @NamedQuery(name = "Solicitudes.findByActivo", query = "SELECT s FROM Solicitudes s WHERE s.activo = :activo"),
    @NamedQuery(name = "Solicitudes.findByNumeroSolicitud", query = "SELECT s FROM Solicitudes s WHERE s.numeroSolicitud = :numeroSolicitud"),
    @NamedQuery(name = "Solicitudes.findByFechaSolitud", query = "SELECT s FROM Solicitudes s WHERE s.fechaSolicitud = :fechaSolicitud"),
    @NamedQuery(name = "Solicitudes.findByPeriodoSolicitar", query = "SELECT s FROM Solicitudes s WHERE s.periodoSolicitar = :periodoSolicitar"),
    @NamedQuery(name = "Solicitudes.findByUsuarioAlta", query = "SELECT s FROM Solicitudes s WHERE s.usuarioAlta = :usuarioAlta"),
    @NamedQuery(name = "Solicitudes.findByUsuarioBaja", query = "SELECT s FROM Solicitudes s WHERE s.usuarioBaja = :usuarioBaja"),
    @NamedQuery(name = "Solicitudes.findByUsuarioModificacion", query = "SELECT s FROM Solicitudes s WHERE s.usuarioModificacion = :usuarioModificacion"),
    @NamedQuery(name = "Solicitudes.findByFechaAlta", query = "SELECT s FROM Solicitudes s WHERE s.fechaAlta = :fechaAlta"),
    @NamedQuery(name = "Solicitudes.findByFechaBaja", query = "SELECT s FROM Solicitudes s WHERE s.fechaBaja = :fechaBaja"),
    @NamedQuery(name = "Solicitudes.findByFechaModificacion", query = "SELECT s FROM Solicitudes s WHERE s.fechaModificacion = :fechaModificacion")})
public class Solicitudes implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id_solicitud")
    private Integer idSolicitud;
    @Column(name = "activo")
    private Integer activo;
    @Size(max = 45)
    @Column(name = "numero_solicitud")
    private String numeroSolicitud;
    @Column(name = "fecha_solicitud")
    @Temporal(TemporalType.DATE)
    private Date fechaSolicitud;
    @Size(max = 100)
    @Column(name = "periodo_solicitar")
    private String periodoSolicitar;
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
    @JoinColumn(name = "id_unidades_medicas", referencedColumnName = "id_unidades_medicas")
    @ManyToOne(optional = false)
    private UnidadesMedicas idUnidadesMedicas;
    @JoinColumn(name = "id_tipo_solicitud", referencedColumnName = "id_tipo_solicitud")
    @ManyToOne(optional = false)
    private TipoSolicitud idTipoSolicitud;
    @JoinColumn(name = "id_area", referencedColumnName = "id_area")
    @ManyToOne(optional = false)
    private Area idArea;
    @JoinColumn(name = "id_estatus", referencedColumnName = "id_estatus")
    @ManyToOne(optional = false)
    private Estatus idEstatus;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idSolicitud")
    private List<SolicitudesInsumosPacientes> solicitudesInsumosPacientesList;

    public Solicitudes() {
    }

    public Solicitudes(Integer idSolicitud) {
        this.idSolicitud = idSolicitud;
    }

    public Integer getIdSolicitud() {
        return idSolicitud;
    }

    public void setIdSolicitud(Integer idSolicitud) {
        this.idSolicitud = idSolicitud;
    }

    public Integer getActivo() {
        return activo;
    }

    public void setActivo(Integer activo) {
        this.activo = activo;
    }

    public String getNumeroSolicitud() {
        return numeroSolicitud;
    }

    public void setNumeroSolicitud(String numeroSolicitud) {
        this.numeroSolicitud = numeroSolicitud;
    }

    public Date getFechaSolicitud() {
        return fechaSolicitud;
    }

    public void setFechaSolicitud(Date fechaSolicitud) {
        this.fechaSolicitud = fechaSolicitud;
    }

    public String getPeriodoSolicitar() {
        return periodoSolicitar;
    }

    public void setPeriodoSolicitar(String periodoSolicitar) {
        this.periodoSolicitar = periodoSolicitar;
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

    public UnidadesMedicas getIdUnidadesMedicas() {
        return idUnidadesMedicas;
    }

    public void setIdUnidadesMedicas(UnidadesMedicas idUnidadesMedicas) {
        this.idUnidadesMedicas = idUnidadesMedicas;
    }

    public TipoSolicitud getIdTipoSolicitud() {
        return idTipoSolicitud;
    }

    public void setIdTipoSolicitud(TipoSolicitud idTipoSolicitud) {
        this.idTipoSolicitud = idTipoSolicitud;
    }

    public Area getIdArea() {
        return idArea;
    }

    public void setIdArea(Area idArea) {
        this.idArea = idArea;
    }

    public Estatus getIdEstatus() {
        return idEstatus;
    }

    public void setIdEstatus(Estatus idEstatus) {
        this.idEstatus = idEstatus;
    }

    


    @XmlTransient
    public List<SolicitudesInsumosPacientes> getSolicitudesInsumosPacientesList() {
        return solicitudesInsumosPacientesList;
    }

    public void setSolicitudesInsumosPacientesList(List<SolicitudesInsumosPacientes> solicitudesInsumosPacientesList) {
        this.solicitudesInsumosPacientesList = solicitudesInsumosPacientesList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idSolicitud != null ? idSolicitud.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Solicitudes)) {
            return false;
        }
        Solicitudes other = (Solicitudes) object;
        if ((this.idSolicitud == null && other.idSolicitud != null) || (this.idSolicitud != null && !this.idSolicitud.equals(other.idSolicitud))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.issste.sicabis.ejb.modelo.Solicitudes[ idSolicitud=" + idSolicitud + " ]";
    }
    
}
