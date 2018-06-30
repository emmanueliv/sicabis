/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.issste.sicabis.controller;

import com.issste.sicabis.ejb.ln.BitacoraTareaSerice;
import com.issste.sicabis.ejb.ln.InsumosService;
import com.issste.sicabis.ejb.ln.PacientesService;
import com.issste.sicabis.ejb.ln.SolicitudesInsumosPacientesService;
import com.issste.sicabis.ejb.modelo.BitacoraTareaEstatus;
import com.issste.sicabis.ejb.modelo.Estatus;
import com.issste.sicabis.ejb.modelo.Insumos;
import com.issste.sicabis.ejb.modelo.Pacientes;
import com.issste.sicabis.ejb.modelo.Solicitudes;
import com.issste.sicabis.ejb.modelo.SolicitudesInsumosPacientes;
import com.issste.sicabis.ejb.modelo.Usuarios;
import com.issste.sicabis.ejb.sipeav.ln.VwSicabisService;
import com.issste.sicabis.ejb.sipeav.modelo.Sicabis;
import com.issste.sicabis.utils.Mensajes;
import com.issste.sicabis.utils.Utilidades;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import org.primefaces.context.RequestContext;
import org.primefaces.event.CloseEvent;

/**
 *
 * @author Toshiba Manolo
 */
@ManagedBean(name = "solicitudesDetallePacienteInsumo")
@ViewScoped
public class SolicitudesDetallePacienteInsumoBean {

    private static final long serialVersionUID = 1L;

    @EJB
    private BitacoraTareaSerice bitacoraTareaSerice;
    @EJB
    private VwSicabisService vwSicabisService;
    @EJB
    private PacientesService pacientesService;
    @EJB
    private InsumosService insumosService;
    @EJB
    private SolicitudesInsumosPacientesService solicitudesInsumosPacientesService;

    private Boolean mostrarbtnGuardarInsumoRcb;
    private Boolean deshabilitarInpNombre;
    private Boolean deshabilitarInpExpedinete;
    private Boolean deshabilitarInpDiagnostico;
    private Boolean deshabilitarInpNss;
    private Boolean deshabilitarInpCurp;
    private Boolean deshabilitarInpClave;
    private Boolean deshabilitarInpDescripcion;
    private Boolean deshabilitarInpCantidad;
    private Boolean desHabilitarBtnModificar;
    private Boolean desHabilitarBtnEliminar;
    private Boolean mostrarBtnBuscarPaciente;

    private Mensajes mensaje = new Mensajes();
    private Usuarios usuarios;
    private Utilidades util = new Utilidades();

    private String idSolicitud;
    private String idPaciente;
    private String idSolicitudesInsumosPacientes;
    private String modificar;
    private String diagnostico;
    private Pacientes paciente;
    private Insumos insumos;
    private Integer cantidadInsumo;
    private BitacoraTareaEstatus bitacoraTareaEstatus;

    private List<SolicitudesInsumosPacientes> listInsumosPacientes = new ArrayList<>();
    private List<SolicitudesInsumosPacientes> listInsumosPacientesFilter;
    private SolicitudesInsumosPacientes solicitudInsumoPaciente = new SolicitudesInsumosPacientes();

    private List<Sicabis> listaSipe;
    private List<Pacientes> listPacientes;
    private boolean bbotonbeneficiario;
    private List<Sicabis> listaSipeBeneficiarios;
    private boolean bpaciente;
    private Integer edad;

    @PostConstruct
    public void init() {
        paciente = new Pacientes();
        insumos = new Insumos();
        mostrarbtnGuardarInsumoRcb = true;
        deshabilitarInpExpedinete = false;
        deshabilitarInpNombre = false;
        deshabilitarInpDiagnostico = false;
        deshabilitarInpNss = false;
        deshabilitarInpCurp = false;
        deshabilitarInpClave = false;
        deshabilitarInpDescripcion = false;
        deshabilitarInpCantidad = false;
        desHabilitarBtnModificar = false;
        desHabilitarBtnEliminar = false;
        mostrarBtnBuscarPaciente = true;
        usuarios = (Usuarios) util.getSessionAtributte("usuario");
        bitacoraTareaEstatus = new BitacoraTareaEstatus();
        listInsumosPacientes = new ArrayList<>();
        listaSipe = new ArrayList();
        listaSipeBeneficiarios = new ArrayList();
        listPacientes = new ArrayList<>();
    }

