/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.issste.sicabis.ejb.service.silodisa.modelo;

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
@Table(name = "claves_por_codigo_barras")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "ClavesPorCodigoBarras.findAll", query = "SELECT c FROM ClavesPorCodigoBarras c"),
    @NamedQuery(name = "ClavesPorCodigoBarras.findByIdClavesPorCodigoBarras", query = "SELECT c FROM ClavesPorCodigoBarras c WHERE c.idClavesPorCodigoBarras = :idClavesPorCodigoBarras"),
    @NamedQuery(name = "ClavesPorCodigoBarras.findByClave", query = "SELECT c FROM ClavesPorCodigoBarras c WHERE c.clave = :clave"),
    @NamedQuery(name = "ClavesPorCodigoBarras.findByNombre", query = "SELECT c FROM ClavesPorCodigoBarras c WHERE c.nombre = :nombre"),
    @NamedQuery(name = "ClavesPorCodigoBarras.findByPartidaPresupuestal", query = "SELECT c FROM ClavesPorCodigoBarras c WHERE c.partidaPresupuestal = :partidaPresupuestal"),
    @NamedQuery(name = "ClavesPorCodigoBarras.findByCrossReference", query = "SELECT c FROM ClavesPorCodigoBarras c WHERE c.crossReference = :crossReference"),
    @NamedQuery(name = "ClavesPorCodigoBarras.findByFechaIngreso", query = "SELECT c FROM ClavesPorCodigoBarras c WHERE c.fechaIngreso = :fechaIngreso")})
public class ClavesPorCodigoBarras implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id_claves_por_codigo_barras")
    private Integer idClavesPorCodigoBarras;
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

    public ClavesPorCodigoBarras() {
    }

    public ClavesPorCodigoBarras(Integer idClavesPorCodigoBarras) {
        this.idClavesPorCodigoBarras = idClavesPorCodigoBarras;
    }

    public Integer getIdClavesPorCodigoBarras() {
        return idClavesPorCodigoBarras;
    }

    public void setIdClavesPorCodigoBarras(Integer idClavesPorCodigoBarras) {
        this.idClavesPorCodigoBarras = idClavesPorCodigoBarras;
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
        hash += (idClavesPorCodigoBarras != null ? idClavesPorCodigoBarras.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof ClavesPorCodigoBarras)) {
            return false;
        }
        ClavesPorCodigoBarras other = (ClavesPorCodigoBarras) object;
        if ((this.idClavesPorCodigoBarras == null && other.idClavesPorCodigoBarras != null) || (this.idClavesPorCodigoBarras != null && !this.idClavesPorCodigoBarras.equals(other.idClavesPorCodigoBarras))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.issste.sicabis.ejb.service.silodisa.modelo.ClavesPorCodigoBarras[ idClavesPorCodigoBarras=" + idClavesPorCodigoBarras + " ]";
    }
    
}
