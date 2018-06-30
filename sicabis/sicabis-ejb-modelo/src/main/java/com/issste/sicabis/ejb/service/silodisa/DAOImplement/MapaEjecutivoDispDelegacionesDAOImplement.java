package com.issste.sicabis.ejb.service.silodisa.DAOImplement;

import com.issste.sicabis.ejb.service.silodisa.DAO.MapaEjecutivoDispDelegacionesDAO;
import com.issste.sicabis.ejb.service.silodisa.modelo.MapaEjecutivoDispDelegaciones;
import com.issste.sicabis.ejb.utils.ErrorUtil;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

@Stateless
public class MapaEjecutivoDispDelegacionesDAOImplement implements MapaEjecutivoDispDelegacionesDAO {

    @PersistenceContext(unitName = "sicabis")
    private EntityManager em;

    @Override
    public boolean guardar(MapaEjecutivoDispDelegaciones dispDelegaciones) {
        try {
            em.persist(dispDelegaciones);
            em.flush();
            return true;
        } catch (Exception e) {
            Logger.getLogger(MapaEjecutivoDispDelegacionesDAOImplement.class.getName()).log(Level.SEVERE, "", e);
            return false;
        }
    }

    @Override
    public List<MapaEjecutivoDispDelegaciones> getAll() {
        List<MapaEjecutivoDispDelegaciones> resultList = null;
        try {
            resultList = em.createNamedQuery("MapaEjecutivoDispDelegaciones.findAll").getResultList();
        } catch (Exception e) {
            Logger.getLogger(MapaEjecutivoDispDelegacionesDAOImplement.class.getName()).log(Level.SEVERE, ErrorUtil.CODE_0101, e);
        }
        if (resultList != null && resultList.size() > 0) {
            return resultList;
        }
        return null;
    }

    @Override
    public List<MapaEjecutivoDispDelegaciones> getByDelegacion(String delegacion) {
        List<MapaEjecutivoDispDelegaciones> resultList = null;
        try {
            resultList = em.createQuery("select me from MapaEjecutivoDispDelegaciones me where me.delegacion = :delegacion").setParameter("delegacion", delegacion).getResultList();
        } catch (Exception e) {
            Logger.getLogger(MapaEjecutivoDispDelegacionesDAOImplement.class.getName()).log(Level.SEVERE, ErrorUtil.CODE_0101, e);
        }
        return resultList;
    }

    @Override
    public boolean eliminarExistencias() {
        try {
            Query q1 = em.createNativeQuery("DELETE FROM mapa_ejecutivo_disp_delegaciones");
            q1.executeUpdate();
            return true;
        } catch (Exception e) {
            Logger.getLogger(MapaEjecutivoDispDelegacionesDAOImplement.class.getName()).log(Level.SEVERE, "MapaEjecutivoDispDelegacionesDAOImplement", e);
            return false;
        }
    }

}
