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
@Table(name = "estatus")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Estatus.findAll", query = "SELECT e FROM Estatus e"),
    @NamedQuery(name = "Estatus.findByIdEstatus", query = "SELECT e FROM Estatus e WHERE e.idEstatus = :idEstatus"),
    @NamedQuery(name = "Estatus.findByActivo", query = "SELECT e FROM Estatus e WHERE e.activo = :activo"),
    @NamedQuery(name = "Estatus.findByNombre", query = "SELECT e FROM Estatus e WHERE e.nombre = :nombre"),
    @NamedQuery(name = "Estatus.findByUsuarioAlta", query = "SELECT e FROM Estatus e WHERE e.usuarioAlta = :usuarioAlta"),
    @NamedQuery(name = "Estatus.findByUsuarioBaja", query = "SELECT e FROM Estatus e WHERE e.usuarioBaja = :usuarioBaja"),
    @NamedQuery(name = "Estatus.findByUsuarioModificacion", query = "SELECT e FROM Estatus e WHERE e.usuarioModificacion = :usuarioModificacion"),
    @NamedQuery(name = "Estatus.findByFechaAlta", query = "SELECT e FROM Estatus e WHERE e.fechaAlta = :fechaAlta"),
    @NamedQuery(name = "Estatus.findByFechaBaja", query = "SELECT e FROM Estatus e WHERE e.fechaBaja = :fechaBaja"),
    @NamedQuery(name = "Estatus.findByFechaModificacion", query = "SELECT e FROM Estatus e WHERE e.fechaModificacion = :fechaModificacion")})
public class Estatus implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id_estatus")
    private Integer idEstatus;
    @Column(name = "activo")
    private Integer activo;
    @Basic(optional = false)
    @NotNull
    //@Size(min = 1, max = 45)
    @Column(name = "nombre")
    private String nombre;
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
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idEstatus")
    private List<Remisiones> remisionesList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "estatusFinal")
    private List<EstatusSiguiente> estatusSiguienteList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "estatusInicial")
    private List<EstatusSiguiente> estatusSiguienteList1;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idEstatus")
    private List<OrdenSuministro> ordenSuministroList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idEstatus")
    private List<Cancelaciones> cancelacionesList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idEstatus")
    private List<Contratos> contratosList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idEstatus")
    private List<Rcb> rcbList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idEstatus")
    private List<Cr> crList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idEstatus")
    private List<Rescisiones> rescisionesList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idEstatus")
    private List<Procedimientos> procedimientosList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idEstatus")
    private List<Fallos> fallosList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idEstatus")
    private List<Solicitudes> solicitudesList;
    @JoinColumn(name = "id_tarea", referencedColumnName = "id_tarea")
    @ManyToOne(optional = false)
    private Tareas idTarea;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idEstatus")
    private List<Dpn> dpnList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idEstatus")
    private List<AlertasEnvio> alertasEnvioList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idEstatus")
    private List<AlertasDpn> alertasDpnList;

    public Estatus() {
    }

    public Estatus(Integer idEstatus) {
        this.idEstatus = idEstatus;
    }

    public Estatus(Integer idEstatus, String nombre) {
        this.idEstatus = idEstatus;
        this.nombre = nombre;
    }

    public Integer getIdEstatus() {
        return idEstatus;
    }

    public void setIdEstatus(Integer idEstatus) {
        this.idEstatus = idEstatus;
    }

    public Integer getActivo() {
        return activo;
    }

    public void setActivo(Integer activo) {
        this.activo = activo;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
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
    public List<Remisiones> getRemisionesList() {
        return remisionesList;
    }

    public void setRemisionesList(List<Remisiones> remisionesList) {
        this.remisionesList = remisionesList;
    }

    @XmlTransient
    public List<EstatusSiguiente> getEstatusSiguienteList() {
        return estatusSiguienteList;
    }

    public void setEstatusSiguienteList(List<EstatusSiguiente> estatusSiguienteList) {
        this.estatusSiguienteList = estatusSiguienteList;
    }

    @XmlTransient
    public List<EstatusSiguiente> getEstatusSiguienteList1() {
        return estatusSiguienteList1;
    }

    public void setEstatusSiguienteList1(List<EstatusSiguiente> estatusSiguienteList1) {
        this.estatusSiguienteList1 = estatusSiguienteList1;
    }

    @XmlTransient
    public List<OrdenSuministro> getOrdenSuministroList() {
        return ordenSuministroList;
    }

    public void setOrdenSuministroList(List<OrdenSuministro> ordenSuministroList) {
        this.ordenSuministroList = ordenSuministroList;
    }

    @XmlTransient
    public List<Cancelaciones> getCancelacionesList() {
        return cancelacionesList;
    }

    public void setCancelacionesList(List<Cancelaciones> cancelacionesList) {
        this.cancelacionesList = cancelacionesList;
    }

    @XmlTransient
    public List<Contratos> getContratosList() {
        return contratosList;
    }

    public void setContratosList(List<Contratos> contratosList) {
        this.contratosList = contratosList;
    }

    @XmlTransient
    public List<Rcb> getRcbList() {
        return rcbList;
    }

    public void setRcbList(List<Rcb> rcbList) {
        this.rcbList = rcbList;
    }

    @XmlTransient
    public List<Rescisiones> getRescisionesList() {
        return rescisionesList;
    }

    public void setRescisionesList(List<Rescisiones> rescisionesList) {
        this.rescisionesList = rescisionesList;
    }

    @XmlTransient
    public List<Procedimientos> getProcedimientosList() {
        return procedimientosList;
    }

    public void setProcedimientosList(List<Procedimientos> procedimientosList) {
        this.procedimientosList = procedimientosList;
    }

    @XmlTransient
    public List<Fallos> getFallosList() {
        return fallosList;
    }

    public void setFallosList(List<Fallos> fallosList) {
        this.fallosList = fallosList;
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

    public List<Cr> getCrList() {
        return crList;
    }

    public void setCrList(List<Cr> crList) {
        this.crList = crList;
    }

    @XmlTransient
    public List<Dpn> getDpnList() {
        return dpnList;
    }

    public void setDpnList(List<Dpn> dpnList) {
        this.dpnList = dpnList;
    }

    @XmlTransient
    public List<AlertasEnvio> getAlertasEnvioList() {
        return alertasEnvioList;
    }

    public void setAlertasEnvioList(List<AlertasEnvio> alertasEnvioList) {
        this.alertasEnvioList = alertasEnvioList;
    }

    @XmlTransient
    public List<AlertasDpn> getAlertasDpnList() {
        return alertasDpnList;
    }

    public void setAlertasDpnList(List<AlertasDpn> alertasDpnList) {
        this.alertasDpnList = alertasDpnList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idEstatus != null ? idEstatus.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Estatus)) {
            return false;
        }
        Estatus other = (Estatus) object;
        if ((this.idEstatus == null && other.idEstatus != null) || (this.idEstatus != null && !this.idEstatus.equals(other.idEstatus))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.issste.sicabis.ejb.modelo.Estatus[ idEstatus=" + idEstatus + " ]";
    }
    
}
