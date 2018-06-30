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
@Table(name = "existencia_umus_programas_historico")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "ExistenciaUmusProgramasHistorico.findAll", query = "SELECT e FROM ExistenciaUmusProgramasHistorico e"),
    @NamedQuery(name = "ExistenciaUmusProgramasHistorico.findByIdExistenciaUmusProgramasHistorico", query = "SELECT e FROM ExistenciaUmusProgramasHistorico e WHERE e.idExistenciaUmusProgramasHistorico = :idExistenciaUmusProgramasHistorico"),
    @NamedQuery(name = "ExistenciaUmusProgramasHistorico.findByDelegacion", query = "SELECT e FROM ExistenciaUmusProgramasHistorico e WHERE e.delegacion = :delegacion"),
    @NamedQuery(name = "ExistenciaUmusProgramasHistorico.findByClaveUnidad", query = "SELECT e FROM ExistenciaUmusProgramasHistorico e WHERE e.claveUnidad = :claveUnidad"),
    @NamedQuery(name = "ExistenciaUmusProgramasHistorico.findByNumeroUmu", query = "SELECT e FROM ExistenciaUmusProgramasHistorico e WHERE e.numeroUmu = :numeroUmu"),
    @NamedQuery(name = "ExistenciaUmusProgramasHistorico.findByNombreUmu", query = "SELECT e FROM ExistenciaUmusProgramasHistorico e WHERE e.nombreUmu = :nombreUmu"),
    @NamedQuery(name = "ExistenciaUmusProgramasHistorico.findByClave", query = "SELECT e FROM ExistenciaUmusProgramasHistorico e WHERE e.clave = :clave"),
    @NamedQuery(name = "ExistenciaUmusProgramasHistorico.findByDescripcion", query = "SELECT e FROM ExistenciaUmusProgramasHistorico e WHERE e.descripcion = :descripcion"),
    @NamedQuery(name = "ExistenciaUmusProgramasHistorico.findByTipo", query = "SELECT e FROM ExistenciaUmusProgramasHistorico e WHERE e.tipo = :tipo"),
    @NamedQuery(name = "ExistenciaUmusProgramasHistorico.findByNombrePrograma", query = "SELECT e FROM ExistenciaUmusProgramasHistorico e WHERE e.nombrePrograma = :nombrePrograma"),
    @NamedQuery(name = "ExistenciaUmusProgramasHistorico.findByExistencia", query = "SELECT e FROM ExistenciaUmusProgramasHistorico e WHERE e.existencia = :existencia"),
    @NamedQuery(name = "ExistenciaUmusProgramasHistorico.findByFechaInventario", query = "SELECT e FROM ExistenciaUmusProgramasHistorico e WHERE e.fechaInventario = :fechaInventario"),
    @NamedQuery(name = "ExistenciaUmusProgramasHistorico.findByFecha", query = "SELECT e FROM ExistenciaUmusProgramasHistorico e WHERE e.fecha = :fecha"),
    @NamedQuery(name = "ExistenciaUmusProgramasHistorico.findByActivo", query = "SELECT e FROM ExistenciaUmusProgramasHistorico e WHERE e.activo = :activo"),
    @NamedQuery(name = "ExistenciaUmusProgramasHistorico.findByFechaIngresoAux", query = "SELECT e FROM ExistenciaUmusProgramasHistorico e WHERE e.fechaIngresoAux = :fechaIngresoAux")})
