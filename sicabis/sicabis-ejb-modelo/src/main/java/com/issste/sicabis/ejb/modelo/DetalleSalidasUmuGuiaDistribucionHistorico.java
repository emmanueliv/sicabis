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
@Table(name = "detalle_salidas_umu_guia_distribucion_historico")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "DetalleSalidasUmuGuiaDistribucionHistorico.findAll", query = "SELECT d FROM DetalleSalidasUmuGuiaDistribucionHistorico d"),
    @NamedQuery(name = "DetalleSalidasUmuGuiaDistribucionHistorico.findByIdDetalleSalidasUmuGuiaDistribucionHistorico", query = "SELECT d FROM DetalleSalidasUmuGuiaDistribucionHistorico d WHERE d.idDetalleSalidasUmuGuiaDistribucionHistorico = :idDetalleSalidasUmuGuiaDistribucionHistorico"),
    @NamedQuery(name = "DetalleSalidasUmuGuiaDistribucionHistorico.findByDelegacion", query = "SELECT d FROM DetalleSalidasUmuGuiaDistribucionHistorico d WHERE d.delegacion = :delegacion"),
    @NamedQuery(name = "DetalleSalidasUmuGuiaDistribucionHistorico.findByCodigoumu", query = "SELECT d FROM DetalleSalidasUmuGuiaDistribucionHistorico d WHERE d.codigoumu = :codigoumu"),
    @NamedQuery(name = "DetalleSalidasUmuGuiaDistribucionHistorico.findByPresupuestal", query = "SELECT d FROM DetalleSalidasUmuGuiaDistribucionHistorico d WHERE d.presupuestal = :presupuestal"),
    @NamedQuery(name = "DetalleSalidasUmuGuiaDistribucionHistorico.findByUmu", query = "SELECT d FROM DetalleSalidasUmuGuiaDistribucionHistorico d WHERE d.umu = :umu"),
    @NamedQuery(name = "DetalleSalidasUmuGuiaDistribucionHistorico.findByFolioissste", query = "SELECT d FROM DetalleSalidasUmuGuiaDistribucionHistorico d WHERE d.folioissste = :folioissste"),
    @NamedQuery(name = "DetalleSalidasUmuGuiaDistribucionHistorico.findByClave", query = "SELECT d FROM DetalleSalidasUmuGuiaDistribucionHistorico d WHERE d.clave = :clave"),
    @NamedQuery(name = "DetalleSalidasUmuGuiaDistribucionHistorico.findByRemision", query = "SELECT d FROM DetalleSalidasUmuGuiaDistribucionHistorico d WHERE d.remision = :remision"),
    @NamedQuery(name = "DetalleSalidasUmuGuiaDistribucionHistorico.findByFechasalida", query = "SELECT d FROM DetalleSalidasUmuGuiaDistribucionHistorico d WHERE d.fechasalida = :fechasalida"),
    @NamedQuery(name = "DetalleSalidasUmuGuiaDistribucionHistorico.findByNombre", query = "SELECT d FROM DetalleSalidasUmuGuiaDistribucionHistorico d WHERE d.nombre = :nombre"),
    @NamedQuery(name = "DetalleSalidasUmuGuiaDistribucionHistorico.findByTipo", query = "SELECT d FROM DetalleSalidasUmuGuiaDistribucionHistorico d WHERE d.tipo = :tipo"),
    @NamedQuery(name = "DetalleSalidasUmuGuiaDistribucionHistorico.findByLote", query = "SELECT d FROM DetalleSalidasUmuGuiaDistribucionHistorico d WHERE d.lote = :lote"),
    @NamedQuery(name = "DetalleSalidasUmuGuiaDistribucionHistorico.findBySolicitada", query = "SELECT d FROM DetalleSalidasUmuGuiaDistribucionHistorico d WHERE d.solicitada = :solicitada"),
    @NamedQuery(name = "DetalleSalidasUmuGuiaDistribucionHistorico.findByEstatus", query = "SELECT d FROM DetalleSalidasUmuGuiaDistribucionHistorico d WHERE d.estatus = :estatus"),
    @NamedQuery(name = "DetalleSalidasUmuGuiaDistribucionHistorico.findByPrecio", query = "SELECT d FROM DetalleSalidasUmuGuiaDistribucionHistorico d WHERE d.precio = :precio"),
    @NamedQuery(name = "DetalleSalidasUmuGuiaDistribucionHistorico.findByImporte", query = "SELECT d FROM DetalleSalidasUmuGuiaDistribucionHistorico d WHERE d.importe = :importe"),
    @NamedQuery(name = "DetalleSalidasUmuGuiaDistribucionHistorico.findByDpnMes", query = "SELECT d FROM DetalleSalidasUmuGuiaDistribucionHistorico d WHERE d.dpnMes = :dpnMes"),
    @NamedQuery(name = "DetalleSalidasUmuGuiaDistribucionHistorico.findByDpnYear", query = "SELECT d FROM DetalleSalidasUmuGuiaDistribucionHistorico d WHERE d.dpnYear = :dpnYear"),
    @NamedQuery(name = "DetalleSalidasUmuGuiaDistribucionHistorico.findByTipopedido", query = "SELECT d FROM DetalleSalidasUmuGuiaDistribucionHistorico d WHERE d.tipopedido = :tipopedido"),
    @NamedQuery(name = "DetalleSalidasUmuGuiaDistribucionHistorico.findByFecha", query = "SELECT d FROM DetalleSalidasUmuGuiaDistribucionHistorico d WHERE d.fecha = :fecha"),
    @NamedQuery(name = "DetalleSalidasUmuGuiaDistribucionHistorico.findByFechaIngreso", query = "SELECT d FROM DetalleSalidasUmuGuiaDistribucionHistorico d WHERE d.fechaIngreso = :fechaIngreso")})
