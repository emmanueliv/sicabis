package com.issste.sicabis.controller;

import com.issste.sicabis.ejb.ln.BitacoraTareaSerice;
import com.issste.sicabis.ejb.ln.ClasificacionImportanciaService;
import com.issste.sicabis.ejb.modelo.BitacoraTareaEstatus;
import com.issste.sicabis.ejb.modelo.ClasificacionImportancia;
import com.issste.sicabis.ejb.modelo.Usuarios;
import com.issste.sicabis.utils.Mensajes;
import com.issste.sicabis.utils.Utilidades;
import java.util.Date;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;

public class DetalleClasificacionImportanciaBean {

    @EJB
    private BitacoraTareaSerice bitacoraTareaSerice;

    @EJB
    private ClasificacionImportanciaService clasificacionImportanciaService;

    private Usuarios usuarioLogin;
    private ClasificacionImportancia clasificacionImportancia;
    private BitacoraTareaEstatus bitacoraTareaEstatus;

    private Utilidades util = new Utilidades();
    private Mensajes mensaje = new Mensajes();

    @PostConstruct
    public void init() {
        usuarioLogin = new Usuarios();
        bitacoraTareaEstatus = new BitacoraTareaEstatus();
        clasificacionImportancia = new ClasificacionImportancia();
        usuarioLogin = (Usuarios) util.getSessionAtributte("usuario");
        clasificacionImportancia = (ClasificacionImportancia) util.getSessionAtributte("clasificacionImportancia");
    }

    public boolean valida() {
        boolean bandera = true;
        if (clasificacionImportancia.getSigla() == null || clasificacionImportancia.getSigla().equals("")) {
            mensaje.mensaje("Debes ingresar la sigla de la clasificación", "amarillo");
            bandera = false;
        } else {
            ClasificacionImportancia ci = new ClasificacionImportancia();
            ci.setSigla(clasificacionImportancia.getSigla());
            List<ClasificacionImportancia> listaCI = clasificacionImportanciaService.obtenerByClasificacion(ci);
            if (listaCI != null) {
                mensaje.mensaje("La sigla de la clasificación ya se encuentra previamente almacenada", "amarillo");
                bandera = false;
            }
        }
        if (clasificacionImportancia.getDescripcion() == null || clasificacionImportancia.getDescripcion().equals("")) {
            mensaje.mensaje("Debes ingresar la descripción de la clasificación", "amarillo");
            bandera = false;
        }
        return bandera;
    }

    public void guardar() {
        if (valida()) {
            clasificacionImportancia.setActivo(1);
            clasificacionImportancia.setUsuarioModificacion(usuarioLogin.getUsuario());
            clasificacionImportancia.setFechaModificacion(new Date());
            clasificacionImportanciaService.guardarClasificacionImportancia(clasificacionImportancia);
            bitacoraTareaEstatus.setDescripcion("Actualiza clasificacion importancia insumos.");
            bitacoraTareaEstatus.setFecha(new Date());
            bitacoraTareaEstatus.setIdModulos(4);
            bitacoraTareaEstatus.setIdUsuarios(usuarioLogin.getIdUsuario());
            bitacoraTareaEstatus.setIdTareaId(16);
            bitacoraTareaSerice.guardarEnBitacora(bitacoraTareaEstatus);
            mensaje.mensaje("La clasificación " + clasificacionImportancia.getSigla() + " se ha guardado correctamente.", "verde");
        }
    }

    public ClasificacionImportancia getClasificacionImportancia() {
        return clasificacionImportancia;
    }

    public void setClasificacionImportancia(ClasificacionImportancia clasificacionImportancia) {
        this.clasificacionImportancia = clasificacionImportancia;
    }

    public Mensajes getMensaje() {
        return mensaje;
    }

    public void setMensaje(Mensajes mensaje) {
        this.mensaje = mensaje;
    }

}
