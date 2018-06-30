/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.issste.sicabis.ejb.DAO;


import com.issste.sicabis.ejb.modelo.InsumosRcbSolicitudInvestigacionMercado;
import com.issste.sicabis.ejb.modelo.RcbInsumos;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

/**
 *
 * @author erik
 */

@Stateless
public class InsumosRcbSolicitudInvestigacionMercadoDAOImplement implements InsumosRcbSolicitudInvestigacionMercadoDAO{

     @PersistenceContext(unitName = "sicabis")
    private EntityManager em;
    @Override
    public List<InsumosRcbSolicitudInvestigacionMercado> traerTodasLasSolicitudes() {
        return (List<InsumosRcbSolicitudInvestigacionMercado>) em.createNamedQuery("InsumosRcbSolicitudInvestigacionMercado.findAll").getResultList();
    }

    @Override
    public InsumosRcbSolicitudInvestigacionMercado guardarInsumosSolicitud(InsumosRcbSolicitudInvestigacionMercado insumoSolicitud) {
//        InsumosRcbSolicitudInvestigacionMercado result=em.merge(insumoSolicitud);
        em.merge(insumoSolicitud);
        return insumoSolicitud;
    }

    @Override
    public List<InsumosRcbSolicitudInvestigacionMercado> traerLasSolicitudesPorIdRcbInsumo(Integer idRcbInsumos) {
        List<InsumosRcbSolicitudInvestigacionMercado> resultList = em.createQuery("SELECT i FROM InsumosRcbSolicitudInvestigacionMercado i WHERE i.idRcbInsumos.idRcbInsumos =:idRcbInsumos").setParameter("idRcbInsumos", idRcbInsumos).getResultList(); 
        return resultList;
    }

    @Override
    public Integer borrarInsumosRcbSolicitudPorRcbInsumo(RcbInsumos rcbInsumo) {
            Query query = em.createQuery(
      "DELETE FROM InsumosRcbSolicitudInvestigacionMercado i WHERE i.idRcbInsumos.idRcbInsumos =:idRcbInsumos");
       return query.setParameter("idRcbInsumos", rcbInsumo.getIdRcbInsumos()).executeUpdate();
    }
    
    
}
