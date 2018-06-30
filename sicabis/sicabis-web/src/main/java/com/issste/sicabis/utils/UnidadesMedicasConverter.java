/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.issste.sicabis.utils;

import com.issste.sicabis.ejb.ln.UnidadMedicaService;
import com.issste.sicabis.ejb.modelo.UnidadesMedicas;
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
@FacesConverter("unidadMedicaConverter")
public class UnidadesMedicasConverter implements Converter  {
    
    @EJB
    private UnidadMedicaService unidadMedicaService;

    @Override
    public Object getAsObject(FacesContext context, UIComponent component, String value) {
        if(value != null && value.trim().length() > 0) {
            try {
                System.out.println("valor: "+ value);                
                return unidadMedicaService.obtenUnidadesMedicas().get(Integer.parseInt(value)-1);
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
            return String.valueOf(((UnidadesMedicas) value).getIdUnidadesMedicas());
        }
        else {
            return null;
        }
    }
    
}
