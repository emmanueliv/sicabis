/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.issste.sicabis.ejb.DAO;

import com.issste.sicabis.ejb.modelo.Almacen;
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
public class AlmacenDAOImplement implements AlmacenDAO {

    @PersistenceContext(unitName = "sicabis")
    private EntityManager em;

    @Override
    public List<Almacen> getAlmacenes() {
        List<Almacen> resultList = null;
        try {
            resultList = em.createNamedQuery("Almacen.findAll").getResultList();
        } catch (Exception e) {
            Logger.getLogger(AlmacenDAOImplement.class.getName()).log(Level.SEVERE, ErrorUtil.CODE_0101, e);
        }
        return resultList;
    }

    @Override
    public List<Almacen> getAlmacenesByActivo() {
        List<Almacen> resultList = null;
        try {
            resultList = em.createNamedQuery("Almacen.findAllByActivo").getResultList();
        } catch (Exception e) {
            Logger.getLogger(AlmacenDAOImplement.class.getName()).log(Level.SEVERE, ErrorUtil.CODE_0101, e);
        }
        return resultList;
    }

    @Override
    public List<Almacen> getAlmacenesActivosAndSelect(int idAlmacen) {
        List<Almacen> resultList = null;
        try {
            resultList = em.createNativeQuery("select * from almacen where activo = 1\n"
                    + "union\n"
                    + "select * from almacen where id_almacen = " + idAlmacen + "", Almacen.class).getResultList();
        } catch (Exception e) {
            Logger.getLogger(AlmacenDAOImplement.class.getName()).log(Level.SEVERE, ErrorUtil.CODE_0101, e);
        }
        return resultList;
    }

    @Override
    public List<Almacen> obtenerAlmacenByNombre(String nombre) {
        return em.createNamedQuery("Almacen.findByNombreAlmacen").setParameter("nombreAlmacen", nombre).getResultList();
    }

    @Override
    public void guardarAlmacen(Almacen almacen) {
        try {
            if (almacen.getIdAlmacen() == null) {
                em.persist(almacen);
            } else {
                em.merge(almacen);
            }
        } catch (Exception e) {
            Logger.getLogger(AlmacenDAOImplement.class.getName()).log(Level.SEVERE, ErrorUtil.CODE_0101, e);
        }
    }

}
