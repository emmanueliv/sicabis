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
 * @author fabianvr
 */
@Entity
@Table(name = "respositorio_documentos")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "RespositorioDocumentos.findAll", query = "SELECT r FROM RespositorioDocumentos r"),
    @NamedQuery(name = "RespositorioDocumentos.findByIdRespositorioDocumento", query = "SELECT r FROM RespositorioDocumentos r WHERE r.idRespositorioDocumento = :idRespositorioDocumento"),
    @NamedQuery(name = "RespositorioDocumentos.findByActivo", query = "SELECT r FROM RespositorioDocumentos r WHERE r.activo = :activo"),
    @NamedQuery(name = "RespositorioDocumentos.findByNombre", query = "SELECT r FROM RespositorioDocumentos r WHERE r.nombre = :nombre"),
    @NamedQuery(name = "RespositorioDocumentos.findByRuta", query = "SELECT r FROM RespositorioDocumentos r WHERE r.ruta = :ruta"),
    @NamedQuery(name = "RespositorioDocumentos.findByUsuarioAlta", query = "SELECT r FROM RespositorioDocumentos r WHERE r.usuarioAlta = :usuarioAlta"),
    @NamedQuery(name = "RespositorioDocumentos.findByUsuarioBaja", query = "SELECT r FROM RespositorioDocumentos r WHERE r.usuarioBaja = :usuarioBaja"),
    @NamedQuery(name = "RespositorioDocumentos.findByUsuarioModificacion", query = "SELECT r FROM RespositorioDocumentos r WHERE r.usuarioModificacion = :usuarioModificacion"),
    @NamedQuery(name = "RespositorioDocumentos.findByFechaAlta", query = "SELECT r FROM RespositorioDocumentos r WHERE r.fechaAlta = :fechaAlta"),
    @NamedQuery(name = "RespositorioDocumentos.findByFechaBaja", query = "SELECT r FROM RespositorioDocumentos r WHERE r.fechaBaja = :fechaBaja"),
    @NamedQuery(name = "RespositorioDocumentos.findByFechaModificacion", query = "SELECT r FROM RespositorioDocumentos r WHERE r.fechaModificacion = :fechaModificacion")})
public class RespositorioDocumentos implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue (strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id_respositorio_documento")
    private Integer idRespositorioDocumento;
    @Column(name = "activo")
    private Integer activo;
    @NotNull
    @Column(name = "id_proceso")
    private Integer idProceso;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 100)
    @Column(name = "nombre")
    private String nombre;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 100)
    @Column(name = "nombre_servidor")
    private String nombreArchivo;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 1000)
    @Column(name = "ruta")
    private String ruta;
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
    @JoinColumn(name = "id_tipo_documento", referencedColumnName = "id_tipo_documento")
    @ManyToOne(optional = false)
    private TipoDocumentos idTipoDocumento;
    

    public RespositorioDocumentos() {
    }

    public RespositorioDocumentos(Integer idRespositorioDocumento) {
        this.idRespositorioDocumento = idRespositorioDocumento;
    }

    public RespositorioDocumentos(Integer idRespositorioDocumento, String nombre, String ruta) {
        this.idRespositorioDocumento = idRespositorioDocumento;
        this.nombre = nombre;
        this.ruta = ruta;
    }

    public Integer getIdRespositorioDocumento() {
        return idRespositorioDocumento;
    }

    public void setIdRespositorioDocumento(Integer idRespositorioDocumento) {
        this.idRespositorioDocumento = idRespositorioDocumento;
    }

    public Integer getActivo() {
        return activo;
    }

    public void setActivo(Integer activo) {
        this.activo = activo;
    }
    
    public Integer getIdProceso() {
        return idProceso;
    }

    public void setIdProceso(Integer idProceso) {
        this.idProceso = idProceso;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
    
    public String getNombreArchivo() {
        return nombreArchivo;
    }

    public void setNombreArchivo(String nombreArchivo) {
        this.nombreArchivo = nombreArchivo;
    }

    public String getRuta() {
        return ruta;
    }

    public void setRuta(String ruta) {
        this.ruta = ruta;
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

    public TipoDocumentos getIdTipoDocumento() {
        return idTipoDocumento;
    }

    public void setIdTipoDocumento(TipoDocumentos idTipoDocumento) {
        this.idTipoDocumento = idTipoDocumento;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idRespositorioDocumento != null ? idRespositorioDocumento.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof RespositorioDocumentos)) {
            return false;
        }
        RespositorioDocumentos other = (RespositorioDocumentos) object;
        if ((this.idRespositorioDocumento == null && other.idRespositorioDocumento != null) || (this.idRespositorioDocumento != null && !this.idRespositorioDocumento.equals(other.idRespositorioDocumento))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.issste.sicabis.ejb.modelo.RespositorioDocumentos[ idRespositorioDocumento=" + idRespositorioDocumento +"";
    }
    
}
