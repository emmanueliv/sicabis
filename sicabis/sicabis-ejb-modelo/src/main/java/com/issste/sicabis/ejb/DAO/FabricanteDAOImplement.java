package com.issste.sicabis.ejb.DAO;

import com.issste.sicabis.ejb.modelo.Fabricante;
import com.issste.sicabis.ejb.utils.ErrorUtil;
import java.util.ArrayList;
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
public class FabricanteDAOImplement implements FabricanteDAO {

    @PersistenceContext(unitName = "sicabis")
    private EntityManager em;

    @Override
    public List<Fabricante> fabricanteByNumero(Integer numero, Integer nombre) {
        List<Fabricante> resultList = null;
        try {
            if (numero != null) {
                resultList = em.createQuery("Select f From Fabricante f where f.idFabricante = " + numero + "").getResultList();
            } else {
                resultList = em.createQuery("Select f From Fabricante f where f.idFabricante = " + nombre + "").getResultList();
            }
        } catch (Exception e) {
            Logger.getLogger(FabricanteDAOImplement.class.getName()).log(Level.SEVERE, ErrorUtil.CODE_0101, e);
        }
        if (resultList != null && resultList.size() > 0) {
            return resultList;
        }
        return null;
    }

    @Override
    public List<Fabricante> fabricanteList() {
        List<Fabricante> resultList = null;
        try {

            resultList = em.createQuery("Select f From Fabricante f Where f.activo = 1").getResultList();

        } catch (Exception e) {
            Logger.getLogger(FabricanteDAOImplement.class.getName()).log(Level.SEVERE, ErrorUtil.CODE_0101, e);
        }
        if (resultList != null && resultList.size() > 0) {
            return resultList;
        }
        return null;
    }

    @Override
    public Fabricante fabricanteByIdFacbricante(Integer idFabricante) {
        try {
            return (Fabricante) em.createNamedQuery("Fabricante.findByIdFabricante").setParameter("idFabricante", idFabricante).getSingleResult();
            //resultList = em.createQuery("Select f From Fabricante f Where f.idFabricante = " + idFabricante + "").getResultList();
        } catch (Exception e) {
            Logger.getLogger(FabricanteDAOImplement.class.getName()).log(Level.SEVERE, ErrorUtil.CODE_0101, e);
            return null;
        }
    }

    @Override
    public boolean guardarFabricante(Fabricante fabricante) {
        try {
            if (fabricante.getIdFabricante() == null) {
                em.persist(fabricante);
            } else {
                em.merge(fabricante);
            }
            return true;
        } catch (Exception e) {
            Logger.getLogger(FabricanteDAOImplement.class.getName()).log(Level.SEVERE, ErrorUtil.CODE_0101, e);
            return false;
        }
    }

    @Override
    public Fabricante fabricanteByNombre(String nombre) {
        List<Fabricante> resultList = null;
        try {
            resultList = em.createNamedQuery("Fabricante.findByNombre").setParameter("nombre", nombre).getResultList();
        } catch (Exception e) {
            Logger.getLogger(FabricanteDAOImplement.class.getName()).log(Level.SEVERE, ErrorUtil.CODE_0101, e);
            return null;
        }
        if (resultList != null && resultList.size() > 0) {
            return resultList.get(0);
        }
        return null;
    }

    @Override
    public List<Fabricante> fabricanteByIdRemision(Integer idRemision) {
        List<Fabricante> resultList = null;
        Fabricante remiList = new Fabricante();
        String sQuery = "";
        Query query;
        List<Object[]> objectList = null;
        try {

            sQuery = "Select f.id_fabricante, f.nombre From fabricante f "
                    + "Join proveedor_fabricante pf on pf.id_fabricante = f.id_fabricante "
                    + "Join proveedores p on p.id_proveedor  = pf.id_proveedor "
                    + "Join fallo_procedimiento_rcb fpr on fpr.id_proveedor = p.id_proveedor "
                    + "Join contrato_fallo_procedimiento_rcb cfpr on cfpr.id_fallo_procedimiento_rcb = fpr.id_fallo_procedimiento_rcb "
                    + "Join contratos c on c.id_contrato = cfpr.id_contrato "
                    + "Join orden_suministro os on os.id_contrato = c.id_contrato "
                    + "Join detalle_orden_suministro dos on dos.id_orden_suministro = os.id_orden_suministro "
                    + "Join remisiones r on r.id_detalle_orden_suministro = dos.id_detalle_orden_suministro "
                    + "Where r.id_remision = " + idRemision + " "
                    + "Group By f.id_fabricante, f.nombre ";
            query = em.createNativeQuery(sQuery);
            objectList = query.getResultList();
            resultList = new ArrayList();
            for (Object[] result : objectList) {
                remiList = new Fabricante();
                int id = Integer.parseInt(String.valueOf(result[0]));
                String nombre = String.valueOf(result[1]);

                remiList.setIdFabricante(id);
                remiList.setNombre(nombre);
                resultList.add(remiList);
            }
        } catch (Exception e) {
            Logger.getLogger(FabricanteDAOImplement.class.getName()).log(Level.SEVERE, ErrorUtil.CODE_0101, e);
        }
        if (resultList != null && resultList.size() > 0) {
            return resultList;
        }
        return null;
    }

}
