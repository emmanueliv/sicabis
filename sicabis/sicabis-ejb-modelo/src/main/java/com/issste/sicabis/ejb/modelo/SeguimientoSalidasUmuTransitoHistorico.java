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
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author 9RZCBG2
 */
@Entity
@Table(name = "seguimiento_salidas_umu_transito_historico")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "SeguimientoSalidasUmuTransitoHistorico.findAll", query = "SELECT s FROM SeguimientoSalidasUmuTransitoHistorico s"),
    @NamedQuery(name = "SeguimientoSalidasUmuTransitoHistorico.findByIdSeguimientoSalidasUmuTransitoHistorico", query = "SELECT s FROM SeguimientoSalidasUmuTransitoHistorico s WHERE s.idSeguimientoSalidasUmuTransitoHistorico = :idSeguimientoSalidasUmuTransitoHistorico"),
    @NamedQuery(name = "SeguimientoSalidasUmuTransitoHistorico.findByFecha", query = "SELECT s FROM SeguimientoSalidasUmuTransitoHistorico s WHERE s.fecha = :fecha"),
    @NamedQuery(name = "SeguimientoSalidasUmuTransitoHistorico.findByDelegacion", query = "SELECT s FROM SeguimientoSalidasUmuTransitoHistorico s WHERE s.delegacion = :delegacion"),
    @NamedQuery(name = "SeguimientoSalidasUmuTransitoHistorico.findByCodigoumu", query = "SELECT s FROM SeguimientoSalidasUmuTransitoHistorico s WHERE s.codigoumu = :codigoumu"),
    @NamedQuery(name = "SeguimientoSalidasUmuTransitoHistorico.findByPresupuestal", query = "SELECT s FROM SeguimientoSalidasUmuTransitoHistorico s WHERE s.presupuestal = :presupuestal"),
    @NamedQuery(name = "SeguimientoSalidasUmuTransitoHistorico.findByUmu", query = "SELECT s FROM SeguimientoSalidasUmuTransitoHistorico s WHERE s.umu = :umu"),
    @NamedQuery(name = "SeguimientoSalidasUmuTransitoHistorico.findByFolioissste", query = "SELECT s FROM SeguimientoSalidasUmuTransitoHistorico s WHERE s.folioissste = :folioissste"),
    @NamedQuery(name = "SeguimientoSalidasUmuTransitoHistorico.findByClave", query = "SELECT s FROM SeguimientoSalidasUmuTransitoHistorico s WHERE s.clave = :clave"),
    @NamedQuery(name = "SeguimientoSalidasUmuTransitoHistorico.findByRemision", query = "SELECT s FROM SeguimientoSalidasUmuTransitoHistorico s WHERE s.remision = :remision"),
    @NamedQuery(name = "SeguimientoSalidasUmuTransitoHistorico.findByFechaComprometida", query = "SELECT s FROM SeguimientoSalidasUmuTransitoHistorico s WHERE s.fechaComprometida = :fechaComprometida"),
    @NamedQuery(name = "SeguimientoSalidasUmuTransitoHistorico.findByFechaRemision", query = "SELECT s FROM SeguimientoSalidasUmuTransitoHistorico s WHERE s.fechaRemision = :fechaRemision"),
    @NamedQuery(name = "SeguimientoSalidasUmuTransitoHistorico.findByNombre", query = "SELECT s FROM SeguimientoSalidasUmuTransitoHistorico s WHERE s.nombre = :nombre"),
    @NamedQuery(name = "SeguimientoSalidasUmuTransitoHistorico.findByTipo", query = "SELECT s FROM SeguimientoSalidasUmuTransitoHistorico s WHERE s.tipo = :tipo"),
    @NamedQuery(name = "SeguimientoSalidasUmuTransitoHistorico.findBySolicitada", query = "SELECT s FROM SeguimientoSalidasUmuTransitoHistorico s WHERE s.solicitada = :solicitada"),
    @NamedQuery(name = "SeguimientoSalidasUmuTransitoHistorico.findByLote", query = "SELECT s FROM SeguimientoSalidasUmuTransitoHistorico s WHERE s.lote = :lote"),
    @NamedQuery(name = "SeguimientoSalidasUmuTransitoHistorico.findByPrecio", query = "SELECT s FROM SeguimientoSalidasUmuTransitoHistorico s WHERE s.precio = :precio"),
    @NamedQuery(name = "SeguimientoSalidasUmuTransitoHistorico.findByImporte", query = "SELECT s FROM SeguimientoSalidasUmuTransitoHistorico s WHERE s.importe = :importe"),
    @NamedQuery(name = "SeguimientoSalidasUmuTransitoHistorico.findByFechaCamion", query = "SELECT s FROM SeguimientoSalidasUmuTransitoHistorico s WHERE s.fechaCamion = :fechaCamion"),
    @NamedQuery(name = "SeguimientoSalidasUmuTransitoHistorico.findByFechaIngreso", query = "SELECT s FROM SeguimientoSalidasUmuTransitoHistorico s WHERE s.fechaIngreso = :fechaIngreso")})
public class SeguimientoSalidasUmuTransitoHistorico implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id_seguimiento_salidas_umu_transito_historico")
    private Integer idSeguimientoSalidasUmuTransitoHistorico;
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
    @Column(name = "fecha_comprometida")
    @Temporal(TemporalType.DATE)
    private Date fechaComprometida;
    @Column(name = "fecha_remision")
    @Temporal(TemporalType.DATE)
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
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Column(name = "importe")
    private BigDecimal importe;
    @Column(name = "fecha_camion")
    @Temporal(TemporalType.DATE)
    private Date fechaCamion;
    @Column(name = "fecha_ingreso")
    @Temporal(TemporalType.DATE)
    private Date fechaIngreso;

    public SeguimientoSalidasUmuTransitoHistorico() {
    }

    public SeguimientoSalidasUmuTransitoHistorico(Integer idSeguimientoSalidasUmuTransitoHistorico) {
        this.idSeguimientoSalidasUmuTransitoHistorico = idSeguimientoSalidasUmuTransitoHistorico;
    }

    public Integer getIdSeguimientoSalidasUmuTransitoHistorico() {
        return idSeguimientoSalidasUmuTransitoHistorico;
    }

    public void setIdSeguimientoSalidasUmuTransitoHistorico(Integer idSeguimientoSalidasUmuTransitoHistorico) {
        this.idSeguimientoSalidasUmuTransitoHistorico = idSeguimientoSalidasUmuTransitoHistorico;
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
        hash += (idSeguimientoSalidasUmuTransitoHistorico != null ? idSeguimientoSalidasUmuTransitoHistorico.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof SeguimientoSalidasUmuTransitoHistorico)) {
            return false;
        }
        SeguimientoSalidasUmuTransitoHistorico other = (SeguimientoSalidasUmuTransitoHistorico) object;
        if ((this.idSeguimientoSalidasUmuTransitoHistorico == null && other.idSeguimientoSalidasUmuTransitoHistorico != null) || (this.idSeguimientoSalidasUmuTransitoHistorico != null && !this.idSeguimientoSalidasUmuTransitoHistorico.equals(other.idSeguimientoSalidasUmuTransitoHistorico))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.issste.sicabis.ejb.modelo.SeguimientoSalidasUmuTransitoHistorico[ idSeguimientoSalidasUmuTransitoHistorico=" + idSeguimientoSalidasUmuTransitoHistorico + " ]";
    }
    
}
