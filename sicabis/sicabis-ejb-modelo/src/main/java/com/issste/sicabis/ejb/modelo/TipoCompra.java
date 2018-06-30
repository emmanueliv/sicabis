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
@Table(name = "tipo_compra")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "TipoCompra.findAll", query = "SELECT t FROM TipoCompra t"),
    @NamedQuery(name = "TipoCompra.findByIdTipoCompra", query = "SELECT t FROM TipoCompra t WHERE t.idTipoCompra = :idTipoCompra"),
    @NamedQuery(name = "TipoCompra.findByActivo", query = "SELECT t FROM TipoCompra t WHERE t.activo = :activo"),
    @NamedQuery(name = "TipoCompra.findByNombre", query = "SELECT t FROM TipoCompra t WHERE t.nombre = :nombre"),
    @NamedQuery(name = "TipoCompra.findByUsuarioAlta", query = "SELECT t FROM TipoCompra t WHERE t.usuarioAlta = :usuarioAlta"),
    @NamedQuery(name = "TipoCompra.findByUsuarioBaja", query = "SELECT t FROM TipoCompra t WHERE t.usuarioBaja = :usuarioBaja"),
    @NamedQuery(name = "TipoCompra.findByUsuarioModificacion", query = "SELECT t FROM TipoCompra t WHERE t.usuarioModificacion = :usuarioModificacion"),
    @NamedQuery(name = "TipoCompra.findByFechaAlta", query = "SELECT t FROM TipoCompra t WHERE t.fechaAlta = :fechaAlta"),
    @NamedQuery(name = "TipoCompra.findByFechaBaja", query = "SELECT t FROM TipoCompra t WHERE t.fechaBaja = :fechaBaja"),
    @NamedQuery(name = "TipoCompra.findByFechaModificacion", query = "SELECT t FROM TipoCompra t WHERE t.fechaModificacion = :fechaModificacion")})
public class TipoCompra implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id_tipo_compra")
    private Integer idTipoCompra;
    @Column(name = "activo")
    private Integer activo;
    @Basic(optional = false)
    @NotNull
    //@Size(min = 1, max = 250)
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
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idTipoCompra")
    private List<Rcb> rcbList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idTipoCompra")
    private List<Procedimientos> procedimientosList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idTipoCompra")
    private List<CrInsumos> crInsumosList;

    public TipoCompra() {
    }

    public TipoCompra(Integer idTipoCompra) {
        this.idTipoCompra = idTipoCompra;
    }

    public TipoCompra(Integer idTipoCompra, String nombre) {
        this.idTipoCompra = idTipoCompra;
        this.nombre = nombre;
    }

    public Integer getIdTipoCompra() {
        return idTipoCompra;
    }

    public void setIdTipoCompra(Integer idTipoCompra) {
        this.idTipoCompra = idTipoCompra;
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
    public List<Rcb> getRcbList() {
        return rcbList;
    }

    public void setRcbList(List<Rcb> rcbList) {
        this.rcbList = rcbList;
    }

    @XmlTransient
    public List<CrInsumos> getCrInsumosList() {
        return crInsumosList;
    }

    public void setCrInsumosList(List<CrInsumos> crInsumosList) {
        this.crInsumosList = crInsumosList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idTipoCompra != null ? idTipoCompra.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof TipoCompra)) {
            return false;
        }
        TipoCompra other = (TipoCompra) object;
        if ((this.idTipoCompra == null && other.idTipoCompra != null) || (this.idTipoCompra != null && !this.idTipoCompra.equals(other.idTipoCompra))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.issste.sicabis.ejb.modelo.TipoCompra[ idTipoCompra=" + idTipoCompra + " ]";
    }
    
}
