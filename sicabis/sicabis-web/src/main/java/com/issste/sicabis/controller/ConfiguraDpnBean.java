package com.issste.sicabis.controller;

import com.issste.sicabis.DTO.RepositorioDocumentosDTO;
import com.issste.sicabis.ejb.ln.AlertasEnvioService;
import com.issste.sicabis.ejb.ln.AreasService;
import com.issste.sicabis.ejb.ln.BitacoraTareaSerice;
import com.issste.sicabis.ejb.ln.ConfiguraDpnService;
import com.issste.sicabis.ejb.ln.ConsumoDiarioSiamService;
import com.issste.sicabis.ejb.ln.ContactosAlertasDpnService;
import com.issste.sicabis.ejb.ln.DelegacionesService;
import com.issste.sicabis.ejb.ln.DpnInsumosService;
import com.issste.sicabis.ejb.ln.UnidadMedicaService;
import com.issste.sicabis.ejb.ln.UsuariosService;
import com.issste.sicabis.ejb.modelo.BitacoraTareaEstatus;
import com.issste.sicabis.ejb.modelo.ConfiguraDpn;
import com.issste.sicabis.ejb.modelo.ContactosAlertasDpn;
import com.issste.sicabis.ejb.modelo.Delegaciones;
import com.issste.sicabis.ejb.modelo.UnidadesMedicas;
import com.issste.sicabis.ejb.modelo.Usuarios;
import com.issste.sicabis.ejb.service.silodisa.modelo.MapaEjecutivoDispDelegaciones;
import com.issste.sicabis.ejb.service.silodisa.modelo.MapaEjecutivoDispG40;
import com.issste.sicabis.ejb.service.silodisa.service.MapaEjecutivoDispDelegacionesService;
import com.issste.sicabis.ejb.service.silodisa.service.MapaEjecutivoDispG40Service;
import com.issste.sicabis.ejb.sheduler.SchedulerJob;
import com.issste.sicabis.ejb.sheduler.ShedulerJobDAO;
import com.issste.sicabis.ejb.sheduler.ShedulerJobDAOImplement;
import com.issste.sicabis.utils.ArchivosUtilidades;
import com.issste.sicabis.utils.Mensajes;
import com.issste.sicabis.utils.SendMail;
import com.issste.sicabis.utils.Utilidades;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.Serializable;
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
import org.primefaces.context.RequestContext;
import org.primefaces.event.FileUploadEvent;
import org.primefaces.model.DefaultStreamedContent;
import org.primefaces.model.UploadedFile;

public class ConfiguraDpnBean implements Serializable {
    
    @EJB
    private SchedulerJob schedulerJob;

    @EJB
    private MapaEjecutivoDispG40Service mapaEjecutivoDispG40Service;

    @EJB
    private MapaEjecutivoDispDelegacionesService mapaEjecutivoDispDelegacionesService;

    @EJB
    private AlertasEnvioService alertasEnvioService;

    @EJB
    private ConsumoDiarioSiamService consumoDiarioSiamService;

    @EJB
    private DpnInsumosService dpnInsumosService;

    @EJB
    private ContactosAlertasDpnService contactosAlertasDpnService;

    @EJB
    private DelegacionesService delegacionesService;

    @EJB
    private BitacoraTareaSerice bitacoraTareaSerice;

    @EJB
    private AreasService areasService;

    @EJB
    private UsuariosService usuariosService;

    @EJB
    private UnidadMedicaService unidadMedicaService;

    @EJB
    private ConfiguraDpnService configuraDpnService;

    private Usuarios usuarios;
    private ConfiguraDpn configuraDpn;
    private ContactosAlertasDpn contactosAlertasDpn;
    private List<ContactosAlertasDpn> listContactosAlertasDpn;

    private boolean bopcion;
    private List<ConfiguraDpn> listaConfiguraDpn;
    private List<UnidadesMedicas> listaUnidadesMedicas;
    private List<Delegaciones> listDelegaciones;
    private Integer idUnidadesMedicas;
    private Integer idDelegacion;
    private Integer idContacto;
    private boolean bvercorreos;
    private boolean bunidadmed;
    private List<String> tipoMapas;
    private boolean bmapasDisp;
    private String destinatario;
    private String copia;
    private String asunto;
    private String mensajeCorreo;
    private List<RepositorioDocumentosDTO> listaRepoDocsDto;
    private UploadedFile uploadedfile;

    private final Integer idTareaProc = 16;

