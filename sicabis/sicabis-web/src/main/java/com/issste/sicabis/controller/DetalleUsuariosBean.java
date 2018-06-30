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
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Objects;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import org.apache.commons.codec.digest.DigestUtils;

/**
 *
 * @author kriosoft
 */
public class DetalleUsuariosBean implements Serializable {

    @EJB
    private UrService urService;

    @EJB
    private DelegacionesService delegacionesService;

    @EJB
    private UsuariosService usuariosService;

    @EJB
    private PerfilesService perfilesService;

    @EJB
    private UnidadMedicaService unidadesService;

    @EJB
    private TipoUsuariosService tipoUsuariosService;

    @EJB
    private BitacoraTareaSerice bitacoraTareaSerice;

    private Usuarios usuarioLogin;
    private Usuarios usuarioNuevo;
    private List<Area> areasList;
    private Area areaSelect;
    private UsuarioPerfil usuarioPerfil;
    private List<Perfiles> perfilesList;
    private List<UnidadesMedicas> unidadesList;
    private Perfiles perfil;
    private String contrasenia;
    private UsuariosTipoUsuarios usuariosTipoUsuarios;
    private List<TipoUsuarios> listaTipoUsuarios;
    private BitacoraTareaEstatus bitacora;
    private List<String> tiposUsuariosSelect;
    private List<UsuariosTipoUsuarios> listUsuariosTipoUsuarios;
    private List<Delegaciones> delegacionesList;
    private Delegaciones delegaciones;
    private UnidadesMedicas unidadesMedicas;
    private boolean bresetpass;
    private Perfiles perfilLogin;
    private String nombreUsuarioInicial;
    private List<Ur> listUr;

    private Utilidades util = new Utilidades();
    private Mensajes mensaje = new Mensajes();

    public DetalleUsuariosBean() {
        areasList = new ArrayList<>();
        areaSelect = new Area();
        usuarioLogin = new Usuarios();
        perfil = new Perfiles();
        usuarioPerfil = new UsuarioPerfil();
        perfilesList = new ArrayList<>();
        unidadesList = new ArrayList<>();
        usuariosTipoUsuarios = new UsuariosTipoUsuarios();
        listaTipoUsuarios = new ArrayList<>();
        listUsuariosTipoUsuarios = new ArrayList<>();
        bitacora = new BitacoraTareaEstatus();
        tiposUsuariosSelect = new ArrayList<>();
        bitacora = new BitacoraTareaEstatus();
        unidadesMedicas = new UnidadesMedicas(1);
        listUr = new ArrayList<>();
    }

    @PostConstruct
    public void init() {
        bresetpass = false;
        usuarioLogin = (Usuarios) util.getSessionAtributte("usuario");
        usuarioNuevo = (Usuarios) util.getSessionAtributte("usuarioModificar");
        perfilLogin = perfilesService.obtenerPerfilByIdUsuario(usuarioLogin.getIdUsuario());
        nombreUsuarioInicial = usuarioNuevo.getUsuario();
        usuarioPerfil = usuariosService.getUsuarioPerfil(usuarioNuevo.getIdUsuario());
        if (usuarioNuevo != null) {
            contrasenia = usuarioNuevo.getContrasenia();
            areasList = usuariosService.getAreas();
            areaSelect = usuarioNuevo.getIdArea();
            perfilesList = perfilesService.obtenerPerfilesService();
            perfil = perfilesService.obtenerPerfilByIdUsuario(usuarioNuevo.getIdUsuario());
        }
        unidadesList = unidadesService.unidadMedicaByActivo();
        listUsuariosTipoUsuarios = usuariosService.obtenerTiposUsuariosByIdUsuario(usuarioNuevo.getIdUsuario());
        if (listUsuariosTipoUsuarios != null) {
            for (UsuariosTipoUsuarios iterator : listUsuariosTipoUsuarios) {
                tiposUsuariosSelect.add(iterator.getIdTipoUsuario().getIdTipoUsuario().toString());
            }
        }
        listaTipoUsuarios = tipoUsuariosService.obtenerListaTiposUsuarios("");
        delegacionesList = delegacionesService.obtenerDelegaciones();
        listUr = urService.getAll();
    }

    public void guardarUsuario() {
        if (existeUsuario()) {
            usuarioNuevo.setFechaModificacion(new Date());
            //usuarioNuevo.setContrasenia(DigestUtils.md5Hex(usuarioNuevo.getContrasenia()));
            if (bresetpass == true) {
                usuarioNuevo.setContrasenia(DigestUtils.md5Hex("SICABIS"));
                usuarioNuevo.setFechaCambioContrasenia(null);
            }
            usuarioPerfil.setIdPerfil(perfil);
            usuarioPerfil.setActivo(1);
            usuarioPerfil.setFechaModificacion(new Date());
            usuarioPerfil.setUsuarioModificacion(usuarioLogin.getNombre());
            usuarioPerfil.setIdUsuario(usuarioNuevo);
            //Guardado de usuario y perfil
            usuariosService.guardarUsuario(usuarioNuevo, usuarioPerfil, tiposUsuariosSelect, 0);
            String nombreUsuario = usuarioNuevo.getUsuario();
            //Bitacora
            bitacora.setDescripcion("Guardar usuario");
            bitacora.setFecha(new Date());
            bitacora.setIdTareaId(16);
            bitacora.setIdUsuarios(usuarioLogin.getIdUsuario());
            bitacora.setIdModulos(5);
            bitacoraTareaSerice.guardarEnBitacora(bitacora);
            mensaje.mensaje("Se han modificado los datos del usuario " + usuarioNuevo.getUsuario(), "verde");
        } else {
            mensaje.mensaje("El usuario " + usuarioNuevo.getUsuario() + " ya existe", "rojo");
        }
    }
    
    public void cambiaUr() {
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
            if (nombreUsuarioInicial.equals(usuarioNuevo.getUsuario())) {
                return true;
            } else {
                return false;
            }
        } else {
            return true;
        }
    }

    public Usuarios getUsuarioLogin() {
        return usuarioLogin;
    }

    public void setUsuarioLogin(Usuarios usuarioLogin) {
        this.usuarioLogin = usuarioLogin;
    }

    public Usuarios getUsuarioNuevo() {
        return usuarioNuevo;
    }

    public void setUsuarioNuevo(Usuarios usuarioNuevo) {
        this.usuarioNuevo = usuarioNuevo;
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

    public List<String> getTiposUsuariosSelect() {
        return tiposUsuariosSelect;
    }

    public void setTiposUsuariosSelect(List<String> tiposUsuariosSelect) {
        this.tiposUsuariosSelect = tiposUsuariosSelect;
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

    public boolean isBresetpass() {
        return bresetpass;
    }

    public void setBresetpass(boolean bresetpass) {
        this.bresetpass = bresetpass;
    }

    public Perfiles getPerfilLogin() {
        return perfilLogin;
    }

    public void setPerfilLogin(Perfiles perfilLogin) {
        this.perfilLogin = perfilLogin;
    }

    public List<Ur> getListUr() {
        return listUr;
    }

    public void setListUr(List<Ur> listUr) {
        this.listUr = listUr;
    }

}
