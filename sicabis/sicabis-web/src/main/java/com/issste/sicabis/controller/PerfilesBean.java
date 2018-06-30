package com.issste.sicabis.controller;

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

public class PerfilesBean implements Serializable {

    @EJB
    private BitacoraTareaSerice bitacoraTareaSerice;

    @EJB
    private MenuPerfilService menuPerfilService;

    @EJB
    private PerfilesService perfilesService;

    @EJB
    private MenuService menuService;

    private Perfiles perfiles;
    private Perfiles perfilesAux;
    private Usuarios usuarios;
    private Menu menu;
    private BitacoraTareaEstatus bitacoraTareaEstatus;

    private String nombre;
    private Integer activo;
    private Integer idPerfil;
    private Integer tabActivo;
    private boolean botonActualizar;
    private boolean botonGuardar = true;
    private boolean mensajeBorrar;
    private boolean messageGuardar = true;
    private boolean modificar;
    private List<Perfiles> listaPerfiles;
    private Date fecha;
    private int opcion;
    private List<Menu> listaMenu;
    private List<Menu> listaMenuPrincipal;
    private List<Menu> listaMenuPrincipal2;
    private List<Menu> listaMenuPrincipalSeleccion;
    private String[] seleccionMenuPrincipal;
    private Integer consulta;
    private boolean tipoPerfil;
    private int idMenuR;
    private DualListModel<Menu> listaDualMenu;

    private List<Menu> menuSource;
    private List<Menu> menuTarget;
    private Mensajes mensaje = new Mensajes();
    private Utilidades util = new Utilidades();

    public PerfilesBean() {
        perfiles = new Perfiles();
        usuarios = new Usuarios();
        bitacoraTareaEstatus = new BitacoraTareaEstatus();
        menu = new Menu();
        usuarios = (Usuarios) util.getSessionAtributte("usuario");
        opcion = 0;
        tipoPerfil = false;
        seleccionMenuPrincipal = null;
        menuSource = new ArrayList<>();
        menuTarget = new ArrayList<>();
        listaMenu = new ArrayList<>();
    }

    @PostConstruct
    public void cargaCombos() {
        int autoriza = 0;
        perfiles = perfilesService.obtenerPerfilByIdUsuario(usuarios.getIdUsuario());
        idPerfil = perfiles.getIdPerfil();
        String nom = perfiles.getNombre();
        for (MenuPerfil iterator : perfiles.getMenuPerfilList()) {
            if (iterator.getIdMenu().getIdMenu() != 40 || iterator.getIdMenu().getIdMenu() != 41) {
                autoriza++;
            } else {
                autoriza++;
            }
        }
        //listaMenu = menuService.obtenerMenuService();
        if (!nom.equals("administrador") && autoriza > 2) {
            listaMenu = menuService.obtenerMenuByidPerfil(perfiles.getIdPerfil());
        } else {
            listaMenu = menuService.obtenerMenuByidPerfil(1);
        }
        List<Menu> menuPadre = new ArrayList<>();
        for (Menu m : listaMenu) {
            if (m.getIdMenuPadre() != 0) {
                menuPadre.add(menuService.obtenerMenuById(m.getIdMenuPadre()));
            }
        }
        for (Menu m : menuPadre) {
            boolean blnAdd = false;
            for (Menu maux : listaMenu) {
                if (Objects.equals(maux.getIdMenu(), m.getIdMenu())) {
                    blnAdd = true;
                }
            }
            if (!blnAdd) {
                listaMenu.add(m);
            }
        }
        listaMenuPrincipal = new ArrayList();
        listaMenuPrincipal2 = new ArrayList();
        listaMenuPrincipalSeleccion = new ArrayList();
        perfiles.setIdPerfil(null);
        perfiles.setNombre("");
        modificar(perfiles);
        for (Menu menuAux : listaMenu) {
            if (menuAux.getIdMenuPadre() == 0) {
                if (perfiles != null) {
                    if (!nom.equals("administrador") && autoriza > 2) {
                        for (String seleccion : seleccionMenuPrincipal) {
                            if (menuAux.getIdTarea().getIdTarea().toString().equals(seleccion)) {
                                listaMenuPrincipal.add(menuAux);
                            }
                        }
                    } else {
                        listaMenuPrincipal.add(menuAux);
                    }
                }
                if (menuAux.getDescripcion().equals("ADMINISTRACIÓN")
                        || menuAux.getDescripcion().equals("REPORTES")
                        || menuAux.getDescripcion().equals("CATÁLOGOS")
                        || menuAux.getDescripcion().equals("AUDITORIA")) {
                    if (perfiles != null) {
                        if (!nom.equals("administrador") && autoriza > 2) {
                            for (String seleccion : seleccionMenuPrincipal) {
                                if (menuAux.getIdTarea().getIdTarea().toString().equals(seleccion)) {
                                    listaMenuPrincipal2.add(menuAux);
                                }
                            }
                        } else {
                            listaMenuPrincipal2.add(menuAux);
                        }
                    }
                } else {
                    if (perfiles != null) {
                        if (!nom.equals("administrador") && autoriza > 2) {
                            if (menuAux.getIdTarea().getIdTarea().equals(idMenuR)) {
                                listaMenuPrincipalSeleccion.add(menuAux);
                            }
                        } else {
                            listaMenuPrincipalSeleccion.add(menuAux);
                        }
                    }
                }
            }
        }
        seleccionMenuPrincipal = null;
        idMenuR = 0;
        menuSource = new ArrayList<>();
        menuTarget = new ArrayList<>();
        listaDualMenu = new DualListModel(menuSource, menuTarget);
    }

