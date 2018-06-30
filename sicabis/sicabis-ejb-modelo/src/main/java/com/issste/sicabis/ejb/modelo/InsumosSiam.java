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
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author kriosoft
 */
@Entity
@Table(name = "insumos_siam")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "InsumosSiam.findAll", query = "SELECT i FROM InsumosSiam i"),
    @NamedQuery(name = "InsumosSiam.findByClave", query = "SELECT i FROM InsumosSiam i WHERE i.clave = :clave"),
    @NamedQuery(name = "InsumosSiam.findByDescripcion", query = "SELECT i FROM InsumosSiam i WHERE i.descripcion = :descripcion"),
    @NamedQuery(name = "InsumosSiam.findByIndicaciones", query = "SELECT i FROM InsumosSiam i WHERE i.indicaciones = :indicaciones"),
    @NamedQuery(name = "InsumosSiam.findByPartidaPresupuestal", query = "SELECT i FROM InsumosSiam i WHERE i.partidaPresupuestal = :partidaPresupuestal"),
    @NamedQuery(name = "InsumosSiam.findByViaAdministracionDosis", query = "SELECT i FROM InsumosSiam i WHERE i.viaAdministracionDosis = :viaAdministracionDosis"),
    @NamedQuery(name = "InsumosSiam.findByFechaAlta", query = "SELECT i FROM InsumosSiam i WHERE i.fechaAlta = :fechaAlta"),
    @NamedQuery(name = "InsumosSiam.findByFechaBaja", query = "SELECT i FROM InsumosSiam i WHERE i.fechaBaja = :fechaBaja"),
    @NamedQuery(name = "InsumosSiam.findByFechaActualizacion", query = "SELECT i FROM InsumosSiam i WHERE i.fechaActualizacion = :fechaActualizacion")})
public class InsumosSiam implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "clave")
    private String clave;
    @Basic(optional = false)
    @NotNull
    @Column(name = "descripcion")
    private String descripcion;
    @Basic(optional = false)
    @Column(name = "clasificacion")
    private String clasificacion;
    @Basic(optional = false)
    @Column(name = "indicaciones")
    private String indicaciones;
    @Basic(optional = false)
    @Column(name = "partida_presupuestal")
    private String partidaPresupuestal;
    @Basic(optional = false)
    @Column(name = "via_administracion_dosis")
    private String viaAdministracionDosis;
    @Column(name = "id_nivel")
    private Integer idNivel;
    @Column(name = "id_grupo")
    private Integer idGrupo;
    @Basic(optional = false)
    @Column(name = "tipo")
    private String tipo;
    @Column(name = "fecha_alta")
    @Temporal(TemporalType.DATE)
    private Date fechaAlta;
    @Column(name = "fecha_baja")
    @Temporal(TemporalType.DATE)
    private Date fechaBaja;
    @Column(name = "fecha_actualizacion")
    @Temporal(TemporalType.DATE)
    private Date fechaActualizacion;

    public InsumosSiam() {
    }

    public InsumosSiam(String clave) {
        this.clave = clave;
    }

    public InsumosSiam(String clave, String descripcion, String indicaciones, String grupo, String partidaPresupuestal, String viaAdministracionDosis) {
        this.clave = clave;
        this.descripcion = descripcion;
        this.indicaciones = indicaciones;
        this.partidaPresupuestal = partidaPresupuestal;
        this.viaAdministracionDosis = viaAdministracionDosis;
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

    public String getPartidaPresupuestal() {
        return partidaPresupuestal;
    }

    public void setPartidaPresupuestal(String partidaPresupuestal) {
        this.partidaPresupuestal = partidaPresupuestal;
    }

    public String getViaAdministracionDosis() {
        return viaAdministracionDosis;
    }

    public void setViaAdministracionDosis(String viaAdministracionDosis) {
        this.viaAdministracionDosis = viaAdministracionDosis;
    }

    public Integer getIdNivel() {
        return idNivel;
    }

    public void setIdNivel(Integer idNivel) {
        this.idNivel = idNivel;
    }

    public Integer getIdGrupo() {
        return idGrupo;
    }

    public void setIdGrupo(Integer idGrupo) {
        this.idGrupo = idGrupo;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public Date getFechaAlta() {
        return fechaAlta;
    }

    public void setFechaAlta(Date fechaAlta) {
        this.fechaAlta = fechaAlta;
    }

    public Date getFechaBaja() {
        return fechaBaja;
    }

    public void setFechaBaja(Date fechaBaja) {
        this.fechaBaja = fechaBaja;
    }

    public Date getFechaActualizacion() {
        return fechaActualizacion;
    }

    public void setFechaActualizacion(Date fechaActualizacion) {
        this.fechaActualizacion = fechaActualizacion;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (clave != null ? clave.hashCode() : 0);
        return hash;
    }

    @Override
    public String toString() {
        return "com.issste.sicabis.ejb.modelo.InsumosSiam[ clave=" + clave + " ]";
    }

}
