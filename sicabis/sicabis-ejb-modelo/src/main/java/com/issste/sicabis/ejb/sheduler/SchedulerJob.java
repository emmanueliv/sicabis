package com.issste.sicabis.ejb.sheduler;

import com.issste.sicabis.ejb.ln.AlertasDpnService;
import com.issste.sicabis.ejb.ln.AlertasEnvioService;
import com.issste.sicabis.ejb.ln.BitacoraTareaSerice;
import com.issste.sicabis.ejb.ln.ConfiguraDpnService;
import com.issste.sicabis.ejb.ln.ContactosAlertasDpnService;
import com.issste.sicabis.ejb.ln.DpnService;
import com.issste.sicabis.ejb.ln.UrService;
import com.issste.sicabis.ejb.ln.UsuariosService;
import com.issste.sicabis.ejb.modelo.AlertasDpn;
import com.issste.sicabis.ejb.modelo.BitacoraTareaEstatus;
import com.issste.sicabis.ejb.modelo.ConfiguraDpn;
import com.issste.sicabis.ejb.modelo.ContactosAlertasDpn;
import com.issste.sicabis.ejb.modelo.Dpn;
import com.issste.sicabis.ejb.modelo.Estatus;
import com.issste.sicabis.ejb.modelo.Ur;
import com.issste.sicabis.ejb.modelo.Usuarios;
import com.issste.sicabis.ejb.modelo.UsuariosTipoUsuarios;
import com.issste.sicabis.ejb.service.silodisa.controller.CatalogoInsumosController;
import com.issste.sicabis.ejb.service.silodisa.controller.CatalogoUnidadesMedicasController;
import com.issste.sicabis.ejb.service.silodisa.controller.ClavesPorCodigoBarrasController;
import com.issste.sicabis.ejb.service.silodisa.controller.DetalleSalidasUmuGuiaDistribucionController;
import com.issste.sicabis.ejb.service.silodisa.controller.EntradasMymcqCenadiController;
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
import com.issste.sicabis.ejb.service.silodisa.service.MapaEjecutivoDispDelegacionesService;
import com.issste.sicabis.ejb.service.silodisa.service.MapaEjecutivoDispG40Service;
import com.issste.sicabis.ejb.utils.AlertasUtil;
import com.issste.sicabis.ejb.utils.MailInsumosPendientes;
import com.issste.sicabis.ejb.utils.Utilidades;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.EJB;
import javax.ejb.LocalBean;
import javax.ejb.Schedule;
import javax.ejb.Stateless;

@Stateless
@LocalBean
public class SchedulerJob {

    @EJB
    private UsuariosService usuariosService;

    @EJB
    private AlertasEnvioService alertasEnvioService;

    @EJB
    private UrService urService;

    @EJB
    private DpnService dpnService;

    @EJB
    private AlertasDpnService alertasDpnService;

    @EJB
    private ExistenciasUMUsProgramasController existenciasUMUsProgramasController;

    @EJB
    private SeguimientoSalidasUmuTransitoController seguimientoSalidasUmuTransitoController;

    @EJB
    private SeguimientoSalidasUmuInternoController seguimientoSalidasUmuInternoController;

    @EJB
    private SalidasCenadiUmuGuiaDeDistribucionController salidasCenadiUmuGuiaDeDistribucionController;

    @EJB
    private RemisionesElectronicasEntregasUmuController remisionesElectronicasEntregasUmuController;

    @EJB
    private DetalleSalidasUmuGuiaDistribucionController detalleSalidasUmuGuiaDistribucionController;

    @EJB
    private EntradasMymcqCenadiController entradasMymcqCenadiController;

    @EJB
    private ClavesPorCodigoBarrasController clavesPorCodigoBarrasController;

    @EJB
    private CatalogoUnidadesMedicasController catalogoUnidadesMedicasController;

    @EJB
    private CatalogoInsumosController catalogoInsumosController;

