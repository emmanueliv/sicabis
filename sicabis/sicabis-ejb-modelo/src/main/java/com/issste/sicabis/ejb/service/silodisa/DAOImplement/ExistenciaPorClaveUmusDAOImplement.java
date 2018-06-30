/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.issste.sicabis.ejb.service.silodisa.DAOImplement;

import com.issste.sicabis.ejb.DTO.ExistenciaClaveUmuDTO;
import com.issste.sicabis.ejb.ln.ConsumoDiarioSiamService;
import com.issste.sicabis.ejb.ln.DpnInsumosService;
import com.issste.sicabis.ejb.ln.InsumosService;
import com.issste.sicabis.ejb.modelo.DpnInsumos;
import com.issste.sicabis.ejb.service.silodisa.DAO.ExistenciaPorClaveUmusDAO;
import com.issste.sicabis.ejb.service.silodisa.modelo.ExistenciaPorClaveUmus;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

/**
 *
 * @author 9RZCBG2
 */
@Stateless
public class ExistenciaPorClaveUmusDAOImplement implements ExistenciaPorClaveUmusDAO {

    @EJB
    private ConsumoDiarioSiamService consumoDiarioSiamService;

    @EJB
    private DpnInsumosService dpnInsumosService;

    @EJB
    private InsumosService insumosService;

    @PersistenceContext(unitName = "sicabis")
    private EntityManager em;

    @Override
    public boolean guardar(ExistenciaPorClaveUmus epcu) {
        try {
            em.persist(epcu);
            em.flush();
            return true;
        } catch (Exception e) {
            Logger.getLogger(ExistenciaPorClaveUmusDAOImplement.class.getName()).log(Level.SEVERE, "", e);
            return false;
        }
    }

    @Override
    public boolean actualiar(ExistenciaPorClaveUmus epcu) {
        try {
            em.merge(epcu);
            em.flush();
            return true;
        } catch (Exception e) {
            Logger.getLogger(ExistenciaPorClaveUmusDAOImplement.class.getName()).log(Level.SEVERE, "", e);
            return false;
        }
    }

    @Override
    public List<ExistenciaPorClaveUmus> existenciaPorClaveUmusByUmu(String umu) {
        List<ExistenciaPorClaveUmus> resultList = null;
        try {
            resultList = em.createQuery("Select epcu From ExistenciaPorClaveUmus epcu "
                    + "Where epcu.umu = '" + umu + "' ").getResultList();
        } catch (Exception e) {
            Logger.getLogger(ExistenciaPorClaveUmusDAOImplement.class.getName()).log(Level.SEVERE, "", e);
        }
        if (resultList != null && resultList.size() > 0) {
            return resultList;
        }
        System.out.println("vacio");
        return null;
    }

    @Override
    public Integer existenciaPorClaveUmusByUmuAndClave(String umu, String clave) {
        Object suma = 0;
        try {
            System.out.println("entre a consulta");
            suma = em.createNativeQuery("select  NVL(SUM(cast(epcu.existencia as Integer)),0) from existencia_por_clave_umus epcu\n"
                    + "where epcu.clave = '" + clave + "' and epcu.umu = '" + umu + "'").getSingleResult();
        } catch (Exception e) {
            Logger.getLogger(ExistenciaPorClaveUmusDAOImplement.class.getName()).log(Level.SEVERE, "", e);
        }
        return Integer.parseInt(String.valueOf(suma));
    }

    @Override
    public boolean eliminarExistenciasUmus() {
        try {
            Query q1 = em.createNativeQuery("DELETE FROM existencia_por_clave_umus");
            q1.executeUpdate();
            return true;
        } catch (Exception e) {
            Logger.getLogger(ExistenciaPorClaveUmusDAOImplement.class.getName()).log(Level.SEVERE, "", e);
            return false;
        }
    }

