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
@Table(name = "destinos")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Destinos.findAll", query = "SELECT d FROM Destinos d"),
    @NamedQuery(name = "Destinos.findByIdDestino", query = "SELECT d FROM Destinos d WHERE d.idDestino = :idDestino"),
    @NamedQuery(name = "Destinos.findByActivo", query = "SELECT d FROM Destinos d WHERE d.activo = :activo"),
    @NamedQuery(name = "Destinos.findByClave", query = "SELECT d FROM Destinos d WHERE d.clave = :clave AND d.activo = 1"),
    @NamedQuery(name = "Destinos.findByNombre", query = "SELECT d FROM Destinos d WHERE d.nombre = :nombre AND d.activo = 1"),
    @NamedQuery(name = "Destinos.findByDomicilio", query = "SELECT d FROM Destinos d WHERE d.domicilio = :domicilio"),
    @NamedQuery(name = "Destinos.findByUsuarioAlta", query = "SELECT d FROM Destinos d WHERE d.usuarioAlta = :usuarioAlta"),
    @NamedQuery(name = "Destinos.findByUsuarioBaja", query = "SELECT d FROM Destinos d WHERE d.usuarioBaja = :usuarioBaja"),
    @NamedQuery(name = "Destinos.findByUsuarioModifcacion", query = "SELECT d FROM Destinos d WHERE d.usuarioModifcacion = :usuarioModifcacion"),
    @NamedQuery(name = "Destinos.findByFechaAlta", query = "SELECT d FROM Destinos d WHERE d.fechaAlta = :fechaAlta"),
    @NamedQuery(name = "Destinos.findByFechaBaja", query = "SELECT d FROM Destinos d WHERE d.fechaBaja = :fechaBaja"),
    @NamedQuery(name = "Destinos.findByFechaModificacion", query = "SELECT d FROM Destinos d WHERE d.fechaModificacion = :fechaModificacion")})
public class Destinos implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id_destino")
    private Integer idDestino;
    @Column(name = "activo")
    private Integer activo;
    @Basic(optional = false)
    @NotNull
    //@Size(min = 1, max = 45)
    @Column(name = "clave")
    private String clave;
    @Basic(optional = false)
    @NotNull
    //@Size(min = 1, max = 45)
    @Column(name = "nombre")
    private String nombre;
    @Basic(optional = false)
    @NotNull
    //@Size(min = 1, max = 45)
    @Column(name = "domicilio")
    private String domicilio;
    @Column(name = "id_padre")
    private Integer idPadre;
    @Size(max = 45)
    @Column(name = "usuario_alta")
    private String usuarioAlta;
    @Size(max = 45)
    @Column(name = "usuario_baja")
    private String usuarioBaja;
    @Size(max = 45)
    @Column(name = "usuario_modifcacion")
    private String usuarioModifcacion;
    @Column(name = "fecha_alta")
    @Temporal(TemporalType.DATE)
    private Date fechaAlta;
    @Column(name = "fecha_baja")
    @Temporal(TemporalType.DATE)
    private Date fechaBaja;
    @Column(name = "fecha_modificacion")
    @Temporal(TemporalType.DATE)
    private Date fechaModificacion;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idDestino")
    private List<ProcedimientoRcbDestinos> procedimientoRcbDestinosList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idDestino")
    private List<Contratos> contratosList;

    public Destinos() {
    }

    public Destinos(Integer idDestino) {
        this.idDestino = idDestino;
    }

    public Destinos(Integer idDestino, String clave, String nombre, String domicilio) {
        this.idDestino = idDestino;
        this.clave = clave;
        this.nombre = nombre;
        this.domicilio = domicilio;
    }

    public Integer getIdDestino() {
        return idDestino;
    }

    public void setIdDestino(Integer idDestino) {
        this.idDestino = idDestino;
    }

    public Integer getActivo() {
        return activo;
    }

    public void setActivo(Integer activo) {
        this.activo = activo;
    }

    public String getClave() {
        return clave;
    }

    public void setClave(String clave) {
        this.clave = clave;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getDomicilio() {
        return domicilio;
    }

    public void setDomicilio(String domicilio) {
        this.domicilio = domicilio;
    }

    public Integer getIdPadre() {
        return idPadre;
    }

    public void setIdPadre(Integer idPadre) {
        this.idPadre = idPadre;
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

    public String getUsuarioModifcacion() {
        return usuarioModifcacion;
    }

    public void setUsuarioModifcacion(String usuarioModifcacion) {
        this.usuarioModifcacion = usuarioModifcacion;
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
    public List<ProcedimientoRcbDestinos> getProcedimientoRcbDestinosList() {
        return procedimientoRcbDestinosList;
    }

    public void setProcedimientoRcbDestinosList(List<ProcedimientoRcbDestinos> procedimientoRcbDestinosList) {
        this.procedimientoRcbDestinosList = procedimientoRcbDestinosList;
    }

    @XmlTransient
    public List<Contratos> getContratosList() {
        return contratosList;
    }

    public void setContratosList(List<Contratos> contratosList) {
        this.contratosList = contratosList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idDestino != null ? idDestino.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Destinos)) {
            return false;
        }
        Destinos other = (Destinos) object;
        if ((this.idDestino == null && other.idDestino != null) || (this.idDestino != null && !this.idDestino.equals(other.idDestino))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.issste.sicabis.ejb.modelo.Destinos[ idDestino=" + idDestino + " ]";
    }

}
