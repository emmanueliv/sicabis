/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.issste.sicabis.ejb.modelo;

import java.io.Serializable;
import java.math.BigDecimal;
import java.math.RoundingMode;
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
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author 6JWBBG2
 */
@Entity
@Table(name = "porcentaje_delegacion")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "PorcentajeDelegacion.findAll", query = "SELECT p FROM PorcentajeDelegacion p"),
    @NamedQuery(name = "PorcentajeDelegacion.findByIdPorcentajeDelegacion", query = "SELECT p FROM PorcentajeDelegacion p WHERE p.idPorcentajeDelegacion = :idPorcentajeDelegacion"),
//    @NamedQuery(name = "PorcentajeDelegacion.findByClaveDelegacion", query = "SELECT p FROM PorcentajeDelegacion p WHERE p.claveDelegacion = :claveDelegacion"),
    @NamedQuery(name = "PorcentajeDelegacion.findByPorcentaje", query = "SELECT p FROM PorcentajeDelegacion p WHERE p.porcentaje = :porcentaje"),
    @NamedQuery(name = "PorcentajeDelegacion.findByIdDelegacion", query = "SELECT p FROM PorcentajeDelegacion p WHERE p.idDelegacion = :idDelegacion")})
public class PorcentajeDelegacion implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id_porcentaje_delegacion")
    private Integer idPorcentajeDelegacion;
    @Size(max = 10)
    @Column(name = "claves_DPN")
    private String clavesDPN;
    @Size(max = 10)
    @Column(name = "claves_en_UMU")
    private String clavesEnUMU;
//    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Column(name = "porcentaje")
    private BigDecimal porcentaje;
    @Column(name = "fecha_actualizacion")
    @Temporal(TemporalType.DATE)
    private Date fechaActualizacion;
    @JoinColumn(name = "id_delegacion", referencedColumnName = "id_delegacion")
    @ManyToOne(optional = false)
    private Delegaciones idDelegacion;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idPorcentajeDelegacion")
    private List<DetalleDelegacion> detalleDelegacionList;
    @JoinColumn(name = "id_indicador", referencedColumnName = "id_indicador")
    @ManyToOne(optional = false)
    private Indicador idIndicador;

    public PorcentajeDelegacion() {
    }

    public PorcentajeDelegacion(Integer idPorcentajeDelegacion) {
        this.idPorcentajeDelegacion = idPorcentajeDelegacion;
    }

    public Integer getIdPorcentajeDelegacion() {
        return idPorcentajeDelegacion;
    }

    public void setIdPorcentajeDelegacion(Integer idPorcentajeDelegacion) {
        this.idPorcentajeDelegacion = idPorcentajeDelegacion;
    }

    public BigDecimal getPorcentaje() {
        return porcentaje.setScale(2, RoundingMode.HALF_UP);
    }

    public void setPorcentaje(BigDecimal porcentaje) {
        this.porcentaje = porcentaje.setScale(2, RoundingMode.HALF_UP);
    }

    public Delegaciones getIdDelegacion() {
        return idDelegacion;
    }

    public void setIdDelegacion(Delegaciones idDelegacion) {
        this.idDelegacion = idDelegacion;
    }

    public List<DetalleDelegacion> getDetalleDelegacionList() {
        return detalleDelegacionList;
    }

    public void setDetalleDelegacionList(List<DetalleDelegacion> detalleDelegacionList) {
        this.detalleDelegacionList = detalleDelegacionList;
    }

    public Indicador getIdIndicador() {
        return idIndicador;
    }

    public void setIdIndicador(Indicador idIndicador) {
        this.idIndicador = idIndicador;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idPorcentajeDelegacion != null ? idPorcentajeDelegacion.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof PorcentajeDelegacion)) {
            return false;
        }
        PorcentajeDelegacion other = (PorcentajeDelegacion) object;
        if ((this.idPorcentajeDelegacion == null && other.idPorcentajeDelegacion != null) || (this.idPorcentajeDelegacion != null && !this.idPorcentajeDelegacion.equals(other.idPorcentajeDelegacion))) {
            return false;
        }
        return true;
    }

    public Date getFechaActualizacion() {
        return fechaActualizacion;
    }

    public void setFechaActualizacion(Date fechaActualizacion) {
        this.fechaActualizacion = fechaActualizacion;
    }

    public String getClavesDPN() {
        return clavesDPN;
    }

    public void setClavesDPN(String clavesDPN) {
        this.clavesDPN = clavesDPN;
    }

    public String getClavesEnUMU() {
        return clavesEnUMU;
    }

    public void setClavesEnUMU(String clavesEnUMU) {
        this.clavesEnUMU = clavesEnUMU;
    }

    
    @Override
    public String toString() {
        return "com.issste.sicabis.ejb.modelo.PorcentajeDelegacion[ idPorcentajeDelegacion=" + idPorcentajeDelegacion + " ]";
    }

}