    private Mensajes mensaje = new Mensajes();
    private Utilidades util = new Utilidades();
    private BitacoraTareaEstatus bitacoraTareaEstatus;
    private ArchivosUtilidades archivosUtilidades = new ArchivosUtilidades();

    public ConfiguraDpnBean() {
        listaUnidadesMedicas = new ArrayList<>();
        listContactosAlertasDpn = new ArrayList<>();
        contactosAlertasDpn = new ContactosAlertasDpn();
        listaConfiguraDpn = new ArrayList<>();
        listDelegaciones = new ArrayList<>();
        tipoMapas = new ArrayList();
        configuraDpn = new ConfiguraDpn();
        bitacoraTareaEstatus = new BitacoraTareaEstatus();
        listaRepoDocsDto = new ArrayList();
    }

    @PostConstruct
    public void init() {
        usuarios = (Usuarios) util.getSessionAtributte("usuario");
        listaConfiguraDpn = configuraDpnService.getAllByActivo(1);
        if (listaConfiguraDpn != null) {
            configuraDpn = listaConfiguraDpn.get(0);
            bopcion = true;
        }
        listDelegaciones = delegacionesService.obtenerDelegaciones();
        listaUnidadesMedicas = unidadMedicaService.unidadMedica();

    }

    public void cambiaContacto() {
        listContactosAlertasDpn = contactosAlertasDpnService.getByIdDelegacion(idDelegacion);
        if (idContacto == -2) {
            contactosAlertasDpn = new ContactosAlertasDpn();
            bvercorreos = true;
            bopcion = true;
        } else if (idContacto == -1) {
            bvercorreos = false;
            bopcion = true;
        } else {
            contactosAlertasDpn = contactosAlertasDpnService.getByIdContactoAlectaDpn(idContacto);
            if (contactosAlertasDpn.getMapas().intValue() == 1) {
                tipoMapas = new ArrayList();
                if (contactosAlertasDpn.getEstados().intValue() == 1) {
                    tipoMapas.add("1");
                }
                if (contactosAlertasDpn.getDelegaciones().intValue() == 1) {
                    tipoMapas.add("2");
                }
                if (contactosAlertasDpn.getG40().intValue() == 1) {
                    tipoMapas.add("3");
                }
            }
            bvercorreos = true;
            bopcion = false;
        }
    }

    public void cambiaUnidad() {
        listContactosAlertasDpn = contactosAlertasDpnService.getByIdDelegacion(idUnidadesMedicas);

    }

    public void cambiaDelegacion() {
        if (idDelegacion == 80) {
            listContactosAlertasDpn = contactosAlertasDpnService.getByIdDelegacion(idDelegacion);
            listaUnidadesMedicas = unidadMedicaService.getByHospitalRegional(1);
            bunidadmed = true;
            bmapasDisp = false;
        } else if (idDelegacion == -2) {
            listContactosAlertasDpn = contactosAlertasDpnService.getByMapas(1);
            bunidadmed = false;
            bmapasDisp = true;
        } else {
            listContactosAlertasDpn = contactosAlertasDpnService.getByIdDelegacion(idDelegacion);
            bunidadmed = false;
            bmapasDisp = false;
        }
    }

    public boolean valida() {
        boolean band = true;
//        if (configuraDpn.getDiaInicio() == null) {
//            mensaje.mensaje("Debes ingresar el día de inicio", "amarillo");
//            band = false;
//        }
        if (configuraDpn.getNumDias() == null) {
            configuraDpn.setNumDias(0);
        }
//        if (configuraDpn.getMinPiezas() == null) {
//            configuraDpn.setMinPiezas(0);
//        }
        return band;
    }

    public void validaCorreos(String correo) {
        boolean band = true;
        if (correo.equals("")) {
            if (!util.validaCorreo(correo)) {
                mensaje.mensaje("El correo capturado es incorrecto", "amarillo");
                band = false;
            }
        }
    }

    public boolean validaContacto() {
        boolean band = true;
        if (idDelegacion == -1) {
            mensaje.mensaje("Debes seleccionar una delegación", "amarillo");
            band = false;
        } else if (idDelegacion == -2) {
            if (tipoMapas == null || tipoMapas.size() == 0) {
                mensaje.mensaje("Debes seleccionar al menos un mapa", "amarillo");
                band = false;
            }
        }
        if (band) {
            if (contactosAlertasDpn.getNombre().equals("")) {
                mensaje.mensaje("Debes capturar el nombre del contacto", "amarillo");
                band = false;
            }
            if (contactosAlertasDpn.getCorreo().equals("")) {
                mensaje.mensaje("Debes capturar el correo electrónico del contacto", "amarillo");
                band = false;
            }
        }
        return band;
    }

