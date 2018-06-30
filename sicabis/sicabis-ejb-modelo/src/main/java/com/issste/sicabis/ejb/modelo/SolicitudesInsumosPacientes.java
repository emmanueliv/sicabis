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
@Table(name = "solicitudes_insumos_pacientes")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "SolicitudesInsumosPacientes.findAll", query = "SELECT s FROM SolicitudesInsumosPacientes s"),
    @NamedQuery(name = "SolicitudesInsumosPacientes.findByIdSolicitudesInsumosPacientes", query = "SELECT s FROM SolicitudesInsumosPacientes s WHERE s.idSolicitudesInsumosPacientes = :idSolicitudesInsumosPacientes"),
    @NamedQuery(name = "SolicitudesInsumosPacientes.findByActivo", query = "SELECT s FROM SolicitudesInsumosPacientes s WHERE s.activo = :activo"),
    @NamedQuery(name = "SolicitudesInsumosPacientes.findByCantidad", query = "SELECT s FROM SolicitudesInsumosPacientes s WHERE s.cantidad = :cantidad"),
    @NamedQuery(name = "SolicitudesInsumosPacientes.findByUsuarioAlta", query = "SELECT s FROM SolicitudesInsumosPacientes s WHERE s.usuarioAlta = :usuarioAlta"),
    @NamedQuery(name = "SolicitudesInsumosPacientes.findByUsuarioBaja", query = "SELECT s FROM SolicitudesInsumosPacientes s WHERE s.usuarioBaja = :usuarioBaja"),
    @NamedQuery(name = "SolicitudesInsumosPacientes.findByUsuarioModificacion", query = "SELECT s FROM SolicitudesInsumosPacientes s WHERE s.usuarioModificacion = :usuarioModificacion"),
    @NamedQuery(name = "SolicitudesInsumosPacientes.findByFechaAlta", query = "SELECT s FROM SolicitudesInsumosPacientes s WHERE s.fechaAlta = :fechaAlta"),
    @NamedQuery(name = "SolicitudesInsumosPacientes.findByFechaBaja", query = "SELECT s FROM SolicitudesInsumosPacientes s WHERE s.fechaBaja = :fechaBaja"),
    @NamedQuery(name = "SolicitudesInsumosPacientes.findByFechaModificacion", query = "SELECT s FROM SolicitudesInsumosPacientes s WHERE s.fechaModificacion = :fechaModificacion")})
public class SolicitudesInsumosPacientes implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id_solicitudes_insumos_pacientes")
    private Integer idSolicitudesInsumosPacientes;
    @Column(name = "activo")
    private Integer activo;
    @Column(name = "cantidad")
    private Integer cantidad;
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
    @JoinColumn(name = "id_solicitud", referencedColumnName = "id_solicitud")
    @ManyToOne(optional = false)
    private Solicitudes idSolicitud;
    @JoinColumn(name = "id_paciente", referencedColumnName = "id_paciente")
    @ManyToOne(optional = false)
    private Pacientes idPaciente;
    @JoinColumn(name = "id_insumo", referencedColumnName = "id_insumo")
    @ManyToOne(optional = false)
    private Insumos idInsumo;

    public SolicitudesInsumosPacientes() {
    }

    public SolicitudesInsumosPacientes(Integer idSolicitudesInsumosPacientes) {
        this.idSolicitudesInsumosPacientes = idSolicitudesInsumosPacientes;
    }

    public Integer getIdSolicitudesInsumosPacientes() {
        return idSolicitudesInsumosPacientes;
    }

    public void setIdSolicitudesInsumosPacientes(Integer idSolicitudesInsumosPacientes) {
        this.idSolicitudesInsumosPacientes = idSolicitudesInsumosPacientes;
    }

    public Integer getActivo() {
        return activo;
    }

    public void setActivo(Integer activo) {
        this.activo = activo;
    }

    public Integer getCantidad() {
        return cantidad;
    }

    public void setCantidad(Integer cantidad) {
        this.cantidad = cantidad;
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

    public Solicitudes getIdSolicitud() {
        return idSolicitud;
    }

    public void setIdSolicitud(Solicitudes idSolicitud) {
        this.idSolicitud = idSolicitud;
    }

    public Pacientes getIdPaciente() {
        return idPaciente;
    }

    public void setIdPaciente(Pacientes idPaciente) {
        this.idPaciente = idPaciente;
    }

    public Insumos getIdInsumo() {
        return idInsumo;
    }

    public void setIdInsumo(Insumos idInsumo) {
        this.idInsumo = idInsumo;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idSolicitudesInsumosPacientes != null ? idSolicitudesInsumosPacientes.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof SolicitudesInsumosPacientes)) {
            return false;
        }
        SolicitudesInsumosPacientes other = (SolicitudesInsumosPacientes) object;
        if ((this.idSolicitudesInsumosPacientes == null && other.idSolicitudesInsumosPacientes != null) || (this.idSolicitudesInsumosPacientes != null && !this.idSolicitudesInsumosPacientes.equals(other.idSolicitudesInsumosPacientes))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.issste.sicabis.ejb.modelo.SolicitudesInsumosPacientes[ idSolicitudesInsumosPacientes=" + idSolicitudesInsumosPacientes + " ]";
    }
    
}
