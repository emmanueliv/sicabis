/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.issste.sicabis.controller;

import com.issste.sicabis.ejb.DTO.PacientesInsumosViewDto;
import com.issste.sicabis.ejb.ln.AreasService;
import com.issste.sicabis.ejb.ln.BitacoraTareaSerice;
import com.issste.sicabis.ejb.ln.DelegacionesService;
import com.issste.sicabis.ejb.ln.PeriodoAreaService;
import com.issste.sicabis.ejb.ln.SolicitudesInsumosPacientesService;
import com.issste.sicabis.ejb.ln.SolicitudesService;
import com.issste.sicabis.ejb.ln.TipoSolicitudService;
import com.issste.sicabis.ejb.modelo.Area;
import com.issste.sicabis.ejb.modelo.BitacoraTareaEstatus;
import com.issste.sicabis.ejb.modelo.Delegaciones;
import com.issste.sicabis.ejb.modelo.Estatus;
import com.issste.sicabis.ejb.modelo.PeriodoArea;
import com.issste.sicabis.ejb.modelo.Solicitudes;
import com.issste.sicabis.ejb.modelo.SolicitudesInsumosPacientes;
import com.issste.sicabis.ejb.modelo.TipoSolicitud;
import com.issste.sicabis.ejb.modelo.Usuarios;
import com.issste.sicabis.utils.BitacoraUtil;
import com.issste.sicabis.utils.Mensajes;
import com.issste.sicabis.utils.PlaneacionEstatusEnum;
import com.issste.sicabis.utils.Utilidades;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import org.primefaces.context.RequestContext;
import org.primefaces.event.CloseEvent;
import org.primefaces.event.SelectEvent;

/**
 *
 * @author Toshiba Manolo
 */
@ManagedBean(name = "solicitudesUnidadesBeanDetalle")
@ViewScoped
public class SolicitudesUnidadesBeanDetalle implements Serializable {

    private static final long serialVersionUID = 1L;

    private Solicitudes solicitud = new Solicitudes();
    private Delegaciones delegacionSeleccionada;
    private List<Delegaciones> listaDelegaciones;
    private List<Area> listaAreas;
    private List<PacientesInsumosViewDto> listaPacientesInsumosDto;
    private List<PacientesInsumosViewDto> listaPacientesInsumosDtoFilter;
    private List<SolicitudesInsumosPacientes> listaInsumosDto;
    private List<SolicitudesInsumosPacientes> listaInsumosDtoFilter;
    private List<TipoSolicitud> listaTipoSolicitud;

    @EJB
    private DelegacionesService delegacionesService;
    @EJB
    private AreasService areasService;
    @EJB
    private SolicitudesService solicitudesServices;
    @EJB
    private SolicitudesInsumosPacientesService solicitudesInsumosPacientesService;
    @EJB
    private PeriodoAreaService periodoAreaService;
    @EJB
    private TipoSolicitudService tipoSolictudService;

    @EJB
    BitacoraTareaSerice bitacoraService;
    BitacoraTareaEstatus bitacora = new BitacoraTareaEstatus();
    BitacoraUtil bitacoraUtil = new BitacoraUtil();

    private Boolean desHabilitarBtnCargarPeriodoAnterior;
    private Boolean desHabilitarBtnAgregarPaciente;
    private Boolean desHabilitarBtnEliminarTodo;
    private Boolean desHabilitarBtnModificar;
    private Boolean desHabilitarBtnEliminar;
    private Boolean desHabilitarSelDelegacion;
    private Boolean desHabilitarSelUnidadMedica;
    private Boolean desHabilitarSelidTipoSolicitud;
    private Boolean desHabilitarSelArea;
    private Boolean desHabilitarInpPeriodo;

    private Boolean desHabilitarBtnCargarPeriodoAnteriorInsumo;
    private Boolean desHabilitarBtnAgregarPacienteInsumo;
    private Boolean desHabilitarBtnEliminarTodoInsumo;
    private Boolean desHabilitarBtnModificarInsumo;
    private Boolean desHabilitarBtnEliminarInsumo;

    private Boolean mostrarBtnGuardar;
    private Boolean mostrarBtnActualizar;
    private Boolean muestrapnlPacientesInsumos;
    private Boolean muestrapnlInsumos;

    private Mensajes mensaje = new Mensajes();
    private Usuarios usuarios;
    private Utilidades util = new Utilidades();