    public void onload() {
        if (idSolicitud != null) {
            listInsumosPacientes = solicitudesInsumosPacientesService.buscaInsumosPorIdSolicitud(Integer.parseInt(idSolicitud));
        }

        if (idSolicitudesInsumosPacientes != null) {
            solicitudInsumoPaciente = solicitudesInsumosPacientesService.getSolInsumoById(Integer.parseInt(idSolicitudesInsumosPacientes));
            insumos = solicitudInsumoPaciente.getIdInsumo();
            cantidadInsumo = solicitudInsumoPaciente.getCantidad();
        }

    }

    public void actualizarPaciente() {
        pacientesService.guardarPaciente(this.paciente);
        buscarPaciente();
    }

    public void onloadPaciente() {
        if (idPaciente != null && idSolicitud != null) {
            listInsumosPacientes = solicitudesInsumosPacientesService.traeInsumosPorPaciente(Integer.parseInt(idSolicitud), Integer.parseInt(idPaciente));
            paciente = pacientesService.buscaPacientePorIdPaciente(Integer.parseInt(idPaciente));
            mostrarBtnBuscarPaciente = false;
        }

    }

    public void guardarInsumosPacientes() {

    }

    public void buscarPaciente() {
        Boolean validaCamposCurp = false;
        Boolean validaCamposNss = false;
        bpaciente = false;
        listaSipe = null;
        if (paciente.getCurp() == null || paciente.getCurp().isEmpty() || paciente.getCurp().trim().isEmpty()) {
            validaCamposCurp = true;
        }

        if (paciente.getNss() == null || paciente.getNss().isEmpty() || paciente.getNss().trim().isEmpty()) {
            validaCamposNss = true;
        }

        if (validaCamposCurp && validaCamposNss) {
            mensaje.mensaje("Debe buscar por CURP ó Número ISSSTE", "rojo");
        } else {
            listaSipe = vwSicabisService.obtenerByNssCurp(paciente.getNss(), paciente.getCurp());
            //bbotonbeneficiario = true;
            if (listaSipe != null && listaSipe.size() > 0) {
                if (pacientesService.obtenerPacientesByCurp(listaSipe.get(0).getCurp()) == null) {
                    paciente = new Pacientes();
                    bpaciente = true;
                    Sicabis s = listaSipe.get(0);
                    paciente.setNss(s.getNumIssste().toString());
                    paciente.setNombre(s.getNomTra());
                    paciente.setApaterno(s.getApaTra());
                    paciente.setAmaterno(s.getAmaTra());
                    paciente.setCurp(s.getCurpTra());
                    paciente.setFechaNacimiento(util.curp2date(paciente.getCurp()));
                    paciente.setGenero(util.sacarGeneroCurp(paciente.getCurp()));
                    paciente.setActivo(1);
                    edad = calculaEdad(paciente.getFechaNacimiento());
                    if (s.getItoId() != null) {
                        paciente.setBeneficiario(0);
                        bbotonbeneficiario = true;
                        listaSipeBeneficiarios = vwSicabisService.obtenerBeneficiarios(paciente.getNss());
                    } else {
                        bbotonbeneficiario = false;
                    }
                    pacientesService.guardarPaciente(paciente);
                } else {
                    Sicabis s = listaSipe.get(0);
                    paciente.setIdPaciente(pacientesService.obtenerPacientesByCurp(s.getCurp()).getIdPaciente());
                    paciente.setNss(s.getNumIssste().toString());
                    paciente.setNombre(s.getNomTra());
                    paciente.setApaterno(s.getApaTra());
                    paciente.setAmaterno(s.getAmaTra());
                    paciente.setCurp(s.getCurpTra());
                    paciente.setFechaNacimiento(util.curp2date(paciente.getCurp()));
                    paciente.setGenero(util.sacarGeneroCurp(paciente.getCurp()));
                    edad = calculaEdad(paciente.getFechaNacimiento());
                    paciente.setActivo(1);
                    pacientesService.guardarPaciente(paciente);
                }
            } else {
                listPacientes = pacientesService.obtenerPacientesByCurpOrNSS(paciente.getCurp(), paciente.getNss());
                if (listPacientes.size() > 0) {
                    paciente.setIdPaciente(listPacientes.get(0).getIdPaciente());
                    paciente.setNss(listPacientes.get(0).getNss());
                    paciente.setNombre(listPacientes.get(0).getNombre());
                    paciente.setApaterno(listPacientes.get(0).getApaterno());
                    paciente.setAmaterno(listPacientes.get(0).getAmaterno());
                    paciente.setCurp(listPacientes.get(0).getCurp());
                    paciente.setFechaNacimiento(util.curp2date(paciente.getCurp()));
                    edad = calculaEdad(paciente.getFechaNacimiento());
                    paciente.setGenero(util.sacarGeneroCurp(paciente.getCurp()));
                    paciente.setActivo(1);
                    if (listPacientes.get(0).getBeneficiario() != null) {
                        paciente.setBeneficiario(0);
                        bbotonbeneficiario = true;
                        listaSipeBeneficiarios = vwSicabisService.obtenerBeneficiarios(paciente.getNss());
                    } else {
                        bbotonbeneficiario = false;
                    }
                    if (pacientesService.obtenerPacientesByCurpOrNSS(paciente.getCurp(), paciente.getNss()) != null
                            && pacientesService.obtenerPacientesByCurpOrNSS(paciente.getCurp(), paciente.getNss()).size() > 0) {
                        listPacientes = pacientesService.obtenerPacientesByCurpOrNSS(paciente.getCurp(), paciente.getNss());
                        pacientesService.guardarPaciente(listPacientes.get(0));
                    }

                }
                List<Pacientes> listaPacientes = pacientesService.buscaPacientesTypedQuery(paciente);
                if (listaPacientes.size() > 1) {
                    mensaje.mensaje("Existen mas de una coincidencia en la búsqueda, consulte al administrador", "amarillo");
                    paciente = new Pacientes();
                }
                if (listaPacientes.size() == 0) {
                    mensaje.mensaje("No existen coincidencias en la búsqueda, verifique los datos", "amarillo");
                    paciente = new Pacientes();
                } else {
                    paciente = listaPacientes.get(0);
                    edad = calculaEdad(paciente.getFechaNacimiento());
                    bpaciente = true;
                }
            }
        }

        bitacoraTareaEstatus.setDescripcion("Paciente modificacion/Guardado");
        bitacoraTareaEstatus.setFecha(new Date());
        bitacoraTareaEstatus.setIdEstatus(0);
        bitacoraTareaEstatus.setIdModulos(1);
        bitacoraTareaEstatus.setIdTareaId(0);
        bitacoraTareaEstatus.setIdUsuarios(usuarios.getIdUsuario());
        bitacoraTareaSerice.guardarEnBitacora(bitacoraTareaEstatus);
    }

