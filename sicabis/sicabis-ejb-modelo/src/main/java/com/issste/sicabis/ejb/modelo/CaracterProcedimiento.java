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
@Table(name = "caracter_procedimiento")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "CaracterProcedimiento.findAll", query = "SELECT c FROM CaracterProcedimiento c WHERE c.activo = 1"),
    @NamedQuery(name = "CaracterProcedimiento.findByIdCaracterProcedimiento", query = "SELECT c FROM CaracterProcedimiento c WHERE c.idCaracterProcedimiento = :idCaracterProcedimiento"),
    @NamedQuery(name = "CaracterProcedimiento.findByActivo", query = "SELECT c FROM CaracterProcedimiento c WHERE c.activo = :activo"),
    @NamedQuery(name = "CaracterProcedimiento.findByDescripcion", query = "SELECT c FROM CaracterProcedimiento c WHERE c.descripcion = :descripcion AND c.activo = 1"),
    @NamedQuery(name = "CaracterProcedimiento.findByUsuarioAlta", query = "SELECT c FROM CaracterProcedimiento c WHERE c.usuarioAlta = :usuarioAlta"),
    @NamedQuery(name = "CaracterProcedimiento.findByUsuarioBaja", query = "SELECT c FROM CaracterProcedimiento c WHERE c.usuarioBaja = :usuarioBaja"),
    @NamedQuery(name = "CaracterProcedimiento.findByUsuarioModificacion", query = "SELECT c FROM CaracterProcedimiento c WHERE c.usuarioModificacion = :usuarioModificacion"),
    @NamedQuery(name = "CaracterProcedimiento.findByFechaAlta", query = "SELECT c FROM CaracterProcedimiento c WHERE c.fechaAlta = :fechaAlta"),
    @NamedQuery(name = "CaracterProcedimiento.findByFechaBaja", query = "SELECT c FROM CaracterProcedimiento c WHERE c.fechaBaja = :fechaBaja"),
    @NamedQuery(name = "CaracterProcedimiento.findByFechaModificacion", query = "SELECT c FROM CaracterProcedimiento c WHERE c.fechaModificacion = :fechaModificacion")})
public class CaracterProcedimiento implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id_caracter_procedimiento")
    private Integer idCaracterProcedimiento;
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
    @Column(name = "fecha_modificacion")
    @Temporal(TemporalType.DATE)
    private Date fechaModificacion;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idCaracterProcedimiento")
    private List<Procedimientos> procedimientosList;

    public CaracterProcedimiento() {
    }

    public CaracterProcedimiento(Integer idCaracterProcedimiento) {
        this.idCaracterProcedimiento = idCaracterProcedimiento;
    }

    public CaracterProcedimiento(Integer idCaracterProcedimiento, String descripcion) {
        this.idCaracterProcedimiento = idCaracterProcedimiento;
        this.descripcion = descripcion;
    }

    public Integer getIdCaracterProcedimiento() {
        return idCaracterProcedimiento;
    }

    public void setIdCaracterProcedimiento(Integer idCaracterProcedimiento) {
        this.idCaracterProcedimiento = idCaracterProcedimiento;
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
        hash += (idCaracterProcedimiento != null ? idCaracterProcedimiento.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof CaracterProcedimiento)) {
            return false;
        }
        CaracterProcedimiento other = (CaracterProcedimiento) object;
        if ((this.idCaracterProcedimiento == null && other.idCaracterProcedimiento != null) || (this.idCaracterProcedimiento != null && !this.idCaracterProcedimiento.equals(other.idCaracterProcedimiento))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.issste.sicabis.ejb.modelo.CaracterProcedimiento[ idCaracterProcedimiento=" + idCaracterProcedimiento + " ]";
    }
    
}
