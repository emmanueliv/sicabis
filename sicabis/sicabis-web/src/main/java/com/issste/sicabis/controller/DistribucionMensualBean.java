/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.issste.sicabis.controller;

import com.issste.sicabis.ejb.DAO.TipoSolicitudDAO;
import com.issste.sicabis.ejb.ln.BitacoraTareaSerice;
import com.issste.sicabis.ejb.ln.DetalleOrdenSuministroService;
import com.issste.sicabis.ejb.ln.DpnInsumosService;
import com.issste.sicabis.ejb.ln.InsumosService;
import com.issste.sicabis.ejb.ln.PeriodoAreaService;
import com.issste.sicabis.ejb.ln.PlaneacionDetalleService;
import com.issste.sicabis.ejb.ln.PlaneacionService;
import com.issste.sicabis.ejb.ln.SolicitudesInsumosPacientesService;
import com.issste.sicabis.ejb.ln.SolicitudesService;
import com.issste.sicabis.ejb.ln.UnidadMedicaService;
import com.issste.sicabis.ejb.modelo.Area;
import com.issste.sicabis.ejb.modelo.BitacoraTareaEstatus;
import com.issste.sicabis.ejb.modelo.Insumos;
import com.issste.sicabis.ejb.modelo.PeriodoArea;
import com.issste.sicabis.ejb.modelo.Planeacion;
import com.issste.sicabis.ejb.modelo.PlaneacionDetalle;
import com.issste.sicabis.ejb.modelo.Solicitudes;
import com.issste.sicabis.ejb.modelo.SolicitudesInsumosPacientes;
import com.issste.sicabis.ejb.modelo.TipoSolicitud;
import com.issste.sicabis.ejb.modelo.UnidadesMedicas;
import com.issste.sicabis.ejb.modelo.Usuarios;
import com.issste.sicabis.ejb.service.silodisa.modelo.ExistenciaPorClaveCenadi;
import com.issste.sicabis.ejb.service.silodisa.service.AlertasOperativasService;
import com.issste.sicabis.ejb.service.silodisa.service.ExistenciaPorClaveUmusService;
import com.issste.sicabis.ejb.service.silodisa.service.ExistenciasPorClaveCenadiService;
import com.issste.sicabis.ejb.utils.ExistenciaPorClaveCenadiUtil;
import com.issste.sicabis.utils.InsumosAutoCompleteService;
import com.issste.sicabis.utils.Mensajes;
import com.issste.sicabis.utils.PlaneacionEstatusEnum;
import com.issste.sicabis.utils.Utilidades;
import java.io.IOException;
import java.io.Serializable;
import java.net.MalformedURLException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;
import org.primefaces.context.RequestContext;
import org.primefaces.event.CellEditEvent;

/**
 *
 * @author Toshiba Manolo
 */
@ManagedBean(name = "distribucionMensualBean")
@ViewScoped
public class DistribucionMensualBean implements Serializable {

    @EJB
    private DpnInsumosService dpnInsumosService;
    @EJB
    private BitacoraTareaSerice bitacoraTareaSerice;
    @EJB
    private ExistenciaPorClaveUmusService existenciaPorClaveUmusService;
    @EJB
    private ExistenciasPorClaveCenadiService existenciasPorClaveCenadiService;
    @EJB
    private ExistenciaPorClaveCenadiUtil existenciaPorClaveCenadiUtil;

    private List<PlaneacionDetalle> listplanDetalle;
    private List<PlaneacionDetalle> listplanDetalleFilter;
    private Planeacion planeacion;
    private BitacoraTareaEstatus bitacoraTareaEstatus;
    private Integer idPlaneacion;
    private Integer idTipoSolicitud;
    private Integer idAreaDistribucion;
    private Mensajes mensaje = new Mensajes();
    private Usuarios usuarios;
    private Utilidades util = new Utilidades();
    private Insumos insumo;
    private List<Insumos> listInsumos;
    private UnidadesMedicas unidadesMedicas;
    private TipoSolicitud tipoSolicitud;

    private Boolean mostrarPnlProyec;
    private Boolean desHabilitarBtnCargarDistAnterior;
    private Boolean desHabilitarBtnEliminarRegs;

