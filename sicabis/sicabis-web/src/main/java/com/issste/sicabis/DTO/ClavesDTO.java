/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.issste.sicabis.DTO;

/**
 *
 * @author fabianvr
 */
public class ClavesDTO {

    private Integer idClave;
    private String clave;
    private String descripcion;
    private String unidadMedida;

    public Integer getIdClave() {
        return idClave;
    }

    public void setIdClave(Integer idClave) {
        this.idClave = idClave;
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

    public String getUnidadMedida() {
        return unidadMedida;
    }

    public void setUnidadMedida(String unidadMedida) {
        this.unidadMedida = unidadMedida;
    }
    
    
}
