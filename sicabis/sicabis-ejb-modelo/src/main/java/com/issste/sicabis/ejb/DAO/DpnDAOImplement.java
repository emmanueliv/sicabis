package com.issste.sicabis.ejb.DAO;

import com.issste.sicabis.ejb.modelo.Dpn;
import com.issste.sicabis.ejb.modelo.DpnInsumos;
import com.issste.sicabis.ejb.utils.ErrorUtil;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

@Stateless
public class DpnDAOImplement implements DpnDAO {

    @PersistenceContext(unitName = "sicabis")
    private EntityManager em;

    @Override
    public List<Dpn> getAllDesc() {
        try {
            return (List<Dpn>) em.createNamedQuery("Dpn.findAllDesc").getResultList();
        } catch (Exception e) {
            Logger.getLogger(DpnDAOImplement.class.getName()).log(Level.SEVERE, ErrorUtil.CODE_0101, e);
            return null;
        }
    }

    @Override
    public List<Dpn> getByAnio(int anio) {
        List<Dpn> resultList = null;
        try {
            Query query = em.createNativeQuery("SELECT d.* "
                    + "FROM dpn d JOIN periodo_mes pm on d.id_periodo_mes = pm.id_periodo_mes "
                    + "WHERE YEAR(pm.fecha_corte) = " + anio + " "
                    + "ORDER BY d.id_dpn DESC", Dpn.class);
            resultList = query.getResultList();
        } catch (Exception e) {
            Logger.getLogger(DpnDAOImplement.class.getName()).log(Level.ALL.SEVERE, ErrorUtil.CODE_0101, e);
        }
        if (resultList != null && resultList.size() > 0) {
            return resultList;
        }
        return null;
    }

    @Override
    public boolean guardaActualiza(Dpn dpn) {
        try {
            if (dpn.getIdDpn() == null) {
                em.persist(dpn);
            } else {
                em.merge(dpn);
            }
            return true;
        } catch (Exception e) {
            Logger.getLogger(DpnDAOImplement.class.getName()).log(Level.SEVERE, ErrorUtil.CODE_0101, e);
            return false;
        }
    }

    @Override
    public Dpn getDpnPrevio() {
        List<Dpn> resultList = null;
        try {
            Query query = em.createQuery("SELECT d FROM Dpn d WHERE d.idEstatus.idEstatus != 213 and d.activo = 0");
            resultList = query.getResultList();
        } catch (Exception e) {
            Logger.getLogger(DpnDAOImplement.class.getName()).log(Level.ALL.SEVERE, ErrorUtil.CODE_0101, e);
        }
        if (resultList != null && resultList.size() > 0) {
            return resultList.get(0);
        }
        return null;
    }

    @Override
    public Dpn getByIdDpn(Integer idDpn) {
        List<Dpn> resultList = null;
        try {
            Query query = em.createQuery("SELECT d FROM Dpn d WHERE d.idDpn = :idDpn").setParameter("idDpn", idDpn);
            resultList = query.getResultList();
        } catch (Exception e) {
            Logger.getLogger(DpnDAOImplement.class.getName()).log(Level.ALL.SEVERE, ErrorUtil.CODE_0101, e);
        }
        if (resultList != null && resultList.size() > 0) {
            return resultList.get(0);
        }
        return null;
    }

    @Override
    public Dpn getUltimaAutorizada() {
        List<Dpn> resultList = null;
        try {
            Query query = em.createQuery("SELECT d FROM Dpn d WHERE d.idEstatus.idEstatus = 213 and d.activo = 1");
            resultList = query.getResultList();
        } catch (Exception e) {
            Logger.getLogger(DpnDAOImplement.class.getName()).log(Level.ALL.SEVERE, ErrorUtil.CODE_0101, e);
        }
        if (resultList != null && resultList.size() > 0) {
            return resultList.get(0);
        }
        return null;
    }

