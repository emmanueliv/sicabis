/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.issste.sicabis.ejb.DAO;

import com.issste.sicabis.ejb.modelo.Compradores;
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
public class CompradoresDAOImplement implements CompradoresDAO {

    @PersistenceContext(unitName = "sicabis")
    private EntityManager em;

    @Override
    public void guardarComprador(Compradores comprador) {
        try {
            if (comprador.getIdComprador() == null) {
                em.persist(comprador);
            } else {
                em.merge(comprador);
            }
        } catch (Exception e) {
            Logger.getLogger(ProveedoresDAOImplement.class.getName()).log(Level.SEVERE, ErrorUtil.CODE_0101, e);
        }
    }

    @Override
    public List<Compradores> obtenerCompradorByNombre(String nombreComprador) {
        return (List<Compradores>) em.createNamedQuery("Compradores.findByNombre").setParameter("nombre", nombreComprador).getResultList();
    }

    @Override
    public List<Compradores> obtenerCompradores() {
        return (List<Compradores>) em.createNamedQuery("Compradores.findAll").getResultList();
    }

    @Override
    public List<Compradores> obtenerCompradoresByActivo() {
        return (List<Compradores>) em.createNamedQuery("Compradores.findByActivo").setParameter("activo", 1).getResultList();
    }

    @Override
    public List<Compradores> obtenerCompradoresByActivoAndSelect(int idComprador) {
        return (List<Compradores>) em.createNativeQuery("select * from compradores where activo = 1\n"
                + "union \n"
                + "select * from compradores where id_comprador = " + idComprador + "", Compradores.class).getResultList();
    }

}