    @EJB
    private InsumosService insumoService;
    @EJB
    private UnidadMedicaService unidadMedicaService;
    @EJB
    private PeriodoAreaService periodoAreaService;
    @EJB
    private SolicitudesService solicitudesServices;
    @EJB
    private SolicitudesInsumosPacientesService solicitudesInsumosPacientesService;
    @EJB
    private PlaneacionService planeacionService;
    @EJB
    private TipoSolicitudDAO tipoSolicitudDAO;
    @EJB
    private DetalleOrdenSuministroService detalleOrdenSuministroService;
    @EJB
    private PlaneacionDetalleService planeacionDetalleService;

    @ManagedProperty("#{insumosAutoCompleteService}")
    private InsumosAutoCompleteService serviceInsumo;

    public DistribucionMensualBean() {
        bitacoraTareaEstatus = new BitacoraTareaEstatus();
    }

    @PostConstruct
    public void init() {
        usuarios = (Usuarios) util.getSessionAtributte("usuario");
        idPlaneacion = (Integer) util.getContextAtributte("idPlaneacion");
        idTipoSolicitud = (Integer) util.getContextAtributte("idTipoSolicitud");
        idAreaDistribucion = (Integer) util.getContextAtributte("idAreaDistribucion");
        serviceInsumo.setListInsumos(insumoService.traeListaInsumos());
        mostrarPnlProyec = true;
        desHabilitarBtnCargarDistAnterior = false;
        desHabilitarBtnEliminarRegs = false;
        if (idPlaneacion == null) {
            listplanDetalle = new ArrayList<>();
            planeacion = new Planeacion();
            planeacion.setMesesProyeccion(1);
            planeacion.setFechaInicial(new Date());
            planeacion.setFechaFinal(new Date());
            planeacion.setIdTipoSolicitud(new TipoSolicitud(idTipoSolicitud));

            java.util.Date date = new Date();
            Calendar calPeriodo = Calendar.getInstance();
            calPeriodo.setTime(date);
            calPeriodo.add(Calendar.MONTH, 1);
            int month = calPeriodo.get(Calendar.MONTH);
            int year = calPeriodo.get(Calendar.YEAR);

            System.out.println("year" + year);
            System.out.println("month" + month);
            System.out.println("idTipoSolicitud:" + idTipoSolicitud);
            System.out.println("idAreaDistribucion" + idAreaDistribucion);
            tipoSolicitud = tipoSolicitudDAO.obtenerTipoSolPorIdTipoSolicitud(idTipoSolicitud).get(0);
            //List<Solicitudes> listaSolicitudes = solicitudesServices.buscaSolicitudesPorMesAnioTipoSolicitudArea(month + 1, year, idTipoSolicitud, idAreaDistribucion);
            List<String> tipoSolicitudes = null;
            if (tipoSolicitud.getIdTipoSolicitud() == PlaneacionEstatusEnum.IDSOLICITUD_MENSUAL.getValue()) {
                tipoSolicitudes = Arrays.asList(String.valueOf(PlaneacionEstatusEnum.IDSOLICITUD_MENSUAL.getValue()),
                        String.valueOf(PlaneacionEstatusEnum.IDSOLICITUD_EXTRAORDINARIA.getValue()));
            } else {
                tipoSolicitudes = Arrays.asList(String.valueOf(PlaneacionEstatusEnum.IDSOLICITUD_SOPORTE.getValue()));
            }
            List<Solicitudes> listaSolicitudes = solicitudesServices.buscaSolicitudesPorMesAnioTipoSolicitudesAreaEstatus(month + 1, year, tipoSolicitudes, idAreaDistribucion, PlaneacionEstatusEnum.SOLICITUD_TERMINADA.getValue());
            System.out.println("listaSolicitudes" + listaSolicitudes.size());
            if (listaSolicitudes == null && listaSolicitudes.size() <= 0) {
                mensaje.mensaje("No hay solicitudes correspondiente a este periodo", "amarillo");
            }
            for (Solicitudes solicitudes : listaSolicitudes) {
                for (SolicitudesInsumosPacientes detalleSol : solicitudes.getSolicitudesInsumosPacientesList()) {
                    PlaneacionDetalle plandetalle = new PlaneacionDetalle();
                    plandetalle.setIdUnidadesMedicas(detalleSol.getIdSolicitud().getIdUnidadesMedicas());
                    plandetalle.setIdInsumo(detalleSol.getIdInsumo());
                    String claveUmu = detalleSol.getIdSolicitud().getIdUnidadesMedicas().getClavePresupuestal();
                    plandetalle.setExistenciasCenadi(existenciaPorClaveUmusService.existenciaPorClaveUmusByUmuAndClave(detalleSol.getIdInsumo().getClave(), claveUmu));
                    plandetalle.setInsumosPendientesContratos(detalleOrdenSuministroService.cantidadPendientePorContratoInsumo(detalleSol.getIdInsumo().getIdInsumo()));
                    plandetalle.setIdPaciente(detalleSol.getIdPaciente());
                    plandetalle.setCantidadPromedioMensual(0);
                    plandetalle.setCantidadSolicitada(detalleSol.getCantidad());
                    plandetalle.setCantidadProyectada(0);
                    plandetalle.setNecesidadPeriodoSiguiente(dpnInsumosService.getBySumDpnByInsumo(detalleSol.getIdInsumo().getClave(), claveUmu));
                    plandetalle.setDistribucionAnterior(0);
                    Planeacion tmpPlaneacion = new Planeacion();
                    tmpPlaneacion.setIdTipoSolicitud(solicitudes.getIdTipoSolicitud());
                    tmpPlaneacion.setFechaInicial(planeacion.getFechaInicial());
                    tmpPlaneacion.setMesesProyeccion(planeacion.getMesesProyeccion());
                    plandetalle.setIdPlaneacion(tmpPlaneacion);
                    listplanDetalle.add(plandetalle);

                }
            }
        } else {
            planeacion = planeacionService.obtenerPlaneacionPorIdPlaneacion(idPlaneacion).get(0);
            tipoSolicitud = planeacion.getIdTipoSolicitud();
            listplanDetalle = planeacion.getPlaneacionDetalleList();
            for (PlaneacionDetalle itemPlanDetalle1 : listplanDetalle) {
                itemPlanDetalle1.setDistribucionAnterior(0);
            }
            if (listplanDetalle.size() > 0) {
                mostrarPnlProyec = true;
            }
        }
    }

