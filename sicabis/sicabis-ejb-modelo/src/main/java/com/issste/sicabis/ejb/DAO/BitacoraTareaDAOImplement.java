/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.issste.sicabis.ejb.DAO;

import com.issste.sicabis.ejb.modelo.BitacoraTareaEstatus;
import com.issste.sicabis.ejb.utils.ErrorUtil;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

/**
 *
 * @author kriosoft
 */
@Stateless
public class BitacoraTareaDAOImplement implements BitacoraTareaDAO {

    @PersistenceContext(unitName = "sicabis")
    private EntityManager em;

    @Override
    public List<BitacoraTareaEstatus> obtenerRegistros() {
        List<BitacoraTareaEstatus> resultList = null;
        try {
            resultList = em.createQuery("SELECT t FROM BitacoraTareaEstatus t").getResultList();
        } catch (Exception e) {
            Logger.getLogger(BitacoraTareaDAOImplement.class.getName()).log(Level.SEVERE, ErrorUtil.CODE_0101, e);
        }
        return resultList;
    }

    @Override
    public void guardarEnBitacora(BitacoraTareaEstatus bt) {
        try {
            if (bt.getIdBitacoraTareaEstatus() == null) {
                em.persist(bt);
            } else {
                em.merge(bt);
            }
        } catch (Exception e) {
            Logger.getLogger(BitacoraTareaDAOImplement.class.getName()).log(Level.SEVERE, ErrorUtil.CODE_0101, e);
        }
    }

    @Override
    public List<BitacoraTareaEstatus> consultaBitacoraTarea(BitacoraTareaEstatus bt,
            Date fechaInicial, Date fechaFinal) {
        List<BitacoraTareaEstatus> resultList = null;
        try {
            StringBuilder sb = new StringBuilder();
            sb.append("SELECT bte FROM BitacoraTareaEstatus bte WHERE bte.idBitacoraTareaEstatus != null ");
            if (fechaInicial != null && fechaFinal != null) {
                sb.append("AND bte.fecha BETWEEN :fechaInicial AND :fechaFinal ");
            }
            if (bt.getDescripcion() != null && !bt.getDescripcion().equals("")) {
                sb.append("AND bte.descripcion like :descripcion ");
            }
            if (bt.getIdUsuarios() != 0) {
                sb.append("AND bte.idUsuarios = :idUsuarios ");
            }
            if (bt.getIdTareaId() != 0) {
                sb.append("AND bte.idTareaId = :idTareaId ");
            }
            if (bt.getIdEstatus() != 0) {
                sb.append("AND bte.idEstatus = :idEstatus ");
            }
            Query query = em.createQuery(sb.toString());
            if (fechaInicial != null && fechaFinal != null) {
                query.setParameter("fechaInicial", fechaInicial);
                query.setParameter("fechaFinal", fechaFinal);
            }
            if (bt.getDescripcion() != null && !bt.getDescripcion().equals("")) {
                query.setParameter("descripcion", "%" + bt.getDescripcion() + "%");
            }
            if (bt.getIdUsuarios() != 0) {
                query.setParameter("idUsuarios", bt.getIdUsuarios());
            }
            if (bt.getIdTareaId() != 0) {
                query.setParameter("idTareaId", bt.getIdTareaId());
            }
            if (bt.getIdEstatus() != 0) {
                query.setParameter("idEstatus", bt.getIdEstatus());
            }
            System.out.println(sb.toString());
            resultList = query.getResultList();
        } catch (Exception e) {
            Logger.getLogger(BitacoraTareaDAOImplement.class.getName()).log(Level.SEVERE, ErrorUtil.CODE_0101, e);
        }
        return resultList;
    }

}
