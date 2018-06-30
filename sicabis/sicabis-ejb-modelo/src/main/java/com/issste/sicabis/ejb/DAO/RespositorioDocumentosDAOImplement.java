package com.issste.sicabis.ejb.DAO;

import com.issste.sicabis.ejb.modelo.RespositorioDocumentos;
import com.issste.sicabis.ejb.utils.ErrorUtil;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

@Stateless
public class RespositorioDocumentosDAOImplement implements RespositorioDocumentosDAO {

    @PersistenceContext(unitName = "sicabis")
    private EntityManager em;

    @Override
    public boolean guardaProcedimiento(RespositorioDocumentos respositorioDocumentos) {
        try {
            em.persist(respositorioDocumentos);
            em.flush();
            return true;
        } catch (Exception e) {
            Logger.getLogger(RespositorioDocumentosDAOImplement.class.getName()).log(Level.SEVERE, ErrorUtil.CODE_0101, e);
            return false;
        }
    }

    @Override
    public boolean actualizaProcedimiento(RespositorioDocumentos respositorioDocumentos) {
        try {
            em.merge(respositorioDocumentos);
            em.flush();
            return true;
        } catch (Exception e) {
            Logger.getLogger(RespositorioDocumentosDAOImplement.class.getName()).log(Level.SEVERE, ErrorUtil.CODE_0101, e);
            return false;
        }
    }

    @Override
    public boolean borrarByIdRespositorioDocumento(Integer idRespositorioDocumento) {
        try {
            Query query = em.createQuery("DELETE FROM RespositorioDocumentos rd WHERE rd.idRespositorioDocumento=:idRespositorioDocumento");
            query.setParameter("idRespositorioDocumento", idRespositorioDocumento).executeUpdate();
            em.flush();
        } catch (Exception e) {
            Logger.getLogger(RespositorioDocumentosDAOImplement.class.getName()).log(Level.SEVERE, ErrorUtil.CODE_0101, e);
            return false;
        }
        return true;
    }

    @Override
    public List<RespositorioDocumentos> obtenerByIdProcesoIdTarea(Integer idProceso, Integer idTarea) {
        List<RespositorioDocumentos> resultList = null;
        String query = "";
        query = "  SELECT rd \n"
                + "  FROM TipoDocumentos td \n"
                + "       JOIN FETCH td.respositorioDocumentosList rd \n"
                + " WHERE rd.activo = 1"
                + "   AND rd.idProceso = " + idProceso + " \n"
                + "   AND td.idTarea.idTarea = " + idTarea + " \n";
        try {
            resultList = em.createQuery(query).getResultList();
        } catch (Exception e) {
            Logger.getLogger(PerfilesDAOImplement.class.getName()).log(Level.SEVERE, ErrorUtil.CODE_0101, e);
        }
        if (resultList != null && resultList.size() > 0) {
            System.out.println("resultListA--->" + resultList.size());
            return resultList;
        }
        return null;
    }

    @Override
    public List<RespositorioDocumentos> getByOrden(Integer idDetalle) {
        List<RespositorioDocumentos> resultList = null;
        try {
            resultList = em.createNamedQuery("RespositorioDocumentos.findByIdRespositorioDocumento").setParameter("idRespositorioDocumento", idDetalle).getResultList();
        } catch (Exception e) {
            Logger.getLogger(PerfilesDAOImplement.class.getName()).log(Level.SEVERE, ErrorUtil.CODE_0101, e);
        }
        if (resultList != null && resultList.size() > 0) {
            return resultList;
        }
        return null;
    }

}
