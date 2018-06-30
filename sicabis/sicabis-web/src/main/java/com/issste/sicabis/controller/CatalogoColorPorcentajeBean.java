/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.issste.sicabis.controller;

import com.issste.sicabis.ejb.ln.BitacoraTareaSerice;
import com.issste.sicabis.ejb.ln.ColorPorcentajeService;
import com.issste.sicabis.ejb.modelo.BitacoraTareaEstatus;
import com.issste.sicabis.ejb.modelo.ColorPorcentaje;
import com.issste.sicabis.ejb.modelo.Usuarios;
import com.issste.sicabis.utils.Mensajes;
import com.issste.sicabis.utils.Utilidades;
import java.io.IOException;
import java.io.Serializable;
import java.math.BigDecimal;
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
import org.primefaces.context.RequestContext;

/**
 *
 * @author 6JWBBG2
 */
public class CatalogoColorPorcentajeBean implements Serializable {

    @EJB
    private BitacoraTareaSerice bitacoraTareaSerice;

    @EJB
    private ColorPorcentajeService colorPorcentajeService;

    private int tabActivo;
    private List<ColorPorcentaje> listaColorPorcentaje;
    private List<ColorPorcentaje> listaBusquedaColorPorcentaje;
    private ColorPorcentaje colorPorcentaje;
    private Usuarios usuarioLogin;

    private boolean botonActualizar;
    private boolean botonGuardar = true;
    private boolean mensajeBorrar;
    private boolean messageGuardar = true;
    private boolean modificar;
    private BigDecimal porcentajeInicial;
    private BigDecimal porcentajeFinal;
    private String hexColor;
    private BigDecimal porcentajeInicialAux;
    private BigDecimal porcentajeFinalAux;
    private String hexColorAux;

    private ColorPorcentaje colorPorcentajeAux;
    private BigDecimal porcentajeABuscar;

    private Utilidades util = new Utilidades();
    private Mensajes mensaje = new Mensajes();
    private boolean verMensaje;
    private BitacoraTareaEstatus bitacoraTareaEstatus;

    /**
     * Creates a new instance of CatalogoColorPorcentajeBean
     */
    public CatalogoColorPorcentajeBean() {
        colorPorcentaje = new ColorPorcentaje();
        bitacoraTareaEstatus = new BitacoraTareaEstatus();
        usuarioLogin = new Usuarios();
        listaColorPorcentaje = new ArrayList<>();

    }

    @PostConstruct
    public void init() {
        usuarioLogin = (Usuarios) util.getSessionAtributte("usuario");
        listaColorPorcentaje = colorPorcentajeService.colorPorcentajeAllActivos();
        tabActivo = 0;
    }

    public void guardarActualizarColorPorcentaje() {
        listaColorPorcentaje = colorPorcentajeService.colorPorcentajeAllActivos();
        if (valida()) {
            if (existeFiltroPorcentajeColor() == null) {
                colorPorcentaje.setActivo(1);
                colorPorcentaje.setPorcentajeInicial(porcentajeInicial);
                colorPorcentaje.setPorcentajeFinal(porcentajeFinal);
                colorPorcentaje.setHexColor(hexColor);
                colorPorcentaje.setFechaAlta(new Date());
                colorPorcentaje.setUsuarioAlta(usuarioLogin.getUsuario());
                colorPorcentajeService.guardarActualizaColorPorcentaje(colorPorcentaje);
                bitacoraTareaEstatus.setDescripcion("Guardar porcentajes:" + porcentajeInicial + " , " + porcentajeFinal + "");
                bitacoraTareaEstatus.setFecha(new Date());
                bitacoraTareaEstatus.setIdModulos(4);
                bitacoraTareaEstatus.setIdUsuarios(usuarioLogin.getIdUsuario());
                bitacoraTareaSerice.guardarEnBitacora(bitacoraTareaEstatus);
                colorPorcentaje = new ColorPorcentaje();
                mensaje.mensaje("El filtro rango - color se ha guardado correctamente.", "verde");
            }

        }
    }

    public ColorPorcentaje existeFiltroPorcentajeColor() {
        ColorPorcentaje cp = new ColorPorcentaje();
        cp.setPorcentajeInicial(porcentajeInicial);
        cp.setPorcentajeFinal(porcentajeFinal);
        cp.setHexColor(hexColor);
        ColorPorcentaje c = colorPorcentajeService.validaExistenciaFiltroColorPorcentaje(cp);
        return c;
    }

