/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.issste.sicabis.ejb.DAO;

import com.issste.sicabis.ejb.ln.ProveedorService;
import com.issste.sicabis.ejb.modelo.FalloProcedimientoRcb;
import com.issste.sicabis.ejb.modelo.Fallos;
import com.issste.sicabis.ejb.modelo.Proveedores;
import com.issste.sicabis.ejb.utils.ErrorUtil;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

@Stateless
public class FalloProcedimientoRcbDAOImplement implements FalloProcedimientoRcbDAO {

    @EJB
    private ProveedorService proveedorService;

    @PersistenceContext(unitName = "sicabis")
    private EntityManager em;

    @Override
    public List<FalloProcedimientoRcb> obtenerByFalloProcRcb(Integer idProveedor, Integer tipoInsumo, Integer noProcedimiento) {
        Proveedores pro = proveedorService.obtenerByIdProveedor(idProveedor);
//        List<FalloProcedimientoRcb> resultList = null;
//        try {
//            resultList = em.createQuery("SELECT fpr FROM FalloProcedimientoRcb fpr WHERE fpr.activo = 1 AND fpr.idFallo.idEstatus.idEstatus = 42 AND fpr.idProveedor.idProveedor = :idProveedor")
//                    .setParameter("idProveedor", idProveedor).getResultList();
//        } catch (Exception e) {
//            Logger.getLogger(FalloProcedimientoRcbDAOImplement.class.getName()).log(Level.SEVERE, ErrorUtil.CODE_0101, e);
//        }
//        if (resultList != null && resultList.size() > 0) {
//            return resultList;
//        }
//        return null;

        List<Object[]> objectList = null;
        List<FalloProcedimientoRcb> resultList = null;
        FalloProcedimientoRcb fpr = null;
        Query query;
        String sQuery = "  select fpr.id_fallo_procedimiento_rcb, fpr.id_fallo \n"
                + "   from fallos f join fallo_procedimiento_rcb fpr \n"
                + "        on (f.id_fallo = fpr.id_fallo) \n"
                + "        left outer join contrato_fallo_procedimiento_rcb cfpr \n"
                + "        on (fpr.id_fallo_procedimiento_rcb = cfpr.id_fallo_procedimiento_rcb) \n"
                + "        left outer join proveedores pro\n"
                + "        on (pro.id_proveedor = fpr.id_proveedor)"
                + "        left outer join procedimiento_rcb pr\n"
                + "        on(pr.id_procedimiento_rcb = fpr.id_procedimiento_rcb)\n"
                + "        left outer join procedimientos p\n"
                + "        on(p.id_procedimiento = pr.id_procedimiento)\n"
                + "        left outer join rcb_insumos ri "
                + "        on (ri.id_rcb_insumos = pr.id_rcb_insumos)\n"
                + "        left outer join insumos i"
                + "        on (i.id_insumo = ri.id_insumo)\n"
                + "  where fpr.activo = 1 \n"
                + "    and f.activo = 1 \n"
                + "    and pro.nombre_proveedor = '" + pro.getNombreProveedor() + "' \n"
                + "    and f.id_estatus = 42 \n"
                + "    and cfpr.id_contrato_fallo_procedimiento_rcb is null \n"
                + "    and i.id_tipo_insumos = " + tipoInsumo + "\n"
                + "    and p.id_procedimiento = " + noProcedimiento + "";
        try {
            query = em.createNativeQuery(sQuery);
            objectList = query.getResultList();
            resultList = new ArrayList();
            Integer idFalloProcedimientoRcb = 0;
            for (Object[] result : objectList) {
                idFalloProcedimientoRcb = Integer.parseInt(String.valueOf(result[0]));
                fpr = this.obtenerByIdFalloProcedimientoRcb(idFalloProcedimientoRcb);
                if (fpr != null) {
                    resultList.add(fpr);
                }
            }
        } catch (Exception e) {
            Logger.getLogger(FalloProcedimientoRcbDAOImplement.class.getName()).log(Level.SEVERE, ErrorUtil.CODE_0101, e);
        }
        if (resultList != null && resultList.size() > 0) {
            return resultList;
        }
        return null;

    }

    @Override
    public FalloProcedimientoRcb obtenerByIdFalloProcedimientoRcb(Integer idFalloProcedimientoRcb) {
        try {
            return (FalloProcedimientoRcb) em.createNamedQuery("FalloProcedimientoRcb.findByIdFalloProcedimientoRcb").setParameter("idFalloProcedimientoRcb", idFalloProcedimientoRcb).getSingleResult();
        } catch (Exception e) {
            Logger.getLogger(FalloProcedimientoRcbDAOImplement.class.getName()).log(Level.SEVERE, ErrorUtil.CODE_0101, e);
            return null;
        }
    }