    public Integer obtenerExistenciaCenadi(String clave, String umu) throws IOException, MalformedURLException {
        boolean existencia = existenciaPorClaveCenadiUtil.validaExistenciaClaveCenadi(clave);
        if (existencia == true) {
            Integer exitencia = existenciaPorClaveUmusService.existenciaPorClaveUmusByUmuAndClave(umu, clave);
            if (exitencia != null) {
                Integer totalExistencia = exitencia;
                return totalExistencia;
            }
        }
        return null;
    }

    public void guardarPlaneacion() {
        if (listplanDetalle.size() > 0) {

            if (idPlaneacion == null) {
                System.out.println("Entro a guardar planeacion");
                DateFormat df = new SimpleDateFormat("dd/MM/YYYY HH:mm:ss");
                String strFechaElaboracion = df.format(new Date());
                planeacion.setNumeroPlaneacion("pln-" + strFechaElaboracion);
                planeacion.setIdArea(new Area(idAreaDistribucion));
                planeacion.setFechaAlta(new Date());

                for (PlaneacionDetalle plandetalle : listplanDetalle) {
                    plandetalle.setIdPlaneacion(planeacion);
                }
                planeacion.setPlaneacionDetalleList(listplanDetalle);
                Planeacion pln = planeacionService.save(planeacion);
                bitacoraTareaEstatus.setDescripcion("Guardar planeacion:" + planeacion.getNumeroPlaneacion() + "");
                bitacoraTareaEstatus.setFecha(new Date());
                bitacoraTareaEstatus.setIdModulos(1);
                bitacoraTareaEstatus.setIdUsuarios(usuarios.getIdUsuario());
                bitacoraTareaEstatus.setIdTareaId(9);
                bitacoraTareaSerice.guardarEnBitacora(bitacoraTareaEstatus);
                pln.setNumeroPlaneacion("PLN-" + pln.getIdPlaneacion());
                planeacion = planeacionService.updateParcial(pln);
                idPlaneacion = pln.getIdPlaneacion();
                System.out.println("pln" + pln.getIdPlaneacion());
                mensaje.mensaje("La planeación se guardo correctamente", "verde");
            } else {
                actualizarPlaneacion();
            }

        } else {
            mensaje.mensaje("Debe agregar registros de distribución a la lista", "rojo");
        }

    }

