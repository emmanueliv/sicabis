package com.issste.sicabis.ejb.service.silodisa.DAOImplement;

import com.issste.sicabis.ejb.service.silodisa.DAO.MapaEjecutivoDispG40DAO;
import com.issste.sicabis.ejb.service.silodisa.modelo.MapaEjecutivoDispG40;
import com.issste.sicabis.ejb.utils.ErrorUtil;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

@Stateless
public class MapaEjecutivoDispG40DAOImplement implements MapaEjecutivoDispG40DAO {

    @PersistenceContext(unitName = "sicabis")
    private EntityManager em;

    @Override
    public boolean guardar(MapaEjecutivoDispG40 dispG40) {
        try {
            em.persist(dispG40);
            em.flush();
            return true;
        } catch (Exception e) {
            Logger.getLogger(MapaEjecutivoDispG40DAOImplement.class.getName()).log(Level.SEVERE, "", e);
            return false;
        }
    }

    @Override
    public List<MapaEjecutivoDispG40> getAll() {
        List<MapaEjecutivoDispG40> resultList = null;
        try {
            resultList = em.createNamedQuery("MapaEjecutivoDispG40.findAll").getResultList();
        } catch (Exception e) {
            Logger.getLogger(MapaEjecutivoDispG40DAOImplement.class.getName()).log(Level.SEVERE, ErrorUtil.CODE_0101, e);
        }
        if (resultList != null && resultList.size() > 0) {
            return resultList;
        }
        return null;
    }

    @Override
    public List<MapaEjecutivoDispG40> getByClaveUnidad(String claveUnidad) {
        List<MapaEjecutivoDispG40> resultList = null;
        try {
            resultList = em.createQuery("SELECT med FROM MapaEjecutivoDispG40 med WHERE med.nombre =:nombre").setParameter("nombre", claveUnidad).getResultList();
        } catch (Exception e) {
            Logger.getLogger(MapaEjecutivoDispG40DAOImplement.class.getName()).log(Level.SEVERE, ErrorUtil.CODE_0101, e);
        }
        return resultList;
    }

    @Override
    public boolean eliminarExistencias() {
        try {
            Query q1 = em.createNativeQuery("DELETE FROM mapa_ejecutivo_disp_g40");
            q1.executeUpdate();
            return true;
        } catch (Exception e) {
            Logger.getLogger(MapaEjecutivoDispG40DAOImplement.class.getName()).log(Level.SEVERE, "MapaEjecutivoDispG40DAOImplement", e);
            return false;
        }
    }

}
