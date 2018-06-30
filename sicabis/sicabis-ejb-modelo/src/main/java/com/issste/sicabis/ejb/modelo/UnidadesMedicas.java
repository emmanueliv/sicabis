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
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author kriosoft
 */
@Entity
@Table(name = "unidades_medicas")
@NamedQueries({
    @NamedQuery(name = "UnidadesMedicas.findAll", query = "SELECT u FROM UnidadesMedicas u"),
    @NamedQuery(name = "UnidadesMedicas.findByConcentradora", query = "SELECT u FROM UnidadesMedicas u WHERE u.concentradora = :concentradora AND u.activo = 1"),
    @NamedQuery(name = "UnidadesMedicas.findByIdUnidadesMedicas", query = "SELECT u FROM UnidadesMedicas u WHERE u.idUnidadesMedicas = :idUnidadesMedicas"),
    @NamedQuery(name = "UnidadesMedicas.findByNombre", query = "SELECT u FROM UnidadesMedicas u WHERE u.nombre = :nombre"),
    @NamedQuery(name = "UnidadesMedicas.findByUsuarioAlta", query = "SELECT u FROM UnidadesMedicas u WHERE u.usuarioAlta = :usuarioAlta"),
    @NamedQuery(name = "UnidadesMedicas.findByUsuarioBaja", query = "SELECT u FROM UnidadesMedicas u WHERE u.usuarioBaja = :usuarioBaja"),
    @NamedQuery(name = "UnidadesMedicas.findByUsuarioModificacion", query = "SELECT u FROM UnidadesMedicas u WHERE u.usuarioModificacion = :usuarioModificacion"),
    @NamedQuery(name = "UnidadesMedicas.findByFechaAlta", query = "SELECT u FROM UnidadesMedicas u WHERE u.fechaAlta = :fechaAlta"),
    @NamedQuery(name = "UnidadesMedicas.findByFechaBaja", query = "SELECT u FROM UnidadesMedicas u WHERE u.fechaBaja = :fechaBaja"),
    @NamedQuery(name = "UnidadesMedicas.findByFechaModificaciones", query = "SELECT u FROM UnidadesMedicas u WHERE u.fechaModificaciones = :fechaModificaciones")})
public class UnidadesMedicas implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id_unidades_medicas")
    private Integer idUnidadesMedicas;
    @Basic(optional = false)
    @NotNull
    //@Size(min = 1, max = 100)
    @Column(name = "nombre")
    private String nombre;
    @Basic(optional = false)
    @NotNull
    @Column(name = "clave_umu")
    private String claveUmu;
    @Column(name = "id_padre")
    private Integer idPadre;
    @Column(name = "activo")
    private Integer activo;
    @Basic(optional = false)
    @NotNull
    @Column(name = "clave_presupuestal")
    private String clavePresupuestal;
    @Column(name = "concentradora")
    private Integer concentradora;
    @Column(name = "hospital_regional")
    private Integer hospitalRegional;
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
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idUnidadesMedicas")
    private List<Contratos> contratosList;
    @JoinColumn(name = "id_delegacion", referencedColumnName = "id_delegacion")
    @ManyToOne(optional = false)
    private Delegaciones idDelegacion;
    @JoinColumn(name = "id_nivel", referencedColumnName = "id_nivel")
    @ManyToOne(optional = false)
    private Nivel idNivel;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idUnidadesMedicas")
    private List<DpnInsumos> dpnInsumosList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idUnidadesMedicas")
    private List<UnidadInsumosDpn> unidadInsumoDpnList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idUnidadMedica")
    private List<ContactosAlertasDpn> contactosAlertasDpnList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idUnidadMedica")
    private List<Usuarios> usuariosList;

    public UnidadesMedicas() {
    }

    public UnidadesMedicas(Integer idUnidadesMedicas) {
        this.idUnidadesMedicas = idUnidadesMedicas;
    }

    public UnidadesMedicas(Integer idUnidadesMedicas, String nombre) {
        this.idUnidadesMedicas = idUnidadesMedicas;
        this.nombre = nombre;
    }

    public Integer getIdUnidadesMedicas() {
        return idUnidadesMedicas;
    }

    public void setIdUnidadesMedicas(Integer idUnidadesMedicas) {
        this.idUnidadesMedicas = idUnidadesMedicas;
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

    @XmlTransient
    public List<Contratos> getContratosList() {
        return contratosList;
    }

    public void setContratosList(List<Contratos> contratosList) {
        this.contratosList = contratosList;
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

    public Delegaciones getIdDelegacion() {
        return idDelegacion;
    }

    public void setIdDelegacion(Delegaciones idDelegacion) {
        this.idDelegacion = idDelegacion;
    }

    public List<DpnInsumos> getDpnInsumosList() {
        return dpnInsumosList;
    }

    public void setDpnInsumosList(List<DpnInsumos> dpnInsumosList) {
        this.dpnInsumosList = dpnInsumosList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idUnidadesMedicas != null ? idUnidadesMedicas.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof UnidadesMedicas)) {
            return false;
        }
        UnidadesMedicas other = (UnidadesMedicas) object;
        if ((this.idUnidadesMedicas == null && other.idUnidadesMedicas != null) || (this.idUnidadesMedicas != null && !this.idUnidadesMedicas.equals(other.idUnidadesMedicas))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.issste.sicabis.ejb.modelo.UnidadesMedicas[ idUnidadesMedicas=" + idUnidadesMedicas + " ]";
    }

    @XmlTransient
    public List<Usuarios> getUsuariosList() {
        return usuariosList;
    }

    public void setUsuariosList(List<Usuarios> usuariosList) {
        this.usuariosList = usuariosList;
    }

    public String getClaveUmu() {
        return claveUmu;
    }

    public void setClaveUmu(String claveUmu) {
        this.claveUmu = claveUmu;
    }

    public String getClavePresupuestal() {
        return clavePresupuestal;
    }

    public void setClavePresupuestal(String clavePresupuestal) {
        this.clavePresupuestal = clavePresupuestal;
    }

    public Nivel getIdNivel() {
        return idNivel;
    }

    public void setIdNivel(Nivel idNivel) {
        this.idNivel = idNivel;
    }

    public Integer getIdPadre() {
        return idPadre;
    }

    public void setIdPadre(Integer idPadre) {
        this.idPadre = idPadre;
    }

    public Integer getActivo() {
        return activo;
    }

    public void setActivo(Integer activo) {
        this.activo = activo;
    }

    @XmlTransient
    public List<UnidadInsumosDpn> getUnidadInsumoDpnList() {
        return unidadInsumoDpnList;
    }

    public void setUnidadInsumoDpnList(List<UnidadInsumosDpn> unidadInsumoDpnList) {
        this.unidadInsumoDpnList = unidadInsumoDpnList;
    }

    public Integer getConcentradora() {
        return concentradora;
    }

    public void setConcentradora(Integer concentradora) {
        this.concentradora = concentradora;
    }

    public Integer getHospitalRegional() {
        return hospitalRegional;
    }

    public void setHospitalRegional(Integer hospitalRegional) {
        this.hospitalRegional = hospitalRegional;
    }

    public List<ContactosAlertasDpn> getContactosAlertasDpnList() {
        return contactosAlertasDpnList;
    }

    public void setContactosAlertasDpnList(List<ContactosAlertasDpn> contactosAlertasDpnList) {
        this.contactosAlertasDpnList = contactosAlertasDpnList;
    }

}