    private Integer idSolicitud;
    private Boolean ingresoPlaneacion;
    private Integer idTiposolSeleccioanda;

    public SolicitudesUnidadesBeanDetalle() {
        bitacora = new BitacoraTareaEstatus();

    }

    @PostConstruct
    public void init() {
        System.out.println("Entro a SolicitudesUnidadesBeanDetalle");
        bitacora = new BitacoraTareaEstatus();
        usuarios = (Usuarios) util.getSessionAtributte("usuario");
        idSolicitud = (Integer) util.getContextAtributte("idSolicitud");
        ingresoPlaneacion = (Boolean) util.getContextAtributte("ingresoPlaneacion");
        System.out.println("SolicitudesUnidadesBeanDetalle idSolicitud: " + idSolicitud);
        listaDelegaciones = delegacionesService.obtenerDelegaciones();
        listaAreas = areasService.obtenerAreas();
        listaTipoSolicitud = tipoSolictudService.obtenerTiposSolicitud();
        solicitud = solicitudesServices.buscasolictudPorId(idSolicitud);
        idTiposolSeleccioanda = solicitud.getIdTipoSolicitud().getIdTipoSolicitud();
        System.out.println("getIdSolicitud" + solicitud.getIdSolicitud());
        setDelegacionSeleccionada(solicitud.getIdUnidadesMedicas().getIdDelegacion());
        listaPacientesInsumosDto = solicitudesInsumosPacientesService.buscaPacientesPorIdSolicitud(solicitud.getIdSolicitud());
        listaInsumosDto = solicitudesInsumosPacientesService.buscaInsumosPorIdSolicitud(idSolicitud);
        bloquearSelects();
        mostrarBtnGuardar = true;
        if (solicitud.getIdEstatus().getIdEstatus() == PlaneacionEstatusEnum.SOLICITUD_TERMINADA.getValue()) {
            bloquearSolicitudEnviada();
            bloquearSolicitudEnviadaInsumos();
        }
        if (solicitud.getIdArea().getIdArea() == PlaneacionEstatusEnum.ID_AREA_INFRAESTRUCTURA.getValue()) {
            muestrapnlInsumos = true;
            muestrapnlPacientesInsumos = false;
        } else if (solicitud.getIdArea().getIdArea() == PlaneacionEstatusEnum.ID_AREA_PREVENCION.getValue()) {
            muestrapnlInsumos = true;
            muestrapnlPacientesInsumos = false;
        } else {
            muestrapnlInsumos = false;
            muestrapnlPacientesInsumos = true;
        }

        if (ingresoPlaneacion != null && ingresoPlaneacion) {
            desHabilitarSelidTipoSolicitud = false;
            mostrarBtnActualizar = true;
            desHabilitarBtnModificar = false;
        } else {
            mostrarBtnActualizar = false;
        }
    }

    public void bloquearSelects() {
        desHabilitarSelDelegacion = true;
        desHabilitarSelUnidadMedica = true;
        desHabilitarSelidTipoSolicitud = true;
        desHabilitarSelArea = true;
        desHabilitarInpPeriodo = true;
    }

    public void bloquearSolicitudEnviada() {

        desHabilitarBtnCargarPeriodoAnterior = true;
        desHabilitarBtnAgregarPaciente = true;
        desHabilitarBtnEliminarTodo = true;
        desHabilitarBtnModificar = true;
        desHabilitarBtnEliminar = true;
        desHabilitarSelDelegacion = true;
        desHabilitarSelUnidadMedica = true;
        desHabilitarSelidTipoSolicitud = true;
        mostrarBtnGuardar = false;
        mostrarBtnActualizar = false;
        desHabilitarSelArea = true;
        desHabilitarInpPeriodo = true;

    }

    public void bloquearSolicitudEnviadaInsumos() {

        desHabilitarBtnCargarPeriodoAnteriorInsumo = true;
        desHabilitarBtnAgregarPacienteInsumo = true;
        desHabilitarBtnEliminarTodoInsumo = true;
        desHabilitarBtnModificarInsumo = true;
        desHabilitarBtnEliminarInsumo = true;
        desHabilitarSelDelegacion = true;
        desHabilitarSelUnidadMedica = true;
        desHabilitarSelidTipoSolicitud = true;
        mostrarBtnGuardar = false;
        mostrarBtnActualizar = false;
        desHabilitarInpPeriodo = true;

    }

