package com.issste.sicabis.ejb.DTO;

import java.math.BigDecimal;
import java.util.Date;

/**
 *
 * @author fabianvr
 */
public class RemisionesDTO {

    private String registroControl;
    private String descripcionCorta;
    private String clave;
    private String descripcion;
    private Integer cantidad;
    private String numeroContrato;
    private String almacen;
    private String ordenSuministro;
    private String folioRemision;
    private Date fechaRemision;
    private String proveedor;
    private String estatus;
    private BigDecimal importeTotal;
    private BigDecimal importe;
    private Date fechaEntregaInicio;
    private Date fechaEntregaFin;
    private Integer renglon;
    private Integer idRemision;
    private Integer idSuministro;
    private String codigoBarras;
    private Integer lote;
    private String unidadPieza;
    private String articulo;
    private String estatusRemision;
    private Integer cantidadRecibidaRemision;
    
    private String numeroProcedimiento;

    public RemisionesDTO() {
       
    }

    public String getRegistroControl() {
        return registroControl;
    }

    public void setRegistroControl(String registroControl) {
        this.registroControl = registroControl;
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

    public Integer getCantidad() {
        return cantidad;
    }

    public void setCantidad(Integer cantidad) {
        this.cantidad = cantidad;
    }

    public String getNumeroContrato() {
        return numeroContrato;
    }

    public void setNumeroContrato(String numeroContrato) {
        this.numeroContrato = numeroContrato;
    }

    public String getAlmacen() {
        return almacen;
    }

    public void setAlmacen(String almacen) {
        this.almacen = almacen;
    }

    public String getOrdenSuministro() {
        return ordenSuministro;
    }

    public void setOrdenSuministro(String ordenSuministro) {
        this.ordenSuministro = ordenSuministro;
    }

    public String getFolioRemision() {
        return folioRemision;
    }

    public void setFolioRemision(String folioRemision) {
        this.folioRemision = folioRemision;
    }

    public Date getFechaRemision() {
        return fechaRemision;
    }

    public void setFechaRemision(Date fechaRemision) {
        this.fechaRemision = fechaRemision;
    }

    public String getProveedor() {
        return proveedor;
    }

    public void setProveedor(String proveedor) {
        this.proveedor = proveedor;
    }

    public String getEstatus() {
        return estatus;
    }

    public void setEstatus(String estatus) {
        this.estatus = estatus;
    }

    public BigDecimal getImporteTotal() {
        return importeTotal;
    }

    public void setImporteTotal(BigDecimal importeTotal) {
        this.importeTotal = importeTotal;
    }

    public Date getFechaEntregaInicio() {
        return fechaEntregaInicio;
    }

    public void setFechaEntregaInicio(Date fechaEntregaInicio) {
        this.fechaEntregaInicio = fechaEntregaInicio;
    }

    public Date getFechaEntregaFin() {
        return fechaEntregaFin;
    }

    public void setFechaEntregaFin(Date fechaEntregaFin) {
        this.fechaEntregaFin = fechaEntregaFin;
    }

    public Integer getRenglon() {
        return renglon;
    }

    public void setRenglon(Integer renglon) {
        this.renglon = renglon;
    }

    public Integer getIdSuministro() {
        return idSuministro;
    }

    public void setIdSuministro(Integer idSuministro) {
        this.idSuministro = idSuministro;
    }

    public String getCodigoBarras() {
        return codigoBarras;
    }

    public void setCodigoBarras(String codigoBarras) {
        this.codigoBarras = codigoBarras;
    }

    public Integer getLote() {
        return lote;
    }

    public void setLote(Integer lote) {
        this.lote = lote;
    }

    public BigDecimal getImporte() {
        return importe;
    }

    public void setImporte(BigDecimal importe) {
        this.importe = importe;
    }

    public String getUnidadPieza() {
        return unidadPieza;
    }

    public void setUnidadPieza(String unidadPieza) {
        this.unidadPieza = unidadPieza;
    }

    public String getArticulo() {
        return articulo;
    }

    public void setArticulo(String articulo) {
        this.articulo = articulo;
    }

    public Integer getIdRemision() {
        return idRemision;
    }

    public void setIdRemision(Integer idRemision) {
        this.idRemision = idRemision;
    }

    public String getEstatusRemision() {
        return estatusRemision;
    }

    public void setEstatusRemision(String estatusRemision) {
        this.estatusRemision = estatusRemision;
    }

    public Integer getCantidadRecibidaRemision() {
        return cantidadRecibidaRemision;
    }

    public void setCantidadRecibidaRemision(Integer cantidadRecibidaRemision) {
        this.cantidadRecibidaRemision = cantidadRecibidaRemision;
    }

    public String getDescripcionCorta() {
        return descripcionCorta;
    }

    public void setDescripcionCorta(String descripcionCorta) {
        this.descripcionCorta = descripcionCorta;
    }

    public String getNumeroProcedimiento() {
        return numeroProcedimiento;
    }

    public void setNumeroProcedimiento(String numeroProcedimiento) {
        this.numeroProcedimiento = numeroProcedimiento;
    }
    
}
