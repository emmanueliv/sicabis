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
 * @author kriosoft
 */
@Entity
@Table(name = "procedimiento_rcb_destinos_orden")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "ProcedimientoRcbDestinosOrden.findAll", query = "SELECT p FROM ProcedimientoRcbDestinosOrden p"),
    @NamedQuery(name = "ProcedimientoRcbDestinosOrden.findByIdProcedimientoRcbDestinosOrden", query = "SELECT p FROM ProcedimientoRcbDestinosOrden p WHERE p.idProcedimientoRcbDestinosOrden = :idProcedimientoRcbDestinosOrden"),
    @NamedQuery(name = "ProcedimientoRcbDestinosOrden.findByActivo", query = "SELECT p FROM ProcedimientoRcbDestinosOrden p WHERE p.activo = :activo"),
    @NamedQuery(name = "ProcedimientoRcbDestinosOrden.findByUsuarioAlta", query = "SELECT p FROM ProcedimientoRcbDestinosOrden p WHERE p.usuarioAlta = :usuarioAlta"),
    @NamedQuery(name = "ProcedimientoRcbDestinosOrden.findByUsuarioBaja", query = "SELECT p FROM ProcedimientoRcbDestinosOrden p WHERE p.usuarioBaja = :usuarioBaja"),
    @NamedQuery(name = "ProcedimientoRcbDestinosOrden.findByUsuarioModificacion", query = "SELECT p FROM ProcedimientoRcbDestinosOrden p WHERE p.usuarioModificacion = :usuarioModificacion"),
    @NamedQuery(name = "ProcedimientoRcbDestinosOrden.findByFechaAlta", query = "SELECT p FROM ProcedimientoRcbDestinosOrden p WHERE p.fechaAlta = :fechaAlta"),
    @NamedQuery(name = "ProcedimientoRcbDestinosOrden.findByFechaBaja", query = "SELECT p FROM ProcedimientoRcbDestinosOrden p WHERE p.fechaBaja = :fechaBaja"),
    @NamedQuery(name = "ProcedimientoRcbDestinosOrden.findByFechaModificacion", query = "SELECT p FROM ProcedimientoRcbDestinosOrden p WHERE p.fechaModificacion = :fechaModificacion")})
public class ProcedimientoRcbDestinosOrden implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id_procedimiento_rcb_destinos_orden")
    private Integer idProcedimientoRcbDestinosOrden;
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
    @JoinColumn(name = "id_procedimiento_rcb_destinos", referencedColumnName = "id_procedimiento_rcb_destinos")
    @ManyToOne(optional = false)
    private ProcedimientoRcbDestinos idProcedimientoRcbDestinos;
    @JoinColumn(name = "id_orden_suministro", referencedColumnName = "id_orden_suministro")
    @ManyToOne(optional = false)
    private OrdenSuministro idOrdenSuministro;

    public ProcedimientoRcbDestinosOrden() {
    }

    public ProcedimientoRcbDestinosOrden(Integer idProcedimientoRcbDestinosOrden) {
        this.idProcedimientoRcbDestinosOrden = idProcedimientoRcbDestinosOrden;
    }

    public Integer getIdProcedimientoRcbDestinosOrden() {
        return idProcedimientoRcbDestinosOrden;
    }

    public void setIdProcedimientoRcbDestinosOrden(Integer idProcedimientoRcbDestinosOrden) {
        this.idProcedimientoRcbDestinosOrden = idProcedimientoRcbDestinosOrden;
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

    public ProcedimientoRcbDestinos getIdProcedimientoRcbDestinos() {
        return idProcedimientoRcbDestinos;
    }

    public void setIdProcedimientoRcbDestinos(ProcedimientoRcbDestinos idProcedimientoRcbDestinos) {
        this.idProcedimientoRcbDestinos = idProcedimientoRcbDestinos;
    }

    public OrdenSuministro getIdOrdenSuministro() {
        return idOrdenSuministro;
    }

    public void setIdOrdenSuministro(OrdenSuministro idOrdenSuministro) {
        this.idOrdenSuministro = idOrdenSuministro;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idProcedimientoRcbDestinosOrden != null ? idProcedimientoRcbDestinosOrden.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof ProcedimientoRcbDestinosOrden)) {
            return false;
        }
        ProcedimientoRcbDestinosOrden other = (ProcedimientoRcbDestinosOrden) object;
        if ((this.idProcedimientoRcbDestinosOrden == null && other.idProcedimientoRcbDestinosOrden != null) || (this.idProcedimientoRcbDestinosOrden != null && !this.idProcedimientoRcbDestinosOrden.equals(other.idProcedimientoRcbDestinosOrden))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.issste.sicabis.ejb.modelo.ProcedimientoRcbDestinosOrden[ idProcedimientoRcbDestinosOrden=" + idProcedimientoRcbDestinosOrden + " ]";
    }
    
}