    public void cargarPeriodoAnterior() {

        Integer solicitudAnterior = solicitudesServices.buscarPeriodoAnterior(solicitud.getFechaSolicitud());
        System.out.println("solicitudAnterior: " + solicitudAnterior);
        if (solicitudAnterior != null) {
            if (solicitudAnterior > 0) {
                solicitudesInsumosPacientesService.deleteSolicitudInsumosByIdSolicitud(solicitud.getIdSolicitud());
                if (listaPacientesInsumosDto != null) {
                    listaPacientesInsumosDto.clear();
                }
                //traer lista solictudes anteriores
                List<SolicitudesInsumosPacientes> listaAnterior = solicitudesInsumosPacientesService.getListSolInsumosPorIdSolicitud(solicitudAnterior);
                //cambiar el id de solicitud
                for (SolicitudesInsumosPacientes itemsol : listaAnterior) {
                    itemsol.setIdSolicitudesInsumosPacientes(null);
                    itemsol.setIdSolicitud(solicitud);
                    solicitudesInsumosPacientesService.guardarSolInsumo(itemsol);
                }
                //traer lista de pacientes insumos actual
                listaPacientesInsumosDto = solicitudesInsumosPacientesService.buscaPacientesPorIdSolicitud(solicitud.getIdSolicitud());

            } else {
                mensaje.mensaje("!No existe periodo anterior.", "amarillo");
            }
        } else {
            mensaje.mensaje("!No existe periodo anterior.", "amarillo");
        }

    }

    public void cargarPeriodoAnteriorInsumo() {
        solicitudesInsumosPacientesService.deleteSolicitudInsumosByIdSolicitud(solicitud.getIdSolicitud());
        Integer solicitudAnterior = solicitudesServices.buscarPeriodoAnterior(solicitud.getFechaSolicitud());
        System.out.println("solicitudAnterior: " + solicitudAnterior);
        if (solicitudAnterior != null) {
            if (solicitudAnterior > 0) {
                if (listaInsumosDto != null) {
                    listaInsumosDto.clear();
                }
                //traer lista solictudes anteriores
                List<SolicitudesInsumosPacientes> listaAnterior = solicitudesInsumosPacientesService.getListSolInsumosPorIdSolicitud(solicitudAnterior);
                //cambiar el id de solicitud
                for (SolicitudesInsumosPacientes itemsol : listaAnterior) {
                    itemsol.setIdSolicitudesInsumosPacientes(null);
                    itemsol.setIdSolicitud(solicitud);
                    solicitudesInsumosPacientesService.guardarSolInsumo(itemsol);
                }
                //traer lista de pacientes insumos actual
                listaInsumosDto = solicitudesInsumosPacientesService.buscaInsumosPorIdSolicitud(solicitud.getIdSolicitud());

            } else {
                mensaje.mensaje("!No existe periodo anterior.", "amarillo");
            }
        } else {
            mensaje.mensaje("!No existe periodo anterior.", "amarillo");
        }

    }

    public void seleccionaPaciente() {
        System.out.println("seleccionaPaciente");
        Map<String, Object> options = new HashMap<String, Object>();
        Map<String, List<String>> params = new HashMap<String, List<String>>();
        List<String> valIdSolicitud = new ArrayList<String>();
        valIdSolicitud.add(String.valueOf(idSolicitud));
        params.put("idSolicitud", valIdSolicitud);
        options.put("responsive", true);
        options.put("resizable", true);
        options.put("draggable", false);
        options.put("modal", true);
        options.put("includeViewParams", true);
        options.put("width", "1020");
        options.put("height", "400");
        options.put("contentWidth", "1020");
        options.put("contentHeight", "400");
        System.out.println("entro a choose antes request");
        RequestContext.getCurrentInstance().openDialog("/vistas/rcb/solicitudesDetallePacienteInsumo.xhtml", options, params);
        System.out.println("salio a choose despues request");

    }

    public void seleccionaInsumo() {
        System.out.println("seleccionaInsumo");
        Map<String, Object> options = new HashMap<String, Object>();
        Map<String, List<String>> params = new HashMap<String, List<String>>();
        List<String> valIdSolicitud = new ArrayList<String>();
        valIdSolicitud.add(String.valueOf(idSolicitud));
        params.put("idSolicitud", valIdSolicitud);
        options.put("responsive", true);
        options.put("resizable", true);
        options.put("draggable", false);
        options.put("modal", true);
        options.put("includeViewParams", true);
        options.put("width", "1020");
        options.put("height", "400");
        options.put("contentWidth", "1020");
        options.put("contentHeight", "400");
        System.out.println("entro a choose antes request");
        RequestContext.getCurrentInstance().openDialog("/vistas/rcb/solicitudesDetalleInsumo.xhtml", options, params);
        System.out.println("salio a choose despues request");

    }