    @EJB
    private ExistenciasPorClaveCenadiController existenciasPorClaveCenadiController;

    @EJB
    private ExistenciaReservaClaveCenadiController existenciaReservaClaveCenadiController;

    @EJB
    private BitacoraTareaSerice bitacoraTareaSerice;

    @EJB
    private MapaEjecutivoDispG40Service mapaEjecutivoDispG40Service;

    @EJB
    private MapaEjecutivoDispDelegacionesService mapaEjecutivoDispDelegacionesService1;

    @EJB
    private MapaEjecutivoDispDelegacionesService mapaEjecutivoDispDelegacionesService;

    @EJB
    private ContactosAlertasDpnService contactosAlertasDpnService;

    @EJB
    private ConfiguraDpnService configuraDpnService;

    @EJB
    private MapaEjecutivoDispG40Controller mapaEjecutivoDispG40Controller;

    @EJB
    private MapaEjecutivoDispDelegacionesController mapaEjecutivoDispDelegacionesController;

    @EJB
    private MapaEjecutivoDisponibilidadEstadosController mapaEjecutivoDisponibilidadEstadosController;

    //Objects
    private MailInsumosPendientes MailInsumosPendientes = new MailInsumosPendientes();
    private AlertasUtil alertasUtil;
    private Utilidades util = new Utilidades();

    @Schedule(second = "0", minute = "30", hour = "08")
    public void executeWebServiceMapas() {
        try {
            BitacoraTareaEstatus bitacora = new BitacoraTareaEstatus();
            bitacora.setFecha(new Date());
            bitacora.setIdEstatus(0);
            bitacora.setIdModulos(0);
            bitacora.setDescripcion("Inicio actualización de mapas");
            bitacora.setIdUsuarios(0);
            bitacora.setIdTareaId(0);
            bitacoraTareaSerice.guardarEnBitacora(bitacora);
            System.out.println("envio mapas------->" + new Date());
            mapaEjecutivoDisponibilidadEstadosController.obtenerPorcentajePorEstado(1);
            mapaEjecutivoDispDelegacionesController.obtenerMapaEjecutivoDispDelegacion(1);
            mapaEjecutivoDispG40Controller.obtenerMapaEjecutivoDispG40(1);
            bitacora.setDescripcion("Sali actualización de mapas");
            bitacoraTareaSerice.guardarEnBitacora(bitacora);
            System.out.println("sali envio mapas------->" + new Date());
            Calendar cal = Calendar.getInstance();
            cal.setTime(new Date());
            if (cal.get(Calendar.DAY_OF_WEEK) != 1 && cal.get(Calendar.DAY_OF_WEEK) != 7) {
                bitacora.setDescripcion("Inicio envío correos");
                bitacoraTareaSerice.guardarEnBitacora(bitacora);
                this.executeEnvioCorreos();
                bitacora.setDescripcion("Sali envío correos");
                bitacoraTareaSerice.guardarEnBitacora(bitacora);
            }
        } catch (Exception ex) {
            System.out.println("Error en web service mapas: " + ex);
        }
    }

