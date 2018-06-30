/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.issste.sicabis.ejb.utils;

import com.issste.sicabis.ejb.ln.CorreoElectronicoService;
import com.issste.sicabis.ejb.ln.DetalleOrdenSuministroService;
import com.issste.sicabis.ejb.ln.RemisionesService;
import com.issste.sicabis.ejb.ln.UsuariosService;
import com.issste.sicabis.ejb.modelo.CorreoElectronico;
import com.issste.sicabis.ejb.modelo.DetalleOrdenSuministro;
import com.issste.sicabis.ejb.modelo.Usuarios;
import com.issste.sicabis.ejb.modelo.UsuariosTipoUsuarios;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import javax.ejb.EJB;
import javax.ejb.Stateless;

/**
 *
 * @author 9RZCBG2
 */
@Stateless
public class MailInsumosPendientes {

    //EJB's
    @EJB
    private UsuariosService usuariosService;
    @EJB
    private CorreoElectronicoService correoElectronicoService;
    @EJB
    private RemisionesService remisionesService;
    @EJB
    private DetalleOrdenSuministroService detalleOrdenSuministroService;

    //Objetos
    SendMail sendMail;
    Utilidades util;

    public MailInsumosPendientes() {
        sendMail = new SendMail();
        util = new Utilidades();
    }

    public boolean sendMailInsumosPendientesPorSuministrar() {
        List<UsuariosTipoUsuarios> destinatarios = new ArrayList<>();
        destinatarios = usuariosService.getUsuariosByTipoUsuario(1);
        for (UsuariosTipoUsuarios iterator : destinatarios) {
            SendMail.writeMail(iterator.getIdUsuario(), "Insumos Pendientes de Suministrar con un dia de atraso", listaOrdenesVencidasPrimerDia(new Date()));
            System.out.println("users: " + iterator.getIdTipoUsuario());

        }
        destinatarios = new ArrayList<>();
        destinatarios = usuariosService.getUsuariosByTipoUsuario(5);
        for (UsuariosTipoUsuarios iterator : destinatarios) {
            SendMail.writeMail(iterator.getIdUsuario(), "Insumos Pendientes de Suministrar con 5 dias de atraso", listaOrdenesVencidasQuintoDia(new Date()));
            System.out.println("users: " + iterator.getIdUsuario());
        }
        return true;
    }

    //Correo html
    public String listaOrdenesVencidasPrimerDia(Date fechaAnterior) {
//        SimpleDateFormat formatoDeFecha = new SimpleDateFormat("yyyy/MM/dd");
//        try {
//            fechaAnterior = formatoDeFecha.parse("2012/10/25");
//        } catch (ParseException ex) {
//            Logger.getLogger(CancelacionRescisionBean.class.getName()).log(Level.SEVERE, null, ex);
//        }
        List<DetalleOrdenSuministro> listDetalleOrd = new ArrayList<>();
        String correo = "";
        String parts = "";
        boolean bandera = false;
        Integer cantidadEntregada = 0;
        Date fechaExtendida = util.sumaDiasHabiles(fechaAnterior, 1);
        listDetalleOrd = detalleOrdenSuministroService.detalleByFechaFinalPorDia(fechaExtendida, fechaAnterior);
        if (listDetalleOrd != null) {
            for (DetalleOrdenSuministro iterator : listDetalleOrd) {
                if (remisionesService.obtenerCantidadEntregadaPorOrden(iterator.getIdDetalleOrdenSuministro()) != null) {
                    cantidadEntregada = remisionesService.obtenerCantidadEntregadaPorOrden(iterator.getIdDetalleOrdenSuministro()).intValue();
                }
                Integer cantidadPendiente = iterator.getCantidadSuministrar() - cantidadEntregada;
                if (bandera) {
                    parts = parts + "<tr>"
                            + "<td>" + iterator.getIdOrdenSuministro().getIdContrato().getNumeroContrato() + "</td>\n"
                            + "<td>" + iterator.getIdFalloProcedimientoRcb().getIdProcedimientoRcb().getIdRcbInsumos().getClaveInsumo() + "</td>\n"
                            + "<td>" + iterator.getIdFalloProcedimientoRcb().getIdProcedimientoRcb().getIdRcbInsumos().getDescripcionInsumo() + "</td>\n"
                            + "<td>" + iterator.getCantidadSuministrar() + "</td>\n"
                            + "<td>" + cantidadEntregada + "</td>\n"
                            + "<td>" + cantidadPendiente.toString() + "</td>\n"
                            + "<td>" + iterator.getIdFalloProcedimientoRcb().getIdProcedimientoRcb().getIdProcedimiento().getNumeroProcedimiento() + "</td>\n"
                            + "<td>" + iterator.getFechaEntregaInicial() + "</td>\n"
                            + "<td>" + iterator.getFechaEntregaFinal() + "</td>"
                            + "</tr>\n";
                } else {
                    bandera = true;
                    parts = "<tr><td>" + iterator.getIdOrdenSuministro().getIdContrato().getNumeroContrato() + "</td>\n"
                            + "<td>" + iterator.getIdFalloProcedimientoRcb().getIdProcedimientoRcb().getIdRcbInsumos().getClaveInsumo() + "</td>\n"
                            + "<td>" + iterator.getIdFalloProcedimientoRcb().getIdProcedimientoRcb().getIdRcbInsumos().getDescripcionInsumo() + "</td>\n"
                            + "<td>" + iterator.getCantidadSuministrar() + "</td>\n"
                            + "<td>" + cantidadEntregada + "</td>\n"
                            + "<td>" + cantidadPendiente.toString() + "</td>\n"
                            + "<td>" + iterator.getIdFalloProcedimientoRcb().getIdProcedimientoRcb().getIdProcedimiento().getNumeroProcedimiento() + "</td>\n"
                            + "<td>" + iterator.getFechaEntregaInicial() + "</td>\n"
                            + "<td>" + iterator.getFechaEntregaFinal() + "</td></tr>\n";
                }
            }
            correo = "<!DOCTYPE html>\n"
                    + "<html>\n"
                    + "<body>\n"
                    + "<table border=\"1\">\n"
                    + "<tr>\n"
                    + "<th Align=\"center\">Contrato</th>\n"
                    + "<th Align=\"center\">Clave</th>\n"
                    + "<th Align=\"center\">Descripcion</th>\n"
                    + "<th Align=\"center\">Cantidad programada</th>\n"
                    + "<th Align=\"center\">Cantidad entregada</th>\n"
                    + "<th Align=\"center\">Cantidad pendiente</th>\n"
                    + "<th Align=\"center\">Procedimiento</th>\n"
                    + "<th Align=\"center\">Fecha incial</th>\n"
                    + "<th Align=\"center\">Fecha final</th>\n"
                    + "</tr>\n"
                    + parts
                    + "</table>\n"
                    + "</body>\n"
                    + "</html>";
        }
        return correo;
    }

