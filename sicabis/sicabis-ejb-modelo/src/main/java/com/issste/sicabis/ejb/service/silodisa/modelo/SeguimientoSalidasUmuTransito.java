/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.issste.sicabis.ejb.service.silodisa.modelo;

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
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author 9RZCBG2
 */
@Entity
@Table(name = "seguimiento_salidas_umu_transito")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "SeguimientoSalidasUmuTransito.findAll", query = "SELECT s FROM SeguimientoSalidasUmuTransito s"),
    @NamedQuery(name = "SeguimientoSalidasUmuTransito.findByIdSeguimientoSalidasUmuTransito", query = "SELECT s FROM SeguimientoSalidasUmuTransito s WHERE s.idSeguimientoSalidasUmuTransito = :idSeguimientoSalidasUmuTransito"),
    @NamedQuery(name = "SeguimientoSalidasUmuTransito.findByFecha", query = "SELECT s FROM SeguimientoSalidasUmuTransito s WHERE s.fecha = :fecha"),
    @NamedQuery(name = "SeguimientoSalidasUmuTransito.findByDelegacion", query = "SELECT s FROM SeguimientoSalidasUmuTransito s WHERE s.delegacion = :delegacion"),
    @NamedQuery(name = "SeguimientoSalidasUmuTransito.findByCodigoumu", query = "SELECT s FROM SeguimientoSalidasUmuTransito s WHERE s.codigoumu = :codigoumu"),
    @NamedQuery(name = "SeguimientoSalidasUmuTransito.findByPresupuestal", query = "SELECT s FROM SeguimientoSalidasUmuTransito s WHERE s.presupuestal = :presupuestal"),
    @NamedQuery(name = "SeguimientoSalidasUmuTransito.findByUmu", query = "SELECT s FROM SeguimientoSalidasUmuTransito s WHERE s.umu = :umu"),
    @NamedQuery(name = "SeguimientoSalidasUmuTransito.findByFolioissste", query = "SELECT s FROM SeguimientoSalidasUmuTransito s WHERE s.folioissste = :folioissste"),
    @NamedQuery(name = "SeguimientoSalidasUmuTransito.findByClave", query = "SELECT s FROM SeguimientoSalidasUmuTransito s WHERE s.clave = :clave"),
    @NamedQuery(name = "SeguimientoSalidasUmuTransito.findByRemision", query = "SELECT s FROM SeguimientoSalidasUmuTransito s WHERE s.remision = :remision"),
    @NamedQuery(name = "SeguimientoSalidasUmuTransito.findByFechaComprometida", query = "SELECT s FROM SeguimientoSalidasUmuTransito s WHERE s.fechaComprometida = :fechaComprometida"),
    @NamedQuery(name = "SeguimientoSalidasUmuTransito.findByFechaRemision", query = "SELECT s FROM SeguimientoSalidasUmuTransito s WHERE s.fechaRemision = :fechaRemision"),
    @NamedQuery(name = "SeguimientoSalidasUmuTransito.findByNombre", query = "SELECT s FROM SeguimientoSalidasUmuTransito s WHERE s.nombre = :nombre"),
    @NamedQuery(name = "SeguimientoSalidasUmuTransito.findByTipo", query = "SELECT s FROM SeguimientoSalidasUmuTransito s WHERE s.tipo = :tipo"),
    @NamedQuery(name = "SeguimientoSalidasUmuTransito.findBySolicitada", query = "SELECT s FROM SeguimientoSalidasUmuTransito s WHERE s.solicitada = :solicitada"),
    @NamedQuery(name = "SeguimientoSalidasUmuTransito.findByLote", query = "SELECT s FROM SeguimientoSalidasUmuTransito s WHERE s.lote = :lote"),
    @NamedQuery(name = "SeguimientoSalidasUmuTransito.findByPrecio", query = "SELECT s FROM SeguimientoSalidasUmuTransito s WHERE s.precio = :precio"),
    @NamedQuery(name = "SeguimientoSalidasUmuTransito.findByImporte", query = "SELECT s FROM SeguimientoSalidasUmuTransito s WHERE s.importe = :importe"),
    @NamedQuery(name = "SeguimientoSalidasUmuTransito.findByFechaCamion", query = "SELECT s FROM SeguimientoSalidasUmuTransito s WHERE s.fechaCamion = :fechaCamion"),
    @NamedQuery(name = "SeguimientoSalidasUmuTransito.findByFechaIngreso", query = "SELECT s FROM SeguimientoSalidasUmuTransito s WHERE s.fechaIngreso = :fechaIngreso")})
