package com.issste.sicabis.ejb.DAO;

import com.issste.sicabis.ejb.modelo.ProveedorFabricante;
import com.issste.sicabis.ejb.utils.ErrorUtil;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

/**
 *
 * @author fabianvr
 */
@Stateless
public class ProveedorFabricanteDAOImplement implements ProveedorFabricanteDAO {

    @PersistenceContext(unitName = "sicabis")
    private EntityManager em;

    @Override
    public boolean guardarProveedorFabricante(ProveedorFabricante proveedorFabricante) {
        try {
            em.persist(proveedorFabricante);
            em.flush();
            em.getEntityManagerFactory().getCache().evictAll();
            return true;
        } catch (Exception e) {
            Logger.getLogger(ProveedorFabricanteDAOImplement.class.getName()).log(Level.SEVERE, ErrorUtil.CODE_0101, e);
            return false;
        }
    }

    @Override
    public boolean borrarByIdProveedorFabricante(Integer idProcedimientoRcb) {
        try {
            Query query = em.createQuery("DELETE FROM ProveedorFabricante pf WHERE pf.idProcedimientoRcb=:idProcedimientoRcb");
            query.setParameter("idProcedimientoRcb", idProcedimientoRcb).executeUpdate();
            em.flush();
        } catch (Exception e) {
            Logger.getLogger(ProveedorFabricanteDAOImplement.class.getName()).log(Level.SEVERE, ErrorUtil.CODE_0101, e);
            return false;
        }
        return true;
    }

    @Override
    public List<ProveedorFabricante> obtenerByProveedorProcRcb(Integer idProveedor, Integer idProcedimientoRcb) {
        List<ProveedorFabricante> resultList = null;
        try {
            Query query = em.createQuery("Select pf From ProveedorFabricante pf Where pf.activo = 1 and pf.idProcedimientoRcb = :idProcedimientoRcb and pf.idProveedor.idProveedor = :idProveedor ");
            query.setParameter("idProcedimientoRcb", idProcedimientoRcb);
            query.setParameter("idProveedor", idProveedor);
            resultList = query.getResultList();
        } catch (Exception r) {
            Logger.getLogger(ProveedorFabricanteDAOImplement.class.getName()).log(Level.SEVERE, ErrorUtil.CODE_0101, r);
        }
        if (resultList != null && resultList.size() > 0) {
            return resultList;
        }
        return null;
    }
}
