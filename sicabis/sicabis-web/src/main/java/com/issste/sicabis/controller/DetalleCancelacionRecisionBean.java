/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.issste.sicabis.controller;

import com.issste.sicabis.ejb.DTO.CancelacionRescicionDTO;
import com.issste.sicabis.ejb.ln.BitacoraTareaSerice;
import com.issste.sicabis.ejb.ln.CancelacionesService;
import com.issste.sicabis.ejb.ln.ContratoService;
import com.issste.sicabis.ejb.ln.DetalleOrdenSuministroService;
import com.issste.sicabis.ejb.ln.OrdenSuministroService;
import com.issste.sicabis.ejb.ln.RemisionesService;
import com.issste.sicabis.ejb.ln.RescisionesService;
import com.issste.sicabis.ejb.ln.UsuariosService;
import com.issste.sicabis.ejb.modelo.BitacoraTareaEstatus;
import com.issste.sicabis.ejb.modelo.Cancelaciones;
import com.issste.sicabis.ejb.modelo.ContratoFalloProcedimientoRcb;
import com.issste.sicabis.ejb.modelo.Contratos;
import com.issste.sicabis.ejb.modelo.DetalleOrdenSuministro;
import com.issste.sicabis.ejb.modelo.Estatus;
import com.issste.sicabis.ejb.modelo.OrdenSuministro;
import com.issste.sicabis.ejb.modelo.Rescisiones;
import com.issste.sicabis.ejb.modelo.UsuariosTipoUsuarios;
import com.issste.sicabis.ejb.utils.SendMail;
import com.issste.sicabis.utils.Mensajes;
import com.issste.sicabis.utils.Utilidades;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Objects;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;

/**
 *
 * @author 9RZCBG2
 */
public class DetalleCancelacionRecisionBean {

    //EJB's
    @EJB
    private UsuariosService usuariosService;
    @EJB
    private BitacoraTareaSerice bitacoraTareaSerice;
    @EJB
    private RescisionesService rescisionesService;
    @EJB
    private CancelacionesService cancelacionesService;
    @EJB
    private OrdenSuministroService ordenSuministroService;
    @EJB
    private RemisionesService remisionesService;
    @EJB
    private ContratoService contratoService;
    @EJB
    private DetalleOrdenSuministroService detalleOrdenSuministroService;

    //Varibles
    private Mensajes mensaje;
    private Integer seleccionaConsulta;

    //Objectos
    private List<DetalleOrdenSuministro> cancelacionRescisionList;
    private List<CancelacionRescicionDTO> listCancelacionRescicionDTO;
    private CancelacionRescicionDTO cancelacionRescicionDTO;
    private BitacoraTareaEstatus bitacoraTareaEstatus;
    private boolean verMensaje;
    private Utilidades util;
    private Contratos contratos;
    private List<Contratos> listContratos;
    private Integer posibleRescion;
    private Integer rescision;
    private String numContrato;

    public DetalleCancelacionRecisionBean() {
        listContratos = new ArrayList<>();
        listCancelacionRescicionDTO = new ArrayList<>();
        cancelacionRescicionDTO = new CancelacionRescicionDTO();
        bitacoraTareaEstatus = new BitacoraTareaEstatus();
        contratos = new Contratos();
        mensaje = new Mensajes();
        util = new Utilidades();
    }

