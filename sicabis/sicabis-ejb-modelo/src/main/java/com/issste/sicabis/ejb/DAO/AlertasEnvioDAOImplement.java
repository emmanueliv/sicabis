package com.issste.sicabis.ejb.DAO;

import com.issste.sicabis.ejb.modelo.AlertasEnvio;
import com.issste.sicabis.ejb.utils.ErrorUtil;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

@Stateless
public class AlertasEnvioDAOImplement implements AlertasEnvioDAO {

    @PersistenceContext(unitName = "sicabis")
    private EntityManager em;

    @Override
    public boolean guardar(AlertasEnvio alertasEnvio) {
        try {
            if (alertasEnvio.getIdAlertasEnvio() == null) {
                em.persist(alertasEnvio);
            } else {
                em.merge(alertasEnvio);
            }
            return true;
        } catch (Exception e) {
            Logger.getLogger(AlertasEnvioDAOImplement.class.getName()).log(Level.SEVERE, ErrorUtil.CODE_0101, e);
            return false;
        }
    }

    @Override
    public AlertasEnvio getByAnioMesClaveUnidad(AlertasEnvio alertasEnvio) {
        List<AlertasEnvio> resultList = null;
        String query = "";
        boolean bandera = false;
        try {
            query = "  SELECT ae \n"
                    + "  FROM AlertasEnvio ae \n"
                    + " WHERE ae.anio = :anio \n"
                    + "   AND ae.mes = :mes \n"
                    + "   AND ae.claveInsumo = :claveInsumo \n"
                    + "   AND ae.claveUnidad = :claveUnidad \n";
            Query q = em.createQuery(query);
//            q.setParameter("anio", alertasEnvio.getAnio());
//            q.setParameter("mes", alertasEnvio.getMes());
//            q.setParameter("claveInsumo", alertasEnvio.getClaveInsumo());
//            q.setParameter("claveUnidad", alertasEnvio.getClaveUnidad());
            resultList = q.getResultList();
        } catch (Exception e) {
            Logger.getLogger(AlertasEnvioDAOImplement.class.getName()).log(Level.SEVERE, ErrorUtil.CODE_0101, e);
        }
        if (resultList != null && resultList.size() > 0) {
            return resultList.get(0);
        }
        return null;
    }

    @Override
    public List<AlertasEnvio> getAlertasByClaveUnidad(Integer claveUnidad, String claveInsumo, Integer delegacion) {
        List<AlertasEnvio> resultList = null;
        String query = "";
        boolean bandera = false;
        try {
            query = "select * from alertas_envio ae\n"
                    + "join unidades_medicas um on um.clave_presupuestal = ae.clave_unidad\n"
                    + "join delegaciones d on d.id_delegacion = um.id_delegacion\n"
                    + "where ae.activo = 1\n";
            if (!claveInsumo.equals("")) {
                query = query + "and ae.clave_insumo = '" + claveInsumo + "'\n";
            }
            if (delegacion != -1) {
                query = query + "and d.id_delegacion = " + delegacion + "\n";
            }
            if (claveUnidad != -1) {
                query = query + "and um.id_unidades_medicas = " + claveUnidad + "\n";
            }
            Query q = em.createNativeQuery(query, AlertasEnvio.class);

            resultList = q.getResultList();
        } catch (Exception e) {
            Logger.getLogger(AlertasEnvioDAOImplement.class.getName()).log(Level.SEVERE, ErrorUtil.CODE_0101, e);
        }
        if (resultList != null && resultList.size() > 0) {
            return resultList;
        }
        return null;
    }

    @Override
    public List<AlertasEnvio> getByAnioMes(Integer anio, Integer mes) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean updateAllByActivo() {
        try {
            em.createQuery("UPDATE AlertasEnvio ae SET ae.activo = 0").executeUpdate();
            return true;
        } catch (Exception e) {
            Logger.getLogger(AlertasEnvioDAOImplement.class.getName()).log(Level.SEVERE, "", e);
            return false;
        }
    }

