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
 * @author 9RZCBG2
 */
@Entity
@Table(name = "contactos_alertas_dpn")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "ContactosAlertasDpn.findAll", query = "SELECT c FROM ContactosAlertasDpn c"),
    @NamedQuery(name = "ContactosAlertasDpn.findByIdContactosAlertasDpn", query = "SELECT c FROM ContactosAlertasDpn c WHERE c.idContactosAlertasDpn = :idContactosAlertasDpn"),
    @NamedQuery(name = "ContactosAlertasDpn.findByCorreo", query = "SELECT c FROM ContactosAlertasDpn c WHERE c.correo = :correo"),
    @NamedQuery(name = "ContactosAlertasDpn.findByNombre", query = "SELECT c FROM ContactosAlertasDpn c WHERE c.nombre = :nombre"),
    @NamedQuery(name = "ContactosAlertasDpn.findByApellidoPaterno", query = "SELECT c FROM ContactosAlertasDpn c WHERE c.apellidoPaterno = :apellidoPaterno"),
    @NamedQuery(name = "ContactosAlertasDpn.findByApellidoMaterno", query = "SELECT c FROM ContactosAlertasDpn c WHERE c.apellidoMaterno = :apellidoMaterno"),
    @NamedQuery(name = "ContactosAlertasDpn.findByRed", query = "SELECT c FROM ContactosAlertasDpn c WHERE c.red = :red"),
    @NamedQuery(name = "ContactosAlertasDpn.findByIdDelegacion", query = "SELECT c FROM ContactosAlertasDpn c WHERE c.idDelegacion = :idDelegacion"),
    @NamedQuery(name = "ContactosAlertasDpn.findByIdUnidadMedica", query = "SELECT c FROM ContactosAlertasDpn c WHERE c.idUnidadMedica = :idUnidadMedica"),
    @NamedQuery(name = "ContactosAlertasDpn.findByUsuarioAlta", query = "SELECT c FROM ContactosAlertasDpn c WHERE c.usuarioAlta = :usuarioAlta"),
    @NamedQuery(name = "ContactosAlertasDpn.findByUsuarioBaja", query = "SELECT c FROM ContactosAlertasDpn c WHERE c.usuarioBaja = :usuarioBaja"),
    @NamedQuery(name = "ContactosAlertasDpn.findByUsuarioModificacion", query = "SELECT c FROM ContactosAlertasDpn c WHERE c.usuarioModificacion = :usuarioModificacion"),
    @NamedQuery(name = "ContactosAlertasDpn.findByFechaAlta", query = "SELECT c FROM ContactosAlertasDpn c WHERE c.fechaAlta = :fechaAlta"),
    @NamedQuery(name = "ContactosAlertasDpn.findByFechaBaja", query = "SELECT c FROM ContactosAlertasDpn c WHERE c.fechaBaja = :fechaBaja"),
    @NamedQuery(name = "ContactosAlertasDpn.findByFechaModificacion", query = "SELECT c FROM ContactosAlertasDpn c WHERE c.fechaModificacion = :fechaModificacion")})
public class ContactosAlertasDpn implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id_contactos_alertas_dpn")
    private Integer idContactosAlertasDpn;
    @Size(max = 250)
    @Column(name = "correo")
    private String correo;
    @Size(max = 250)
    @Column(name = "nombre")
    private String nombre;
    @Size(max = 250)
    @Column(name = "apellido_paterno")
    private String apellidoPaterno;
    @Size(max = 250)
    @Column(name = "apellido_materno")
    private String apellidoMaterno;
    @Size(max = 20)
    @Column(name = "red")
    private String red;
    @Column(name = "mapas")
    private Integer mapas;
    @Column(name = "estados")
    private Integer estados;
    @Column(name = "delegaciones")
    private Integer delegaciones;
    @Column(name = "g40")
    private Integer g40;
    @Column(name = "activo")
    private Integer activo;
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
    @JoinColumn(name = "id_delegacion", referencedColumnName = "id_delegacion")
    @ManyToOne(optional = false)
    private Delegaciones idDelegacion;
    @JoinColumn(name = "id_unidad_medica", referencedColumnName = "id_unidades_medicas")
    @ManyToOne(optional = true)
    private UnidadesMedicas idUnidadMedica;

    public ContactosAlertasDpn() {
    }

    public ContactosAlertasDpn(Integer idContactosAlertasDpn) {
        this.idContactosAlertasDpn = idContactosAlertasDpn;
    }

    public ContactosAlertasDpn(Integer idContactosAlertasDpn, Delegaciones idDelegacion, UnidadesMedicas idUnidadMedica) {
        this.idContactosAlertasDpn = idContactosAlertasDpn;
        this.idDelegacion = idDelegacion;
        this.idUnidadMedica = idUnidadMedica;
    }

    public Integer getIdContactosAlertasDpn() {
        return idContactosAlertasDpn;
    }

    public void setIdContactosAlertasDpn(Integer idContactosAlertasDpn) {
        this.idContactosAlertasDpn = idContactosAlertasDpn;
    }

    public String getCorreo() {
        return correo;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellidoPaterno() {
        return apellidoPaterno;
    }

    public void setApellidoPaterno(String apellidoPaterno) {
        this.apellidoPaterno = apellidoPaterno;
    }

    public String getApellidoMaterno() {
        return apellidoMaterno;
    }

    public void setApellidoMaterno(String apellidoMaterno) {
        this.apellidoMaterno = apellidoMaterno;
    }

    public String getRed() {
        return red;
    }

    public void setRed(String red) {
        this.red = red;
    }

    public Integer getMapas() {
        return mapas;
    }

    public void setMapas(Integer mapas) {
        this.mapas = mapas;
    }

    public Integer getEstados() {
        return estados;
    }

    public void setEstados(Integer estados) {
        this.estados = estados;
    }

    public Integer getDelegaciones() {
        return delegaciones;
    }

    public void setDelegaciones(Integer delegaciones) {
        this.delegaciones = delegaciones;
    }

    public Integer getG40() {
        return g40;
    }

    public void setG40(Integer g40) {
        this.g40 = g40;
    }

    public Integer getActivo() {
        return activo;
    }

    public void setActivo(Integer activo) {
        this.activo = activo;
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

    public Delegaciones getIdDelegacion() {
        return idDelegacion;
    }

    public void setIdDelegacion(Delegaciones idDelegacion) {
        this.idDelegacion = idDelegacion;
    }

    public UnidadesMedicas getIdUnidadMedica() {
        return idUnidadMedica;
    }

    public void setIdUnidadMedica(UnidadesMedicas idUnidadMedica) {
        this.idUnidadMedica = idUnidadMedica;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idContactosAlertasDpn != null ? idContactosAlertasDpn.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof ContactosAlertasDpn)) {
            return false;
        }
        ContactosAlertasDpn other = (ContactosAlertasDpn) object;
        if ((this.idContactosAlertasDpn == null && other.idContactosAlertasDpn != null) || (this.idContactosAlertasDpn != null && !this.idContactosAlertasDpn.equals(other.idContactosAlertasDpn))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.issste.sicabis.ejb.modelo.ContactosAlertasDpn[ idContactosAlertasDpn=" + idContactosAlertasDpn + " ]";
    }

}
