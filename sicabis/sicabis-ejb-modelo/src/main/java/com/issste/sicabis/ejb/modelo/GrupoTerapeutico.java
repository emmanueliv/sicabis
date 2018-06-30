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
@Table(name = "grupo_terapeutico")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "GrupoTerapeutico.findAll", query = "SELECT g FROM GrupoTerapeutico g WHERE g.activo = 1"),
    @NamedQuery(name = "GrupoTerapeutico.findByIdGrupoTerapeutico", query = "SELECT g FROM GrupoTerapeutico g WHERE g.idGrupoTerapeutico = :idGrupoTerapeutico"),
    @NamedQuery(name = "GrupoTerapeutico.findByActivo", query = "SELECT g FROM GrupoTerapeutico g WHERE g.activo = :activo"),
    @NamedQuery(name = "GrupoTerapeutico.findByDescripcion", query = "SELECT g FROM GrupoTerapeutico g WHERE g.descripcion = :descripcion AND g.activo = 1"),
    @NamedQuery(name = "GrupoTerapeutico.findByUsuarioAlta", query = "SELECT g FROM GrupoTerapeutico g WHERE g.usuarioAlta = :usuarioAlta"),
    @NamedQuery(name = "GrupoTerapeutico.findByUsuarioBaja", query = "SELECT g FROM GrupoTerapeutico g WHERE g.usuarioBaja = :usuarioBaja"),
    @NamedQuery(name = "GrupoTerapeutico.findByUsuarioModificacion", query = "SELECT g FROM GrupoTerapeutico g WHERE g.usuarioModificacion = :usuarioModificacion"),
    @NamedQuery(name = "GrupoTerapeutico.findByFechaAlta", query = "SELECT g FROM GrupoTerapeutico g WHERE g.fechaAlta = :fechaAlta"),
    @NamedQuery(name = "GrupoTerapeutico.findByFechaBaja", query = "SELECT g FROM GrupoTerapeutico g WHERE g.fechaBaja = :fechaBaja"),
    @NamedQuery(name = "GrupoTerapeutico.findByFechaModificacion", query = "SELECT g FROM GrupoTerapeutico g WHERE g.fechaModificacion = :fechaModificacion")})
public class GrupoTerapeutico implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id_grupo_terapeutico")
    private Integer idGrupoTerapeutico;
    @Column(name = "activo")
    private Integer activo;
    @Basic(optional = false)
    @NotNull
    //@Size(min = 1, max = 50)
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
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idGrupoTerapeutico")
    private List<Insumos> insumosList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idGrupoTerapeutico")
    private List<RcbInsumos> rcbInsumosList;

    public GrupoTerapeutico() {
    }

    public GrupoTerapeutico(Integer idGrupoTerapeutico) {
        this.idGrupoTerapeutico = idGrupoTerapeutico;
    }

    public GrupoTerapeutico(Integer idGrupoTerapeutico, String descripcion) {
        this.idGrupoTerapeutico = idGrupoTerapeutico;
        this.descripcion = descripcion;
    }

    public Integer getIdGrupoTerapeutico() {
        return idGrupoTerapeutico;
    }

    public void setIdGrupoTerapeutico(Integer idGrupoTerapeutico) {
        this.idGrupoTerapeutico = idGrupoTerapeutico;
    }

    public Integer getActivo() {
        return activo;
    }

    public void setActivo(Integer activo) {
        this.activo = activo;
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

    public List<RcbInsumos> getRcbInsumosList() {
        return rcbInsumosList;
    }

    public void setRcbInsumosList(List<RcbInsumos> rcbInsumosList) {
        this.rcbInsumosList = rcbInsumosList;
    }
    
    

    @XmlTransient
    public List<Insumos> getInsumosList() {
        return insumosList;
    }

    public void setInsumosList(List<Insumos> insumosList) {
        this.insumosList = insumosList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idGrupoTerapeutico != null ? idGrupoTerapeutico.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof GrupoTerapeutico)) {
            return false;
        }
        GrupoTerapeutico other = (GrupoTerapeutico) object;
        if ((this.idGrupoTerapeutico == null && other.idGrupoTerapeutico != null) || (this.idGrupoTerapeutico != null && !this.idGrupoTerapeutico.equals(other.idGrupoTerapeutico))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.issste.sicabis.ejb.modelo.GrupoTerapeutico[ idGrupoTerapeutico=" + idGrupoTerapeutico + " ]";
    }
    
}
