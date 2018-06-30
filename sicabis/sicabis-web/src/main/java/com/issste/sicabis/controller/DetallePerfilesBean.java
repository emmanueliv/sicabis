/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.issste.sicabis.controller;

import com.issste.sicabis.ejb.ln.BitacoraTareaSerice;
import com.issste.sicabis.ejb.ln.MenuPerfilService;
import com.issste.sicabis.ejb.ln.MenuService;
import com.issste.sicabis.ejb.ln.PerfilesService;
import com.issste.sicabis.ejb.modelo.BitacoraTareaEstatus;
import com.issste.sicabis.ejb.modelo.Menu;
import com.issste.sicabis.ejb.modelo.MenuPerfil;
import com.issste.sicabis.ejb.modelo.Perfiles;
import com.issste.sicabis.ejb.modelo.Usuarios;
import com.issste.sicabis.utils.Mensajes;
import com.issste.sicabis.utils.Utilidades;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import org.primefaces.context.RequestContext;
import org.primefaces.model.DualListModel;

public class DetallePerfilesBean implements Serializable {

    @EJB
    private BitacoraTareaSerice bitacoraTareaSerice;

    @EJB
    private PerfilesService perfilesService;

    @EJB
    private MenuService menuService;

    @EJB
    private MenuPerfilService menuPerfilService;

    private Perfiles perfiles;
    private Usuarios usuarios;
    private Perfiles perfilesAux;
    private Utilidades util = new Utilidades();
    private String nombre;
    private boolean tipoPerfil;
    private int idMenuR;
    private List<Menu> listaMenu;
    private List<Menu> menuTarget;
    private List<Menu> menuSource;
    private List<Menu> listaMenuPrincipal;
    private List<Menu> listaMenuPrincipal2;
    private List<Menu> listaMenuPrincipalSeleccion;
    private DualListModel<Menu> listaDualMenu;
    private BitacoraTareaEstatus bitacoraTareaEstatus;
    private String[] seleccionMenuPrincipal;
    private boolean botonActualizar;
    private boolean botonGuardar = true;
    private int opcion;
    private Mensajes mensaje = new Mensajes();

    public DetallePerfilesBean() {
        bitacoraTareaEstatus = new BitacoraTareaEstatus();
    }