    public void verBeneficiario() {
        RequestContext context = RequestContext.getCurrentInstance();
        context.execute("PF('dlg4').show();");
    }

    public void seleccionaBeneficiario(Sicabis s) {
        //paciente.setNss(s.getNumIssste().toString());
        //paciente = new Pacientes();
        paciente.setNombre(s.getNombre());
        paciente.setApaterno(s.getApellidoPaterno());
        paciente.setAmaterno(s.getApellidoMaterno());
        paciente.setCurp(s.getCurp());
        paciente.setBeneficiario(1);
        paciente.setFechaNacimiento(util.curp2date(paciente.getCurp()));
        edad = calculaEdad(paciente.getFechaNacimiento());
    }

    public void buscarInsumo() {
        if (insumos.getClave() == null || insumos.getClave().isEmpty() || insumos.getClave().trim().isEmpty()) {
            mensaje.mensaje("Debe Buscar por Clave de Insumo", "rojo");
        } else {
            List<Insumos> listaInsumo = insumosService.buscarInsumosPorClave(insumos.getClave());
            if (listaInsumo.size() > 1) {
                mensaje.mensaje("Existen mas de una coincidencia en la busqueda, consulte al administrador", "amarillo");
                insumos = new Insumos();
            }
            if (listaInsumo.size() == 0) {
                mensaje.mensaje("No existen coincidencias en la búsqueda, verifique los datos", "amarillo");
                insumos = new Insumos();
            } else {
                insumos = listaInsumo.get(0);
            }
        }
    }

