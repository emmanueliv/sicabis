/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.issste.sicabis.ejb.modelo;

import java.io.Serializable;
import java.math.BigDecimal;
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

/**
 *
 * @author 9RZCBG2
 */
@Entity
@Table(name = "porcentaje_delegacion_historico")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "PorcentajeDelegacionHistorico.findAll", query = "SELECT p FROM PorcentajeDelegacionHistorico p"),
    @NamedQuery(name = "PorcentajeDelegacionHistorico.findByIdPorcentajeDelegacionHistorico", query = "SELECT p FROM PorcentajeDelegacionHistorico p WHERE p.idPorcentajeDelegacionHistorico = :idPorcentajeDelegacionHistorico"),
    @NamedQuery(name = "PorcentajeDelegacionHistorico.findByClavesUmu", query = "SELECT p FROM PorcentajeDelegacionHistorico p WHERE p.clavesUmu = :clavesUmu"),
    @NamedQuery(name = "PorcentajeDelegacionHistorico.findByPorcentaje", query = "SELECT p FROM PorcentajeDelegacionHistorico p WHERE p.porcentaje = :porcentaje"),
    @NamedQuery(name = "PorcentajeDelegacionHistorico.findByIdDelegacion", query = "SELECT p FROM PorcentajeDelegacionHistorico p WHERE p.idDelegacion = :idDelegacion"),
    @NamedQuery(name = "PorcentajeDelegacionHistorico.findByFechaActualizacion", query = "SELECT p FROM PorcentajeDelegacionHistorico p WHERE p.fechaActualizacion = :fechaActualizacion")})
public class PorcentajeDelegacionHistorico implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id_porcentaje_delegacion_historico")
    private Integer idPorcentajeDelegacionHistorico;
    @Column(name = "claves_dpn")
    private String clavesDpn;
    @Column(name = "claves_en_umu")
    private String clavesUmu;
    @Column(name = "porcentaje")
    private BigDecimal porcentaje;
    @Column(name = "fecha_actualizacion")
    @Temporal(TemporalType.DATE)
    private Date fechaActualizacion;
    @JoinColumn(name = "id_delegacion", referencedColumnName = "id_delegacion")
    @ManyToOne(optional = false)
    private Delegaciones idDelegacion;
    @JoinColumn(name = "id_indicador", referencedColumnName = "id_indicador")
    @ManyToOne(optional = false)
    private Indicador idIndicador;

    public PorcentajeDelegacionHistorico() {
    }

    public PorcentajeDelegacionHistorico(Integer idPorcentajeDelegacionHistorico) {
        this.idPorcentajeDelegacionHistorico = idPorcentajeDelegacionHistorico;
    }

    public Integer getIdPorcentajeDelegacionHistorico() {
        return idPorcentajeDelegacionHistorico;
    }

    public void setIdPorcentajeDelegacionHistorico(Integer idPorcentajeDelegacionHistorico) {
        this.idPorcentajeDelegacionHistorico = idPorcentajeDelegacionHistorico;
    }

    public BigDecimal getPorcentaje() {
        return porcentaje;
    }

    public void setPorcentaje(BigDecimal porcentaje) {
        this.porcentaje = porcentaje;
    }

    public Date getFechaActualizacion() {
        return fechaActualizacion;
    }

    public void setFechaActualizacion(Date fechaActualizacion) {
        this.fechaActualizacion = fechaActualizacion;
    }

    public Delegaciones getIdDelegacion() {
        return idDelegacion;
    }

    public void setIdDelegacion(Delegaciones idDelegacion) {
        this.idDelegacion = idDelegacion;
    }

    public Indicador getIdIndicador() {
        return idIndicador;
    }

    public void setIdIndicador(Indicador idIndicador) {
        this.idIndicador = idIndicador;
    }

    public String getClavesDpn() {
        return clavesDpn;
    }

    public void setClavesDpn(String clavesDpn) {
        this.clavesDpn = clavesDpn;
    }

    public String getClavesUmu() {
        return clavesUmu;
    }

    public void setClavesUmu(String clavesUmu) {
        this.clavesUmu = clavesUmu;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idPorcentajeDelegacionHistorico != null ? idPorcentajeDelegacionHistorico.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof PorcentajeDelegacionHistorico)) {
            return false;
        }
        PorcentajeDelegacionHistorico other = (PorcentajeDelegacionHistorico) object;
        if ((this.idPorcentajeDelegacionHistorico == null && other.idPorcentajeDelegacionHistorico != null) || (this.idPorcentajeDelegacionHistorico != null && !this.idPorcentajeDelegacionHistorico.equals(other.idPorcentajeDelegacionHistorico))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.issste.sicabis.ejb.modelo.PorcentajeDelegacionHistorico[ idPorcentajeDelegacionHistorico=" + idPorcentajeDelegacionHistorico + " ]";
    }

}
