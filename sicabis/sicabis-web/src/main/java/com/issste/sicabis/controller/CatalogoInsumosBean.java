/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.issste.sicabis.controller;

import com.issste.sicabis.ejb.ln.InsumosService;
import com.issste.sicabis.ejb.ln.InsumosSiamService;
import com.issste.sicabis.ejb.modelo.Insumos;
import com.issste.sicabis.ejb.modelo.InsumosSiam;
import com.issste.sicabis.ejb.modelo.Usuarios;
import com.issste.sicabis.ejb.siam.ln.VwInsumosControlSICABISService;
import com.issste.sicabis.ejb.siam.ln.vwInsumosSICABISService;
import com.issste.sicabis.ejb.siam.modelo.VwInsumosControlSICABIS;
import com.issste.sicabis.ejb.siam.modelo.VwInsumosSICABIS;
import com.issste.sicabis.utils.Mensajes;
import com.issste.sicabis.utils.PlaneacionEstatusEnum;
import com.issste.sicabis.utils.Utilidades;
import java.io.Serializable;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import org.primefaces.context.RequestContext;
import org.primefaces.event.SelectEvent;

public class CatalogoInsumosBean implements Serializable {

    private static final long serialVersionUID = 1L;

    @EJB
    private vwInsumosSICABISService vwInsumosSICABISService;
    @EJB
    private VwInsumosControlSICABISService vwInsumosControlSICABISService;
    @EJB
    private InsumosSiamService insumosSiamService;
    @EJB
    private InsumosService insumoService;

    private Usuarios usuarios;
    private Utilidades util = new Utilidades();
    private List<Insumos> listInsumos;
    private List<Insumos> listInsumosFilter;
    private List<InsumosSiam> listaInsumosSIAM;
    private List<InsumosSiam> listaInsumosSIAMFilter;
    private boolean mensajeGuardar = true;
    private Mensajes mensaje = new Mensajes();
    private String claveSIAM;

    @PostConstruct
    public void init() {
        usuarios = (Usuarios) util.getSessionAtributte("usuario");
        listInsumos = insumoService.traeListaInsumos();
        listaInsumosSIAM = insumosSiamService.obtenerAll();
        if (listaInsumosSIAM == null) {
            listaInsumosSIAM = new ArrayList();
        }
    }

    public void abrirModalAgregarInsumo(String clave) {
        claveSIAM = clave;
        Map<String, Object> options = new HashMap<String, Object>();
        Map<String, List<String>> params = new HashMap<String, List<String>>();
        List<String> values = new ArrayList<String>();
        values.add(String.valueOf(clave));
        params.put("clave", values);
        options.put("resizable", false);
        options.put("draggable", false);
        options.put("modal", true);
        options.put("includeViewParams", true);
        options.put("width", "1020");
        options.put("height", "400");
        options.put("contentWidth", "1020");
        options.put("contentHeight", "400");
        System.out.println("entro a choose antes request");
        RequestContext.getCurrentInstance().openDialog("/vistas/rcb/agregarInsumo.xhtml", options, params);
    }

    public void cacharInsumoRegistrado(SelectEvent event) {
        //Boolean existeInsumo = false;
        boolean insumoRegistrado = (boolean) event.getObject();
        listInsumos.clear();
        listInsumos = insumoService.traeListaInsumos();
        listaInsumosSIAM.clear();
        listaInsumosSIAM = insumosSiamService.obtenerAll();
        if (insumoRegistrado) {
            mensaje.mensaje(mensaje.datos_guardados, "verde");
        }
    }

    public void cacharInsumoRegistradoSIAM(SelectEvent event) {
        //Boolean existeInsumo = false;
        boolean insumoRegistrado = (boolean) event.getObject();
        listInsumos.clear();
        listInsumos = insumoService.traeListaInsumos();
        listaInsumosSIAM.clear();
        listaInsumosSIAM = insumosSiamService.obtenerAll();
        if (insumoRegistrado) {
            mensaje.mensaje(mensaje.datos_guardados, "verde");
        }
        boolean band = insumosSiamService.borrarByClave(claveSIAM);
        if (band) {
            listaInsumosSIAM.clear();
            listaInsumosSIAM = insumosSiamService.obtenerAll();
        }

    }

