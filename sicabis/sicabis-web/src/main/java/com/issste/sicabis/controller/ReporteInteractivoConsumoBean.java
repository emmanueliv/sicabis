/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.issste.sicabis.controller;

import com.issste.sicabis.ejb.ln.AlertasCorreoService;
import com.issste.sicabis.ejb.ln.AlertasDpnService;
import com.issste.sicabis.ejb.ln.AlertasEnvioService;
import com.issste.sicabis.ejb.ln.ConfiguraDpnService;
import com.issste.sicabis.ejb.ln.ConsumoDiarioSiamService;
import com.issste.sicabis.ejb.ln.DelegacionesService;
import com.issste.sicabis.ejb.ln.DpnInsumosService;
import com.issste.sicabis.ejb.ln.EstatusService;
import com.issste.sicabis.ejb.ln.UnidadMedicaService;
import com.issste.sicabis.ejb.ln.UsuariosService;
import com.issste.sicabis.ejb.modelo.AlertasCorreo;
import com.issste.sicabis.ejb.modelo.AlertasDpn;
import com.issste.sicabis.ejb.modelo.AlertasEnvio;
import com.issste.sicabis.ejb.modelo.ConfiguraDpn;
import com.issste.sicabis.ejb.modelo.Delegaciones;
import com.issste.sicabis.ejb.modelo.DpnInsumos;
import com.issste.sicabis.ejb.modelo.Estatus;
import com.issste.sicabis.ejb.modelo.Perfiles;
import com.issste.sicabis.ejb.modelo.UnidadesMedicas;
import com.issste.sicabis.ejb.modelo.UsuarioPerfil;
import com.issste.sicabis.ejb.modelo.Usuarios;
import com.issste.sicabis.ejb.modelo.UsuariosTipoUsuarios;
import com.issste.sicabis.ejb.sheduler.ShedulerJobDAOImplement;
import com.issste.sicabis.ejb.utils.SendMail;
import com.issste.sicabis.utils.Mensajes;
import com.issste.sicabis.utils.Utilidades;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.context.FacesContext;
import javax.faces.event.ValueChangeEvent;
import org.primefaces.component.datatable.DataTable;
import org.primefaces.context.RequestContext;
import org.primefaces.event.CellEditEvent;
import org.primefaces.event.RowEditEvent;


public class ReporteInteractivoConsumoBean {

    @EJB
    private EstatusService estatusService;

    @EJB
    private AlertasDpnService alertasDpnService;

    @EJB
    private DpnInsumosService dpnInsumosService;

    @EJB
    private ConsumoDiarioSiamService consumoDiarioSiamService;

    @EJB
    private ConfiguraDpnService configuraDpnService;

    @EJB
    private DelegacionesService delegacionesService;

    @EJB
    private AlertasEnvioService alertasEnvioService;

    @EJB
    private AlertasCorreoService alertasCorreoService;

    @EJB
    private UnidadMedicaService unidadMedicaService;

    @EJB
    private UsuariosService usuariosService;

    //Objectos
    private Usuarios usuario;
    private Perfiles perfiles;
    private Utilidades util = new Utilidades();
    private Mensajes mensaje = new Mensajes();
    private AlertasDpn alertasDpn;

    private List<UnidadesMedicas> listaUnidadesMedicas;
    private List<AlertasEnvio> listAlertasEnvio;
    private List<AlertasEnvio> listAlertasEnvioFilter;
    private List<Delegaciones> listDelegacion;
    private List<Estatus> listEstatus;

    //Variables
    private String clave;
    private String usuarioAD;
    private Integer tipoUsuario;
    private Integer um;
    private Integer delegacion;
    private boolean bunidadmed;
    private Integer diaActual;
    private Integer mesActual;
    private Integer anioActual;
    private List<AlertasDpn> listAlertasDpn;
    private Integer mes;
    private boolean bactivo;
    private Integer opciones;

    public ReporteInteractivoConsumoBean() {
        usuario = new Usuarios();
        listaUnidadesMedicas = new ArrayList<>();
        
        listAlertasEnvioFilter = new ArrayList<>();
        listAlertasDpn = new ArrayList<>();
        listEstatus = new ArrayList<>();
        alertasDpn = new AlertasDpn();
    }