    @PostConstruct
    public void ModificarPerfilesBean() {
        perfiles = new Perfiles();
        usuarios = new Usuarios();
        menuTarget = new ArrayList<>();
        listaMenu = new ArrayList<>();
        menuSource = new ArrayList<>();
        listaMenuPrincipal = new ArrayList<>();
        listaMenuPrincipal2 = new ArrayList<>();
        listaMenuPrincipalSeleccion = new ArrayList<>();
        listaDualMenu = new DualListModel<>();
        opcion = 0;
        perfiles = (Perfiles) util.getSessionAtributte("perfil");
        usuarios = (Usuarios) util.getSessionAtributte("usuario");
        perfilesAux = perfiles;
        nombre = perfiles.getNombre();
        tipoPerfil = perfiles.getConsulta() == 1;
        seleccionMenuPrincipal = null;
        listaMenu = menuService.obtenerMenuService();
        for (Menu menuAux : listaMenu) {
            if (menuAux.getIdMenuPadre() == 0) {
                listaMenuPrincipal.add(menuAux);
                if (menuAux.getDescripcion().equals("ADMINISTRACIÓN")
                        || menuAux.getDescripcion().equals("REPORTES")
                        || menuAux.getDescripcion().equals("CATÁLOGOS")
                        || menuAux.getDescripcion().equals("AUDITORIA")) {
                    listaMenuPrincipal2.add(menuAux);
                } else {
                    listaMenuPrincipalSeleccion.add(menuAux);
                }
            }
        }

        int i = 0;
        boolean bandera = false;
        List<String> listaString = new ArrayList<>();
        if (!tipoPerfil) {
            for (MenuPerfil menuPerfilList : perfiles.getMenuPerfilList()) {
                bandera = false;
                if (i == 0) {
                    if (!menuPerfilList.getIdMenu().getIdTarea().getDescripcion().equals("ADMINISTRACIÓN")
                            && !menuPerfilList.getIdMenu().getIdTarea().getDescripcion().equals("REPORTES")
                            && !menuPerfilList.getIdMenu().getIdTarea().getDescripcion().equals("CATÁLOGOS")
                            && !menuPerfilList.getIdMenu().getIdTarea().getDescripcion().equals("AUDITORIA")) {
                        idMenuR = menuPerfilList.getIdMenu().getIdTarea().getIdTarea();
                    } else {
                        listaString.add(menuPerfilList.getIdMenu().getIdTarea().getIdTarea().toString());
                    }
                } else {
                    for (String listaux : listaString) {
                        if (listaux.equals(menuPerfilList.getIdMenu().getIdTarea().getIdTarea().toString())) {
                            bandera = true;
                        }
                    }
                    if (!bandera) {
                        if (!menuPerfilList.getIdMenu().getIdTarea().getDescripcion().equals("ADMINISTRACIÓN")
                                && !menuPerfilList.getIdMenu().getIdTarea().getDescripcion().equals("REPORTES")
                                && !menuPerfilList.getIdMenu().getIdTarea().getDescripcion().equals("CATÁLOGOS")
                                && !menuPerfilList.getIdMenu().getIdTarea().getDescripcion().equals("AUDITORIA")) {
                            idMenuR = menuPerfilList.getIdMenu().getIdTarea().getIdTarea();
                        } else {
                            listaString.add(menuPerfilList.getIdMenu().getIdTarea().getIdTarea().toString());
                        }
                    }
                }
                menuTarget.add(menuPerfilList.getIdMenu());
                i++;
            }
            i = 0;
            seleccionMenuPrincipal = new String[listaString.size()];
            for (String listaString1 : listaString) {
                seleccionMenuPrincipal[i] = listaString1;
                i++;
            }
            cambiaSeleccion(4);
        } else {
            for (MenuPerfil menuPerfilList : perfiles.getMenuPerfilList()) {
                bandera = false;
                if (seleccionMenuPrincipal != null) {
                    seleccionMenuPrincipal[i] = menuPerfilList.getIdMenu().getIdTarea().getIdTarea().toString();
                    for (int j = 0; j < seleccionMenuPrincipal.length; j++) {
                        if (seleccionMenuPrincipal[j].equals(menuPerfilList.getIdMenu().getIdTarea().getIdTarea().toString())) {
                            bandera = true;
                        }
                    }
                    if (!bandera) {
                        seleccionMenuPrincipal[i] = menuPerfilList.getIdMenu().getIdTarea().getIdTarea().toString();
                    }
                    menuTarget.add(menuPerfilList.getIdMenu());
                    i++;
                }
                cambiaSeleccion(1);
            }
            botonGuardar = false;
            botonActualizar = true;
            opcion = 1;
            RequestContext.getCurrentInstance().update("frmTab");
        }
    }

    public void cambiaTipo() {
        seleccionMenuPrincipal = null;
    }

    public void cambiaSeleccion(int opcion) {
        menuSource = new ArrayList<>();
        boolean bandera = false;
        if (seleccionMenuPrincipal != null) {
            switch (opcion) {
                case 1:
                    for (int i = 0; i < seleccionMenuPrincipal.length; i++) {
                        for (Menu listMenu : listaMenu) {
                            if (listMenu.getFinal1().equals(1)) {
                                if (Integer.parseInt(seleccionMenuPrincipal[i]) == listMenu.getIdTarea().getIdTarea()) {
                                    menuSource.add(listMenu);
                                }
                            }
                        }
                    }
                    break;
                case 2:
                    for (Menu listMenu : listaMenu) {
                        if (seleccionMenuPrincipal != null) {
                            for (int i = 0; i < seleccionMenuPrincipal.length; i++) {
                                if (listMenu.getFinal1().equals(1)) {
                                    if (Integer.parseInt(seleccionMenuPrincipal[i]) == listMenu.getIdTarea().getIdTarea()) {
                                        menuSource.add(listMenu);
                                    }
                                }
                            }
                        }
                        if (idMenuR == listMenu.getIdTarea().getIdTarea() && listMenu.getFinal1().equals(1)) {
                            menuSource.add(listMenu);
                        }
                    }
                    break;
                case 3:
                    break;
                case 4:
                    for (Menu listMenu : listaMenu) {
                        if (seleccionMenuPrincipal != null) {
                            for (int i = 0; i < seleccionMenuPrincipal.length; i++) {
                                if (listMenu.getFinal1().equals(1) && Integer.parseInt(seleccionMenuPrincipal[i]) == listMenu.getIdTarea().getIdTarea()) {
                                    bandera = false;
                                    for (MenuPerfil menuPerfilList : perfilesAux.getMenuPerfilList()) {
                                        if (menuPerfilList.getIdMenu().equals(listMenu.getIdMenu())) {
                                            bandera = true;
                                        }
                                    }
                                    if (!bandera) {
                                        menuSource.add(listMenu);
                                    }
                                }
                            }
                        }
                        if (idMenuR == listMenu.getIdTarea().getIdTarea() && listMenu.getFinal1().equals(1)) {
                            bandera = false;
                            for (MenuPerfil menuPerfilList : perfilesAux.getMenuPerfilList()) {
                                if (menuPerfilList.getIdMenu().equals(listMenu.getIdMenu())) {
                                    bandera = true;
                                }
                            }
                            if (!bandera) {
                                menuSource.add(listMenu);
                            }
                        }
                    }
                    break;
            }
        }
        listaDualMenu = new DualListModel<Menu>(menuSource, menuTarget);
    }

