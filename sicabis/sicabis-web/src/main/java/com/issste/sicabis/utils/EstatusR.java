/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.issste.sicabis.utils;

/**
 *
 * @author fabianvr
 */
public class EstatusR {

    private Integer recepcionDocumental = 28;
    private Integer controlCalidad = 29;
    private Integer autorizado = 30;
    private Integer devolucion = 31;
    private Integer procesado = 32;

    public EstatusR() {
    }

    public Integer getRecepcionDocumental() {
        return recepcionDocumental;
    }

    public void setRecepcionDocumental(Integer recepcionDocumental) {
        this.recepcionDocumental = recepcionDocumental;
    }

    public Integer getControlCalidad() {
        return controlCalidad;
    }

    public void setControlCalidad(Integer controlCalidad) {
        this.controlCalidad = controlCalidad;
    }

    public Integer getAutorizado() {
        return autorizado;
    }

    public void setAutorizado(Integer autorizado) {
        this.autorizado = autorizado;
    }

    public Integer getDevolucion() {
        return devolucion;
    }

    public void setDevolucion(Integer devolucion) {
        this.devolucion = devolucion;
    }

    public Integer getProcesado() {
        return procesado;
    }

    public void setProcesado(Integer procesado) {
        this.procesado = procesado;
    }

}