    public void agregarInsumo() {
        if (listInsumosPacientes == null) {
            listInsumosPacientes = new ArrayList<>();
        }
        Boolean agregarPacienteInsumo = true;
        if (!bpaciente) {
            agregarPacienteInsumo = false;
        }
        if (insumos.getIdInsumo() == null || insumos.getIdInsumo() <= 0) {
            agregarPacienteInsumo = false;
        }
        if (cantidadInsumo == null || cantidadInsumo <= 0) {
            agregarPacienteInsumo = false;
        }

        for (SolicitudesInsumosPacientes insumoPaciente : listInsumosPacientes) {
            if (insumoPaciente.getIdInsumo().equals(insumos)) {
                agregarPacienteInsumo = false;
                mensaje.mensaje("El insumo ya fue registrado.", "rojo");
                break;
            }
        }
        if (agregarPacienteInsumo) {
            solicitudInsumoPaciente = new SolicitudesInsumosPacientes();
            solicitudInsumoPaciente.setActivo(1);
            solicitudInsumoPaciente.setCantidad(cantidadInsumo);
            solicitudInsumoPaciente.setFechaAlta(new Date());
            solicitudInsumoPaciente.setIdInsumo(insumos);
            solicitudInsumoPaciente.setIdPaciente(paciente);
            solicitudInsumoPaciente.setIdSolicitud(new Solicitudes(Integer.parseInt(idSolicitud)));
            listInsumosPacientes.add(solicitudInsumoPaciente);
            insumos = new Insumos();
            mostrarBtnBuscarPaciente = false;
        } else {
            mensaje.mensaje("Debe seleccionar paciente, clave y la cantidad.", "rojo");
        }

    }

    public void agregarInsumoSinPaciente() {
        Boolean agregarPacienteInsumo = true;
        if (listInsumosPacientes == null) {
            listInsumosPacientes = new ArrayList<>();
        }
        if (insumos.getIdInsumo() == null || insumos.getIdInsumo() <= 0) {
            agregarPacienteInsumo = false;
        }
        if (cantidadInsumo == null || cantidadInsumo <= 0) {
            agregarPacienteInsumo = false;
        }
        for (SolicitudesInsumosPacientes insumoPaciente : listInsumosPacientes) {
            if (insumoPaciente.getIdInsumo().equals(insumos)) {
                agregarPacienteInsumo = false;
                mensaje.mensaje("El insumo ya fue registrado.", "rojo");
                break;
            }
        }
        if (agregarPacienteInsumo) {
            solicitudInsumoPaciente = new SolicitudesInsumosPacientes();
            solicitudInsumoPaciente.setActivo(1);
            solicitudInsumoPaciente.setCantidad(cantidadInsumo);
            solicitudInsumoPaciente.setFechaAlta(new Date());
            solicitudInsumoPaciente.setIdInsumo(insumos);
            solicitudInsumoPaciente.setIdSolicitud(new Solicitudes(Integer.parseInt(idSolicitud)));
            listInsumosPacientes.add(solicitudInsumoPaciente);
            insumos = new Insumos();
            mostrarBtnBuscarPaciente = false;
        } else {
            mensaje.mensaje("Debe seleccionar la clave y la cantidad.", "rojo");
        }
    }

    public void seleccionaInsumoAmodificar(SolicitudesInsumosPacientes insumoPaciente) {
        for (SolicitudesInsumosPacientes busquedainsPac : listInsumosPacientes) {
            if (busquedainsPac.equals(insumoPaciente)) {

            }
        }

        paciente = insumoPaciente.getIdPaciente();
        insumos = insumoPaciente.getIdInsumo();
        cantidadInsumo = insumoPaciente.getCantidad();

    }

    public void eliminarInsumo(SolicitudesInsumosPacientes insumoPaciente) {
        boolean remove = listInsumosPacientes.remove(insumoPaciente);
        if (listInsumosPacientes.size() == 0) {
            mostrarBtnBuscarPaciente = true;
        }
    }

    public void guardarInsumo() {
        if (listInsumosPacientes.size() > 0) {
            if (listaSipe != null && listaSipe.size() > 0) {
                List<Pacientes> listaPacientes = pacientesService.buscaPacientesTypedQuery(paciente);
                if (listaPacientes.size() == 0) {
                    paciente.setActivo(1);
                    paciente.setFechaAlta(new Date());
                    paciente.setGenero(0);
                    pacientesService.guardarPaciente(paciente);
                    idPaciente = paciente.getIdPaciente().toString();
                } else {
                    idPaciente = listaPacientes.get(0).getIdPaciente().toString();
                    paciente.setIdPaciente(listaPacientes.get(0).getIdPaciente());
                }
            }
            if (idPaciente != null && idSolicitud != null) {
                solicitudesInsumosPacientesService.deleteSolicitudInsumosByIdSolicitudIdPaciente(Integer.parseInt(idSolicitud), Integer.parseInt(idPaciente));
            }
            for (SolicitudesInsumosPacientes insmpacient : listInsumosPacientes) {
                insmpacient.setIdPaciente(paciente);
                solicitudesInsumosPacientesService.guardarSolInsumo(insmpacient);
            }

        }

        RequestContext.getCurrentInstance().closeDialog(this);

    }

