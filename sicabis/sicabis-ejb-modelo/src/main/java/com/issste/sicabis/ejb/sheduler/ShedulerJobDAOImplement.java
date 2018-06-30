/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.issste.sicabis.ejb.sheduler;

import com.issste.sicabis.ejb.ln.AlertasCorreoService;
import com.issste.sicabis.ejb.ln.AlertasEnvioService;
import com.issste.sicabis.ejb.ln.BitacoraTareaSerice;
import com.issste.sicabis.ejb.ln.ConfiguraDpnService;
import com.issste.sicabis.ejb.ln.ConsumoDiarioSiamService;
import com.issste.sicabis.ejb.ln.ContactosAlertasDpnService;
import com.issste.sicabis.ejb.ln.DpnInsumosService;
import com.issste.sicabis.ejb.ln.DpnService;
import com.issste.sicabis.ejb.ln.EstatusService;
import com.issste.sicabis.ejb.ln.InsumosService;
import com.issste.sicabis.ejb.ln.OrdenSuministroService;
import com.issste.sicabis.ejb.ln.PeriodoMesService;
import com.issste.sicabis.ejb.ln.PorcentajeDelegacionService;
import com.issste.sicabis.ejb.ln.UsuariosService;
import com.issste.sicabis.ejb.modelo.AlertasCorreo;
import com.issste.sicabis.ejb.modelo.AlertasEnvio;
import com.issste.sicabis.ejb.modelo.BitacoraTareaEstatus;
import com.issste.sicabis.ejb.modelo.ConfiguraDpn;
import com.issste.sicabis.ejb.modelo.ContactosAlertasDpn;
import com.issste.sicabis.ejb.modelo.Dpn;
import com.issste.sicabis.ejb.modelo.DpnInsumos;
import com.issste.sicabis.ejb.modelo.Estatus;
import com.issste.sicabis.ejb.modelo.OrdenSuministro;
import com.issste.sicabis.ejb.modelo.PeriodoMes;
import com.issste.sicabis.ejb.modelo.PorcentajeDelegacion;
import com.issste.sicabis.ejb.modelo.UsuarioPerfil;
import com.issste.sicabis.ejb.modelo.UsuariosTipoUsuarios;
import com.issste.sicabis.ejb.service.silodisa.controller.AlertasOperativasController;
import com.issste.sicabis.ejb.service.silodisa.controller.CatalogoInsumosController;
import com.issste.sicabis.ejb.service.silodisa.controller.CatalogoUnidadesMedicasController;
import com.issste.sicabis.ejb.service.silodisa.controller.ClavesPorCodigoBarrasController;
import com.issste.sicabis.ejb.service.silodisa.controller.DetalleSalidasUmuGuiaDistribucionController;
import com.issste.sicabis.ejb.service.silodisa.controller.EntradasMymcqCenadiController;
import com.issste.sicabis.ejb.service.silodisa.controller.ExistenciaPorClaveUmusController;
import com.issste.sicabis.ejb.service.silodisa.controller.ExistenciaReservaClaveCenadiController;
import com.issste.sicabis.ejb.service.silodisa.controller.ExistenciasPorClaveCenadiController;
import com.issste.sicabis.ejb.service.silodisa.controller.ExistenciasUMUsProgramasController;
import com.issste.sicabis.ejb.service.silodisa.controller.MapaEjecutivoDispDelegacionesController;
import com.issste.sicabis.ejb.service.silodisa.controller.MapaEjecutivoDispG40Controller;
import com.issste.sicabis.ejb.service.silodisa.controller.MapaEjecutivoDisponibilidadEstadosController;
import com.issste.sicabis.ejb.service.silodisa.controller.RemisionesElectronicasEntregasUmuController;
import com.issste.sicabis.ejb.service.silodisa.controller.SalidasCenadiUmuGuiaDeDistribucionController;
import com.issste.sicabis.ejb.service.silodisa.controller.SeguimientoSalidasUmuInternoController;
import com.issste.sicabis.ejb.service.silodisa.controller.SeguimientoSalidasUmuTransitoController;
import com.issste.sicabis.ejb.service.silodisa.modelo.MapaEjecutivoDispDelegaciones;
import com.issste.sicabis.ejb.service.silodisa.modelo.MapaEjecutivoDispG40;
import com.issste.sicabis.ejb.service.silodisa.service.ExistenciaPorClaveUmusService;
import com.issste.sicabis.ejb.service.silodisa.service.MapaEjecutivoDispDelegacionesService;
import com.issste.sicabis.ejb.service.silodisa.service.MapaEjecutivoDispG40Service;
import com.issste.sicabis.ejb.utils.AlertasUtil;
import com.issste.sicabis.ejb.utils.MailInsumosPendientes;
import com.issste.sicabis.ejb.utils.OrdenesSuministroAuto;
import com.issste.sicabis.ejb.utils.SendMail;
import com.issste.sicabis.ejb.utils.Utilidades;
import java.io.IOException;
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
import javax.ejb.EJB;
import javax.ejb.Schedule;
import javax.ejb.Singleton;

