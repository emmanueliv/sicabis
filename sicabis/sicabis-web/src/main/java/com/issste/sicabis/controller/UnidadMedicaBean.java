/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.issste.sicabis.controller;

import com.issste.sicabis.ejb.ln.BitacoraTareaSerice;
import com.issste.sicabis.ejb.ln.DelegacionesService;
import com.issste.sicabis.ejb.ln.NivelService;
import com.issste.sicabis.ejb.ln.UnidadMedicaService;
import com.issste.sicabis.ejb.modelo.BitacoraTareaEstatus;
import com.issste.sicabis.ejb.modelo.Delegaciones;
import com.issste.sicabis.ejb.modelo.Nivel;
import com.issste.sicabis.ejb.modelo.UnidadesMedicas;
import com.issste.sicabis.ejb.modelo.Usuarios;
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
import org.primefaces.context.RequestContext;

/**
 *
 * @author kriosoft
 */
public class UnidadMedicaBean implements Serializable {

    //EJB's
    @EJB
    private NivelService nivelService;
    @EJB
    private UnidadMedicaService unidadMedicaService;
    @EJB
    private DelegacionesService delegacionesService;
    @EJB
    private BitacoraTareaSerice bitacoraTareaSerice;

    private String nombreUnidadesMedicas;
    private Usuarios usuarioLogin;
    private UnidadesMedicas unidadMedica;
    private List<UnidadesMedicas> unidadMedicaList;
    private List<Delegaciones> delegacionesList;
    private List<Nivel> nivelList;
    private BitacoraTareaEstatus bitacoraTareaEstatus;
    private boolean bConcentradora;

    private Utilidades util = new Utilidades();
    private Mensajes mensaje = new Mensajes();

    public UnidadMedicaBean() {
        usuarioLogin = new Usuarios();
        unidadMedica = new UnidadesMedicas();
        unidadMedicaList = new ArrayList<>();
        delegacionesList = new ArrayList<>();
        nivelList = new ArrayList<>();
        bitacoraTareaEstatus = new BitacoraTareaEstatus();
    }

    @PostConstruct
    public void init() {
        usuarioLogin = (Usuarios) util.getSessionAtributte("usuario");
        delegacionesList = delegacionesService.obtenerDelegaciones();
        nivelList = nivelService.getAllNivel();
    }