    public void actualizaPacienteInsumo(SelectEvent event) {
        if (listaPacientesInsumosDto != null) {
            listaPacientesInsumosDto.clear();
        }
        listaPacientesInsumosDto = solicitudesInsumosPacientesService.buscaPacientesPorIdSolicitud(solicitud.getIdSolicitud());
    }

    public void actualizaInsumo(SelectEvent event) {
        if (listaInsumosDto != null) {
            listaInsumosDto.clear();
        }
        listaInsumosDto = solicitudesInsumosPacientesService.buscaInsumosPorIdSolicitud(solicitud.getIdSolicitud());
    }

    public void pacienteModificado(SelectEvent event) {
        if (listaPacientesInsumosDto != null) {
            listaPacientesInsumosDto.clear();
        }
        listaPacientesInsumosDto = solicitudesInsumosPacientesService.buscaPacientesPorIdSolicitud(solicitud.getIdSolicitud());

    }

    public void insumoModificado(SelectEvent event) {
        if (listaInsumosDto != null) {
            listaInsumosDto.clear();
        }
        listaInsumosDto = solicitudesInsumosPacientesService.buscaInsumosPorIdSolicitud(solicitud.getIdSolicitud());

    }

    public void handleClose(CloseEvent event) {
        System.out.println("entre a vento cerrar");
    }

    public void eliminarPacientes() {

        solicitudesInsumosPacientesService.deleteSolicitudInsumosByIdSolicitud(idSolicitud);
        listaPacientesInsumosDto.clear();
    }

    public void eliminarInsumos() {

        solicitudesInsumosPacientesService.deleteSolicitudInsumosByIdSolicitud(idSolicitud);
        listaInsumosDto.clear();
    }

    public void eliminarPaciente(PacientesInsumosViewDto pacienteNumInsumo) {
        solicitudesInsumosPacientesService.deleteSolicitudInsumosByIdSolicitudIdPaciente(pacienteNumInsumo.getIdSolicitud(),
                pacienteNumInsumo.getPaciente().getIdPaciente());
        listaPacientesInsumosDto.remove(pacienteNumInsumo);

    }

    public void eliminarInsumo(SolicitudesInsumosPacientes numInsumo) {
        solicitudesInsumosPacientesService.deleteSolInsumos(numInsumo);
        listaInsumosDto.remove(numInsumo);

    }

    public void seleccionaPacienteAmodificar(PacientesInsumosViewDto pacienteNumInsumo) {
        System.out.println("entro a choose");
        System.out.println("pacienteNumInsumo: " + pacienteNumInsumo.getPaciente().getNombre());
        System.out.println("idsolicitud: " + pacienteNumInsumo.getIdSolicitud());
        Map<String, Object> options = new HashMap<String, Object>();
        Map<String, List<String>> params = new HashMap<String, List<String>>();
        List<String> valIdSolicitud = new ArrayList<String>();
        List<String> valIdPaciente = new ArrayList<String>();
        valIdSolicitud.add(String.valueOf(pacienteNumInsumo.getIdSolicitud()));
        valIdPaciente.add(String.valueOf(pacienteNumInsumo.getPaciente().getIdPaciente()));
        params.put("idSolicitud", valIdSolicitud);
        params.put("idPaciente", valIdPaciente);
        options.put("resizable", false);
        options.put("draggable", false);
        options.put("modal", true);
        options.put("includeViewParams", true);
        options.put("width", "1020");
        options.put("height", "400");
        options.put("contentWidth", "1020");
        options.put("contentHeight", "400");
        System.out.println("entro a choose antes request");
        RequestContext.getCurrentInstance().openDialog("/vistas/rcb/solicitudesDetallePacienteInsumo.xhtml", options, params);
        System.out.println("salio a choose despues request");

    }

