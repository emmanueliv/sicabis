/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.issste.sicabis.ejb.DAO;

import com.issste.sicabis.ejb.DTO.AlertasDTO;
import com.issste.sicabis.ejb.modelo.AlertasOperativasHistorico;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

/**
 *
 * @author 9RZCBG2
 */
@Stateless
public class AlertasOperativasHistoricoDAOImplement implements AlertasOperativasHistoricoDAO {

    @PersistenceContext(unitName = "sicabis")
    private EntityManager em;

    @Override
    public boolean guardar(AlertasOperativasHistorico aoh) {
        try {
            em.persist(aoh);
            em.flush();
            return true;
        } catch (Exception e) {
            Logger.getLogger(AlertasOperativasHistoricoDAOImplement.class.getName()).log(Level.SEVERE, "", e);
            return false;
        }
    }

    @Override
    public List<AlertasOperativasHistorico> getByFechaIngreso(Date fechaIngreso) {
        List<AlertasOperativasHistorico> resultList = null;
        try {
            resultList = em.createQuery("SELECT aoh FROM AlertasOperativasHistorico aoh WHERE aoh.fechaIngreso = :fechaIngreso").setParameter("fechaIngreso", fechaIngreso).getResultList();
        } catch (Exception e) {
            Logger.getLogger(AlertasOperativasHistoricoDAOImplement.class.getName()).log(Level.SEVERE, "", e);
        }
        if (resultList != null && resultList.size() > 0) {
            return resultList;
        }
        return null;
    }

    @Override
    public List<AlertasDTO> getAll() {
        List<AlertasDTO> resultList = null;
        Query query2;
        List<Object[]> objectList = null;
        AlertasDTO alerta = new AlertasDTO();
        try {
            String query = (" SELECT Distinct(cla_delegacion),nombre_delegacion ,COUNT(clave_uml) as unidad,COUNT(clave) as clave, \n"
                    + "              SUM(piezas_dpn) as piezaDpn, Sum(ordinarios_confirmados) ,SUM(extraordinarios_confirmado) \n"
                    + "              ,((SUM(ordinarios_confirmados)/Sum(piezas_dpn)) * 100) as porcentajeOrdinario \n"
                    + "              ,((SUM(extraordinarios_confirmado)/SUM(piezas_dpn)) * 100) as porcentajeExtraodinario \n"
                    + "              ,(100.0 - ((SUM(ordinarios_confirmados)/SUM(piezas_dpn)) * 100)) as disponible  \n"
                    + "         FROM alertas_operativas_historico\n"
                    + "     GROUP BY cla_delegacion, nombre_delegacion");
            query2 = em.createNativeQuery(query);
            System.out.println("query---->" + query);
            objectList = query2.getResultList();
            resultList = new ArrayList();
            for (Object[] result : objectList) {
                alerta = new AlertasDTO();
                alerta.setEstado(String.valueOf(result[1]));
                alerta.setUmu(Integer.parseInt(String.valueOf(result[2])));
                alerta.setClaves(Integer.parseInt(String.valueOf(result[3])));
                alerta.setPiezas(Integer.parseInt(String.valueOf(result[4])));
                alerta.setOrdinarios(Integer.parseInt(String.valueOf(result[5])));
                alerta.setExtraodinarios(Integer.parseInt(String.valueOf(result[6])));
                alerta.setPorcentajeOrdinario((BigDecimal) result[7]);
                alerta.setPorcentajeExtraordinario((BigDecimal) result[8]);
                alerta.setDisponible((BigDecimal) result[9]);
                resultList.add(alerta);
            }

        } catch (Exception e) {
            Logger.getLogger(AlertasOperativasHistoricoDAOImplement.class.getName()).log(Level.SEVERE, "", e);
        }
        if (resultList != null && resultList.size() > 0) {
            return resultList;
        }
        return null;
    }
}