    @PostConstruct
    public void init() {
        String sigla = "";
        cancelacionRescisionList = new ArrayList<>();
        Integer cantidadPen = 0;
        Integer cantSum = 0;
        Integer cantEnt = 0;
        Integer size = 0;
        List<DetalleOrdenSuministro> listIterator = new ArrayList<>();
        Date fechaFinal;
        cancelacionRescicionDTO = (CancelacionRescicionDTO) util.getContextAtributte("cancelacionRescicionDTO");
        listIterator = detalleOrdenSuministroService.obtenerListaOrdenesPendientesporSuministrar("", cancelacionRescicionDTO.getContrato());
        listContratos = contratoService.obtenerByNumeroContratoOrderById(cancelacionRescicionDTO.getContrato());
        contratos = listContratos.get(0);
        numContrato = contratos.getNumeroContrato();
        if(contratos.getIdContratoFalloProcedimientoRcbList() != null){
            ContratoFalloProcedimientoRcb cfpr = contratos.getIdContratoFalloProcedimientoRcbList().get(0);
            sigla = cfpr.getIdFalloProcedimientoRcb().getIdProcedimientoRcb().getIdRcbInsumos().getIdInsumo().getIdTipoInsumos().getSigla();
            numContrato = numContrato + sigla;
        }
        for (DetalleOrdenSuministro iterator : listIterator) {
            fechaFinal = iterator.getFechaEntregaFinal();
            fechaFinal = util.sumarRestarDiasFecha(fechaFinal, 4);
            String Contrato = iterator.getIdOrdenSuministro().getIdContrato().getNumeroContrato();
//            System.out.println("Contrato--->"+Contrato);
//            sigla = iterator.getIdFalloProcedimientoRcb().getIdProcedimientoRcb().getIdRcbInsumos().getIdInsumo().getIdTipoInsumos().getSigla();
//            System.out.println("sigla--->"+sigla);
            Double porcentaje = remisionesService.obtenerPorcentajePiezasPorSuministrar(cancelacionRescicionDTO.getContrato(), iterator.getIdFalloProcedimientoRcb().getIdProcedimientoRcb().getIdRcbInsumos().getClaveInsumo());
            Double cantidadEntregada = remisionesService.obtenerCantidadEntregadaPorOrden(iterator.getIdDetalleOrdenSuministro());
            if (cantidadEntregada == null) {
                iterator.setCantidadSuministrada(0);
            } else {
                iterator.setCantidadSuministrada(cantidadEntregada.intValue());
            }
            if (new Date().before(fechaFinal)) {
                iterator.setUsuarioAlta("Iniciar Proceso Cancelacion");// Varible temporal para mostar estatus sugerido
                iterator.setUsuarioBaja("PCancelacion"); // Varible temporal mostrar botones
            } else {
                if (porcentaje > .10 && new Date().after(fechaFinal)) {
                    iterator.setUsuarioAlta("Iniciar Proceso Rescision");
                    iterator.setUsuarioBaja("");// Varible temporal mostrar botones

                }
            }
            // Opciones Rescision
            if (iterator.getUsuarioAlta().equals("Iniciar Proceso Rescision")) {
                posibleRescion = 1;
                rescision = 0;
            }
            if (contratos.getIdEstatus().getIdEstatus() == 60) {
                posibleRescion = 0;
                rescision = 1;
                iterator.setUsuarioAlta("Rescindir");
            }
            if (contratos.getIdEstatus().getIdEstatus() == 58) {
                posibleRescion = 0;
                rescision = 0;
                iterator.setUsuarioAlta("Rescindido");
                iterator.setUsuarioBaja("S/N");
            }
            //Opciones Cancelaciones
            if (iterator.getIdOrdenSuministro().getIdEstatus().getIdEstatus() == 75) {
                iterator.setUsuarioAlta("Cancelar");
                iterator.setUsuarioBaja("S/N");
            }
            if (iterator.getIdOrdenSuministro().getIdEstatus().getIdEstatus() == 74) {
                iterator.setUsuarioAlta("Cancelado");
                iterator.setUsuarioBaja("S/N");
            }

            cantSum = cantSum + iterator.getCantidadSuministrar();
            cantEnt = cantEnt + iterator.getCantidadSuministrada();
            Integer sizeList = listIterator.size();
            String ordenSig = "";
            if (sizeList != size + 1) {
                ordenSig = listIterator.get(size + 1).getIdOrdenSuministro().getIdContrato().getNumeroContrato();
            } else {
                ordenSig = "ya no hay mas contratos";
            }
            if (!iterator.getIdOrdenSuministro().getNumeroOrden().equals(ordenSig)) {
                cantidadPen = cantSum - cantEnt;
                if (cantidadPen <= 0) {
                    iterator.setUsuarioAlta("Completo");// Varible temporal para mostar estatus sugerido
                    iterator.setUsuarioBaja("");// Varible temporal mostrar botones
                }
                iterator.setCantidadSuministrada(cantEnt);
                iterator.setCantidadSuministrar(cantSum);
                cancelacionRescisionList.add(iterator);
                cantEnt = 0;
                cantSum = 0;
            }
            size++;
        }
    }