    public void seleccionaInsumoAmodificar(SolicitudesInsumosPacientes numInsumo) {
        System.out.println("entro a choose");
        System.out.println("numInsumo: " + numInsumo.getIdInsumo().getClave());
        System.out.println("IdSolicitudesInsumosPacientes: " + numInsumo.getIdSolicitudesInsumosPacientes());
        Map<String, Object> options = new HashMap<String, Object>();
        Map<String, List<String>> params = new HashMap<String, List<String>>();
        List<String> valIdSolicitud = new ArrayList<String>();
        valIdSolicitud.add(String.valueOf(numInsumo.getIdSolicitudesInsumosPacientes()));
        params.put("idSolicitudesInsumosPacientes", valIdSolicitud);
        options.put("resizable", false);
        options.put("draggable", false);
        options.put("modal", true);
        options.put("includeViewParams", true);
        options.put("width", "1020");
        options.put("height", "400");
        options.put("contentWidth", "1020");
        options.put("contentHeight", "400");
        System.out.println("entro a choose antes request");
        RequestContext.getCurrentInstance().openDialog("/vistas/rcb/solicitudesDetalleModificaInsumo.xhtml", options, params);
        System.out.println("salio a choose despues request");

    }

    public void guardarSolicitud() {
        if (listaPacientesInsumosDto != null) {
            //Tipo de solicitud mensual
            if (listaPacientesInsumosDto.size() > 0 && idTiposolSeleccioanda == 1) {
                solicitud.setIdEstatus(new Estatus(PlaneacionEstatusEnum.SOLICITUD_TERMINADA.getValue()));
                List<PeriodoArea> listperiodo = periodoAreaService.obtenerPeriodosPorArea(solicitud.getIdArea().getIdArea());
                System.out.println("listperiodo" + listperiodo.size());
                Calendar cal = Calendar.getInstance();
                int dayOfMonth = cal.get(Calendar.DAY_OF_MONTH);
                if (solicitud.getIdTipoSolicitud().getIdTipoSolicitud() == PlaneacionEstatusEnum.IDSOLICITUD_SOPORTE.getValue()) {
                    Solicitudes tmpSolicitud = solicitudesServices.updateEstatusSolicitud(solicitud);
                    init();
                    mensaje.mensaje("Solicitud enviada", "verde");
                } else {
                    if (dayOfMonth > listperiodo.get(0).getDiaInicial()
                            && dayOfMonth < listperiodo.get(0).getDiaFinal()) {
                        solicitudesServices.updateEstatusSolicitud(solicitud);
                        init();
                        mensaje.mensaje("Solicitud enviada", "verde");
                    } else {
                        solicitud.setIdTipoSolicitud(new TipoSolicitud(PlaneacionEstatusEnum.IDSOLICITUD_EXTRAORDINARIA.getValue()));
                        solicitudesServices.updateEstatusTipoSolicitud(solicitud);
                        bitacora.setFecha(new Date());
                        bitacora.setIdEstatus(solicitud.getIdEstatus().getIdEstatus());
                        bitacora.setDescripcion("Se modifico el estatus de la solicitud " + solicitud.getNumeroSolicitud());
                        bitacora.setIdUsuarios(usuarios.getIdUsuario());
                        bitacora.setIdTareaId(solicitud.getIdSolicitud());
                        bitacora.setIdModulos(PlaneacionEstatusEnum.ID_MODULO_SOLICITUDES.getValue());
                        bitacoraService.guardarEnBitacora(bitacora);
                        init();
                        mensaje.mensaje("La solicitud fue procesada como extraordinaria ya que se genero fuera del periodo mensual, solicitud enviada", "amarillo");
                    }

                }
            } else {
                if (listaPacientesInsumosDto.size() < 0) {
                    //mensaje.mensaje("Debe agregar pacientes e insumos 1", "rojo");
                }
            }
            // Tipo de solicitud de insumos por soporte de vida
            if (listaPacientesInsumosDto.size() > 0 && idTiposolSeleccioanda == 2) {
                solicitud.setIdEstatus(new Estatus(PlaneacionEstatusEnum.SOLICITUD_TERMINADA.getValue()));
                List<PeriodoArea> listperiodo = periodoAreaService.obtenerPeriodosPorArea(solicitud.getIdArea().getIdArea());
                System.out.println("listperiodo" + listperiodo.size());
                Calendar cal = Calendar.getInstance();
                Solicitudes tmpSolicitud = solicitudesServices.updateEstatusSolicitud(solicitud);
                bitacora.setFecha(new Date());
                bitacora.setIdEstatus(solicitud.getIdEstatus().getIdEstatus());
                bitacora.setDescripcion("Se modifico el estatus de la solicitud " + solicitud.getNumeroSolicitud());
                bitacora.setIdUsuarios(usuarios.getIdUsuario());
                bitacora.setIdTareaId(solicitud.getIdSolicitud());
                bitacora.setIdModulos(PlaneacionEstatusEnum.ID_MODULO_SOLICITUDES.getValue());
                bitacoraService.guardarEnBitacora(bitacora);
                init();
                mensaje.mensaje("Solicitud enviada", "verde");
            } else {
                //mensaje.mensaje("Debe agregar pacientes e insumos 1", "rojo");
            }
        } else {
            mensaje.mensaje("Debe agregar pacientes e insumos 2", "rojo");
        }

    }

