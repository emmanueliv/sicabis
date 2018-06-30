/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.issste.sicabis.controller;

import com.issste.sicabis.ejb.ln.AreasService;
import com.issste.sicabis.ejb.ln.BitacoraTareaSerice;
import com.issste.sicabis.ejb.ln.DelegacionesService;
import com.issste.sicabis.ejb.ln.SolicitudesService;
import com.issste.sicabis.ejb.ln.TipoSolicitudService;
import com.issste.sicabis.ejb.modelo.Area;
import com.issste.sicabis.ejb.modelo.BitacoraTareaEstatus;
import com.issste.sicabis.ejb.modelo.Delegaciones;
import com.issste.sicabis.ejb.modelo.Estatus;
import com.issste.sicabis.ejb.modelo.Solicitudes;
import com.issste.sicabis.ejb.modelo.TipoSolicitud;
import com.issste.sicabis.ejb.modelo.UnidadesMedicas;
import com.issste.sicabis.ejb.modelo.Usuarios;
import com.issste.sicabis.utils.BitacoraUtil;
import com.issste.sicabis.utils.Mensajes;
import com.issste.sicabis.utils.PlaneacionEstatusEnum;
import com.issste.sicabis.utils.Utilidades;
import java.io.Serializable;
import java.text.DateFormatSymbols;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Objects;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpServletRequest;
import org.primefaces.context.RequestContext;

/**
 *
 * @author Toshiba Manolo
 */
@ManagedBean(name = "solicitudUnidadesBeanNuevo")
@ViewScoped
public class SolicitudUnidadesBeanNuevo implements Serializable {

    private static final long serialVersionUID = 1L;

    private Solicitudes solicitud = new Solicitudes();
    private Delegaciones delegacionSeleccionada;
    private List<Delegaciones> listaDelegaciones;
    private List<Area> listaAreas;

    @EJB
    private DelegacionesService delegacionesService;
    @EJB
    private AreasService areasService;
    @EJB
    private SolicitudesService solicitudesServices;
    @EJB
    private TipoSolicitudService tipoSolicitudService;

    @EJB
    BitacoraTareaSerice bitacoraService;
    BitacoraTareaEstatus bitacora = new BitacoraTareaEstatus();
    BitacoraUtil bitacoraUtil = new BitacoraUtil();

    private Mensajes mensaje = new Mensajes();
    private Usuarios usuarios = new Usuarios();
    private Utilidades util = new Utilidades();
    private Integer idSolicitudCreada;
    private Integer idTipoSolicitud;
    private Integer idArea;

    @PostConstruct
    public void init() {
        System.out.println("Entro a SolicitudUnidadesBeanNuevo");
        listaAreas = new ArrayList<>();
        bitacora = new BitacoraTareaEstatus();
        usuarios = (Usuarios) util.getSessionAtributte("usuario");
        HttpServletRequest request = (HttpServletRequest) FacesContext.getCurrentInstance().getExternalContext()
                .getRequest();
        idTipoSolicitud = Integer.parseInt(request.getParameter("idTipoSolicitud"));
        System.out.println("idTipoSolicitud;" + idTipoSolicitud);
        listaDelegaciones = delegacionesService.obtenerDelegaciones();
        if (usuarios.getIdArea() == null) {
            idArea = 0;
        } else {
            idArea = usuarios.getIdArea().getIdArea();
        }
        this.cargarAreas();
        for (Iterator<Area> iterator = listaAreas.iterator(); iterator.hasNext();) {
            Area next = iterator.next();
            if (next.getIdArea() == PlaneacionEstatusEnum.ID_AREA_INFRAESTRUCTURA.getValue()
                    || next.getIdArea() == PlaneacionEstatusEnum.ID_AREA_PLANEACION.getValue()) {
                iterator.remove();
            }
        }
        delegacionSeleccionada = new Delegaciones();
        solicitud = new Solicitudes();
        solicitud.setIdUnidadesMedicas(new UnidadesMedicas());
        java.util.Date date = new Date();
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        int month = cal.get(Calendar.MONTH);
        int year = cal.get(Calendar.YEAR);
        solicitud.setPeriodoSolicitar(getMonth(month) + "-" + year);
        cal.add(Calendar.MONTH, 1);
        solicitud.setFechaSolicitud(cal.getTime());
        List<TipoSolicitud> listTipoSol = tipoSolicitudService.obtenerTipoSolPorIdTipoSolicitud(idTipoSolicitud);
        TipoSolicitud tipoSol = listTipoSol.get(0);
        solicitud.setIdTipoSolicitud(tipoSol);
        solicitud.setIdArea(new Area());
    }

    public String getMonth(int month) {
        String resultado = String.valueOf(month + 1);
        //return new DateFormatSymbols().getMonths()[month + 1];

        return resultado;
    }

    public void cargarAreas() {
        List<Area> areasListAux = areasService.obtenerAreas();
        for (Area ar : areasListAux) {
            if (ar.getIdArea() != 10 && ar.getIdPadre() != null) {
                if (idArea == 16 || idArea == 17) {
                    if (ar.getIdArea() >= 12 && ar.getIdArea() <= 14) {
                        listaAreas.add(ar);
                    }
                } else if (Objects.equals(ar.getIdArea(), idArea)) {
                    if (ar.getIdArea() != 11) {
                        listaAreas.add(ar);
                        idArea = ar.getIdArea();
                    }
                }
            }
            if (usuarios.getIdUsuario() == 1) {
                listaAreas.add(ar);
            }
        }
    }

