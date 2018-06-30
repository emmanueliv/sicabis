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
public class PacientesBean implements Serializable {

    @EJB
    private BitacoraTareaSerice bitacoraTareaSerice;

    @EJB
    private PacientesService pacientesService;

    private String nombrePaciente;
    private String curp;
    private Usuarios usuarioLogin;
    private Pacientes paciente;
    private BitacoraTareaEstatus bitacoraTareaEstatus;
    private List<Pacientes> pacientesList;

    private Utilidades util = new Utilidades();
    private Mensajes mensaje = new Mensajes();

    public PacientesBean() {
        usuarioLogin = new Usuarios();
        paciente = new Pacientes();
        pacientesList = new ArrayList<>();
        bitacoraTareaEstatus = new BitacoraTareaEstatus();
    }

    @PostConstruct
    public void init() {
        usuarioLogin = (Usuarios) util.getSessionAtributte("usuario");
    }

    public void cancel() {
        ExternalContext ctx = FacesContext.getCurrentInstance().getExternalContext();
        String ctxPath = ((ServletContext) ctx.getContext()).getContextPath();
        try {
            ctx.redirect(ctxPath + "/vistas/administracion/catalogos/catPacientes.xhtml");
        } catch (IOException ex) {
            Logger.getLogger(TiposDocumentoBean.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void consultarPacientes() {
        pacientesList = new ArrayList<>();
        if (nombrePaciente == null || nombrePaciente.equals("")) {
            pacientesList = pacientesService.obtenerPacientes();
        } else {
            Pacientes p = pacientesService.obtenerPacientesByCurp(curp);
            if (p != null) {
                pacientesList.add(p);
            }
        }
        if (pacientesList == null || pacientesList.isEmpty()) {
            mensaje.mensaje("No se encontraron pacientes.", "amarillo");
        }
    }

    public void mostrarDialogo(Pacientes p) {
        nombrePaciente = p.getNombre() + " " + p.getApaterno() + " " + p.getAmaterno();
        paciente = p;
        RequestContext.getCurrentInstance().execute("PF('dialogEliminaPacientes').show();");
    }

    public void eliminarPacientes() {
        nombrePaciente = paciente.getNombre() + " " + paciente.getApaterno() + " " + paciente.getAmaterno();
        paciente.setActivo(0);
        paciente.setFechaBaja(new Date());
        paciente.setUsuarioBaja(usuarioLogin.getUsuario());
        pacientesService.guardarPaciente(paciente);
        bitacoraTareaEstatus.setDescripcion("Borrado paciente:" + nombrePaciente + "");
        bitacoraTareaEstatus.setFecha(new Date());
        bitacoraTareaEstatus.setIdModulos(4);
        bitacoraTareaEstatus.setIdUsuarios(usuarioLogin.getIdUsuario());
        bitacoraTareaEstatus.setIdTareaId(16);
        bitacoraTareaSerice.guardarEnBitacora(bitacoraTareaEstatus);
        pacientesList = new ArrayList<>();
        pacientesList = pacientesService.obtenerPacientes();
        mensaje.mensaje("El pacientes " + nombrePaciente + " ha sido dado de baja.", "verde");
    }

    public void modificarRedirect(Pacientes pac) {
        ExternalContext ctx = FacesContext.getCurrentInstance().getExternalContext();
        String ctxPath = ((ServletContext) ctx.getContext()).getContextPath();
        util.setSessionMapValue("paciente", pac);
        try {
            ctx.redirect(ctxPath + "/vistas/administracion/catalogos/detallePaciente.xhtml");
        } catch (IOException ex) {
            Logger.getLogger(TiposDocumentoBean.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void guardarPacientes() {
        nombrePaciente = paciente.getNombre() + " " + paciente.getApaterno() + " " + paciente.getAmaterno();
        if (validar()) {
            if (!existePacientes()) {
                paciente.setActivo(1);
                paciente.setFechaAlta(new Date());
                paciente.setUsuarioAlta(usuarioLogin.getUsuario());
                pacientesService.guardarPaciente(paciente);
                bitacoraTareaEstatus.setDescripcion("Guardar paciente:" + nombrePaciente + "");
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
        Pacientes c = pacientesService.obtenerPacientesByCurp(paciente.getCurp());
        return c != null;
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

    public List<Pacientes> getPacientesList() {
        return pacientesList;
    }

    public void setPacientesList(List<Pacientes> pacientesList) {
        this.pacientesList = pacientesList;
    }

    public String getCurp() {
        return curp;
    }

    public void setCurp(String curp) {
        this.curp = curp;
    }

}
