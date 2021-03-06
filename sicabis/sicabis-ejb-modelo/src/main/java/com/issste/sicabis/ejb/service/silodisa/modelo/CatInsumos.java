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
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author 9RZCBG2
 */
@Entity
@Table(name = "cat_insumos")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "CatInsumos.findAll", query = "SELECT c FROM CatInsumos c"),
    @NamedQuery(name = "CatInsumos.findByIdCatInsumos", query = "SELECT c FROM CatInsumos c WHERE c.idCatInsumos = :idCatInsumos"),
    @NamedQuery(name = "CatInsumos.findByClave", query = "SELECT c FROM CatInsumos c WHERE c.clave = :clave"),
    @NamedQuery(name = "CatInsumos.findByDescripcion", query = "SELECT c FROM CatInsumos c WHERE c.descripcion = :descripcion"),
    @NamedQuery(name = "CatInsumos.findByDescripcionLarga", query = "SELECT c FROM CatInsumos c WHERE c.descripcionLarga = :descripcionLarga"),
    @NamedQuery(name = "CatInsumos.findByTipoCarga", query = "SELECT c FROM CatInsumos c WHERE c.tipoCarga = :tipoCarga"),
    @NamedQuery(name = "CatInsumos.findByTipoMedicamento", query = "SELECT c FROM CatInsumos c WHERE c.tipoMedicamento = :tipoMedicamento"),
    @NamedQuery(name = "CatInsumos.findByPeso", query = "SELECT c FROM CatInsumos c WHERE c.peso = :peso"),
    @NamedQuery(name = "CatInsumos.findBySubinventario", query = "SELECT c FROM CatInsumos c WHERE c.subinventario = :subinventario"),
    @NamedQuery(name = "CatInsumos.findByPartidaPresupuestal", query = "SELECT c FROM CatInsumos c WHERE c.partidaPresupuestal = :partidaPresupuestal"),
    @NamedQuery(name = "CatInsumos.findByFechaIngreso", query = "SELECT c FROM CatInsumos c WHERE c.fechaIngreso = :fechaIngreso")})
public class CatInsumos implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id_cat_insumos")
    private Integer idCatInsumos;
    @Size(max = 250)
    @Column(name = "clave")
    private String clave;
    @Size(max = 250)
    @Column(name = "descripcion")
    private String descripcion;
    @Size(max = 5000)
    @Column(name = "descripcion_larga")
    private String descripcionLarga;
    @Size(max = 250)
    @Column(name = "tipo_carga")
    private String tipoCarga;
    @Size(max = 250)
    @Column(name = "tipo_medicamento")
    private String tipoMedicamento;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 250)
    @Column(name = "peso")
    private String peso;
    @Size(max = 250)
    @Column(name = "subinventario")
    private String subinventario;
    @Size(max = 250)
    @Column(name = "partida_presupuestal")
    private String partidaPresupuestal;
    @Column(name = "fecha_ingreso")
    @Temporal(TemporalType.DATE)
    private Date fechaIngreso;

    public CatInsumos() {
    }

    public CatInsumos(Integer idCatInsumos) {
        this.idCatInsumos = idCatInsumos;
    }

    public CatInsumos(Integer idCatInsumos, String peso) {
        this.idCatInsumos = idCatInsumos;
        this.peso = peso;
    }

    public Integer getIdCatInsumos() {
        return idCatInsumos;
    }

    public void setIdCatInsumos(Integer idCatInsumos) {
        this.idCatInsumos = idCatInsumos;
    }

    public String getClave() {
        return clave;
    }

    public void setClave(String clave) {
        this.clave = clave;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getDescripcionLarga() {
        return descripcionLarga;
    }

    public void setDescripcionLarga(String descripcionLarga) {
        this.descripcionLarga = descripcionLarga;
    }

    public String getTipoCarga() {
        return tipoCarga;
    }

    public void setTipoCarga(String tipoCarga) {
        this.tipoCarga = tipoCarga;
    }

    public String getTipoMedicamento() {
        return tipoMedicamento;
    }

    public void setTipoMedicamento(String tipoMedicamento) {
        this.tipoMedicamento = tipoMedicamento;
    }

    public String getPeso() {
        return peso;
    }

    public void setPeso(String peso) {
        this.peso = peso;
    }

    public String getSubinventario() {
        return subinventario;
    }

    public void setSubinventario(String subinventario) {
        this.subinventario = subinventario;
    }

    public String getPartidaPresupuestal() {
        return partidaPresupuestal;
    }

    public void setPartidaPresupuestal(String partidaPresupuestal) {
        this.partidaPresupuestal = partidaPresupuestal;
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
        hash += (idCatInsumos != null ? idCatInsumos.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof CatInsumos)) {
            return false;
        }
        CatInsumos other = (CatInsumos) object;
        if ((this.idCatInsumos == null && other.idCatInsumos != null) || (this.idCatInsumos != null && !this.idCatInsumos.equals(other.idCatInsumos))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.issste.sicabis.ejb.service.silodisa.modelo.CatInsumos[ idCatInsumos=" + idCatInsumos + " ]";
    }
    
}
