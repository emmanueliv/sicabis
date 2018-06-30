/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.issste.sicabis.controller;

import com.issste.sicabis.ejb.ln.ConfiguracionesService;
import com.issste.sicabis.ejb.ln.PerfilesService;
import com.issste.sicabis.ejb.modelo.Usuarios;
import com.issste.sicabis.ejb.ln.UsuariosService;
import com.issste.sicabis.ejb.modelo.Perfiles;
import com.issste.sicabis.utils.Accesos;
import com.issste.sicabis.utils.Mensajes;
import com.issste.sicabis.utils.Utilidades;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.EJB;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.apache.commons.codec.digest.DigestUtils;

/**
 *
 * @author Toshiba Manolo
 */
public class LoginBean {

    @EJB
    private PerfilesService perfilesService;

    @EJB
    private ConfiguracionesService configuracionesService;

    @EJB
    private UsuariosService usuariosService;

    private Usuarios usuarios;
    private Accesos accesos;
    private Perfiles perfiles;

    private String alertaDPN;
    private String usuario;
    private String contrasenia;
    private boolean isLogged = false;
    private String nombre;
    private String ctxPath;
    private Integer idParametro;
    private boolean esConsulta;
    private int tabNuevoActivo;
    private Mensajes messages = new Mensajes();
    public static Map<Usuarios, HttpSession> logins = new HashMap<Usuarios, HttpSession>();
    public String mensajeTablaVacia;
    private Utilidades util = new Utilidades();

    public LoginBean() {
        mensajeTablaVacia = messages.mensajeTablaVacia;
        ExternalContext ctx = FacesContext.getCurrentInstance().getExternalContext();
        ctxPath = ((ServletContext) ctx.getContext()).getContextPath();
        accesos = new Accesos();
        tabNuevoActivo = 0;
    }

    //Método para validar el usuario al momento de iniciar sesión.
    public void login(HttpServletRequest request, HttpServletResponse response) {
        System.out.println("Validando Usuario y Contrasenia");
        ExternalContext ctx = FacesContext.getCurrentInstance().getExternalContext();
        String action = "";
        //usuariosService = new UsuariosService();
        usuarios = usuariosService.obtenerUsuario(usuario);
        if (usuarios != null) {
            //if (usuarios.getContrasenia().equals(DigestUtils.md5Hex(this.contrasenia)) || configurations.getValue().equals(DigestUtils.md5Hex(this.password))) {
            if (usuarios.getContrasenia().equals(DigestUtils.md5Hex(this.contrasenia))) {
                HttpSession session = request.getSession();
                session.setMaxInactiveInterval(-1);
                logins.put(this.usuarios, session);
                this.setIsLogged(true);
                //cargaMenus(this.users.getProfileId().getProfileId());
                session.setAttribute("usuario", this.usuarios);
                nombre = usuarios.getNombre();
                try {
                    perfiles = new Perfiles();
                    perfiles = perfilesService.obtenerPerfilByIdUsuario(usuarios.getIdUsuario());
                    session.setAttribute("perfil", perfiles);
                    esConsulta = perfiles.getConsulta() == 1;
                    if (esConsulta) {
                        tabNuevoActivo = 1;
                    } else {
                        tabNuevoActivo = 0;
                    }
                    accesos.cargaMenus(perfiles);
                    if (validarCambioContrasenia(usuarios) && alertaDPN == null) {
                        session.setAttribute("breadCrumbVisible", 0);
                        ctx.redirect("../vistas/administracion/cambioContrasenia.xhtml");
                    } else if (!validarCambioContrasenia(usuarios) && alertaDPN == null) {
                        session.setAttribute("breadCrumbVisible", 1);
                        ctx.redirect("menuInicio.xhtml");
                    } else if (alertaDPN != null) {
                        session.setAttribute("breadCrumbVisible", 1);
                        ctx.redirect("rcb/alertasDpn.xhtml");
                        util.setContextAtributte("usuarioPerfilAlertaDPN", this.usuario);
                    }
                } catch (IOException ex) {
                    Logger.getLogger(LoginBean.class.getName()).log(Level.SEVERE, null, ex);
                }
            } else {
                messages.mensaje("Usuario y/o contraseña incorrecto", "rojo");
            }
        } else {
            messages.mensaje("Usuario y/o contraseña incorrecta", "rojo");
        }
        alertaDPN = null;
    }

