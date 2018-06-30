/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.issste.sicabis.controller;

import com.issste.sicabis.ejb.ln.BitacoraTareaSerice;
import com.issste.sicabis.ejb.ln.DelegacionesService;
import com.issste.sicabis.ejb.ln.PerfilesService;
import com.issste.sicabis.ejb.ln.TipoUsuariosService;
import com.issste.sicabis.ejb.ln.UnidadMedicaService;
import com.issste.sicabis.ejb.ln.UrService;
import com.issste.sicabis.ejb.ln.UsuariosService;
import com.issste.sicabis.ejb.modelo.Area;
import com.issste.sicabis.ejb.modelo.BitacoraTareaEstatus;
import com.issste.sicabis.ejb.modelo.Delegaciones;
import com.issste.sicabis.ejb.modelo.Perfiles;
import com.issste.sicabis.ejb.modelo.TipoUsuarios;
import com.issste.sicabis.ejb.modelo.UnidadesMedicas;
import com.issste.sicabis.ejb.modelo.Ur;
import com.issste.sicabis.ejb.modelo.UsuarioPerfil;
import com.issste.sicabis.ejb.modelo.Usuarios;
import com.issste.sicabis.ejb.modelo.UsuariosTipoUsuarios;
import com.issste.sicabis.utils.Mensajes;
import com.issste.sicabis.utils.Utilidades;
import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.servlet.ServletContext;
import org.apache.commons.codec.digest.DigestUtils;
import org.primefaces.context.RequestContext;

public class UsuariosBean implements Serializable {

    @EJB
    private UrService urService;

    @EJB
    private DelegacionesService delegacionesService;

    @EJB
    private BitacoraTareaSerice bitacoraTareaSerice;

    @EJB
    private TipoUsuariosService tipoUsuariosService;

    @EJB
    private UsuariosService usuariosService;

    @EJB
    private PerfilesService perfilesService;

    @EJB
    private UnidadMedicaService unidadesService;

    private String nombreUsuario;
    private Usuarios usuariosLogin;
    private Usuarios usuarioNuevo;
    private Usuarios usuarioEliminar;
    private List<Usuarios> usuariosList;
    private List<Area> areasList;
    private Area areaSelect;
    private List<Perfiles> perfilesList;
    private List<Perfiles> perfilesListTemp;
    private List<UnidadesMedicas> unidadesList;
    private Perfiles perfil;
    private TipoUsuarios tipoUsuario;
    private List<TipoUsuarios> listaTipoUsuarios;
    private BitacoraTareaEstatus bitacora;
    private List<String> tiposUsuariosSelect;
    private List<Delegaciones> delegacionesList;
    private Delegaciones delegaciones;
    private UnidadesMedicas unidadesMedicas;
    private List<Ur> listUr;
    private Integer ur;

    private Utilidades util = new Utilidades();
    private Mensajes mensaje = new Mensajes();

    public UsuariosBean() {
        usuariosLogin = (Usuarios) util.getSessionAtributte("usuario");
        tipoUsuario = new TipoUsuarios();
        listaTipoUsuarios = new ArrayList<>();
        bitacora = new BitacoraTareaEstatus();
        tiposUsuariosSelect = new ArrayList<>();
        unidadesMedicas = new UnidadesMedicas(1);
        listUr = new ArrayList<>();
    }

    @PostConstruct
    public void init() {
        areasList = new ArrayList<>();
        unidadesList = new ArrayList<>();
        areasList = usuariosService.getAreas();
        usuariosList = new ArrayList<>();
        usuarioNuevo = new Usuarios();
        perfil = new Perfiles();
        perfilesList = new ArrayList<>();
        perfilesListTemp = new ArrayList<>();
        perfilesListTemp = perfilesService.obtenerPerfilesService();
        for (Perfiles iterator : perfilesListTemp) {
//            if (!iterator.getNombre().equals("administrador")) {
            perfilesList.add(iterator);
//            }
        }
        unidadesList = unidadesService.unidadMedicaByActivo();
        listaTipoUsuarios = tipoUsuariosService.obtenerListaTiposUsuarios("");
        delegacionesList = delegacionesService.obtenerDelegaciones();
        listUr = urService.getAll();
    }