/**
 *
 * @author 9RZCBG2
 */
@Singleton
public class ShedulerJobDAOImplement implements ShedulerJobDAO {

    @EJB
    private PorcentajeDelegacionService porcentajeDelegacionService;

    @EJB
    private MapaEjecutivoDisponibilidadEstadosController mapaEjecutivoDisponibilidadEstadosController;

    @EJB
    private BitacoraTareaSerice bitacoraTareaSerice;

    @EJB
    private MapaEjecutivoDispDelegacionesService mapaEjecutivoDispDelegacionesService;

    @EJB
    private MapaEjecutivoDispG40Service mapaEjecutivoDispG40Service;

    @EJB
    private ContactosAlertasDpnService contactosAlertasDpnService;

    @EJB
    private UsuariosService usuariosService;

    @EJB
    private OrdenSuministroService ordenSuministroService;

    @EJB
    private EstatusService estatusService;

    @EJB
    private InsumosService insumosService;

    @EJB
    private AlertasCorreoService alertasCorreoService;

    @EJB
    private ConsumoDiarioSiamService consumoDiarioSiamService;

    @EJB
    private ExistenciaPorClaveUmusService existenciaPorClaveUmusService;

    @EJB
    private OrdenesSuministroAuto ordenesSuministroAuto;

    @EJB
    private AlertasEnvioService alertasEnvioService;

    @EJB
    private PeriodoMesService periodoMesService;

    @EJB
    private DpnInsumosService dpnInsumosService;

    @EJB
    private DpnService dpnService;

    @EJB
    private ConfiguraDpnService configuraDpnService;

    @EJB
    private SalidasCenadiUmuGuiaDeDistribucionController salidasCenadiUmuGuiaDeDistribucionController;

    @EJB
    private CatalogoUnidadesMedicasController catalogoUnidadesMedicasController;

    @EJB
    private ExistenciasPorClaveCenadiController existenciasPorClaveCenadiController;

    @EJB
    private SeguimientoSalidasUmuInternoController seguimientoSalidasUmuInternoController;

    @EJB
    private SeguimientoSalidasUmuTransitoController seguimientoSalidasUmuTransitoController;

    @EJB
    private RemisionesElectronicasEntregasUmuController remisionesElectronicasEntregasUmuController;

    @EJB
    private MapaEjecutivoDispG40Controller mapaEjecutivoDispG40Controller;

    @EJB
    private MapaEjecutivoDispDelegacionesController mapaEjecutivoDispDelegacionesController;

    @EJB
    private ExistenciasUMUsProgramasController existenciasUMUsProgramasController;

    @EJB
    private ExistenciaReservaClaveCenadiController existenciaReservaClaveCenadiController;

    @EJB
    private ExistenciaPorClaveUmusController existenciaPorClaveUmusController1;

    @EJB
    private DetalleSalidasUmuGuiaDistribucionController detalleSalidasUmuGuiaDistribucionController;

    @EJB
    private ClavesPorCodigoBarrasController clavesPorCodigoBarrasController;

    @EJB
    private AlertasOperativasController alertasOperativasController;

    @EJB
    private CatalogoInsumosController catalogoInsumosController;

    @EJB
    private ExistenciaPorClaveUmusController existenciaPorClaveUmusController;

    @EJB
    private EntradasMymcqCenadiController entradasMymcqCenadiController;

    //Objects
    private MailInsumosPendientes MailInsumosPendientes = new MailInsumosPendientes();
    private AlertasUtil alertasUtil;
    private Utilidades util = new Utilidades();

