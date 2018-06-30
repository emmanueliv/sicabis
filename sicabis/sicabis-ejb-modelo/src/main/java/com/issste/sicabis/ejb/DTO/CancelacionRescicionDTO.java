/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.issste.sicabis.ejb.DTO;

import java.util.Date;

/**
 *
 * @author 9RZCBG2
 */
public class CancelacionRescicionDTO {

    private String contrato;
    private Integer cantidadSuministrar;
    private Integer cantidasSuministrada;
    private Integer cantidasPendiente;
    private Date vigenciaInicial;
    private Date vigenciaFinal;
    private String estatusSugerido;
    private Integer activo;

    public String getContrato() {
        return contrato;
    }

    public void setContrato(String contrato) {
        this.contrato = contrato;
    }

    public Integer getCantidadSuministrar() {
        return cantidadSuministrar;
    }

    public void setCantidadSuministrar(Integer cantidadSuministrar) {
        this.cantidadSuministrar = cantidadSuministrar;
    }

    public Integer getCantidasSuministrada() {
        return cantidasSuministrada;
    }

    public void setCantidasSuministrada(Integer cantidasSuministrada) {
        this.cantidasSuministrada = cantidasSuministrada;
    }

    public Integer getCantidasPendiente() {
        return cantidasPendiente;
    }

    public void setCantidasPendiente(Integer cantidasPendiente) {
        this.cantidasPendiente = cantidasPendiente;
    }

    public Date getVigenciaInicial() {
        return vigenciaInicial;
    }

    public void setVigenciaInicial(Date vigenciaInicial) {
        this.vigenciaInicial = vigenciaInicial;
    }

    public Date getVigenciaFinal() {
        return vigenciaFinal;
    }

    public void setVigenciaFinal(Date vigenciaFinal) {
        this.vigenciaFinal = vigenciaFinal;
    }

    public String getEstatusSugerido() {
        return estatusSugerido;
    }

    public void setEstatusSugerido(String estatusSugerido) {
        this.estatusSugerido = estatusSugerido;
    }

    public Integer getActivo() {
        return activo;
    }

    public void setActivo(Integer activo) {
        this.activo = activo;
    }

}