    public void limpiaContacto() {
        idUnidadesMedicas = -1;
        idDelegacion = -1;
        bvercorreos = false;
        bopcion = true;
        idContacto = -1;
        contactosAlertasDpn = new ContactosAlertasDpn();
        this.cambiaContacto();
    }

    public void agregaContacto() {
        if (validaContacto()) {
            if (idDelegacion == -2) {
                contactosAlertasDpn.setMapas(1);
                contactosAlertasDpn.setEstados(0);
                contactosAlertasDpn.setDelegaciones(0);
                contactosAlertasDpn.setG40(0);
                for (String tp : tipoMapas) {
                    if (tp.equals("1")) {
                        contactosAlertasDpn.setEstados(1);
                    } else if (tp.equals("2")) {
                        contactosAlertasDpn.setDelegaciones(1);
                    } else if (tp.equals("3")) {
                        contactosAlertasDpn.setG40(1);
                    }
                }
                contactosAlertasDpn.setIdDelegacion(null);
                contactosAlertasDpn.setIdUnidadMedica(null);
            } else {
                contactosAlertasDpn.setMapas(0);
                contactosAlertasDpn.setIdDelegacion(delegacionesService.obtenerDelegacionporId(idDelegacion));
                contactosAlertasDpn.setIdUnidadMedica(unidadMedicaService.getByIdUnidadMedica(idUnidadesMedicas));
            }
            contactosAlertasDpn.setActivo(1);
            if (contactosAlertasDpnService.guardarActualiza(contactosAlertasDpn)) {
                mensaje.mensaje("Contacto almacenado exitosamente.", "verde");
                bvercorreos = false;
                RequestContext.getCurrentInstance().update("panelContacto, panelNuevo");
                tipoMapas = new ArrayList();
                bitacoraTareaEstatus.setDescripcion("Guardar contactoAlertasDPN:" + usuarios.getIdUsuario() + "");
                bitacoraTareaEstatus.setFecha(new Date());
                bitacoraTareaEstatus.setIdModulos(1);
                bitacoraTareaEstatus.setIdUsuarios(usuarios.getIdUsuario());
                bitacoraTareaEstatus.setIdTareaId(0);
                bitacoraTareaSerice.guardarEnBitacora(bitacoraTareaEstatus);
                cambiaUnidad();
                cambiaDelegacion();
            }
        }
        this.limpiaContacto();

    }

    public void enviaMail() {
        System.out.println("entre------------>");
        //shedulerJobDAOImplement.execute();
        schedulerJob.executeEnvioCorreos();
        System.out.println("sali------------->");
    }

    public void borrarContacto() {
        System.out.println("borrar------------>");
        contactosAlertasDpn.setActivo(0);
        if (contactosAlertasDpnService.guardarActualiza(contactosAlertasDpn)) {
            bvercorreos = false;
            idDelegacion = -1;
            bmapasDisp = false;
            bunidadmed = false;
        }
    }

    public void enviaMailPersonalizado() {
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
        String fecha = sdf.format(new Date());
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
                + mensajeCorreo
                + "</p>\n"
                + "</div>\n"
                + "</div>\n"
                + "<center><img src='http://www2.issste.gob.mx:8080/images/logos/issste2013-footer.jpg' alt='Footer' /></center>";
        System.out.println("entre mail personalizado------------>" + correo);
        System.out.println("lista---->" + listaRepoDocsDto.size());
        if (listaRepoDocsDto.size() == 0) {
            System.out.println("envio 1");
            com.issste.sicabis.utils.SendMail.writeMailFileCorreo(destinatario, asunto, correo, new File("../docSicabis/clausulado/clausula_contrato"));
        } else {
            System.out.println("envio adjunto---->" + uploadedfile.getFileName());
            String nombreArchivo = uploadedfile.getFileName();
            archivosUtilidades.guardaArchivo(uploadedfile, nombreArchivo);
            com.issste.sicabis.utils.SendMail.writeMailFileCorreo(destinatario, asunto, correo, archivosUtilidades.getFileByName(nombreArchivo));
        }

    }

    public void quitaArchivo() {
        listaRepoDocsDto = new ArrayList();
    }

