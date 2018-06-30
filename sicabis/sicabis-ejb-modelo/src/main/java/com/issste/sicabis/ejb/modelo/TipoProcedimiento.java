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
@Table(name = "tipo_procedimiento")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "TipoProcedimiento.findAll", query = "SELECT t FROM TipoProcedimiento t WHERE t.activo = 1"),
    @NamedQuery(name = "TipoProcedimiento.findByIdTipoProcedimiento", query = "SELECT t FROM TipoProcedimiento t WHERE t.idTipoProcedimiento = :idTipoProcedimiento"),
    @NamedQuery(name = "TipoProcedimiento.findByActivo", query = "SELECT t FROM TipoProcedimiento t WHERE t.activo = :activo"),
    @NamedQuery(name = "TipoProcedimiento.findByDescripcion", query = "SELECT t FROM TipoProcedimiento t WHERE t.descripcion = :descripcion AND t.activo = 1"),
    @NamedQuery(name = "TipoProcedimiento.findByUsuarioAlta", query = "SELECT t FROM TipoProcedimiento t WHERE t.usuarioAlta = :usuarioAlta"),
    @NamedQuery(name = "TipoProcedimiento.findByUsuarioBaja", query = "SELECT t FROM TipoProcedimiento t WHERE t.usuarioBaja = :usuarioBaja"),
    @NamedQuery(name = "TipoProcedimiento.findByUsuarioModificacion", query = "SELECT t FROM TipoProcedimiento t WHERE t.usuarioModificacion = :usuarioModificacion"),
    @NamedQuery(name = "TipoProcedimiento.findByFechaAlta", query = "SELECT t FROM TipoProcedimiento t WHERE t.fechaAlta = :fechaAlta"),
    @NamedQuery(name = "TipoProcedimiento.findByFechaBaja", query = "SELECT t FROM TipoProcedimiento t WHERE t.fechaBaja = :fechaBaja"),
    @NamedQuery(name = "TipoProcedimiento.findByFechaModificiacion", query = "SELECT t FROM TipoProcedimiento t WHERE t.fechaModificiacion = :fechaModificiacion")})
public class TipoProcedimiento implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id_tipo_procedimiento")
    private Integer idTipoProcedimiento;
    @Column(name = "activo")
    private Integer activo;
    @Basic(optional = false)
    @NotNull
    //@Size(min = 1, max = 45)
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
    @Column(name = "fecha_modificiacion")
    @Temporal(TemporalType.DATE)
    private Date fechaModificiacion;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idTipoProcedimiento")
    private List<Procedimientos> procedimientosList;

    public TipoProcedimiento() {
    }

    public TipoProcedimiento(Integer idTipoProcedimiento) {
        this.idTipoProcedimiento = idTipoProcedimiento;
    }

    public TipoProcedimiento(Integer idTipoProcedimiento, String descripcion) {
        this.idTipoProcedimiento = idTipoProcedimiento;
        this.descripcion = descripcion;
    }

    public Integer getIdTipoProcedimiento() {
        return idTipoProcedimiento;
    }

    public void setIdTipoProcedimiento(Integer idTipoProcedimiento) {
        this.idTipoProcedimiento = idTipoProcedimiento;
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

    public Date getFechaModificiacion() {
        return fechaModificiacion;
    }

    public void setFechaModificiacion(Date fechaModificiacion) {
        this.fechaModificiacion = fechaModificiacion;
    }

    @XmlTransient
    public List<Procedimientos> getProcedimientosList() {
        return procedimientosList;
    }

    public void setProcedimientosList(List<Procedimientos> procedimientosList) {
        this.procedimientosList = procedimientosList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idTipoProcedimiento != null ? idTipoProcedimiento.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof TipoProcedimiento)) {
            return false;
        }
        TipoProcedimiento other = (TipoProcedimiento) object;
        if ((this.idTipoProcedimiento == null && other.idTipoProcedimiento != null) || (this.idTipoProcedimiento != null && !this.idTipoProcedimiento.equals(other.idTipoProcedimiento))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.issste.sicabis.ejb.modelo.TipoProcedimiento[ idTipoProcedimiento=" + idTipoProcedimiento + " ]";
    }
    
}