    @Schedule(second = "0", minute = "00", hour = "*/2")
//    @Schedule(second = "0", minute = "48", hour = "16")
    public void excecuteCorreos() {
        try {
            System.out.println("envioCorreosDisponibilidadAux------->" + new Date());
            SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
            String fecha = sdf.format(new Date());
            String mensaje = "Buenos días, anexo me permito reenviar a Usted la información correspondiente a los Niveles de Disponibilidad de insumos para la salud del Instituto, los cuales se generan en forma automática a través de la plataforma informática del Sistema Integral de la Cadena de Abasto de Insumos para la Salud. \n"
                    + "<br><br> \n";
            mensaje = mensaje + "<strong>\n"
                    + "Disponibilidad por Estados: Considera todas las Unidades de Medicina Familiar incluyendo Hospitales Generales, Hospitales Regionales y el Centro Médico Nacional 20 de noviembre.\n"
                    + "</strong>\n"
                    + "<h3><a href='https://sicabis.issste.gob.mx:8080/sicabis_public-web/vistas/indicadores/dispEstados.xhtml'>Ver mapa Estados</a></h3>\n"
                    + "<br>\n";

            mensaje = mensaje + "<strong>\n"
                    + "Disponibilidad por Delegaciones: Considera Únicamente Unidades de Medicina Familiar. No incluye Hospitales Generales, Hospitales Regionales y Centro Médico Nacional 20 de Noviembre.\n"
                    + "</strong>\n"
                    + "<h3><a href='https://sicabis.issste.gob.mx:8080/sicabis_public-web/vistas/indicadores/dispDelegaciones.xhtml'>Ver mapa Delegaciones</a></h3>\n"
                    + "<br>\n";

            mensaje = mensaje + "<strong>\n"
                    + "Disponibilidad G40: Considera Únicamente Hospitales Generales, Hospitales Regionales y Centro Médico Nacional 20 de Noviembre.\n"
                    + "</strong>\n"
                    + "<h3><a href='https://sicabis.issste.gob.mx:8080/sicabis_public-web/vistas/indicadores/dispG40.xhtml'>Ver mapa G40</a></h3>\n"
                    + "<br>\n";

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
            com.issste.sicabis.ejb.utils.SendMail.writeMailCorreoAux(new ContactosAlertasDpn(), "Niveles de Disponibilidad de Insumos para la Salud", correo);
            System.out.println("sali envioCorreosDisponibilidad AUX------->" + new Date());
        } catch (Exception ex) {
            System.out.println("Error en web service correos AUX: " + ex);
            Logger.getLogger(ShedulerJobDAOImplement.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void executeEnvioCorreos() {
        try {
            System.out.println("envioCorreosDisponibilidad------->" + new Date());
            Integer diaActual = 1;
            SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
            String fecha = sdf.format(new Date());
            List<ContactosAlertasDpn> listContactosDPN = new ArrayList<>();
            if (diaActual == 1) {
                listContactosDPN = contactosAlertasDpnService.getAllContactos(1);
                System.out.println("listContactosDPN correo -------->" + listContactosDPN);
                for (ContactosAlertasDpn iterator : listContactosDPN) {
                    if (iterator.getMapas() == 0) {
                        System.out.println("entre anterior correos");
                    } else {
                        String mensaje = "Buenos días, anexo me permito reenviar a Usted la información correspondiente a los Niveles de Disponibilidad de insumos para la salud del Instituto, los cuales se generan en forma automática a través de la plataforma informática del Sistema Integral de la Cadena de Abasto de Insumos para la Salud. \n"
                                + "<br><br> \n";
                        if (iterator.getEstados() == 1) {
                            mensaje = mensaje + "<strong>\n"
                                    + "Disponibilidad por Estados: Considera todas las Unidades de Medicina Familiar incluyendo Hospitales Generales, Hospitales Regionales y el Centro Médico Nacional 20 de noviembre.\n"
                                    + "</strong>\n"
                                    + "<h3><a href='https://sicabis.issste.gob.mx:8080/sicabis_public-web/vistas/indicadores/dispEstados.xhtml'>Ver mapa Estados</a></h3>\n"
                                    + "<br>\n";
                        }
                        if (iterator.getDelegaciones() == 1) {
                            mensaje = mensaje + "<strong>\n"
                                    + "Disponibilidad por Delegaciones: Considera Únicamente Unidades de Medicina Familiar. No incluye Hospitales Generales, Hospitales Regionales y Centro Médico Nacional 20 de Noviembre.\n"
                                    + "</strong>\n"
                                    + "<h3><a href='https://sicabis.issste.gob.mx:8080/sicabis_public-web/vistas/indicadores/dispDelegaciones.xhtml'>Ver mapa Delegaciones</a></h3>\n"
                                    + "<br>\n";
                        }
                        if (iterator.getG40() == 1) {
                            mensaje = mensaje + "<strong>\n"
                                    + "Disponibilidad G40: Considera Únicamente Hospitales Generales, Hospitales Regionales y Centro Médico Nacional 20 de Noviembre.\n"
                                    + "</strong>\n"
                                    + "<h3><a href='https://sicabis.issste.gob.mx:8080/sicabis_public-web/vistas/indicadores/dispG40.xhtml'>Ver mapa G40</a></h3>\n"
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

    @Schedule(second = "0", minute = "50", hour = "08")
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
//                existenciasPorClaveCenadiController.obtenerExistencias(1);
                //existenciaPorClaveUmusController.obtenerTodosExistenciaPorClaveUmus(1);
                //alertasOperativasController.obtenerTodasAlertasOperativas(1);
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

    @Schedule(dayOfMonth = "1", second = "0", minute = "0", hour = "0")
    public void executeAlertasDPN() {
        this.enviaAlertas();
    }

    public void enviaAlertas() {
        SimpleDateFormat format = new SimpleDateFormat("yyyy/MM/dd");
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
        String fecha = sdf.format(new Date());
        Calendar c = Calendar.getInstance(Locale.ENGLISH);
        int diaActual = c.get(Calendar.DAY_OF_MONTH);
        int mesActual = c.get(Calendar.MONTH) + 1;
        int anioActual = c.get(Calendar.YEAR);
        List<UsuariosTipoUsuarios> listUsuarios = new ArrayList<>();
        List<ConfiguraDpn> cd = configuraDpnService.getAllByActivo(1);
        String url = "";
        if (cd != null) {
            if (cd.get(0).getUrlLink() != null) {
                url = cd.get(0).getUrlLink();
            }
        }
        List<Ur> listUr;
        Dpn dpn = dpnService.getByAnioMesIdEstatus(anioActual, mesActual, 213);
        if (dpn != null) {
            listUr = urService.getAll();
            AlertasDpn ad = null;
            alertasEnvioService.borraAlertasEnvio(mesActual, anioActual, null);
            alertasDpnService.borraAlertasDpn(anioActual, mesActual, null);
            for (Ur u : listUr) {
                ad = new AlertasDpn();
                ad.setActivo(1);
                ad.setAnio(anioActual);
                ad.setMes(mesActual);
                ad.setUr(u);
                ad.setIdEstatus(new Estatus(221));
                ad.setIdUsuario(new Usuarios(1));
                System.out.println("mesActual--->" + mesActual);
                alertasDpnService.guardar(ad);
                alertasEnvioService.generaAlertasEnvio(u.getNumUr(), ad.getIdAlertasDpn(), dpn.getIdDpn());
            }
            listUsuarios = usuariosService.getUsuariosByTipoUsuario(6);
            for (UsuariosTipoUsuarios iterator : listUsuarios) {
                String mensaje = "Buenos días, anexo encontrarán el hipervínculo en donde podrán tener acceso al módulo “Reporte Interactivo Estadístico de Consumo el cual se produce  a través de la plataforma informática del Sistema Integral de la Cadena de Abasto de Insumos para la Salud (SICABIS). \n"
                        + "<br><br> \n";
                mensaje = mensaje + "<strong>\n"
                        + "Este correo se genera de forma automática el primer día del mes y se cuenta con 10 días para hacer la propuesta, una vez pasada esta fecha no se podrán hacer modificaciones.\n"
                        + "</strong>\n"
                        + "<h3><a href='" + url + "'>LIGA ACCESO SICABIS</a></h3>\n"
                        + "<br>\n";
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

                com.issste.sicabis.ejb.utils.SendMail.writeMailCorreoAlertas(iterator.getIdUsuario().getCorreo(), "Niveles de Disponibilidad de Insumos para la Salud", correo);
            }
        }
    }
}
