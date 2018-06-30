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
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author 84JBBG2
 */
@Entity
@Table(name = "clasificacion_importancia")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "ClasificacionImportancia.findAll", query = "SELECT c FROM ClasificacionImportancia c"),
    @NamedQuery(name = "ClasificacionImportancia.findByIdClasificacionImportancia", query = "SELECT c FROM ClasificacionImportancia c WHERE c.idClasificacionImportancia = :idClasificacionImportancia"),
    @NamedQuery(name = "ClasificacionImportancia.findByActivo", query = "SELECT c FROM ClasificacionImportancia c WHERE c.activo = :activo"),
    @NamedQuery(name = "ClasificacionImportancia.findBySigla", query = "SELECT c FROM ClasificacionImportancia c WHERE c.sigla = :sigla"),
    @NamedQuery(name = "ClasificacionImportancia.findByDescripcion", query = "SELECT c FROM ClasificacionImportancia c WHERE c.descripcion = :descripcion"),
    @NamedQuery(name = "ClasificacionImportancia.findByUsuarioAlta", query = "SELECT c FROM ClasificacionImportancia c WHERE c.usuarioAlta = :usuarioAlta"),
    @NamedQuery(name = "ClasificacionImportancia.findByUsuarioBaja", query = "SELECT c FROM ClasificacionImportancia c WHERE c.usuarioBaja = :usuarioBaja"),
    @NamedQuery(name = "ClasificacionImportancia.findByUsuarioModificacion", query = "SELECT c FROM ClasificacionImportancia c WHERE c.usuarioModificacion = :usuarioModificacion"),
    @NamedQuery(name = "ClasificacionImportancia.findByFechaAlta", query = "SELECT c FROM ClasificacionImportancia c WHERE c.fechaAlta = :fechaAlta"),
    @NamedQuery(name = "ClasificacionImportancia.findByFechaBaja", query = "SELECT c FROM ClasificacionImportancia c WHERE c.fechaBaja = :fechaBaja"),
    @NamedQuery(name = "ClasificacionImportancia.findByFechaModificacion", query = "SELECT c FROM ClasificacionImportancia c WHERE c.fechaModificacion = :fechaModificacion")})
public class ClasificacionImportancia implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id_clasificacion_importancia")
    private Integer idClasificacionImportancia;
    @Basic(optional = false)
    @NotNull
    @Column(name = "activo")
    private int activo;
    @Basic(optional = false)
    @NotNull
    //@Size(min = 1, max = 20)
    @Column(name = "sigla")
    private String sigla;
    //@Size(max = 250)
    @Column(name = "descripcion")
    private String descripcion;
    //@Size(max = 45)
    @Column(name = "usuario_alta")
    private String usuarioAlta;
    //@Size(max = 45)
    @Column(name = "usuario_baja")
    private String usuarioBaja;
    //@Size(max = 45)
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
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idClasificacionImportancia")
    private List<Insumos> insumosList;

    public ClasificacionImportancia() {
    }

    public ClasificacionImportancia(Integer idClasificacionImportancia) {
        this.idClasificacionImportancia = idClasificacionImportancia;
    }

    public ClasificacionImportancia(Integer idClasificacionImportancia, int activo, String sigla) {
        this.idClasificacionImportancia = idClasificacionImportancia;
        this.activo = activo;
        this.sigla = sigla;
    }

    public Integer getIdClasificacionImportancia() {
        return idClasificacionImportancia;
    }

    public void setIdClasificacionImportancia(Integer idClasificacionImportancia) {
        this.idClasificacionImportancia = idClasificacionImportancia;
    }

    public int getActivo() {
        return activo;
    }

    public void setActivo(int activo) {
        this.activo = activo;
    }

    public String getSigla() {
        return sigla;
    }

    public void setSigla(String sigla) {
        this.sigla = sigla;
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

    public List<Insumos> getInsumosList() {
        return insumosList;
    }

    public void setInsumosList(List<Insumos> insumosList) {
        this.insumosList = insumosList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idClasificacionImportancia != null ? idClasificacionImportancia.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof ClasificacionImportancia)) {
            return false;
        }
        ClasificacionImportancia other = (ClasificacionImportancia) object;
        if ((this.idClasificacionImportancia == null && other.idClasificacionImportancia != null) || (this.idClasificacionImportancia != null && !this.idClasificacionImportancia.equals(other.idClasificacionImportancia))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.issste.sicabis.ejb.modelo.ClasificacionImportancia[ idClasificacionImportancia=" + idClasificacionImportancia + " ]";
    }

}
