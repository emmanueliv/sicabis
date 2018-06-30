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
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
@Table(name = "dpn_insumo_tmp")
public class DpnInsumoTmp implements Serializable {
    
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id_dpn_insumo_tmp")
    private Integer idDpnInsumoTmp;
    @Column(name = "id_dpn")
    private Integer idDpn;
    @Column(name = "piezas_dpn")
    private Integer piezasDpn;
    @Column(name = "clave_insumo")
    private String claveInsumo;
    @Column(name = "clave_unidad")
    private String claveUnidad;
    @Column(name = "fecha_ini_1")
    @Temporal(TemporalType.DATE)
    private Date fechaIni1;
    @Column(name = "fecha_ini_2")
    @Temporal(TemporalType.DATE)
    private Date fechaIni2;
    @Column(name = "fecha_ini_3")
    @Temporal(TemporalType.DATE)
    private Date fechaIni3;
    @Column(name = "fecha_fin")
    @Temporal(TemporalType.DATE)
    private Date fechaFin;

    public DpnInsumoTmp() {
    }

    public Integer getIdDpnInsumoTmp() {
        return idDpnInsumoTmp;
    }

    public void setIdDpnInsumoTmp(Integer idDpnInsumoTmp) {
        this.idDpnInsumoTmp = idDpnInsumoTmp;
    }

    public Integer getIdDpn() {
        return idDpn;
    }

    public void setIdDpn(Integer idDpn) {
        this.idDpn = idDpn;
    }

    public Integer getPiezasDpn() {
        return piezasDpn;
    }

    public void setPiezasDpn(Integer piezasDpn) {
        this.piezasDpn = piezasDpn;
    }

    public String getClaveInsumo() {
        return claveInsumo;
    }

    public void setClaveInsumo(String claveInsumo) {
        this.claveInsumo = claveInsumo;
    }

    public String getClaveUnidad() {
        return claveUnidad;
    }

    public void setClaveUnidad(String claveUnidad) {
        this.claveUnidad = claveUnidad;
    }

    public Date getFechaIni1() {
        return fechaIni1;
    }

    public void setFechaIni1(Date fechaIni1) {
        this.fechaIni1 = fechaIni1;
    }

    public Date getFechaIni2() {
        return fechaIni2;
    }

    public void setFechaIni2(Date fechaIni2) {
        this.fechaIni2 = fechaIni2;
    }

    public Date getFechaIni3() {
        return fechaIni3;
    }

    public void setFechaIni3(Date fechaIni3) {
        this.fechaIni3 = fechaIni3;
    }

    public Date getFechaFin() {
        return fechaFin;
    }

    public void setFechaFin(Date fechaFin) {
        this.fechaFin = fechaFin;
    }
    
}
