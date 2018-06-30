/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.issste.sicabis.ejb.ln;

import com.issste.sicabis.ejb.DAO.ContactoDAO;
import com.issste.sicabis.ejb.modelo.Contactos;
import java.util.List;
import javax.ejb.EJB;
import javax.ejb.Stateless;

/**
 *
 * @author kriosoft
 */
@Stateless
public class ContactoService {

    @EJB
    ContactoDAO contactosDAOImpl;

    public List<Contactos> obtenerContactos() {
        return contactosDAOImpl.obtenerContactos();
    }

    public void guardarContacto(Contactos contacto) {
        contactosDAOImpl.guardarContacto(contacto);
    }

    public Contactos obtenerContactoByNombre(String nombreContacto) {
        List<Contactos> conList = contactosDAOImpl.obtenerContactoByNombre(nombreContacto);
        if (conList.isEmpty()) {
            return null;
        } else {
            return conList.get(0);
        }
    }
    

}
