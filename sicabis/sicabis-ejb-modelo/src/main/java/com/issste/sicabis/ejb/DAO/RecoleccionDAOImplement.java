/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.issste.sicabis.ejb.DAO;

import com.issste.sicabis.ejb.modelo.FalloProcedimientoRcb;
import com.issste.sicabis.ejb.modelo.Recoleccion;
import com.issste.sicabis.ejb.utils.ErrorUtil;
import java.math.BigDecimal;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

/**
 *
 * @author fabianvr
 */
@Stateless
public class RecoleccionDAOImplement implements RecoleccionDAO {

    @PersistenceContext(unitName = "sicabis")
    private EntityManager em;

    @Override
    public Integer guardarRecoleccion(Recoleccion recoleccion) {
        try {
            em.persist(recoleccion);
            em.flush();
            return recoleccion.getIdRecoleccion();
        } catch (Exception e) {
            Logger.getLogger(RecoleccionDAOImplement.class.getName()).log(Level.SEVERE, ErrorUtil.CODE_0101, e);
            return null;
        }
    }

    @Override
    public List<Recoleccion> recoleccion() {
        List<Recoleccion> resultList = null;
        try {
            resultList = em.createQuery("Select r From Recoleccion r").getResultList();
        } catch (Exception e) {
            Logger.getLogger(RecoleccionDAOImplement.class.getName()).log(Level.SEVERE, ErrorUtil.CODE_0101, e);
        }
        if (resultList != null && resultList.size() > 0) {
            return resultList;
        }
        System.out.println("vacio");
        return null;
    }

    @Override
    public Integer folioRecoleccion() {
        List<Recoleccion> resultList = null;
        Integer maximo = 0;
        try {
            maximo = (Integer) em.createQuery("Select MAX(r.idRecoleccion) From Recoleccion r").getSingleResult();
            System.out.println("maximo-->" + maximo);
        } catch (Exception e) {
            Logger.getLogger(RecoleccionDAOImplement.class.getName()).log(Level.SEVERE, ErrorUtil.CODE_0101, e);
        }
        if (maximo != null) {
            return maximo;
        }
        return null;
    }

    @Override
    public BigDecimal precioPromedioByClave(String clave) {
        BigDecimal maximo = null;
        Query query;
        List<Object[]> objectList = null;
        try {
            query = em.createQuery("SELECT fpr.precioUnitario, MAX(fpr.idFalloProcedimientoRcb) FROM FalloProcedimientoRcb fpr "
                    + "WHERE fpr.idProcedimientoRcb.idRcbInsumos.idInsumo.clave = '" + clave + "'"
                    + "GROUP BY fpr.precioUnitario");
            System.out.println("query---->" + query);
            objectList = query.getResultList();
            for (Object[] result : objectList) {
                BigDecimal precio = (BigDecimal) result[0];
                maximo = precio;
            }
        } catch (Exception e) {
            Logger.getLogger(RecoleccionDAOImplement.class.getName()).log(Level.SEVERE, ErrorUtil.CODE_0101, e);
        }
        if (maximo != null) {
            return maximo;
        }
        return null;
    }

    @Override
    public List<Recoleccion> buscarRecoleccion(String folio) {
        List<Recoleccion> resultList = null;
        try {
            if (folio.equals("")) {
                resultList = em.createQuery("Select r From Recoleccion r").getResultList();
            } else {
                resultList = em.createQuery("Select r From Recoleccion r "
                        + "Where r.oficioRecoleccion = '" + folio + "' ").getResultList();
            }
        } catch (Exception e) {
            Logger.getLogger(RecoleccionDAOImplement.class.getName()).log(Level.SEVERE, ErrorUtil.CODE_0101, e);
        }
        if (resultList != null && resultList.size() > 0) {
            return resultList;
        }
        System.out.println("vacio");
        return null;
    }

    @Override
    public List<Recoleccion> recoleccionById(Integer id) {
        List<Recoleccion> resultList = null;
        try {

            resultList = em.createQuery("Select r From Recoleccion r  "
                    + "Where r.idRecoleccion = " + id + " ").getResultList();

        } catch (Exception e) {
            Logger.getLogger(RecoleccionDAOImplement.class.getName()).log(Level.SEVERE, ErrorUtil.CODE_0101, e);
        }
        if (resultList != null && resultList.size() > 0) {
            return resultList;
        }
        System.out.println("vacio");
        return null;
    }

}