    public void actualizarPosibleCancelacion(DetalleOrdenSuministro detalle) {
        Cancelaciones cancelaciones = new Cancelaciones();
        OrdenSuministro OrdenSuministro = new OrdenSuministro();
        OrdenSuministro.setIdOrdenSuministro(detalle.getIdOrdenSuministro().getIdOrdenSuministro());
        OrdenSuministro.setIdEstatus(new Estatus(75));
        OrdenSuministro.setActivo(1);
        OrdenSuministro.setCantidadSuministrar(detalle.getIdOrdenSuministro().getCantidadSuministrar());
        OrdenSuministro.setFechaAlta(detalle.getIdOrdenSuministro().getFechaOrden());
        OrdenSuministro.setImporte(detalle.getIdOrdenSuministro().getImporte());
        OrdenSuministro.setNumeroOrden(detalle.getIdOrdenSuministro().getNumeroOrden());
        OrdenSuministro.setObservaciones(detalle.getIdOrdenSuministro().getObservaciones());
        OrdenSuministro.setIdContrato(detalle.getIdOrdenSuministro().getIdContrato());
        ordenSuministroService.actualizaOrdenSuministro(OrdenSuministro);
//        // Guardado de Bitacora
//        bitacoraTareaEstatus.setFecha(new Date());
//        bitacoraTareaEstatus.setDescripcion("Posible Cancelacion");
//        bitacoraTareaEstatus.setIdEstatus(75);
//        bitacoraTareaEstatus.setIdModulos(4);
//        bitacoraTareaEstatus.setIdUsuarios(1);
//        bitacoraTareaEstatus.setIdTareaId(7);
//        bitacoraTareaSerice.guardarEnBitacora(bitacoraTareaEstatus);
        List<UsuariosTipoUsuarios> destinatarios = new ArrayList<>();
        destinatarios = usuariosService.getUsuariosByTipoUsuario(3);
        String mensaje = "Buenas Tardes \n"
                + "Se les informa que la orden de suministro con numero " + OrdenSuministro.getNumeroOrden() + " "
                + "inicia el proceso de cancelación.";
        for (UsuariosTipoUsuarios iterator : destinatarios) {
            SendMail.writeMail(iterator.getIdUsuario(), "Posible cancelación de la orden de suministro" + OrdenSuministro.getNumeroOrden() + "", mensaje);
        }
        init();
    }

    public void actualizarPosibleRescision() {
        Rescisiones rescisiones = new Rescisiones();
        for (Contratos iterator : listContratos) {
            iterator.setIdEstatus(new Estatus(60));
            contratoService.actualizaContrato(iterator);
            init();
        }
        List<UsuariosTipoUsuarios> destinatarios = new ArrayList<>();
        destinatarios = usuariosService.getUsuariosByTipoUsuario(4);
        String mensaje = "Buenas Tardes \n"
                + "Se les informa que el contrato " + listContratos.get(0).getNumeroContrato() + " "
                + "se inicia el proceso de rescisión.";
        for (UsuariosTipoUsuarios iterator : destinatarios) {
            SendMail.writeMail(iterator.getIdUsuario(), "Posible rescisión del contrato" + listContratos.get(0).getNumeroContrato() + "", mensaje);
        }
    }

