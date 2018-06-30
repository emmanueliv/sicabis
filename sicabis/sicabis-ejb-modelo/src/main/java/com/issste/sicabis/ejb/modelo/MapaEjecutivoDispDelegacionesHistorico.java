/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.issste.sicabis.ejb.modelo;

import java.io.Serializable;
import java.math.BigDecimal;
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
@Table(name = "mapa_ejecutivo_disp_delegaciones_historico")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "MapaEjecutivoDispDelegacionesHistorico.findAll", query = "SELECT m FROM MapaEjecutivoDispDelegacionesHistorico m"),
    @NamedQuery(name = "MapaEjecutivoDispDelegacionesHistorico.findByIdMapaEjecutivoDispDelegacionesHistorico", query = "SELECT m FROM MapaEjecutivoDispDelegacionesHistorico m WHERE m.idMapaEjecutivoDispDelegacionesHistorico = :idMapaEjecutivoDispDelegacionesHistorico"),
    @NamedQuery(name = "MapaEjecutivoDispDelegacionesHistorico.findByEstado", query = "SELECT m FROM MapaEjecutivoDispDelegacionesHistorico m WHERE m.estado = :estado"),
    @NamedQuery(name = "MapaEjecutivoDispDelegacionesHistorico.findByIdDelegacion", query = "SELECT m FROM MapaEjecutivoDispDelegacionesHistorico m WHERE m.idDelegacion = :idDelegacion"),
    @NamedQuery(name = "MapaEjecutivoDispDelegacionesHistorico.findByDelegacion", query = "SELECT m FROM MapaEjecutivoDispDelegacionesHistorico m WHERE m.delegacion = :delegacion"),
    @NamedQuery(name = "MapaEjecutivoDispDelegacionesHistorico.findByClavesAutorizadas", query = "SELECT m FROM MapaEjecutivoDispDelegacionesHistorico m WHERE m.clavesAutorizadas = :clavesAutorizadas"),
    @NamedQuery(name = "MapaEjecutivoDispDelegacionesHistorico.findByClavesDisponibles", query = "SELECT m FROM MapaEjecutivoDispDelegacionesHistorico m WHERE m.clavesDisponibles = :clavesDisponibles"),
    @NamedQuery(name = "MapaEjecutivoDispDelegacionesHistorico.findByDisponibilidad", query = "SELECT m FROM MapaEjecutivoDispDelegacionesHistorico m WHERE m.disponibilidad = :disponibilidad"),
    @NamedQuery(name = "MapaEjecutivoDispDelegacionesHistorico.findByFecha", query = "SELECT m FROM MapaEjecutivoDispDelegacionesHistorico m WHERE m.fecha = :fecha"),
    @NamedQuery(name = "MapaEjecutivoDispDelegacionesHistorico.findByActivo", query = "SELECT m FROM MapaEjecutivoDispDelegacionesHistorico m WHERE m.activo = :activo"),
    @NamedQuery(name = "MapaEjecutivoDispDelegacionesHistorico.findByFechaIngreso", query = "SELECT m FROM MapaEjecutivoDispDelegacionesHistorico m WHERE m.fechaIngreso = :fechaIngreso"),
    @NamedQuery(name = "MapaEjecutivoDispDelegacionesHistorico.findByIdIndicador", query = "SELECT m FROM MapaEjecutivoDispDelegacionesHistorico m WHERE m.idIndicador = :idIndicador")})
