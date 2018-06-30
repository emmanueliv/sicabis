/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.issste.sicabis.ejb.service.silodisa.DAOImplement;

import com.issste.sicabis.ejb.service.silodisa.DAO.ExistenciaReservaPorClaveCenadDAO;
import com.issste.sicabis.ejb.service.silodisa.modelo.ExistenciaReservaClaveCenadi;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

/**
 *
 * @author 9RZCBG2
 */
@Stateless
public class ExistenciaReservaPorClaveCenadDAOImplement implements ExistenciaReservaPorClaveCenadDAO {

    @PersistenceContext(unitName = "sicabis")
    private EntityManager em;

    @Override
    public boolean guardar(ExistenciaReservaClaveCenadi ercc) {
        try {
            em.persist(ercc);
            em.flush();
            return true;
        } catch (Exception e) {
            Logger.getLogger(ExistenciaReservaPorClaveCenadDAOImplement.class.getName()).log(Level.SEVERE, "", e);
            return false;
        }
    }

    @Override
    public boolean actualizar(ExistenciaReservaClaveCenadi ercc) {
        try {
            em.merge(ercc);
            em.flush();
            return true;
        } catch (Exception e) {
            Logger.getLogger(ExistenciaReservaPorClaveCenadDAOImplement.class.getName()).log(Level.SEVERE, "", e);
            return false;
        }
    }

    @Override
    public List<ExistenciaReservaClaveCenadi> detalleExistenciaReservaClaveCenadi(String clave) {
        List<ExistenciaReservaClaveCenadi> resultList = null;
        try {
            resultList = em.createQuery("Select ercc From ExistenciaReservaClaveCenadi ercc "
                    + "Where ercc.clave = '" + clave + "' ").getResultList();
        } catch (Exception e) {
            Logger.getLogger(ExistenciaReservaPorClaveCenadDAOImplement.class.getName()).log(Level.SEVERE, "", e);
        }
        if (resultList != null && resultList.size() > 0) {
            return resultList;
        }
        System.out.println("vacio");
        return null;
    }
    
    @Override
    public boolean eliminarExistencias() {
        try {
            Query q1 = em.createNativeQuery("delete existencia_reserva_clave_cenadi");
            q1.executeUpdate();
            return true;
        } catch (Exception e) {
            Logger.getLogger(ExistenciaReservaPorClaveCenadDAOImplement.class.getName()).log(Level.SEVERE, "", e);
            return false;
        }
    }

    @Override
    public List<ExistenciaReservaClaveCenadi> existenciaReservaAll() {
      List<ExistenciaReservaClaveCenadi> resultList = null;
        try {
            resultList = em.createQuery("Select ercc From ExistenciaReservaClaveCenadi ercc ").getResultList();
        } catch (Exception e) {
            Logger.getLogger(ExistenciaReservaPorClaveCenadDAOImplement.class.getName()).log(Level.SEVERE, "", e);
        }
        if (resultList != null && resultList.size() > 0) {
            return resultList;
        }
        System.out.println("vacio");
        return null;
    }
}
