/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.issste.sicabis.ejb.DAO;

import com.issste.sicabis.ejb.modelo.Fabricante;
import com.issste.sicabis.ejb.modelo.Insumos;
import com.issste.sicabis.ejb.modelo.Proveedores;
import com.issste.sicabis.ejb.modelo.Rcb;
import com.issste.sicabis.ejb.modelo.RcbInsumos;
import com.issste.sicabis.ejb.utils.ErrorUtil;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.NonUniqueResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

/**
 *
 * @author erik
 */
@Stateless
public class RcbInsumosDAOImplement implements RcbInsumosDAO {

    @PersistenceContext(unitName = "sicabis")
    private EntityManager em;

    @Override
    public List<RcbInsumos> getListRCBInsumosByidRCB(Integer idRcb) {
        List<RcbInsumos> resultList = em.createQuery("SELECT r FROM RcbInsumos r WHERE r.idRcb.idRcb =:idRcb").setParameter("idRcb", idRcb).getResultList();
        return resultList;
    }

    @Override
    public RcbInsumos getRcbInsumoById(Integer idRcbInsumos) {
        return (RcbInsumos) em.createNamedQuery("RcbInsumos.findByIdRcbInsumos").setParameter("idRcbInsumos", idRcbInsumos).getSingleResult();
    }

    @Override
    public List<Proveedores> getProveedores() {
        return (List<Proveedores>) em.createNamedQuery("Proveedores.findAll").getResultList();
    }

    @Override
    public List<Fabricante> getFabricantes() {
        return (List<Fabricante>) em.createNamedQuery("Fabricante.findAll").getResultList();
    }

//    @Override
//    public List<RcbInsumos> getListClavesByNumeroTipoCompra(String numeroRcb, int tipoCompra) {
//        List<RcbInsumos> resultList = null;
//        String query = "";
//        query = "  SELECT ri \n"
//                + "   FROM Rcb r \n"
//                + "        JOIN FETCH r.rcbInsumosList ri \n"
//                + "        LEFT OUTER JOIN FETCH ri.procedimientoRcbList pr \n"
//                + "  WHERE r.idTipoCompra.idTipoCompra = "+tipoCompra+" \n"
//                + "   AND r.idEstatus.idEstatus = 15 \n"
//                + "    AND r.numero = '"+numeroRcb+"' \n"
//                + "    AND pr.idRcbInsumos.idRcbInsumos = null";
//        try {
//            System.out.println("llegue 1---> \n "+query);
//            resultList = em.createQuery(query).getResultList();
//        } catch (Exception e) {
//            System.out.println("error 1---->"+e);
//            Logger.getLogger(PerfilesDAOImplement.class.getName()).log(Level.SEVERE, ErrorUtil.CODE_0101, e);
//        }
//        if (resultList != null && resultList.size() > 0) {
//            System.out.println("resultList--->"+resultList.size());
//            return resultList;
//        }
//        return null;
//    }
    @Override
    public List<RcbInsumos> getListClavesByNumeroTipoCompra(String numeroRcb, int tipoCompra) {
        List<Object[]> objectList = null;
        List<RcbInsumos> listaRcbInsumos = null;
        RcbInsumos rcbInsumos = null;
        Query query;
        String sQuery = "";
        sQuery = "  SELECT r.id_rcb, ri.id_insumo, ri.clave_insumo, ri.descripcion_insumo, ri.cantidad_piezas, \n"
                + "       ri.precio_unitario, ri.importe, ri.id_rcb_insumos \n"
                + "  FROM rcb r \n"
                + "       JOIN rcb_insumos ri on (r.id_rcb = ri.id_rcb) \n"
                + "       LEFT OUTER JOIN procedimiento_rcb pr\n"
                + "        ON (ri.id_rcb_insumos = pr.id_rcb_insumos) \n"
                //+ "       JOIN insumos i on (i.id_insumo = ri.id_insumo) \n"
                + " WHERE r.activo = 1 \n"
                + "   AND r.id_tipo_compra = " + tipoCompra + " \n"
                + "   AND r.id_estatus = 16 \n"
                + "   AND r.numero = '" + numeroRcb + "' \n"
                + "   AND pr.id_rcb_insumos is null";
        try {
            query = em.createNativeQuery(sQuery);
            objectList = query.getResultList();
            listaRcbInsumos = new ArrayList();
            for (Object[] result : objectList) {
                rcbInsumos = new RcbInsumos();
                Rcb rcb = new Rcb(Integer.parseInt(String.valueOf(result[0])));
                rcb.setNumero(numeroRcb);
                rcbInsumos.setIdRcb(rcb);
                Insumos insumos = new Insumos(Integer.parseInt(String.valueOf(result[1])));
//                insumos.setClave(String.valueOf(result[2]));
//                insumos.setDescripcion(String.valueOf(result[3]));
                rcbInsumos.setIdInsumo(insumos);
                rcbInsumos.setClaveInsumo(String.valueOf(result[2]));
                rcbInsumos.setDescripcionInsumo(String.valueOf(result[3]));
                rcbInsumos.setCantidadPiezas(Integer.parseInt(String.valueOf(result[4])));
                rcbInsumos.setPrecioUnitario(new BigDecimal(String.valueOf(result[5])));
                rcbInsumos.setImporte(new BigDecimal(String.valueOf(result[6])));
                rcbInsumos.setIdRcbInsumos(Integer.parseInt(String.valueOf(result[7])));
                listaRcbInsumos.add(rcbInsumos);
            }
        } catch (Exception e) {
            Logger.getLogger(RcbInsumosDAOImplement.class.getName()).log(Level.SEVERE, ErrorUtil.CODE_0101, e);
        }
        //System.out.println("");
        if (listaRcbInsumos != null && listaRcbInsumos.size() > 0) {
            return listaRcbInsumos;
        }
        return null;
    }

