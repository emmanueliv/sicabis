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
@Table(name = "remisiones_electronicas_entregas_umu_historico")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "RemisionesElectronicasEntregasUmuHistorico.findAll", query = "SELECT r FROM RemisionesElectronicasEntregasUmuHistorico r"),
    @NamedQuery(name = "RemisionesElectronicasEntregasUmuHistorico.findByIdRemisionesElectronicasEntregasUmuHistorico", query = "SELECT r FROM RemisionesElectronicasEntregasUmuHistorico r WHERE r.idRemisionesElectronicasEntregasUmuHistorico = :idRemisionesElectronicasEntregasUmuHistorico"),
    @NamedQuery(name = "RemisionesElectronicasEntregasUmuHistorico.findByDelegacion", query = "SELECT r FROM RemisionesElectronicasEntregasUmuHistorico r WHERE r.delegacion = :delegacion"),
    @NamedQuery(name = "RemisionesElectronicasEntregasUmuHistorico.findByCodigoumu", query = "SELECT r FROM RemisionesElectronicasEntregasUmuHistorico r WHERE r.codigoumu = :codigoumu"),
    @NamedQuery(name = "RemisionesElectronicasEntregasUmuHistorico.findByPresupuestal", query = "SELECT r FROM RemisionesElectronicasEntregasUmuHistorico r WHERE r.presupuestal = :presupuestal"),
    @NamedQuery(name = "RemisionesElectronicasEntregasUmuHistorico.findByUmu", query = "SELECT r FROM RemisionesElectronicasEntregasUmuHistorico r WHERE r.umu = :umu"),
    @NamedQuery(name = "RemisionesElectronicasEntregasUmuHistorico.findByNombreUmu", query = "SELECT r FROM RemisionesElectronicasEntregasUmuHistorico r WHERE r.nombreUmu = :nombreUmu"),
    @NamedQuery(name = "RemisionesElectronicasEntregasUmuHistorico.findByFolioissste", query = "SELECT r FROM RemisionesElectronicasEntregasUmuHistorico r WHERE r.folioissste = :folioissste"),
    @NamedQuery(name = "RemisionesElectronicasEntregasUmuHistorico.findByClave", query = "SELECT r FROM RemisionesElectronicasEntregasUmuHistorico r WHERE r.clave = :clave"),
    @NamedQuery(name = "RemisionesElectronicasEntregasUmuHistorico.findByDescripcionClave", query = "SELECT r FROM RemisionesElectronicasEntregasUmuHistorico r WHERE r.descripcionClave = :descripcionClave"),
    @NamedQuery(name = "RemisionesElectronicasEntregasUmuHistorico.findByRemision", query = "SELECT r FROM RemisionesElectronicasEntregasUmuHistorico r WHERE r.remision = :remision"),
    @NamedQuery(name = "RemisionesElectronicasEntregasUmuHistorico.findByFechaCamion", query = "SELECT r FROM RemisionesElectronicasEntregasUmuHistorico r WHERE r.fechaCamion = :fechaCamion"),
    @NamedQuery(name = "RemisionesElectronicasEntregasUmuHistorico.findByFechacert", query = "SELECT r FROM RemisionesElectronicasEntregasUmuHistorico r WHERE r.fechacert = :fechacert"),
    @NamedQuery(name = "RemisionesElectronicasEntregasUmuHistorico.findByNombre", query = "SELECT r FROM RemisionesElectronicasEntregasUmuHistorico r WHERE r.nombre = :nombre"),
    @NamedQuery(name = "RemisionesElectronicasEntregasUmuHistorico.findByTipo", query = "SELECT r FROM RemisionesElectronicasEntregasUmuHistorico r WHERE r.tipo = :tipo"),
    @NamedQuery(name = "RemisionesElectronicasEntregasUmuHistorico.findByFechaPrerecibo", query = "SELECT r FROM RemisionesElectronicasEntregasUmuHistorico r WHERE r.fechaPrerecibo = :fechaPrerecibo"),
    @NamedQuery(name = "RemisionesElectronicasEntregasUmuHistorico.findByFechaRemision", query = "SELECT r FROM RemisionesElectronicasEntregasUmuHistorico r WHERE r.fechaRemision = :fechaRemision"),
    @NamedQuery(name = "RemisionesElectronicasEntregasUmuHistorico.findBySolicitada", query = "SELECT r FROM RemisionesElectronicasEntregasUmuHistorico r WHERE r.solicitada = :solicitada"),
    @NamedQuery(name = "RemisionesElectronicasEntregasUmuHistorico.findByEntregada", query = "SELECT r FROM RemisionesElectronicasEntregasUmuHistorico r WHERE r.entregada = :entregada"),
    @NamedQuery(name = "RemisionesElectronicasEntregasUmuHistorico.findByLote", query = "SELECT r FROM RemisionesElectronicasEntregasUmuHistorico r WHERE r.lote = :lote"),
    @NamedQuery(name = "RemisionesElectronicasEntregasUmuHistorico.findByPrecio", query = "SELECT r FROM RemisionesElectronicasEntregasUmuHistorico r WHERE r.precio = :precio"),
    @NamedQuery(name = "RemisionesElectronicasEntregasUmuHistorico.findByImporte", query = "SELECT r FROM RemisionesElectronicasEntregasUmuHistorico r WHERE r.importe = :importe"),
    @NamedQuery(name = "RemisionesElectronicasEntregasUmuHistorico.findByDpnMes", query = "SELECT r FROM RemisionesElectronicasEntregasUmuHistorico r WHERE r.dpnMes = :dpnMes"),
    @NamedQuery(name = "RemisionesElectronicasEntregasUmuHistorico.findByDpnYear", query = "SELECT r FROM RemisionesElectronicasEntregasUmuHistorico r WHERE r.dpnYear = :dpnYear"),
    @NamedQuery(name = "RemisionesElectronicasEntregasUmuHistorico.findByTipopedido", query = "SELECT r FROM RemisionesElectronicasEntregasUmuHistorico r WHERE r.tipopedido = :tipopedido"),
    @NamedQuery(name = "RemisionesElectronicasEntregasUmuHistorico.findByTransactionDt", query = "SELECT r FROM RemisionesElectronicasEntregasUmuHistorico r WHERE r.transactionDt = :transactionDt"),
    @NamedQuery(name = "RemisionesElectronicasEntregasUmuHistorico.findByFechaIngreso", query = "SELECT r FROM RemisionesElectronicasEntregasUmuHistorico r WHERE r.fechaIngreso = :fechaIngreso"),
    @NamedQuery(name = "RemisionesElectronicasEntregasUmuHistorico.findByFecha", query = "SELECT r FROM RemisionesElectronicasEntregasUmuHistorico r WHERE r.fecha = :fecha")})