    public boolean valida() {
        boolean bandera = true;
        if (nombre.equals("") || nombre == null) {
            mensaje.mensaje("Debe capturar el nombre del perfil", "amarillo");
            bandera = false;
        }
        if (!(listaDualMenu.getTarget() != null && listaDualMenu.getTarget().size() > 0)) {
            mensaje.mensaje("Debe agregar alguna opción del menu para el perfil", "amarillo");
            bandera = false;
        }
        return bandera;
    }

    public void validaNombre() {
        if (!existePerfil()) {
            nombre = "";
            mensaje.mensaje("El perfil que desea agregar ya existe", "amarillo");
        }
    }

    public boolean existePerfil() {
        boolean bandera = perfilesService.obtenerPerfilByNombreService(nombre, perfilesAux.getIdPerfil());
        return bandera;
    }

    public void guardarActualizarPerfil() {
        if (valida() || opcion == 2) {
            Date fecha = new Date();
            boolean bandera = true;
            perfilesAux.setNombre(nombre);
            perfilesAux.setFechaModificacion(fecha);
            perfilesAux.setUsuarioModificacion(usuarios.getNombre());
            if (menuPerfilService.borrarByIdMenuPerfil(perfilesAux.getIdPerfil())) {
                perfiles.setMenuPerfilList(llenaLista());
                bandera = perfilesService.guardaPerfilService(perfilesAux);
                if (bandera) {
                    bitacoraTareaEstatus.setDescripcion("Actualiza perfil");
                    bitacoraTareaEstatus.setFecha(new Date());
                    bitacoraTareaEstatus.setIdModulos(4);
                    bitacoraTareaEstatus.setIdUsuarios(usuarios.getIdUsuario());
                    bitacoraTareaEstatus.setIdTareaId(16);
                    bitacoraTareaSerice.guardarEnBitacora(bitacoraTareaEstatus);
                    mensaje.mensaje(mensaje.datos_actualizados, "verde");
                    limpiarPerfil();
                } else {
                    mensaje.mensaje(mensaje.error_modificar, "rojo");
                }
            } else {
                mensaje.mensaje("Error al actualizar los menus", "rojo");
            }
        }
    }

    public void limpiarPerfil() {
        botonGuardar = true;
        botonActualizar = false;
        //    mensajeBorrar = false;
        opcion = 0;
        nombre = "";
        perfilesAux = new Perfiles();
        tipoPerfil = false;
        idMenuR = 0;
        seleccionMenuPrincipal = null;
        menuSource = new ArrayList<Menu>();
        menuTarget = new ArrayList<Menu>();
        listaDualMenu = new DualListModel<Menu>(menuSource, menuTarget);
        //    this.obtenerPerfiles();
    }