    /**
     * Metodo para la validacion de cambio de contrasenia
     *
     * @param usuarios
     * @return cambioContrasenia
     */
    public boolean validarCambioContrasenia(Usuarios usuarios) {
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
        boolean cambioContrasenia = false;
        if (usuarios.getFechaCambioContrasenia() == null) {
            //validacion para cambio de contrasenia por ingreso 1ra vez
            cambioContrasenia = true;
        } else {
            //Validacion para fecha de modificacion de contrasenia por fecha de ultima modificacion
            Date today = getFecha(new Date(), 0);
            Date fecCambioContrasenia = getFecha(usuarios.getFechaCambioContrasenia(), 1);
            if (df.format(today).equals(df.format(fecCambioContrasenia))) {
                cambioContrasenia = true;
            }
        }
        return cambioContrasenia;
    }

    public void tipoOrdenSuministro(int opcion) {
        util.setContextAtributte("opcionOrden", opcion);
    }

    public Date getFecha(Date date, int mes) {
        Calendar calendar = GregorianCalendar.getInstance();
        calendar.set(date.getYear(), date.getMonth(), date.getDate(), 0, 0, 0);
        if (mes > 0) {
            int meses = configuracionesService.getValorParametroByParam("DIAS_CAMBIO_CONTRASENIA");
            calendar.add(Calendar.MONTH, meses);
        }
        return calendar.getTime();
    }

    public void logout(HttpServletRequest request, HttpServletResponse response) throws Exception {
        HttpSession session = request.getSession(false);
        if (session != null) {
            session.invalidate();
            ExternalContext ctx = FacesContext.getCurrentInstance().getExternalContext();
            String ctxPath = ((ServletContext) ctx.getContext()).getContextPath();
            //System.out.println("ctxPath--->" + ctxPath);
            ctx.redirect(ctxPath);
        }
    }
    
    public void logout2() {
        FacesContext facesContext = FacesContext.getCurrentInstance();
        HttpSession session = (HttpSession) facesContext.getExternalContext().getSession(false);
        if (session != null) {
            session.invalidate();
            ExternalContext ctx = facesContext.getExternalContext();
            String ctxPath = ((ServletContext) ctx.getContext()).getContextPath();
            try {
                ctx.redirect(ctxPath);
            } catch (IOException ex) {
                Logger.getLogger(LoginBean.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    public String getUsuario() {
        return usuario;
    }

    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }

    public String getContrasenia() {
        return contrasenia;
    }

    public void setContrasenia(String contrasenia) {
        this.contrasenia = contrasenia;
    }

    public boolean isIsLogged() {
        return isLogged;
    }

    public void setIsLogged(boolean isLogged) {
        this.isLogged = isLogged;
    }

    public String getCtxPath() {
        return ctxPath;
    }

    public void setCtxPath(String ctxPath) {
        this.ctxPath = ctxPath;
    }

    public String getMensajeTablaVacia() {
        return mensajeTablaVacia;
    }

    public void setMensajeTablaVacia(String mensajeTablaVacia) {
        this.mensajeTablaVacia = mensajeTablaVacia;
    }

    public Integer getIdParametro() {
        return idParametro;
    }

    public void setIdParametro(Integer idParametro) {
        this.idParametro = idParametro;
    }

    public Accesos getAccesos() {
        return accesos;
    }

    public void setAccesos(Accesos accesos) {
        this.accesos = accesos;
    }

    public boolean isEsConsulta() {
        return esConsulta;
    }

    public void setEsConsulta(boolean esConsulta) {
        this.esConsulta = esConsulta;
    }

    public int getTabNuevoActivo() {
        return tabNuevoActivo;
    }

    public void setTabNuevoActivo(int tabNuevoActivo) {
        this.tabNuevoActivo = tabNuevoActivo;
    }

    public String getAlertaDPN() {
        return alertaDPN;
    }

    public void setAlertaDPN(String alertaDPN) {
        this.alertaDPN = alertaDPN;
    }

    public Usuarios getUsuarios() {
        return usuarios;
    }

    public void setUsuarios(Usuarios usuarios) {
        this.usuarios = usuarios;
    }

}