    public void guardarSolicitudInsumos() {
        if (listaInsumosDto != null) {
            if (listaInsumosDto.size() > 0) {
                solicitud.setIdEstatus(new Estatus(PlaneacionEstatusEnum.SOLICITUD_TERMINADA.getValue()));
                List<PeriodoArea> listperiodo = periodoAreaService.obtenerPeriodosPorArea(solicitud.getIdArea().getIdArea());
                System.out.println("listperiodo" + listperiodo.size());
                Calendar cal = Calendar.getInstance();
                int dayOfMonth = cal.get(Calendar.DAY_OF_MONTH);
                if (dayOfMonth > listperiodo.get(0).getDiaInicial()
                        && dayOfMonth < listperiodo.get(0).getDiaFinal()) {
                    solicitudesServices.updateEstatusSolicitud(solicitud);
                    init();
                    mensaje.mensaje("Solicitud enviada", "verde");
                } else {
                    if (dayOfMonth > listperiodo.get(0).getDiaInicial()
                            && dayOfMonth < listperiodo.get(0).getDiaFinal()) {
                        solicitudesServices.updateEstatusSolicitud(solicitud);
                        init();
                        mensaje.mensaje("Solicitud enviada", "verde");
                    } else {
                        solicitud.setIdTipoSolicitud(new TipoSolicitud(PlaneacionEstatusEnum.IDSOLICITUD_EXTRAORDINARIA.getValue()));
                        solicitudesServices.updateEstatusTipoSolicitud(solicitud);
                        bitacora.setFecha(new Date());
                        bitacora.setIdEstatus(solicitud.getIdEstatus().getIdEstatus());
                        bitacora.setDescripcion("Se modifico el estatus de la solicitud " + solicitud.getNumeroSolicitud());
                        bitacora.setIdUsuarios(usuarios.getIdUsuario());
                        bitacora.setIdTareaId(solicitud.getIdSolicitud());
                        bitacora.setIdModulos(PlaneacionEstatusEnum.ID_MODULO_SOLICITUDES.getValue());
                        bitacoraService.guardarEnBitacora(bitacora);
                        init();
                        mensaje.mensaje("La solicitud fue procesada como extraordinaria ya que se genero fuera del periodo mensual, solicitud enviada", "amarillo");
                    }
                }
            } else {
                mensaje.mensaje("Debe agregar insumos 1", "rojo");
            }
        } else {
            mensaje.mensaje("Debe agregar insumos 2", "rojo");
        }

    }

    public void actualizarSolictud() {

        if (listaPacientesInsumosDto != null) {
            if (listaPacientesInsumosDto.size() > 0) {
                System.out.println("solicitud tipo:" + idTiposolSeleccioanda);
                solicitud.getIdTipoSolicitud().setIdTipoSolicitud(idTiposolSeleccioanda);
                solicitud = solicitudesServices.update(solicitud);
                mensaje.mensaje("Solicitud actualizada", "verde");
            } else {
                mensaje.mensaje("Debe agregar pacientes e insumos 1", "rojo");
            }
        } else {
            mensaje.mensaje("Debe agregar pacientes e insumos 2", "rojo");
        }

    }

    public void cargarTipoSolicitud() {
        solicitud.setIdTipoSolicitud(new TipoSolicitud(idTiposolSeleccioanda));

    }

    public Solicitudes getSolicitud() {
        return solicitud;
    }

    public void setSolicitud(Solicitudes solicitud) {
        this.solicitud = solicitud;
    }

    public Delegaciones getDelegacionSeleccionada() {
        return delegacionSeleccionada;
    }