    public void cambiaSeleccion(int opcion) {
        menuSource = new ArrayList<>();
        //menuTarget = new ArrayList<Menu>();
        boolean bandera = false;
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
        listaDualMenu = new DualListModel<Menu>(menuSource, menuTarget);
        tabActivo = 0;
    }

    public void cambiaTipo() {
        //quita la seleccion de los radiobutton al seleccionar el checked de perfil consulta
        if (tipoPerfil) {
            idMenuR = 0;

        }

        tabActivo = 0;
        // limpia los checkbox seleccionados tanto si selecciona o no el checkbox de consulta
        seleccionMenuPrincipal = null;
        // Limpia las dos listas del picklist
        menuSource = new ArrayList<Menu>();
        menuTarget = new ArrayList<Menu>();
        listaDualMenu = new DualListModel<Menu>(menuSource, menuTarget);

    }

    public void obtenerPerfiles() {
        listaPerfiles = new ArrayList();
        List<Perfiles> perfilesAux = perfilesService.getByNombreActivo(nombre, activo);
        // Se agrego condicion para validar si la lista viene en null
        if (perfilesAux != null) {
            for (Perfiles perfil : perfilesAux) {
                if (!Objects.equals(perfil.getIdPerfil(), idPerfil)) {
                    listaPerfiles.add(perfil);
                }
            }
        }

    }

    public void cancel() {
        limpiarPerfil();
        ExternalContext ctx = FacesContext.getCurrentInstance().getExternalContext();
        String ctxPath = ((ServletContext) ctx.getContext()).getContextPath();
        try {
            ctx.redirect(ctxPath + "/vistas/administracion/perfiles.xhtml");
        } catch (IOException ex) {
            Logger.getLogger(PerfilesBean.class.getName()).log(Level.SEVERE, null, ex);
        }
        tabActivo = 0;
    }

    public void cancelDialog() {
        RequestContext.getCurrentInstance().execute("PF('dialogElimina').hide();");
    }

    public void limpiarPerfil() {
        botonGuardar = true;
        botonActualizar = false;
        mensajeBorrar = false;
        opcion = 0;
        nombre = "";
        perfilesAux = new Perfiles();
        tipoPerfil = false;
        idMenuR = 0;
        seleccionMenuPrincipal = null;
        menuSource = new ArrayList<Menu>();
        menuTarget = new ArrayList<Menu>();
        listaDualMenu = new DualListModel<Menu>(menuSource, menuTarget);
        this.obtenerPerfiles();
    }

