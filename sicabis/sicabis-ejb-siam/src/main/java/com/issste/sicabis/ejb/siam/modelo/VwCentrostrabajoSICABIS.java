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
@Table(name = "vwCentros_trabajoSICABIS")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "VwCentrostrabajoSICABIS.findAll", query = "SELECT v FROM VwCentrostrabajoSICABIS v"),
    @NamedQuery(name = "VwCentrostrabajoSICABIS.findByClaveUnidad", query = "SELECT v FROM VwCentrostrabajoSICABIS v WHERE v.claveUnidad = :claveUnidad"),
    @NamedQuery(name = "VwCentrostrabajoSICABIS.findByNombreUnidad", query = "SELECT v FROM VwCentrostrabajoSICABIS v WHERE v.nombreUnidad = :nombreUnidad"),
    @NamedQuery(name = "VwCentrostrabajoSICABIS.findByTipoNivel", query = "SELECT v FROM VwCentrostrabajoSICABIS v WHERE v.tipoNivel = :tipoNivel"),
    @NamedQuery(name = "VwCentrostrabajoSICABIS.findByEstado", query = "SELECT v FROM VwCentrostrabajoSICABIS v WHERE v.estado = :estado"),
    @NamedQuery(name = "VwCentrostrabajoSICABIS.findByTipoUnidad", query = "SELECT v FROM VwCentrostrabajoSICABIS v WHERE v.tipoUnidad = :tipoUnidad")})
public class VwCentrostrabajoSICABIS implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Size(min = 1, max = 10)
    @Column(name = "Clave_Unidad")
    private String claveUnidad;
    @Size(max = 200)
    @Column(name = "Nombre_Unidad")
    private String nombreUnidad;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 50)
    @Column(name = "Tipo_Nivel")
    private String tipoNivel;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 50)
    @Column(name = "Estado")
    private String estado;
    @Size(max = 200)
    @Column(name = "Tipo_Unidad")
    private String tipoUnidad;

    public VwCentrostrabajoSICABIS() {
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

    public String getTipoNivel() {
        return tipoNivel;
    }

    public void setTipoNivel(String tipoNivel) {
        this.tipoNivel = tipoNivel;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public String getTipoUnidad() {
        return tipoUnidad;
    }

    public void setTipoUnidad(String tipoUnidad) {
        this.tipoUnidad = tipoUnidad;
    }
    
}
