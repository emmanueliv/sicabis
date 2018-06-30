/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.issste.sicabis.controller;

import com.issste.sicabis.ejb.ln.BitacoraTareaSerice;
import com.issste.sicabis.ejb.ln.PacientesService;
import com.issste.sicabis.ejb.modelo.BitacoraTareaEstatus;
import com.issste.sicabis.ejb.modelo.Pacientes;
import com.issste.sicabis.ejb.modelo.Usuarios;
import com.issste.sicabis.utils.Mensajes;
import com.issste.sicabis.utils.Utilidades;
import java.io.Serializable;
import java.util.Date;
import java.util.Objects;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;

/**
 *
 * @author kriosoft
 */
public class DetallePacientesBean implements Serializable {

    @EJB
    private BitacoraTareaSerice bitacoraTareaSerice;

    @EJB
    private PacientesService pacientesService;

    private String nombrePaciente;
    private Usuarios usuarioLogin;
    private Pacientes paciente;
    private BitacoraTareaEstatus bitacoraTareaEstatus;

    private Utilidades util = new Utilidades();
    private Mensajes mensaje = new Mensajes();

    public DetallePacientesBean() {
        usuarioLogin = new Usuarios();
        paciente = new Pacientes();
        bitacoraTareaEstatus = new BitacoraTareaEstatus();
    }

    @PostConstruct
    public void init() {
        usuarioLogin = (Usuarios) util.getSessionAtributte("usuario");
        paciente = (Pacientes) util.getSessionAtributte("paciente");
    }

    public void guardarPacientes() {
        nombrePaciente = paciente.getNombre();
        if (validar()) {
            if (existePacientes()) {
                paciente.setActivo(1);
                paciente.setFechaAlta(new Date());
                paciente.setUsuarioAlta(usuarioLogin.getUsuario());
                pacientesService.guardarPaciente(paciente);
                bitacoraTareaEstatus.setDescripcion("Actualizar paciente:" + nombrePaciente + "");
                bitacoraTareaEstatus.setFecha(new Date());
                bitacoraTareaEstatus.setIdModulos(4);
                bitacoraTareaEstatus.setIdUsuarios(usuarioLogin.getIdUsuario());
                bitacoraTareaEstatus.setIdTareaId(16);
                bitacoraTareaSerice.guardarEnBitacora(bitacoraTareaEstatus);
                paciente = new Pacientes();
                mensaje.mensaje("El paciente " + nombrePaciente + " se ha guardado correctamente.", "verde");
            } else {
                mensaje.mensaje("El paciente " + nombrePaciente + " ya existe.", "rojo");
            }
        }
    }

    public boolean validar() {
        boolean bandera = true;
        if (paciente.getNombre() == null || paciente.getNombre().equals("")) {
            mensaje.mensaje("Debes capturar el nombre del paciente", "amarillo");
            bandera = false;
        }
        if (paciente.getApaterno() == null || paciente.getApaterno().equals("")) {
            mensaje.mensaje("Debes capturar el apellido paterno del paciente", "amarillo");
            bandera = false;
        }
        if (paciente.getAmaterno() == null || paciente.getAmaterno().equals("")) {
            mensaje.mensaje("Debes capturar el apellido materno del paciente", "amarillo");
            bandera = false;
        }
        if (paciente.getNss() == null || paciente.getNss().equals("")) {
            mensaje.mensaje("Debes capturar el número de seguro del paciente", "amarillo");
            bandera = false;
        }
        return bandera;
    }

    public boolean existePacientes() {
        Pacientes p = pacientesService.obtenerPacientesByCurp(paciente.getCurp());
        if (p != null) {
            if (Objects.equals(p.getIdPaciente(), p.getIdPaciente())) {
                return true;
            } else {
                return false;
            }
        }
        return true;
    }

    public String getNombrePaciente() {
        return nombrePaciente;
    }

    public void setNombrePaciente(String nombrePaciente) {
        this.nombrePaciente = nombrePaciente;
    }

    public Usuarios getUsuarioLogin() {
        return usuarioLogin;
    }

    public void setUsuarioLogin(Usuarios usuarioLogin) {
        this.usuarioLogin = usuarioLogin;
    }

    public Pacientes getPaciente() {
        return paciente;
    }

    public void setPaciente(Pacientes paciente) {
        this.paciente = paciente;
    }

}