    public void guardar() {
        System.out.println("entro a guardar solicitud");
        solicitud.setActivo(1);
        solicitud.setIdEstatus(new Estatus(151));
        solicitud.setFechaAlta(new Date());
        Calendar cal = Calendar.getInstance();
        cal.setTime(solicitud.getFechaAlta());
        int day = cal.get(Calendar.DAY_OF_MONTH);
        int month = cal.get(Calendar.MONTH);
        int year = cal.get(Calendar.YEAR);
        int minute = cal.get(Calendar.MINUTE);
        int second = cal.get(Calendar.SECOND);
        solicitud.setNumeroSolicitud(solicitud.getIdUnidadesMedicas().getIdUnidadesMedicas()
                + String.valueOf(day) + String.valueOf(month + 1)
                + String.valueOf(year) + String.valueOf(minute)
                + String.valueOf(second)
                + solicitud.getIdTipoSolicitud().getIdTipoSolicitud());

        System.out.println("getNumeroSolicitud: " + solicitud.getNumeroSolicitud());
        System.out.println("getPeriodoSolicitar: " + solicitud.getPeriodoSolicitar());
        System.out.println("getIdArea: " + solicitud.getIdArea().getIdArea());
        System.out.println("getIdEstatus: " + solicitud.getIdEstatus().getIdEstatus());
        System.out.println("getIdSolicitud: " + solicitud.getIdSolicitud());
        System.out.println("getIdTipoSolicitud: " + solicitud.getIdTipoSolicitud().getIdTipoSolicitud());
        System.out.println("getIdUnidadesMedicas: " + solicitud.getIdUnidadesMedicas().getIdUnidadesMedicas());
        System.out.println("getFechaAlta: " + solicitud.getFechaAlta());
        System.out.println("getFechaSolicitud: " + solicitud.getFechaSolicitud());
        List<Solicitudes> validaSolicitud = solicitudesServices.buscaSolicitudPorNumeroSolicitud(solicitud.getNumeroSolicitud());
        if (validaSolicitud != null) {
            if (validaSolicitud.size() == 0) {
                Solicitudes tmpSolicitud = solicitudesServices.save(solicitud);
                idSolicitudCreada = tmpSolicitud.getIdSolicitud();
                bitacora.setFecha(new Date());
                bitacora.setIdEstatus(tmpSolicitud.getIdEstatus().getIdEstatus());
                bitacora.setDescripcion("Se genero la solicitud" + tmpSolicitud.getNumeroSolicitud());
                bitacora.setIdUsuarios(usuarios.getIdUsuario());
                bitacora.setIdTareaId(tmpSolicitud.getIdSolicitud());
                bitacora.setIdModulos(PlaneacionEstatusEnum.ID_MODULO_SOLICITUDES.getValue());
                bitacoraService.guardarEnBitacora(bitacora);
                System.out.println("idSolicitudCreada:" + idSolicitudCreada);
                mensaje.mensaje(mensaje.datos_guardados + ",el número de Soicitud generado es: " + tmpSolicitud.getNumeroSolicitud(), "verde");
                RequestContext.getCurrentInstance().execute("PF('wdContCaptura').show()");
            } else {
                mensaje.mensaje("La solicitud ya fue registrada 0", "amarillo");
            }
        } else {
            mensaje.mensaje("La solicitud ya fue registrada", "amarillo");
        }

    }

    public void cambiaDelegacion() {
        for (Delegaciones delegacion : listaDelegaciones) {
            if (delegacion.getIdDelegacion() == delegacionSeleccionada.getIdDelegacion()) {
                System.out.println("encontro la delegacion");
                delegacionSeleccionada = delegacionesService.obtenerDelegacionporId(delegacionSeleccionada.getIdDelegacion());
                break;
            }
        }
    }

    public String irSolicitudDetalle(Integer idSolicitud) {
        util.setContextAtributte("idSolicitud", idSolicitud);
        return "solicitudUnidadesDetalle.xhtml?faces-redirect=true";

    }

    public void limpiarFrmNuevo() {
        System.out.println("Entro a limpiar");
        RequestContext.getCurrentInstance().reset("formSolicitud");
        init();

    }

    public Solicitudes getSolicitud() {
        return solicitud;
    }

    public void setSolicitud(Solicitudes solicitud) {
        this.solicitud = solicitud;
    }

    public List<Delegaciones> getListaDelegaciones() {
        return listaDelegaciones;
    }

    public void setListaDelegaciones(List<Delegaciones> listaDelegaciones) {
        this.listaDelegaciones = listaDelegaciones;
    }

    public List<Area> getListaAreas() {
        return listaAreas;
    }

    public void setListaAreas(List<Area> listaAreas) {
        this.listaAreas = listaAreas;
    }

    public Delegaciones getDelegacionSeleccionada() {
        return delegacionSeleccionada;
    }

    public void setDelegacionSeleccionada(Delegaciones delegacionSeleccionada) {
        this.delegacionSeleccionada = delegacionSeleccionada;
    }

    public Integer getIdSolicitudCreada() {
        return idSolicitudCreada;
    }

    public void setIdSolicitudCreada(Integer idSolicitudCreada) {
        this.idSolicitudCreada = idSolicitudCreada;
    }

    public Integer getIdTipoSolicitud() {
        return idTipoSolicitud;
    }

    public void setIdTipoSolicitud(Integer idTipoSolicitud) {
        this.idTipoSolicitud = idTipoSolicitud;
    }

}
