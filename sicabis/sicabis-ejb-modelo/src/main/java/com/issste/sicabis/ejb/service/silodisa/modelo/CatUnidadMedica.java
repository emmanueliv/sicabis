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
@Table(name = "cat_unidad_medica")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "CatUnidadMedica.findAll", query = "SELECT c FROM CatUnidadMedica c"),
    @NamedQuery(name = "CatUnidadMedica.findByIdCatUnidadMedica", query = "SELECT c FROM CatUnidadMedica c WHERE c.idCatUnidadMedica = :idCatUnidadMedica"),
    @NamedQuery(name = "CatUnidadMedica.findByUmu", query = "SELECT c FROM CatUnidadMedica c WHERE c.umu = :umu"),
    @NamedQuery(name = "CatUnidadMedica.findByClavePresupuestal", query = "SELECT c FROM CatUnidadMedica c WHERE c.clavePresupuestal = :clavePresupuestal"),
    @NamedQuery(name = "CatUnidadMedica.findByNombre", query = "SELECT c FROM CatUnidadMedica c WHERE c.nombre = :nombre"),
    @NamedQuery(name = "CatUnidadMedica.findByDireccion", query = "SELECT c FROM CatUnidadMedica c WHERE c.direccion = :direccion"),
    @NamedQuery(name = "CatUnidadMedica.findByCiudad", query = "SELECT c FROM CatUnidadMedica c WHERE c.ciudad = :ciudad"),
    @NamedQuery(name = "CatUnidadMedica.findByEstado", query = "SELECT c FROM CatUnidadMedica c WHERE c.estado = :estado"),
    @NamedQuery(name = "CatUnidadMedica.findByDelegacion", query = "SELECT c FROM CatUnidadMedica c WHERE c.delegacion = :delegacion"),
    @NamedQuery(name = "CatUnidadMedica.findByMunicipio", query = "SELECT c FROM CatUnidadMedica c WHERE c.municipio = :municipio"),
    @NamedQuery(name = "CatUnidadMedica.findByLatitud", query = "SELECT c FROM CatUnidadMedica c WHERE c.latitud = :latitud"),
    @NamedQuery(name = "CatUnidadMedica.findByLongitud", query = "SELECT c FROM CatUnidadMedica c WHERE c.longitud = :longitud"),
    @NamedQuery(name = "CatUnidadMedica.findByEstatus", query = "SELECT c FROM CatUnidadMedica c WHERE c.estatus = :estatus"),
    @NamedQuery(name = "CatUnidadMedica.findByFechaIngreso", query = "SELECT c FROM CatUnidadMedica c WHERE c.fechaIngreso = :fechaIngreso")})
public class CatUnidadMedica implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id_cat_unidad_medica")
    private Integer idCatUnidadMedica;
    @Size(max = 250)
    @Column(name = "umu")
    private String umu;
    @Size(max = 250)
    @Column(name = "clave_presupuestal")
    private String clavePresupuestal;
    @Size(max = 250)
    @Column(name = "nombre")
    private String nombre;
    @Size(max = 250)
    @Column(name = "direccion")
    private String direccion;
    @Size(max = 250)
    @Column(name = "ciudad")
    private String ciudad;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 250)
    @Column(name = "estado")
    private String estado;
    @Size(max = 250)
    @Column(name = "delegacion")
    private String delegacion;
    @Size(max = 250)
    @Column(name = "municipio")
    private String municipio;
    @Size(max = 250)
    @Column(name = "latitud")
    private String latitud;
    @Size(max = 250)
    @Column(name = "longitud")
    private String longitud;
    @Size(max = 250)
    @Column(name = "estatus")
    private String estatus;
    @Column(name = "fecha_ingreso")
    @Temporal(TemporalType.DATE)
    private Date fechaIngreso;

    public CatUnidadMedica() {
    }

    public CatUnidadMedica(Integer idCatUnidadMedica) {
        this.idCatUnidadMedica = idCatUnidadMedica;
    }

    public CatUnidadMedica(Integer idCatUnidadMedica, String estado) {
        this.idCatUnidadMedica = idCatUnidadMedica;
        this.estado = estado;
    }

    public Integer getIdCatUnidadMedica() {
        return idCatUnidadMedica;
    }

    public void setIdCatUnidadMedica(Integer idCatUnidadMedica) {
        this.idCatUnidadMedica = idCatUnidadMedica;
    }

    public String getUmu() {
        return umu;
    }

    public void setUmu(String umu) {
        this.umu = umu;
    }

    public String getClavePresupuestal() {
        return clavePresupuestal;
    }

    public void setClavePresupuestal(String clavePresupuestal) {
        this.clavePresupuestal = clavePresupuestal;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public String getCiudad() {
        return ciudad;
    }

    public void setCiudad(String ciudad) {
        this.ciudad = ciudad;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public String getDelegacion() {
        return delegacion;
    }

    public void setDelegacion(String delegacion) {
        this.delegacion = delegacion;
    }

    public String getMunicipio() {
        return municipio;
    }

    public void setMunicipio(String municipio) {
        this.municipio = municipio;
    }

    public String getLatitud() {
        return latitud;
    }

    public void setLatitud(String latitud) {
        this.latitud = latitud;
    }

    public String getLongitud() {
        return longitud;
    }

    public void setLongitud(String longitud) {
        this.longitud = longitud;
    }

    public String getEstatus() {
        return estatus;
    }

    public void setEstatus(String estatus) {
        this.estatus = estatus;
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
        hash += (idCatUnidadMedica != null ? idCatUnidadMedica.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof CatUnidadMedica)) {
            return false;
        }
        CatUnidadMedica other = (CatUnidadMedica) object;
        if ((this.idCatUnidadMedica == null && other.idCatUnidadMedica != null) || (this.idCatUnidadMedica != null && !this.idCatUnidadMedica.equals(other.idCatUnidadMedica))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.issste.sicabis.ejb.service.silodisa.modelo.CatUnidadMedica[ idCatUnidadMedica=" + idCatUnidadMedica + " ]";
    }
    
}