    public List<MenuPerfil> llenaLista() {
        List<MenuPerfil> listaMenuPerfil = new ArrayList();
        List<Menu> listaMenu = new ArrayList();
        MenuPerfil menuPerfil = null;
        String[] cadena = listaDualMenu.getTarget().toString().split(",");
        String id = "";
        for (int i = 0; i < cadena.length; i++) {
            id = cadena[i].replace("[", " ");
            id = id.replace("]", " ");
            Menu menu = new Menu(Integer.parseInt(id.trim()));
            listaMenu.add(menu);
        }
        for (Menu target : listaMenu) {
            menuPerfil = new MenuPerfil();
            menuPerfil.setActivo(1);
            menuPerfil.setIdMenu(target);
            if (opcion == 1) {
                menuPerfil.setIdPerfil(perfilesAux);
            } else {
                menuPerfil.setIdPerfil(perfiles);
            }
            listaMenuPerfil.add(menuPerfil);
        }

        return listaMenuPerfil;
    }

    public Perfiles getPerfiles() {
        return perfiles;
    }

    public void setPerfiles(Perfiles perfiles) {
        this.perfiles = perfiles;
    }

    public Utilidades getUtil() {
        return util;
    }

    public void setUtil(Utilidades util) {
        this.util = util;
    }

    public List<Menu> getMenuTarget() {
        return menuTarget;
    }

    public void setMenuTarget(List<Menu> menuTarget) {
        this.menuTarget = menuTarget;
    }

    public Perfiles getPerfilesAux() {
        return perfilesAux;
    }

    public void setPerfilesAux(Perfiles perfilesAux) {
        this.perfilesAux = perfilesAux;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public boolean isTipoPerfil() {
        return tipoPerfil;
    }

    public void setTipoPerfil(boolean tipoPerfil) {
        this.tipoPerfil = tipoPerfil;
    }

    public int getIdMenuR() {
        return idMenuR;
    }

    public void setIdMenuR(int idMenuR) {
        this.idMenuR = idMenuR;
    }

    public String[] getSeleccionMenuPrincipal() {
        return seleccionMenuPrincipal;
    }

    public void setSeleccionMenuPrincipal(String[] seleccionMenuPrincipal) {
        this.seleccionMenuPrincipal = seleccionMenuPrincipal;
    }

    public boolean isBotonActualizar() {
        return botonActualizar;
    }

    public void setBotonActualizar(boolean botonActualizar) {
        this.botonActualizar = botonActualizar;
    }

    public boolean isBotonGuardar() {
        return botonGuardar;
    }

    public void setBotonGuardar(boolean botonGuardar) {
        this.botonGuardar = botonGuardar;
    }

    public int getOpcion() {
        return opcion;
    }

    public void setOpcion(int opcion) {
        this.opcion = opcion;
    }

    public List<Menu> getListaMenu() {
        return listaMenu;
    }

    public void setListaMenu(List<Menu> listaMenu) {
        this.listaMenu = listaMenu;
    }

    public List<Menu> getMenuSource() {
        return menuSource;
    }

    public void setMenuSource(List<Menu> menuSource) {
        this.menuSource = menuSource;
    }

    public DualListModel<Menu> getListaDualMenu() {
        return listaDualMenu;
    }

    public void setListaDualMenu(DualListModel<Menu> listaDualMenu) {
        this.listaDualMenu = listaDualMenu;
    }

    public List<Menu> getListaMenuPrincipal() {
        return listaMenuPrincipal;
    }

    public void setListaMenuPrincipal(List<Menu> listaMenuPrincipal) {
        this.listaMenuPrincipal = listaMenuPrincipal;
    }

    public List<Menu> getListaMenuPrincipal2() {
        return listaMenuPrincipal2;
    }

    public void setListaMenuPrincipal2(List<Menu> listaMenuPrincipal2) {
        this.listaMenuPrincipal2 = listaMenuPrincipal2;
    }

    public List<Menu> getListaMenuPrincipalSeleccion() {
        return listaMenuPrincipalSeleccion;
    }

    public void setListaMenuPrincipalSeleccion(List<Menu> listaMenuPrincipalSeleccion) {
        this.listaMenuPrincipalSeleccion = listaMenuPrincipalSeleccion;
    }

    public Mensajes getMensaje() {
        return mensaje;
    }

    public void setMensaje(Mensajes mensaje) {
        this.mensaje = mensaje;
    }

}