public class MapaEjecutivoDispDelegacionesHistorico implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id_mapa_ejecutivo_disp_delegaciones_historico")
    private Integer idMapaEjecutivoDispDelegacionesHistorico;
    @Size(max = 250)
    @Column(name = "estado")
    private String estado;
    @Column(name = "id_delegacion")
    private Integer idDelegacion;
    @Size(max = 250)
    @Column(name = "delegacion")
    private String delegacion;
    @Column(name = "claves_autorizadas")
    private Integer clavesAutorizadas;
    @Column(name = "claves_disponibles")
    private Integer clavesDisponibles;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Column(name = "disponibilidad")
    private BigDecimal disponibilidad;
    @Column(name = "fecha")
    @Temporal(TemporalType.DATE)
    private Date fecha;
    @Basic(optional = false)
    @NotNull
    @Column(name = "activo")
    private int activo;
    @Column(name = "fecha_ingreso")
    @Temporal(TemporalType.DATE)
    private Date fechaIngreso;
    @Column(name = "id_indicador")
    private Integer idIndicador;

    public MapaEjecutivoDispDelegacionesHistorico() {
    }

    public MapaEjecutivoDispDelegacionesHistorico(Integer idMapaEjecutivoDispDelegacionesHistorico) {
        this.idMapaEjecutivoDispDelegacionesHistorico = idMapaEjecutivoDispDelegacionesHistorico;
    }

    public MapaEjecutivoDispDelegacionesHistorico(Integer idMapaEjecutivoDispDelegacionesHistorico, int activo) {
        this.idMapaEjecutivoDispDelegacionesHistorico = idMapaEjecutivoDispDelegacionesHistorico;
        this.activo = activo;
    }

    public Integer getIdMapaEjecutivoDispDelegacionesHistorico() {
        return idMapaEjecutivoDispDelegacionesHistorico;
    }

    public void setIdMapaEjecutivoDispDelegacionesHistorico(Integer idMapaEjecutivoDispDelegacionesHistorico) {
        this.idMapaEjecutivoDispDelegacionesHistorico = idMapaEjecutivoDispDelegacionesHistorico;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public Integer getIdDelegacion() {
        return idDelegacion;
    }

    public void setIdDelegacion(Integer idDelegacion) {
        this.idDelegacion = idDelegacion;
    }

    public String getDelegacion() {
        return delegacion;
    }

    public void setDelegacion(String delegacion) {
        this.delegacion = delegacion;
    }

    public Integer getClavesAutorizadas() {
        return clavesAutorizadas;
    }

    public void setClavesAutorizadas(Integer clavesAutorizadas) {
        this.clavesAutorizadas = clavesAutorizadas;
    }

    public Integer getClavesDisponibles() {
        return clavesDisponibles;
    }

    public void setClavesDisponibles(Integer clavesDisponibles) {
        this.clavesDisponibles = clavesDisponibles;
    }

    public BigDecimal getDisponibilidad() {
        return disponibilidad;
    }

    public void setDisponibilidad(BigDecimal disponibilidad) {
        this.disponibilidad = disponibilidad;
    }

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    public int getActivo() {
        return activo;
    }

    public void setActivo(int activo) {
        this.activo = activo;
    }

    public Date getFechaIngreso() {
        return fechaIngreso;
    }

    public void setFechaIngreso(Date fechaIngreso) {
        this.fechaIngreso = fechaIngreso;
    }

    public Integer getIdIndicador() {
        return idIndicador;
    }

    public void setIdIndicador(Integer idIndicador) {
        this.idIndicador = idIndicador;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idMapaEjecutivoDispDelegacionesHistorico != null ? idMapaEjecutivoDispDelegacionesHistorico.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof MapaEjecutivoDispDelegacionesHistorico)) {
            return false;
        }
        MapaEjecutivoDispDelegacionesHistorico other = (MapaEjecutivoDispDelegacionesHistorico) object;
        if ((this.idMapaEjecutivoDispDelegacionesHistorico == null && other.idMapaEjecutivoDispDelegacionesHistorico != null) || (this.idMapaEjecutivoDispDelegacionesHistorico != null && !this.idMapaEjecutivoDispDelegacionesHistorico.equals(other.idMapaEjecutivoDispDelegacionesHistorico))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.issste.sicabis.ejb.modelo.MapaEjecutivoDispDelegacionesHistorico[ idMapaEjecutivoDispDelegacionesHistorico=" + idMapaEjecutivoDispDelegacionesHistorico + " ]";
    }
    
}
