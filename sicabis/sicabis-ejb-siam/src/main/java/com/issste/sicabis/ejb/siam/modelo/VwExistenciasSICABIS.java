/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.issste.sicabis.ejb.siam.modelo;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
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
 * @author Manolo
 */
@Entity
@Table(name = "vwExistenciasSICABIS")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "VwExistenciasSICABIS.findAll", query = "SELECT v FROM VwExistenciasSICABIS v"),
    @NamedQuery(name = "VwExistenciasSICABIS.findByClaveUnidad", query = "SELECT v FROM VwExistenciasSICABIS v WHERE v.claveUnidad = :claveUnidad"),
    @NamedQuery(name = "VwExistenciasSICABIS.findByNombreUnidad", query = "SELECT v FROM VwExistenciasSICABIS v WHERE v.nombreUnidad = :nombreUnidad"),
    @NamedQuery(name = "VwExistenciasSICABIS.findByClaveInsumo", query = "SELECT v FROM VwExistenciasSICABIS v WHERE v.claveInsumo = :claveInsumo"),
    @NamedQuery(name = "VwExistenciasSICABIS.findByInsumoDescripcion", query = "SELECT v FROM VwExistenciasSICABIS v WHERE v.insumoDescripcion = :insumoDescripcion"),
    @NamedQuery(name = "VwExistenciasSICABIS.findByExistenciaCorte", query = "SELECT v FROM VwExistenciasSICABIS v WHERE v.existenciaCorte = :existenciaCorte"),
    @NamedQuery(name = "VwExistenciasSICABIS.findByFechaCorte", query = "SELECT v FROM VwExistenciasSICABIS v WHERE v.fechaCorte = :fechaCorte"),
    @NamedQuery(name = "VwExistenciasSICABIS.findByExistenciaActual", query = "SELECT v FROM VwExistenciasSICABIS v WHERE v.existenciaActual = :existenciaActual")})
public class VwExistenciasSICABIS implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Size(min = 1, max = 10)
    @Column(name = "clave_unidad")
    private String claveUnidad;
    @Size(max = 200)
    @Column(name = "nombre_unidad")
    private String nombreUnidad;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 12)
    @Column(name = "clave_insumo")
    private String claveInsumo;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 255)
    @Column(name = "Insumo_Descripcion")
    private String insumoDescripcion;
    @Basic(optional = false)
    @NotNull
    @Column(name = "Existencia_Corte")
    private long existenciaCorte;
    @Basic(optional = false)
    @NotNull
    @Column(name = "fecha_corte")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaCorte;
    @Basic(optional = false)
    @NotNull
    @Column(name = "Existencia_Actual")
    private long existenciaActual;

    public VwExistenciasSICABIS() {
    }

    public String getClaveUnidad() {
        return claveUnidad;
    }

    public void setClaveUnidad(String claveUnidad) {
        this.claveUnidad = claveUnidad;
    }

    public String getNombreUnidad() {
        return nombreUnidad;
    }

    public void setNombreUnidad(String nombreUnidad) {
        this.nombreUnidad = nombreUnidad;
    }

    public String getClaveInsumo() {
        return claveInsumo;
    }

    public void setClaveInsumo(String claveInsumo) {
        this.claveInsumo = claveInsumo;
    }

    public String getInsumoDescripcion() {
        return insumoDescripcion;
    }

    public void setInsumoDescripcion(String insumoDescripcion) {
        this.insumoDescripcion = insumoDescripcion;
    }

    public long getExistenciaCorte() {
        return existenciaCorte;
    }

    public void setExistenciaCorte(long existenciaCorte) {
        this.existenciaCorte = existenciaCorte;
    }

    public Date getFechaCorte() {
        return fechaCorte;
    }

    public void setFechaCorte(Date fechaCorte) {
        this.fechaCorte = fechaCorte;
    }

    public long getExistenciaActual() {
        return existenciaActual;
    }

    public void setExistenciaActual(long existenciaActual) {
        this.existenciaActual = existenciaActual;
    }
    
}
