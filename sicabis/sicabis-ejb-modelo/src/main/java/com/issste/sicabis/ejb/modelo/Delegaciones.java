/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.issste.sicabis.ejb.modelo;

import com.issste.sicabis.ejb.service.silodisa.modelo.MapaEjecutivoDispDelegaciones;
import com.issste.sicabis.ejb.service.silodisa.modelo.MapaEjecutivoDispG40;
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
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author kriosoft
 */
@Entity
@Table(name = "delegaciones")
@NamedQueries({
    @NamedQuery(name = "Delegaciones.findAll", query = "SELECT d FROM Delegaciones d"),
    @NamedQuery(name = "Delegaciones.findByIdDelegacion", query = "SELECT d FROM Delegaciones d WHERE d.idDelegacion = :idDelegacion"),
    @NamedQuery(name = "Delegaciones.findByNombreDelegacion", query = "SELECT d FROM Delegaciones d WHERE d.nombreDelegacion = :nombreDelegacion"),
    @NamedQuery(name = "Delegaciones.findByUsuarioAlta", query = "SELECT d FROM Delegaciones d WHERE d.usuarioAlta = :usuarioAlta"),
    @NamedQuery(name = "Delegaciones.findByUsuarioBaja", query = "SELECT d FROM Delegaciones d WHERE d.usuarioBaja = :usuarioBaja"),
    @NamedQuery(name = "Delegaciones.findByUsuarioModificacion", query = "SELECT d FROM Delegaciones d WHERE d.usuarioModificacion = :usuarioModificacion"),
    @NamedQuery(name = "Delegaciones.findByFechaAlta", query = "SELECT d FROM Delegaciones d WHERE d.fechaAlta = :fechaAlta"),
    @NamedQuery(name = "Delegaciones.findByFechaBaja", query = "SELECT d FROM Delegaciones d WHERE d.fechaBaja = :fechaBaja"),
    @NamedQuery(name = "Delegaciones.findByFechaModificaciones", query = "SELECT d FROM Delegaciones d WHERE d.fechaModificaciones = :fechaModificaciones"),
    @NamedQuery(name = "Delegaciones.findByActivo", query = "SELECT d FROM Delegaciones d WHERE d.activo = :activo")})
public class Delegaciones implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id_delegacion")
    private Integer idDelegacion;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 100)
    @Column(name = "nombre_delegacion")
    private String nombreDelegacion;
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
    @Column(name = "fecha_modificaciones")
    @Temporal(TemporalType.DATE)
    private Date fechaModificaciones;
    @Column(name = "activo")
    private Integer activo;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idDelegacion")
    private List<UnidadesMedicas> unidadesMedicasList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idDelegacion")
    private List<PorcentajeDelegacion> porcentajeDelegacionList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idDelegacion")
    private List<PorcentajeDelegacionHistorico> porcentajeDelegacionHistoricoList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idDelegacion")
    private List<DetalleDelegacion> detalleDelegacionList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idDelegacion")
    private List<MapaEjecutivoDispDelegaciones> mapaEjecutivoDispDelegacionesList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idDelegacion")
    private List<MapaEjecutivoDispG40> mapaEjecutivoDispG40List;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idDelegacion")
    private List<Usuarios> usuariosList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idDelegacion")
    private List<ContactosAlertasDpn> contactosAlertasDpnList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idDelegacion")
    private List<Ur> urList;

    public Delegaciones() {
    }

    public Delegaciones(Integer idDelegacion) {
        this.idDelegacion = idDelegacion;
    }

    public Delegaciones(Integer idDelegacion, String nombreDelegacion) {
        this.idDelegacion = idDelegacion;
        this.nombreDelegacion = nombreDelegacion;
    }

    public Integer getIdDelegacion() {
        return idDelegacion;
    }

    public void setIdDelegacion(Integer idDelegacion) {
        this.idDelegacion = idDelegacion;
    }

    public String getNombreDelegacion() {
        return nombreDelegacion;
    }

    public void setNombreDelegacion(String nombreDelegacion) {
        this.nombreDelegacion = nombreDelegacion;
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

    public Date getFechaModificaciones() {
        return fechaModificaciones;
    }

    public void setFechaModificaciones(Date fechaModificaciones) {
        this.fechaModificaciones = fechaModificaciones;
    }

    public Integer getActivo() {
        return activo;
    }

    public void setActivo(Integer activo) {
        this.activo = activo;
    }

    public List<UnidadesMedicas> getUnidadesMedicasList() {
        return unidadesMedicasList;
    }

    public void setUnidadesMedicasList(List<UnidadesMedicas> unidadesMedicasList) {
        this.unidadesMedicasList = unidadesMedicasList;
    }

    public List<PorcentajeDelegacion> getPorcentajeDelegacionList() {
        return porcentajeDelegacionList;
    }

    public void setPorcentajeDelegacionList(List<PorcentajeDelegacion> porcentajeDelegacionList) {
        this.porcentajeDelegacionList = porcentajeDelegacionList;
    }

    public List<DetalleDelegacion> getDetalleDelegacionList() {
        return detalleDelegacionList;
    }

    public void setDetalleDelegacionList(List<DetalleDelegacion> detalleDelegacionList) {
        this.detalleDelegacionList = detalleDelegacionList;
    }

    public List<MapaEjecutivoDispDelegaciones> getMapaEjecutivoDispDelegacionesList() {
        return mapaEjecutivoDispDelegacionesList;
    }

    public void setMapaEjecutivoDispDelegacionesList(List<MapaEjecutivoDispDelegaciones> mapaEjecutivoDispDelegacionesList) {
        this.mapaEjecutivoDispDelegacionesList = mapaEjecutivoDispDelegacionesList;
    }

    public List<MapaEjecutivoDispG40> getMapaEjecutivoDispG40List() {
        return mapaEjecutivoDispG40List;
    }

    public void setMapaEjecutivoDispG40List(List<MapaEjecutivoDispG40> mapaEjecutivoDispG40List) {
        this.mapaEjecutivoDispG40List = mapaEjecutivoDispG40List;
    }

    public List<Usuarios> getUsuariosList() {
        return usuariosList;
    }

    public void setUsuariosList(List<Usuarios> usuariosList) {
        this.usuariosList = usuariosList;
    }

    public List<ContactosAlertasDpn> getContactosAlertasDpnList() {
        return contactosAlertasDpnList;
    }

    public void setContactosAlertasDpnList(List<ContactosAlertasDpn> contactosAlertasDpnList) {
        this.contactosAlertasDpnList = contactosAlertasDpnList;
    }

    @XmlTransient
    public List<PorcentajeDelegacionHistorico> getPorcentajeDelegacionHistoricoList() {
        return porcentajeDelegacionHistoricoList;
    }

    public void setPorcentajeDelegacionHistoricoList(List<PorcentajeDelegacionHistorico> porcentajeDelegacionHistoricoList) {
        this.porcentajeDelegacionHistoricoList = porcentajeDelegacionHistoricoList;
    }

    @XmlTransient
    public List<Ur> getUrList() {
        return urList;
    }

    public void setUrList(List<Ur> urList) {
        this.urList = urList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idDelegacion != null ? idDelegacion.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Delegaciones)) {
            return false;
        }
        Delegaciones other = (Delegaciones) object;
        if ((this.idDelegacion == null && other.idDelegacion != null) || (this.idDelegacion != null && !this.idDelegacion.equals(other.idDelegacion))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.issste.sicabis.ejb.modelo.Delegaciones[ idDelegacion=" + idDelegacion + " ]";
    }

}
