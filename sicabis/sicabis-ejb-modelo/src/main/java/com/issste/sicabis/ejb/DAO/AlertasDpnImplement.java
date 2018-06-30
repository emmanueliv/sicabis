package com.issste.sicabis.ejb.DAO;

import com.issste.sicabis.ejb.modelo.AlertasDpn;
import com.issste.sicabis.ejb.utils.ErrorUtil;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

@Stateless
public class AlertasDpnImplement implements AlertasDpnDAO {

    @PersistenceContext(unitName = "sicabis")
    private EntityManager em;

    @Override
    public List<AlertasDpn> getByAnioMes(Integer anio, Integer mes) {
        List<AlertasDpn> resultList = null;
        AlertasDpn ad = null;
        List<Object[]> objectList = null;
        try {
            Query q = em.createQuery("SELECT DISTINCT(ad.mes), ad.anio FROM AlertasDpn ad WHERE ad.activo = 1 AND ad.anio = :anio AND (:mes IS NULL OR ad.mes = :mes) ");
            q.setParameter("anio", anio);
            q.setParameter("mes", mes);
            objectList = q.getResultList();
            resultList = new ArrayList<>();
            for (Object[] result : objectList) {
                ad = new AlertasDpn();
                ad.setAnio(Integer.parseInt(String.valueOf(result[1])));
                ad.setMes(Integer.parseInt(String.valueOf(result[0])));
                resultList.add(ad);
            }
        } catch (Exception e) {
            Logger.getLogger(AlertasDpnImplement.class.getName()).log(Level.SEVERE, ErrorUtil.CODE_0101, e);
        }
        if (resultList != null && resultList.size() > 0) {
            return resultList;
        }
        return null;
    }

    @Override
    public AlertasDpn getByIdDelegacionAnioMes(Integer idDelegacion, Integer anio, Integer mes) {
        List<AlertasDpn> resultList = null;
        try {
            Query q = em.createQuery("SELECT ad FROM AlertasDpn ad WHERE ad.activo = 1 AND ad.anio = :anio AND ad.mes = :mes AND ad.idDelegacion.idDelegacion = :idDelegacion");
            q.setParameter("idDelegacion", idDelegacion);
            q.setParameter("anio", anio);
            q.setParameter("mes", mes);
            resultList = q.getResultList();
        } catch (Exception e) {
            Logger.getLogger(AlertasDpnImplement.class.getName()).log(Level.SEVERE, ErrorUtil.CODE_0101, e);
        }
        if (resultList != null && resultList.size() > 0) {
            return resultList.get(0);
        }
        return null;
    }

    @Override
    public boolean guardar(AlertasDpn alertasDpn) {
        try {
            if (alertasDpn.getIdAlertasDpn()== null) {
                em.persist(alertasDpn);
            } else {
                em.merge(alertasDpn);
            }
            return true;
        } catch (Exception e) {
            Logger.getLogger(AlertasDpnImplement.class.getName()).log(Level.SEVERE, ErrorUtil.CODE_0101, e);
            return false;
        }
    }

    @Override
    public List<AlertasDpn> getAllByAnioMes(Integer anio, Integer mes) {
        List<AlertasDpn> resultList = null;
        try {
            Query q = em.createQuery("SELECT ad FROM AlertasDpn ad WHERE ad.activo = 1 AND ad.anio = :anio AND ad.mes = :mes ");
            q.setParameter("anio", anio);
            q.setParameter("mes", mes);
            resultList = q.getResultList();
        } catch (Exception e) {
            Logger.getLogger(AlertasDpnImplement.class.getName()).log(Level.SEVERE, ErrorUtil.CODE_0101, e);
        }
        if (resultList != null && resultList.size() > 0) {
            return resultList;
        }
        return null;
    }

    @Override
    public boolean borraAlertasDpn(Integer anio, Integer mes, Integer ur) {
        try {
            Query q = em.createQuery("DELETE FROM AlertasDpn ad WHERE ad.anio = :anio AND ad.mes = :mes AND (:ur is null OR ad.ur.ur = :ur) ");
            q.setParameter("mes", mes);
            q.setParameter("anio", anio);
            q.setParameter("ur", ur);
            q.executeUpdate();
            return true;
        } catch (Exception e) {
            Logger.getLogger(AlertasDpnImplement.class.getName()).log(Level.SEVERE, "", e);
            return false;
        }
    }

    @Override
    public boolean updateEstatusById(Integer idEstatus, Integer idAlertasDpn) {
        try {
            Query q = em.createNativeQuery("update alertas_dpn set id_estatus = ? where id_alertas_dpn = ?");
//            Query q = em.createQuery("UPDATE AlertasDpn ad SET ad.idEstatus.idEstatus = "+idEstatus+" WHERE ad.idAlertasDpn = "+idAlertasDpn);
            q.setParameter(1, idEstatus);
            q.setParameter(2, idAlertasDpn);
            q.executeUpdate();
            em.getEntityManagerFactory().getCache().evictAll();
            em.flush();
            em.clear();
            return true;
        } catch (Exception e) {
            Logger.getLogger(AlertasDpnImplement.class.getName()).log(Level.SEVERE, ErrorUtil.CODE_0101, e);
            return false;
        }
    }

}
