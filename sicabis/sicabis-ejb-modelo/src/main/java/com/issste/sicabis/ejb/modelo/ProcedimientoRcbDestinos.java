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
 * @author kriosoft
 */
@Entity
@Table(name = "procedimiento_rcb_destinos")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "ProcedimientoRcbDestinos.findAll", query = "SELECT p FROM ProcedimientoRcbDestinos p"),
    @NamedQuery(name = "ProcedimientoRcbDestinos.findByIdProcedimientoRcbDestinos", query = "SELECT p FROM ProcedimientoRcbDestinos p WHERE p.idProcedimientoRcbDestinos = :idProcedimientoRcbDestinos"),
    @NamedQuery(name = "ProcedimientoRcbDestinos.findByActivo", query = "SELECT p FROM ProcedimientoRcbDestinos p WHERE p.activo = :activo"),
    @NamedQuery(name = "ProcedimientoRcbDestinos.findByUsuarioAlta", query = "SELECT p FROM ProcedimientoRcbDestinos p WHERE p.usuarioAlta = :usuarioAlta"),
    @NamedQuery(name = "ProcedimientoRcbDestinos.findByUsuarioBaja", query = "SELECT p FROM ProcedimientoRcbDestinos p WHERE p.usuarioBaja = :usuarioBaja"),
    @NamedQuery(name = "ProcedimientoRcbDestinos.findByUsuarioModificacion", query = "SELECT p FROM ProcedimientoRcbDestinos p WHERE p.usuarioModificacion = :usuarioModificacion"),
    @NamedQuery(name = "ProcedimientoRcbDestinos.findByFechaAlta", query = "SELECT p FROM ProcedimientoRcbDestinos p WHERE p.fechaAlta = :fechaAlta"),
    @NamedQuery(name = "ProcedimientoRcbDestinos.findByFechaBaja", query = "SELECT p FROM ProcedimientoRcbDestinos p WHERE p.fechaBaja = :fechaBaja"),
    @NamedQuery(name = "ProcedimientoRcbDestinos.findByFechaModificacion", query = "SELECT p FROM ProcedimientoRcbDestinos p WHERE p.fechaModificacion = :fechaModificacion")})
public class ProcedimientoRcbDestinos implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id_procedimiento_rcb_destinos")
    private Integer idProcedimientoRcbDestinos;
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
    @JoinColumn(name = "id_procedimiento_rcb", referencedColumnName = "id_procedimiento_rcb")
    @ManyToOne(optional = false)
    private ProcedimientoRcb idProcedimientoRcb;
    @JoinColumn(name = "id_destino", referencedColumnName = "id_destino")
    @ManyToOne(optional = false)
    private Destinos idDestino;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idProcedimientoRcbDestinos")
    private List<ProcedimientoRcbDestinosOrden> procedimientoRcbDestinosOrdenList;

    public ProcedimientoRcbDestinos() {
    }

    public ProcedimientoRcbDestinos(Integer idProcedimientoRcbDestinos) {
        this.idProcedimientoRcbDestinos = idProcedimientoRcbDestinos;
    }

    public Integer getIdProcedimientoRcbDestinos() {
        return idProcedimientoRcbDestinos;
    }

    public void setIdProcedimientoRcbDestinos(Integer idProcedimientoRcbDestinos) {
        this.idProcedimientoRcbDestinos = idProcedimientoRcbDestinos;
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

    public ProcedimientoRcb getIdProcedimientoRcb() {
        return idProcedimientoRcb;
    }

    public void setIdProcedimientoRcb(ProcedimientoRcb idProcedimientoRcb) {
        this.idProcedimientoRcb = idProcedimientoRcb;
    }

    public Destinos getIdDestino() {
        return idDestino;
    }

    public void setIdDestino(Destinos idDestino) {
        this.idDestino = idDestino;
    }

    @XmlTransient
    public List<ProcedimientoRcbDestinosOrden> getProcedimientoRcbDestinosOrdenList() {
        return procedimientoRcbDestinosOrdenList;
    }

    public void setProcedimientoRcbDestinosOrdenList(List<ProcedimientoRcbDestinosOrden> procedimientoRcbDestinosOrdenList) {
        this.procedimientoRcbDestinosOrdenList = procedimientoRcbDestinosOrdenList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idProcedimientoRcbDestinos != null ? idProcedimientoRcbDestinos.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof ProcedimientoRcbDestinos)) {
            return false;
        }
        ProcedimientoRcbDestinos other = (ProcedimientoRcbDestinos) object;
        if ((this.idProcedimientoRcbDestinos == null && other.idProcedimientoRcbDestinos != null) || (this.idProcedimientoRcbDestinos != null && !this.idProcedimientoRcbDestinos.equals(other.idProcedimientoRcbDestinos))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.issste.sicabis.ejb.modelo.ProcedimientoRcbDestinos[ idProcedimientoRcbDestinos=" + idProcedimientoRcbDestinos + " ]";
    }
    
}
