/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.issste.sicabis.ejb.DAO;

import com.issste.sicabis.ejb.modelo.FundamentoLegal;
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
public class ArticulosDAOImplement implements ArticulosDAO {

    @PersistenceContext(unitName = "sicabis")
    private EntityManager em;

    @Override
    public List<FundamentoLegal> getFundamentos() {
        List<FundamentoLegal> resultList = null;
        try {
            resultList = em.createNamedQuery("FundamentoLegal.findAll").getResultList();
        } catch (Exception e) {
            Logger.getLogger(ArticulosDAOImplement.class.getName()).log(Level.SEVERE, ErrorUtil.CODE_0101, e);
        }
        return resultList;
    }

    @Override
    public List<FundamentoLegal> getFundamentosByActivo() {
        List<FundamentoLegal> resultList = null;
        try {
            resultList = em.createNamedQuery("FundamentoLegal.findAllByActivo").getResultList();
        } catch (Exception e) {
            Logger.getLogger(ArticulosDAOImplement.class.getName()).log(Level.SEVERE, ErrorUtil.CODE_0101, e);
        }
        return resultList;
    }

    @Override
    public List<FundamentoLegal> obtenerFundamentoLegalByNombre(String nombreFundamentoLegal) {
        return em.createNamedQuery("FundamentoLegal.findByNombre").setParameter("nombre", nombreFundamentoLegal).getResultList();
    }

    @Override
    public List<FundamentoLegal> obtenerFundamentoLegalByIdAndActivo(int fundamentoLegal) {
        return em.createNativeQuery("select * from fundamento_legal where activo = 1\n"
                + "union \n"
                + "select * from fundamento_legal where id_fundamento_legal = " + fundamentoLegal + "",FundamentoLegal.class).getResultList();
    }

    @Override
    public void guardarFundamentoLegal(FundamentoLegal fdmt) {
        try {
            if (fdmt.getIdFundamentoLegal() == null) {
                em.persist(fdmt);
            } else {
                em.merge(fdmt);
            }
        } catch (Exception e) {
            Logger.getLogger(ArticulosDAOImplement.class.getName()).log(Level.SEVERE, ErrorUtil.CODE_0101, e);
        }
    }

}
