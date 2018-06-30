/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.issste.sicabis.ejb.modelo;

import java.io.Serializable;
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
import javax.validation.constraints.NotNull;

/**
 *
 * @author Toshiba Manolo
 */
@Entity
@Table(name = "asignacion_insumos")
@NamedQueries({
    @NamedQuery(name = "AsignacionInsumos.findAll", query = "SELECT a FROM AsignacionInsumos a"),
    @NamedQuery(name = "AsignacionInsumos.findByIdAsignacionInsumos", query = "SELECT a FROM AsignacionInsumos a WHERE a.idAsignacionInsumos = :idAsignacionInsumos")})
public class AsignacionInsumos implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id_asignacion_insumos")
    private Integer idAsignacionInsumos;
    @JoinColumn(name = "id_insumo", referencedColumnName = "id_insumo")
    @ManyToOne(optional = false)
    private Insumos idInsumo;   
    @JoinColumn(name = "id_area", referencedColumnName = "id_area")
    @ManyToOne(optional = false)
    private Area idArea;
    @JoinColumn(name = "id_programa", referencedColumnName = "id_programa")
    @ManyToOne(optional = false)
    private Programas idPrograma;

    public AsignacionInsumos() {
    }

    public AsignacionInsumos(Integer idAsignacionInsumos) {
        this.idAsignacionInsumos = idAsignacionInsumos;
    }

    public Integer getIdAsignacionInsumos() {
        return idAsignacionInsumos;
    }

    public void setIdAsignacionInsumos(Integer idAsignacionInsumos) {
        this.idAsignacionInsumos = idAsignacionInsumos;
    }

    public Insumos getIdInsumo() {
        return idInsumo;
    }

    public void setIdInsumo(Insumos idInsumo) {
        this.idInsumo = idInsumo;
    }

    public Area getIdArea() {
        return idArea;
    }

    public void setIdArea(Area idArea) {
        this.idArea = idArea;
    }

    public Programas getIdPrograma() {
        return idPrograma;
    }

    public void setIdPrograma(Programas idPrograma) {
        this.idPrograma = idPrograma;
    }

   

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idAsignacionInsumos != null ? idAsignacionInsumos.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof AsignacionInsumos)) {
            return false;
        }
        AsignacionInsumos other = (AsignacionInsumos) object;
        if ((this.idAsignacionInsumos == null && other.idAsignacionInsumos != null) || (this.idAsignacionInsumos != null && !this.idAsignacionInsumos.equals(other.idAsignacionInsumos))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.issste.sicabis.ejb.modelo.AsignacionInsumos[ idAsignacionInsumos=" + idAsignacionInsumos + " ]";
    }
    
}
