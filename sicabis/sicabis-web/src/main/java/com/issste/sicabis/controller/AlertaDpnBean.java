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
import com.issste.sicabis.ejb.ln.DpnService;
import com.issste.sicabis.ejb.ln.EstatusService;
import com.issste.sicabis.ejb.ln.UnidadMedicaService;
import com.issste.sicabis.ejb.ln.UrService;
import com.issste.sicabis.ejb.ln.UsuariosService;
import com.issste.sicabis.ejb.modelo.AlertasDpn;
import com.issste.sicabis.ejb.modelo.AlertasEnvio;
import com.issste.sicabis.ejb.modelo.ConfiguraDpn;
import com.issste.sicabis.ejb.modelo.Delegaciones;
import com.issste.sicabis.ejb.modelo.Dpn;
import com.issste.sicabis.ejb.modelo.DpnInsumos;
import com.issste.sicabis.ejb.modelo.Estatus;
import com.issste.sicabis.ejb.modelo.Perfiles;
import com.issste.sicabis.ejb.modelo.Ur;
import com.issste.sicabis.ejb.modelo.Usuarios;
import com.issste.sicabis.ejb.modelo.UsuariosTipoUsuarios;
import com.issste.sicabis.utils.ArchivosUtilidades;
import static com.issste.sicabis.utils.ArchivosUtilidades.PATHFILESPROPUESTAS;
import com.issste.sicabis.utils.Mensajes;
import com.issste.sicabis.utils.Utilidades;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.primefaces.component.datatable.DataTable;
import org.primefaces.event.CellEditEvent;
import org.primefaces.event.FileUploadEvent;
import org.primefaces.event.RowEditEvent;
import org.primefaces.model.UploadedFile;

public class AlertaDpnBean {

    @EJB
    private UrService urService;

    @EJB
    private DpnService dpnService;

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
    private ArchivosUtilidades archivosUtilidades = new ArchivosUtilidades();
    private final Integer idTarea = 22;
    private Estatus estatusActualizado;
    private Estatus estatusSA;
    private Estatus estatusNR;

    private AlertasDpn alertasDpn;

    private List<AlertasEnvio> listAlertasEnvio;
    private List<AlertasEnvio> listAlertasEnvioFilter;
    private List<Delegaciones> listDelegacion;
    private List<Estatus> listEstatus;
    private List<Ur> listUr;
    private List<UsuariosTipoUsuarios> listUsuarios;

    //Variables
    private String clave;
    private String usuarioAD;
    private Integer tipoUsuario;
    private Integer diaActual;
    private Integer mesActual;
    private Integer anioActual;
    private List<AlertasDpn> listAlertasDpn;
    private Integer mes;
    private boolean bactivo;
    private Integer opciones;
    private boolean badmin;
    private Integer ur;
    private Integer urConsulta;
    private Integer idUsuario;

    public AlertaDpnBean() {
        usuario = new Usuarios();
        listAlertasEnvio = new ArrayList<>();
        listAlertasEnvioFilter = new ArrayList<>();
        listAlertasDpn = new ArrayList<>();
        listEstatus = new ArrayList<>();
        listUr = new ArrayList<>();
        alertasDpn = new AlertasDpn();
    }