    @Override
    public boolean actualizaCantidadConvenio(FalloProcedimientoRcb falloProcedimientoRcb) {
        try {
            em.merge(falloProcedimientoRcb);
            em.getEntityManagerFactory().getCache().evictAll();
            return true;
        } catch (Exception e) {
            Logger.getLogger(FalloProcedimientoRcbDAOImplement.class.getName()).log(Level.SEVERE, ErrorUtil.CODE_0101, e);
            return false;
        }
    }

    @Override
    public FalloProcedimientoRcb unidadPiezaByInsumoOrdenId(Integer falloProcedimientoRcbId) {
        try {
            return (FalloProcedimientoRcb) em.createNamedQuery("FalloProcedimientoRcb.findByUnidadPieza").setParameter("idFalloProcedimientoRcb", falloProcedimientoRcbId).getSingleResult();
        } catch (Exception e) {
            Logger.getLogger(FalloProcedimientoRcbDAOImplement.class.getName()).log(Level.SEVERE, ErrorUtil.CODE_0101, e);
            return null;
        }
    }

    @Override
    public List<FalloProcedimientoRcb> obtenerByIdFalloProcedimientoRcb(String numeroP) {
        List<Object[]> objectList = null;
        List<FalloProcedimientoRcb> listFalloProcedimientoRcb = null;
        Fallos fallo = null;
        FalloProcedimientoRcb falloProcedimientoRcb = null;
        Query query;
        String sQuery = "";
        sQuery = "select UNIQUE f.id_fallo, fpr.id_fallo_procedimiento_rcb,f.numero_fallo from fallo_procedimiento_rcb fpr\n"
                + "join fallos f on f.id_fallo = fpr.id_fallo\n"
                + "join procedimiento_rcb pr on pr.id_procedimiento_rcb = fpr.id_procedimiento_rcb\n"
                + "join procedimientos p on p.id_procedimiento = pr.id_procedimiento\n"
                + "where p.numero_procedimiento ='" + numeroP + "'";
        try {
            query = em.createNativeQuery(sQuery);
            objectList = query.getResultList();
            listFalloProcedimientoRcb = new ArrayList();
            for (Object[] result : objectList) {
                System.out.println("dentro del ciclo");
                falloProcedimientoRcb = new FalloProcedimientoRcb();
                fallo = new Fallos();
                fallo.setIdFallo(Integer.parseInt(String.valueOf(result[0])));
                falloProcedimientoRcb.setIdFalloProcedimientoRcb(Integer.parseInt(String.valueOf(result[1])));
                fallo.setNumeroFallo(String.valueOf(result[2]));
                falloProcedimientoRcb.setIdFallo(fallo);
                listFalloProcedimientoRcb.add(falloProcedimientoRcb);
            }
            return listFalloProcedimientoRcb;
        } catch (Exception e) {
            Logger.getLogger(FalloProcedimientoRcbDAOImplement.class.getName()).log(Level.SEVERE, ErrorUtil.CODE_0101, e);
            return null;
        }

    }

    @Override
    public BigDecimal obtenerUltimoPrecio(String clave) {
        List<Object[]> objectList = null;
        Query query;
        String sQuery = "";
        BigDecimal precio = BigDecimal.ZERO;
        sQuery = "SELECT fprd.precioUnitario, MAX(fprd.idFalloProcedimientoRcb) \n"
                + " FROM FalloProcedimientoRcb fprd \n"
                + " JOIN fprd.idFallo f \n"
                + "WHERE f.idEstatus.idEstatus = 42 \n"
                + "  AND fprd.idProcedimientoRcb.idRcbInsumos.claveInsumo = :cveInsumo \n"
                + "Group by fprd.precioUnitario";
        try {

            query = em.createQuery(sQuery).setParameter("cveInsumo", clave);
            objectList = query.getResultList();
            for (Object[] result : objectList) {
                precio = (BigDecimal) result[0];
                break;
            }
            return precio;
        } catch (Exception e) {
            Logger.getLogger(FalloProcedimientoRcbDAOImplement.class.getName()).log(Level.SEVERE, ErrorUtil.CODE_0101, e);
            return null;
        }
    }

}
