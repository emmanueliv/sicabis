/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.issste.sicabis.ejb.DAO;

import com.issste.sicabis.ejb.modelo.ContactosAlertasDpn;
import com.issste.sicabis.ejb.utils.ErrorUtil;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author 9RZCBG2
 */
@Stateless
public class ContactosAlertasDpnDAOImplement implements ContactosAlertasDpnDAO {

    @PersistenceContext(unitName = "sicabis")
    private EntityManager em;

    @Override
    public List<ContactosAlertasDpn> getByIdDelegacion(Integer idDelegacion) {
        List<ContactosAlertasDpn> resultList = null;
        try {
            resultList = em.createQuery("SELECT cad FROM ContactosAlertasDpn cad WHERE cad.idDelegacion.idDelegacion = :idDelegacion AND cad.activo = 1").setParameter("idDelegacion", idDelegacion).getResultList();
        } catch (Exception e) {
            Logger.getLogger(ContactosAlertasDpnDAOImplement.class.getName()).log(Level.SEVERE, ErrorUtil.CODE_0101, e);
        }
        if (resultList != null && resultList.size() > 0) {
            return resultList;
        }
        return null;
    }

    @Override
    public List<ContactosAlertasDpn> getAllContactos(Integer activo) {
        List<ContactosAlertasDpn> resultList = null;
        try {
            resultList = em.createQuery("SELECT cad FROM ContactosAlertasDpn cad WHERE cad.activo = :activo").setParameter("activo", activo).getResultList();
        } catch (Exception e) {
            Logger.getLogger(ContactosAlertasDpnDAOImplement.class.getName()).log(Level.SEVERE, ErrorUtil.CODE_0101, e);
        }
        if (resultList != null && resultList.size() > 0) {
            return resultList;
        }
        return null;
    }

    @Override
    public ContactosAlertasDpn getByIdContactoAlectaDpn(Integer idContactoAlertaDpn) {
        ContactosAlertasDpn contacto = null;
        try {
            return contacto = (ContactosAlertasDpn) em.createQuery("SELECT cad FROM ContactosAlertasDpn cad WHERE cad.idContactosAlertasDpn = :idContactosAlertasDpn AND cad.activo = 1", ContactosAlertasDpn.class).setParameter("idContactosAlertasDpn", idContactoAlertaDpn).getSingleResult();
        } catch (Exception e) {
            Logger.getLogger(ContactosAlertasDpnDAOImplement.class.getName()).log(Level.SEVERE, ErrorUtil.CODE_0101, e);
        }
        return null;
    }

    @Override
    public List<ContactosAlertasDpn> getByIdUnidadMedica(Integer idUnidadMedica) {
        List<ContactosAlertasDpn> resultList = null;
        try {
            resultList = em.createQuery("SELECT cad FROM ContactosAlertasDpn cad WHERE cad.idUnidadMedica.idUnidadesMedicas = :idUnidadMedica AND cad.activo = 1").setParameter("idUnidadMedica", idUnidadMedica).getResultList();
        } catch (Exception e) {
            Logger.getLogger(ContactosAlertasDpnDAOImplement.class.getName()).log(Level.SEVERE, ErrorUtil.CODE_0101, e);
        }
        if (resultList != null && resultList.size() > 0) {
            return resultList;
        }
        return null;
    }

    @Override
    public Boolean guardarActualiza(ContactosAlertasDpn contactosAlertasDpn) {
        try {
            if (contactosAlertasDpn.getIdContactosAlertasDpn() == null) {
                em.persist(contactosAlertasDpn);
                return true;
            } else {
                em.merge(contactosAlertasDpn);
                return true;
            }
        } catch (Exception e) {
            Logger.getLogger(UsuariosDAOImplement.class.getName()).log(Level.SEVERE, ErrorUtil.CODE_0101, e);
            return false;
        }
    }

    @Override
    public List<ContactosAlertasDpn> getByMapas(Integer mapas) {
        List<ContactosAlertasDpn> resultList = null;
        try {
            resultList = em.createQuery("SELECT cad FROM ContactosAlertasDpn cad WHERE cad.activo = 1 AND cad.mapas = :mapas").setParameter("mapas", mapas).getResultList();
        } catch (Exception e) {
            Logger.getLogger(ContactosAlertasDpnDAOImplement.class.getName()).log(Level.SEVERE, ErrorUtil.CODE_0101, e);
        }
        if (resultList != null && resultList.size() > 0) {
            return resultList;
        }
        return null;
    }
}