    @PostConstruct
    public void init() {

        clave = "";
        ur = -1;
        opciones = 0;
        usuario = (Usuarios) util.getSessionAtributte("usuario");
        perfiles = (Perfiles) util.getSessionAtributte("perfil");
        if (perfiles.getAdministrador() != 1) {
            usuario.setUsuariosTipoUsuariosList(usuariosService.getByIdUsuarioIdTipoUsuario(usuario.getIdUsuario(), 6));
            if (usuario.getUsuariosTipoUsuariosList() == null || usuario.getUsuariosTipoUsuariosList().isEmpty()) {
                tipoUsuario = 0;
            } else {
                tipoUsuario = 0;
                for (UsuariosTipoUsuarios utul : usuario.getUsuariosTipoUsuariosList()) {
                    if (utul.getIdTipoUsuario().getIdTipoUsuario() == 6) {
                        tipoUsuario = 6;
                        break;
                    }
                }
                //tipoUsuario = usuario.getUsuariosTipoUsuariosList().get(0).getIdTipoUsuario().getIdTipoUsuario();
            }
        } else {
            badmin = true;
            tipoUsuario = 0;
            //bactivo = true;
        }
//        System.out.println("tipoUsuario--->"+tipoUsuario);
        listDelegacion = delegacionesService.obtenerDelegaciones();
        listUr = urService.getAll();

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
        ur = usuario.getUr() != null ? usuario.getUr().getUr() : -1;
        if (tipoUsuario == 6) {
            bactivo = true;
            if (diaActual > diasHabiles) {
                opciones = 1;
                bactivo = false;
            } else {
                listAlertasEnvio = alertasEnvioService.getByIdAlertaDpnUr(ur, anioActual, mes);
                if (listAlertasEnvio == null || listAlertasEnvio.isEmpty()) {
                    opciones = 2;
                } else {
                    this.validaEstatus();
                }
            }
        }
        listEstatus = estatusService.getEstatusByTarea(23);
        listUsuarios = usuariosService.getUsuariosByTipoUsuario(6);
//        estatusSA = estatusService.getRemisionEstatus(231);
//        estatusNR = estatusService.getRemisionEstatus(232);
        estatusActualizado = estatusService.getRemisionEstatus(233);
    }

    public void onload() {
        if (opciones == 1) {
            mensaje.mensaje("Estas fuera del periodo, deberás ingresar a partir del día 1° del siguiente mes", "amarillo");
        } else if (opciones == 2) {
            mensaje.mensaje("No existen registros para el periodo y/o la delegación", "amarillo");
        }
    }

    public void editaCelda(RowEditEvent event) {
        try {
            DataTable s = (DataTable) event.getSource();
            AlertasEnvio ae = (AlertasEnvio) s.getRowData();
            this.actualizaCelda(ae);
        } catch (Exception e) {
            System.out.println("falle");
            //this.consultarAlertas();
        }
    }

    public void cambiaCelda(CellEditEvent e) {
        try {
            DataTable s = (DataTable) e.getSource();
            AlertasEnvio ae = (AlertasEnvio) s.getRowData();
            this.actualizaCelda(ae);
        } catch (Exception ex) {
            System.out.println("fallo");
            //this.consultarAlertas();
        }
    }

