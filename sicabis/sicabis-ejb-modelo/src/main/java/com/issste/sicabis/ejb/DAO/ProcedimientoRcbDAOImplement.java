/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.issste.sicabis.ejb.DAO;

import com.issste.sicabis.ejb.modelo.ProcedimientoRcb;
import com.issste.sicabis.ejb.modelo.Procedimientos;
import com.issste.sicabis.ejb.utils.ErrorUtil;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.LocalBean;
import javax.ejb.Stateful;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

@Stateful
@LocalBean
public class ProcedimientoRcbDAOImplement implements ProcedimientoRcbDAO {

    @PersistenceContext(unitName = "sicabis")
    private EntityManager em;

    @Override
    public boolean borrarByIdProcedimiento(Integer idProcedimiento) {
        try {
            Query query = em.createQuery("DELETE FROM ProcedimientoRcb pr WHERE pr.idProcedimiento.idProcedimiento=:idProcedimiento");
            query.setParameter("idProcedimiento", idProcedimiento).executeUpdate();
            em.flush();
        } catch (Exception e) {
            Logger.getLogger(ProcedimientoRcbDAOImplement.class.getName()).log(Level.SEVERE, ErrorUtil.CODE_0101, e);
            return false;
        }
        return true;
    }

    @Override
    public void guardaProcedimientoRcb(ProcedimientoRcb procedimientoRcb) {
        try {
            em.persist(procedimientoRcb);
            //em.flush();
            em.getEntityManagerFactory().getCache().evictAll();
        } catch (Exception e) {
            Logger.getLogger(ProcedimientoRcbDAOImplement.class.getName()).log(Level.SEVERE, ErrorUtil.CODE_0101, e);
        }
    }

    @Override
    public List<ProcedimientoRcb> obtenerByNumeroProc(String numeroProcedimiento) {
        List<ProcedimientoRcb> resultList = null;
        //String query = "";
        try {
            //query = "  Select pr FROM ProcedimientoRcb pr where pr.idProcedimiento.idProcedimiento = '" + numeroProcedimiento + "' \n";
            resultList = em.createQuery("Select pr FROM ProcedimientoRcb pr where pr.activo = 1 and pr.idProcedimiento.idEstatus.idEstatus = 32 and pr.idProcedimiento.numeroProcedimiento = :numeroProcedimiento ").setParameter("numeroProcedimiento", numeroProcedimiento).getResultList();
        } catch (Exception e) {
            Logger.getLogger(ProcedimientoRcbDAOImplement.class.getName()).log(Level.SEVERE, ErrorUtil.CODE_0101, e);
        }
        if (resultList != null && resultList.size() > 0) {
            return resultList;
        }
        return null;
    }

    @Override
    public boolean actualizaProcedimientoRcb(ProcedimientoRcb procedimientoRcb) {
        try {
            em.merge(procedimientoRcb);
            em.flush();
            em.getEntityManagerFactory().getCache().evictAll();
            return true;
        } catch (Exception e) {
            Logger.getLogger(ProcedimientoRcbDAOImplement.class.getName()).log(Level.SEVERE, ErrorUtil.CODE_0101, e);
            return false;
        }
    }

    @Override
    public List<ProcedimientoRcb> obtenerByIdNumRcbTipoCompra(String numeroRcb, int tipoCompra) {
        List<ProcedimientoRcb> resultList = null;
        String query = "";
        try {
            query = "  Select pr \n"
                    + "  From ProcedimientoRcb pr"
                    + " Where pr.activo = 1 \n"
                    + "   and pr.desierta != 2\n"
                    + "   and pr.desiertaParcial != 2 \n"
                    + "   and pr.idRcbInsumos.idRcb.numero = pr.idRcbInsumos.idRcb.numero \n"
                    + "   and pr.idRcbInsumos.idRcb.idTipoCompra.idTipoCompra in (1,2)";

            resultList = em.createQuery(query).getResultList();
        } catch (Exception e) {
            Logger.getLogger(ProcedimientoRcbDAOImplement.class.getName()).log(Level.SEVERE, ErrorUtil.CODE_0101, e);
            System.out.println("error consulta");
        }
        if (resultList != null && resultList.size() > 0) {
            return resultList;
        }
        return null;
    }

    @Override
    public List<ProcedimientoRcb> obtenerByIdProcedimiento(Integer idProcedimiento) {
        List<ProcedimientoRcb> resultList = null;
        try {
            resultList = em.createQuery("Select pr From ProcedimientoRcb pr Where pr.activo = 1 and pr.idProcedimiento.idProcedimiento = :idProcedimiento").setParameter("idProcedimiento", idProcedimiento).getResultList();
        } catch (Exception e) {
            Logger.getLogger(ProcedimientoRcbDAOImplement.class.getName()).log(Level.SEVERE, ErrorUtil.CODE_0101, e);
        }
        if (resultList != null && resultList.size() > 0) {
            return resultList;
        }
        return null;
    }

    public List<Procedimientos> obtenerByRcb(String numeroRcb) {

        List<Object[]> objectList = null;
        List<Procedimientos> listProcedimientos = null;
        Procedimientos procedimiento = null;
        Query query;
        String sQuery = "";
        sQuery = "Select UNIQUE p.id_procedimiento,p.numero_procedimiento From procedimientos p\n"
                + "join procedimiento_rcb pr on pr.id_procedimiento = p.id_procedimiento \n"
                + "join rcb_insumos ri on ri.id_rcb_insumos = pr.id_rcb_insumos \n"
                + "join rcb r on r.id_rcb = ri.id_rcb \n"
                + "where r.numero = '" + numeroRcb + "'";
        try {
            query = em.createNativeQuery(sQuery);
            objectList = query.getResultList();
            listProcedimientos = new ArrayList();
            for (Object[] result : objectList) {
                procedimiento = new Procedimientos();
                procedimiento.setNumeroProcedimiento(String.valueOf(result[1]));
                procedimiento.setIdProcedimiento(Integer.valueOf(String.valueOf(result[0])));
                listProcedimientos.add(procedimiento);
            }
            return listProcedimientos;
        } catch (Exception e) {
            Logger.getLogger(RcbInsumosDAOImplement.class.getName()).log(Level.SEVERE, ErrorUtil.CODE_0101, e);
            return null;
        }

    }

}
