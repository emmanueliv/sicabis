package com.issste.sicabis.ejb.DAO;

import com.issste.sicabis.ejb.modelo.Procedimientos;
import com.issste.sicabis.ejb.utils.ErrorUtil;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@Stateless
public class ProcedimientoDAOImplement implements ProcedimientoDAO {

    @PersistenceContext(unitName = "sicabis")
    private EntityManager em;

    @Override
    public boolean guardaProcedimiento(Procedimientos procedimiento) {
        try {
            em.persist(procedimiento);
            return true;
        } catch (Exception e) {
            Logger.getLogger(ProcedimientoDAOImplement.class.getName()).log(Level.SEVERE, ErrorUtil.CODE_0101, e);
            return false;
        }
    }

    @Override
    public List<Procedimientos> obtenerByProcedimientos(Procedimientos procedimiento) {
        List<Procedimientos> resultList = null;
        String query = "";
        boolean bandera = false;
        try {
            query = "  Select p \n"
                    + "  From Procedimientos p \n"
                    + " Where p.activo = 1 \n";

            if (procedimiento.getIdProcedimiento().intValue() != 0) {
                query = query + "   and p.idProcedimiento = " + procedimiento.getIdProcedimiento() + " \n";
            }
            if (!procedimiento.getNumeroProcedimiento().equals("")) {
                query = query + "   and p.numeroProcedimiento = '" + procedimiento.getNumeroProcedimiento() + "' \n";
            }
            if (procedimiento.getIdTipoProcedimiento().getIdTipoProcedimiento().intValue() != -1) {
                query = query + "   and p.idTipoProcedimiento.idTipoProcedimiento = " + procedimiento.getIdTipoProcedimiento().getIdTipoProcedimiento() + " \n";
            }
            if (procedimiento.getIdCaracterProcedimiento().getIdCaracterProcedimiento().intValue() != -1) {
                query = query + "   and p.idCaracterProcedimiento.idCaracterProcedimiento = " + procedimiento.getIdCaracterProcedimiento().getIdCaracterProcedimiento() + " \n";
            }
            resultList = em.createQuery(query).getResultList();
        } catch (Exception e) {
            Logger.getLogger(ProcedimientoDAOImplement.class.getName()).log(Level.SEVERE, ErrorUtil.CODE_0101, e);
        }
        if (resultList != null && resultList.size() > 0) {
            return resultList;
        }
        return null;
    }

    @Override
    public boolean actualizaProcedimiento(Procedimientos procedimientos) {
        try {
            em.merge(procedimientos);
            return true;
        } catch (Exception e) {
            Logger.getLogger(ProcedimientoDAOImplement.class.getName()).log(Level.SEVERE, ErrorUtil.CODE_0101, e);
            return false;
        }
    }

    @Override
    public Procedimientos obtenerByNumeroProcedimiento(String numeroProcedimiento) {
        List<Procedimientos> resultList = null;
        try {
            resultList = em.createNamedQuery("Procedimientos.findByNumeroProcedimiento").setParameter("numeroProcedimiento", numeroProcedimiento).getResultList();
        } catch (Exception e) {
            Logger.getLogger(ProcedimientoDAOImplement.class.getName()).log(Level.SEVERE, ErrorUtil.CODE_0101, e);
            System.out.println("Exception obtenerByNumeroProcedimiento");
        }
        if (resultList.size() > 0 && resultList != null) {
            return resultList.get(0);
        }
        return null;
    }

    @Override
    public Procedimientos obtenerByNumeroProcedimientoById(Integer idProcedimiento) {
        List<Procedimientos> resultList = null;
        try {
            resultList = em.createNamedQuery("Procedimientos.findByIdProcedimiento").setParameter("idProcedimiento", idProcedimiento).getResultList();
        } catch (Exception e) {
            Logger.getLogger(ProcedimientoDAOImplement.class.getName()).log(Level.SEVERE, ErrorUtil.CODE_0101, e);
            System.out.println("Exception obtenerByNumeroProcedimiento");
        }
        if (resultList.size() > 0 && resultList != null) {
            return resultList.get(0);
        }
        return null;
    }

    @Override
    public Procedimientos obtenerByNumeroProcedimientoSeguimiento(String numeroProcedimiento) {
        List<Procedimientos> resultList = null;
        try {
            resultList = em.createQuery("Select p from Procedimientos p Where p.activo = 1 and p.idEstatus.idEstatus = 32 and p.numeroProcedimiento = :numeroProcedimiento").setParameter("numeroProcedimiento", numeroProcedimiento).getResultList();
        } catch (Exception e) {
            Logger.getLogger(ProcedimientoDAOImplement.class.getName()).log(Level.SEVERE, ErrorUtil.CODE_0101, e);
        }
        if (resultList.size() > 0 && resultList != null) {
            return resultList.get(0);
        }
        return null;
    }

}
