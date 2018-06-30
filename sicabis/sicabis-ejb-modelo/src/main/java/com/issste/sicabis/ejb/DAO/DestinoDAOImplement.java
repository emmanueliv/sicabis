/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.issste.sicabis.ejb.DAO;

import com.issste.sicabis.ejb.modelo.Destinos;
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
public class DestinoDAOImplement implements DestinoDAO {

    @PersistenceContext(unitName = "sicabis")
    private EntityManager em;

    @Override
    public List<Destinos> obtenerDestinos() {
        return (List<Destinos>) em.createNamedQuery("Destinos.findAll").getResultList();
    }

    @Override
    public List<Destinos> obtenerDestinosByActivo() {
        return (List<Destinos>) em.createNamedQuery("Destinos.findByActivo").setParameter("activo", 1).getResultList();
    }

    @Override
    public List<Destinos> obtenerDestinoByCve(String claveDestino) {
        return (List<Destinos>) em.createNamedQuery("Destinos.findByClave").setParameter("clave", claveDestino).getResultList();
    }

    @Override
    public List<Destinos> obtenerDestinoByCveAndActivo(int claveDestino) {
        return (List<Destinos>) em.createNativeQuery("select * from destinos where activo = 1\n"
                + "union \n"
                + "select * from destinos where id_destino = " + claveDestino + "",Destinos.class).getResultList();
    }

    @Override
    public void guardarDestino(Destinos destino) {
        try {
            if (destino.getIdDestino() == null) {
                em.persist(destino);
            } else {
                em.merge(destino);
            }
        } catch (Exception e) {
            Logger.getLogger(DestinoDAOImplement.class.getName()).log(Level.SEVERE, ErrorUtil.CODE_0101, e);
        }
    }

}
