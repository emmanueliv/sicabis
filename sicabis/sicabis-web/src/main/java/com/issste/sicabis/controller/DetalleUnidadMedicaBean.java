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
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Objects;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;

/**
 *
 * @author kriosoft
 */
public class DetalleUnidadMedicaBean implements Serializable {

    @EJB
    private BitacoraTareaSerice bitacoraTareaSerice;

    @EJB
    private NivelService nivelService;

    @EJB
    private UnidadMedicaService unidadMedicaService;

    @EJB
    private DelegacionesService delegacionesService;

    private String nombreUnidadesMedicas;
    private String nombreUnidadesMedicasAnt;
    private String claveUMUant;
    private Integer idDelagacionAnt;
    private Integer idNivelAnt;
    private String partidaPresupuestalAnt;
    private boolean bConcentradora;

    private Usuarios usuarioLogin;
    private UnidadesMedicas unidadMedica;
    private BitacoraTareaEstatus bitacoraTareaEstatus;

    private List<Delegaciones> delegacionesList;
    private List<Nivel> nivelList;

    private Utilidades util = new Utilidades();
    private Mensajes mensaje = new Mensajes();

    public DetalleUnidadMedicaBean() {
        usuarioLogin = new Usuarios();
        unidadMedica = new UnidadesMedicas();
        bitacoraTareaEstatus = new BitacoraTareaEstatus();
        nivelList = new ArrayList<>();
        delegacionesList = new ArrayList<>();
    }

    @PostConstruct
    public void init() {
        usuarioLogin = (Usuarios) util.getSessionAtributte("usuario");
        unidadMedica = (UnidadesMedicas) util.getSessionAtributte("unidadMedica");
        delegacionesList = delegacionesService.obtenerDelegaciones();
        if (unidadMedica.getConcentradora() == 1) {
            bConcentradora = true;
        }
        nivelList = nivelService.getAllNivel();
        nombreUnidadesMedicasAnt = unidadMedica.getNombre();
        idDelagacionAnt = unidadMedica.getIdDelegacion().getIdDelegacion();
        idNivelAnt = unidadMedica.getIdNivel().getIdNivel();
        claveUMUant = unidadMedica.getClaveUmu();
        partidaPresupuestalAnt = unidadMedica.getClavePresupuestal();
    }

    public void guardarUnidadesMedicas() {
        nombreUnidadesMedicas = unidadMedica.getNombre();
        if (validar()) {
            if (existeUnidadesMedicas()) {
                int id = unidadMedica.getIdUnidadesMedicas();
                if (bConcentradora) {
                    unidadMedica.setConcentradora(1);
                } else {
                    unidadMedica.setConcentradora(0);
                }
                unidadMedica.setIdUnidadesMedicas(null);
                unidadMedica.setActivo(1);
                unidadMedica.setIdPadre(id);
                unidadMedica.setFechaAlta(new Date());
                unidadMedica.setUsuarioAlta(usuarioLogin.getUsuario());
                unidadMedicaService.guardarUnidadesMedicas(unidadMedica);

                unidadMedica = new UnidadesMedicas();
                if (bConcentradora) {
                    unidadMedica.setConcentradora(1);
                } else {
                    unidadMedica.setConcentradora(0);
                }
                unidadMedica.setFechaAlta(new Date());
                unidadMedica.setActivo(0);
                unidadMedica.setIdUnidadesMedicas(id);
                unidadMedica.setUsuarioAlta(usuarioLogin.getUsuario());
                unidadMedica.setNombre(nombreUnidadesMedicasAnt);
                unidadMedica.setIdDelegacion(new Delegaciones(idDelagacionAnt));
                unidadMedica.setIdNivel(new Nivel(idNivelAnt));
                unidadMedica.setClaveUmu(claveUMUant);
                unidadMedica.setClavePresupuestal(partidaPresupuestalAnt);
                unidadMedicaService.guardarUnidadesMedicas(unidadMedica);

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
        if (u != null) {
            if (Objects.equals(u.getIdUnidadesMedicas(), unidadMedica.getIdUnidadesMedicas())) {
                return true;
            } else {
                return false;
            }
        }
        return true;
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
