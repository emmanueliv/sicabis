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
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author 9RZCBG2
 */
@Entity
@Table(name = "claves_por_codigo_barras_historico")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "ClavesPorCodigoBarrasHistorico.findAll", query = "SELECT c FROM ClavesPorCodigoBarrasHistorico c"),
    @NamedQuery(name = "ClavesPorCodigoBarrasHistorico.findByIdClavesPorCodigoBarrasHistorico", query = "SELECT c FROM ClavesPorCodigoBarrasHistorico c WHERE c.idClavesPorCodigoBarrasHistorico = :idClavesPorCodigoBarrasHistorico"),
    @NamedQuery(name = "ClavesPorCodigoBarrasHistorico.findByClave", query = "SELECT c FROM ClavesPorCodigoBarrasHistorico c WHERE c.clave = :clave"),
    @NamedQuery(name = "ClavesPorCodigoBarrasHistorico.findByNombre", query = "SELECT c FROM ClavesPorCodigoBarrasHistorico c WHERE c.nombre = :nombre"),
    @NamedQuery(name = "ClavesPorCodigoBarrasHistorico.findByPartidaPresupuestal", query = "SELECT c FROM ClavesPorCodigoBarrasHistorico c WHERE c.partidaPresupuestal = :partidaPresupuestal"),
    @NamedQuery(name = "ClavesPorCodigoBarrasHistorico.findByCrossReference", query = "SELECT c FROM ClavesPorCodigoBarrasHistorico c WHERE c.crossReference = :crossReference"),
    @NamedQuery(name = "ClavesPorCodigoBarrasHistorico.findByFechaIngreso", query = "SELECT c FROM ClavesPorCodigoBarrasHistorico c WHERE c.fechaIngreso = :fechaIngreso")})
public class ClavesPorCodigoBarrasHistorico implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id_claves_por_codigo_barras_historico")
    private Integer idClavesPorCodigoBarrasHistorico;
    @Size(max = 250)
    @Column(name = "clave")
    private String clave;
    @Size(max = 250)
    @Column(name = "nombre")
    private String nombre;
    @Size(max = 250)
    @Column(name = "partida_presupuestal")
    private String partidaPresupuestal;
    @Size(max = 250)
    @Column(name = "cross_reference")
    private String crossReference;
    @Column(name = "fecha_ingreso")
    @Temporal(TemporalType.DATE)
    private Date fechaIngreso;

    public ClavesPorCodigoBarrasHistorico() {
    }

    public ClavesPorCodigoBarrasHistorico(Integer idClavesPorCodigoBarrasHistorico) {
        this.idClavesPorCodigoBarrasHistorico = idClavesPorCodigoBarrasHistorico;
    }

    public Integer getIdClavesPorCodigoBarrasHistorico() {
        return idClavesPorCodigoBarrasHistorico;
    }

    public void setIdClavesPorCodigoBarrasHistorico(Integer idClavesPorCodigoBarrasHistorico) {
        this.idClavesPorCodigoBarrasHistorico = idClavesPorCodigoBarrasHistorico;
    }

    public String getClave() {
        return clave;
    }

    public void setClave(String clave) {
        this.clave = clave;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getPartidaPresupuestal() {
        return partidaPresupuestal;
    }

    public void setPartidaPresupuestal(String partidaPresupuestal) {
        this.partidaPresupuestal = partidaPresupuestal;
    }

    public String getCrossReference() {
        return crossReference;
    }

    public void setCrossReference(String crossReference) {
        this.crossReference = crossReference;
    }

    public Date getFechaIngreso() {
        return fechaIngreso;
    }

    public void setFechaIngreso(Date fechaIngreso) {
        this.fechaIngreso = fechaIngreso;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idClavesPorCodigoBarrasHistorico != null ? idClavesPorCodigoBarrasHistorico.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof ClavesPorCodigoBarrasHistorico)) {
            return false;
        }
        ClavesPorCodigoBarrasHistorico other = (ClavesPorCodigoBarrasHistorico) object;
        if ((this.idClavesPorCodigoBarrasHistorico == null && other.idClavesPorCodigoBarrasHistorico != null) || (this.idClavesPorCodigoBarrasHistorico != null && !this.idClavesPorCodigoBarrasHistorico.equals(other.idClavesPorCodigoBarrasHistorico))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.issste.sicabis.ejb.modelo.ClavesPorCodigoBarrasHistorico[ idClavesPorCodigoBarrasHistorico=" + idClavesPorCodigoBarrasHistorico + " ]";
    }
    
}