    @PostConstruct
    public void init() {
        clave = "";
        um = -1;
        delegacion = -1;
        opciones = 0;
        usuario = (Usuarios) util.getSessionAtributte("usuario");
        perfiles = (Perfiles) util.getSessionAtributte("perfil");
        if (perfiles.getAdministrador() != 1) {
            if (usuario.getUsuariosTipoUsuariosList().isEmpty()) {
                tipoUsuario = 0;
            } else {
                tipoUsuario = usuario.getUsuariosTipoUsuariosList().get(0).getIdTipoUsuario().getIdTipoUsuario();
            }
        } else {
            tipoUsuario = 0;
            bactivo = true;
        }
        listDelegacion = delegacionesService.obtenerDelegaciones();
        listaUnidadesMedicas = unidadMedicaService.unidadMedica();

        List<ConfiguraDpn> cd = configuraDpnService.getAllByActivo(1);
        Integer diasHabiles = 0;
        if (cd != null) {
            diasHabiles = cd.get(0).getNumDias();
        }
        SimpleDateFormat format = new SimpleDateFormat("yyyy/MM/dd");
        Calendar c = Calendar.getInstance(Locale.ENGLISH);
        diaActual = c.get(Calendar.DAY_OF_MONTH);
        mesActual = c.get(Calendar.MONTH) + 1;
        anioActual = c.get(Calendar.YEAR);
        mes = mesActual;
        listAlertasDpn = alertasDpnService.getByAnioMes(anioActual, null);
        um = usuario.getIdUnidadMedica() != null ? usuario.getIdUnidadMedica().getIdUnidadesMedicas() : -1;
        delegacion = usuario.getIdDelegacion() != null ? usuario.getIdDelegacion().getIdDelegacion() : -1;
        
        if (tipoUsuario == 6) {
            bactivo = true;
            if (diaActual > diasHabiles) {
                opciones = 1;
                bactivo = false;
            } else {
                //listAlertasEnvio = alertasEnvioService.getByIdAlertaDpnIdDelegacion(delegacion, anioActual, mes);
                if (listAlertasEnvio == null || listAlertasEnvio.isEmpty()) {
                    opciones = 2;
                } else {
                    this.validaEstatus();
                }
            }
        } else {
//            listAlertasEnvio = alertasEnvioService.getByIdAlertaDpnIdDelegacion(null, anioActual, mes);
//            bactivo = false;
        }
        listEstatus = estatusService.getEstatusByTarea(23);
    }

    public void onload() {
        if (opciones == 1) {
            mensaje.mensaje("Estas fuera del periodo, deberás ingresar a partir del día 1° del siguiente mes", clave);
        } else if (opciones == 2) {
            mensaje.mensaje("No existen registros para el periodo y/o la delegación", "amarillo");
        }
    }

    public void editaCelda(RowEditEvent event) {
        System.out.println("entre en editar-------------------->");
        try {
            DataTable s = (DataTable) event.getSource();
            AlertasEnvio ae = (AlertasEnvio) s.getRowData();
            this.actualizaCelda(ae);
        } catch (Exception e) {
            System.out.println("falle");
            //this.consultarAlertas();
        }
    }
    
    public void cambiaCelda(CellEditEvent e){
        System.out.println("entre en editar-------------------->");
        try {
            DataTable s = (DataTable) e.getSource();
            AlertasEnvio ae = (AlertasEnvio) s.getRowData();
            this.actualizaCelda(ae);
        } catch (Exception ex) {
            System.out.println("falle");
            //this.consultarAlertas();
        }
    }

    public void actualizaCelda(AlertasEnvio ae) {
        System.out.println("entre cambia valor");
        Estatus e = null;
        if (ae.getActivo() == 1) {
            if (ae.getDpnSugeridaUmu() != null) {
                e = estatusService.getRemisionEstatus(233);
                ae.setIdEstatus(e);
            } else {
                e = estatusService.getRemisionEstatus(231);
                ae.setIdEstatus(e);
            }
        } else {
            e = estatusService.getRemisionEstatus(232);
            ae.setIdEstatus(e);
            ae.setDpnSugeridaUmu(null);
        }
        System.out.println("estatus--->" + e.getNombre());
    }

