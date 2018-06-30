package com.issste.sicabis.ejb.DAO;

import com.issste.sicabis.ejb.modelo.PeriodoMes;
import com.issste.sicabis.ejb.utils.ErrorUtil;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.Stateful;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

@Stateful
public class PeriodoMesDAOImplement implements PeriodoMesDAO {

    @PersistenceContext(unitName = "sicabis")
    private EntityManager em;

    @Override
    public List<PeriodoMes> getByAnio(int anio) {
        List<PeriodoMes> resultList = null;
        try {
            Query query = em.createNativeQuery("SELECT * FROM periodo_mes pm WHERE YEAR(pm.fecha_corte) = " + anio + " ORDER BY pm.fecha_corte", PeriodoMes.class);
            //query.setParameter("anio", anio);
            resultList = query.getResultList();
        } catch (Exception e) {
            Logger.getLogger(PeriodoMesDAOImplement.class.getName()).log(Level.ALL.SEVERE, ErrorUtil.CODE_0101, e);
        }
        if (resultList != null && resultList.size() > 0) {
            return resultList;
        }
        return null;
    }

    @Override
    public boolean guardarPeriodoMes(PeriodoMes pm) {
        try {
            if (pm.getIdPeriodoMes() == null) {
                em.persist(pm);
            } else {
                em.merge(pm);
            }
            return true;
        } catch (Exception e) {
            Logger.getLogger(PeriodoMesDAOImplement.class.getName()).log(Level.SEVERE, ErrorUtil.CODE_0101, e);
            return false;
        }
    }

    @Override
    public PeriodoMes getPeriodoActivo(int anio, int mes) {
        List<Object[]> objectList = null;
        PeriodoMes pm = null;
        try {
            Query query = em.createNativeQuery(""
                    + "SELECT pm.id_periodo_mes,"
                    + "       CASE \n"
                    + "         WHEN today > pm.fecha_corte THEN 1 \n"
                    + "         ELSE 0 \n"
                    + "       END valida, pm.fecha_corte, \n"
                    + "       (SELECT fecha_corte \n"
                    + "          FROM periodo_mes \n"
                    + "WHERE YEAR(fecha_corte) = " + anio + " \n"
                    + "  AND MONTH(fecha_corte) = " + (mes - 1) + ") fecha_anterior \n"
                    + "FROM periodo_mes pm \n"
                    + "WHERE YEAR(pm.fecha_corte) = " + anio + " \n"
                    + "  AND MONTH(pm.fecha_corte) = " + mes );
            //System.out.println("query-->"+query);
            objectList = query.getResultList();
            for (Object[] result : objectList) {
                pm = new PeriodoMes();
                pm.setIdPeriodoMes(Integer.parseInt(String.valueOf(result[0])));
                pm.setActivo(Integer.parseInt(String.valueOf(result[1])));
                pm.setFechaFinal((Date)result[2]);
                pm.setFechaInicial((Date)result[3]);
            }
        } catch (Exception e) {
            Logger.getLogger(PeriodoMesDAOImplement.class.getName()).log(Level.ALL.SEVERE, ErrorUtil.CODE_0101, e);
        }
        if(pm != null){
            return pm;
        }
        return null;
    }

    @Override
    public PeriodoMes getByFechaCorte(Date fechaCorte) {
        List<PeriodoMes> resultList = null;
        try {
            Query query = em.createNamedQuery("PeriodoMes.findByFechaCorte").setParameter("fechaCorte", fechaCorte);
            resultList = query.getResultList();
        } catch (Exception e) {
            Logger.getLogger(PeriodoMesDAOImplement.class.getName()).log(Level.ALL.SEVERE, ErrorUtil.CODE_0101, e);
        }
        if (resultList != null && resultList.size() > 0) {
            return resultList.get(0);
        }
        return null;
    }
    
    @Override
    public PeriodoMes getByFechaCorteMod(Date fechaCorte) {
        List<PeriodoMes> resultList = null;
        try {
            Query query = em.createNamedQuery("PeriodoMes.findByFechaCorteMod").setParameter("fechaCorte", fechaCorte);
            resultList = query.getResultList();
        } catch (Exception e) {
            Logger.getLogger(PeriodoMesDAOImplement.class.getName()).log(Level.ALL.SEVERE, ErrorUtil.CODE_0101, e);
        }
        if (resultList != null && resultList.size() > 0) {
            return resultList.get(0);
        }
        return null;
    }

}