    public void guardarInsumoSinPaciente() {
        System.out.println("entro a guardar insumo");
        if (listInsumosPacientes.size() > 0) {
            if (idSolicitud != null) {
                solicitudesInsumosPacientesService.deleteSolicitudInsumosByIdSolicitud(Integer.parseInt(idSolicitud));
            }
            for (SolicitudesInsumosPacientes insmpacient : listInsumosPacientes) {
                solicitudesInsumosPacientesService.guardarSolInsumo(insmpacient);
            }
        }

        RequestContext.getCurrentInstance().closeDialog(this);

    }

    public void modificarInsumo() {
        System.out.println("entro a guardar insumo");
        if (cantidadInsumo != null) {
            if (cantidadInsumo > 0) {
                solicitudInsumoPaciente.setCantidad(cantidadInsumo);
                solicitudesInsumosPacientesService.guardarSolInsumo(solicitudInsumoPaciente);
                RequestContext.getCurrentInstance().closeDialog(this);
            } else {
                mensaje.mensaje("La cantidad debe ser mayor a 0", "rojo");
            }
        } else {
            mensaje.mensaje("Ingrese cantidad", "rojo");
        }

    }

    public int calculaEdad(Date fechaNacimiento) {
        if (fechaNacimiento != null) {
            Calendar fechaNac = Calendar.getInstance();
            fechaNac.setTime(fechaNacimiento);
            Calendar today = Calendar.getInstance();

            int diff_year = today.get(Calendar.YEAR) - fechaNac.get(Calendar.YEAR);
            int diff_month = today.get(Calendar.MONTH) - fechaNac.get(Calendar.MONTH);
            int diff_day = today.get(Calendar.DAY_OF_MONTH) - fechaNac.get(Calendar.DAY_OF_MONTH);

            //Si está en ese año pero todavía no los ha cumplido
            if (diff_month < 0 || (diff_month == 0 && diff_day < 0)) {
                diff_year = diff_year - 1; //no aparecían los dos guiones del postincremento :|
            }
            return diff_year;
        }
        return 0;
    }

    public void salirDialogo() {
        System.out.println("entro a salir insumo");
        RequestContext.getCurrentInstance().closeDialog(this);

    }

    public void handleClose(CloseEvent event) {
        System.out.println("entre a vento cerrar paciente insumo");
    }

    public String getIdSolicitud() {
        return idSolicitud;
    }

    public void setIdSolicitud(String idSolicitud) {
        this.idSolicitud = idSolicitud;
    }

    public String getIdPaciente() {
        return idPaciente;
    }

    public void setIdPaciente(String idPaciente) {
        this.idPaciente = idPaciente;
    }

    public String getIdSolicitudesInsumosPacientes() {
        return idSolicitudesInsumosPacientes;
    }

    public void setIdSolicitudesInsumosPacientes(String idSolicitudesInsumosPacientes) {
        this.idSolicitudesInsumosPacientes = idSolicitudesInsumosPacientes;
    }

    public Boolean getMostrarbtnGuardarInsumoRcb() {
        return mostrarbtnGuardarInsumoRcb;
    }

    public void setMostrarbtnGuardarInsumoRcb(Boolean mostrarbtnGuardarInsumoRcb) {
        this.mostrarbtnGuardarInsumoRcb = mostrarbtnGuardarInsumoRcb;
    }

    public SolicitudesInsumosPacientes getSolicitudInsumoPaciente() {
        return solicitudInsumoPaciente;
    }

    public void setSolicitudInsumoPaciente(SolicitudesInsumosPacientes solicitudInsumoPaciente) {
        this.solicitudInsumoPaciente = solicitudInsumoPaciente;
    }

    public Boolean getDeshabilitarInpNombre() {
        return deshabilitarInpNombre;
    }

    public void setDeshabilitarInpNombre(Boolean deshabilitarInpNombre) {
        this.deshabilitarInpNombre = deshabilitarInpNombre;
    }