    public void guardaAlerta(int opcion) {
        Estatus e = null;
        if (opcion == 1) {
            e = estatusService.getRemisionEstatus(222);//actualizada
        } else if (opcion == 2) {
            e = estatusService.getRemisionEstatus(224);//enviada
            DpnInsumos di = null;
            for (AlertasEnvio ae : listAlertasEnvio) {
                di = ae.getIdDpnInsumo();
                if (ae.getActivo() == 1) {
                    di.setDpnSugerida(ae.getDpnSugeridaUmu());
                } else {
                    di.setDpnSugerida(-1);
                }
                dpnInsumosService.guardaActualiza(di);
            }
        }
        alertasDpn.setActivo(1);
        alertasDpn.setIdEstatus(e);
        alertasDpn.setIdUsuario(usuario);
        alertasDpn.setAlertasEnvioList(listAlertasEnvio);
        if (alertasDpnService.guardar(alertasDpn)) {
            mensaje.mensaje(mensaje.datos_guardados, "verde");
        }
    }

    public void cambiaValores() {
        System.out.println("e");
    }
    
    public void consultarAlertas() {
        boolean band = true;
        if (mes == -1) {
            mensaje.mensaje("Debes seleccionar un periodo.", "amarillo");
            band = false;
        }
        if (delegacion == -1) {
            mensaje.mensaje("Debes seleccionar una delegación.", "amarillo");
            band = false;
        } else {
            if (delegacion == 80) {
                mensaje.mensaje("Llenar el campo unidad medica.", "amarillo");
            } else {
                //listAlertasEnvio = alertasEnvioService.getByIdAlertaDpnIdDelegacion(delegacion, anioActual, mes);
            }
        }
        if (listAlertasEnvio == null || listAlertasEnvio.isEmpty()) {
            if (band) {
                mensaje.mensaje("No existen registros que concuerden con los parametros seleccionados.", "amarillo");
            }
        } else {
            this.validaEstatus();
        }
    }

    public void validaEstatus() {
        alertasDpn = listAlertasEnvio.get(0).getIdAlertasDpn();
        if (alertasDpn.getIdEstatus().getIdEstatus() == 224) {
            bactivo = false;
        } else {
            bactivo = true;
        }
    }

    public void cambiaDelegacion() {
        if (delegacion == 80) {
            listaUnidadesMedicas = unidadMedicaService.getByHospitalRegional(1);
            bunidadmed = true;
        } else {
            bunidadmed = false;
        }
        //this.consultarAlertas();
    }