    public void adjuntaArchivos(FileUploadEvent event) {
        InputStream is = null;
        uploadedfile = event.getFile();
        try {
            RepositorioDocumentosDTO dTO = new RepositorioDocumentosDTO();
            is = event.getFile().getInputstream();
            dTO.setFile(new DefaultStreamedContent(is, "text/xml", event.getFile().getFileName()));
            dTO.setNombre(event.getFile().getFileName());
            listaRepoDocsDto = new ArrayList();
            listaRepoDocsDto.add(dTO);
        } catch (IOException ex) {
            Logger.getLogger(ConfiguraDpnBean.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                is.close();
            } catch (IOException ex) {
                Logger.getLogger(ConfiguraDpnBean.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    public void guardaActualiza() {
        if (valida()) {
            configuraDpn.setActivo(1);
            configuraDpn.setUsuarioAlta(usuarios.getUsuario());
            configuraDpn.setFechaAlta(new Date());
            configuraDpn.setDiaInicio(1);
            if (configuraDpnService.guardaConfiguraDpn(configuraDpn)) {
                bitacoraTareaEstatus.setDescripcion("Guardar configuración de alertas DPN");
                bitacoraTareaEstatus.setFecha(new Date());
                bitacoraTareaEstatus.setIdModulos(1);
                bitacoraTareaEstatus.setIdUsuarios(usuarios.getIdUsuario());
                bitacoraTareaEstatus.setIdTareaId(21);
                bitacoraTareaSerice.guardarEnBitacora(bitacoraTareaEstatus);
                mensaje.mensaje(mensaje.datos_guardados, "verde");
            } else {
                mensaje.mensaje(mensaje.error_guardar, "rojo");
            }
        }
    }

    public void envioCorreosDisponibilidad() {
        SimpleDateFormat format = new SimpleDateFormat("yyyy/MM/dd");
        Calendar c = Calendar.getInstance(Locale.ENGLISH);
        Integer diaActual = 1;
        List<ConfiguraDpn> cd = configuraDpnService.getAllByActivo(1);
        List<MapaEjecutivoDispDelegaciones> listDisponiblidad = new ArrayList<>();
        List<MapaEjecutivoDispG40> listDisponiblidadG40 = new ArrayList<>();
        Integer diasHabiles = cd.get(0).getNumDias();
        Date fechaActualizada;
        try {
            fechaActualizada = util.sumarRestarDiasFecha(format.parse("" + c.get(Calendar.YEAR) + "/ " + c.get(Calendar.MONTH) + "/01"), diasHabiles);
            List<ContactosAlertasDpn> listContactosDPN = new ArrayList<>();
            if (diaActual == 1) {
                listContactosDPN = contactosAlertasDpnService.getAllContactos(1);
                for (ContactosAlertasDpn iterator : listContactosDPN) {
                    if (iterator.getIdUnidadMedica() == null) {
                        listDisponiblidad = mapaEjecutivoDispDelegacionesService.getByDelegacion(iterator.getIdDelegacion().getNombreDelegacion());
                        String mensaje = "Buen día\n"
                                + "Se envia la disponibilidad el detalle del disponibilidad por la delegación " + iterator.getIdDelegacion().getNombreDelegacion() + ".\n"
                                + "Disponibilidad : " + listDisponiblidad.get(0).getDisponibilidad() + ""
                                + "Clave disponibles : " + listDisponiblidad.get(0).getClavesDisponibles() + ""
                                + "Clave ingresada : " + listDisponiblidad.get(0).getClavesAutorizadas() + "";
                        com.issste.sicabis.ejb.utils.SendMail.writeMailCorreo(iterator, "disponibilidad por delegación", mensaje);
                    } else if (iterator.getIdDelegacion() != null && iterator.getIdUnidadMedica() != null) {
                        listDisponiblidadG40 = mapaEjecutivoDispG40Service.getByClaveUnidad(iterator.getIdUnidadMedica().getNombre());
                        String mensaje = "Buen día\n"
                                + "Se envia la disponibilidad el detalle del disponibilidad G40 por la unidad medica " + iterator.getIdUnidadMedica().getNombre() + ".\n"
                                + "Disponibilidad : " + listDisponiblidadG40.get(0).getDisponibilidad().intValueExact() + ""
                                + "Clave disponibles : " + listDisponiblidadG40.get(0).getClavesDisponibles() + ""
                                + "Clave ingresada : " + listDisponiblidadG40.get(0).getClavesAutorizadas() + "";
                        com.issste.sicabis.ejb.utils.SendMail.writeMailCorreo(iterator, "disponibilidad G40", mensaje);
                    }
                }
            }
        } catch (Exception ex) {
            Logger.getLogger(ShedulerJobDAOImplement.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public Mensajes getMensaje() {
        return mensaje;
    }

    public void setMensaje(Mensajes mensaje) {
        this.mensaje = mensaje;
    }

    public ConfiguraDpn getConfiguraDpn() {
        return configuraDpn;
    }

    public void setConfiguraDpn(ConfiguraDpn configuraDpn) {
        this.configuraDpn = configuraDpn;
    }

    public boolean isBopcion() {
        return bopcion;
    }

    public void setBopcion(boolean bopcion) {
        this.bopcion = bopcion;
    }

    public List<ConfiguraDpn> getListaConfiguraDpn() {
        return listaConfiguraDpn;
    }

    public void setListaConfiguraDpn(List<ConfiguraDpn> listaConfiguraDpn) {
        this.listaConfiguraDpn = listaConfiguraDpn;
    }

    public List<UnidadesMedicas> getListaUnidadesMedicas() {
        return listaUnidadesMedicas;
    }

    public void setListaUnidadesMedicas(List<UnidadesMedicas> listaUnidadesMedicas) {
        this.listaUnidadesMedicas = listaUnidadesMedicas;
    }

    public Integer getIdUnidadesMedicas() {
        return idUnidadesMedicas;
    }

    public void setIdUnidadesMedicas(Integer idUnidadesMedicas) {
        this.idUnidadesMedicas = idUnidadesMedicas;
    }

    public boolean isBvercorreos() {
        return bvercorreos;
    }

    public void setBvercorreos(boolean bvercorreos) {
        this.bvercorreos = bvercorreos;
    }

    public List<Delegaciones> getListDelegaciones() {
        return listDelegaciones;
    }

    public void setListDelegaciones(List<Delegaciones> listDelegaciones) {
        this.listDelegaciones = listDelegaciones;
    }

    public Integer getIdDelegacion() {
        return idDelegacion;
    }

    public void setIdDelegacion(Integer idDelegacion) {
        this.idDelegacion = idDelegacion;
    }

    public ContactosAlertasDpn getContactosAlertasDpn() {
        return contactosAlertasDpn;
    }

    public void setContactosAlertasDpn(ContactosAlertasDpn contactosAlertasDpn) {
        this.contactosAlertasDpn = contactosAlertasDpn;
    }

    public List<ContactosAlertasDpn> getListContactosAlertasDpn() {
        return listContactosAlertasDpn;
    }

    public void setListContactosAlertasDpn(List<ContactosAlertasDpn> listContactosAlertasDpn) {
        this.listContactosAlertasDpn = listContactosAlertasDpn;
    }

    public Integer getIdContacto() {
        return idContacto;
    }

    public void setIdContacto(Integer idContacto) {
        this.idContacto = idContacto;
    }

    public boolean isBunidadmed() {
        return bunidadmed;
    }

    public void setBunidadmed(boolean bunidadmed) {
        this.bunidadmed = bunidadmed;
    }

    public List<String> getTipoMapas() {
        return tipoMapas;
    }

    public void setTipoMapas(List<String> tipoMapas) {
        this.tipoMapas = tipoMapas;
    }

    public boolean isBmapasDisp() {
        return bmapasDisp;
    }

    public void setBmapasDisp(boolean bmapasDisp) {
        this.bmapasDisp = bmapasDisp;
    }

    public String getDestinatario() {
        return destinatario;
    }

    public void setDestinatario(String destinatario) {
        this.destinatario = destinatario;
    }

    public String getCopia() {
        return copia;
    }

    public void setCopia(String copia) {
        this.copia = copia;
    }

    public String getAsunto() {
        return asunto;
    }

    public void setAsunto(String asunto) {
        this.asunto = asunto;
    }

    public String getMensajeCorreo() {
        return mensajeCorreo;
    }

    public void setMensajeCorreo(String mensajeCorreo) {
        this.mensajeCorreo = mensajeCorreo;
    }

    public List<RepositorioDocumentosDTO> getListaRepoDocsDto() {
        return listaRepoDocsDto;
    }

    public void setListaRepoDocsDto(List<RepositorioDocumentosDTO> listaRepoDocsDto) {
        this.listaRepoDocsDto = listaRepoDocsDto;
    }

}
