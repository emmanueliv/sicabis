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
@Table(name = "entradas_mymcq_cenadi_historico")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "EntradasMymcqCenadiHistorico.findAll", query = "SELECT e FROM EntradasMymcqCenadiHistorico e"),
    @NamedQuery(name = "EntradasMymcqCenadiHistorico.findByIdEntradasMymcqCenadiHistorico", query = "SELECT e FROM EntradasMymcqCenadiHistorico e WHERE e.idEntradasMymcqCenadiHistorico = :idEntradasMymcqCenadiHistorico"),
    @NamedQuery(name = "EntradasMymcqCenadiHistorico.findByOcOracle", query = "SELECT e FROM EntradasMymcqCenadiHistorico e WHERE e.ocOracle = :ocOracle"),
    @NamedQuery(name = "EntradasMymcqCenadiHistorico.findByRegistroControl", query = "SELECT e FROM EntradasMymcqCenadiHistorico e WHERE e.registroControl = :registroControl"),
    @NamedQuery(name = "EntradasMymcqCenadiHistorico.findByContratoIssste", query = "SELECT e FROM EntradasMymcqCenadiHistorico e WHERE e.contratoIssste = :contratoIssste"),
    @NamedQuery(name = "EntradasMymcqCenadiHistorico.findByNumeroProveedor", query = "SELECT e FROM EntradasMymcqCenadiHistorico e WHERE e.numeroProveedor = :numeroProveedor"),
    @NamedQuery(name = "EntradasMymcqCenadiHistorico.findByProveedor", query = "SELECT e FROM EntradasMymcqCenadiHistorico e WHERE e.proveedor = :proveedor"),
    @NamedQuery(name = "EntradasMymcqCenadiHistorico.findByFechaIngreso", query = "SELECT e FROM EntradasMymcqCenadiHistorico e WHERE e.fechaIngreso = :fechaIngreso"),
    @NamedQuery(name = "EntradasMymcqCenadiHistorico.findByTipoEntrada", query = "SELECT e FROM EntradasMymcqCenadiHistorico e WHERE e.tipoEntrada = :tipoEntrada"),
    @NamedQuery(name = "EntradasMymcqCenadiHistorico.findByPresupuestal", query = "SELECT e FROM EntradasMymcqCenadiHistorico e WHERE e.presupuestal = :presupuestal"),
    @NamedQuery(name = "EntradasMymcqCenadiHistorico.findByClave", query = "SELECT e FROM EntradasMymcqCenadiHistorico e WHERE e.clave = :clave"),
    @NamedQuery(name = "EntradasMymcqCenadiHistorico.findByDescripcion", query = "SELECT e FROM EntradasMymcqCenadiHistorico e WHERE e.descripcion = :descripcion"),
    @NamedQuery(name = "EntradasMymcqCenadiHistorico.findByLote", query = "SELECT e FROM EntradasMymcqCenadiHistorico e WHERE e.lote = :lote"),
    @NamedQuery(name = "EntradasMymcqCenadiHistorico.findByCaducidad", query = "SELECT e FROM EntradasMymcqCenadiHistorico e WHERE e.caducidad = :caducidad"),
    @NamedQuery(name = "EntradasMymcqCenadiHistorico.findByCantidad", query = "SELECT e FROM EntradasMymcqCenadiHistorico e WHERE e.cantidad = :cantidad"),
    @NamedQuery(name = "EntradasMymcqCenadiHistorico.findByPrecioUnitario", query = "SELECT e FROM EntradasMymcqCenadiHistorico e WHERE e.precioUnitario = :precioUnitario"),
    @NamedQuery(name = "EntradasMymcqCenadiHistorico.findByImporte", query = "SELECT e FROM EntradasMymcqCenadiHistorico e WHERE e.importe = :importe"),
    @NamedQuery(name = "EntradasMymcqCenadiHistorico.findByImporteIva", query = "SELECT e FROM EntradasMymcqCenadiHistorico e WHERE e.importeIva = :importeIva"),
    @NamedQuery(name = "EntradasMymcqCenadiHistorico.findByImporteLiva", query = "SELECT e FROM EntradasMymcqCenadiHistorico e WHERE e.importeLiva = :importeLiva"),
    @NamedQuery(name = "EntradasMymcqCenadiHistorico.findByImporteTotal", query = "SELECT e FROM EntradasMymcqCenadiHistorico e WHERE e.importeTotal = :importeTotal"),
    @NamedQuery(name = "EntradasMymcqCenadiHistorico.findByFecha", query = "SELECT e FROM EntradasMymcqCenadiHistorico e WHERE e.fecha = :fecha"),
    @NamedQuery(name = "EntradasMymcqCenadiHistorico.findByActivo", query = "SELECT e FROM EntradasMymcqCenadiHistorico e WHERE e.activo = :activo"),
    @NamedQuery(name = "EntradasMymcqCenadiHistorico.findByFechaIngresoAux", query = "SELECT e FROM EntradasMymcqCenadiHistorico e WHERE e.fechaIngresoAux = :fechaIngresoAux")})