    public void ejecutaAlertaDPN() {
        AlertasEnvio ae = null;
        List<ConfiguraDpn> cd = configuraDpnService.getAllByActivo(1);
        Integer diasHabiles = 0;
        if (cd != null) {
            diasHabiles = cd.get(0).getNumDias();
        }
        SimpleDateFormat format = new SimpleDateFormat("yyyy/MM/dd");
        Calendar c = Calendar.getInstance(Locale.ENGLISH);
        Integer diaActual = c.get(Calendar.DAY_OF_MONTH);
        List<DpnInsumos> listDpnInsumo = null;
        try {
            Date fechaActualizada = util.sumarRestarDiasFecha(format.parse("" + c.get(Calendar.YEAR) + "/ " + c.get(Calendar.MONTH) + "/01"), diasHabiles);
            List<UsuariosTipoUsuarios> listUsuarios = new ArrayList<>();
            if (true) {
                listUsuarios = usuariosService.getUsuariosByTipoUsuario(6);
                for (UsuariosTipoUsuarios iterator : listUsuarios) {
                    listDpnInsumo = new ArrayList<>();
                    int unidadMedica = iterator.getIdUsuario().getIdUnidadMedica() != null ? iterator.getIdUsuario().getIdUnidadMedica().getIdUnidadesMedicas() : 0;
                    int delegacion = iterator.getIdUsuario().getIdDelegacion() != null ? iterator.getIdUsuario().getIdDelegacion().getIdDelegacion() : 0;
                    listDpnInsumo = dpnInsumosService.getByUnidadMedicaOrDelegacion(unidadMedica, delegacion);
                    if (listDpnInsumo != null) {
                        for (DpnInsumos dpn : listDpnInsumo) {
                            ae = new AlertasEnvio();
                            ae.setActivo(1);
//                            ae.setDpnSugerida(0);
//                            ae.setAnio(c.get(Calendar.YEAR));
//                            ae.setClaveInsumo(dpn.getClaveInsumo());
//                            ae.setClaveUnidad(dpn.getClaveUnidad());
//                            if (dpn.getPiezasDpn() != null) {
//                                ae.setDpn(new BigDecimal(dpn.getPiezasDpn()));
//                            } else {
//                                ae.setDpn(BigDecimal.ONE);
//                            }
//                            ae.setFechaAlta(new Date());
//                            ae.setExistencia(new BigDecimal(BigInteger.ZERO));
//                            ae.setConsumo(new BigDecimal(consumoDiarioSiamService.sumaConsumo(ae.getClaveInsumo(), ae.getClaveUnidad(), new Date(), new Date())));
//                            ae.setMes(c.get(Calendar.MONTH));
                            alertasEnvioService.guardar(ae);

                        }
                        String url = "";
                        if (cd != null) {
                            if (cd.get(0).getUrlLink() != null) {
                                url = cd.get(0).getUrlLink();
                            }
                        }
                        String mensaje = "Buen dÃ­a\n"
                                + "Se envÃ­a usuario para segurencia DPN del periodo en curso.\n"
                                + "Usuario: " + iterator.getIdUsuario().getUsuario() + "\n"
                                + "ContraseÃ±a: SICABIS\n"
                                + "URL: " + url + "";
                        SendMail.writeMail(iterator.getIdUsuario(), "DPN sugerida", mensaje);
                    }
                }
            } else if (diaActual == fechaActualizada.getDate()) {
                alertasEnvioService.updateAllByActivo();
            }
        } catch (ParseException ex) {
            Logger.getLogger(ShedulerJobDAOImplement.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    //Getters y Setters
    public String getClave() {
        return clave;
    }

    public void setClave(String clave) {
        this.clave = clave;
    }

    public String getUsuarioAD() {
        return usuarioAD;
    }

    public void setUsuarioAD(String usuarioAD) {
        this.usuarioAD = usuarioAD;
    }

    public List<UnidadesMedicas> getListaUnidadesMedicas() {
        return listaUnidadesMedicas;
    }

    public void setListaUnidadesMedicas(List<UnidadesMedicas> listaUnidadesMedicas) {
        this.listaUnidadesMedicas = listaUnidadesMedicas;
    }

    public List<AlertasEnvio> getListAlertasEnvio() {
        return listAlertasEnvio;
    }

    public void setListAlertasEnvio(List<AlertasEnvio> listAlertasEnvio) {
        this.listAlertasEnvio = listAlertasEnvio;
    }

    public Usuarios getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuarios usuario) {
        this.usuario = usuario;
    }

    public Integer getUm() {
        return um;
    }

    public void setUm(Integer um) {
        this.um = um;
    }

    public Integer getDelegacion() {
        return delegacion;
    }

    public void setDelegacion(Integer delegacion) {
        this.delegacion = delegacion;
    }

    public List<Delegaciones> getListDelegacion() {
        return listDelegacion;
    }

    public void setListDelegacion(List<Delegaciones> listDelegacion) {
        this.listDelegacion = listDelegacion;
    }

    public boolean isBunidadmed() {
        return bunidadmed;
    }

    public void setBunidadmed(boolean bunidadmed) {
        this.bunidadmed = bunidadmed;
    }

    public Integer getTipoUsuario() {
        return tipoUsuario;
    }

    public void setTipoUsuario(Integer tipoUsuario) {
        this.tipoUsuario = tipoUsuario;
    }

    public List<AlertasDpn> getListAlertasDpn() {
        return listAlertasDpn;
    }

    public void setListAlertasDpn(List<AlertasDpn> listAlertasDpn) {
        this.listAlertasDpn = listAlertasDpn;
    }

    public Integer getMes() {
        return mes;
    }

    public void setMes(Integer mes) {
        this.mes = mes;
    }

    public List<Estatus> getListEstatus() {
        return listEstatus;
    }

    public void setListEstatus(List<Estatus> listEstatus) {
        this.listEstatus = listEstatus;
    }

    public boolean isBactivo() {
        return bactivo;
    }

    public void setBactivo(boolean bactivo) {
        this.bactivo = bactivo;
    }

    public List<AlertasEnvio> getListAlertasEnvioFilter() {
        return listAlertasEnvioFilter;
    }

    public void setListAlertasEnvioFilter(List<AlertasEnvio> listAlertasEnvioFilter) {
        this.listAlertasEnvioFilter = listAlertasEnvioFilter;
    }

}
