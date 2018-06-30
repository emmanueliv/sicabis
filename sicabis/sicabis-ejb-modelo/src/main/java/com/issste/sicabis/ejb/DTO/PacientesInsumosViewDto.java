/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.issste.sicabis.ejb.DTO;

import com.issste.sicabis.ejb.modelo.Pacientes;

/**
 *
 * @author Toshiba Manolo
 */
public class PacientesInsumosViewDto {
    
    private Pacientes paciente;
    private Integer idSolicitud;
    private Integer insumosSolicitados;
    

    public Pacientes getPaciente() {
        return paciente;
    }

    public void setPaciente(Pacientes paciente) {
        this.paciente = paciente;
    }

    public Integer getInsumosSolicitados() {
        return insumosSolicitados;
    }

    public void setInsumosSolicitados(Integer insumosSolicitados) {
        this.insumosSolicitados = insumosSolicitados;
    }

    public Integer getIdSolicitud() {
        return idSolicitud;
    }

    public void setIdSolicitud(Integer idSolicitud) {
        this.idSolicitud = idSolicitud;
    }
    
    
    
    
}