public class DetalleSalidasUmuGuiaDistribucionHistorico implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id_detalle_salidas_umu_guia_distribucion_historico")
    private Integer idDetalleSalidasUmuGuiaDistribucionHistorico;
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
    @Column(name = "fechasalida")
    @Temporal(TemporalType.DATE)
    private Date fechasalida;
    @Size(max = 250)
    @Column(name = "nombre")
    private String nombre;
    @Size(max = 250)
    @Column(name = "tipo")
    private String tipo;
    @Size(max = 250)
    @Column(name = "lote")
    private String lote;
    @Column(name = "solicitada")
    private Integer solicitada;
    @Size(max = 250)
    @Column(name = "estatus")
    private String estatus;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Column(name = "precio")
    private BigDecimal precio;
    @Column(name = "importe")
    private BigDecimal importe;
    @Size(max = 250)
    @Column(name = "dpn_mes")
    private String dpnMes;
    @Size(max = 250)
    @Column(name = "dpn_year")
    private String dpnYear;
    @Size(max = 250)
    @Column(name = "tipopedido")
    private String tipopedido;
    @Column(name = "fecha")
    @Temporal(TemporalType.DATE)
    private Date fecha;
    @Column(name = "fecha_ingreso")
    @Temporal(TemporalType.DATE)
    private Date fechaIngreso;

    public DetalleSalidasUmuGuiaDistribucionHistorico() {
    }

    public DetalleSalidasUmuGuiaDistribucionHistorico(Integer idDetalleSalidasUmuGuiaDistribucionHistorico) {
        this.idDetalleSalidasUmuGuiaDistribucionHistorico = idDetalleSalidasUmuGuiaDistribucionHistorico;
    }

    public Integer getIdDetalleSalidasUmuGuiaDistribucionHistorico() {
        return idDetalleSalidasUmuGuiaDistribucionHistorico;
    }

    public void setIdDetalleSalidasUmuGuiaDistribucionHistorico(Integer idDetalleSalidasUmuGuiaDistribucionHistorico) {
        this.idDetalleSalidasUmuGuiaDistribucionHistorico = idDetalleSalidasUmuGuiaDistribucionHistorico;
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

    public Date getFechasalida() {
        return fechasalida;
    }

    public void setFechasalida(Date fechasalida) {
        this.fechasalida = fechasalida;
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

    public String getLote() {
        return lote;
    }

    public void setLote(String lote) {
        this.lote = lote;
    }

    public Integer getSolicitada() {
        return solicitada;
    }

    public void setSolicitada(Integer solicitada) {
        this.solicitada = solicitada;
    }

    public String getEstatus() {
        return estatus;
    }

    public void setEstatus(String estatus) {
        this.estatus = estatus;
    }

    public BigDecimal getPrecio() {
        return precio;
    }

    public void setPrecio(BigDecimal precio) {
        this.precio = precio;
    }

    public BigDecimal getImporte() {
        return importe;
    }

    public void setImporte(BigDecimal importe) {
        this.importe = importe;
    }

    public String getDpnMes() {
        return dpnMes;
    }

    public void setDpnMes(String dpnMes) {
        this.dpnMes = dpnMes;
    }

    public String getDpnYear() {
        return dpnYear;
    }

    public void setDpnYear(String dpnYear) {
        this.dpnYear = dpnYear;
    }

    public String getTipopedido() {
        return tipopedido;
    }

    public void setTipopedido(String tipopedido) {
        this.tipopedido = tipopedido;
    }

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
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
        hash += (idDetalleSalidasUmuGuiaDistribucionHistorico != null ? idDetalleSalidasUmuGuiaDistribucionHistorico.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof DetalleSalidasUmuGuiaDistribucionHistorico)) {
            return false;
        }
        DetalleSalidasUmuGuiaDistribucionHistorico other = (DetalleSalidasUmuGuiaDistribucionHistorico) object;
        if ((this.idDetalleSalidasUmuGuiaDistribucionHistorico == null && other.idDetalleSalidasUmuGuiaDistribucionHistorico != null) || (this.idDetalleSalidasUmuGuiaDistribucionHistorico != null && !this.idDetalleSalidasUmuGuiaDistribucionHistorico.equals(other.idDetalleSalidasUmuGuiaDistribucionHistorico))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.issste.sicabis.ejb.modelo.DetalleSalidasUmuGuiaDistribucionHistorico[ idDetalleSalidasUmuGuiaDistribucionHistorico=" + idDetalleSalidasUmuGuiaDistribucionHistorico + " ]";
    }
    
}
