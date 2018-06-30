/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.issste.sicabis.utils;

import com.issste.sicabis.ejb.ln.InsumosService;
import com.issste.sicabis.ejb.modelo.Insumos;
import java.util.List;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.ConverterException;
import javax.faces.convert.FacesConverter;

/**
 *
 * @author Toshiba Manolo
 */

@FacesConverter("insumoConverter")
public class InsumosConverter implements Converter {

    @EJB
    private InsumosService insumoService;
    @Override
    public Object getAsObject(FacesContext context, UIComponent component, String value) {
        if(value != null && value.trim().length() > 0) {
            try {
                System.out.println("valor: "+ value);
                InsumosAutoCompleteService service = (InsumosAutoCompleteService) context.getExternalContext().getApplicationMap().get("insumosAutoCompleteService");
                return service.getListInsumos().get(Integer.parseInt(value)-1);
            } catch(NumberFormatException e) {
                throw new ConverterException(new FacesMessage(FacesMessage.SEVERITY_ERROR, "Conversion Error", "Not a valid theme."));
            }
        }
        else {
            return null;
        }
    }

    @Override
    public String getAsString(FacesContext context, UIComponent component, Object value) {
        if(value != null) {
            return String.valueOf(((Insumos) value).getIdInsumo());
        }
        else {
            return "";
        }
    }
    
}
