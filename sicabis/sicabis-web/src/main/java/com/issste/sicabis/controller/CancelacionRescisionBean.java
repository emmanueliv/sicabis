/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.issste.sicabis.controller;

import com.issste.sicabis.ejb.DTO.CancelacionRescicionDTO;
import com.issste.sicabis.ejb.ln.DetalleOrdenSuministroService;
import com.issste.sicabis.ejb.ln.RemisionesService;
import com.issste.sicabis.ejb.modelo.DetalleOrdenSuministro;
import com.issste.sicabis.utils.Mensajes;
import com.issste.sicabis.utils.Utilidades;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;

/**
 *
 * @author 9RZCBG2
 */
public class CancelacionRescisionBean {

    //EJB's
    @EJB
    private DetalleOrdenSuministroService detalleOrdenSuministroService;

    @EJB
    private RemisionesService remisionesService;

    //Varibles
    private Mensajes mensaje;
    private Integer seleccionaConsulta;
    private String clave;
    private String contrato;

    //Objectos
    private List<DetalleOrdenSuministro> cancelacionRescisionList;
    private List<DetalleOrdenSuministro> ordenesSinPenaList;
    private List<CancelacionRescicionDTO> listCancelacionRescicionDTO;
    private CancelacionRescicionDTO cancelacionRescicionDTO;
    private boolean verMensaje;
    private Utilidades util;

    public CancelacionRescisionBean() {
        cancelacionRescisionList = new ArrayList<>();
        listCancelacionRescicionDTO = new ArrayList<>();
        cancelacionRescicionDTO = new CancelacionRescicionDTO();
        mensaje = new Mensajes();
        util = new Utilidades();
    }

    @PostConstruct
    public void init() {

    }

    public String verDetalle() {
        util.setContextAtributte("cancelacionRescicionDTO", this.cancelacionRescicionDTO);
        return "detalleCancelacionRescision.xhtml?faces-redirect=true";
    }