    public void cancel() {
        ExternalContext ctx = FacesContext.getCurrentInstance().getExternalContext();
        String ctxPath = ((ServletContext) ctx.getContext()).getContextPath();
        try {
            ctx.redirect(ctxPath + "/vistas/administracion/catalogos/catUnidadMedica.xhtml");
        } catch (IOException ex) {
            Logger.getLogger(UnidadMedicaBean.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void consultarUnidadesMedicass() {
        unidadMedicaList = new ArrayList<>();
        if (nombreUnidadesMedicas == null || nombreUnidadesMedicas.equals("")) {
            unidadMedicaList = unidadMedicaService.unidadMedicaByActivo();
        } else {
            UnidadesMedicas u = unidadMedicaService.obtenerUnidadesMedicasByNombre(nombreUnidadesMedicas);
            if (u != null) {
                unidadMedicaList.add(u);
            }
        }
        if (unidadMedicaList == null || unidadMedicaList.isEmpty()) {
            mensaje.mensaje("No se encontraron unidades médicas.", "amarillo");
        }
    }

    public void mostrarDialogo(UnidadesMedicas um) {
        nombreUnidadesMedicas = um.getNombre();
        unidadMedica = um;
        RequestContext.getCurrentInstance().execute("PF('dialogEliminaUnidadesMedicas').show();");
    }

    public void eliminarUnidadesMedicas() {
        nombreUnidadesMedicas = unidadMedica.getNombre();
        unidadMedica.setFechaBaja(new Date());
        unidadMedica.setUsuarioBaja(usuarioLogin.getUsuario());
        unidadMedica.setActivo(1);
        unidadMedicaService.guardarUnidadesMedicas(unidadMedica);
        bitacoraTareaEstatus.setDescripcion("Elimina unidad medica:" + nombreUnidadesMedicas + "");
        bitacoraTareaEstatus.setFecha(new Date());
        bitacoraTareaEstatus.setIdModulos(4);
        bitacoraTareaEstatus.setIdUsuarios(usuarioLogin.getIdUsuario());
        bitacoraTareaEstatus.setIdTareaId(16);
        bitacoraTareaSerice.guardarEnBitacora(bitacoraTareaEstatus);
        unidadMedicaList = new ArrayList<>();
        unidadMedicaList = unidadMedicaService.unidadMedica();
        mensaje.mensaje("La unidadMedica " + nombreUnidadesMedicas + " ha sido dado de baja.", "verde");
    }

    public void modificarRedirect(UnidadesMedicas um) {
        ExternalContext ctx = FacesContext.getCurrentInstance().getExternalContext();
        String ctxPath = ((ServletContext) ctx.getContext()).getContextPath();
        util.setSessionMapValue("unidadMedica", um);
        try {
            ctx.redirect(ctxPath + "/vistas/administracion/catalogos/detalleUnidadMedica.xhtml");
        } catch (IOException ex) {
            Logger.getLogger(TiposDocumentoBean.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void guardarUnidadesMedicas() {
        nombreUnidadesMedicas = unidadMedica.getNombre();
        if (validar()) {
            if (!existeUnidadesMedicas()) {
                if (bConcentradora) {
                    unidadMedica.setConcentradora(1);
                }
                unidadMedica.setActivo(1);
                unidadMedica.setFechaAlta(new Date());
                unidadMedica.setUsuarioAlta(usuarioLogin.getUsuario());
                unidadMedicaService.guardarUnidadesMedicas(unidadMedica);
                bitacoraTareaEstatus.setDescripcion("Guardar unidad medica:" + nombreUnidadesMedicas + "");
                bitacoraTareaEstatus.setFecha(new Date());
                bitacoraTareaEstatus.setIdModulos(4);
                bitacoraTareaEstatus.setIdUsuarios(usuarioLogin.getIdUsuario());
                bitacoraTareaEstatus.setIdTareaId(16);
                bitacoraTareaSerice.guardarEnBitacora(bitacoraTareaEstatus);
                unidadMedica = new UnidadesMedicas();
                mensaje.mensaje("La unidadMedica " + nombreUnidadesMedicas + " se ha guardado correctamente.", "verde");
            } else {
                mensaje.mensaje("La unidadMedica " + nombreUnidadesMedicas + " ya existe.", "rojo");
            }
        }
    }

    public boolean validar() {
        boolean bandera = true;
        if (unidadMedica.getNombre() == null || unidadMedica.getNombre().equals("")) {
            mensaje.mensaje("Debes capturar el nombre de la unidad médica", "amarillo");
            bandera = false;
        }
        if (unidadMedica.getIdDelegacion() == null || unidadMedica.getIdDelegacion().equals("")) {
            mensaje.mensaje("Debes seleccionar la delegación", "amarillo");
            bandera = false;
        }
        return bandera;
    }

    public boolean existeUnidadesMedicas() {
        UnidadesMedicas u = unidadMedicaService.obtenerUnidadesMedicasByNombre(nombreUnidadesMedicas);
        return u != null;
    }

    public String getNombreUnidadesMedicas() {
        return nombreUnidadesMedicas;
    }

    public void setNombreUnidadesMedicas(String nombreUnidadesMedicas) {
        this.nombreUnidadesMedicas = nombreUnidadesMedicas;
    }

    public Usuarios getUsuarioLogin() {
        return usuarioLogin;
    }

    public void setUsuarioLogin(Usuarios usuarioLogin) {
        this.usuarioLogin = usuarioLogin;
    }

    public UnidadesMedicas getUnidadMedica() {
        return unidadMedica;
    }

    public void setUnidadMedica(UnidadesMedicas unidadMedica) {
        this.unidadMedica = unidadMedica;
    }

    public List<UnidadesMedicas> getUnidadMedicaList() {
        return unidadMedicaList;
    }

    public void setUnidadMedicaList(List<UnidadesMedicas> unidadMedicaList) {
        this.unidadMedicaList = unidadMedicaList;
    }

    public List<Delegaciones> getDelegacionesList() {
        return delegacionesList;
    }

    public void setDelegacionesList(List<Delegaciones> delegacionesList) {
        this.delegacionesList = delegacionesList;
    }

    public List<Nivel> getNivelList() {
        return nivelList;
    }

    public void setNivelList(List<Nivel> nivelList) {
        this.nivelList = nivelList;
    }

    public boolean isbConcentradora() {
        return bConcentradora;
    }

    public void setbConcentradora(boolean bConcentradora) {
        this.bConcentradora = bConcentradora;
    }
}