    public boolean valida() {
        boolean bandera = true;

        if (porcentajeInicial == null || porcentajeInicial.toString().trim().equals("")) {
            mensaje.mensaje("Debes capturar un porcentaje inicial", "amarillo");
            bandera = false;
        } else if (!validaDisponibilidadPorcentajeInicial(porcentajeInicial)) {
            mensaje.mensaje("Porcentaje inicial actualmente en uso", "amarillo");
            bandera = false;
        }

        if (porcentajeFinal == null || porcentajeFinal.toString().trim().equals("")) {
            mensaje.mensaje("Debes capturar un porcentaje final", "amarillo");
            bandera = false;
        } else if (!validaDisponibilidadPorcentajeFinal(porcentajeFinal)) {
            mensaje.mensaje("Porcentaje final actualmente en uso", "amarillo");
            bandera = false;
        }

        if (hexColor == null || hexColor.trim().equals("")) {
            mensaje.mensaje("Debes seleccionar un color", "amarillo");
            bandera = false;
        } else {
            if (!validaDisponibilidadColor(hexColor)) {
                mensaje.mensaje("Color seleccionado actualmente en uso", "amarillo");
                bandera = false;
            }
        }
        if (porcentajeInicial != null && porcentajeFinal != null && !porcentajeInicial.toString().trim().equals("") && !porcentajeFinal.toString().trim().equals("") && porcentajeFinal.compareTo(porcentajeInicial) < 0) {
            mensaje.mensaje("El porcentaje final no puede ser menor o igual al porcentaje inicial", "amarillo");
            bandera = false;
        }
        return bandera;
    }

    private boolean validaDisponibilidadPorcentajeInicial(BigDecimal porcentaje) {
        boolean result = true;
        for (ColorPorcentaje item : listaColorPorcentaje) {
            if (porcentaje.compareTo(item.getPorcentajeInicial()) >= 0 && porcentaje.compareTo(item.getPorcentajeFinal()) <= 0) {
                System.err.println("El porcentaje " + porcentaje + " está dentro del rango almacenado de " + item.getPorcentajeInicial() + " - " + item.getPorcentajeFinal());
                result = false;
                break;
            }
        }
        return result;
    }

    private boolean validaDisponibilidadPorcentajeFinal(BigDecimal porcentaje) {
        boolean result = true;
        for (ColorPorcentaje item : listaColorPorcentaje) {
            if (porcentaje.compareTo(item.getPorcentajeInicial()) >= 0 && porcentaje.compareTo(item.getPorcentajeFinal()) <= 0) {
                result = false;
                break;
            }
        }
        return result;
    }

    private boolean validaDisponibilidadColor(String color) {
        System.err.println("Dentro del método validaDisponibilidadColor");
        System.err.println(listaColorPorcentaje);
        boolean result = true;
        for (ColorPorcentaje item : listaColorPorcentaje) {
            if (color.equals(item.getHexColor())) {
                result = false;
                break;
            }
        }
        return result;
    }

    public void consultarColorPorcentaje() {
        System.out.println("DENTRO DEL METODO DE CONSULTA");
        listaBusquedaColorPorcentaje = new ArrayList();
        listaColorPorcentaje = colorPorcentajeService.colorPorcentajeAllActivos();
        if (porcentajeABuscar == null || porcentajeABuscar.toString().trim().equals("")) {
            listaBusquedaColorPorcentaje = listaColorPorcentaje;
        } else {
            for (ColorPorcentaje item : listaColorPorcentaje) {
                if ((porcentajeABuscar.compareTo(item.getPorcentajeInicial()) >= 0) && porcentajeABuscar.compareTo(item.getPorcentajeFinal()) <= 0) {
                    listaBusquedaColorPorcentaje.clear();
                    listaBusquedaColorPorcentaje.add(item);
                    break;
                } else {
                    System.out.println("entro");
                    mensaje.mensaje("No se encontraron resultados.", "amarillo");
                    verMensaje = true;
                }
            }
        }
        if (listaColorPorcentaje == null || listaColorPorcentaje.isEmpty() || listaBusquedaColorPorcentaje == null || listaBusquedaColorPorcentaje.isEmpty()) {
            mensaje.mensaje("No se encontraron resultados.", "amarillo");
            verMensaje = true;
        }

    }

