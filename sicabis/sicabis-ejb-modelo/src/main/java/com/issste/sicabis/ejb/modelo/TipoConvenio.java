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
@Table(name = "tipo_convenio")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "TipoConvenio.findAll", query = "SELECT t FROM TipoConvenio t WHERE t.activo = 1"),
    @NamedQuery(name = "TipoConvenio.findByIdTipoConvenio", query = "SELECT t FROM TipoConvenio t WHERE t.idTipoConvenio = :idTipoConvenio"),
    @NamedQuery(name = "TipoConvenio.findByActivo", query = "SELECT t FROM TipoConvenio t WHERE t.activo = :activo"),
    @NamedQuery(name = "TipoConvenio.findByDescripcion", query = "SELECT t FROM TipoConvenio t WHERE t.descripcion = :descripcion AND t.activo = 1"),
    @NamedQuery(name = "TipoConvenio.findByUsuarioAlta", query = "SELECT t FROM TipoConvenio t WHERE t.usuarioAlta = :usuarioAlta"),
    @NamedQuery(name = "TipoConvenio.findByUsuarioBaja", query = "SELECT t FROM TipoConvenio t WHERE t.usuarioBaja = :usuarioBaja"),
    @NamedQuery(name = "TipoConvenio.findByUsuarioModificacion", query = "SELECT t FROM TipoConvenio t WHERE t.usuarioModificacion = :usuarioModificacion"),
    @NamedQuery(name = "TipoConvenio.findByFechaAlta", query = "SELECT t FROM TipoConvenio t WHERE t.fechaAlta = :fechaAlta"),
    @NamedQuery(name = "TipoConvenio.findByFechaBaja", query = "SELECT t FROM TipoConvenio t WHERE t.fechaBaja = :fechaBaja"),
    @NamedQuery(name = "TipoConvenio.findByFechaModificacion", query = "SELECT t FROM TipoConvenio t WHERE t.fechaModificacion = :fechaModificacion")})
public class TipoConvenio implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id_tipo_convenio")
    private Integer idTipoConvenio;
    @Column(name = "activo")
    private Integer activo;
    @Basic(optional = false)
    @NotNull
    //@Size(min = 1, max = 45)
    @Column(name = "nombre")
    private String nombre;
    @Basic(optional = false)
    @NotNull
    //@Size(max = 1000)
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
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idTipoConvenio")
    private List<Contratos> contratosList;

    public TipoConvenio() {
    }

    public TipoConvenio(Integer idTipoConvenio) {
        this.idTipoConvenio = idTipoConvenio;
    }

    public TipoConvenio(Integer idTipoConvenio, String descripcion) {
        this.idTipoConvenio = idTipoConvenio;
        this.descripcion = descripcion;
    }

    public Integer getIdTipoConvenio() {
        return idTipoConvenio;
    }

    public void setIdTipoConvenio(Integer idTipoConvenio) {
        this.idTipoConvenio = idTipoConvenio;
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
        hash += (idTipoConvenio != null ? idTipoConvenio.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof TipoConvenio)) {
            return false;
        }
        TipoConvenio other = (TipoConvenio) object;
        if ((this.idTipoConvenio == null && other.idTipoConvenio != null) || (this.idTipoConvenio != null && !this.idTipoConvenio.equals(other.idTipoConvenio))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.issste.sicabis.ejb.modelo.TipoConvenio[ idTipoConvenio=" + idTipoConvenio + " ]";
    }

}