    public void actualizaCelda(AlertasEnvio ae) {
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
                bactivo = false;
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
        } else {
            bactivo = true;
            if (mes != mesActual) {
                bactivo = false;
            }
        }
        if (ur == -1) {
            mensaje.mensaje("Debes seleccionar una unidad responsable.", "amarillo");
            band = false;
        } else {
            listAlertasEnvio = alertasEnvioService.getByIdAlertaDpnUr(ur, anioActual, mes);
        }
        if (listAlertasEnvio == null || listAlertasEnvio.isEmpty()) {
            if (band) {
                mensaje.mensaje("No existen registros que concuerden con los parametros seleccionados.", "amarillo");
            }
        } else {
            if (bactivo) {
                this.validaEstatus();
            }
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

    public void cargarArchivo(FileUploadEvent event) {
        boolean op1 = false;
        boolean op2 = false;
        if (listAlertasEnvio != null && listAlertasEnvio.size() >0 && bactivo) {
            if(alertasDpn.getIdEstatus().getIdEstatus() == 221){
                op1 = true;
            }
            UploadedFile uploadedFile = event.getFile();
            String nombreArchivo = archivosUtilidades.generaNombreArchivo(uploadedFile, idTarea, 1);
            FileInputStream file = null;
            int fila = 1;
            int columna = 1;
            Double d = null;
            AlertasEnvio ae;
            Integer idAlertaEnvio = -1;
            Integer dpnSugerida = null;
            try {
                archivosUtilidades.copyFilePropuestas(nombreArchivo, uploadedFile.getInputstream());
                file = new FileInputStream(new File(PATHFILESPROPUESTAS + nombreArchivo));
                XSSFWorkbook workbook = new XSSFWorkbook(file);

                XSSFSheet sheet = workbook.getSheetAt(0);
                Iterator<Row> rowIterator = sheet.iterator();
                Row row;
                y:
                while (rowIterator.hasNext()) {
                    if (fila == 1) {
                        row = rowIterator.next();
                        fila++;
                    }
                    row = rowIterator.next();
                    Iterator<Cell> cellIterator = row.cellIterator();
                    Cell celda;
                    columna = 1;
                    z:
                    while (cellIterator.hasNext()) {
                        celda = cellIterator.next();
                        d = null;
                        switch (columna) {
                            case 1:
                                try {
                                    if (celda.getCellType() == Cell.CELL_TYPE_NUMERIC) {
                                        d = celda.getNumericCellValue();
                                        idAlertaEnvio = d.intValue();
                                    } else if (celda.getCellType() == Cell.CELL_TYPE_STRING) {
                                        idAlertaEnvio = Integer.parseInt(celda.getStringCellValue());
                                    } else {
                                        idAlertaEnvio = -1;
                                    }
                                } catch (Exception e) {
                                    idAlertaEnvio = -1;
                                }
                                break;
                            case 8:
                                try {
                                    if (celda.getCellType() == Cell.CELL_TYPE_NUMERIC) {
                                        d = celda.getNumericCellValue();
                                        dpnSugerida = d.intValue();
                                    } else if (celda.getCellType() == Cell.CELL_TYPE_STRING) {
                                        dpnSugerida = Integer.parseInt(celda.getStringCellValue());
                                    } else {
                                        dpnSugerida = null;
                                    }
                                } catch (Exception e) {
                                    dpnSugerida = null;
                                }
                                System.out.println("dpnSugerida-->" + dpnSugerida);
                                break;
                        }
                        columna++;
                    }
                    if (idAlertaEnvio != -1 && dpnSugerida != null) {
                        op2 = true;
                        alertasEnvioService.updateEstatusDpnById(idAlertaEnvio, estatusActualizado.getIdEstatus(), dpnSugerida);
                    }
                    fila++;
                }
                if(op1 && op2){
                    alertasDpnService.updateEstatusById(222, alertasDpn.getIdAlertasDpn());
                }
                file.close();
                listAlertasEnvio = new ArrayList();
                listAlertasEnvio = alertasEnvioService.getByIdAlertaDpnUr(ur, anioActual, mes);
                //this.consultarAlertas();
            } catch (IOException ex) {
                System.out.println("entre en error ");
                Logger.getLogger(AlertaDpnBean.class.getName()).log(Level.SEVERE, null, ex);
            }
        } else {
            mensaje.mensaje("No hay registros a cargar", "amarillo");
        }
    }

    public void obtieneAlertas() {
        Dpn dpn = dpnService.getByAnioMesIdEstatus(anioActual, mesActual, 213);
        if (dpn != null) {
            AlertasDpn ad = null;
            //List<Delegaciones> listDelegacion = delegacionesService.obtenerDelegaciones();
            if (urConsulta == -1) {
                //borrar alertas_envio y alertas_dpn
                alertasEnvioService.borraAlertasEnvio(mesActual, anioActual, null);
                alertasDpnService.borraAlertasDpn(anioActual, mesActual, null);
                for (Ur u : listUr) {
                    ad = new AlertasDpn();
                    ad.setActivo(1);
                    ad.setAnio(anioActual);
                    ad.setMes(mesActual);
                    ad.setUr(u);
                    ad.setIdEstatus(new Estatus(221));
                    ad.setIdUsuario(usuario);
                    System.out.println("mesActual--->" + mesActual);
                    alertasDpnService.guardar(ad);
                    this.llenaAlertasEnvio(u, ad, dpn);
                }
                mensaje.mensaje("Información actualizada correctamente", "verde");
            } else {
                alertasEnvioService.borraAlertasEnvio(mesActual, anioActual, urConsulta);
                alertasDpnService.borraAlertasDpn(anioActual, mesActual, urConsulta);
                Ur u = urService.getByUr(urConsulta);
                ad = new AlertasDpn();
                ad.setActivo(1);
                ad.setAnio(anioActual);
                ad.setMes(mesActual);
                ad.setUr(u);
                ad.setIdEstatus(new Estatus(221));
                ad.setIdUsuario(usuario);
                alertasDpnService.guardar(ad);
                this.llenaAlertasEnvio(u, ad, dpn);
                mensaje.mensaje("Información actualizada correctamente", "verde");
            }
            listAlertasDpn = alertasDpnService.getByAnioMes(anioActual, null);
            listAlertasEnvio = new ArrayList<>();
        } else {
            mensaje.mensaje("No existen información de la DPN del mes actual", "amarillo");
        }
    }

    public void llenaAlertasEnvio(Ur u, AlertasDpn ad, Dpn dpn) {
        alertasEnvioService.generaAlertasEnvio(u.getNumUr(), ad.getIdAlertasDpn(), dpn.getIdDpn());
    }

    public void ejecutaAlertaDPN() {
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
        String fecha = sdf.format(new Date());
        //List<UsuariosTipoUsuarios> listUsuarios = new ArrayList<>();
        List<ConfiguraDpn> cd = configuraDpnService.getAllByActivo(1);
        String url = "";
        if (cd != null) {
            if (cd.get(0).getUrlLink() != null) {
                url = cd.get(0).getUrlLink();
            }
        }
        if (idUsuario == -1) {
            for (UsuariosTipoUsuarios iterator : listUsuarios) {
                this.enviaCorreo(iterator.getIdUsuario(), fecha, url);
            }
        } else {
            Usuarios u = usuariosService.getByIdUsuario(idUsuario);
            this.enviaCorreo(u, fecha, url);
        }
    }

    public void enviaCorreo(Usuarios u, String fecha, String url) {

        String mensaje = "Buenos días, anexo encontrarán el hipervínculo en donde podrán tener acceso al módulo “Reporte Interactivo Estadístico de Consumo el cual se produce  a través de la plataforma informática del Sistema Integral de la Cadena de Abasto de Insumos para la Salud (SICABIS). \n"
                + "<br><br> \n";
        mensaje = mensaje + "<strong>\n"
                + "Este correo se genera de forma automática el primer día del mes y se cuenta con 10 días para hacer la propuesta, una vez pasada esta fecha no se podrán hacer modificaciones.\n"
                + "</strong>\n"
                + "Tu cuenta de usuario es " + u.getUsuario() + ", y la contraseña inicial es SICABIS con Máyusculas, una vez iniciada la sesión, el sistema te pedirá automáticamente que cambies por cuestiones de seguridad tu contraseña.\n"
                + "Cabe mencionar que la cuenta de usuario y contraseña es única e intransferible.\n"
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
                + "Estimado " + u.getNombre() + " " + u.getApellidoPaterno() + " " + u.getApellidoMaterno() + " \n"
                + "<br><br>\n"
                + mensaje
                + "</p>\n"
                + "</div>\n"
                + "</div>\n"
                + "<center><img src='http://www2.issste.gob.mx:8080/images/logos/issste2013-footer.jpg' alt='Footer' /></center>";

        com.issste.sicabis.ejb.utils.SendMail.writeMailCorreoAlertas(u.getCorreo(), "Niveles de Disponibilidad de Insumos para la Salud", correo);

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

    public List<Delegaciones> getListDelegacion() {
        return listDelegacion;
    }

    public void setListDelegacion(List<Delegaciones> listDelegacion) {
        this.listDelegacion = listDelegacion;
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

    public boolean isBadmin() {
        return badmin;
    }

    public void setBadmin(boolean badmin) {
        this.badmin = badmin;
    }

    public Integer getUr() {
        return ur;
    }

    public void setUr(Integer ur) {
        this.ur = ur;
    }

    public Integer getUrConsulta() {
        return urConsulta;
    }

    public void setUrConsulta(Integer urConsulta) {
        this.urConsulta = urConsulta;
    }

    public List<Ur> getListUr() {
        return listUr;
    }

    public void setListUr(List<Ur> listUr) {
        this.listUr = listUr;
    }

    public List<UsuariosTipoUsuarios> getListUsuarios() {
        return listUsuarios;
    }

    public void setListUsuarios(List<UsuariosTipoUsuarios> listUsuarios) {
        this.listUsuarios = listUsuarios;
    }

    public Integer getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(Integer idUsuario) {
        this.idUsuario = idUsuario;
    }

}
