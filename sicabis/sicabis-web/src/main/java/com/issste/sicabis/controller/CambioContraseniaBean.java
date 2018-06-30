/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.issste.sicabis.controller;

import com.issste.sicabis.ejb.ln.BitacoraTareaSerice;
import com.issste.sicabis.ejb.ln.UsuariosService;
import com.issste.sicabis.ejb.modelo.BitacoraTareaEstatus;
import com.issste.sicabis.ejb.modelo.Usuarios;
import com.issste.sicabis.utils.Mensajes;
import com.issste.sicabis.utils.Utilidades;
import java.io.IOException;
import java.io.Serializable;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.servlet.ServletContext;
import org.apache.commons.codec.digest.DigestUtils;

/**
 *
 * @author kriosoft
 */
public class CambioContraseniaBean implements Serializable {

    private Usuarios usuarioLogin;
    private Utilidades util = new Utilidades();
    private boolean breadCrumbVisible;

    private BitacoraTareaEstatus bitacoraTareaEstatus;
    private Mensajes mensaje = new Mensajes();

    @EJB
    private BitacoraTareaSerice bitacoraTareaSerice;
    @EJB
    private UsuariosService usuariosService;

    public CambioContraseniaBean() {
        bitacoraTareaEstatus = new BitacoraTareaEstatus();
    }

    @PostConstruct
    public void init() {
        usuarioLogin = (Usuarios) util.getSessionAtributte("usuario");
        usuarioLogin.setContrasenia("");
        int val = (int) util.getSessionAtributte("breadCrumbVisible");
        breadCrumbVisible = val != 0;
    }

    public void guardarUsuario() {
        if (!usuarioLogin.getContrasenia().equals("")) {
            if (validarContrasenia()) {
                ExternalContext ctx = FacesContext.getCurrentInstance().getExternalContext();
                String ctxPath = ((ServletContext) ctx.getContext()).getContextPath();
                try {
                    usuarioLogin.setFechaCambioContrasenia(new Date());
                    usuarioLogin.setContrasenia(DigestUtils.md5Hex(usuarioLogin.getContrasenia()));
                    usuariosService.guardarUsuario(usuarioLogin, null, null, 1);
                    bitacoraTareaEstatus.setDescripcion("Modificacion contraseña usuario:" + usuarioLogin.getIdUsuario() + "");
                    bitacoraTareaEstatus.setFecha(new Date());
                    bitacoraTareaEstatus.setIdModulos(1);
                    bitacoraTareaEstatus.setIdUsuarios(usuarioLogin.getIdUsuario());
                    bitacoraTareaSerice.guardarEnBitacora(bitacoraTareaEstatus);
                    mensaje.mensaje("La contraseña se ha modificado correctamente. ", "verde");
                    ctx.redirect(ctxPath + "/vistas/menuInicio.xhtml");
                } catch (IOException ex) {
                    Logger.getLogger(PerfilesBean.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        } else {
            mensaje.mensaje("Es necesario proporcionar una contraseña. ", "rojo");
        }

    }

    public boolean validarContrasenia() {
        if (usuarioLogin.getContrasenia().length() < 6) {
            mensaje.mensaje("La contraseña debe contener almenos 6 caracteres. ", "rojo");
            return false;
        }
        return true;
    }

    public Usuarios getUsuarioLogin() {
        return usuarioLogin;
    }

    public void setUsuarioLogin(Usuarios usuarioLogin) {
        this.usuarioLogin = usuarioLogin;
    }

    public boolean isBreadCrumbVisible() {
        return breadCrumbVisible;
    }

    public void setBreadCrumbVisible(boolean breadCrumbVisible) {
        this.breadCrumbVisible = breadCrumbVisible;
    }

}