public class SeguimientoSalidasUmuTransito implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id_seguimiento_salidas_umu_transito")
    private Integer idSeguimientoSalidasUmuTransito;
    @Column(name = "fecha")
    @Temporal(TemporalType.DATE)
    private Date fecha;
    @Size(max = 250)
    @Column(name = "delegacion")
    private String delegacion;
    @Size(max = 250)
    @Column(name = "codigoumu")
    private String codigoumu;
    @Size(max = 250)
    @Column(name = "presupuestal")
    private String presupuestal;
    @Size(max = 250)
    @Column(name = "umu")
    private String umu;
    @Column(name = "folioissste")
    private Integer folioissste;
    @Size(max = 250)
    @Column(name = "clave")
    private String clave;
    @Column(name = "remision")
    private Integer remision;
    @Temporal(TemporalType.DATE)
    @Column(name = "fecha_comprometida")
    private Date fechaComprometida;
    @Temporal(TemporalType.DATE)
    @Column(name = "fecha_remision")
    private Date fechaRemision;
    @Size(max = 250)
    @Column(name = "nombre")
    private String nombre;
    @Size(max = 250)
    @Column(name = "tipo")
    private String tipo;
    @Column(name = "solicitada")
    private Integer solicitada;
    @Size(max = 250)
    @Column(name = "lote")
    private String lote;
    @Size(max = 250)
    @Column(name = "precio")
    private String precio;
    @Column(name = "importe")
    private BigDecimal importe;
    @Column(name = "fecha_camion")
    @Temporal(TemporalType.DATE)
    private Date fechaCamion;
    @Column(name = "fecha_ingreso")
    @Temporal(TemporalType.DATE)
    private Date fechaIngreso;

    public SeguimientoSalidasUmuTransito() {
    }

    public SeguimientoSalidasUmuTransito(Integer idSeguimientoSalidasUmuTransito) {
        this.idSeguimientoSalidasUmuTransito = idSeguimientoSalidasUmuTransito;
    }

    public Integer getIdSeguimientoSalidasUmuTransito() {
        return idSeguimientoSalidasUmuTransito;
    }

    public void setIdSeguimientoSalidasUmuTransito(Integer idSeguimientoSalidasUmuTransito) {
        this.idSeguimientoSalidasUmuTransito = idSeguimientoSalidasUmuTransito;
    }

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    public String getDelegacion() {
        return delegacion;
    }

    public void setDelegacion(String delegacion) {
        this.delegacion = delegacion;
    }

    public String getCodigoumu() {
        return codigoumu;
    }

    public void setCodigoumu(String codigoumu) {
        this.codigoumu = codigoumu;
    }

    public String getPresupuestal() {
        return presupuestal;
    }

    public void setPresupuestal(String presupuestal) {
        this.presupuestal = presupuestal;
    }

    public String getUmu() {
        return umu;
    }

    public void setUmu(String umu) {
        this.umu = umu;
    }

    public Integer getFolioissste() {
        return folioissste;
    }

    public void setFolioissste(Integer folioissste) {
        this.folioissste = folioissste;
    }

    public String getClave() {
        return clave;
    }

    public void setClave(String clave) {
        this.clave = clave;
    }

    public Integer getRemision() {
        return remision;
    }

    public void setRemision(Integer remision) {
        this.remision = remision;
    }

    public Date getFechaComprometida() {
        return fechaComprometida;
    }

    public void setFechaComprometida(Date fechaComprometida) {
        this.fechaComprometida = fechaComprometida;
    }

    public Date getFechaRemision() {
        return fechaRemision;
    }

    public void setFechaRemision(Date fechaRemision) {
        this.fechaRemision = fechaRemision;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public Integer getSolicitada() {
        return solicitada;
    }

    public void setSolicitada(Integer solicitada) {
        this.solicitada = solicitada;
    }

    public String getLote() {
        return lote;
    }

    public void setLote(String lote) {
        this.lote = lote;
    }

    public String getPrecio() {
        return precio;
    }

    public void setPrecio(String precio) {
        this.precio = precio;
    }

    public BigDecimal getImporte() {
        return importe;
    }

    public void setImporte(BigDecimal importe) {
        this.importe = importe;
    }

    public Date getFechaCamion() {
        return fechaCamion;
    }

    public void setFechaCamion(Date fechaCamion) {
        this.fechaCamion = fechaCamion;
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
        hash += (idSeguimientoSalidasUmuTransito != null ? idSeguimientoSalidasUmuTransito.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof SeguimientoSalidasUmuTransito)) {
            return false;
        }
        SeguimientoSalidasUmuTransito other = (SeguimientoSalidasUmuTransito) object;
        if ((this.idSeguimientoSalidasUmuTransito == null && other.idSeguimientoSalidasUmuTransito != null) || (this.idSeguimientoSalidasUmuTransito != null && !this.idSeguimientoSalidasUmuTransito.equals(other.idSeguimientoSalidasUmuTransito))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.issste.sicabis.ejb.service.silodisa.modelo.SeguimientoSalidasUmuTransito[ idSeguimientoSalidasUmuTransito=" + idSeguimientoSalidasUmuTransito + " ]";
    }

}
