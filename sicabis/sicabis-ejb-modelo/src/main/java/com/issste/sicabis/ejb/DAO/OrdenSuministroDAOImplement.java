package com.issste.sicabis.ejb.DAO;

import com.issste.sicabis.ejb.modelo.OrdenSuministro;
import com.issste.sicabis.ejb.utils.ErrorUtil;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

@Stateless
public class OrdenSuministroDAOImplement implements OrdenSuministroDAO {

    @PersistenceContext(unitName = "sicabis")
    private EntityManager em;

    @Override
    public boolean guardaOrdenSuministro(OrdenSuministro ordenSuministro) {
        try {
            em.persist(ordenSuministro);
            return true;
        } catch (Exception e) {
            Logger.getLogger(OrdenSuministroDAOImplement.class.getName()).log(Level.SEVERE, ErrorUtil.CODE_0101, e);
            return false;
        }
    }

    @Override
    public List<OrdenSuministro> obtenerOrdenesSuministro(OrdenSuministro ordenSuministro, Integer idArea) {
        List<OrdenSuministro> resultList = null;
        String query = "";
        try {
            query = "  Select os \n"
                    + "  From OrdenSuministro os \n"
                    + " Where os.activo = 1 \n";

            if (!ordenSuministro.getIdContrato().getNumeroContrato().equals("")) {
                query = query + "   and os.idContrato.numeroContrato = '" + ordenSuministro.getIdContrato().getNumeroContrato() + "' \n";
            }
            if (!ordenSuministro.getNumeroOrden().equals("")) {
                query = query + "   and os.numeroOrden = '" + ordenSuministro.getNumeroOrden() + "' \n";
            }
            resultList = em.createQuery(query).getResultList();
        } catch (Exception e) {
            Logger.getLogger(OrdenSuministroDAOImplement.class.getName()).log(Level.SEVERE, ErrorUtil.CODE_0101, e);
        }
        if (resultList != null && resultList.size() > 0) {
            return resultList;
        }
        return null;
    }

    @Override
    public List<OrdenSuministro> obtenerOrdenesSuministroByArea(OrdenSuministro ordenSuministro, Integer idArea) {
        List<OrdenSuministro> resultList = null;
        String query = "";
        try {
            query = "select * from orden_suministro os\n"
                    + "join detalle_orden_suministro dos on dos.id_orden_suministro = os.id_orden_suministro\n"
                    + "join fallo_procedimiento_rcb fpr on fpr.id_fallo_procedimiento_rcb = dos.id_fallo_procedimiento_rcb\n"
                    + "join procedimiento_rcb pr on pr.id_procedimiento_rcb = fpr.id_procedimiento_rcb\n"
                    + "join rcb_insumos ri on ri.id_rcb_insumos = pr.id_rcb_insumos\n"
                    + "join rcb r on r.id_rcb = ri.id_rcb\n"
                    + "where os.activo = 1 ";
            if (idArea != null && idArea != 10) {
                query = query + " and r.id_area = " + idArea + " \n";
            }
            if (!ordenSuministro.getIdContrato().getNumeroContrato().equals("")) {
                query = query + " and c.id_contrato = " + ordenSuministro.getIdContrato().getIdContrato() + " \n";
            }
            if (!ordenSuministro.getNumeroOrden().equals("")) {
                query = query + " and os.numero_orden = '" + ordenSuministro.getNumeroOrden() + "' \n";
            }
            resultList = em.createNativeQuery(query,OrdenSuministro.class).getResultList();
        } catch (Exception e) {
            Logger.getLogger(OrdenSuministroDAOImplement.class.getName()).log(Level.SEVERE, ErrorUtil.CODE_0101, e);
        }
        if (resultList != null && resultList.size() > 0) {
            return resultList;
        }
        return null;
    }

    @Override
    public boolean actualizaOrdenSuministro(OrdenSuministro ordenSuministro) {
        try {
            em.merge(ordenSuministro);
            return true;
        } catch (Exception e) {
            Logger.getLogger(OrdenSuministroDAOImplement.class.getName()).log(Level.SEVERE, ErrorUtil.CODE_0101, e);
            return false;
        }
    }

    @Override
    public List<OrdenSuministro> obtenerByNumeroOrden(String numeroOrden) {
        try {
            return em.createQuery("SELECT os FROM OrdenSuministro os WHERE os.activo = 1 and os.numeroOrden = :numeroOrden").setParameter("numeroOrden", numeroOrden).getResultList();
        } catch (Exception e) {
            Logger.getLogger(OrdenSuministroDAOImplement.class.getName()).log(Level.SEVERE, ErrorUtil.CODE_0101, e);
            return null;
        }
    }

    @Override
    public List<OrdenSuministro> obtenerOrdenByNumContrato(Integer idContrato) {
        try {
            return em.createQuery("SELECT os FROM OrdenSuministro os WHERE os.activo = 1 and os.idContrato.idContrato = :idContrato").setParameter("idContrato", idContrato).getResultList();
        } catch (Exception e) {
            Logger.getLogger(OrdenSuministroDAOImplement.class.getName()).log(Level.SEVERE, ErrorUtil.CODE_0101, e);
            return null;
        }
    }

    @Override
    public List<OrdenSuministro> obtenerOrdenByPreOrdenSistema() {
        try {
            return em.createQuery("SELECT os FROM OrdenSuministro os WHERE os.preOrden = 1").getResultList();
        } catch (Exception e) {
            Logger.getLogger(OrdenSuministroDAOImplement.class.getName()).log(Level.SEVERE, ErrorUtil.CODE_0101, e);
            return null;
        }
    }

    @Override
    public Integer obtenerUltimoIdOrden() {
        try {
            return (Integer) em.createQuery("SELECT MAX(os.idOrdenSuministro) FROM OrdenSuministro os WHERE os.activo = 1").getSingleResult();
        } catch (Exception e) {
            Logger.getLogger(OrdenSuministroDAOImplement.class.getName()).log(Level.SEVERE, ErrorUtil.CODE_0101, e);
            return null;
        }
    }

    @Override
    public boolean eliminarPreOrdenesSistema() {
        try {
            em.createNativeQuery("delete detalle_orden_suministro where id_orden_suministro in((select id_orden_suministro from orden_suministro where pre_orden = 1 and id_estatus = 71));\n"
                    + "delete orden_suministro where pre_orden = 1 and id_estatus = 71;").executeUpdate();
            return true;
        } catch (Exception e) {
            Logger.getLogger(OrdenSuministroDAOImplement.class.getName()).log(Level.SEVERE, ErrorUtil.CODE_0101, e);
            return false;
        }
    }

    @Override
    public List<OrdenSuministro> getOrdenByClave(String clave) {
        List<OrdenSuministro> resultList = null;
        try {
            Query q = em.createQuery("SELECT os FROM OrdenSuministro os JOIN FETCH os.detalleOrdenSuministroList dos WHERE (os.idEstatus.idEstatus = 73 or os.idEstatus.idEstatus = 75) and dos.idFalloProcedimientoRcb.idProcedimientoRcb.idRcbInsumos.claveInsumo = :clave");
            q.setParameter("clave", clave);
            resultList = q.getResultList();
        } catch (Exception e) {
            Logger.getLogger(OrdenSuministroDAOImplement.class.getName()).log(Level.SEVERE, ErrorUtil.CODE_0101, e);
        }
        if (resultList != null && resultList.size() > 0) {
            return resultList;
        }
        return null;
    }

}
