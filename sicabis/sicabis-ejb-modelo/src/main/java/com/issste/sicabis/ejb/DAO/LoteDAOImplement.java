package com.issste.sicabis.ejb.DAO;

import com.issste.sicabis.ejb.modelo.Lote;
import com.issste.sicabis.ejb.utils.ErrorUtil;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.Stateful;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

/**
 *
 * @author fabianvr
 */
@Stateful
public class LoteDAOImplement implements LoteDAO {

    @PersistenceContext(unitName = "sicabis")
    private EntityManager em;

    @Override
    public boolean guardarLotes(Lote lote) {
        try {
            if (lote.getIdLote() == null) {
                em.persist(lote);
            } else {
                em.merge(lote);
            }
            return true;
        } catch (Exception e) {
            Logger.getLogger(LoteDAOImplement.class.getName()).log(Level.SEVERE, ErrorUtil.CODE_0101, e);
            return false;
        }
    }

    @Override
    public List<Lote> loteByRemision(Integer remision) {
        List<Lote> resultList = null;
        try {
            resultList = em.createQuery("Select l From Lote l "
                    + "Where l.idRemision.idRemision = " + remision + " AND l.activo = 1").getResultList();
        } catch (Exception e) {
            Logger.getLogger(LoteDAOImplement.class.getName()).log(Level.SEVERE, ErrorUtil.CODE_0101, e);
        }
        if (resultList != null && resultList.size() > 0) {
            return resultList;
        }
        System.out.println("vacio");
        return null;
    }

    @Override
    public Date loteByClave(String clave, String l) {
        List<Date> resultList = null;
        String sQuery = "";
        Query query;

        try {
            sQuery = "Select l.fecha_caducidad From insumos i "
                    + "                     Join rcb_insumos ri on i.id_insumo = ri.id_insumo "
                    + "                     Join procedimiento_rcb pr on pr.id_rcb_insumos = ri.id_rcb_insumos "
                    + "                     Join fallo_procedimiento_rcb fpr on fpr.id_procedimiento_rcb = pr.id_procedimiento_rcb "
                    + "                     Join detalle_orden_suministro dos on dos.id_fallo_procedimiento_rcb = fpr.id_fallo_procedimiento_rcb "
                    + "                     Join remisiones r on r.id_detalle_orden_suministro = dos.id_detalle_orden_suministro "
                    + "                     Join lote l on l.id_remision = l.id_remision "
                    + "                     Where  l.activo = 1 "
                    + "                     And l.lote = '" + l + "' "
                    + "                     Group by l.fecha_caducidad";
            query = em.createNativeQuery(sQuery);
            resultList = query.getResultList();
        } catch (Exception e) {
            Logger.getLogger(FabricanteDAOImplement.class.getName()).log(Level.SEVERE, ErrorUtil.CODE_0101, e);
        }
        if (resultList != null && !resultList.isEmpty()) {
            System.out.println("Resultado " + resultList.get(0));
            return resultList.get(0);
        }

        return null;
    }

    @Override
    public List<Lote> buscarLotes() {
        return em.createNamedQuery("Lote.findAll").getResultList();
    }

    @Override
    public List<Lote> getLoteByCve(String nombreLote) {
        List<Lote> lista = new ArrayList<>();
        try {
            lista = em.createNamedQuery("Lote.findByLote").setParameter("lote", nombreLote).getResultList();
        } catch (Exception e) {
            Logger.getLogger(LoteDAOImplement.class.getName()).log(Level.SEVERE, ErrorUtil.CODE_0101, e);
        }
        return lista;
    }

    @Override
    public List<Lote> lote(String lote) {
        List<Lote> resultList = null;
        try {
            resultList = em.createQuery("Select l From Lote l "
                    + "Where l.lote = '" + lote + "' ").getResultList();
        } catch (Exception e) {
            Logger.getLogger(LoteDAOImplement.class.getName()).log(Level.SEVERE, ErrorUtil.CODE_0101, e);
        }
        if (resultList != null && !resultList.isEmpty()) {
            return resultList;
        }
        System.out.println("vacio");
        return null;
    }

    @Override
    public List<Lote> loteByLoteInsumo(String lote, String clave) {
        List<Lote> lotes = null;
        try {
            Query i = em.createQuery("Select l From Lote l \n"
                    + "  Join l.idRemision r \n"
                    + "  Join r.idDetalleOrdenSuministro dos \n"
                    + "  Join dos.idFalloProcedimientoRcb fpr \n"
                    + "  Join fpr.idProcedimientoRcb pr \n"
                    + "  Join pr.idRcbInsumos ri \n"
                    + "  Join ri.idInsumo i \n"
                    + "Where l.lote = :lote \n"
                    + "  and i.clave = :clave \n"
                    + "  and l.idLotePadre = null \n"
                    + "Order by l.idLote desc ");
            i.setParameter("lote", lote);
            i.setParameter("clave", clave);
            lotes = i.getResultList();
        } catch (Exception e) {
            Logger.getLogger(LoteDAOImplement.class.getName()).log(Level.SEVERE, ErrorUtil.CODE_0101, e);
        }
        if (lotes != null && lotes.size() > 0) {
            return lotes;
        }
        return null;
    }

    @Override
    public Lote getByIdLote(Integer idLote) {
        List<Lote> lista = new ArrayList<>();
        try {
            lista = em.createNamedQuery("Lote.findByIdLote").setParameter("idLote", idLote).getResultList();
        } catch (Exception e) {
            Logger.getLogger(LoteDAOImplement.class.getName()).log(Level.SEVERE, ErrorUtil.CODE_0101, e);
        }
        if (lista != null && lista.size() > 0) {
            return lista.get(0);
        }
        return null;
    }

}
