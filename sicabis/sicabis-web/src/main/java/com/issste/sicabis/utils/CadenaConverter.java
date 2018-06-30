/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.issste.sicabis.utils;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;


/**
 *
 * @author Toshiba Manolo
 */
@FacesConverter("recortarCadena")
public class CadenaConverter implements Converter{

    @Override
    public Object getAsObject(FacesContext context, UIComponent component, String value) {
        return value;
    }

    @Override
    public String getAsString(FacesContext context, UIComponent component, Object value) {
        String cadena =value.toString();    
        if(cadena.length()>20){
            cadena=cadena.substring(0, 20);
        }
        return cadena;
    }

    
}