public class ExistenciaUmusProgramasHistorico implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id_existencia_umus_programas_historico")
    private Integer idExistenciaUmusProgramasHistorico;
    @Size(max = 250)
    @Column(name = "delegacion")
    private String delegacion;
    @Size(max = 250)
    @Column(name = "clave_unidad")
    private String claveUnidad;
    @Size(max = 250)
    @Column(name = "numero_umu")
    private String numeroUmu;
    @Size(max = 250)
    @Column(name = "nombre_umu")
    private String nombreUmu;
    @Size(max = 250)
    @Column(name = "clave")
    private String clave;
    @Size(max = 5000)
    @Column(name = "descripcion")
    private String descripcion;
    @Size(max = 250)
    @Column(name = "tipo")
    private String tipo;
    @Size(max = 250)
    @Column(name = "nombre_programa")
    private String nombrePrograma;
    @Column(name = "existencia")
    private Integer existencia;
    @Column(name = "fecha_inventario")
    @Temporal(TemporalType.DATE)
    private Date fechaInventario;
    @Column(name = "fecha")
    @Temporal(TemporalType.DATE)
    private Date fecha;
    @Basic(optional = false)
    @NotNull
    @Column(name = "activo")
    private int activo;
    @Column(name = "fecha_ingreso_aux")
    @Temporal(TemporalType.DATE)
    private Date fechaIngresoAux;

    public ExistenciaUmusProgramasHistorico() {
    }

    public ExistenciaUmusProgramasHistorico(Integer idExistenciaUmusProgramasHistorico) {
        this.idExistenciaUmusProgramasHistorico = idExistenciaUmusProgramasHistorico;
    }

    public ExistenciaUmusProgramasHistorico(Integer idExistenciaUmusProgramasHistorico, int activo) {
        this.idExistenciaUmusProgramasHistorico = idExistenciaUmusProgramasHistorico;
        this.activo = activo;
    }

    public Integer getIdExistenciaUmusProgramasHistorico() {
        return idExistenciaUmusProgramasHistorico;
    }

    public void setIdExistenciaUmusProgramasHistorico(Integer idExistenciaUmusProgramasHistorico) {
        this.idExistenciaUmusProgramasHistorico = idExistenciaUmusProgramasHistorico;
    }

    public String getDelegacion() {
        return delegacion;
    }

    public void setDelegacion(String delegacion) {
        this.delegacion = delegacion;
    }

    public String getClaveUnidad() {
        return claveUnidad;
    }

    public void setClaveUnidad(String claveUnidad) {
        this.claveUnidad = claveUnidad;
    }

    public String getNumeroUmu() {
        return numeroUmu;
    }

    public void setNumeroUmu(String numeroUmu) {
        this.numeroUmu = numeroUmu;
    }

    public String getNombreUmu() {
        return nombreUmu;
    }

    public void setNombreUmu(String nombreUmu) {
        this.nombreUmu = nombreUmu;
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

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public String getNombrePrograma() {
        return nombrePrograma;
    }

    public void setNombrePrograma(String nombrePrograma) {
        this.nombrePrograma = nombrePrograma;
    }

    public Integer getExistencia() {
        return existencia;
    }

    public void setExistencia(Integer existencia) {
        this.existencia = existencia;
    }

    public Date getFechaInventario() {
        return fechaInventario;
    }

    public void setFechaInventario(Date fechaInventario) {
        this.fechaInventario = fechaInventario;
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

    public Date getFechaIngresoAux() {
        return fechaIngresoAux;
    }

    public void setFechaIngresoAux(Date fechaIngresoAux) {
        this.fechaIngresoAux = fechaIngresoAux;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idExistenciaUmusProgramasHistorico != null ? idExistenciaUmusProgramasHistorico.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof ExistenciaUmusProgramasHistorico)) {
            return false;
        }
        ExistenciaUmusProgramasHistorico other = (ExistenciaUmusProgramasHistorico) object;
        if ((this.idExistenciaUmusProgramasHistorico == null && other.idExistenciaUmusProgramasHistorico != null) || (this.idExistenciaUmusProgramasHistorico != null && !this.idExistenciaUmusProgramasHistorico.equals(other.idExistenciaUmusProgramasHistorico))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.issste.sicabis.ejb.modelo.ExistenciaUmusProgramasHistorico[ idExistenciaUmusProgramasHistorico=" + idExistenciaUmusProgramasHistorico + " ]";
    }
    
}