    public void listaOrdenesPendientesPorSuministrar() {
        listCancelacionRescicionDTO = new ArrayList<>();
        int size = 0;
        int cantSum = 0;
        int cantEnt = 0;
        String contratoSig = "";
        String contratoAct = "";
        String contratoAnt = "";
        boolean bandera = true;
        Date fechaFinal;
        CancelacionRescicionDTO cancelacionRescicionDTO;
        cancelacionRescisionList = detalleOrdenSuministroService.obtenerListaOrdenesPendientesporSuministrar(clave, contrato);
        ordenesSinPenaList = detalleOrdenSuministroService.obtenerClavesSinAtraso(clave, contrato);
        if (cancelacionRescisionList != null || ordenesSinPenaList != null) {
//            if (ordenesSinPenaList != null) {
//                String contrato = "";
//                String contratoSiguiente = "";
//                int x = 0;
//                for (DetalleOrdenSuministro iterator1 : ordenesSinPenaList) {
//                    cancelacionRescicionDTO = new CancelacionRescicionDTO();
//                    contrato = iterator1.getIdOrdenSuministro().getIdContrato().getNumeroContrato();
//                    Integer sizeList = ordenesSinPenaList.size();
//                    if (sizeList != x + 1) {
//                        contratoSiguiente = ordenesSinPenaList.get(x + 1).getIdOrdenSuministro().getIdContrato().getNumeroContrato();
//                    } else {
//                        contratoSiguiente = "ya no hay mas contratos";
//                    }
//                    cantSum = cantSum + iterator1.getCantidadSuministrar();
//                    cantEnt = cantEnt + iterator1.getCantidadSuministrada();
//                    if (!contrato.equals(contratoSiguiente)) {
//                        cancelacionRescicionDTO.setCantidadSuministrar(cantSum);
//                        cancelacionRescicionDTO.setCantidasSuministrada(cantEnt);
//                        cancelacionRescicionDTO.setContrato(contrato);
//                        cancelacionRescicionDTO.setVigenciaFinal(iterator1.getFechaEntregaFinal());
//                        cancelacionRescicionDTO.setVigenciaInicial(iterator1.getFechaEntregaInicial());
//                        cancelacionRescicionDTO.setEstatusSugerido("Sin Incumplimiento");
//                        cancelacionRescicionDTO.setCantidasPendiente(cantSum - cantEnt);
//                        cancelacionRescicionDTO.setActivo(0);
//                        listCancelacionRescicionDTO.add(cancelacionRescicionDTO);
//                        cantEnt = 0;
//                        cantSum = 0;
//                        cantSum = iterator1.getCantidadSuministrar();
//                        cantEnt = iterator1.getCantidadSuministrada();
//                    }
//                    x++;
//                }
//            }
//            cantEnt = 0;
//            cantSum = 0;
            if (cancelacionRescisionList != null) {
                for (DetalleOrdenSuministro iterator : cancelacionRescisionList) {
                    fechaFinal = iterator.getFechaEntregaFinal();
                    fechaFinal = util.sumarRestarDiasFecha(fechaFinal, 4);
                    cancelacionRescicionDTO = new CancelacionRescicionDTO();
                    Double cantidadEntregada = remisionesService.obtenerCantidadEntregadaPorOrden(iterator.getIdDetalleOrdenSuministro());
                    if (cantidadEntregada == null) {
                        iterator.setCantidadSuministrada(0);
                    } else {
                        iterator.setCantidadSuministrada(cantidadEntregada.intValue());
                    }
                    Integer cantidadPen = iterator.getCantidadSuministrar() - iterator.getCantidadSuministrada();
                    if (size == 0) {
                        contratoAnt = cancelacionRescisionList.get(size).getIdOrdenSuministro().getIdContrato().getNumeroContrato();
                    } else {
                        contratoAnt = cancelacionRescisionList.get(size - 1).getIdOrdenSuministro().getIdContrato().getNumeroContrato();
                    }
                    contratoAct = iterator.getIdOrdenSuministro().getIdContrato().getNumeroContrato();
                    Integer sizeList = cancelacionRescisionList.size();
                    if (sizeList != size + 1) {
                        contratoSig = cancelacionRescisionList.get(size + 1).getIdOrdenSuministro().getIdContrato().getNumeroContrato();
                    } else {
                        contratoSig = "ya no hay mas contratos";
                    }
                    cancelacionRescicionDTO.setActivo(1);
                    if (contratoAnt.equals(contratoAct) && cantidadPen >= 0) {
                        cantSum = cantSum + iterator.getCantidadSuministrar();
                        cantEnt = cantEnt + iterator.getCantidadSuministrada();
                        // Cuando la clave del contrato es la misma 
                        if (!contrato.equals("")) {
                            bandera = false;
                            listCancelacionRescicionDTO = new ArrayList<>();
                            cancelacionRescicionDTO.setCantidadSuministrar(cantSum);
                            cancelacionRescicionDTO.setCantidasSuministrada(cantEnt);
                            cantidadPen = cancelacionRescicionDTO.getCantidadSuministrar() - cancelacionRescicionDTO.getCantidasSuministrada();
                            cancelacionRescicionDTO.setContrato(contratoAct);
                            cancelacionRescicionDTO.setVigenciaFinal(iterator.getIdOrdenSuministro().getIdContrato().getVigenciaFinal());
                            cancelacionRescicionDTO.setVigenciaInicial(iterator.getIdOrdenSuministro().getIdContrato().getVigenciaInicial());
                            Double porcentaje = remisionesService.obtenerPorcentajePiezasPorSuministrar(contratoAct, "");
                            if (cantidadPen > 0) {
                                if (new Date().after(fechaFinal)) {
                                    if (porcentaje > .10) {
                                        cancelacionRescicionDTO.setEstatusSugerido("Posible Rescision");
                                    } else {
                                        cancelacionRescicionDTO.setEstatusSugerido("Posible Cancelacion");
                                    }
                                } else {
                                    cancelacionRescicionDTO.setEstatusSugerido("Posible Cancelacion");
                                }
                                cancelacionRescicionDTO.setCantidasPendiente(cantSum - cantEnt);
                            }
                            listCancelacionRescicionDTO.add(cancelacionRescicionDTO);
                        }
                    }
                    if (!contratoAct.equals(contratoSig) && cantidadPen >= 0 && bandera) {
                        cancelacionRescicionDTO.setCantidadSuministrar(cantSum);
                        cancelacionRescicionDTO.setCantidasSuministrada(cantEnt);
                        cancelacionRescicionDTO.setContrato(contratoAct);
                        cancelacionRescicionDTO.setVigenciaFinal(iterator.getFechaEntregaFinal());
                        cancelacionRescicionDTO.setVigenciaInicial(iterator.getFechaEntregaInicial());
                        Double porcentaje = remisionesService.obtenerPorcentajePiezasPorSuministrar(contratoAct, clave);
                        if (new Date().after(fechaFinal)) {
                            if (porcentaje > .10) {
                                cancelacionRescicionDTO.setEstatusSugerido("Posible Rescision");
                            } else {
                                cancelacionRescicionDTO.setEstatusSugerido("Posible Cancelacion");
                            }
                        } else {
                            cancelacionRescicionDTO.setEstatusSugerido("Posible Cancelacion");
                        }
                        cancelacionRescicionDTO.setCantidasPendiente(cantSum - cantEnt);
                        listCancelacionRescicionDTO.add(cancelacionRescicionDTO);

                        //Resetean variables para conteo de piezas
                        cantEnt = 0;
                        cantSum = 0;
                        cantSum = iterator.getCantidadSuministrar();
                        cantEnt = iterator.getCantidadSuministrada();
                    }
                    size++;
                }
            }

        } else {
            mensaje.mensaje("No existen ordenes de suministro.", "amarillo");
        }
    }

