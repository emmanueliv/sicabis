package com.issste.sicabis.controller;

import com.issste.sicabis.DTO.MenuPerfilDTO;
import com.issste.sicabis.ejb.ln.BitacoraTareaSerice;
import com.issste.sicabis.ejb.ln.MenuPerfilService;
import com.issste.sicabis.ejb.ln.MenuService;
import com.issste.sicabis.utils.Mensajes;
import com.issste.sicabis.ejb.ln.PerfilesService;
import com.issste.sicabis.ejb.modelo.BitacoraTareaEstatus;
import com.issste.sicabis.ejb.modelo.Menu;
import com.issste.sicabis.ejb.modelo.MenuPerfil;
import com.issste.sicabis.ejb.modelo.Perfiles;
import com.issste.sicabis.ejb.modelo.Usuarios;
import com.issste.sicabis.utils.Utilidades;
import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Objects;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.servlet.ServletContext;
import org.primefaces.context.RequestContext;
import org.primefaces.model.DualListModel;

public class Perfiles2Bean implements Serializable {

    @EJB
    private BitacoraTareaSerice bitacoraTareaSerice;

    @EJB
    private MenuPerfilService menuPerfilService;

    @EJB
    private PerfilesService perfilesService;

    @EJB
    private MenuService menuService;

    private Perfiles perfiles;
    private Usuarios usuarios;
    private Menu menu;
    private BitacoraTareaEstatus bitacoraTareaEstatus;
    private MenuPerfilDTO menuPerfilDTO;
    private MenuPerfil menuPerfil;

    private String nombre;
    private Integer activo;
    private Integer idPerfil;
    private List<Menu> listaMenuPrincipal;
    private List<MenuPerfilDTO> listaMenuPerfilDTO;
    private List<Menu> listaMenu;
    private List<MenuPerfil> listaMenuPerfil;
    private List<Perfiles> listaPerfiles;

    private Mensajes mensaje = new Mensajes();
    private Utilidades util = new Utilidades();

    @PostConstruct
    public void init() {
        usuarios = new Usuarios();
        bitacoraTareaEstatus = new BitacoraTareaEstatus();
        menu = new Menu();
        usuarios = (Usuarios) util.getSessionAtributte("usuario");
        perfiles = perfilesService.obtenerPerfilByIdUsuario(usuarios.getIdUsuario());
        idPerfil = perfiles.getIdPerfil();
        perfiles = new Perfiles();
        this.inicializa();
    }

    public void inicializa() {
        nombre = "";
        listaMenuPrincipal = menuService.obtenerMenuByIdMenuPadre(0, 1);
        if (listaMenuPrincipal != null) {
            listaMenuPerfilDTO = new ArrayList<>();
            for (Menu menu : listaMenuPrincipal) {
                menuPerfilDTO = new MenuPerfilDTO();
                menuPerfilDTO.setMenu(menu);
                listaMenu = menuService.obtenerMenuByFinalIdTarea(1, menu.getIdTarea().getIdTarea());
                if (listaMenu != null) {
                    listaMenuPerfil = new ArrayList<>();
                    for (Menu m2 : listaMenu) {
                        menuPerfil = new MenuPerfil();
                        menuPerfil.setIdMenu(m2);
                        menuPerfil.setConsulta(-1);
                        listaMenuPerfil.add(menuPerfil);
                    }
                }
                menuPerfilDTO.setListMenuPerfil(listaMenuPerfil);
                listaMenuPerfilDTO.add(menuPerfilDTO);
            }
        }
        listaMenuPerfil = new ArrayList<>();
    }

    public void agrega(MenuPerfil mpAux) {
        boolean bandera = true;
        List<MenuPerfil> listaMenuPerfilAux = new ArrayList<>();
        System.out.println("lista--->" + listaMenuPerfil.size());
        if (listaMenuPerfil.size() == 0) {
            menuPerfil = new MenuPerfil();
            menuPerfil.setConsulta(mpAux.getConsulta());
            menuPerfil.setIdMenu(mpAux.getIdMenu());
            menuPerfil.setIdPerfil(perfiles);
            menuPerfil.setActivo(1);
            menuPerfil.setFechaAlta(new Date());
            menuPerfil.setUsuarioAlta(usuarios.getUsuario());
            listaMenuPerfilAux.add(menuPerfil);
            bandera = false;
        } else {
            for (MenuPerfil mp : listaMenuPerfil) {
                System.out.println("am---->" + mpAux.getIdMenu().getIdMenu());
                System.out.println("m---->" + mp.getIdMenu().getIdMenu());
                if (mpAux.getIdMenu().getIdMenu().intValue() == mp.getIdMenu().getIdMenu().intValue()) {
                    bandera = false;
                    System.out.println("mpAux.getConsulta()-->" + mpAux.getConsulta());
                    if (mpAux.getConsulta() != -1) {
                        menuPerfil = new MenuPerfil();
                        menuPerfil.setConsulta(mpAux.getConsulta());
                        menuPerfil.setIdMenu(mpAux.getIdMenu());
                        menuPerfil.setIdPerfil(perfiles);
                        menuPerfil.setActivo(1);
                        menuPerfil.setFechaAlta(new Date());
                        menuPerfil.setUsuarioAlta(usuarios.getUsuario());
                        listaMenuPerfilAux.add(menuPerfil);
                    }
                } else {
                    System.out.println("mp.getConsulta()-->" + mp.getConsulta());
                    listaMenuPerfilAux.add(mp);
                }
            }
        }
        if (bandera) {
            menuPerfil = new MenuPerfil();
            menuPerfil.setConsulta(mpAux.getConsulta());
            menuPerfil.setIdMenu(mpAux.getIdMenu());
            menuPerfil.setIdPerfil(perfiles);
            menuPerfil.setActivo(1);
            menuPerfil.setFechaAlta(new Date());
            menuPerfil.setUsuarioAlta(usuarios.getUsuario());
            listaMenuPerfilAux.add(menuPerfil);
        }
        System.out.println("listaAux--->" + listaMenuPerfilAux.size());
        listaMenuPerfil.clear();
        listaMenuPerfil = listaMenuPerfilAux;
    }

