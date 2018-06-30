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
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author 9RZCBG2
 */
@Entity
@Table(name = "seguimiento_salidas_umu_interno")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "SeguimientoSalidasUmuInterno.findAll", query = "SELECT s FROM SeguimientoSalidasUmuInterno s"),
    @NamedQuery(name = "SeguimientoSalidasUmuInterno.findByIdSeguimientoSalidasUmuInterno", query = "SELECT s FROM SeguimientoSalidasUmuInterno s WHERE s.idSeguimientoSalidasUmuInterno = :idSeguimientoSalidasUmuInterno"),
    @NamedQuery(name = "SeguimientoSalidasUmuInterno.findByFecha", query = "SELECT s FROM SeguimientoSalidasUmuInterno s WHERE s.fecha = :fecha"),
    @NamedQuery(name = "SeguimientoSalidasUmuInterno.findByFolioissste", query = "SELECT s FROM SeguimientoSalidasUmuInterno s WHERE s.folioissste = :folioissste"),
    @NamedQuery(name = "SeguimientoSalidasUmuInterno.findByPedido", query = "SELECT s FROM SeguimientoSalidasUmuInterno s WHERE s.pedido = :pedido"),
    @NamedQuery(name = "SeguimientoSalidasUmuInterno.findByUmu", query = "SELECT s FROM SeguimientoSalidasUmuInterno s WHERE s.umu = :umu"),
    @NamedQuery(name = "SeguimientoSalidasUmuInterno.findByClave", query = "SELECT s FROM SeguimientoSalidasUmuInterno s WHERE s.clave = :clave"),
    @NamedQuery(name = "SeguimientoSalidasUmuInterno.findByFechaValidacion", query = "SELECT s FROM SeguimientoSalidasUmuInterno s WHERE s.fechaValidacion = :fechaValidacion"),
    @NamedQuery(name = "SeguimientoSalidasUmuInterno.findByFechaPlaneacion", query = "SELECT s FROM SeguimientoSalidasUmuInterno s WHERE s.fechaPlaneacion = :fechaPlaneacion"),
    @NamedQuery(name = "SeguimientoSalidasUmuInterno.findByFechaCargaUnidad", query = "SELECT s FROM SeguimientoSalidasUmuInterno s WHERE s.fechaCargaUnidad = :fechaCargaUnidad"),
    @NamedQuery(name = "SeguimientoSalidasUmuInterno.findByFechaAduana", query = "SELECT s FROM SeguimientoSalidasUmuInterno s WHERE s.fechaAduana = :fechaAduana"),
    @NamedQuery(name = "SeguimientoSalidasUmuInterno.findByFechaIngreso", query = "SELECT s FROM SeguimientoSalidasUmuInterno s WHERE s.fechaIngreso = :fechaIngreso")})
public class SeguimientoSalidasUmuInterno implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id_seguimiento_salidas_umu_interno")
    private Integer idSeguimientoSalidasUmuInterno;
    @Column(name = "fecha")
    @Temporal(TemporalType.DATE)
    private Date fecha;
    @Column(name = "folioissste")
    private Integer folioissste;
    @Size(max = 250)
    @Column(name = "pedido")
    private String pedido;
    @Size(max = 250)
    @Column(name = "umu")
    private String umu;
    @Size(max = 250)
    @Column(name = "clave")
    private String clave;
    @Column(name = "fecha_validacion")
    @Temporal(TemporalType.DATE)
    private Date fechaValidacion;
    @Column(name = "fecha_planeacion")
    @Temporal(TemporalType.DATE)
    private Date fechaPlaneacion;
    @Column(name = "fecha_carga_unidad")
    @Temporal(TemporalType.DATE)
    private Date fechaCargaUnidad;
    @Column(name = "fecha_aduana")
    @Temporal(TemporalType.DATE)
    private Date fechaAduana;
    @Column(name = "fecha_ingreso")
    @Temporal(TemporalType.DATE)
    private Date fechaIngreso;

    public SeguimientoSalidasUmuInterno() {
    }

    public SeguimientoSalidasUmuInterno(Integer idSeguimientoSalidasUmuInterno) {
        this.idSeguimientoSalidasUmuInterno = idSeguimientoSalidasUmuInterno;
    }

    public Integer getIdSeguimientoSalidasUmuInterno() {
        return idSeguimientoSalidasUmuInterno;
    }

    public void setIdSeguimientoSalidasUmuInterno(Integer idSeguimientoSalidasUmuInterno) {
        this.idSeguimientoSalidasUmuInterno = idSeguimientoSalidasUmuInterno;
    }

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    public Integer getFolioissste() {
        return folioissste;
    }

    public void setFolioissste(Integer folioissste) {
        this.folioissste = folioissste;
    }

    public String getPedido() {
        return pedido;
    }

    public void setPedido(String pedido) {
        this.pedido = pedido;
    }

    public String getUmu() {
        return umu;
    }

    public void setUmu(String umu) {
        this.umu = umu;
    }

    public String getClave() {
        return clave;
    }

    public void setClave(String clave) {
        this.clave = clave;
    }

    public Date getFechaValidacion() {
        return fechaValidacion;
    }

    public void setFechaValidacion(Date fechaValidacion) {
        this.fechaValidacion = fechaValidacion;
    }

    public Date getFechaPlaneacion() {
        return fechaPlaneacion;
    }

    public void setFechaPlaneacion(Date fechaPlaneacion) {
        this.fechaPlaneacion = fechaPlaneacion;
    }

    public Date getFechaCargaUnidad() {
        return fechaCargaUnidad;
    }

    public void setFechaCargaUnidad(Date fechaCargaUnidad) {
        this.fechaCargaUnidad = fechaCargaUnidad;
    }

    public Date getFechaAduana() {
        return fechaAduana;
    }

    public void setFechaAduana(Date fechaAduana) {
        this.fechaAduana = fechaAduana;
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
        hash += (idSeguimientoSalidasUmuInterno != null ? idSeguimientoSalidasUmuInterno.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof SeguimientoSalidasUmuInterno)) {
            return false;
        }
        SeguimientoSalidasUmuInterno other = (SeguimientoSalidasUmuInterno) object;
        if ((this.idSeguimientoSalidasUmuInterno == null && other.idSeguimientoSalidasUmuInterno != null) || (this.idSeguimientoSalidasUmuInterno != null && !this.idSeguimientoSalidasUmuInterno.equals(other.idSeguimientoSalidasUmuInterno))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.issste.sicabis.ejb.service.silodisa.modelo.SeguimientoSalidasUmuInterno[ idSeguimientoSalidasUmuInterno=" + idSeguimientoSalidasUmuInterno + " ]";
    }
    
}
