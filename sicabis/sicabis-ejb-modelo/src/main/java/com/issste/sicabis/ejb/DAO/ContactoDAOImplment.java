/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.issste.sicabis.ejb.DAO;

import com.issste.sicabis.ejb.modelo.Contactos;
import com.issste.sicabis.ejb.utils.ErrorUtil;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author kriosoft
 */
@Stateless
public class ContactoDAOImplment implements ContactoDAO {

    @PersistenceContext(unitName = "sicabis")
    private EntityManager em;

    @Override
    public List<Contactos> obtenerContactos() {
        return (List<Contactos>) em.createNamedQuery("Contactos.findAll").getResultList();
    }

    @Override
    public void guardarContacto(Contactos contacto) {
        try {
            if (contacto.getIdContacto() == null) {
                em.persist(contacto);
            } else {
                em.merge(contacto);
            }
        } catch (Exception e) {
            Logger.getLogger(DestinoDAOImplement.class.getName()).log(Level.SEVERE, ErrorUtil.CODE_0101, e);
        }
    }

    @Override
    public List<Contactos> obtenerContactoByNombre(String nombreContacto) {
        return (List<Contactos>) em.createNamedQuery("Contactos.findByNombre").setParameter("nombre", nombreContacto).getResultList();
    }
    
}