    public Boolean getDeshabilitarInpExpedinete() {
        return deshabilitarInpExpedinete;
    }

    public void setDeshabilitarInpExpedinete(Boolean deshabilitarInpExpedinete) {
        this.deshabilitarInpExpedinete = deshabilitarInpExpedinete;
    }

    public Boolean getDeshabilitarInpDiagnostico() {
        return deshabilitarInpDiagnostico;
    }

    public void setDeshabilitarInpDiagnostico(Boolean deshabilitarInpDiagnostico) {
        this.deshabilitarInpDiagnostico = deshabilitarInpDiagnostico;
    }

    public Boolean getDeshabilitarInpNss() {
        return deshabilitarInpNss;
    }

    public void setDeshabilitarInpNss(Boolean deshabilitarInpNss) {
        this.deshabilitarInpNss = deshabilitarInpNss;
    }

    public Boolean getDeshabilitarInpCurp() {
        return deshabilitarInpCurp;
    }

    public void setDeshabilitarInpCurp(Boolean deshabilitarInpCurp) {
        this.deshabilitarInpCurp = deshabilitarInpCurp;
    }

    public Pacientes getPaciente() {
        return paciente;
    }

    public void setPaciente(Pacientes paciente) {
        this.paciente = paciente;
    }

    public Insumos getInsumos() {
        return insumos;
    }

    public void setInsumos(Insumos insumos) {
        this.insumos = insumos;
    }

    public Boolean getDeshabilitarInpClave() {
        return deshabilitarInpClave;
    }

    public void setDeshabilitarInpClave(Boolean deshabilitarInpClave) {
        this.deshabilitarInpClave = deshabilitarInpClave;
    }

    public Boolean getDeshabilitarInpDescripcion() {
        return deshabilitarInpDescripcion;
    }

    public void setDeshabilitarInpDescripcion(Boolean deshabilitarInpDescripcion) {
        this.deshabilitarInpDescripcion = deshabilitarInpDescripcion;
    }

    public Integer getCantidadInsumo() {
        return cantidadInsumo;
    }

    public void setCantidadInsumo(Integer cantidadInsumo) {
        this.cantidadInsumo = cantidadInsumo;
    }

    public Boolean getDeshabilitarInpCantidad() {
        return deshabilitarInpCantidad;
    }

    public void setDeshabilitarInpCantidad(Boolean deshabilitarInpCantidad) {
        this.deshabilitarInpCantidad = deshabilitarInpCantidad;
    }

    public List<SolicitudesInsumosPacientes> getListInsumosPacientes() {
        return listInsumosPacientes;
    }

    public void setListInsumosPacientes(List<SolicitudesInsumosPacientes> listInsumosPacientes) {
        this.listInsumosPacientes = listInsumosPacientes;
    }

    public List<SolicitudesInsumosPacientes> getListInsumosPacientesFilter() {
        return listInsumosPacientesFilter;
    }

    public void setListInsumosPacientesFilter(List<SolicitudesInsumosPacientes> listInsumosPacientesFilter) {
        this.listInsumosPacientesFilter = listInsumosPacientesFilter;
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

    public Boolean getMostrarBtnBuscarPaciente() {
        return mostrarBtnBuscarPaciente;
    }

    public void setMostrarBtnBuscarPaciente(Boolean mostrarBtnBuscarPaciente) {
        this.mostrarBtnBuscarPaciente = mostrarBtnBuscarPaciente;
    }

    public String getModificar() {
        return modificar;
    }

    public void setModificar(String modificar) {
        this.modificar = modificar;
    }

    public List<Sicabis> getListaSipe() {
        return listaSipe;
    }

    public void setListaSipe(List<Sicabis> listaSipe) {
        this.listaSipe = listaSipe;
    }

    public boolean isBbotonbeneficiario() {
        return bbotonbeneficiario;
    }

    public void setBbotonbeneficiario(boolean bbotonbeneficiario) {
        this.bbotonbeneficiario = bbotonbeneficiario;
    }

    public List<Sicabis> getListaSipeBeneficiarios() {
        return listaSipeBeneficiarios;
    }

    public void setListaSipeBeneficiarios(List<Sicabis> listaSipeBeneficiarios) {
        this.listaSipeBeneficiarios = listaSipeBeneficiarios;
    }

    public Integer getEdad() {
        return edad;
    }

    public void setEdad(Integer edad) {
        this.edad = edad;
    }

}
