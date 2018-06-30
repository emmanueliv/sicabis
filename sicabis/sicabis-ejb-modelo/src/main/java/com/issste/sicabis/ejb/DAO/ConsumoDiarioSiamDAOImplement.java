package com.issste.sicabis.ejb.DAO;

import com.issste.sicabis.ejb.modelo.ConsumoDiarioSiam;
import com.issste.sicabis.ejb.utils.ErrorUtil;
import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

@Stateless
public class ConsumoDiarioSiamDAOImplement implements ConsumoDiarioSiamDAO {

    @PersistenceContext(unitName = "sicabis")
    private EntityManager em;

    @Override
    public boolean guarda(ConsumoDiarioSiam cds) {
        try {
            em.persist(cds);
            return true;
        } catch (Exception e) {
            Logger.getLogger(ConsumoDiarioSiamDAOImplement.class.getName()).log(Level.SEVERE, ErrorUtil.CODE_0101, e);
            return false;
        }
    }

    @Override
    public boolean eliminaTodo() {
        try {
            em.createQuery("DELETE FROM ConsumoDiarioSiam cds").executeUpdate();
            return true;
        } catch (Exception e) {
            Logger.getLogger(ConsumoDiarioSiamDAOImplement.class.getName()).log(Level.SEVERE, "", e);
            return false;
        }
    }

    @Override
    public Integer sumaConsumo(String claveInsumo, String claveUnidad, Date fechaInicio, Date fechaFin) {
        Integer sumaConsumo = 0;
        System.out.println("fechaInicio-->" + fechaInicio);
        System.out.println("fechaFin-->" + fechaFin);
        String sQuery = "";
        SimpleDateFormat formatoDeFecha = new SimpleDateFormat("yyyy/MM/dd");
        try {
            sQuery = "SELECT NVL(SUM(cds.consumo),0) \n"
                    + " FROM consumo_diario_siam cds \n"
                    + "WHERE cds.clave_insumo ='" + claveInsumo + "' \n"
                    + "  AND cds.clave_unidad = '" + claveUnidad + "' \n"
                    + "  AND cds.fecha between '" + formatoDeFecha.format(fechaInicio) + "' AND '" + formatoDeFecha.format(fechaFin) + "' ";
            System.out.println("s-->" + sQuery);
            Query q = em.createNativeQuery(sQuery);
            BigDecimal sum = (BigDecimal) q.getSingleResult();
            System.out.println("sum-->" + sum);
            sumaConsumo = sum.intValue();
            System.out.println("sumaConsumo-->" + sumaConsumo);
        } catch (Exception e) {
            Logger.getLogger(ConsumoDiarioSiamDAOImplement.class.getName()).log(Level.SEVERE, "", e);
        }
        return sumaConsumo;
    }

    @Override
    public boolean eliminaFecha(Date fecha, Date fechaFin) {
        try {
            em.createQuery("DELETE FROM ConsumoDiarioSiam cds WHERE cds.fecha >= :fecha AND cds.fecha <= :fechaFin")
                    .setParameter("fecha", fecha)
                    .setParameter("fechaFin", fechaFin)
                    .executeUpdate();
            return true;
        } catch (Exception e) {
            Logger.getLogger(ConsumoDiarioSiamDAOImplement.class.getName()).log(Level.SEVERE, "", e);
            return false;
        }
    }

    @Override
    public ResultSet ejecutaQuery(String query) {
        Connection cnn = em.unwrap(java.sql.Connection.class);
        Statement st;
        ResultSet rs;
        try {
            st = cnn.createStatement();
            rs = st.executeQuery(query);
            return rs;
        } catch (SQLException ex) {
            Logger.getLogger(ConsumoDiarioSiamDAOImplement.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    @Override
    public int ejecutaUpdate(String query) {
        Connection cnn = em.unwrap(java.sql.Connection.class);
        Statement st;
        int valor = -1;
        try {
            st = cnn.createStatement();
            valor = st.executeUpdate(query);
            return valor;
        } catch (SQLException ex) {
            Logger.getLogger(ConsumoDiarioSiamDAOImplement.class.getName()).log(Level.SEVERE, null, ex);
        }
        return valor;
    }

    @Override
    public List<ConsumoDiarioSiam> getByFechas(String fechaIni, String fechaFin) {
        List<ConsumoDiarioSiam> resultList = null;
        SimpleDateFormat formatoDelTexto = new SimpleDateFormat("yyyyMMdd");
        Date fini = null;
        Date ffin = null;
        try {
            fini = formatoDelTexto.parse(fechaIni);
            ffin = formatoDelTexto.parse(fechaFin);
            Query q = em.createQuery("SELECT cds FROM ConsumoDiarioSiam cds WHERE cds.fecha BETWEEN :fechaIni AND :fechaFin ");
            q.setParameter("fechaIni", fini);
            q.setParameter("fechaFin", ffin);
            resultList = q.getResultList();
        } catch (Exception e) {
            Logger.getLogger(ConsumoDiarioSiamDAOImplement.class.getName()).log(Level.SEVERE, ErrorUtil.CODE_0101, e);
        }
        if (resultList != null && resultList.size() > 0) {
            return resultList;
        }
        return null;
    }

}
