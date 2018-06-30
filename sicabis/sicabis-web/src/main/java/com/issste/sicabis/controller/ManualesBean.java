    /*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.issste.sicabis.controller;

import java.io.InputStream;
import javax.faces.bean.ManagedBean;
import javax.faces.context.FacesContext;
import org.primefaces.model.DefaultStreamedContent;
import org.primefaces.model.StreamedContent;

/**
 *
 * @author 9RZCBG2
 */
@ManagedBean
public class ManualesBean {

    private StreamedContent file;

    public StreamedContent getFile(int opcion) {
        if (opcion == 1) {
            InputStream stream = FacesContext.getCurrentInstance().getExternalContext().getResourceAsStream("/resources/images/manualPlaneacion.pdf");
            file = new DefaultStreamedContent(stream, "application/pdf", "manualPlaneacion.pdf");
        } else if (opcion == 2) {
            InputStream stream = FacesContext.getCurrentInstance().getExternalContext().getResourceAsStream("/resources/images/manualAdquisicion.pdf");
            file = new DefaultStreamedContent(stream, "application/pdf", "manualAdquisicion.pdf");
        } else if (opcion == 3) {
            InputStream stream = FacesContext.getCurrentInstance().getExternalContext().getResourceAsStream("/resources/images/manualRecepcion.pdf");
            file = new DefaultStreamedContent(stream, "application/pdf", "manualRecepcion.pdf");
        }
        return file;
    }
}
