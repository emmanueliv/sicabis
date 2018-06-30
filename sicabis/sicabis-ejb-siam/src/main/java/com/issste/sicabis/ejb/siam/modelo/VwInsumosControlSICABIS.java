/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.issste.sicabis.ejb.siam.modelo;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Manolo
 */
@Entity
@Table(name = "vwInsumos_ControlSICABIS")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "VwInsumosControlSICABIS.findAll", query = "SELECT v FROM VwInsumosControlSICABIS v"),
    @NamedQuery(name = "VwInsumosControlSICABIS.findByClave", query = "SELECT v FROM VwInsumosControlSICABIS v WHERE v.clave = :clave"),
    @NamedQuery(name = "VwInsumosControlSICABIS.findByDescMed", query = "SELECT v FROM VwInsumosControlSICABIS v WHERE v.descMed = :descMed"),
    @NamedQuery(name = "VwInsumosControlSICABIS.findByCantidadMinima", query = "SELECT v FROM VwInsumosControlSICABIS v WHERE v.cantidadMinima = :cantidadMinima"),
    @NamedQuery(name = "VwInsumosControlSICABIS.findByCantidadMaxima", query = "SELECT v FROM VwInsumosControlSICABIS v WHERE v.cantidadMaxima = :cantidadMaxima"),
    @NamedQuery(name = "VwInsumosControlSICABIS.findByDias", query = "SELECT v FROM VwInsumosControlSICABIS v WHERE v.dias = :dias"),
    @NamedQuery(name = "VwInsumosControlSICABIS.findByDescNivel", query = "SELECT v FROM VwInsumosControlSICABIS v WHERE v.descNivel = :descNivel")})
public class VwInsumosControlSICABIS implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Size(min = 1, max = 12)
    @Column(name = "clave")
    private String clave;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 255)
    @Column(name = "desc_med")
    private String descMed;
    @Basic(optional = false)
    @NotNull
    @Column(name = "cantidad_minima")
    private int cantidadMinima;
    @Basic(optional = false)
    @NotNull
    @Column(name = "cantidad_maxima")
    private int cantidadMaxima;
    @Basic(optional = false)
    @NotNull
    @Column(name = "dias")
    private int dias;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 50)
    @Column(name = "desc_nivel")
    private String descNivel;

    public VwInsumosControlSICABIS() {
    }

    public String getClave() {
        return clave;
    }

    public void setClave(String clave) {
        this.clave = clave;
    }

    public String getDescMed() {
        return descMed;
    }

    public void setDescMed(String descMed) {
        this.descMed = descMed;
    }

    public int getCantidadMinima() {
        return cantidadMinima;
    }

    public void setCantidadMinima(int cantidadMinima) {
        this.cantidadMinima = cantidadMinima;
    }

    public int getCantidadMaxima() {
        return cantidadMaxima;
    }

    public void setCantidadMaxima(int cantidadMaxima) {
        this.cantidadMaxima = cantidadMaxima;
    }

    public int getDias() {
        return dias;
    }

    public void setDias(int dias) {
        this.dias = dias;
    }

    public String getDescNivel() {
        return descNivel;
    }

    public void setDescNivel(String descNivel) {
        this.descNivel = descNivel;
    }
    
}
