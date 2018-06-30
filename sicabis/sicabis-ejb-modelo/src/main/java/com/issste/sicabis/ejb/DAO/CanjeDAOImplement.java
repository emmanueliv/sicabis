package com.issste.sicabis.ejb.DAO;

import com.issste.sicabis.ejb.modelo.CanjePermuta;
import com.issste.sicabis.ejb.utils.ErrorUtil;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author fabianvr
 */
@Stateless
public class CanjeDAOImplement implements CanjeDAO {

    @PersistenceContext(unitName = "sicabis")
    private EntityManager em;

    @Override
    public Integer guardarCanje(CanjePermuta canje) {
        try {
            em.persist(canje);
            em.flush();
            return canje.getIdCanjePermuta();
        } catch (Exception e) {
            Logger.getLogger(RecoleccionDAOImplement.class.getName()).log(Level.SEVERE, ErrorUtil.CODE_0101, e);
            return null;
        }
    }

    @Override
    public Integer folioCanje() {
        List<CanjePermuta> resultList = null;
        Integer maximo = 0;
        try {
            maximo = (Integer) em.createQuery("Select MAX(cp.idCanjePermuta) From CanjePermuta cp").getSingleResult();
            System.out.println("maximo-->" + maximo);
        } catch (Exception e) {
            Logger.getLogger(CanjeDAOImplement.class.getName()).log(Level.SEVERE, ErrorUtil.CODE_0101, e);
        }
        if (maximo != null) {
            return maximo;
        }
        return null;
    }

    @Override
    public BigDecimal precioInsumoCanje(String clave) {
        BigDecimal maximo = new BigDecimal(BigInteger.ZERO);
        try {
            List<BigDecimal> precios = em.createQuery("SELECT fprcb.precioUnitario FROM FalloProcedimientoRcb fprcb WHERE fprcb.idProcedimientoRcb.idRcbInsumos.claveInsumo = :cveInsumo")
                    .setParameter("cveInsumo", clave).getResultList();
            for (BigDecimal precio : precios) {
                System.out.println("Precio" + precio.compareTo(maximo));
                if (precio.compareTo(maximo) == 1) {
                    maximo = precio;
                }
            }
            System.out.println("maximo-->" + maximo);
        } catch (Exception e) {
            Logger.getLogger(RecoleccionDAOImplement.class.getName()).log(Level.SEVERE, ErrorUtil.CODE_0101, e);
        }
        if (maximo != null) {
            return maximo;
        }
        return new BigDecimal(BigInteger.ZERO);
    }

    @Override
    public List<CanjePermuta> canjePermuta() {
        List<CanjePermuta> resultList = null;
        try {
            resultList = em.createQuery("Select cp From CanjePermuta cp where cp.activo = 1").getResultList();
        } catch (Exception e) {
            Logger.getLogger(CanjeDAOImplement.class.getName()).log(Level.SEVERE, ErrorUtil.CODE_0101, e);
        }
        if (resultList != null && resultList.size() > 0) {
            return resultList;
        }
        return null;
    }

    @Override
    public List<CanjePermuta> canjePermutaById(Integer id) {
        List<CanjePermuta> resultList = null;
        try {
            resultList = em.createQuery("Select cp From CanjePermuta cp "
                    + "Where cp.idCanjePermuta = " + id + "").getResultList();
        } catch (Exception e) {
            Logger.getLogger(CanjeDAOImplement.class.getName()).log(Level.SEVERE, ErrorUtil.CODE_0101, e);
        }
        if (resultList != null && resultList.size() > 0) {
            return resultList;
        }
        System.out.println("vacio");
        return null;
    }

}
