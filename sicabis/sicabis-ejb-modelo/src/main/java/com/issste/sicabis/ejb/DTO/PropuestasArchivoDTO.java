/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.issste.sicabis.ejb.DTO;

import com.issste.sicabis.ejb.modelo.ProcedimientoRcb;
import com.issste.sicabis.ejb.modelo.Propuestas;
import java.math.BigDecimal;

/**
 *
 * @author Manolo
 */
public class PropuestasArchivoDTO {
    
    private Integer id;
    private ProcedimientoRcb procedimientoRcb;
    private String clave;
    private Integer cantidad;
    private BigDecimal precioRef;
    private BigDecimal precioDesc;
    private String nombreProveedor;
    private String tipoProveedor;
    private String clavesMal;
    private int numClavesMal;
    private String clavesBien;
    private int numClavesBien;
    private BigDecimal precioSinIva;
    private BigDecimal iva;
    
    private String desierta;
    private Propuestas propuestas;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public ProcedimientoRcb getProcedimientoRcb() {
        return procedimientoRcb;
    }

    public void setProcedimientoRcb(ProcedimientoRcb procedimientoRcb) {
        this.procedimientoRcb = procedimientoRcb;
    }
    
    public String getClave() {
        return clave;
    }

    public void setClave(String clave) {
        this.clave = clave;
    }

    public Integer getCantidad() {
        return cantidad;
    }

    public void setCantidad(Integer cantidad) {
        this.cantidad = cantidad;
    }

    public BigDecimal getPrecioRef() {
        return precioRef;
    }

    public void setPrecioRef(BigDecimal precioRef) {
        this.precioRef = precioRef;
    }

    public BigDecimal getPrecioDesc() {
        return precioDesc;
    }

    public void setPrecioDesc(BigDecimal precioDesc) {
        this.precioDesc = precioDesc;
    }

    public String getNombreProveedor() {
        return nombreProveedor;
    }

    public void setNombreProveedor(String nombreProveedor) {
        this.nombreProveedor = nombreProveedor;
    }

    public String getTipoProveedor() {
        return tipoProveedor;
    }

    public void setTipoProveedor(String tipoProveedor) {
        this.tipoProveedor = tipoProveedor;
    }

    public String getClavesMal() {
        return clavesMal;
    }

    public void setClavesMal(String clavesMal) {
        this.clavesMal = clavesMal;
    }

    public int getNumClavesMal() {
        return numClavesMal;
    }

    public void setNumClavesMal(int numClavesMal) {
        this.numClavesMal = numClavesMal;
    }
    
    public String getClavesBien() {
        return clavesBien;
    }

    public void setClavesBien(String clavesBien) {
        this.clavesBien = clavesBien;
    }

    public int getNumClavesBien() {
        return numClavesBien;
    }

    public void setNumClavesBien(int numClavesBien) {
        this.numClavesBien = numClavesBien;
    }

    public String getDesierta() {
        return desierta;
    }

    public void setDesierta(String desierta) {
        this.desierta = desierta;
    }

    public Propuestas getPropuestas() {
        return propuestas;
    }

    public void setPropuestas(Propuestas propuestas) {
        this.propuestas = propuestas;
    }

    public BigDecimal getPrecioSinIva() {
        return precioSinIva;
    }

    public void setPrecioSinIva(BigDecimal precioSinIva) {
        this.precioSinIva = precioSinIva;
    }

    public BigDecimal getIva() {
        return iva;
    }

    public void setIva(BigDecimal iva) {
        this.iva = iva;
    }
    
}