public class EntradasMymcqCenadiHistorico implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id_entradas_mymcq_cenadi_historico")
    private Integer idEntradasMymcqCenadiHistorico;
    @Size(max = 250)
    @Column(name = "oc_oracle")
    private String ocOracle;
    @Size(max = 250)
    @Column(name = "registro_control")
    private String registroControl;
    @Size(max = 250)
    @Column(name = "contrato_issste")
    private String contratoIssste;
    @Size(max = 250)
    @Column(name = "numero_proveedor")
    private String numeroProveedor;
    @Size(max = 1000)
    @Column(name = "proveedor")
    private String proveedor;
    @Column(name = "fecha_ingreso")
    @Temporal(TemporalType.DATE)
    private Date fechaIngreso;
    @Size(max = 100)
    @Column(name = "tipo_entrada")
    private String tipoEntrada;
    @Size(max = 250)
    @Column(name = "presupuestal")
    private String presupuestal;
    @Size(max = 250)
    @Column(name = "clave")
    private String clave;
    @Size(max = 5000)
    @Column(name = "descripcion")
    private String descripcion;
    @Size(max = 250)
    @Column(name = "lote")
    private String lote;
    @Size(max = 250)
    @Column(name = "caducidad")
    private String caducidad;
    @Column(name = "cantidad")
    private Integer cantidad;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Size(max = 250)
    @Column(name = "precio_unitario")
    private String precioUnitario;
    @Size(max = 250)
    @Column(name = "importe")
    private String importe;
    @Size(max = 250)
    @Column(name = "importe_iva")
    private String importeIva;
    @Size(max = 250)
    @Column(name = "importe_liva")
    private String importeLiva;
    @Size(max = 250)
    @Column(name = "importe_total")
    private String importeTotal;
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

    public EntradasMymcqCenadiHistorico() {
    }

    public EntradasMymcqCenadiHistorico(Integer idEntradasMymcqCenadiHistorico) {
        this.idEntradasMymcqCenadiHistorico = idEntradasMymcqCenadiHistorico;
    }

    public EntradasMymcqCenadiHistorico(Integer idEntradasMymcqCenadiHistorico, int activo) {
        this.idEntradasMymcqCenadiHistorico = idEntradasMymcqCenadiHistorico;
        this.activo = activo;
    }

    public Integer getIdEntradasMymcqCenadiHistorico() {
        return idEntradasMymcqCenadiHistorico;
    }

    public void setIdEntradasMymcqCenadiHistorico(Integer idEntradasMymcqCenadiHistorico) {
        this.idEntradasMymcqCenadiHistorico = idEntradasMymcqCenadiHistorico;
    }

    public String getOcOracle() {
        return ocOracle;
    }

    public void setOcOracle(String ocOracle) {
        this.ocOracle = ocOracle;
    }

    public String getRegistroControl() {
        return registroControl;
    }

    public void setRegistroControl(String registroControl) {
        this.registroControl = registroControl;
    }

    public String getContratoIssste() {
        return contratoIssste;
    }

    public void setContratoIssste(String contratoIssste) {
        this.contratoIssste = contratoIssste;
    }

    public String getNumeroProveedor() {
        return numeroProveedor;
    }

    public void setNumeroProveedor(String numeroProveedor) {
        this.numeroProveedor = numeroProveedor;
    }

    public String getProveedor() {
        return proveedor;
    }

    public void setProveedor(String proveedor) {
        this.proveedor = proveedor;
    }

    public Date getFechaIngreso() {
        return fechaIngreso;
    }

    public void setFechaIngreso(Date fechaIngreso) {
        this.fechaIngreso = fechaIngreso;
    }

    public String getTipoEntrada() {
        return tipoEntrada;
    }

    public void setTipoEntrada(String tipoEntrada) {
        this.tipoEntrada = tipoEntrada;
    }

    public String getPresupuestal() {
        return presupuestal;
    }

    public void setPresupuestal(String presupuestal) {
        this.presupuestal = presupuestal;
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

    public String getLote() {
        return lote;
    }

    public void setLote(String lote) {
        this.lote = lote;
    }

    public String getCaducidad() {
        return caducidad;
    }

    public void setCaducidad(String caducidad) {
        this.caducidad = caducidad;
    }

    public Integer getCantidad() {
        return cantidad;
    }

    public void setCantidad(Integer cantidad) {
        this.cantidad = cantidad;
    }

    public String getPrecioUnitario() {
        return precioUnitario;
    }

    public void setPrecioUnitario(String precioUnitario) {
        this.precioUnitario = precioUnitario;
    }

    public String getImporte() {
        return importe;
    }

    public void setImporte(String importe) {
        this.importe = importe;
    }

    public String getImporteIva() {
        return importeIva;
    }

    public void setImporteIva(String importeIva) {
        this.importeIva = importeIva;
    }

    public String getImporteLiva() {
        return importeLiva;
    }

    public void setImporteLiva(String importeLiva) {
        this.importeLiva = importeLiva;
    }

    public String getImporteTotal() {
        return importeTotal;
    }

    public void setImporteTotal(String importeTotal) {
        this.importeTotal = importeTotal;
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
        hash += (idEntradasMymcqCenadiHistorico != null ? idEntradasMymcqCenadiHistorico.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof EntradasMymcqCenadiHistorico)) {
            return false;
        }
        EntradasMymcqCenadiHistorico other = (EntradasMymcqCenadiHistorico) object;
        if ((this.idEntradasMymcqCenadiHistorico == null && other.idEntradasMymcqCenadiHistorico != null) || (this.idEntradasMymcqCenadiHistorico != null && !this.idEntradasMymcqCenadiHistorico.equals(other.idEntradasMymcqCenadiHistorico))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.issste.sicabis.ejb.modelo.EntradasMymcqCenadiHistorico[ idEntradasMymcqCenadiHistorico=" + idEntradasMymcqCenadiHistorico + " ]";
    }
    
}
