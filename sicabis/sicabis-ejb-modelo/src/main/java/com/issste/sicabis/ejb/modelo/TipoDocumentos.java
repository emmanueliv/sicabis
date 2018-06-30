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
 * @author fabianvr
 */
@Entity
@Table(name = "tipo_documentos")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "TipoDocumentos.findAll", query = "SELECT t FROM TipoDocumentos t WHERE t.activo = 1"),
    @NamedQuery(name = "TipoDocumentos.findByIdTipoDocumento", query = "SELECT t FROM TipoDocumentos t WHERE t.idTipoDocumento = :idTipoDocumento"),
    @NamedQuery(name = "TipoDocumentos.findByNombre", query = "SELECT t FROM TipoDocumentos t WHERE t.nombre = :nombre AND t.activo = 1"),
    @NamedQuery(name = "TipoDocumentos.findByExtension", query = "SELECT t FROM TipoDocumentos t WHERE t.extension = :extension"),
    @NamedQuery(name = "TipoDocumentos.findByUsuarioAlta", query = "SELECT t FROM TipoDocumentos t WHERE t.usuarioAlta = :usuarioAlta"),
    @NamedQuery(name = "TipoDocumentos.findByUsuarioBaja", query = "SELECT t FROM TipoDocumentos t WHERE t.usuarioBaja = :usuarioBaja"),
    @NamedQuery(name = "TipoDocumentos.findByUsuarioModificacion", query = "SELECT t FROM TipoDocumentos t WHERE t.usuarioModificacion = :usuarioModificacion"),
    @NamedQuery(name = "TipoDocumentos.findByFechaAlta", query = "SELECT t FROM TipoDocumentos t WHERE t.fechaAlta = :fechaAlta"),
    @NamedQuery(name = "TipoDocumentos.findByFechaBaja", query = "SELECT t FROM TipoDocumentos t WHERE t.fechaBaja = :fechaBaja"),
    @NamedQuery(name = "TipoDocumentos.findByFechaModificacion", query = "SELECT t FROM TipoDocumentos t WHERE t.fechaModificacion = :fechaModificacion"),
    @NamedQuery(name = "TipoDocumentos.findByIdTarea", query = "SELECT t FROM TipoDocumentos t WHERE t.idTarea.idTarea = :idTarea"),})
public class TipoDocumentos implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue (strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id_tipo_documento")
    private Integer idTipoDocumento;
    @Column(name = "activo")
    private Integer activo;
    @Basic(optional = false)
    @NotNull
    //@Size(min = 1, max = 100)
    @Column(name = "nombre")
    private String nombre;
    @Basic(optional = false)
    @NotNull
    //@Size(min = 1, max = 45)
    @Column(name = "extension")
    private String extension;
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
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idTipoDocumento")
    private List<RespositorioDocumentos> respositorioDocumentosList;
    @JoinColumn(name = "id_tarea", referencedColumnName = "id_tarea")
    @ManyToOne(optional = false)
    private Tareas idTarea;

    public TipoDocumentos() {
    }

    public TipoDocumentos(Integer idTipoDocumento) {
        this.idTipoDocumento = idTipoDocumento;
    }

    public TipoDocumentos(Integer idTipoDocumento, String nombre, String extension) {
        this.idTipoDocumento = idTipoDocumento;
        this.nombre = nombre;
        this.extension = extension;
    }

    public Integer getIdTipoDocumento() {
        return idTipoDocumento;
    }

    public void setIdTipoDocumento(Integer idTipoDocumento) {
        this.idTipoDocumento = idTipoDocumento;
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

    public String getExtension() {
        return extension;
    }

    public void setExtension(String extension) {
        this.extension = extension;
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
    public List<RespositorioDocumentos> getRespositorioDocumentosList() {
        return respositorioDocumentosList;
    }

    public void setRespositorioDocumentosList(List<RespositorioDocumentos> respositorioDocumentosList) {
        this.respositorioDocumentosList = respositorioDocumentosList;
    }
    
    public Tareas getIdTarea() {
        return idTarea;
    }

    public void setIdTarea(Tareas idTarea) {
        this.idTarea = idTarea;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idTipoDocumento != null ? idTipoDocumento.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof TipoDocumentos)) {
            return false;
        }
        TipoDocumentos other = (TipoDocumentos) object;
        if ((this.idTipoDocumento == null && other.idTipoDocumento != null) || (this.idTipoDocumento != null && !this.idTipoDocumento.equals(other.idTipoDocumento))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.issste.sicabis.ejb.modelo.TipoDocumentos[ idTipoDocumento=" + idTipoDocumento + " ]";
    }
    
}
