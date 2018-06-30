package com.issste.sicabis.ejb.DAO;

import com.issste.sicabis.ejb.modelo.InsumosSiam;
import com.issste.sicabis.ejb.utils.ErrorUtil;
import java.sql.SQLException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

@Stateless
public class InsumosSiamDAOImplement implements InsumosSiamDAO {

    @PersistenceContext(unitName = "sicabis")
    private EntityManager em;

    @Override
    public boolean guardarActualizar(InsumosSiam is) {
        try {
            em.merge(is);
            return true;
        } catch (Exception e) {
            Logger.getLogger(InsumosSiamDAOImplement.class.getName()).log(Level.SEVERE, ErrorUtil.CODE_0101, e);
            return false;
        } 
    }

    @Override
    public InsumosSiam obtenerByClave(String clave) {
        List<InsumosSiam> listaInsumos = null;
        try {
            listaInsumos = em.createNamedQuery("InsumosSiam.findByClave").setParameter("clave", clave).getResultList();
        } catch (Exception e) {
            Logger.getLogger(InsumosSiamDAOImplement.class.getName()).log(Level.SEVERE, ErrorUtil.CODE_0101, e);
        }
        if(listaInsumos != null && listaInsumos.size() > 0){
            return listaInsumos.get(0);
        }
        return null;
    }

    @Override
    public boolean borrarByClave(String clave) {
        try {
            Query query = em.createQuery("DELETE FROM InsumosSiam i WHERE i.clave=:clave");
            query.setParameter("clave", clave).executeUpdate();
            em.flush();
        } catch (Exception e) {
            Logger.getLogger(InsumosSiamDAOImplement.class.getName()).log(Level.SEVERE, ErrorUtil.CODE_0101, e);
            return false;
        }
        return true;
    }

    @Override
    public List<InsumosSiam> obtenerAll() {
        List<InsumosSiam> listaInsumos = null;
        try {
            listaInsumos = em.createNamedQuery("InsumosSiam.findAll").getResultList();
        } catch (Exception e) {
            Logger.getLogger(InsumosSiamDAOImplement.class.getName()).log(Level.SEVERE, ErrorUtil.CODE_0101, e);
        }
        if(listaInsumos != null && listaInsumos.size() > 0){
            return listaInsumos;
        }
        return null;
    }

}