    public void generarUsuario() {
        usuarioNuevo.setUsuario(usuarioNuevo.getNombre().toLowerCase());
    }

    public void altaUsuario() {
        if (validarUsuario()) {
            if (existeUsuario()) {
                //Datos generales del usuario
                usuarioNuevo.setIdUsuarioSuperior(usuariosLogin.getIdUsuario());
                usuarioNuevo.setFechaAlta(new Date());
                usuarioNuevo.setActivo(1);
                //usuarioNuevo.setIdArea(areaSelect);
                usuarioNuevo.setContrasenia(DigestUtils.md5Hex("SICABIS"));
                //Perfil asignado al usuario
                UsuarioPerfil usuarioPerfil = new UsuarioPerfil();
                usuarioPerfil.setIdPerfil(perfil);
                usuarioPerfil.setFechaAlta(new Date());
                usuarioPerfil.setUsuarioAlta(usuariosLogin.getNombre());
                usuarioPerfil.setIdUsuario(usuarioNuevo);
                //Guardado de usuario y perfil
                usuariosService.guardarUsuario(usuarioNuevo, usuarioPerfil, tiposUsuariosSelect, 0);
                String nombreUsuario = usuarioNuevo.getUsuario();
                usuarioNuevo = new Usuarios();
                tiposUsuariosSelect = new ArrayList<>();
                perfil = new Perfiles();
                //Bitacora
                bitacora.setDescripcion("Guadar usuario");
                bitacora.setFecha(new Date());
                bitacora.setIdTareaId(16);
                bitacora.setIdUsuarios(usuariosLogin.getIdUsuario());
                bitacora.setIdModulos(5);
                bitacoraTareaSerice.guardarEnBitacora(bitacora);
                mensaje.mensaje("El usuario " + nombreUsuario + " se ha guardado correctamente.", "verde");
            } else {
                mensaje.mensaje("El usuario que desea agregar ya existe", "rojo");
            }
        }
    }
    
    public void cambiaUr() {
        System.out.println("ur--->" + usuarioNuevo.getUr());
        System.out.println("ur dele--->" + usuarioNuevo.getUr().getIdDelegacion());
        usuarioNuevo.setIdDelegacion(usuarioNuevo.getUr().getIdDelegacion());
        this.cambiaDelegación();
    }

    public void cambiaDelegación() {
        System.out.println("dele--->" + usuarioNuevo.getIdDelegacion());
        usuarioNuevo.setIdUnidadMedica(null);
        if (usuarioNuevo.getIdDelegacion() != null) {
            if (usuarioNuevo.getIdDelegacion().getIdDelegacion() == 80) {
                unidadesList = unidadesService.getByHospitalRegional(1);
            } else {
                unidadesList = usuarioNuevo.getIdDelegacion().getUnidadesMedicasList();
            }
        } else {
            unidadesList = unidadesService.unidadMedicaByActivo();
        }
    }

    public boolean existeUsuario() {
        Usuarios userNew = usuariosService.obtenerUsuario(usuarioNuevo.getUsuario());
        if (userNew != null) {
            return false;
        } else {
            return true;
        }
    }