    public void actualizarPlaneacion() {
        System.out.println("Entro a actualizarPlaneacion");
        planeacion.setFechaModificacion(new Date());

        for (PlaneacionDetalle plandetalle : listplanDetalle) {
            plandetalle.setIdPlaneacion(planeacion);
        }
        planeacion.setPlaneacionDetalleList(listplanDetalle);
        Planeacion pln = planeacionService.update(planeacion);
        bitacoraTareaEstatus.setDescripcion("Actualizar planeacion:" + planeacion.getNumeroPlaneacion() + "");
        bitacoraTareaEstatus.setFecha(new Date());
        bitacoraTareaEstatus.setIdModulos(1);
        bitacoraTareaEstatus.setIdUsuarios(usuarios.getIdUsuario());
        bitacoraTareaEstatus.setIdTareaId(9);
        bitacoraTareaSerice.guardarEnBitacora(bitacoraTareaEstatus);
        System.out.println("pln" + pln.getIdPlaneacion());
        mensaje.mensaje("La planeación se actualizo correctamente", "verde");
    }

    public void onCellEdit(CellEditEvent event) {
        PlaneacionDetalle selected = null;
        Object oldValue = event.getOldValue();
        Object newValue = event.getNewValue();
        if (newValue != null && !newValue.equals(oldValue)) {
            selected = listplanDetalle.get(event.getRowIndex());
            System.out.println("selected plan detalle" + selected.getCantidadProyectada());
        }

    }

    public List<Insumos> completeTheme(String query) {
        List<Insumos> allThemes = serviceInsumo.getListInsumos();
        List<Insumos> filteredThemes = new ArrayList();

        for (int i = 0; i < allThemes.size(); i++) {
            Insumos skin = allThemes.get(i);
            if (skin.getClave().toLowerCase().startsWith(query)) {
                filteredThemes.add(skin);
            }
        }

        return filteredThemes;
    }

    public List<UnidadesMedicas> completeUnidadMedica(String query) {
        List<UnidadesMedicas> allThemes = unidadMedicaService.obtenUnidadesMedicas();
        List<UnidadesMedicas> filteredThemes = new ArrayList();

        for (int i = 0; i < allThemes.size(); i++) {
            UnidadesMedicas skin = allThemes.get(i);

            filteredThemes.add(skin);

        }

        return filteredThemes;
    }

    public void agregarRegistro() {

        Boolean agregRegistro = true;
//        System.out.println("insumo: " + insumo.getClave());
//        System.out.println("unidad: " + unidadesMedicas.getNombre());
        if (insumo != null && unidadesMedicas != null) {
            for (PlaneacionDetalle plnDet : listplanDetalle) {
                if (plnDet.getIdInsumo().getIdInsumo() == insumo.getIdInsumo()
                        && plnDet.getIdUnidadesMedicas().getIdUnidadesMedicas() == unidadesMedicas.getIdUnidadesMedicas()) {
                    agregRegistro = false;
                }
            }
            if (agregRegistro) {
                PlaneacionDetalle plandetalle = new PlaneacionDetalle();
                plandetalle.setIdUnidadesMedicas(unidadesMedicas);
                plandetalle.setIdInsumo(insumo);

                try {
                    plandetalle.setExistenciasCenadi(obtenerExistenciaCenadi(insumo.getClave(), unidadesMedicas.getClaveUmu()));
                } catch (IOException ex) {
                    Logger.getLogger(DistribucionMensualBean.class.getName()).log(Level.SEVERE, null, ex);
                }
                plandetalle.setInsumosPendientesContratos(detalleOrdenSuministroService.cantidadPendientePorContratoInsumo(insumo.getIdInsumo()));
                plandetalle.setCantidadPromedioMensual(0);
                plandetalle.setCantidadSolicitada(0);
                plandetalle.setCantidadProyectada(0);
                plandetalle.setNecesidadPeriodoSiguiente(0);//DPN
                plandetalle.setDistribucionAnterior(0);
                listplanDetalle.add(plandetalle);
                mostrarPnlProyec = true;
//            insumo = new Insumos();
//            unidadesMedicas = new UnidadesMedicas();
                RequestContext.getCurrentInstance().execute("PF('wvTablaDistMensual').filter()");
            } else {
                mensaje.mensaje("El registro ya se encuentra en la lista", "amarillo");
            }
        } else {
            mensaje.mensaje("Para agregar un registro debe seleccionar un insumo y unidad médica", "rojo");
        }
    }

