package com.issste.sicabis.ejb.service.silodisa.DAOImplement;

import com.issste.sicabis.ejb.service.silodisa.DAO.ExistenciasPorClaveCenadiDAO;
import com.issste.sicabis.ejb.service.silodisa.modelo.ExistenciaPorClaveCenadi;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

/**
 *
 * @author 5XD9BG2
 */
@Stateless
public class ExistenciasPorClaveCenadiDAOImplement implements ExistenciasPorClaveCenadiDAO {

    @PersistenceContext(unitName = "sicabis")
    private EntityManager em;

    @Override
    public boolean guardar(ExistenciaPorClaveCenadi epcc) {
        try {
            em.persist(epcc);
            em.flush();
            return true;
        } catch (Exception e) {
            Logger.getLogger(ExistenciasPorClaveCenadiDAOImplement.class.getName()).log(Level.SEVERE, "", e);
            return false;
        }
    }

    @Override
    public boolean actualizar(ExistenciaPorClaveCenadi epcc) {
        try {
            em.merge(epcc);
            em.flush();
            return true;
        } catch (Exception e) {
            Logger.getLogger(ExistenciasPorClaveCenadiDAOImplement.class.getName()).log(Level.SEVERE, "", e);
            return false;
        }
    }

    @Override
    public List<ExistenciaPorClaveCenadi> existenciaPorClaveCenadiByClave(String clave) {
        List<ExistenciaPorClaveCenadi> resultList = null;
        try {
            System.out.println("entre a consulta");
            resultList = em.createQuery("Select epcc From ExistenciaPorClaveCenadi epcc "
                    + "Where epcc.clave = '" + clave + "' ").getResultList();
        } catch (Exception e) {
            Logger.getLogger(ExistenciasPorClaveCenadiDAOImplement.class.getName()).log(Level.SEVERE, "", e);
        }
        if (resultList != null && resultList.size() > 0) {
            return resultList;
        }
        System.out.println("vacio");
        return null;
    }

    @Override
    public Integer existenciaSumPorClaveCenadiByClave(String clave) {
        Object suma = 0;
        try {
            System.out.println("entre a consulta");
            suma = em.createNativeQuery("Select NVL(SUM(cast(epcc.existencia as Integer)),0) as suma From existencia_por_clave_cenadi epcc\n"
                    + "Where epcc.clave = '" + clave + "' ").getSingleResult();
        } catch (Exception e) {
            Logger.getLogger(ExistenciasPorClaveCenadiDAOImplement.class.getName()).log(Level.SEVERE, "", e);
        }
        return Integer.parseInt(String.valueOf(suma));
    }

    @Override
    public boolean eliminarExistencias() {
        try {
            Query q1 = em.createNativeQuery("DELETE FROM existencia_por_clave_cenadi");
            q1.executeUpdate();
            return true;
        } catch (Exception e) {
            Logger.getLogger(ExistenciasPorClaveCenadiDAOImplement.class.getName()).log(Level.SEVERE, "", e);
            return false;
        }
    }

//    @Override
//    public List<ExistenciaPorClaveCenadi> getByExistenciasPorClaveCenadi(ExistenciaPorClaveCenadi epcc) {
//        List<ExistenciaPorClaveCenadi> resultList = null;
//        String query = "";
//        try {
//            if(epcc.)
//            resultList = em.createQuery("SELECT epcc FROM ExistenciaPorClaveCenadi epcc WHERE epcc.clave = :clave ").getResultList();
//        } catch (Exception e) {
//            Logger.getLogger(ExistenciasPorClaveCenadiDAOImplement.class.getName()).log(Level.SEVERE, "", e);
//        }
//        if (resultList != null && resultList.size() > 0) {
//            return resultList;
//        }
//        return null;
//    }

    @Override
    public List<ExistenciaPorClaveCenadi> exitenciaAll() {
        List<ExistenciaPorClaveCenadi> resultList = null;
        try {
            System.out.println("entre a consulta");
            resultList = em.createQuery("Select epcc From ExistenciaPorClaveCenadi epcc").getResultList();
        } catch (Exception e) {
            Logger.getLogger(ExistenciasPorClaveCenadiDAOImplement.class.getName()).log(Level.SEVERE, "", e);
        }
        if (resultList != null && resultList.size() > 0) {
            return resultList;
        }
        System.out.println("vacio");
        return null;
    }

}