    public boolean validarUsuario() {
        boolean valido = true;

        if (usuarioNuevo.getApellidoMaterno() == null || usuarioNuevo.getApellidoMaterno().equals("")) {
            mensaje.mensaje("Debes capturar el apellido materno", "amarillo");
            valido = false;
        }
        if (usuarioNuevo.getApellidoPaterno() == null || usuarioNuevo.getApellidoPaterno().equals("")) {
            mensaje.mensaje("Debes capturar el apellido materno", "amarillo");
            valido = false;
        }
        if (!usuarioNuevo.getCorreo().equals("")) {
            if (!util.validaCorreo(usuarioNuevo.getCorreo())) {
                mensaje.mensaje("El correo capturado es incorrecto", "amarillo");
                valido = false;
            }
        }
        if (usuarioNuevo.getUsuario() == null || usuarioNuevo.getUsuario().equals("")) {
            mensaje.mensaje("Debes capturar el nombre de usuario", "amarillo");
            valido = false;
        } else if (usuarioNuevo.getUsuario().length() < 6) {
            mensaje.mensaje("El nombre de usuario debe contener almenos 6 caracteres", "amarillo");
            valido = false;
        }
        if (usuarioNuevo.getIdArea() == null) {
            mensaje.mensaje("Debes seleccionar el área", "amarillo");
            valido = false;
        }
        if(usuarioNuevo.getUr() == null){
            mensaje.mensaje("Debes seleccionar la UR", "amarillo");
            valido = false;
        }
        if (perfil == null) {
            mensaje.mensaje("Debes seleccionar el perfil", "amarillo");
            valido = false;
        }
        System.out.println("valido-->" + usuarioNuevo.getIdUnidadMedica());
        return valido;
    }

