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
@Table(name = "estatus_siguiente")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "EstatusSiguiente.findAll", query = "SELECT e FROM EstatusSiguiente e"),
    @NamedQuery(name = "EstatusSiguiente.findByIdEstatusSiguiente", query = "SELECT e FROM EstatusSiguiente e WHERE e.idEstatusSiguiente = :idEstatusSiguiente"),
    @NamedQuery(name = "EstatusSiguiente.findByActivo", query = "SELECT e FROM EstatusSiguiente e WHERE e.activo = :activo"),
    @NamedQuery(name = "EstatusSiguiente.findByUsuarioAlta", query = "SELECT e FROM EstatusSiguiente e WHERE e.usuarioAlta = :usuarioAlta"),
    @NamedQuery(name = "EstatusSiguiente.findByUsuarioBaja", query = "SELECT e FROM EstatusSiguiente e WHERE e.usuarioBaja = :usuarioBaja"),
    @NamedQuery(name = "EstatusSiguiente.findByUsuarioModificacion", query = "SELECT e FROM EstatusSiguiente e WHERE e.usuarioModificacion = :usuarioModificacion"),
    @NamedQuery(name = "EstatusSiguiente.findByFechaAlta", query = "SELECT e FROM EstatusSiguiente e WHERE e.fechaAlta = :fechaAlta"),
    @NamedQuery(name = "EstatusSiguiente.findByFechaBaja", query = "SELECT e FROM EstatusSiguiente e WHERE e.fechaBaja = :fechaBaja"),
    @NamedQuery(name = "EstatusSiguiente.findByFechaModificacion", query = "SELECT e FROM EstatusSiguiente e WHERE e.fechaModificacion = :fechaModificacion"),
    @NamedQuery(name = "EstatusSiguiente.findByIdTarea", query = "SELECT e FROM EstatusSiguiente e WHERE e.idTarea = :idTarea")})
public class EstatusSiguiente implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id_estatus_siguiente")
    private Integer idEstatusSiguiente;
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
    @Basic(optional = false)
    @NotNull
    @Column(name = "id_tarea")
    private int idTarea;
    @JoinColumn(name = "estatus_final", referencedColumnName = "id_estatus")
    @ManyToOne(optional = false)
    private Estatus estatusFinal;
    @JoinColumn(name = "estatus_inicial", referencedColumnName = "id_estatus")
    @ManyToOne(optional = false)
    private Estatus estatusInicial;

    public EstatusSiguiente() {
    }

    public EstatusSiguiente(Integer idEstatusSiguiente) {
        this.idEstatusSiguiente = idEstatusSiguiente;
    }

    public EstatusSiguiente(Integer idEstatusSiguiente, int idTarea) {
        this.idEstatusSiguiente = idEstatusSiguiente;
        this.idTarea = idTarea;
    }

    public Integer getIdEstatusSiguiente() {
        return idEstatusSiguiente;
    }

    public void setIdEstatusSiguiente(Integer idEstatusSiguiente) {
        this.idEstatusSiguiente = idEstatusSiguiente;
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

    public int getIdTarea() {
        return idTarea;
    }

    public void setIdTarea(int idTarea) {
        this.idTarea = idTarea;
    }

    public Estatus getEstatusFinal() {
        return estatusFinal;
    }

    public void setEstatusFinal(Estatus estatusFinal) {
        this.estatusFinal = estatusFinal;
    }

    public Estatus getEstatusInicial() {
        return estatusInicial;
    }

    public void setEstatusInicial(Estatus estatusInicial) {
        this.estatusInicial = estatusInicial;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idEstatusSiguiente != null ? idEstatusSiguiente.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof EstatusSiguiente)) {
            return false;
        }
        EstatusSiguiente other = (EstatusSiguiente) object;
        if ((this.idEstatusSiguiente == null && other.idEstatusSiguiente != null) || (this.idEstatusSiguiente != null && !this.idEstatusSiguiente.equals(other.idEstatusSiguiente))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.issste.sicabis.ejb.modelo.EstatusSiguiente[ idEstatusSiguiente=" + idEstatusSiguiente + " ]";
    }
    
}
