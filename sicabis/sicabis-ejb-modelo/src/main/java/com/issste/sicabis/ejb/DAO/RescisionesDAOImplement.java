/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.issste.sicabis.ejb.DAO;

import com.issste.sicabis.ejb.modelo.Cancelaciones;
import com.issste.sicabis.ejb.modelo.Rescisiones;
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
public class RescisionesDAOImplement implements RescisionesDAO {

    @PersistenceContext(unitName = "sicabis")
    private EntityManager em;

    @Override
    public Integer guardar(Rescisiones r) {
        try {
            em.persist(r);
            return r.getIdRescision();
        } catch (Exception e) {
            Logger.getLogger(FabricanteDAOImplement.class.getName()).log(Level.SEVERE, ErrorUtil.CODE_0101, e);
            return null;
        }
    }

    @Override
    public boolean actualizar(Rescisiones r) {
        try {
            em.merge(r);
            return true;
        } catch (Exception e) {
            Logger.getLogger(FabricanteDAOImplement.class.getName()).log(Level.SEVERE, ErrorUtil.CODE_0101, e);
            return false;
        }
    }

    @Override
    public List<Rescisiones> rescisiones(Integer detalle) {
        List<Rescisiones> resultList = null;
        try {
            resultList = em.createQuery("Select r From Rescisiones r "
                    + "Where r.idDetalleOrdenSuministro.idDetalleOrdenSuministro = " + detalle + " ").getResultList();
        } catch (Exception e) {
            Logger.getLogger(RescisionesDAOImplement.class.getName()).log(Level.SEVERE, ErrorUtil.CODE_0101, e);
        }
        if (resultList != null && resultList.size() > 0) {
            return resultList;
        }
        System.out.println("vacio");
        return null;
    }

    @Override
    public List<Rescisiones> rescisionesConsulta(String criterio, Integer busqueda) {
        List<Rescisiones> Insumo = null;
        Date hoy = new Date();
        System.out.println("hoy" + hoy);
        try {
            if (busqueda == 1) {

                Query i = em.createQuery("Select r From Rescisiones r "
                        + "Join r.idDetalleOrdenSuministro dos "
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

                Query i = em.createQuery("Select r From Rescisiones r "
                        + "Join r.idDetalleOrdenSuministro dos "
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

                Query i = em.createQuery("Select r From Rescisiones r "
                        + "Join r.idDetalleOrdenSuministro dos  "
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
                Query i = em.createQuery("Select r From Rescisiones r ");
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
    public List<Rescisiones> rescisionesByOrden(String criterio) {
        List<Rescisiones> Insumo = null;

        try {
            Query i = em.createQuery("Select r From Rescisiones r "
                    + "Join r.idDetalleOrdenSuministro dos "
                    + "Join dos.idOrdenSuministro os "
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
    public List<Rescisiones> rescisionesByClave(Integer c) {
        List<Rescisiones> Insumo = null;

        try {

            Query i = em.createQuery("Select r From Rescisiones r "
                    + "Where r.idRescision = " + c + " ");

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