    public void actualizarCancelacion(DetalleOrdenSuministro detalle) {
        Cancelaciones cancelaciones = new Cancelaciones();
        OrdenSuministro OrdenSuministro = new OrdenSuministro();
        Date fechaFinal;
        Double porcentaje = 0.0;
        BigDecimal pena = new BigDecimal(0);
        Calendar cal = Calendar.getInstance();
        BigDecimal importe = new BigDecimal(0);
        BigDecimal importeTotalOrden = new BigDecimal(0);
        //Actualizar orden
        OrdenSuministro.setIdOrdenSuministro(detalle.getIdOrdenSuministro().getIdOrdenSuministro());
        OrdenSuministro.setIdEstatus(new Estatus(74));
        OrdenSuministro.setActivo(1);
        OrdenSuministro.setCantidadSuministrar(detalle.getIdOrdenSuministro().getCantidadSuministrar());
        OrdenSuministro.setFechaAlta(detalle.getIdOrdenSuministro().getFechaOrden());
        OrdenSuministro.setImporte(detalle.getIdOrdenSuministro().getImporte());
        OrdenSuministro.setNumeroOrden(detalle.getIdOrdenSuministro().getNumeroOrden());
        OrdenSuministro.setObservaciones(detalle.getIdOrdenSuministro().getObservaciones());
        OrdenSuministro.setIdContrato(detalle.getIdOrdenSuministro().getIdContrato());
        OrdenSuministro.setFechaOrden(detalle.getIdOrdenSuministro().getFechaOrden());

        List<DetalleOrdenSuministro> listCanOrdenDetalle = new ArrayList<>();
        detalleOrdenSuministroService.ordenById(detalle.getIdOrdenSuministro().getIdOrdenSuministro());
        for (DetalleOrdenSuministro iterator : listCanOrdenDetalle) {
            if (remisionesService.obtenerCantidadEntregadaPorOrden(iterator.getIdDetalleOrdenSuministro()) < iterator.getCantidadSuministrar()) {
                importe = iterator.getIdFalloProcedimientoRcb().getPrecioUnitario().multiply(new BigDecimal(detalle.getCantidadSuministrar() - detalle.getCantidadSuministrada()));
                cal.setTime(iterator.getFechaEntregaFinal());
                cal.add(Calendar.DATE, 1);
                fechaFinal = cal.getTime();
                if (new Date().after(fechaFinal)) {
                    porcentaje = 2.5;
                }
                cal.add(Calendar.DATE, 2);
                fechaFinal = cal.getTime();
                if (new Date().after(fechaFinal)) {
                    porcentaje = 5.0;
                }
                cal.add(Calendar.DATE, 3);
                fechaFinal = cal.getTime();
                if (new Date().after(fechaFinal)) {
                    porcentaje = 7.5;
                }
                cal.add(Calendar.DATE, 4);
                fechaFinal = cal.getTime();
                if (new Date().after(fechaFinal)) {
                    porcentaje = 10.0;
                }
                pena = importe.multiply(new BigDecimal(porcentaje / 100));

                // DetalleOrdenSuministro
                detalle.setTotalCancelado(detalle.getCantidadSuministrar() - detalle.getCantidadSuministrada());
                detalle.setCancelado(1);
                detalleOrdenSuministroService.actualizar(detalle);
            }
            importeTotalOrden = importe.add(importeTotalOrden);
        }

        //Cancelacion 
        ordenSuministroService.actualizaOrdenSuministro(OrdenSuministro);
        cancelaciones.setActivo(1);
        cancelaciones.setIdDetalleOrdenSuministro(new DetalleOrdenSuministro(detalle.getIdDetalleOrdenSuministro()));
        cancelaciones.setIdEstatus(new Estatus(171));
        cancelaciones.setNumeroCancelacion("C1");
        cancelaciones.setProcentajeIncumplimiento((int) porcentaje.intValue());
        cancelaciones.setImporteTotal(importeTotalOrden);
        cancelaciones.setPena(pena);
        Integer id = cancelacionesService.guardar(cancelaciones);
        if (id != null) {
            verMensaje = true;
            mensaje.mensaje("Se Inicio Proceso de Cancelacion Correctamente ", "verde");
        }

//        // Guardado de Bitacora
//        bitacoraTareaEstatus.setFecha(new Date());
//        bitacoraTareaEstatus.setDescripcion("Cancelacion");
//        bitacoraTareaEstatus.setIdEstatus(171);
//        bitacoraTareaEstatus.setIdModulos(4);
//        bitacoraTareaEstatus.setIdUsuarios(1);
//        bitacoraTareaEstatus.setIdTareaId(17);
//        bitacoraTareaSerice.guardarEnBitacora(bitacoraTareaEstatus);
        List<UsuariosTipoUsuarios> destinatarios = new ArrayList<>();
        destinatarios = usuariosService.getUsuariosByTipoUsuario(3);
        String mensaje = "Buenas Tardes \n"
                + "Se les informa que la orden de suministro " + OrdenSuministro.getNumeroOrden() + " "
                + "se cancelo.";
        for (UsuariosTipoUsuarios iterator : destinatarios) {
            SendMail.writeMail(iterator.getIdUsuario(), "Cancelación de la orden de suministro" + OrdenSuministro.getNumeroOrden() + "", mensaje);
        }
        init();
    }

