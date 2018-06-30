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
@Table(name = "mapa_ejecutivo_disp_g40_historico")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "MapaEjecutivoDispG40Historico.findAll", query = "SELECT m FROM MapaEjecutivoDispG40Historico m"),
    @NamedQuery(name = "MapaEjecutivoDispG40Historico.findByIdMapaEjecutivoDispG40Historico", query = "SELECT m FROM MapaEjecutivoDispG40Historico m WHERE m.idMapaEjecutivoDispG40Historico = :idMapaEjecutivoDispG40Historico"),
    @NamedQuery(name = "MapaEjecutivoDispG40Historico.findByEstado", query = "SELECT m FROM MapaEjecutivoDispG40Historico m WHERE m.estado = :estado"),
    @NamedQuery(name = "MapaEjecutivoDispG40Historico.findByUmu", query = "SELECT m FROM MapaEjecutivoDispG40Historico m WHERE m.umu = :umu"),
    @NamedQuery(name = "MapaEjecutivoDispG40Historico.findByNombre", query = "SELECT m FROM MapaEjecutivoDispG40Historico m WHERE m.nombre = :nombre"),
    @NamedQuery(name = "MapaEjecutivoDispG40Historico.findByClavesAutorizadas", query = "SELECT m FROM MapaEjecutivoDispG40Historico m WHERE m.clavesAutorizadas = :clavesAutorizadas"),
    @NamedQuery(name = "MapaEjecutivoDispG40Historico.findByClavesDisponibles", query = "SELECT m FROM MapaEjecutivoDispG40Historico m WHERE m.clavesDisponibles = :clavesDisponibles"),
    @NamedQuery(name = "MapaEjecutivoDispG40Historico.findByDisponibilidad", query = "SELECT m FROM MapaEjecutivoDispG40Historico m WHERE m.disponibilidad = :disponibilidad"),
    @NamedQuery(name = "MapaEjecutivoDispG40Historico.findByFecha", query = "SELECT m FROM MapaEjecutivoDispG40Historico m WHERE m.fecha = :fecha"),
    @NamedQuery(name = "MapaEjecutivoDispG40Historico.findByActivo", query = "SELECT m FROM MapaEjecutivoDispG40Historico m WHERE m.activo = :activo"),
    @NamedQuery(name = "MapaEjecutivoDispG40Historico.findByFechaIngreso", query = "SELECT m FROM MapaEjecutivoDispG40Historico m WHERE m.fechaIngreso = :fechaIngreso"),
    @NamedQuery(name = "MapaEjecutivoDispG40Historico.findByIdDelegacion", query = "SELECT m FROM MapaEjecutivoDispG40Historico m WHERE m.idDelegacion = :idDelegacion"),
    @NamedQuery(name = "MapaEjecutivoDispG40Historico.findByIdIndicador", query = "SELECT m FROM MapaEjecutivoDispG40Historico m WHERE m.idIndicador = :idIndicador")})
public class MapaEjecutivoDispG40Historico implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id_mapa_ejecutivo_disp_g40_historico")
    private Integer idMapaEjecutivoDispG40Historico;
    @Size(max = 250)
    @Column(name = "estado")
    private String estado;
    @Size(max = 250)
    @Column(name = "umu")
    private String umu;
    @Size(max = 250)
    @Column(name = "nombre")
    private String nombre;
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
    @Column(name = "id_delegacion")
    private Integer idDelegacion;
    @Column(name = "id_indicador")
    private Integer idIndicador;

    public MapaEjecutivoDispG40Historico() {
    }

    public MapaEjecutivoDispG40Historico(Integer idMapaEjecutivoDispG40Historico) {
        this.idMapaEjecutivoDispG40Historico = idMapaEjecutivoDispG40Historico;
    }

    public MapaEjecutivoDispG40Historico(Integer idMapaEjecutivoDispG40Historico, int activo) {
        this.idMapaEjecutivoDispG40Historico = idMapaEjecutivoDispG40Historico;
        this.activo = activo;
    }

    public Integer getIdMapaEjecutivoDispG40Historico() {
        return idMapaEjecutivoDispG40Historico;
    }

    public void setIdMapaEjecutivoDispG40Historico(Integer idMapaEjecutivoDispG40Historico) {
        this.idMapaEjecutivoDispG40Historico = idMapaEjecutivoDispG40Historico;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public String getUmu() {
        return umu;
    }

    public void setUmu(String umu) {
        this.umu = umu;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
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

    public Integer getIdDelegacion() {
        return idDelegacion;
    }

    public void setIdDelegacion(Integer idDelegacion) {
        this.idDelegacion = idDelegacion;
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
        hash += (idMapaEjecutivoDispG40Historico != null ? idMapaEjecutivoDispG40Historico.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof MapaEjecutivoDispG40Historico)) {
            return false;
        }
        MapaEjecutivoDispG40Historico other = (MapaEjecutivoDispG40Historico) object;
        if ((this.idMapaEjecutivoDispG40Historico == null && other.idMapaEjecutivoDispG40Historico != null) || (this.idMapaEjecutivoDispG40Historico != null && !this.idMapaEjecutivoDispG40Historico.equals(other.idMapaEjecutivoDispG40Historico))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.issste.sicabis.ejb.modelo.MapaEjecutivoDispG40Historico[ idMapaEjecutivoDispG40Historico=" + idMapaEjecutivoDispG40Historico + " ]";
    }
    
}