    @Override
    public boolean actualizaUltimaDpn() {
        try {
            em.createQuery("UPDATE Dpn d SET d.activo = 0 WHERE d.activo = 1 AND d.idEstatus.idEstatus = 212").executeUpdate();
            return true;
        } catch (Exception e) {
            Logger.getLogger(DpnInsumosDAOImplement.class.getName()).log(Level.SEVERE, "", e);
            return false;
        }
    }

    @Override
    public boolean actualizaDpn(Dpn dpn) {
        try {
            Query q = em.createQuery("UPDATE Dpn d SET d.observaciones = :observaciones, d.totalPiezasDpn = :total, d.idEstatus = :estatus, d.usuarioModificacion = :usuarioModificacion, d.fechaModificacion = :fechaModificacion, d.dpnMes = :dpnMes WHERE d.idDpn = :idDpn");
            q.setParameter("observaciones", dpn.getObservaciones());
            q.setParameter("total", dpn.getTotalPiezasDpn());
            q.setParameter("estatus", dpn.getIdEstatus());
            q.setParameter("usuarioModificacion", dpn.getUsuarioModificacion());
            q.setParameter("fechaModificacion", dpn.getFechaModificacion());
            q.setParameter("dpnMes", dpn.getDpnMes());
            q.setParameter("idDpn", dpn.getIdDpn());
            q.executeUpdate();
//            Dpn result = em.find(Dpn.class, dpn.getIdDpn());
//            result.setIdEstatus(dpn.getIdEstatus());
//            result.setObservaciones(dpn.getObservaciones());
//            result.setTotalPiezasDpn(dpn.getTotalPiezasDpn());
            return true;
        } catch (Exception e) {
            Logger.getLogger(DpnInsumosDAOImplement.class.getName()).log(Level.SEVERE, "", e);
            return false;
        }
    }
    
    @Override
    public boolean actualizaDpnInsumo(DpnInsumos di) {
        try {
            Query q = em.createQuery("UPDATE DpnInsumos di SET di.piezasDpn = :piezasDpn WHERE di.idDpnInsumo = :idDpnInsumo");
            q.setParameter("piezasDpn", di.getPiezasDpn());
            q.setParameter("idDpnInsumo", di.getIdDpnInsumo());
            q.executeUpdate();
            return true;
        } catch (Exception e) {
            Logger.getLogger(DpnInsumosDAOImplement.class.getName()).log(Level.SEVERE, "", e);
            return false;
        }
    }

    @Override
    public Dpn getByAnioMesIdEstatus(Integer anio, Integer mes, Integer idEstatus) {
        List<Dpn> resultList = null;
        try {
            Query query = em.createQuery("SELECT d FROM Dpn d WHERE d.activo = 1 AND d.anio = :anio AND d.mes = :mes AND d.idEstatus.idEstatus = :idEstatus ");
            query.setParameter("anio", anio);
            query.setParameter("mes", mes);
            query.setParameter("idEstatus", idEstatus);
            resultList = query.getResultList();
        } catch (Exception e) {
            Logger.getLogger(DpnDAOImplement.class.getName()).log(Level.ALL.SEVERE, ErrorUtil.CODE_0101, e);
        }
        if (resultList != null && resultList.size() > 0) {
            return resultList.get(0);
        }
        return null;
    }

    @Override
    public Dpn getByIdPeriodoMes(Integer idPeriodoMes) {
        List<Dpn> resultList = null;
        try {
            Query query = em.createQuery("SELECT d FROM Dpn d WHERE d.idPeriodoMes.idPeriodoMes = :idPeriodoMes ");
            query.setParameter("idPeriodoMes", idPeriodoMes);
            resultList = query.getResultList();
        } catch (Exception e) {
            Logger.getLogger(DpnDAOImplement.class.getName()).log(Level.ALL.SEVERE, ErrorUtil.CODE_0101, e);
        }
        if (resultList != null && resultList.size() > 0) {
            return resultList.get(0);
        }
        return null;
    }

}