    public void actualizarRescision() {
        for (Contratos iterator : listContratos) {
            iterator.setIdEstatus(new Estatus(58));
            contratoService.actualizaContrato(iterator);
            init();
        }
        List<UsuariosTipoUsuarios> destinatarios = new ArrayList<>();
        destinatarios = usuariosService.getUsuariosByTipoUsuario(4);
        String mensaje = "Buenas Tardes \n"
                + "Se les informa que el contrato " + listContratos.get(0).getNumeroContrato() + " "
                + "se rescindió.";
        for (UsuariosTipoUsuarios iterator : destinatarios) {
            SendMail.writeMail(iterator.getIdUsuario(), "Rescisión del contrato" + listContratos.get(0).getNumeroContrato() + "", mensaje);
        }
    }

    //Getters and Setters
    public DetalleOrdenSuministroService getDetalleOrdenSuministroService() {
        return detalleOrdenSuministroService;
    }

    public void setDetalleOrdenSuministroService(DetalleOrdenSuministroService detalleOrdenSuministroService) {
        this.detalleOrdenSuministroService = detalleOrdenSuministroService;
    }

    public Mensajes getMensaje() {
        return mensaje;
    }

    public void setMensaje(Mensajes mensaje) {
        this.mensaje = mensaje;
    }

    public List<DetalleOrdenSuministro> getCancelacionRescisionList() {
        return cancelacionRescisionList;
    }

    public void setCancelacionRescisionList(List<DetalleOrdenSuministro> cancelacionRescisionList) {
        this.cancelacionRescisionList = cancelacionRescisionList;
    }

    public List<CancelacionRescicionDTO> getListCancelacionRescicionDTO() {
        return listCancelacionRescicionDTO;
    }

    public void setListCancelacionRescicionDTO(List<CancelacionRescicionDTO> listCancelacionRescicionDTO) {
        this.listCancelacionRescicionDTO = listCancelacionRescicionDTO;
    }

    public Utilidades getUtil() {
        return util;
    }

    public void setUtil(Utilidades util) {
        this.util = util;
    }

    public Contratos getContratos() {
        return contratos;
    }

    public void setContratos(Contratos contratos) {
        this.contratos = contratos;
    }

    public CancelacionRescicionDTO getCancelacionRescicionDTO() {
        return cancelacionRescicionDTO;
    }

    public void setCancelacionRescicionDTO(CancelacionRescicionDTO cancelacionRescicionDTO) {
        this.cancelacionRescicionDTO = cancelacionRescicionDTO;
    }

    public Integer getPosibleRescion() {
        return posibleRescion;
    }

    public void setPosibleRescion(Integer posibleRescion) {
        this.posibleRescion = posibleRescion;
    }

    public Integer getRescision() {
        return rescision;
    }

    public void setRescision(Integer rescision) {
        this.rescision = rescision;
    }

    public String getNumContrato() {
        return numContrato;
    }

    public void setNumContrato(String numContrato) {
        this.numContrato = numContrato;
    }

}
