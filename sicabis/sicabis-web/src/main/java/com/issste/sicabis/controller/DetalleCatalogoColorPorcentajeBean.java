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
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;

/**
 *
 * @author 6JWBBG2
 */
public class DetalleCatalogoColorPorcentajeBean {

    @EJB
    private BitacoraTareaSerice bitacoraTareaSerice;
    @EJB
    private ColorPorcentajeService colorPorcentajeService;

    private Usuarios usuarioLogin;
    private ColorPorcentaje colorPorcentaje;
    private ColorPorcentaje colorPorcentajeAux;
    private BitacoraTareaEstatus bitacoraTareaEstatus;
//    private BigDecimal porcentajeInicial;
//    private BigDecimal porcentajeFinal;
//    private String hexColor;
    private List<ColorPorcentaje> listaColorPorcentaje;
    private Utilidades util = new Utilidades();
    private Mensajes mensaje = new Mensajes();

    /**
     * Creates a new instance of DetalleCatalogoColorPorcentajeBean
     */
    public DetalleCatalogoColorPorcentajeBean() {
        usuarioLogin = new Usuarios();
        bitacoraTareaEstatus = new BitacoraTareaEstatus();
    }

    @PostConstruct
    public void init() {
        usuarioLogin = (Usuarios) util.getSessionAtributte("usuario");
        colorPorcentaje = (ColorPorcentaje) util.getSessionAtributte("colorPorcentaje");
        listaColorPorcentaje = colorPorcentajeService.colorPorcentajeAllActivos();
        colorPorcentajeAux = colorPorcentaje;
    }

    public void actualizarColorPorcentaje() {

        if (valida()) {
            colorPorcentajeAux.setPorcentajeInicial(colorPorcentaje.getPorcentajeInicial());
            colorPorcentajeAux.setPorcentajeFinal(colorPorcentaje.getPorcentajeFinal());
            colorPorcentajeAux.setHexColor(colorPorcentaje.getHexColor());
            colorPorcentajeAux.setFechaModificacion(new Date());
            colorPorcentajeAux.setUsuarioModificacion(usuarioLogin.getUsuario());
            colorPorcentajeService.guardarActualizaColorPorcentaje(colorPorcentaje);
            bitacoraTareaEstatus.setDescripcion("Actualiza color porcentaje");
            bitacoraTareaEstatus.setFecha(new Date());
            bitacoraTareaEstatus.setIdModulos(1);
            bitacoraTareaEstatus.setIdUsuarios(usuarioLogin.getIdUsuario());
            bitacoraTareaEstatus.setIdTareaId(16);
            bitacoraTareaSerice.guardarEnBitacora(bitacoraTareaEstatus);
            colorPorcentaje = new ColorPorcentaje();
            mensaje.mensaje("El filtro se ha actualizado correctamente.", "verde");
        }

    }

    public boolean valida() {
        boolean bandera = true;

        if (colorPorcentaje.getPorcentajeInicial() == null || colorPorcentaje.getPorcentajeInicial().toString().trim().equals("")) {
            mensaje.mensaje("Debes capturar un porcentaje inicial", "amarillo");
            bandera = false;
        } else if (!validaDisponibilidadPorcentajeInicial(colorPorcentaje.getPorcentajeInicial())) {
            mensaje.mensaje("Porcentaje inicial actualmente en uso", "amarillo");
            bandera = false;
        }

        if (colorPorcentaje.getPorcentajeFinal() == null || colorPorcentaje.getPorcentajeFinal().toString().trim().equals("")) {
            mensaje.mensaje("Debes capturar un porcentaje final", "amarillo");
            bandera = false;
        } else if (!validaDisponibilidadPorcentajeFinal(colorPorcentaje.getPorcentajeFinal())) {
            mensaje.mensaje("Porcentaje final actualmente en uso", "amarillo");
            bandera = false;
        }

        if (colorPorcentaje.getHexColor() == null || colorPorcentaje.getHexColor().trim().equals("")) {
            mensaje.mensaje("Debes seleccionar un color", "amarillo");
            bandera = false;
        } else {
            if (!validaDisponibilidadColor(colorPorcentaje.getHexColor())) {
                mensaje.mensaje("Color seleccionado actualmente en uso", "amarillo");
                bandera = false;
            }
        }
        if (colorPorcentaje.getPorcentajeInicial() != null && colorPorcentaje.getPorcentajeFinal() != null && !colorPorcentaje.getPorcentajeInicial().toString().trim().equals("") && !colorPorcentaje.getPorcentajeFinal().toString().trim().equals("") && colorPorcentaje.getPorcentajeFinal().compareTo(colorPorcentaje.getPorcentajeInicial()) <= 0) {
            mensaje.mensaje("El porcentaje final no puede ser menor o igual al porcentaje inicial", "amarillo");
            bandera = false;
        }
        return bandera;
    }

    private boolean validaDisponibilidadPorcentajeInicial(BigDecimal porcentaje) {
        boolean result = true;
        for (ColorPorcentaje item : listaColorPorcentaje) {
            if (porcentaje.compareTo(item.getPorcentajeInicial()) >= 0 && porcentaje.compareTo(item.getPorcentajeFinal()) < 0 && colorPorcentaje.getIdColorPorcentaje().compareTo(item.getIdColorPorcentaje()) != 0) {
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
            if (porcentaje.compareTo(item.getPorcentajeInicial()) > 0 && porcentaje.compareTo(item.getPorcentajeFinal()) <= 0 && colorPorcentaje.getIdColorPorcentaje().compareTo(item.getIdColorPorcentaje()) != 0) {
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
            if (color.equals(item.getHexColor()) && colorPorcentaje.getIdColorPorcentaje().compareTo(item.getIdColorPorcentaje()) != 0) {
                result = false;
                break;
            }
        }
        return result;
    }

    public Usuarios getUsuarioLogin() {
        return usuarioLogin;
    }

    public void setUsuarioLogin(Usuarios usuarioLogin) {
        this.usuarioLogin = usuarioLogin;
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

}
