package com.issste.sicabis.ejb.DAO;

import com.issste.sicabis.ejb.modelo.Cancelaciones;
import com.issste.sicabis.ejb.utils.ErrorUtil;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.TemporalType;

/**
 *
 * @author fabianvr
 */
@Stateless
public class CancelacionesDAOImplement implements CancelacionesDAO {

    @PersistenceContext(unitName = "sicabis")
    private EntityManager em;

    @Override
    public Integer guardar(Cancelaciones c) {
        try {
            em.persist(c);
            return c.getIdCancelacion();
        } catch (Exception e) {
            Logger.getLogger(FabricanteDAOImplement.class.getName()).log(Level.SEVERE, ErrorUtil.CODE_0101, e);
            return null;
        }
    }

    @Override
    public boolean actualizar(Cancelaciones c) {
        try {
            em.merge(c);
            return true;
        } catch (Exception e) {
            Logger.getLogger(FabricanteDAOImplement.class.getName()).log(Level.SEVERE, ErrorUtil.CODE_0101, e);
            return false;
        }
    }

    @Override
    public List<Cancelaciones> cancelacion(Integer detalle) {
        List<Cancelaciones> resultList = null;
        try {
            resultList = em.createQuery("Select c From Cancelaciones c "
                    + "Where c.idDetalleOrdenSuministro.idDetalleOrdenSuministro = " + detalle + " order by c.idDetalleOrdenSuministro.idDetalleOrdenSuministro desc ").getResultList();
        } catch (Exception e) {
            Logger.getLogger(CancelacionesDAOImplement.class.getName()).log(Level.SEVERE, ErrorUtil.CODE_0101, e);
        }
        if (resultList != null && resultList.size() > 0) {
            return resultList;
        }
        System.out.println("vacio");
        return null;
    }

    @Override
    public List<Cancelaciones> cancelacionesByConsulta(String criterio, Integer busqueda) {
        List<Cancelaciones> Insumo = null;
        Date hoy = new Date();
        System.out.println("hoy" + hoy);
        try {
            if (busqueda == 1) {

                Query i = em.createQuery("Select c From Cancelaciones c "
                        + "Join c.idDetalleOrdenSuministro dos "
                        + "Join dos.idFalloProcedimientoRcb fpr "
                        + "Join fpr.idProcedimientoRcb pr "
                        + "Join pr.idRcbInsumos ri "
                        + "Join ri.idInsumo i "
                        + "Where i.clave = '" + criterio + "' "
                        + "And :time > dos.fechaEntregaFinal ");
                i.setParameter("time", hoy, TemporalType.DATE);
                System.out.println("em" + i);
                Insumo = i.getResultList();

            } else if (busqueda == 2) {

                Query i = em.createQuery("Select c From Cancelaciones c "
                        + "Join c.idDetalleOrdenSuministro dos "
                        + "Join dos.idOrdenSuministro os "
                        + "Join dos.idFalloProcedimientoRcb fpr "
                        + "Join fpr.idProcedimientoRcb pr "
                        + "Join pr.idRcbInsumos ri "
                        + "Join ri.idInsumo i "
                        + "Where os.numeroOrden = '" + criterio + "' "
                        + "And :time > dos.fechaEntregaFinal ");
                i.setParameter("time", hoy, TemporalType.DATE);
                System.out.println("em" + i);
                Insumo = i.getResultList();

            } else if (busqueda == 3) {

                Query i = em.createQuery("Select c From Cancelaciones c "
                        + "Join c.idDetalleOrdenSuministro dos  "
                        + "Join dos.idFalloProcedimientoRcb fpr "
                        + "Join fpr.idProveedor p"
                        + "Join fpr.idProcedimientoRcb pr "
                        + "Join pr.idRcbInsumos ri "
                        + "Join ri.idInsumo i "
                        + "Where p.nombreProveedor = '" + criterio + "' "
                        + "And :time > dos.fechaEntregaFinal ");
                i.setParameter("time", hoy, TemporalType.DATE);
                System.out.println("em" + i);
                Insumo = i.getResultList();

            } else {
                Query i = em.createQuery("Select c From Cancelaciones c ");
                System.out.println("em" + i);
                Insumo = i.getResultList();
            }
        } catch (Exception e) {
            Logger.getLogger(PerfilesDAOImplement.class.getName()).log(Level.SEVERE, ErrorUtil.CODE_0101, e);
        }
        if (Insumo != null && Insumo.size() > 0) {
            return Insumo;
        }
        System.out.println("vacio");
        return null;
    }

    @Override
    public List<Cancelaciones> consulta(Integer can) {
        List<Cancelaciones> resultList = null;
        try {
            resultList = em.createQuery("Select c From Cancelaciones c "
                    + "Where c.idCancelacion = " + can + " ").getResultList();
        } catch (Exception e) {
            Logger.getLogger(CancelacionesDAOImplement.class.getName()).log(Level.SEVERE, ErrorUtil.CODE_0101, e);
        }
        if (resultList != null && resultList.size() > 0) {
            return resultList;
        }
        System.out.println("vacio");
        return null;
    }

    @Override
    public List<Cancelaciones> cancelacionesByOrden(String criterio) {
        List<Cancelaciones> Insumo = null;
        
        try {
            Query i = em.createQuery("Select c From Cancelaciones c "
                    + "Join c.idDetalleOrdenSuministro dos "
                    + "Join dos.idOrdenSuministro os "
                    + "Join os.idContrato con "
                    + "Join dos.idFalloProcedimientoRcb fpr "
                    + "Join fpr.idProcedimientoRcb pr "
                    + "Join pr.idRcbInsumos ri "
                    + "Join ri.idInsumo i "
                    + "Where os.numeroOrden = '" + criterio + "' ");
        
            System.out.println("em" + i);
            Insumo = i.getResultList();
        } catch (Exception e) {
            Logger.getLogger(PerfilesDAOImplement.class.getName()).log(Level.SEVERE, ErrorUtil.CODE_0101, e);
        }
        if (Insumo != null && Insumo.size() > 0) {
            return Insumo;
        }
        System.out.println("vacio");
        return null;
    }

    @Override
    public List<Cancelaciones> cancelacionesByClave(Integer detalle) {
        List<Cancelaciones> Insumo = null;
        System.out.println("detallee--->" + detalle);
        try {
            Query i = em.createQuery("Select c From Cancelaciones c "
                    + "Where c.idCancelacion = " + detalle + " ");

            System.out.println("em" + i);
            Insumo = i.getResultList();

        } catch (Exception e) {
            Logger.getLogger(PerfilesDAOImplement.class.getName()).log(Level.SEVERE, ErrorUtil.CODE_0101, e);
        }
        if (Insumo != null && Insumo.size() > 0) {
            return Insumo;
        }
        System.out.println("vacio");
        return null;
    }
}