    public void modificarRedirect(Usuarios usuario) {
        ExternalContext ctx = FacesContext.getCurrentInstance().getExternalContext();
        String ctxPath = ((ServletContext) ctx.getContext()).getContextPath();
        util.setSessionMapValue("usuarioModificar", usuario);
        try {
            ctx.redirect(ctxPath + "/vistas/administracion/detalleUsuarios.xhtml");
        } catch (IOException ex) {
            Logger.getLogger(PerfilesBean.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void mostrarDialogo(Usuarios usuario) {
        usuarioEliminar = usuario;
        nombreUsuario = usuario.getUsuario();
        RequestContext.getCurrentInstance().execute("PF('dialogEliminaUsuario').show();");
    }

    public void eliminarUsuario() {
        usuarioEliminar.setFechaBaja(new Date());
        usuarioEliminar.setActivo(0);
        usuarioEliminar.setUsuarioBaja(usuariosLogin.getNombre());
        usuariosService.guardarUsuario(usuarioEliminar, null, null, 1);
        mensaje.mensaje("El usuario ha sido dado de baja", "rojo");
        consultarUsuarios();
    }

    public void consultarUsuarios() {
        usuariosList = new ArrayList<>();
        if (!nombreUsuario.equals("") && areaSelect != null) {
            usuariosList = usuariosService.getUsuarioAreayUsuario(nombreUsuario, areaSelect.getIdArea(), usuariosLogin.getIdUsuario());
        } else if (nombreUsuario.equals("") && areaSelect == null) {
            usuariosList = usuariosService.getUsuariosByIdPadre(usuariosLogin.getIdUsuario());
        } else if (areaSelect != null) {
            usuariosList = usuariosService.getUsuarioAreayUsuario("", areaSelect.getIdArea(), usuariosLogin.getIdUsuario());
        } else {
            usuariosList = usuariosService.getUsuarioAreayUsuario(nombreUsuario, null, usuariosLogin.getIdUsuario());

        }
        if (usuariosList == null || usuariosList.isEmpty()) {
            mensaje.mensaje("No se encontraron resultados.", "amarillo");
        }
    }

    public void cancel() {
        usuarioNuevo = new Usuarios();
        ExternalContext ctx = FacesContext.getCurrentInstance().getExternalContext();
        String ctxPath = ((ServletContext) ctx.getContext()).getContextPath();
        try {
            ctx.redirect(ctxPath + "/vistas/administracion/usuarios.xhtml");
        } catch (IOException ex) {
            Logger.getLogger(PerfilesBean.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public Usuarios getUsuariosLogin() {
        return usuariosLogin;
    }

    public void setUsuariosLogin(Usuarios usuariosLogin) {
        this.usuariosLogin = usuariosLogin;
    }

    public Usuarios getUsuarioNuevo() {
        return usuarioNuevo;
    }

    public void setUsuarioNuevo(Usuarios usuarioNuevo) {
        this.usuarioNuevo = usuarioNuevo;
    }

    public List<Usuarios> getUsuariosList() {
        return usuariosList;
    }

    public void setUsuariosList(List<Usuarios> usuariosList) {
        this.usuariosList = usuariosList;
    }

    public Utilidades getUtil() {
        return util;
    }

    public void setUtil(Utilidades util) {
        this.util = util;
    }

    public List<Area> getAreasList() {
        return areasList;
    }

    public void setAreasList(List<Area> areasList) {
        this.areasList = areasList;
    }

    public Area getAreaSelect() {
        return areaSelect;
    }

    public void setAreaSelect(Area areaSelect) {
        this.areaSelect = areaSelect;
    }

    public String getNombreUsuario() {
        return nombreUsuario;
    }

    public void setNombreUsuario(String nombreUsuario) {
        this.nombreUsuario = nombreUsuario;
    }

    public Usuarios getUsuarioEliminar() {
        return usuarioEliminar;
    }

    public void setUsuarioEliminar(Usuarios usuarioEliminar) {
        this.usuarioEliminar = usuarioEliminar;
    }

    public List<Perfiles> getPerfilesList() {
        return perfilesList;
    }

    public void setPerfilesList(List<Perfiles> perfilesList) {
        this.perfilesList = perfilesList;
    }

    public Perfiles getPerfil() {
        return perfil;
    }

    public void setPerfil(Perfiles perfil) {
        this.perfil = perfil;
    }

    public List<UnidadesMedicas> getUnidadesList() {
        return unidadesList;
    }

    public void setUnidadesList(List<UnidadesMedicas> unidadesList) {
        this.unidadesList = unidadesList;
    }

    public List<TipoUsuarios> getListaTipoUsuarios() {
        return listaTipoUsuarios;
    }

    public void setListaTipoUsuarios(List<TipoUsuarios> listaTipoUsuarios) {
        this.listaTipoUsuarios = listaTipoUsuarios;
    }

    public TipoUsuarios getTipoUsuario() {
        return tipoUsuario;
    }

    public void setTipoUsuario(TipoUsuarios tipoUsuario) {
        this.tipoUsuario = tipoUsuario;
    }

    public List<String> getTiposUsuariosSelect() {
        return tiposUsuariosSelect;
    }

    public void setTiposUsuariosSelect(List<String> tiposUsuariosSelect) {
        this.tiposUsuariosSelect = tiposUsuariosSelect;
    }

    public Mensajes getMensaje() {
        return mensaje;
    }

    public void setMensaje(Mensajes mensaje) {
        this.mensaje = mensaje;
    }

    public List<Delegaciones> getDelegacionesList() {
        return delegacionesList;
    }

    public void setDelegacionesList(List<Delegaciones> delegacionesList) {
        this.delegacionesList = delegacionesList;
    }

    public Delegaciones getDelegaciones() {
        return delegaciones;
    }

    public void setDelegaciones(Delegaciones delegaciones) {
        this.delegaciones = delegaciones;
    }

    public UnidadesMedicas getUnidadesMedicas() {
        return unidadesMedicas;
    }

    public void setUnidadesMedicas(UnidadesMedicas unidadesMedicas) {
        this.unidadesMedicas = unidadesMedicas;
    }

    public List<Ur> getListUr() {
        return listUr;
    }

    public void setListUr(List<Ur> listUr) {
        this.listUr = listUr;
    }

    public Integer getUr() {
        return ur;
    }

    public void setUr(Integer ur) {
        this.ur = ur;
    }

}
