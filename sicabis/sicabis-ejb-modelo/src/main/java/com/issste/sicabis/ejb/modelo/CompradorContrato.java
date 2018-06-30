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
@Table(name = "comprador_contrato")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "CompradorContrato.findAll", query = "SELECT c FROM CompradorContrato c"),
    @NamedQuery(name = "CompradorContrato.findByIdCompradorContrato", query = "SELECT c FROM CompradorContrato c WHERE c.idCompradorContrato = :idCompradorContrato"),
    @NamedQuery(name = "CompradorContrato.findByActivo", query = "SELECT c FROM CompradorContrato c WHERE c.activo = :activo"),
    @NamedQuery(name = "CompradorContrato.findByIdContrato", query = "SELECT c FROM CompradorContrato c WHERE c.idContrato.idContrato = :idContrato"),
    @NamedQuery(name = "CompradorContrato.findByUsuarioAlta", query = "SELECT c FROM CompradorContrato c WHERE c.usuarioAlta = :usuarioAlta"),
    @NamedQuery(name = "CompradorContrato.findByUsuarioBaja", query = "SELECT c FROM CompradorContrato c WHERE c.usuarioBaja = :usuarioBaja"),
    @NamedQuery(name = "CompradorContrato.findByUsuarioModificacion", query = "SELECT c FROM CompradorContrato c WHERE c.usuarioModificacion = :usuarioModificacion"),
    @NamedQuery(name = "CompradorContrato.findByFechaAlta", query = "SELECT c FROM CompradorContrato c WHERE c.fechaAlta = :fechaAlta"),
    @NamedQuery(name = "CompradorContrato.findByFechaBaja", query = "SELECT c FROM CompradorContrato c WHERE c.fechaBaja = :fechaBaja"),
    @NamedQuery(name = "CompradorContrato.findByFechaModificacion", query = "SELECT c FROM CompradorContrato c WHERE c.fechaModificacion = :fechaModificacion")})
public class CompradorContrato implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id_comprador_contrato")
    private Integer idCompradorContrato;
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
    @JoinColumn(name = "id_comprador", referencedColumnName = "id_comprador")
    @ManyToOne(optional = false)
    private Compradores idComprador;
    @JoinColumn(name = "id_contrato", referencedColumnName = "id_contrato")
    @ManyToOne(optional = false)
    private Contratos idContrato;

    public CompradorContrato() {
    }

    public CompradorContrato(Integer idCompradorContrato) {
        this.idCompradorContrato = idCompradorContrato;
    }

    public Integer getIdCompradorContrato() {
        return idCompradorContrato;
    }

    public void setIdCompradorContrato(Integer idCompradorContrato) {
        this.idCompradorContrato = idCompradorContrato;
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

    public Compradores getIdComprador() {
        return idComprador;
    }

    public void setIdComprador(Compradores idComprador) {
        this.idComprador = idComprador;
    }

    public Contratos getIdContrato() {
        return idContrato;
    }

    public void setIdContrato(Contratos idContrato) {
        this.idContrato = idContrato;
    }
    
    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idCompradorContrato != null ? idCompradorContrato.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof CompradorContrato)) {
            return false;
        }
        CompradorContrato other = (CompradorContrato) object;
        if ((this.idCompradorContrato == null && other.idCompradorContrato != null) || (this.idCompradorContrato != null && !this.idCompradorContrato.equals(other.idCompradorContrato))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.issste.sicabis.ejb.modelo.CompradorContrato[ idCompradorContrato=" + idCompradorContrato + " ]";
    }
    
}
