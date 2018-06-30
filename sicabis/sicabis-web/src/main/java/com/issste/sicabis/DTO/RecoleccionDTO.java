package com.issste.sicabis.DTO;

import java.math.BigDecimal;
import java.util.Date;

/**
 *
 * @author fabianvr
 */
public class RecoleccionDTO {

    private String nombreUnidad;
    private String clave;
    private String descripcion;
    private Date caducidad;
    private Integer cantidad;
    private BigDecimal precio;
    private Date fechaAlta;
    private Integer idRecoleccion;

    public String getNombreUnidad() {
        return nombreUnidad;
    }

    public void setNombreUnidad(String nombreUnidad) {
        this.nombreUnidad = nombreUnidad;
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

    public Date getCaducidad() {
        return caducidad;
    }

    public void setCaducidad(Date caducidad) {
        this.caducidad = caducidad;
    }

    public Integer getCantidad() {
        return cantidad;
    }

    public Integer getIdRecoleccion() {
        return idRecoleccion;
    }

    public void setIdRecoleccion(Integer idRecoleccion) {
        this.idRecoleccion = idRecoleccion;
    }
    
    

    public void setCantidad(Integer cantidad) {
        this.cantidad = cantidad;
    }

    public BigDecimal getPrecio() {
        return precio;
    }

    public void setPrecio(BigDecimal precio) {
        this.precio = precio;
    }

    public Date getFechaAlta() {
        return fechaAlta;
    }

    public void setFechaAlta(Date fechaAlta) {
        this.fechaAlta = fechaAlta;
    }

}