    //Getters and Setters
    public Mensajes getMensaje() {
        return mensaje;
    }

    public void setMensaje(Mensajes mensaje) {
        this.mensaje = mensaje;
    }

    public Integer getSeleccionaConsulta() {
        return seleccionaConsulta;
    }

    public void setSeleccionaConsulta(Integer seleccionaConsulta) {
        this.seleccionaConsulta = seleccionaConsulta;
    }

    public List<DetalleOrdenSuministro> getCancelacionRescisionList() {
        return cancelacionRescisionList;
    }

    public void setCancelacionRescisionList(List<DetalleOrdenSuministro> cancelacionRescisionList) {
        this.cancelacionRescisionList = cancelacionRescisionList;
    }

    public boolean isVerMensaje() {
        return verMensaje;
    }

    public void setVerMensaje(boolean verMensaje) {
        this.verMensaje = verMensaje;
    }

    public String getClave() {
        return clave;
    }

    public void setClave(String clave) {
        this.clave = clave;
    }

    public String getContrato() {
        return contrato;
    }

    public void setContrato(String contrato) {
        this.contrato = contrato;
    }

    public List<CancelacionRescicionDTO> getListCancelacionRescicionDTO() {
        return listCancelacionRescicionDTO;
    }

    public void setListCancelacionRescicionDTO(List<CancelacionRescicionDTO> listCancelacionRescicionDTO) {
        this.listCancelacionRescicionDTO = listCancelacionRescicionDTO;
    }

    public CancelacionRescicionDTO getCancelacionRescicionDTO() {
        return cancelacionRescicionDTO;
    }

    public void setCancelacionRescicionDTO(CancelacionRescicionDTO cancelacionRescicionDTO) {
        this.cancelacionRescicionDTO = cancelacionRescicionDTO;
    }

}
