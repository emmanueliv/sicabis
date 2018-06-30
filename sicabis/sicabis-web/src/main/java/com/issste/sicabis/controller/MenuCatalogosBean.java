/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.issste.sicabis.controller;

import java.io.IOException;
import java.io.Serializable;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.servlet.ServletContext;

/**
 *
 * @author kriosoft
 */
public class MenuCatalogosBean implements Serializable {

    private String ctxPath;

    public MenuCatalogosBean() {
        ExternalContext ctx = FacesContext.getCurrentInstance().getExternalContext();
        ctxPath = ((ServletContext) ctx.getContext()).getContextPath();
    }

    public void redirectCatalogo(String cat) {
        ExternalContext ctx = FacesContext.getCurrentInstance().getExternalContext();
        try {
            ctx.redirect(ctxPath + "/vistas/administracion/catalogos/" + cat + ".xhtml");
        } catch (IOException ex) {
            Logger.getLogger(MenuCatalogosBean.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public String getCtxPath() {
        return ctxPath;
    }

    public void setCtxPath(String ctxPath) {
        this.ctxPath = ctxPath;
    }

}