    @Override
    //@Schedule(second = "0", minute = "28", hour = "11")
    public void execute() {
        try {
            System.out.println("envioCorreosDisponibilidad------->" + new Date());
            //SimpleDateFormat format = new SimpleDateFormat("yyyy/MM/dd");
            //Calendar c = Calendar.getInstance(Locale.ENGLISH);
            //List<ConfiguraDpn> cd = configuraDpnService.getAllByActivo(1);
            //List<PorcentajeDelegacion> listDispEstados = new ArrayList<>();
            //Integer diasHabiles = cd.get(0).getNumDias();
            //Date fechaActualizada;
            //fechaActualizada = util.sumarRestarDiasFecha(format.parse("" + c.get(Calendar.YEAR) + "/ " + c.get(Calendar.MONTH) + "/01"), diasHabiles);
            Integer diaActual = 1;
            List<MapaEjecutivoDispDelegaciones> listDisponiblidad = new ArrayList<>();
            List<MapaEjecutivoDispG40> listDisponiblidadG40 = new ArrayList<>();
            SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
            String fecha = sdf.format(new Date());
            List<ContactosAlertasDpn> listContactosDPN = new ArrayList<>();
            if (diaActual == 1) {
                listContactosDPN = contactosAlertasDpnService.getAllContactos(1);
                System.out.println("listContactosDPN correo -------->" + listContactosDPN);
                for (ContactosAlertasDpn iterator : listContactosDPN) {
                    if (iterator.getMapas() == 0) {
                        System.out.println("entre anterior correos");
//                        if (iterator.getIdUnidadMedica() == null) {
//                            listDisponiblidad = mapaEjecutivoDispDelegacionesService.getByDelegacion(iterator.getIdDelegacion().getNombreDelegacion());
//                            String mensaje = "Buen dÃ­a <br> \n"
//                                    + "Se envÃ­a el detalle de la disponibilidad por la delegaciÃ³n " + iterator.getIdDelegacion().getNombreDelegacion() + ".<br> \n"
//                                    + "Disponibilidad : " + listDisponiblidad.get(0).getDisponibilidad() + " \n"
//                                    + ",Clave disponibles : " + listDisponiblidad.get(0).getClavesDisponibles() + " \n"
//                                    + ",Clave ingresada : " + listDisponiblidad.get(0).getClavesAutorizadas() + " \n";
//                            String correo = "<center><img src='http://www2.issste.gob.mx:8080/images/logos/issste2013-header_2.jpg' alt='Header' /></center>\n"
//                                    + "<div>\n"
//                                    + "<center>\n"
//                                    + "<strong><h3>SICABIS</h3>\n"
//                                    + "<h2>" + fecha + "</h2></strong>\n"
//                                    + "</center>\n"
//                                    + "<div style='margin:20px 35px 25px 30px;' align='justify'>\n"
//                                    + "<p>\n"
//                                    + "Estimado(a) \n"
//                                    + "<br><br>\n"
//                                    + mensaje
//                                    + "</p>\n"
//                                    + "</div>\n"
//                                    + "</div>\n"
//                                    + "<center><img src='http://www2.issste.gob.mx:8080/images/logos/issste2013-footer.jpg' alt='Footer' /></center>";
//                            com.issste.sicabis.ejb.utils.SendMail.writeMailCorreo(iterator, "Disponibilidad por delegaciÃ³n", correo);
//                        } else if (iterator.getIdDelegacion() != null && iterator.getIdUnidadMedica() != null) {
//                            listDisponiblidadG40 = mapaEjecutivoDispG40Service.getByClaveUnidad(iterator.getIdUnidadMedica().getNombre());
//                            String mensaje = "Buen dÃ­a <br> \n"
//                                    + "Se envÃ­a el detalle de la disponibilidad G40 por la unidad mÃ©dica " + iterator.getIdUnidadMedica().getNombre() + ". <br> \n"
//                                    + "Disponibilidad : " + listDisponiblidadG40.get(0).getDisponibilidad().intValueExact() + " \n"
//                                    + ",Clave disponibles : " + listDisponiblidadG40.get(0).getClavesDisponibles() + " \n"
//                                    + ",Clave ingresada : " + listDisponiblidadG40.get(0).getClavesAutorizadas() + " \n";
//                            String correo = "<center><img src='http://www2.issste.gob.mx:8080/images/logos/issste2013-header_2.jpg' alt='Header' /></center> \n"
//                                    + "<div>\n"
//                                    + "<center>\n"
//                                    + "<strong><h3>SICABIS</h3>\n"
//                                    + "<h2>" + fecha + "</h2></strong>\n"
//                                    + "</center>\n"
//                                    + "<div style='margin:20px 35px 25px 30px;' align='justify'>\n"
//                                    + "<p>\n"
//                                    + "Estimado(a) \n"
//                                    + "<br><br>\n"
//                                    + mensaje
//                                    + "</p>\n"
//                                    + "</div>\n"
//                                    + "</div>\n"
//                                    + "<center><img src='http://www2.issste.gob.mx:8080/images/logos/issste2013-footer.jpg' alt='Footer' /></center>";
//                            com.issste.sicabis.ejb.utils.SendMail.writeMailCorreo(iterator, "Disponibilidad G40", correo);
//                        }
                    } else {
                        String mensaje = "Buenos días, anexo me permito reenviar a Usted la información correspondiente a los Niveles de Disponibilidad de insumos para la salud del Instituto, los cuales se generan en forma automática a través de la plataforma informática del Sistema Integral de la Cadena de Abasto de Insumos para la Salud. \n"
                                + "<br><br> \n";
                        if (iterator.getEstados() == 1) {
                            mensaje = mensaje + "<strong>\n"
                                    + "Disponibilidad por Estados: Considera todas las Unidades de Medicina Familiar incluyendo Hospitales Generales, Hospitales Regionales y el Centro Médico Nacional 20 de noviembre.\n"
                                    + "</strong>\n"
                                    + "<h3><a href='http://192.168.2.8:8383/sicabis-web/vistas/indicadores/dispEstados.xhtml'>Ver mapa Estados</a></h3>\n"
                                    + "<br>\n";
                        }
                        if (iterator.getDelegaciones() == 1) {
                            mensaje = mensaje + "<strong>\n"
                                    + "Disponibilidad por Delegaciones: Considera Únicamente Unidades de Medicina Familiar. No incluye Hospitales Generales, Hospitales Regionales y Centro Médico Nacional 20 de Noviembre.\n"
                                    + "</strong>\n"
                                    + "<h3><a href='http://192.168.2.8:8383/sicabis-web/vistas/indicadores/dispDelegaciones.xhtml'>Ver mapa Delegaciones</a></h3>\n"
                                    + "<br>\n";
                        }
                        if (iterator.getG40() == 1) {
                            mensaje = mensaje + "<strong>\n"
                                    + "Disponibilidad G40: Considera Únicamente Hospitales Generales, Hospitales Regionales y Centro Médico Nacional 20 de Noviembre.\n"
                                    + "</strong>\n"
                                    + "<h3><a href='http://192.168.2.8:8383/sicabis-web/vistas/indicadores/dispG40.xhtml'>Ver mapa G40</a></h3>\n"
                                    + "<br>\n";
                        }

                        String correo = "<center><img src='http://www2.issste.gob.mx:8080/images/logos/issste2013-header_2.jpg' alt='Header' /></center>\n"
                                + "<div>\n"
                                + "<center>\n"
                                + "<strong><h3>SICABIS</h3>\n"
                                + "<h2>" + fecha + "</h2></strong>\n"
                                + "</center>\n"
                                + "<div style='margin:20px 35px 25px 30px;' align='justify'>\n"
                                + "<p>\n"
                                + "Estimado(a) \n"
                                + "<br><br>\n"
                                + mensaje
                                + "</p>\n"
                                + "</div>\n"
                                + "</div>\n"
                                + "<center><img src='http://www2.issste.gob.mx:8080/images/logos/issste2013-footer.jpg' alt='Footer' /></center>";
                        System.out.println("mensaje correos --------->" + correo);
                        com.issste.sicabis.ejb.utils.SendMail.writeMailCorreo(iterator, "Niveles de Disponibilidad de Insumos para la Salud", correo);
                    }
                }
            }
            System.out.println("sali envioCorreosDisponibilidad------->" + new Date());
        } catch (Exception ex) {
            System.out.println("Error en web service correos: " + ex);
            Logger.getLogger(ShedulerJobDAOImplement.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    //@Schedule(second = "0", minute = "00", hour = "02")
    public void executeAlertas() {
        try {
            Calendar cal = Calendar.getInstance();
            cal.setTime(new Date());
            if (cal.get(Calendar.DAY_OF_WEEK) != 1 && cal.get(Calendar.DAY_OF_WEEK) != 7) {
                generaDPN();
                //ejecutaAlertaDPN();
//                ejecutaAlerta();
            }
        } catch (Exception ex) {
            Logger.getLogger(ShedulerJobDAOImplement.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    //@Schedule(second = "0", minute = "25", hour = "11")
    public void executeWebServiceMapas() {
        try {
            System.out.println("envio mapas------->" + new Date());
            mapaEjecutivoDisponibilidadEstadosController.obtenerPorcentajePorEstado(1);
            mapaEjecutivoDispDelegacionesController.obtenerMapaEjecutivoDispDelegacion(1);
            mapaEjecutivoDispG40Controller.obtenerMapaEjecutivoDispG40(1);
            System.out.println("sali envio mapas------->" + new Date());
        } catch (Exception ex) {
            System.out.println("Error en web service mapas: " + ex);
        }
    }

    @Override
    //@Schedule(second = "0", minute = "10", hour = "12")
    public void executeWebService() {
        System.out.println("WebService in");
        try {
            Calendar cal = Calendar.getInstance();
            cal.setTime(new Date());
            if (cal.get(Calendar.DAY_OF_WEEK) != 1 && cal.get(Calendar.DAY_OF_WEEK) != 7) {
                BitacoraTareaEstatus bitacora = new BitacoraTareaEstatus();
                bitacora.setFecha(new Date());
                bitacora.setIdEstatus(0);
                bitacora.setIdModulos(0);
                bitacora.setDescripcion("Actualizar Web service inicio");
                bitacora.setIdUsuarios(0);
                bitacora.setIdTareaId(0);
                bitacoraTareaSerice.guardarEnBitacora(bitacora);
                existenciasPorClaveCenadiController.obtenerExistencias(1);
                catalogoInsumosController.obtenerCatalogoInsumos();
                catalogoUnidadesMedicasController.obtnerCatalogoUnidadesMedicas();
                clavesPorCodigoBarrasController.obtenerClavesPorCodigoBarras();
                entradasMymcqCenadiController.obtenerExistenciaReservaClaveCenadi(1);
                existenciaReservaClaveCenadiController.obtenerExistenciaReservaClaveCenadi(1);
                detalleSalidasUmuGuiaDistribucionController.obtenerDetalleSalidasUmuGuiaDistribucion(1);
                remisionesElectronicasEntregasUmuController.obtenerDetalleSalidasUmuGuiaDistribucion(1);
                salidasCenadiUmuGuiaDeDistribucionController.obtenerSalidasCenadiUmuGuiaDeDistribucion(1);
                seguimientoSalidasUmuTransitoController.obtenerSeguimientoSalidasUmuTransito(1);
                seguimientoSalidasUmuInternoController.obtenerSalidasCenadiUmuGuiaDeDistribucion(1);
                existenciasUMUsProgramasController.obtenerExistenciasUMUsProgramas(1);
                //existenciaPorClaveUmusController.obtenerTodosExistenciaPorClaveUmus(1);
                //alertasOperativasController.obtenerTodasAlertasOperativas(1);

//                mapaEjecutivoDispDelegacionesController.obtenerMapaEjecutivoDispDelegacion(0);
//                mapaEjecutivoDispG40Controller.obtenerMapaEjecutivoDispG40(0);
//                existenciasPorClaveCenadiController.obtenerExistencias(0);
//                existenciaReservaClaveCenadiController.obtenerExistenciaReservaClaveCenadi(0);
//                entradasMymcqCenadiController.obtenerExistenciaReservaClaveCenadi(0);
//                detalleSalidasUmuGuiaDistribucionController.obtenerDetalleSalidasUmuGuiaDistribucion(0);
//                existenciasUMUsProgramasController.obtenerExistenciasUMUsProgramas(0);
//                remisionesElectronicasEntregasUmuController.obtenerDetalleSalidasUmuGuiaDistribucion(0);
//                salidasCenadiUmuGuiaDeDistribucionController.obtenerSalidasCenadiUmuGuiaDeDistribucion(0);
//                seguimientoSalidasUmuTransitoController.obtenerSeguimientoSalidasUmuTransito(0);
//                seguimientoSalidasUmuInternoController.obtenerSalidasCenadiUmuGuiaDeDistribucion(0);
//                existenciaPorClaveUmusController.obtenerTodosExistenciaPorClaveUmus(0);
//                alertasOperativasController.obtenerTodasAlertasOperativas(0);
                //ordenesSuministroAutoExecute();
                bitacora = new BitacoraTareaEstatus();
                bitacora.setFecha(new Date());
                bitacora.setIdEstatus(0);
                bitacora.setIdModulos(0);
                bitacora.setDescripcion("Actualizar Web service final");
                bitacora.setIdUsuarios(0);
                bitacora.setIdTareaId(0);
                bitacoraTareaSerice.guardarEnBitacora(bitacora);
            }
        } catch (Exception ex) {
            System.out.println("Error en web service: " + ex);
        }
    }

    @Override
    public void SendMailInsumosPendientesExecute() {
        MailInsumosPendientes.sendMailInsumosPendientesPorSuministrar();
    }

    @Override
    public void ejecutaAlerta() {
        List<ConfiguraDpn> cd = configuraDpnService.getAllByActivo(1);
        Integer diasHabiles = cd.get(0).getNumDias();
        SimpleDateFormat format = new SimpleDateFormat("yyyy/MM/dd");
        Calendar c = Calendar.getInstance(Locale.ENGLISH);
        Integer diaActual = c.get(Calendar.DAY_OF_MONTH);
        try {
            Date fechaActualizada = util.sumaDiasHabiles(format.parse("" + c.get(Calendar.YEAR) + "/ " + c.get(Calendar.MONTH) + "/01"), diasHabiles);
        } catch (ParseException ex) {
            Logger.getLogger(ShedulerJobDAOImplement.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

//    @Override
//    public void ejecutaAlerta() {
//        ConfiguraDpn cd = configuraDpnService.getAllByActivo(1).get(0);
//        Calendar cal = Calendar.getInstance();
//        Date today = cal.getTime();
//        Date fechaInicio = null;
//        Date fechaModificada = null;
//        Date fecIni = null;
//        Date fecFin = null;
//        List<DpnInsumos> listaDpnInsumos = null;
//        AlertasEnvio ae = null;
//        AlertasEnvio aeAux = null;
//        int diaAux = 0;
//        boolean bandera = true;
//        Dpn dpn = dpnService.getUltimaAutorizada();
//        listaDpnInsumos = dpn.getDpnInsumosList();
//        if (today.getDate() == 1) {
//            alertasEnvioService.updateAllByActivo();
//        }
//        if (today.getDate() >= cd.getDiaInicio().intValue()) {
//            for (DpnInsumos diIterator : listaDpnInsumos) {
//                aeAux = new AlertasEnvio();
//                ae = new AlertasEnvio();
//                ae.setAnio(new Date().getYear());
//                ae.setMes(new Date().getMonth() - 1);
//                ae.setClaveInsumo(diIterator.getClaveInsumo());
//                ae.setClaveUnidad(diIterator.getClaveUnidad());
//                aeAux = alertasEnvioService.getByAnioMesClaveUnidad(ae);
//                if (aeAux != null && aeAux.getActivo() == 1) {
//                    int auxDay = today.getDate();
//                    if (today.getDate() == cd.getDiaInicio()) {
//                        alertasCalculo(diIterator, cd.getMinPiezas(), cd.getUrlLink(), cd.getDiaInicio(), aeAux);
//                    } else {
//                        bandera = true;
//                        diaAux = cd.getDiaInicio();
//                        while (bandera) {
//                            fechaInicio = new Date(new Date().getYear(), new Date().getMonth(), diaAux);
//                            int diaInicio = cd.getDiaInicio();
//                            while (diaInicio < 30) {
//                                if (today.getDate() == diaInicio) {
//                                    fechaModificada = util.sumaDiasHabiles(fechaInicio, cd.getNumDias());
//                                    break;
//                                }
//                                diaInicio = diaInicio + cd.getNumDias();
//                                if (today.getDate() == diaInicio) {
//                                    fechaModificada = util.sumaDiasHabiles(fechaInicio, diaInicio);
//                                    break;
//                                }
//                            }
//                            int mesModificado = 0;
//                            if (fechaModificada != null) {
//                                mesModificado = fechaModificada.getMonth();
//                            }
//                            if (fechaInicio.getMonth() == mesModificado) {
//                                today = new Date(new Date().getYear(), new Date().getMonth(), today.getDate());
//                                if (today.compareTo(fechaModificada) == 0) {
//                                    alertasCalculo(diIterator, cd.getMinPiezas(), cd.getUrlLink(), cd.getDiaInicio(), aeAux);
//                                } else if (today.before(fechaModificada)) {
//                                    bandera = false;
//                                }
//                            } else {
//                                bandera = false;
//                            }
//                        }
//                    }
//                } else {
//                    alertasCalculo(diIterator, cd.getMinPiezas(), cd.getUrlLink(), cd.getDiaInicio(), aeAux);
//                }
//            }
//        }
//    }
    @Override
    public void ordenesSuministroAutoExecute() {
        try {
            ordenesSuministroAuto.generacionOrdenesAutomaticas();
        } catch (IOException ex) {
            Logger.getLogger(ShedulerJobDAOImplement.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    //Metodo aux para alertas
    //    public boolean alertasCalculo(DpnInsumos diIterator, Integer minimoPiezas, String url, Integer diaInicio, AlertasEnvio aeAux) {
    //        if (diIterator.getPiezasDpn() > minimoPiezas) {
    //            List<DpnInsumos> tempList = new ArrayList<>();
    //            tempList = existenciaPorClaveUmusService.getByUMUClaveFecha(diIterator.getClaveInsumo(), diIterator.getClaveUnidad(), new Date());
    //            int existenciasUmusClave = 0;
    //            if (tempList != null) {
    //                existenciasUmusClave = tempList.get(0).getExistenciasCenadi();
    //            }
    //
    //            int consumoClaveUmu = consumoDiarioSiamService.sumaConsumo(diIterator.getClaveInsumo(), diIterator.getClaveUnidad(), new Date(), new Date(new Date().getYear(), new Date().getMonth() + 1, 1));
    //            int pzConsumoDias = diaInicio - 30;
    //            consumoClaveUmu = consumoClaveUmu / 30;
    //            consumoClaveUmu = consumoClaveUmu * pzConsumoDias;
    //            if (existenciasUmusClave <= consumoClaveUmu) {
    //                List<AlertasCorreo> alertasCorreo = new ArrayList<>();
    //                alertasCorreo = alertasCorreoService.obtenerListaAlertasCorreo(diIterator.getClaveUnidad());
    //                if (aeAux == null) {
    //                    aeAux = new AlertasEnvio();
    //                    aeAux.setActivo(1);
    //                    aeAux.setAnio(new Date().getYear());
    //                    aeAux.setMes(new Date().getMonth() - 1);
    //                    aeAux.setClaveInsumo(diIterator.getClaveInsumo());
    //                    aeAux.setClaveUnidad(diIterator.getClaveUnidad());
    //                    aeAux.setDpn(new BigDecimal(diIterator.getPiezasDpn()));
    //                    aeAux.setConsumo(new BigDecimal(consumoClaveUmu));
    //                    aeAux.setExistencia(new BigDecimal(existenciasUmusClave));
    //                    aeAux.setIdInsumo(insumosService.obtieneByClave(diIterator.getClaveInsumo()));
    //                } else {
    //                    aeAux.setDpn(new BigDecimal(diIterator.getPiezasDpn()));
    //                    aeAux.setConsumo(new BigDecimal(consumoClaveUmu));
    //                    aeAux.setExistencia(new BigDecimal(existenciasUmusClave));
    //                }
    //                alertasEnvioService.guardar(aeAux);
    ////                for (AlertasCorreo iterator : alertasCorreo) {
    ////                    String mensaje = "Buen dia\n"
    ////                            + "Se sugiere de pedir " + consumoClaveUmu + "piezas para suministro del insumo " + diIterator.getClaveInsumo() + " conforme a la DPN del periodo en curso.\n"
    ////                            + "Usuario temporal: " + iterator.getIdUsuario().getIdUsuario() + "\n"
    ////                            + "ContraseÃ±a temporal: " + iterator.getIdUsuario().getContrasenia() + "\n"
    ////                            + "URL: " + url + "";
    ////                    SendMail.writeMail(iterator.getIdUsuario(), "DPN sugerida", mensaje);
    ////                }
    //            }
    //        }
    //        return true;
    ////                Periodo por mes en caso de consutar por periodo;
    ////                PeriodoMes periodoMes = periodoMesService.getPeriodoActivo(today.getYear(), today.getMonth() - 1);
    ////                if (periodoMes.getActivo() == 0) {
    ////                    fecIni = periodoMes.getFechaInicial();
    ////                    fecFin = periodoMes.getFechaFinal();
    ////                } else if (periodoMes.getActivo() == 1) {
    ////                    fecIni = periodoMes.getFechaFinal();
    ////                    fecFin = new Date();
    ////                }
    //    }

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
            if (diaActual == 1) {
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
//                            ae.set(0);
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
                        String mensaje = "Buen día\n"
                                + "Se envía usuario para sugerencia DPN del periodo en curso.\n"
                                + "Usuario: " + iterator.getIdUsuario().getUsuario() + "\n"
                                + ",Contraseña: SICABIS\n"
                                + ",URL: " + url + "";
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

    public void generaDPN() {
        BigDecimal totalPiezas = BigDecimal.ZERO;
        Date fechaCorte = new Date();
        PeriodoMes pm = periodoMesService.getByFechaCorteMod(fechaCorte);
        List<DpnInsumos> listaDpnInsumo = null;
        if (pm != null) {
            Dpn dpn = dpnService.getDpnPrevio();
            Date fechaInicio;
            Date fechaFin = fechaCorte;
            PeriodoMes periodoMes = periodoMesService.getPeriodoActivo(fechaCorte.getYear(), fechaCorte.getMonth() + 1);
            fechaInicio = periodoMes.getFechaInicial();
            fechaFin = fechaCorte;
            String mesDpn = util.getNameByMonth(periodoMes.getIdPeriodoMes() + 1);
            Estatus estatus = null;
            boolean bprimera = false;
            boolean bcontinua = true;

            if (dpn == null) {
                dpn = new Dpn();
                estatus = estatusService.getRemisionEstatus(211);
                dpn.setActivo(0);
                dpn.setIdEstatus(estatus);
                dpn.setIdPeriodoMes(new PeriodoMes(pm.getIdPeriodoMes()));
                dpn.setTotalPiezasDpn(BigDecimal.ZERO);
                dpn.setUsuarioAlta("SYSTEM");
                dpn.setFechaAlta(new Date());
                dpn.setFecha(new Date());
                dpn.setDpnMes(mesDpn + " (PREVIO)");
                dpnService.guardaActualiza(dpn);
                bprimera = true;
            } else {
                if (dpn.getIdEstatus().getIdEstatus() == 213) {
                    bcontinua = false;
                }
            }

            String clave = "";
            String claveUnidad = "-1";
            if (bcontinua) {
                listaDpnInsumo = dpnInsumosService.llenaDpnInsumosActivos(dpn, clave, claveUnidad);
                if (listaDpnInsumo != null) {
//                    SimpleDateFormat formatoDeFecha = new SimpleDateFormat("yyyy/MM/dd");
//                    try {
//                        fechaInicio = formatoDeFecha.parse("2017/04/26");
//                        fechaFin = formatoDeFecha.parse("2017/05/25");
//                    } catch (ParseException ex) {
//                        Logger.getLogger(ShedulerJobDAOImplement.class.getName()).log(Level.SEVERE, null, ex);
//                    }
                    Date fechaInicio2 = util.sumarRestarMesFecha(fechaFin, -2);
                    Date fechaInicio3 = util.sumarRestarMesFecha(fechaFin, -3);
                    dpnInsumosService.actualizaDpnInsumosByProcedure(clave, claveUnidad, fechaInicio, fechaFin, fechaInicio2, fechaInicio3);
                    listaDpnInsumo = dpnInsumosService.getListaAll(clave, claveUnidad);
                    if (listaDpnInsumo != null) {
                        Integer consumo = 0;
                        Integer existencias = 0;
                        double porcentajeAbajo = .9;
                        double porcentajeArriba = 1.1;
                        double dpnAbajo;
                        double dpnArriba;
                        DpnInsumos diAnt = null;
                        List<OrdenSuministro> listaOrdenSuministro = null;
                        boolean bvalida = true;
                        for (DpnInsumos di : listaDpnInsumo) {
                            bvalida = true;
                            if (di.getExistenciasCenadi() == 0) {
                                if (ordenSuministroService.getOrdenByClave(di.getClaveInsumo()) == null) {
                                    bvalida = false;
                                }
                            }
                            if (bvalida) {
                                if (di.getIdInsumoDpn().getIdTipoInsumoDpn() != null) {
//                                    diAnt = dpnInsumosService.getUltimaDpnByUnidadClave(clave, claveUnidad);
//                                    di.setPiezasDpnAnterior(0);
//                                    if (diAnt != null) {
//                                        di.setPiezasDpnAnterior(diAnt.getPiezasDpn());
//                                    }
                                    if (di.getIdInsumoDpn().getIdTipoInsumoDpn().getIdTipoInsumoDpn() != 1) {
                                        di.setPiezasPropuestasDpn(di.getPiezasDpnAnterior());
                                        di.setResultado("C/N/E");
                                    } else {
                                        if (di.getSalidasSiam() < di.getSalidasSiam3()) {
                                            consumo = di.getSalidasSiam3();
                                        } else {
                                            consumo = di.getSalidasSiam();
                                        }
                                        existencias = di.getExistenciasCenadi();
                                        if (di.getCoberturas() < 30) {
                                            di.setPiezasPropuestasDpn(di.getPiezasDpnAnterior());
                                            di.setResultado("DPN anterior cobertura menor a 30 dÃ­as");
                                        } else {
                                            if (di.getSalidasSiam3() == 0) {
                                                di.setPiezasPropuestasDpn(di.getPiezasDpnAnterior());
                                                di.setResultado("DPN cero cobertura mayor a 30 dÃ­as y consumo cero");
                                            } else {
                                                di.setPiezasPropuestasDpn(consumo - existencias);
                                                if (di.getPiezasPropuestasDpn() >= 0) {
                                                    di.setPiezasPropuestasDpn(di.getPiezasPropuestasDpn() + di.getPiezasDpnAnterior());
                                                    di.setPiezasPropuestasDpn(di.getPiezasPropuestasDpn() / 2);
                                                    dpnAbajo = di.getPiezasDpnAnterior() * porcentajeAbajo;
                                                    dpnArriba = di.getPiezasDpnAnterior() * porcentajeArriba;
                                                    if (di.getPiezasPropuestasDpn() < (int) dpnAbajo) {
                                                        di.setPiezasPropuestasDpn((int) dpnAbajo);
                                                        di.setResultado("DPN topada -10%");
                                                    } else if (di.getPiezasPropuestasDpn() > (int) dpnArriba) {
                                                        di.setPiezasPropuestasDpn((int) dpnArriba);
                                                        di.setResultado("DPN topada +10%");
                                                    } else {
                                                        di.setResultado("DPN nueva");
                                                    }
                                                } else {
                                                    dpnAbajo = di.getPiezasDpnAnterior() * porcentajeAbajo;
                                                    di.setPiezasPropuestasDpn((int) dpnAbajo);
                                                    di.setResultado("DPN -10% consumo menor q existencias");
                                                }
                                            }
                                        }
                                    }
                                } else {
                                    di.setResultado("SIN ASIGNAR");
                                }
                            } else {
                                di.setResultado("Existencia cero sin orden de suministro");
                            }
                            if (di.getPiezasDpn() == -1) {
                                di.setPiezasDpn(di.getPiezasPropuestasDpn());
                            }
                            totalPiezas = totalPiezas.add(new BigDecimal(di.getPiezasDpn()));
                            di.setIdDpn(dpn);
                        }
                    }
                }
                estatus = estatusService.getRemisionEstatus(212);
                dpn.setDpnInsumosList(listaDpnInsumo);
                dpn.setTotalPiezasDpn(totalPiezas);
                if (dpnService.guardaActualiza(dpn)) {
                    //nada
                }
            }
            if (!bprimera) {
                dpn.setFechaModificacion(new Date());
                dpn.setUsuarioModificacion("SYSTEM");
            }
        }
    }

}
