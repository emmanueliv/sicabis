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
@Table(name = "detalle_salidas_umu_guia_distribucion")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "DetalleSalidasUmuGuiaDistribucion.findAll", query = "SELECT d FROM DetalleSalidasUmuGuiaDistribucion d"),
    @NamedQuery(name = "DetalleSalidasUmuGuiaDistribucion.findByIdDetalleSalidasUmuGuiaDistribucion", query = "SELECT d FROM DetalleSalidasUmuGuiaDistribucion d WHERE d.idDetalleSalidasUmuGuiaDistribucion = :idDetalleSalidasUmuGuiaDistribucion"),
    @NamedQuery(name = "DetalleSalidasUmuGuiaDistribucion.findByDelegacion", query = "SELECT d FROM DetalleSalidasUmuGuiaDistribucion d WHERE d.delegacion = :delegacion"),
    @NamedQuery(name = "DetalleSalidasUmuGuiaDistribucion.findByCodigoumu", query = "SELECT d FROM DetalleSalidasUmuGuiaDistribucion d WHERE d.codigoumu = :codigoumu"),
    @NamedQuery(name = "DetalleSalidasUmuGuiaDistribucion.findByPresupuestal", query = "SELECT d FROM DetalleSalidasUmuGuiaDistribucion d WHERE d.presupuestal = :presupuestal"),
    @NamedQuery(name = "DetalleSalidasUmuGuiaDistribucion.findByUmu", query = "SELECT d FROM DetalleSalidasUmuGuiaDistribucion d WHERE d.umu = :umu"),
    @NamedQuery(name = "DetalleSalidasUmuGuiaDistribucion.findByFolioissste", query = "SELECT d FROM DetalleSalidasUmuGuiaDistribucion d WHERE d.folioissste = :folioissste"),
    @NamedQuery(name = "DetalleSalidasUmuGuiaDistribucion.findByClave", query = "SELECT d FROM DetalleSalidasUmuGuiaDistribucion d WHERE d.clave = :clave"),
    @NamedQuery(name = "DetalleSalidasUmuGuiaDistribucion.findByRemision", query = "SELECT d FROM DetalleSalidasUmuGuiaDistribucion d WHERE d.remision = :remision"),
    @NamedQuery(name = "DetalleSalidasUmuGuiaDistribucion.findByFechasalida", query = "SELECT d FROM DetalleSalidasUmuGuiaDistribucion d WHERE d.fechasalida = :fechasalida"),
    @NamedQuery(name = "DetalleSalidasUmuGuiaDistribucion.findByNombre", query = "SELECT d FROM DetalleSalidasUmuGuiaDistribucion d WHERE d.nombre = :nombre"),
    @NamedQuery(name = "DetalleSalidasUmuGuiaDistribucion.findByTipo", query = "SELECT d FROM DetalleSalidasUmuGuiaDistribucion d WHERE d.tipo = :tipo"),
    @NamedQuery(name = "DetalleSalidasUmuGuiaDistribucion.findByLote", query = "SELECT d FROM DetalleSalidasUmuGuiaDistribucion d WHERE d.lote = :lote"),
    @NamedQuery(name = "DetalleSalidasUmuGuiaDistribucion.findBySolicitada", query = "SELECT d FROM DetalleSalidasUmuGuiaDistribucion d WHERE d.solicitada = :solicitada"),
    @NamedQuery(name = "DetalleSalidasUmuGuiaDistribucion.findByEstatus", query = "SELECT d FROM DetalleSalidasUmuGuiaDistribucion d WHERE d.estatus = :estatus"),
    @NamedQuery(name = "DetalleSalidasUmuGuiaDistribucion.findByPrecio", query = "SELECT d FROM DetalleSalidasUmuGuiaDistribucion d WHERE d.precio = :precio"),
    @NamedQuery(name = "DetalleSalidasUmuGuiaDistribucion.findByImporte", query = "SELECT d FROM DetalleSalidasUmuGuiaDistribucion d WHERE d.importe = :importe"),
    @NamedQuery(name = "DetalleSalidasUmuGuiaDistribucion.findByDpnMes", query = "SELECT d FROM DetalleSalidasUmuGuiaDistribucion d WHERE d.dpnMes = :dpnMes"),
    @NamedQuery(name = "DetalleSalidasUmuGuiaDistribucion.findByDpnYear", query = "SELECT d FROM DetalleSalidasUmuGuiaDistribucion d WHERE d.dpnYear = :dpnYear"),
    @NamedQuery(name = "DetalleSalidasUmuGuiaDistribucion.findByTipopedido", query = "SELECT d FROM DetalleSalidasUmuGuiaDistribucion d WHERE d.tipopedido = :tipopedido"),
    @NamedQuery(name = "DetalleSalidasUmuGuiaDistribucion.findByFecha", query = "SELECT d FROM DetalleSalidasUmuGuiaDistribucion d WHERE d.fecha = :fecha"),
    @NamedQuery(name = "DetalleSalidasUmuGuiaDistribucion.findByFechaIngreso", query = "SELECT d FROM DetalleSalidasUmuGuiaDistribucion d WHERE d.fechaIngreso = :fechaIngreso")})
public class DetalleSalidasUmuGuiaDistribucion implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id_detalle_salidas_umu_guia_distribucion")
    private Integer idDetalleSalidasUmuGuiaDistribucion;
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

    public DetalleSalidasUmuGuiaDistribucion() {
    }

    public DetalleSalidasUmuGuiaDistribucion(Integer idDetalleSalidasUmuGuiaDistribucion) {
        this.idDetalleSalidasUmuGuiaDistribucion = idDetalleSalidasUmuGuiaDistribucion;
    }

    public Integer getIdDetalleSalidasUmuGuiaDistribucion() {
        return idDetalleSalidasUmuGuiaDistribucion;
    }

    public void setIdDetalleSalidasUmuGuiaDistribucion(Integer idDetalleSalidasUmuGuiaDistribucion) {
        this.idDetalleSalidasUmuGuiaDistribucion = idDetalleSalidasUmuGuiaDistribucion;
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
        hash += (idDetalleSalidasUmuGuiaDistribucion != null ? idDetalleSalidasUmuGuiaDistribucion.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof DetalleSalidasUmuGuiaDistribucion)) {
            return false;
        }
        DetalleSalidasUmuGuiaDistribucion other = (DetalleSalidasUmuGuiaDistribucion) object;
        if ((this.idDetalleSalidasUmuGuiaDistribucion == null && other.idDetalleSalidasUmuGuiaDistribucion != null) || (this.idDetalleSalidasUmuGuiaDistribucion != null && !this.idDetalleSalidasUmuGuiaDistribucion.equals(other.idDetalleSalidasUmuGuiaDistribucion))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.issste.sicabis.ejb.service.silodisa.modelo.DetalleSalidasUmuGuiaDistribucion[ idDetalleSalidasUmuGuiaDistribucion=" + idDetalleSalidasUmuGuiaDistribucion + " ]";
    }

}
