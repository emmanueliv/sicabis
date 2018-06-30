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
@Table(name = "notas_contrato")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "NotasContrato.findAll", query = "SELECT n FROM NotasContrato n"),
    @NamedQuery(name = "NotasContrato.findByIdNotasContrato", query = "SELECT n FROM NotasContrato n WHERE n.idNotasContrato = :idNotasContrato"),
    @NamedQuery(name = "NotasContrato.findByIdContrato", query = "SELECT n FROM NotasContrato n WHERE n.idContrato = :idContrato"),
    @NamedQuery(name = "NotasContrato.findByUsuarioAlta", query = "SELECT n FROM NotasContrato n WHERE n.usuarioAlta = :usuarioAlta"),
    @NamedQuery(name = "NotasContrato.findByUsuarioBaja", query = "SELECT n FROM NotasContrato n WHERE n.usuarioBaja = :usuarioBaja"),
    @NamedQuery(name = "NotasContrato.findByUsuarioModificacion", query = "SELECT n FROM NotasContrato n WHERE n.usuarioModificacion = :usuarioModificacion"),
    @NamedQuery(name = "NotasContrato.findByFechaAlta", query = "SELECT n FROM NotasContrato n WHERE n.fechaAlta = :fechaAlta"),
    @NamedQuery(name = "NotasContrato.findByFechaBaja", query = "SELECT n FROM NotasContrato n WHERE n.fechaBaja = :fechaBaja"),
    @NamedQuery(name = "NotasContrato.findByFechaModificacion", query = "SELECT n FROM NotasContrato n WHERE n.fechaModificacion = :fechaModificacion")})
public class NotasContrato implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id_notas_contrato")
    private Integer idNotasContrato;
    @Basic(optional = false)
    @NotNull
    @Column(name = "id_contrato")
    private int idContrato;
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

    public NotasContrato() {
    }

    public NotasContrato(Integer idNotasContrato) {
        this.idNotasContrato = idNotasContrato;
    }

    public NotasContrato(Integer idNotasContrato, int idContrato) {
        this.idNotasContrato = idNotasContrato;
        this.idContrato = idContrato;
    }

    public Integer getIdNotasContrato() {
        return idNotasContrato;
    }

    public void setIdNotasContrato(Integer idNotasContrato) {
        this.idNotasContrato = idNotasContrato;
    }

    public int getIdContrato() {
        return idContrato;
    }

    public void setIdContrato(int idContrato) {
        this.idContrato = idContrato;
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

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idNotasContrato != null ? idNotasContrato.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof NotasContrato)) {
            return false;
        }
        NotasContrato other = (NotasContrato) object;
        if ((this.idNotasContrato == null && other.idNotasContrato != null) || (this.idNotasContrato != null && !this.idNotasContrato.equals(other.idNotasContrato))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.issste.sicabis.ejb.modelo.NotasContrato[ idNotasContrato=" + idNotasContrato + " ]";
    }
    
}