    public void cancel() {
        ExternalContext ctx = FacesContext.getCurrentInstance().getExternalContext();
        String ctxPath = ((ServletContext) ctx.getContext()).getContextPath();
        tabActivo = 1;
        try {
            ctx.redirect(ctxPath + "/vistas/administracion/catalogos/catColorPorcentaje.xhtml");
        } catch (IOException ex) {
            Logger.getLogger(TiposDocumentoBean.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void modificarRedirect(ColorPorcentaje colorPorcentaje) {
        tabActivo = 1;
        ExternalContext ctx = FacesContext.getCurrentInstance().getExternalContext();
        String ctxPath = ((ServletContext) ctx.getContext()).getContextPath();
        util.setSessionMapValue("colorPorcentaje", colorPorcentaje);
        try {
            ctx.redirect(ctxPath + "/vistas/administracion/catalogos/detalleColorPorcentaje.xhtml");
        } catch (IOException ex) {
            Logger.getLogger(CatalogoColorPorcentajeBean.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void eliminarColorPorcentaje() {
//        nombreAlmacen = almacen.getNombreAlmacen();
        tabActivo = 1;
        System.out.println(colorPorcentajeAux.getIdColorPorcentaje());
        colorPorcentajeAux.setFechaBaja(new Date());
        colorPorcentajeAux.setUsuarioBaja(usuarioLogin.getUsuario());
        colorPorcentajeAux.setActivo(0);
        boolean eliminar = colorPorcentajeService.guardarActualizaColorPorcentaje(colorPorcentajeAux);

        if (eliminar) {
            bitacoraTareaEstatus.setDescripcion("Borrado color porcentaje");
            bitacoraTareaEstatus.setFecha(new Date());
            bitacoraTareaEstatus.setIdModulos(4);
            bitacoraTareaEstatus.setIdUsuarios(usuarioLogin.getIdUsuario());
            bitacoraTareaSerice.guardarEnBitacora(bitacoraTareaEstatus);
            consultarColorPorcentaje();
            mensaje.mensaje("El filtro color - porcentaje ha sido dado de baja.", "verde");
            verMensaje = true;
        }
//        listaColorPorcentaje = new ArrayList<>();

    }

    public void mostrarDialogo(ColorPorcentaje colorP) {
        colorPorcentajeAux = colorP;
        porcentajeInicialAux = colorPorcentajeAux.getPorcentajeInicial();
        porcentajeFinalAux = colorPorcentajeAux.getPorcentajeFinal();
//        hexColorAux = colorPorcentajeAux.getHexColor();

//        nombre = perfiles.getNombre();
//        opcion = 2;
        RequestContext.getCurrentInstance().execute("PF('dialogElimina').show();");
    }

    public BigDecimal getPorcentajeInicial() {
        return porcentajeInicial;
    }

    public void setPorcentajeInicial(BigDecimal porcentajeInicial) {
        this.porcentajeInicial = porcentajeInicial;
    }

    public BigDecimal getPorcentajeFinal() {
        return porcentajeFinal;
    }

    public void setPorcentajeFinal(BigDecimal porcentajeFinal) {
        this.porcentajeFinal = porcentajeFinal;
    }

    public String getHexColor() {
        return hexColor;
    }

    public void setHexColor(String hexColor) {
        this.hexColor = hexColor;
    }

    public ColorPorcentaje getColorPorcentaje() {
        return colorPorcentaje;
    }

    public void setColorPorcentaje(ColorPorcentaje colorPorcentaje) {
        this.colorPorcentaje = colorPorcentaje;
    }

    public List<ColorPorcentaje> getListaColorPorcentaje() {
        return listaColorPorcentaje;
    }

    public void setListaColorPorcentaje(List<ColorPorcentaje> listaColorPorcentaje) {
        this.listaColorPorcentaje = listaColorPorcentaje;
    }

    public Usuarios getUsuarioLogin() {
        return usuarioLogin;
    }

    public void setUsuarioLogin(Usuarios usuarioLogin) {
        this.usuarioLogin = usuarioLogin;
    }

    public Utilidades getUtil() {
        return util;
    }

    public void setUtil(Utilidades util) {
        this.util = util;
    }

    public Mensajes getMensaje() {
        return mensaje;
    }

    public void setMensaje(Mensajes mensaje) {
        this.mensaje = mensaje;
    }

    public BigDecimal getPorcentajeABuscar() {
        return porcentajeABuscar;
    }

    public void setPorcentajeABuscar(BigDecimal porcentajeABuscar) {
        this.porcentajeABuscar = porcentajeABuscar;
    }

    public int getTabActivo() {
        return tabActivo;
    }

    public void setTabActivo(int tabActivo) {
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

    public List<ColorPorcentaje> getListaBusquedaColorPorcentaje() {
        return listaBusquedaColorPorcentaje;
    }

    public void setListaBusquedaColorPorcentaje(List<ColorPorcentaje> listaBusquedaColorPorcentaje) {
        this.listaBusquedaColorPorcentaje = listaBusquedaColorPorcentaje;
    }

    public ColorPorcentaje getColorPorcentajeAux() {
        return colorPorcentajeAux;
    }

    public void setColorPorcentajeAux(ColorPorcentaje colorPorcentajeAux) {
        this.colorPorcentajeAux = colorPorcentajeAux;
    }

    public BigDecimal getPorcentajeInicialAux() {
        return porcentajeInicialAux;
    }

    public void setPorcentajeInicialAux(BigDecimal porcentajeInicialAux) {
        this.porcentajeInicialAux = porcentajeInicialAux;
    }

    public BigDecimal getPorcentajeFinalAux() {
        return porcentajeFinalAux;
    }

    public void setPorcentajeFinalAux(BigDecimal porcentajeFinalAux) {
        this.porcentajeFinalAux = porcentajeFinalAux;
    }

    public String getHexColorAux() {
        return hexColorAux;
    }

    public void setHexColorAux(String hexColorAux) {
        this.hexColorAux = hexColorAux;
    }

    public boolean isVerMensaje() {
        return verMensaje;
    }

    public void setVerMensaje(boolean verMensaje) {
        this.verMensaje = verMensaje;
    }

}