    @Override
    public List<DpnInsumos> getByUMUClaveFecha(String clave, String claveUmu, Date fecha) {
        DpnInsumos existSilodisa = null;
        List<DpnInsumos> listaExistSilodisa = null;
        String sQuery = "";
        try {
            sQuery = "SELECT i.clave, i.descripcion, cum.clave_presupuestal, \n"
                    + "      epcu.desc_umu, NVL(i.precio_unitario,0), \n"
                    + "      SUM(CAST (epcu.existencia as integer)) existencias \n"
                    + " FROM insumos i left JOIN existencia_por_clave_umus epcu \n"
                    + "      ON (i.clave = epcu.clave) \n"
                    + "      JOIN cat_unidad_medica cum ON (cum.umu = epcu.umu) \n"
                    + "      JOIN dpn_insumo di ON (i.clave = di.clave_insumo) \n"
                    + "where i.clave = '"+clave+"' \n";

            if (!clave.equals("")) {
                sQuery = sQuery + "AND i.clave = '" + clave + "' \n";
            }
            if (!claveUmu.equals("-1")) {
                sQuery = sQuery + "AND cum.clave_presupuestal = '" + claveUmu + "' \n";
            }
            sQuery = sQuery + "GROUP BY 1,2,3,4,5 \n"
                    + "ORDER BY 1,3";
            System.out.println("query-->" + sQuery);
            Query q = em.createNativeQuery(sQuery);
            List<Object[]> results = q.getResultList();
            listaExistSilodisa = new ArrayList();
            for (Object[] result : results) {
                existSilodisa = new DpnInsumos();
                existSilodisa.setClaveInsumo(String.valueOf(result[0]));
                existSilodisa.setDescripcionInsumo(String.valueOf(result[1]));
                existSilodisa.setClaveUnidad(String.valueOf(result[2]));
                existSilodisa.setNombreUnidad(String.valueOf(result[3]));
                existSilodisa.setPrecioUnitario((BigDecimal) result[4]);
                existSilodisa.setExistenciasCenadi(Integer.parseInt(String.valueOf(result[5])));
                listaExistSilodisa.add(existSilodisa);
            }
        } catch (Exception e) {
            Logger.getLogger(ExistenciaPorClaveUmusDAOImplement.class.getName()).log(Level.SEVERE, "", e);
        }
        if (listaExistSilodisa != null && listaExistSilodisa.size() > 0) {
            return listaExistSilodisa;
        }
        return null;
    }

    @Override
    public List<ExistenciaClaveUmuDTO> getAll() {
        List<ExistenciaClaveUmuDTO> resultList = null;

        Query query2;
        List<Object[]> objectList = null;
        String query = "";
        try {
            query = "SELECT  t1.fecha_inventario, \n"
                    + "      t1.nombre_delegacion, t0.numero_umu, t0.nombre_umu, t1.clave, \n"
                    + "      t1.nombre, t1.existencia, t0.piezas_dpn \n"
                    + " FROM existencia_por_clave_umus t1, alertas_operativas t0\n"
                    + "WHERE t1.umu = t0.numero_umu and t1.cla_delegacion = t0.cla_delegacion\n"
                    + "  AND t1.clave = t0.clave and t1.umu = t0.numero_umu\n"
                    + "ORDER BY t1.fecha_inventario, \n"
                    + "      t1.nombre_delegacion, t0.numero_umu, t0.nombre_umu, t1.clave, \n"
                    + "      t1.nombre, t1.existencia, t0.piezas_dpn";

            query2 = em.createNativeQuery(query);
            System.out.println("query---->" + query);
            objectList = query2.getResultList();
            resultList = new ArrayList();
            for (Object[] result : objectList) {
                ExistenciaClaveUmuDTO ecud = new ExistenciaClaveUmuDTO();
                System.out.println("(Date) result[1]" + (Date) result[0]);
                if ((Date) result[0] != null) {
                    ecud.setFechaInventario((Date) result[0]);
                }
                if (!String.valueOf(result[1]).equals(" ")) {
                    ecud.setDelegacion(String.valueOf(result[1]));
                }
                if (!String.valueOf(result[2]).equals(" ")) {
                    ecud.setNumeroUmu(String.valueOf(result[2]));
                }
                if (!String.valueOf(result[3]).equals(" ")) {
                    ecud.setNombreUmu(String.valueOf(result[3]));
                }
                if (!String.valueOf(result[4]).equals(" ")) {
                    ecud.setClave(String.valueOf(result[4]));
                }
                if (!String.valueOf(result[5]).equals(" ")) {
                    ecud.setDescripcion(String.valueOf(result[5]));
                }
                if (!String.valueOf(result[6]).equals(" ")) {
                    ecud.setExistencia(Integer.parseInt(String.valueOf(result[6])));
                }
                if (!String.valueOf(result[7]).equals(" ") && !String.valueOf(result[7]).equals("null")) {
                    ecud.setDpn(Integer.parseInt(String.valueOf(result[7])));
                }
                resultList.add(ecud);
            }
        } catch (Exception e) {
            Logger.getLogger(ExistenciaPorClaveUmusDAOImplement.class.getName()).log(Level.SEVERE, "", e);
        }
        if (resultList != null && resultList.size() > 0) {
            return resultList;
        }
        return null;
    }

}