    public void obtieneRegistros() {
        List<VwInsumosSICABIS> listaVwInsumosSicabis = null;
        List<VwInsumosControlSICABIS> listaVwInsumosControlSICABIS = null;
        List<InsumosSiam> listaInsumosSiam = new ArrayList();
        InsumosSiam is = null;
        boolean b1 = false;
        boolean b2 = false;
        boolean b3 = false;

        listaVwInsumosSicabis = vwInsumosSICABISService.obtenerVwInsumos();
        if (listaVwInsumosSicabis != null) {
            for (VwInsumosSICABIS vwis : listaVwInsumosSicabis) {
                is = new InsumosSiam();
                is.setClave(vwis.getClave());
                is.setDescripcion(vwis.getDescripcion());
                is.setClasificacion(vwis.getClasificacion());
                is.setIndicaciones(vwis.getIndicaciones());
                is.setPartidaPresupuestal(vwis.getPartida());
                is.setViaAdministracionDosis(vwis.getViaadmon());
                is.setTipo(vwis.getTipoInsumo());
                String group = vwis.getClave().substring(0, 3);
                //System.out.println("group--->" + group);
                if (group.equals("010")) {
                    is.setIdGrupo(PlaneacionEstatusEnum.ID_GRUPO_010.getValue());
                } else if (group.equals("020")) {
                    is.setIdGrupo(PlaneacionEstatusEnum.ID_GRUPO_020.getValue());
                } else if (group.equals("030")) {
                    is.setIdGrupo(PlaneacionEstatusEnum.ID_GRUPO_030.getValue());
                } else if (group.equals("040")) {
                    is.setIdGrupo(PlaneacionEstatusEnum.ID_GRUPO_040.getValue());
                } else if (group.equals("060")) {
                    is.setIdGrupo(PlaneacionEstatusEnum.ID_GRUPO_060.getValue());
                } else if (group.equals("070")) {
                    is.setIdGrupo(PlaneacionEstatusEnum.ID_GRUPO_070.getValue());
                } else if (group.equals("080")) {
                    is.setIdGrupo(PlaneacionEstatusEnum.ID_GRUPO_080.getValue());
                } else if (group.equals("100")) {
                    is.setIdGrupo(PlaneacionEstatusEnum.ID_GRUPO_100.getValue());
                } else if (group.equals("220")) {
                    is.setIdGrupo(PlaneacionEstatusEnum.ID_GRUPO_220.getValue());
                } else if (group.equals("370")) {
                    is.setIdGrupo(PlaneacionEstatusEnum.ID_GRUPO_370.getValue());
                } else if (group.equals("500")) {
                    is.setIdGrupo(PlaneacionEstatusEnum.ID_GRUPO_500.getValue());
                } else {
                    is.setIdGrupo(0);
                }

                listaVwInsumosControlSICABIS = vwInsumosControlSICABISService.obtenerByClave(vwis.getClave());
                if (listaVwInsumosControlSICABIS != null) {
                    b1 = false;
                    b2 = false;
                    b3 = false;
                    for (VwInsumosControlSICABIS vwics : listaVwInsumosControlSICABIS) {
                        if (vwics.getDescNivel().equals("PRIMER NIVEL")) {
                            b1 = true;
                        }
                        if (vwics.getDescNivel().equals("SEGUNDO NIVEL")) {
                            b2 = true;
                        }
                        if (vwics.getDescNivel().equals("TERCER NIVEL")) {
                            b3 = true;
                        }
                    }
                    if (b1 & b2 & b3) {
                        is.setIdNivel(PlaneacionEstatusEnum.ID_NIVEL_111.getValue());
                    } else if (b1 & b2 & !b3) {
                        is.setIdNivel(PlaneacionEstatusEnum.ID_NIVEL_110.getValue());
                    } else if (!b1 & b2 & b3) {
                        is.setIdNivel(PlaneacionEstatusEnum.ID_NIVEL_011.getValue());
                    } else if (b1 & !b2 & !b3) {
                        is.setIdNivel(PlaneacionEstatusEnum.ID_NIVEL_100.getValue());
                    } else if (!b1 & b2 & !b3) {
                        is.setIdNivel(PlaneacionEstatusEnum.ID_NIVEL_010.getValue());
                    } else if (!b1 & !b2 & b3) {
                        is.setIdNivel(PlaneacionEstatusEnum.ID_NIVEL_001.getValue());
                    } else if (!b1 & !b2 & !b3) {
                        is.setIdNivel(PlaneacionEstatusEnum.ID_NIVEL_000.getValue());
                    }
                } else {
                    is.setIdNivel(0);
                }
                listaInsumosSiam.add(is);
            }
        }
        Insumos insumos = null;
        for (InsumosSiam isAux : listaInsumosSiam) {
            insumos = insumoService.obtieneByClave(isAux.getClave().trim());
            if (insumos == null) {
                String saux = "";
                try {
                    saux = new String(isAux.getDescripcion().getBytes("UTF-8"), "ISO-8859-1");
                } catch (UnsupportedEncodingException ex) {
                    Logger.getLogger(CatalogoInsumosBean.class.getName()).log(Level.SEVERE, null, ex);
                }
                isAux.setDescripcion(saux);
                boolean band = insumosSiamService.guardarActualizar(isAux);
                listaInsumosSIAM.add(isAux);
            } else {
                insumos.setDescripcion(isAux.getDescripcion());
                insumoService.actualiza(insumos);
            }
        }
    }

    public void agregaInsumo(InsumosSiam is) {

    }

    public List<Insumos> getListInsumos() {
        return listInsumos;
    }

    public void setListInsumos(List<Insumos> listInsumos) {
        this.listInsumos = listInsumos;
    }

    public List<Insumos> getListInsumosFilter() {
        return listInsumosFilter;
    }

    public void setListInsumosFilter(List<Insumos> listInsumosFilter) {
        this.listInsumosFilter = listInsumosFilter;
    }

    public List<InsumosSiam> getListaInsumosSIAM() {
        return listaInsumosSIAM;
    }

    public void setListaInsumosSIAM(List<InsumosSiam> listaInsumosSIAM) {
        this.listaInsumosSIAM = listaInsumosSIAM;
    }

    public List<InsumosSiam> getListaInsumosSIAMFilter() {
        return listaInsumosSIAMFilter;
    }

    public void setListaInsumosSIAMFilter(List<InsumosSiam> listaInsumosSIAMFilter) {
        this.listaInsumosSIAMFilter = listaInsumosSIAMFilter;
    }

    public boolean isMensajeGuardar() {
        return mensajeGuardar;
    }

    public void setMensajeGuardar(boolean mensajeGuardar) {
        this.mensajeGuardar = mensajeGuardar;
    }

    public Mensajes getMensaje() {
        return mensaje;
    }

    public void setMensaje(Mensajes mensaje) {
        this.mensaje = mensaje;
    }

}