    public void guardaPerfil() {
        if (valida()) {
            perfiles.setActivo(1);
            perfiles.setEdita(0);
            perfiles.setConsulta(0);
            perfiles.setFechaAlta(new Date());
            perfiles.setUsuarioAlta(usuarios.getUsuario());
            perfiles.setNombre(nombre);
            perfiles.setMenuPerfilList(listaMenuPerfil);
            if (perfilesService.guardaPerfilService(perfiles)) {
                mensaje.mensaje(mensaje.datos_guardados, "verde");
                bitacoraTareaEstatus.setDescripcion("Guardar perfil:" + nombre + "");
                bitacoraTareaEstatus.setFecha(new Date());
                bitacoraTareaEstatus.setIdModulos(4);
                bitacoraTareaEstatus.setIdUsuarios(usuarios.getIdUsuario());
                bitacoraTareaEstatus.setIdTareaId(16);
                bitacoraTareaSerice.guardarEnBitacora(bitacoraTareaEstatus);
                this.inicializa();
            }
        }
    }

    public void obtenerPerfiles() {
        listaPerfiles = new ArrayList();
        List<Perfiles> perfilesAux = perfilesService.getByNombreActivo(nombre, activo);
        // Se agrego condicion para validar si la lista viene en null
        if (perfilesAux != null) {
            for (Perfiles perfil : perfilesAux) {
                if (!Objects.equals(perfil.getIdPerfil(), idPerfil) && !perfil.getNombre().equals("administrador")) {
                    listaPerfiles.add(perfil);
                }
            }
        }
    }

    public void cancel() {
        ExternalContext ctx = FacesContext.getCurrentInstance().getExternalContext();
        String ctxPath = ((ServletContext) ctx.getContext()).getContextPath();
        try {
            ctx.redirect(ctxPath + "/vistas/administracion/perfiles2.xhtml");
        } catch (IOException ex) {
            Logger.getLogger(PerfilesBean.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void cancelDialog() {
        RequestContext.getCurrentInstance().execute("PF('dialogElimina').hide();");
    }

    public void modificarRedirect(Perfiles perfiles) {
        ExternalContext ctx = FacesContext.getCurrentInstance().getExternalContext();
        String ctxPath = ((ServletContext) ctx.getContext()).getContextPath();
        util.setContextAtributte("perfil", perfiles);
        try {
            ctx.redirect(ctxPath + "/vistas/administracion/detallePerfiles2.xhtml");
        } catch (IOException ex) {
            Logger.getLogger(PerfilesBean.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void eliminar(Perfiles perfiles) {
        nombre = perfiles.getNombre();
        this.perfiles = perfiles;
        RequestContext.getCurrentInstance().execute("PF('dialogElimina').show();");
    }

    public boolean existePerfil() {
        boolean bandera = true;
        bandera = perfilesService.obtenerPerfilByNombreService(nombre, 0);
        return bandera;
    }

    public void validaNombre() {
        if (!existePerfil()) {
            nombre = "";
            mensaje.mensaje("El perfil que desea agregar ya existe", "amarillo");
        }
    }

    public boolean valida() {
        boolean bandera = true;
        if (nombre.equals("") || nombre == null) {
            mensaje.mensaje("Debe capturar el nombre del perfil", "amarillo");
            bandera = false;
        } else {
            validaNombre();
        }
        if (listaMenuPerfil.size() == 0) {
            mensaje.mensaje("Debe seleccionar al menos un menú de las opciones", "amarillo");
            bandera = false;
        }
        return bandera;
    }

    public void eliminaPerfil() {
        perfiles.setActivo(0);
        perfiles.setFechaBaja(new Date());
        perfiles.setUsuarioBaja(usuarios.getUsuario());
        if (perfilesService.guardaPerfilService(perfiles)) {
            mensaje.mensaje(mensaje.datos_eliminados, "verde");
            bitacoraTareaEstatus.setDescripcion("Baja perfil:" + nombre + "");
            bitacoraTareaEstatus.setFecha(new Date());
            bitacoraTareaEstatus.setIdModulos(4);
            bitacoraTareaEstatus.setIdUsuarios(usuarios.getIdUsuario());
            bitacoraTareaEstatus.setIdTareaId(16);
            bitacoraTareaSerice.guardarEnBitacora(bitacoraTareaEstatus);
        }
    }

    public Perfiles getPerfiles() {
        return perfiles;
    }

    public void setPerfiles(Perfiles perfiles) {
        this.perfiles = perfiles;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public Integer getActivo() {
        return activo;
    }

    public void setActivo(Integer activo) {
        this.activo = activo;
    }

    public List<MenuPerfilDTO> getListaMenuPerfilDTO() {
        return listaMenuPerfilDTO;
    }

    public void setListaMenuPerfilDTO(List<MenuPerfilDTO> listaMenuPerfilDTO) {
        this.listaMenuPerfilDTO = listaMenuPerfilDTO;
    }

    public List<Perfiles> getListaPerfiles() {
        return listaPerfiles;
    }

    public void setListaPerfiles(List<Perfiles> listaPerfiles) {
        this.listaPerfiles = listaPerfiles;
    }

    public Mensajes getMensaje() {
        return mensaje;
    }

    public void setMensaje(Mensajes mensaje) {
        this.mensaje = mensaje;
    }

}