    public void eliminarRegistro(PlaneacionDetalle planeacionDetalle) {
        if (planeacionDetalle.getIdPlaneacionDetalle() != null) {
            planeacionDetalleService.deletePlaneacionDetallePorId(planeacionDetalle.getIdPlaneacionDetalle());
        }
        boolean remove = listplanDetalle.remove(planeacionDetalle);
        RequestContext.getCurrentInstance().execute("PF('wvTablaDistMensual').filter()");

    }

    public void eliminarTodo() {
        if (planeacion.getIdPlaneacion() != null) {
            planeacionDetalleService.deletePlaneacionDetallePorIdPlaneacion(planeacion.getIdPlaneacion());
        }
        listplanDetalle.clear();
        RequestContext.getCurrentInstance().execute("PF('wvTablaDistMensual').filter()");

    }

    public void cargarDistribucionAnterior() {
        System.out.println("cargarDistribucionAnterior");
        List<Planeacion> listPlaneacion = null;
        listplanDetalle.clear();
        Integer planeacionAnterior = planeacionService.obtenerPlaneacionesAnteriorPorTipoSolicitudArea(idTipoSolicitud, idAreaDistribucion);
        if (planeacionAnterior > 0) {
            listPlaneacion = planeacionService.obtenerPlaneacionPorIdPlaneacion(planeacionAnterior);
            planeacion = listPlaneacion.get(0);
            listplanDetalle = planeacion.getPlaneacionDetalleList();
        } else {
            mensaje.mensaje("No existe distribución anterior", "amarillo");
        }

    }

    public Integer insumoPendientePorContrato(Integer idInsumo) {
        Integer resultado = 0;
        //traerme ordenes de suministro por insumo de hoy en adellante por insumo 
        //traer   remisiones de esas ordenes de suministro restar cantidad de orden menos cantidad de remision
        // me trae insumos pendientes 

        return resultado;
    }

    public String generarProyeccionss() {
        util.setContextAtributte("mesesProyeccion", planeacion.getMesesProyeccion());
        return "proyeccionNecesidadInsumos.xhtml?faces-redirect=true";
    }

    public void generarProyeccion() {
        Calendar c = Calendar.getInstance();
        Date f;
        Integer mesFinal = 0;
        for (PlaneacionDetalle iteratorPd : listplanDetalle) {
            iteratorPd.getIdPlaneacion().setFechaInicial(planeacion.getFechaInicial());
            c.setTime(iteratorPd.getIdPlaneacion().getFechaInicial());
            mesFinal = iteratorPd.getIdPlaneacion().getMesesProyeccion();
            c.add(Calendar.MONTH, mesFinal);
            f = c.getTime();
            iteratorPd.getIdPlaneacion().setFechaFinal(f);
        }
        util.setContextAtributte("mesesProyeccion", planeacion.getMesesProyeccion());
        util.setContextAtributte("listplanDetProyec", listplanDetalle);
        RequestContext context = RequestContext.getCurrentInstance();
        context.execute("window.open('proyeccionNecesidadInsumos.xhtml', '_newtab')");

    }

    public int calculaEdad(Date fechaNacimiento) {
        int diff_year = 0;
        if (fechaNacimiento != null) {
            Calendar fechaNac = Calendar.getInstance();
            fechaNac.setTime(fechaNacimiento);
            Calendar today = Calendar.getInstance();

            diff_year = today.get(Calendar.YEAR) - fechaNac.get(Calendar.YEAR);
            int diff_month = today.get(Calendar.MONTH) - fechaNac.get(Calendar.MONTH);
            int diff_day = today.get(Calendar.DAY_OF_MONTH) - fechaNac.get(Calendar.DAY_OF_MONTH);

            //Si está en ese año pero todavía no los ha cumplido
            if (diff_month < 0 || (diff_month == 0 && diff_day < 0)) {
                diff_year = diff_year - 1; //no aparecían los dos guiones del postincremento :|
            }
        }
        return diff_year;
    }