    @Override
    public List<AlertasEnvio> getByIdAlertaDpnUr(Integer ur, Integer anio, Integer mes) {
        List<AlertasEnvio> resultList = null;
        try {
            Query q = em.createQuery("SELECT ae FROM AlertasEnvio ae WHERE (:ur IS NULL OR ae.idAlertasDpn.ur.ur = :ur) AND (:anio IS NULL OR ae.idAlertasDpn.anio = :anio) AND (:mes IS NULL OR ae.idAlertasDpn.mes = :mes)");
            q.setParameter("ur", ur);
            q.setParameter("anio", anio);
            q.setParameter("mes", mes);
            resultList = q.getResultList();
        } catch (Exception e) {
            Logger.getLogger(AlertasEnvioDAOImplement.class.getName()).log(Level.SEVERE, ErrorUtil.CODE_0101, e);
        }
        if (resultList != null && resultList.size() > 0) {
            return resultList;
        }
        return null;
    }

    @Override
    public boolean generaAlertasEnvio(String ur, Integer idAlertasDpn, Integer idDpn) {
        try {
            Query q = em.createNativeQuery("INSERT INTO alertas_envio(id_alertas_dpn, id_dpn_insumo, id_estatus, \n"
                    + " consumo, activo) \n"
                    + "SELECT "+idAlertasDpn+", di.id_dpn_insumo, 231, di.salidas_siam, 1 \n"
                    + "  FROM dpn_insumo di \n"
                    + "       JOIN dpn dp ON (dp.id_dpn = di.id_dpn) \n"
                    + " WHERE dp.id_dpn = "+idDpn+" \n"
                    + "   AND SUBSTR(di.clave_unidad, 0, 3) = '"+ur+"' ");
            q.executeUpdate();
            return true;
        } catch (Exception e) {
            Logger.getLogger(AlertasEnvioDAOImplement.class.getName()).log(Level.SEVERE, ErrorUtil.CODE_0101, e);
            return false;
        }
    }

    @Override
    public boolean borraAlertasEnvio(Integer mes, Integer anio, Integer ur) {
        try {
            Query q = em.createNativeQuery("delete alertas_envio where id_alertas_envio in \n"
                    + "(select ae.id_alertas_envio \n"
                    + "from alertas_envio ae join alertas_dpn ad on \n"
                    + "(ae.id_alertas_dpn = ad.id_alertas_dpn) \n"
                    + "where ad.mes = "+mes+" \n"
                    + "and ad.anio = "+anio+" \n"
                    + "and ("+ur+" is null OR ad.ur = "+ur+"))");
            q.executeUpdate();
            return true;
        } catch (Exception e) {
            Logger.getLogger(AlertasEnvioDAOImplement.class.getName()).log(Level.SEVERE, ErrorUtil.CODE_0101, e);
            return false;
        }
    }

    @Override
    public boolean updateEstatusDpnById(Integer idAlertaEnvio, Integer idEstatus, Integer dpnSugerida) {
        try {
//            Query q = em.createQuery("UPDATE AlertasEnvio ae SET ae.dpnSugeridaUmu = "+dpnSugerida+", ae.idEstatus.idEstatus = "+idEstatus+" WHERE ae.idAlertasEnvio = :idAlertaEnvio");
            Query q = em.createNativeQuery("update alertas_envio set dpn_sugerida_umu = ?, id_estatus = ? where id_alertas_envio = ?");
            q.setParameter(1, dpnSugerida);
            q.setParameter(2, idEstatus);
            q.setParameter(3, idAlertaEnvio);
            q.executeUpdate();
            em.getEntityManagerFactory().getCache().evictAll();
            em.flush();
            em.clear();
            return true;
        } catch (Exception e) {
            Logger.getLogger(AlertasEnvioDAOImplement.class.getName()).log(Level.SEVERE, ErrorUtil.CODE_0101, e);
            return false;
        }
    }

}
