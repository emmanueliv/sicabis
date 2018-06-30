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
import javax.persistence.Lob;
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
@Table(name = "vwInsumosSICABIS")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "VwInsumosSICABIS.findAll", query = "SELECT v FROM VwInsumosSICABIS v"),
    @NamedQuery(name = "VwInsumosSICABIS.findByClave", query = "SELECT v FROM VwInsumosSICABIS v WHERE v.clave = :clave"),
    @NamedQuery(name = "VwInsumosSICABIS.findByDescripcion", query = "SELECT v FROM VwInsumosSICABIS v WHERE v.descripcion = :descripcion"),
    @NamedQuery(name = "VwInsumosSICABIS.findByClasificacion", query = "SELECT v FROM VwInsumosSICABIS v WHERE v.clasificacion = :clasificacion"),
    @NamedQuery(name = "VwInsumosSICABIS.findByIndicaciones", query = "SELECT v FROM VwInsumosSICABIS v WHERE v.indicaciones = :indicaciones"),
    @NamedQuery(name = "VwInsumosSICABIS.findByPartida", query = "SELECT v FROM VwInsumosSICABIS v WHERE v.partida = :partida"),
    @NamedQuery(name = "VwInsumosSICABIS.findByTipoInsumo", query = "SELECT v FROM VwInsumosSICABIS v WHERE v.tipoInsumo = :tipoInsumo")})
public class VwInsumosSICABIS implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Size(min = 1, max = 12)
    @Column(name = "clave")
    private String clave;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 255)
    @Column(name = "descripcion")
    private String descripcion;
    @Size(max = 50)
    @Column(name = "clasificacion")
    private String clasificacion;
    @Size(max = 255)
    @Column(name = "indicaciones")
    private String indicaciones;
    @Lob
    @Size(max = 2147483647)
    @Column(name = "viaadmon")
    private String viaadmon;
    @Size(max = 20)
    @Column(name = "partida")
    private String partida;
    @Size(max = 2)
    @Column(name = "tipo_insumo")
    private String tipoInsumo;

    public VwInsumosSICABIS() {
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

    public String getClasificacion() {
        return clasificacion;
    }

    public void setClasificacion(String clasificacion) {
        this.clasificacion = clasificacion;
    }

    public String getIndicaciones() {
        return indicaciones;
    }

    public void setIndicaciones(String indicaciones) {
        this.indicaciones = indicaciones;
    }

    public String getViaadmon() {
        return viaadmon;
    }

    public void setViaadmon(String viaadmon) {
        this.viaadmon = viaadmon;
    }

    public String getPartida() {
        return partida;
    }

    public void setPartida(String partida) {
        this.partida = partida;
    }

    public String getTipoInsumo() {
        return tipoInsumo;
    }

    public void setTipoInsumo(String tipoInsumo) {
        this.tipoInsumo = tipoInsumo;
    }
    
}