public class RemisionesElectronicasEntregasUmuHistorico implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id_remisiones_electronicas_entregas_umu_historico")
    private Integer idRemisionesElectronicasEntregasUmuHistorico;
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
    @Size(max = 250)
    @Column(name = "nombre_umu")
    private String nombreUmu;
    @Column(name = "folioissste")
    private Integer folioissste;
    @Size(max = 250)
    @Column(name = "clave")
    private String clave;
    @Size(max = 250)
    @Column(name = "descripcion_clave")
    private String descripcionClave;
    @Column(name = "remision")
    private Integer remision;
    @Size(max = 250)
    @Column(name = "fecha_camion")
    private String fechaCamion;
    @Size(max = 250)
    @Column(name = "fechacert")
    private String fechacert;
    @Size(max = 250)
    @Column(name = "nombre")
    private String nombre;
    @Size(max = 250)
    @Column(name = "tipo")
    private String tipo;
    @Size(max = 250)
    @Column(name = "fecha_prerecibo")
    private String fechaPrerecibo;
    @Size(max = 250)
    @Column(name = "fecha_remision")
    private String fechaRemision;
    @Column(name = "solicitada")
    private Integer solicitada;
    @Column(name = "entregada")
    private Integer entregada;
    @Size(max = 250)
    @Column(name = "lote")
    private String lote;
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
    @Column(name = "transaction_dt")
    private Integer transactionDt;
    @Column(name = "fecha_ingreso")
    @Temporal(TemporalType.DATE)
    private Date fechaIngreso;
    @Column(name = "fecha")
    @Temporal(javax.persistence.TemporalType.DATE)
    private Date fecha;

    public RemisionesElectronicasEntregasUmuHistorico() {
    }

    public RemisionesElectronicasEntregasUmuHistorico(Integer idRemisionesElectronicasEntregasUmuHistorico) {
        this.idRemisionesElectronicasEntregasUmuHistorico = idRemisionesElectronicasEntregasUmuHistorico;
    }

    public Integer getIdRemisionesElectronicasEntregasUmuHistorico() {
        return idRemisionesElectronicasEntregasUmuHistorico;
    }

    public void setIdRemisionesElectronicasEntregasUmuHistorico(Integer idRemisionesElectronicasEntregasUmuHistorico) {
        this.idRemisionesElectronicasEntregasUmuHistorico = idRemisionesElectronicasEntregasUmuHistorico;
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

    public String getNombreUmu() {
        return nombreUmu;
    }

    public void setNombreUmu(String nombreUmu) {
        this.nombreUmu = nombreUmu;
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

    public String getDescripcionClave() {
        return descripcionClave;
    }

    public void setDescripcionClave(String descripcionClave) {
        this.descripcionClave = descripcionClave;
    }

    public Integer getRemision() {
        return remision;
    }

    public void setRemision(Integer remision) {
        this.remision = remision;
    }

    public String getFechaCamion() {
        return fechaCamion;
    }

    public void setFechaCamion(String fechaCamion) {
        this.fechaCamion = fechaCamion;
    }

    public String getFechacert() {
        return fechacert;
    }

    public void setFechacert(String fechacert) {
        this.fechacert = fechacert;
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

    public String getFechaPrerecibo() {
        return fechaPrerecibo;
    }

    public void setFechaPrerecibo(String fechaPrerecibo) {
        this.fechaPrerecibo = fechaPrerecibo;
    }

    public String getFechaRemision() {
        return fechaRemision;
    }

    public void setFechaRemision(String fechaRemision) {
        this.fechaRemision = fechaRemision;
    }

    public Integer getSolicitada() {
        return solicitada;
    }
    
    

    public void setSolicitada(Integer solicitada) {
        this.solicitada = solicitada;
    }

    public Integer getEntregada() {
        return entregada;
    }

    public void setEntregada(Integer entregada) {
        this.entregada = entregada;
    }

    public String getLote() {
        return lote;
    }

    public void setLote(String lote) {
        this.lote = lote;
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

    public Integer getTransactionDt() {
        return transactionDt;
    }

    public void setTransactionDt(Integer transactionDt) {
        this.transactionDt = transactionDt;
    }

    public Date getFechaIngreso() {
        return fechaIngreso;
    }

    public void setFechaIngreso(Date fechaIngreso) {
        this.fechaIngreso = fechaIngreso;
    }

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }
    
    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idRemisionesElectronicasEntregasUmuHistorico != null ? idRemisionesElectronicasEntregasUmuHistorico.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof RemisionesElectronicasEntregasUmuHistorico)) {
            return false;
        }
        RemisionesElectronicasEntregasUmuHistorico other = (RemisionesElectronicasEntregasUmuHistorico) object;
        if ((this.idRemisionesElectronicasEntregasUmuHistorico == null && other.idRemisionesElectronicasEntregasUmuHistorico != null) || (this.idRemisionesElectronicasEntregasUmuHistorico != null && !this.idRemisionesElectronicasEntregasUmuHistorico.equals(other.idRemisionesElectronicasEntregasUmuHistorico))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.issste.sicabis.ejb.modelo.RemisionesElectronicasEntregasUmuHistorico[ idRemisionesElectronicasEntregasUmuHistorico=" + idRemisionesElectronicasEntregasUmuHistorico + " ]";
    }
    
}