    public void setDelegacionSeleccionada(Delegaciones delegacionSeleccionada) {
        this.delegacionSeleccionada = delegacionSeleccionada;
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

    public Boolean getDesHabilitarBtnCargarPeriodoAnterior() {
        return desHabilitarBtnCargarPeriodoAnterior;
    }

    public void setDesHabilitarBtnCargarPeriodoAnterior(Boolean desHabilitarBtnCargarPeriodoAnterior) {
        this.desHabilitarBtnCargarPeriodoAnterior = desHabilitarBtnCargarPeriodoAnterior;
    }

    public Boolean getDesHabilitarBtnAgregarPaciente() {
        return desHabilitarBtnAgregarPaciente;
    }

    public void setDesHabilitarBtnAgregarPaciente(Boolean desHabilitarBtnAgregarPaciente) {
        this.desHabilitarBtnAgregarPaciente = desHabilitarBtnAgregarPaciente;
    }

    public Boolean getDesHabilitarBtnEliminarTodo() {
        return desHabilitarBtnEliminarTodo;
    }

    public void setDesHabilitarBtnEliminarTodo(Boolean desHabilitarBtnEliminarTodo) {
        this.desHabilitarBtnEliminarTodo = desHabilitarBtnEliminarTodo;
    }

    public List<PacientesInsumosViewDto> getListaPacientesInsumosDto() {
        return listaPacientesInsumosDto;
    }

    public void setListaPacientesInsumosDto(List<PacientesInsumosViewDto> listaPacientesInsumosDto) {
        this.listaPacientesInsumosDto = listaPacientesInsumosDto;
    }

    public List<PacientesInsumosViewDto> getListaPacientesInsumosDtoFilter() {
        return listaPacientesInsumosDtoFilter;
    }

    public void setListaPacientesInsumosDtoFilter(List<PacientesInsumosViewDto> listaPacientesInsumosDtoFilter) {
        this.listaPacientesInsumosDtoFilter = listaPacientesInsumosDtoFilter;
    }

    public Boolean getDesHabilitarBtnModificar() {
        return desHabilitarBtnModificar;
    }

    public void setDesHabilitarBtnModificar(Boolean desHabilitarBtnModificar) {
        this.desHabilitarBtnModificar = desHabilitarBtnModificar;
    }

    public Boolean getDesHabilitarBtnEliminar() {
        return desHabilitarBtnEliminar;
    }

    public void setDesHabilitarBtnEliminar(Boolean desHabilitarBtnEliminar) {
        this.desHabilitarBtnEliminar = desHabilitarBtnEliminar;
    }

    public Boolean getMostrarBtnGuardar() {
        return mostrarBtnGuardar;
    }

    public void setMostrarBtnGuardar(Boolean mostrarBtnGuardar) {
        this.mostrarBtnGuardar = mostrarBtnGuardar;
    }

    public Integer getIdSolicitud() {
        return idSolicitud;
    }

    public void setIdSolicitud(Integer idSolicitud) {
        this.idSolicitud = idSolicitud;
    }

    public Boolean getDesHabilitarSelDelegacion() {
        return desHabilitarSelDelegacion;
    }

    public void setDesHabilitarSelDelegacion(Boolean desHabilitarSelDelegacion) {
        this.desHabilitarSelDelegacion = desHabilitarSelDelegacion;
    }

    public Boolean getDesHabilitarSelUnidadMedica() {
        return desHabilitarSelUnidadMedica;
    }

    public void setDesHabilitarSelUnidadMedica(Boolean desHabilitarSelUnidadMedica) {
        this.desHabilitarSelUnidadMedica = desHabilitarSelUnidadMedica;
    }

    public Boolean getMuestrapnlPacientesInsumos() {
        return muestrapnlPacientesInsumos;
    }

    public void setMuestrapnlPacientesInsumos(Boolean muestrapnlPacientesInsumos) {
        this.muestrapnlPacientesInsumos = muestrapnlPacientesInsumos;
    }

    public Boolean getMuestrapnlInsumos() {
        return muestrapnlInsumos;
    }

    public void setMuestrapnlInsumos(Boolean muestrapnlInsumos) {
        this.muestrapnlInsumos = muestrapnlInsumos;
    }

    public Boolean getDesHabilitarBtnCargarPeriodoAnteriorInsumo() {
        return desHabilitarBtnCargarPeriodoAnteriorInsumo;
    }

    public void setDesHabilitarBtnCargarPeriodoAnteriorInsumo(Boolean desHabilitarBtnCargarPeriodoAnteriorInsumo) {
        this.desHabilitarBtnCargarPeriodoAnteriorInsumo = desHabilitarBtnCargarPeriodoAnteriorInsumo;
    }

    public Boolean getDesHabilitarBtnAgregarPacienteInsumo() {
        return desHabilitarBtnAgregarPacienteInsumo;
    }

    public void setDesHabilitarBtnAgregarPacienteInsumo(Boolean desHabilitarBtnAgregarPacienteInsumo) {
        this.desHabilitarBtnAgregarPacienteInsumo = desHabilitarBtnAgregarPacienteInsumo;
    }

    public Boolean getDesHabilitarBtnEliminarTodoInsumo() {
        return desHabilitarBtnEliminarTodoInsumo;
    }

    public void setDesHabilitarBtnEliminarTodoInsumo(Boolean desHabilitarBtnEliminarTodoInsumo) {
        this.desHabilitarBtnEliminarTodoInsumo = desHabilitarBtnEliminarTodoInsumo;
    }

    public Boolean getDesHabilitarBtnModificarInsumo() {
        return desHabilitarBtnModificarInsumo;
    }

    public void setDesHabilitarBtnModificarInsumo(Boolean desHabilitarBtnModificarInsumo) {
        this.desHabilitarBtnModificarInsumo = desHabilitarBtnModificarInsumo;
    }

    public Boolean getDesHabilitarBtnEliminarInsumo() {
        return desHabilitarBtnEliminarInsumo;
    }

    public void setDesHabilitarBtnEliminarInsumo(Boolean desHabilitarBtnEliminarInsumo) {
        this.desHabilitarBtnEliminarInsumo = desHabilitarBtnEliminarInsumo;
    }

    public List<SolicitudesInsumosPacientes> getListaInsumosDto() {
        return listaInsumosDto;
    }

    public void setListaInsumosDto(List<SolicitudesInsumosPacientes> listaInsumosDto) {
        this.listaInsumosDto = listaInsumosDto;
    }

    public List<SolicitudesInsumosPacientes> getListaInsumosDtoFilter() {
        return listaInsumosDtoFilter;
    }

    public void setListaInsumosDtoFilter(List<SolicitudesInsumosPacientes> listaInsumosDtoFilter) {
        this.listaInsumosDtoFilter = listaInsumosDtoFilter;
    }

    public List<TipoSolicitud> getListaTipoSolicitud() {
        return listaTipoSolicitud;
    }

    public void setListaTipoSolicitud(List<TipoSolicitud> listaTipoSolicitud) {
        this.listaTipoSolicitud = listaTipoSolicitud;
    }

    public Boolean getDesHabilitarSelidTipoSolicitud() {
        return desHabilitarSelidTipoSolicitud;
    }

    public void setDesHabilitarSelidTipoSolicitud(Boolean desHabilitarSelidTipoSolicitud) {
        this.desHabilitarSelidTipoSolicitud = desHabilitarSelidTipoSolicitud;
    }

    public Boolean getIngresoPlaneacion() {
        return ingresoPlaneacion;
    }

    public void setIngresoPlaneacion(Boolean ingresoPlaneacion) {
        this.ingresoPlaneacion = ingresoPlaneacion;
    }

    public Boolean getDesHabilitarSelArea() {
        return desHabilitarSelArea;
    }

    public void setDesHabilitarSelArea(Boolean desHabilitarSelArea) {
        this.desHabilitarSelArea = desHabilitarSelArea;
    }

    public Boolean getDesHabilitarInpPeriodo() {
        return desHabilitarInpPeriodo;
    }

    public void setDesHabilitarInpPeriodo(Boolean desHabilitarInpPeriodo) {
        this.desHabilitarInpPeriodo = desHabilitarInpPeriodo;
    }

    public Boolean getMostrarBtnActualizar() {
        return mostrarBtnActualizar;
    }

    public void setMostrarBtnActualizar(Boolean mostrarBtnActualizar) {
        this.mostrarBtnActualizar = mostrarBtnActualizar;
    }

    public Integer getIdTiposolSeleccioanda() {
        return idTiposolSeleccioanda;
    }

    public void setIdTiposolSeleccioanda(Integer idTiposolSeleccioanda) {
        this.idTiposolSeleccioanda = idTiposolSeleccioanda;
    }

}