    public String listaOrdenesVencidasQuintoDia(Date fechaAnterior) {
//        SimpleDateFormat formatoDeFecha = new SimpleDateFormat("yyyy/MM/dd");
//        try {
//            fechaAnterior = formatoDeFecha.parse("2012/10/26");
//        } catch (ParseException ex) {
//            Logger.getLogger(CancelacionRescisionBean.class.getName()).log(Level.SEVERE, null, ex);
//        }
        List<DetalleOrdenSuministro> listDetalleOrd = new ArrayList<>();
        String correo = "";
        String parts = "";
        boolean bandera = false;
        Integer cantidadEntregada = 0;
        Date fechaExtendida = util.sumaDiasHabiles(fechaAnterior, 5);
        listDetalleOrd = detalleOrdenSuministroService.detalleByFechaFinalPor5Dia(fechaExtendida, fechaAnterior);
        if (listDetalleOrd != null) {
            for (DetalleOrdenSuministro iterator : listDetalleOrd) {
                if (remisionesService.obtenerCantidadEntregadaPorOrden(iterator.getIdDetalleOrdenSuministro()) != null) {
                    cantidadEntregada = remisionesService.obtenerCantidadEntregadaPorOrden(iterator.getIdDetalleOrdenSuministro()).intValue();
                }
                Integer cantidadPendiente = iterator.getCantidadSuministrar() - cantidadEntregada;
                if (bandera) {
                    parts = parts + "<tr><td>" + iterator.getIdOrdenSuministro().getIdContrato().getNumeroContrato() + "</td>\n"
                            + "<td>" + iterator.getIdFalloProcedimientoRcb().getIdProcedimientoRcb().getIdRcbInsumos().getClaveInsumo() + "</td>\n"
                            + "<td>" + iterator.getIdFalloProcedimientoRcb().getIdProcedimientoRcb().getIdRcbInsumos().getDescripcionInsumo() + "</td>\n"
                            + "<td>" + iterator.getCantidadSuministrar() + "</td>\n"
                            + "<td>" + cantidadEntregada + "</td>\n"
                            + "<td>" + cantidadPendiente.toString() + "</td>\n"
                            + "<td>" + iterator.getIdFalloProcedimientoRcb().getIdProcedimientoRcb().getIdProcedimiento().getNumeroProcedimiento() + "</td>\n"
                            + "<td>" + iterator.getFechaEntregaInicial() + "</td>\n"
                            + "<td>" + iterator.getFechaEntregaFinal() + "</td></tr>\n";
                } else {
                    bandera = true;
                    parts = "<tr><td>" + iterator.getIdOrdenSuministro().getIdContrato().getNumeroContrato() + "</td>\n"
                            + "<td>" + iterator.getIdFalloProcedimientoRcb().getIdProcedimientoRcb().getIdRcbInsumos().getClaveInsumo() + "</td>\n"
                            + "<td>" + iterator.getIdFalloProcedimientoRcb().getIdProcedimientoRcb().getIdRcbInsumos().getDescripcionInsumo() + "</td>\n"
                            + "<td>" + iterator.getCantidadSuministrar() + "</td>\n"
                            + "<td>" + cantidadEntregada + "</td>\n"
                            + "<td>" + cantidadPendiente.toString() + "</td>\n"
                            + "<td>" + iterator.getIdFalloProcedimientoRcb().getIdProcedimientoRcb().getIdProcedimiento().getNumeroProcedimiento() + "</td>\n"
                            + "<td>" + iterator.getFechaEntregaInicial() + "</td>\n"
                            + "<td>" + iterator.getFechaEntregaFinal() + "</td></tr>\n";
                }
            }
            correo = "<!DOCTYPE html>\n"
                    + "<html>\n"
                    + "<body>\n"
                    + "<table border=\"1\">\n"
                    + "<tr>\n"
                    + "<th Align=\"center\">Contrato</th>\n"
                    + "<th Align=\"center\">Clave</th>\n"
                    + "<th Align=\"center\">Descripcion</th>\n"
                    + "<th Align=\"center\">Cantidad programada</th>\n"
                    + "<th Align=\"center\">Cantidad entregada</th>\n"
                    + "<th Align=\"center\">Cantidad pendiente</th>\n"
                    + "<th Align=\"center\">Procedimiento</th>\n"
                    + "<th Align=\"center\">Fecha incial</th>\n"
                    + "<th Align=\"center\">Fecha final</th>\n"
                    + "</tr>\n"
                    + parts
                    + "</table>\n"
                    + "</body>\n"
                    + "</html>";
        }
        return correo;
    }

}