    public void rangoFechas() {
        java.util.Date date = new Date();
        Calendar calPeriodo = Calendar.getInstance();
        calPeriodo.setTime(date);
        calPeriodo.add(Calendar.MONTH, planeacion.getMesesProyeccion());
        planeacion.setFechaInicial(date);
        planeacion.setFechaFinal(calPeriodo.getTime());

    }

    public List<PlaneacionDetalle> getListplanDetalle() {
        return listplanDetalle;
    }

    public void setListplanDetalle(List<PlaneacionDetalle> listplanDetalle) {
        this.listplanDetalle = listplanDetalle;
    }

    public List<PlaneacionDetalle> getListplanDetalleFilter() {
        return listplanDetalleFilter;
    }

    public void setListplanDetalleFilter(List<PlaneacionDetalle> listplanDetalleFilter) {
        this.listplanDetalleFilter = listplanDetalleFilter;
    }

    public Planeacion getPlaneacion() {
        return planeacion;
    }

    public void setPlaneacion(Planeacion planeacion) {
        this.planeacion = planeacion;
    }

    public Integer getIdPlaneacion() {
        return idPlaneacion;
    }

    public void setIdPlaneacion(Integer idPlaneacion) {
        this.idPlaneacion = idPlaneacion;
    }

    public Integer getIdTipoSolicitud() {
        return idTipoSolicitud;
    }

    public void setIdTipoSolicitud(Integer idTipoSolicitud) {
        this.idTipoSolicitud = idTipoSolicitud;
    }

    public Integer getIdAreaDistribucion() {
        return idAreaDistribucion;
    }

    public void setIdAreaDistribucion(Integer idAreaDistribucion) {
        this.idAreaDistribucion = idAreaDistribucion;
    }

    public Insumos getInsumo() {
        return insumo;
    }

    public void setInsumo(Insumos insumo) {
        this.insumo = insumo;
    }

    public UnidadesMedicas getUnidadesMedicas() {
        return unidadesMedicas;
    }

    public void setUnidadesMedicas(UnidadesMedicas unidadesMedicas) {
        this.unidadesMedicas = unidadesMedicas;
    }

    public List<Insumos> getListInsumos() {
        return listInsumos;
    }

    public void setListInsumos(List<Insumos> listInsumos) {
        this.listInsumos = listInsumos;
    }

    public InsumosAutoCompleteService getServiceInsumo() {
        return serviceInsumo;
    }

    public void setServiceInsumo(InsumosAutoCompleteService serviceInsumo) {
        this.serviceInsumo = serviceInsumo;
    }

    public TipoSolicitud getTipoSolicitud() {
        return tipoSolicitud;
    }

    public void setTipoSolicitud(TipoSolicitud tipoSolicitud) {
        this.tipoSolicitud = tipoSolicitud;
    }

    public Boolean getMostrarPnlProyec() {
        return mostrarPnlProyec;
    }

    public void setMostrarPnlProyec(Boolean mostrarPnlProyec) {
        this.mostrarPnlProyec = mostrarPnlProyec;
    }

    public Boolean getDesHabilitarBtnCargarDistAnterior() {
        return desHabilitarBtnCargarDistAnterior;
    }

    public void setDesHabilitarBtnCargarDistAnterior(Boolean desHabilitarBtnCargarDistAnterior) {
        this.desHabilitarBtnCargarDistAnterior = desHabilitarBtnCargarDistAnterior;
    }

    public Boolean getDesHabilitarBtnEliminarRegs() {
        return desHabilitarBtnEliminarRegs;
    }

    public void setDesHabilitarBtnEliminarRegs(Boolean desHabilitarBtnEliminarRegs) {
        this.desHabilitarBtnEliminarRegs = desHabilitarBtnEliminarRegs;
    }

}