    public void modificar(Perfiles perfiles) {
        tabActivo = 0;
        perfilesAux = perfiles;
        nombre = perfiles.getNombre();
        tipoPerfil = perfiles.getConsulta() == 1;
        int i = 0;
        boolean bandera = false;
        List<String> listaString = new ArrayList<>();
        if (perfiles.getConsulta() == 0) {
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
                if (seleccionMenuPrincipal == null) {
                    seleccionMenuPrincipal[i] = menuPerfilList.getIdMenu().getIdTarea().getIdTarea().toString();
                } else {
                    for (int j = 0; j < seleccionMenuPrincipal.length; j++) {
                        if (seleccionMenuPrincipal[j].equals(menuPerfilList.getIdMenu().getIdTarea().getIdTarea().toString())) {
                            bandera = true;
                        }
                    }
                    if (!bandera) {
                        seleccionMenuPrincipal[i] = menuPerfilList.getIdMenu().getIdTarea().getIdTarea().toString();
                    }
                }
                i++;
            }
            cambiaSeleccion(1);
        }
        botonGuardar = false;
        botonActualizar = true;
        opcion = 0;
    }

    public void modificarRedirect(Perfiles perfiles) {
        ExternalContext ctx = FacesContext.getCurrentInstance().getExternalContext();
        String ctxPath = ((ServletContext) ctx.getContext()).getContextPath();
        util.setSessionMapValue("perfil", perfiles);
        try {
            ctx.redirect(ctxPath + "/vistas/administracion/detallePerfiles.xhtml");
        } catch (IOException ex) {
            Logger.getLogger(PerfilesBean.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void eliminar(Perfiles perfiles) {
        perfilesAux = perfiles;
        nombre = perfiles.getNombre();
        opcion = 2;
        RequestContext.getCurrentInstance().execute("PF('dialogElimina').show();");
    }

    public boolean existePerfil() {
        boolean bandera = true;
        switch (opcion) {
            case 0:
                bandera = perfilesService.obtenerPerfilByNombreService(nombre, 0);
                break;
            case 1:
                bandera = perfilesService.obtenerPerfilByNombreService(nombre, perfilesAux.getIdPerfil());
                break;
        }
        return bandera;
    }

    public void validaNombre() {
        if (!existePerfil()) {
            nombre = "";
            messageGuardar = true;
            mensaje.mensaje("El perfil que desea agregar ya existe", "amarillo");
        }
        tabActivo = 1;
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
        //List<MenuPerfil> lista = llenaLista();
        return bandera;
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

    public void guardarActualizarPerfil() {
        if (valida() || opcion == 2) {
            fecha = new Date();
            boolean bandera = true;
            switch (opcion) {
                case 0:
                    if (existePerfil()) {
                        perfiles.setActivo(1);
                        perfiles.setFechaAlta(fecha);
                        perfiles.setUsuarioAlta(usuarios.getNombre());
                        perfiles.setNombre(nombre);
                        perfiles.setConsulta(tipoPerfil ? 1 : 0);
                        perfiles.setEdita(0);
                        perfiles.setMenuPerfilList(llenaLista());
                        bandera = perfilesService.guardaPerfilService(perfiles);
                        if (bandera) {
                            bitacoraTareaEstatus.setDescripcion("Guardar perfil:" + nombre + "");
                            bitacoraTareaEstatus.setFecha(new Date());
                            bitacoraTareaEstatus.setIdModulos(4);
                            bitacoraTareaEstatus.setIdUsuarios(usuarios.getIdUsuario());
                            bitacoraTareaEstatus.setIdTareaId(16);
                            bitacoraTareaSerice.guardarEnBitacora(bitacoraTareaEstatus);
                            mensaje.mensaje(mensaje.datos_guardados, "verde");
                            this.limpiarPerfil();
                        } else {
                            mensaje.mensaje(mensaje.error_guardar, "rojo");
                        }
                    } else {
                        mensaje.mensaje("El perfil que desea agregar ya existe", "amarillo");
                    }
                    limpiarPerfil();
                    tabActivo = 0;
                    messageGuardar = true;
                    break;
                case 1:
                    if (existePerfil()) {
                        perfilesAux.setNombre(nombre);
                        perfilesAux.setFechaModificacion(fecha);
                        perfilesAux.setUsuarioModificacion(usuarios.getNombre());
                        if (menuPerfilService.borrarByIdMenuPerfil(perfilesAux.getIdPerfil())) {
                            perfiles.setMenuPerfilList(llenaLista());
                            bandera = perfilesService.guardaPerfilService(perfilesAux);
                            if (bandera) {
                                bitacoraTareaEstatus.setDescripcion("Actualiza perfil:" + nombre + "");
                                bitacoraTareaEstatus.setFecha(new Date());
                                bitacoraTareaEstatus.setIdModulos(4);
                                bitacoraTareaEstatus.setIdUsuarios(usuarios.getIdUsuario());
                                bitacoraTareaEstatus.setIdTareaId(16);
                                bitacoraTareaSerice.guardarEnBitacora(bitacoraTareaEstatus);
                                mensaje.mensaje(mensaje.datos_actualizados, "verde");
                                this.limpiarPerfil();
                                tabActivo = 0;
                                messageGuardar = true;
                            } else {
                                mensaje.mensaje(mensaje.error_modificar, "rojo");
                            }
                        } else {
                            mensaje.mensaje("Error al actualizar los menus", "rojo");
                        }
                    } else {
                        mensaje.mensaje("El perfil que desea agregar ya existe", "amarillo");
                    }
                    break;
                case 2:
                    perfilesAux.setFechaBaja(fecha);
                    perfilesAux.setUsuarioBaja(usuarios.getNombre());
                    if (menuPerfilService.borrarByIdMenuPerfil(perfilesAux.getIdPerfil())) {
                        bandera = perfilesService.guardaPerfilService(perfilesAux);
                        if (bandera) {
                            mensaje.mensaje(mensaje.datos_actualizados, "verde");
                            this.limpiarPerfil();
                        } else {
                            mensaje.mensaje(mensaje.error_borrar, "rojo");
                        }
                        mensajeBorrar = true;
                        tabActivo = 1;
                    } else {
                        mensaje.mensaje("Error al actualizar los menus", "rojo");
                    }
                    break;
            }
        }
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

    public Integer getTabActivo() {
        return tabActivo;
    }

    public void setTabActivo(Integer tabActivo) {
        this.tabActivo = tabActivo;
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

    public boolean isMensajeBorrar() {
        return mensajeBorrar;
    }

    public void setMensajeBorrar(boolean mensajeBorrar) {
        this.mensajeBorrar = mensajeBorrar;
    }

    public boolean isMessageGuardar() {
        return messageGuardar;
    }

    public void setMessageGuardar(boolean messageGuardar) {
        this.messageGuardar = messageGuardar;
    }

    public boolean isModificar() {
        return modificar;
    }

    public void setModificar(boolean modificar) {
        this.modificar = modificar;
    }

    public List<Perfiles> getListaPerfiles() {
        return listaPerfiles;
    }

    public void setListaPerfiles(List<Perfiles> listaPerfiles) {
        this.listaPerfiles = listaPerfiles;
    }

    public Integer getIdPerfil() {
        return idPerfil;
    }

    public void setIdPerfil(Integer idPerfil) {
        this.idPerfil = idPerfil;
    }

    public int getOpcion() {
        return opcion;
    }

    public void setOpcion(int opcion) {
        this.opcion = opcion;
    }

    public Perfiles getPerfilesAux() {
        return perfilesAux;
    }

    public void setPerfilesAux(Perfiles perfilesAux) {
        this.perfilesAux = perfilesAux;
    }

    public List<Menu> getListaMenu() {
        return listaMenu;
    }

    public void setListaMenu(List<Menu> listaMenu) {
        this.listaMenu = listaMenu;
    }

    public List<Menu> getListaMenuPrincipal() {
        return listaMenuPrincipal;
    }

    public void setListaMenuPrincipal(List<Menu> listaMenuPrincipal) {
        this.listaMenuPrincipal = listaMenuPrincipal;
    }

    public List<Menu> getListaMenuPrincipalSeleccion() {
        return listaMenuPrincipalSeleccion;
    }

    public void setListaMenuPrincipalSeleccion(List<Menu> listaMenuPrincipalSeleccion) {
        this.listaMenuPrincipalSeleccion = listaMenuPrincipalSeleccion;
    }

    public String[] getSeleccionMenuPrincipal() {
        return seleccionMenuPrincipal;
    }

    public void setSeleccionMenuPrincipal(String[] seleccionMenuPrincipal) {
        this.seleccionMenuPrincipal = seleccionMenuPrincipal;
    }

    public Integer getConsulta() {
        return consulta;
    }

    public void setConsulta(Integer consulta) {
        this.consulta = consulta;
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

    public List<Menu> getListaMenuPrincipal2() {
        return listaMenuPrincipal2;
    }

    public void setListaMenuPrincipal2(List<Menu> listaMenuPrincipal2) {
        this.listaMenuPrincipal2 = listaMenuPrincipal2;
    }

    public DualListModel<Menu> getListaDualMenu() {
        return listaDualMenu;
    }

    public void setListaDualMenu(DualListModel<Menu> listaDualMenu) {
        this.listaDualMenu = listaDualMenu;
    }

}
