/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.issste.sicabis.ejb.DAO;

import com.issste.sicabis.ejb.modelo.CrInsumos;
import com.issste.sicabis.ejb.modelo.RcbInsumos;
import java.util.ArrayList;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.NonUniqueResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

/**
 *
 * @author Toshiba Manolo
 */
@Stateless
public class CrInsumosDAOImplement implements CrInsumosDAO {

    @PersistenceContext(unitName = "sicabis")
    private EntityManager em;

    @Override
    public List<CrInsumos> getListCrInsumosByidCr(Integer idCr) {
        List<CrInsumos> resultList = em.createQuery("SELECT ci FROM CrInsumos ci WHERE ci.idCr.idCr =:idCr").setParameter("idCr", idCr).getResultList();
        return resultList;
    }

    @Override
    public CrInsumos getCrInsumoById(Integer idCrInsumos) {
        return (CrInsumos) em.createNamedQuery("CrInsumos.findByIdCrInsumos").setParameter("idCrInsumos", idCrInsumos).getSingleResult();
    }

    @Override
    public Integer traerMaxCRPorArea(Integer idArea) {
        try {

            return (Integer) em.createNativeQuery("select max(ci.id_cr) FROM cr_insumos ci \n"
                    + "join cr c on c.id_cr = ci.id_cr\n"
                    + "join insumos i on i.id_insumo = ci.id_insumo\n"
                    + " where c.id_area =" + idArea + "\n"
                    + "and c.id_estatus = 22")
                    .getSingleResult();

        } catch (NoResultException | NonUniqueResultException nre) {
            System.out.println("traerMaxCRPorArea nre: " + nre.getMessage());

        }
        // Code for handling NonUniqueResultException

        return 0;
    }

    @Override
    public Integer deleteCrInsumosByIdCr(Integer idCr) {
        Query query = em.createQuery(
                "DELETE FROM CrInsumos ci WHERE ci.idCr.idCr =:idCr");
        return query.setParameter("idCr", idCr).executeUpdate();
    }

    @Override
    public Integer deleteCrInsumos(CrInsumos crInsumo) {
        Query query = em.createQuery(
                "DELETE FROM CrInsumos ci WHERE ci.idCrInsumos =:idCrInsumos");
        return query.setParameter("idCrInsumos", crInsumo.getIdCrInsumos()).executeUpdate();
    }

    @Override
    public CrInsumos guardarCrInsumo(CrInsumos crInsumo) {
        em.persist(crInsumo);
//         em.flush();
        return crInsumo;
    }

    @Override
    public CrInsumos actualizarCrInsumo(CrInsumos crInsumo) {
        em.merge(crInsumo);
        return crInsumo;
    }

    @Override
    public List<Object[]> traerCrGrupo(Integer idCr) {

        try {

            return (List<Object[]>) em.createQuery("SELECT i.partidaPresupuestal,sum(ci.importe) FROM CrInsumos ci join ci.idInsumo i where ci.idCr.idCr=:idCr GROUP BY i.partidaPresupuestal")
                    .setParameter("idCr", idCr)
                    .getResultList();

        } catch (NoResultException | NonUniqueResultException nre) {
            System.out.println("traerMaxCRPorArea nre: " + nre.getMessage());

        }

        return new ArrayList<>();
    }

}