    @Override
    public List<RcbInsumos> getListClavesProcByNumeroTipoCompra(String numeroRcb, int tipoCompra) {
        List<RcbInsumos> resultList = null;
        String query = "";
        query = "  SELECT ri \n"
                + "   FROM Rcb r \n"
                + "        JOIN FETCH r.rcbInsumosList ri \n"
                + "        JOIN FETCH ri.procedimientoRcbList pr \n"
                + "        JOIN pr.idProcedimiento p \n"
                + "  WHERE r.idTipoCompra.idTipoCompra = " + tipoCompra + " \n"
                + "   AND r.idEstatus.idEstatus = 16 \n"
                + "    AND r.numero = '" + numeroRcb + "' \n";
        try {
            resultList = em.createQuery(query).getResultList();
        } catch (Exception e) {
            Logger.getLogger(RcbInsumosDAOImplement.class.getName()).log(Level.SEVERE, ErrorUtil.CODE_0101, e);
        }
        if (resultList != null && resultList.size() > 0) {
            return resultList;
        }
        return null;
    }

    @Override
    public Integer deleteRcbInsumosByIdRcb(Integer idRcb) {
        Query query = em.createQuery(
                "DELETE FROM RcbInsumos ri WHERE ri.idRcb.idRcb=:idRcb");
        return query.setParameter("idRcb", idRcb).executeUpdate();
    }

    @Override
    public RcbInsumos guardarRcbInsumo(RcbInsumos rcbInsumo) {
        em.persist(rcbInsumo);
//         em.flush();
        return rcbInsumo;
    }

    @Override
    public RcbInsumos actualizarRcbInsumo(RcbInsumos rcbInsumo) {
        em.merge(rcbInsumo);
        return rcbInsumo;
    }

    @Override
    public Integer deleteRcbInsumos(RcbInsumos rcbInsumo) {
        Query query = em.createQuery(
                "DELETE FROM RcbInsumos ri WHERE ri.idRcbInsumos=:idRcbInsumos");
        return query.setParameter("idRcbInsumos", rcbInsumo.getIdRcbInsumos()).executeUpdate();
    }

    @Override
    public Integer traerMaxRCBPorArea(Integer idArea, Integer idTipoCompra) {

        try {

            return (Integer) em.createQuery("select max(ri.idRcb) FROM Rcb ri where ri.idArea.idArea =:idArea and ri.idEstatus.idEstatus=16 and ri.idTipoCompra.idTipoCompra=:idTipoCompra")
                    .setParameter("idArea", idArea)
                    .setParameter("idTipoCompra", idTipoCompra)
                    .getSingleResult();

        } catch (NoResultException | NonUniqueResultException nre) {
            System.out.println("traerMaxRCBPorArea nre: " + nre.getMessage());

        }
         // Code for handling NonUniqueResultException

        return 0;

    }

    @Override
    public Integer traerMaxRCBPorAreaGrupo(Integer idArea, Integer idTipoCompra, Integer idGrupo) {
          try {
              System.out.println("entro traerMaxRCBPorAreaGrupo");
            return (Integer) em.createQuery("select max(ri.idRcb.idRcb) FROM RcbInsumos ri join ri.idInsumo ins JOIN FETCH ins.asignacionInsumosList asi where  asi.idArea.idArea =:idArea and ins.idGrupo.idGrupo =:idGrupo and ri.idRcb.idEstatus.idEstatus=15 and ri.idRcb.idTipoCompra.idTipoCompra=:idTipoCompra")
                    .setParameter("idArea", idArea)
                    .setParameter("idTipoCompra", idTipoCompra)
                    .setParameter("idGrupo", idGrupo)
                    .getSingleResult();

        } catch (NoResultException | NonUniqueResultException nre) {
            System.out.println("traerMaxRCBPorArea nre: " + nre.getMessage());

        }
         // Code for handling NonUniqueResultException

        return 0;
    }

}
