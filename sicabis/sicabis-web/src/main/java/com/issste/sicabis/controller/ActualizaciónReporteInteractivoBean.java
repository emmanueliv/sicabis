/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.issste.sicabis.controller;

import com.issste.sicabis.ejb.ln.AlertasDpnService;
import com.issste.sicabis.ejb.modelo.AlertasDpn;
import com.issste.sicabis.ejb.modelo.Usuarios;
import com.issste.sicabis.utils.Mensajes;
import com.issste.sicabis.utils.Utilidades;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Locale;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;

/**
 *
 * @author 84JBBG2
 */
public class ActualizaciónReporteInteractivoBean {

    @EJB
    private AlertasDpnService alertasDpnService;

    private Usuarios usuario;
    private AlertasDpn alertasDpn;

    private List<AlertasDpn> listAlertasDpn;
    private List<AlertasDpn> listAlertasDpnConsulta;

    private Integer mes;
    private Integer diaActual;
    private Integer mesActual;
    private Integer anioActual;

    private Utilidades util = new Utilidades();
    private Mensajes mensaje = new Mensajes();

    public ActualizaciónReporteInteractivoBean() {
        listAlertasDpn = new ArrayList<>();
    }

    @PostConstruct
    public void init() {
        usuario = (Usuarios) util.getSessionAtributte("usuario");
        Calendar c = Calendar.getInstance(Locale.ENGLISH);
        diaActual = c.get(Calendar.DAY_OF_MONTH);
        mesActual = c.get(Calendar.MONTH) + 1;
        anioActual = c.get(Calendar.YEAR);
        mes = mesActual;
        listAlertasDpn = alertasDpnService.getByAnioMes(anioActual, null);
    }
    
    public void cambiaValores() {
        System.out.println("e");
    }
    
    public void consultarAlertas() {
        listAlertasDpnConsulta = alertasDpnService.getAllByAnioMes(anioActual, mes);
        if(listAlertasDpnConsulta == null){
            mensaje.mensaje("No existen registros para el periodo seleccionado", "amarillo");
        }
    }

    public Integer getMes() {
        return mes;
    }

    public void setMes(Integer mes) {
        this.mes = mes;
    }

    public List<AlertasDpn> getListAlertasDpn() {
        return listAlertasDpn;
    }

    public void setListAlertasDpn(List<AlertasDpn> listAlertasDpn) {
        this.listAlertasDpn = listAlertasDpn;
    }

    public Mensajes getMensaje() {
        return mensaje;
    }

    public void setMensaje(Mensajes mensaje) {
        this.mensaje = mensaje;
    }

    public List<AlertasDpn> getListAlertasDpnConsulta() {
        return listAlertasDpnConsulta;
    }

    public void setListAlertasDpnConsulta(List<AlertasDpn> listAlertasDpnConsulta) {
        this.listAlertasDpnConsulta = listAlertasDpnConsulta;
    }

}
