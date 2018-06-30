package com.issste.sicabis.ejb.DAO;

import com.issste.sicabis.ejb.DTO.ExistenciaClaveUmuDTO;
import com.issste.sicabis.ejb.DTO.HistorialExisteciaClaveUmuDTO;
import com.issste.sicabis.ejb.modelo.AlertasOperativasHistorico;
import com.issste.sicabis.ejb.modelo.HistoricoExistenciaPorClaveUmus;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

@Stateless
public class HistoricoExistenciaPorClaveUmusDAOImplement implements HistoricoExistenciaPorClaveUmusDAO {

    @PersistenceContext(unitName = "sicabis")
    private EntityManager em;

    @Override
    public boolean guardar(HistoricoExistenciaPorClaveUmus hepcu) {
        try {
            em.persist(hepcu);
            em.flush();
            return true;
        } catch (Exception e) {
            Logger.getLogger(HistoricoExistenciaPorClaveUmusDAOImplement.class.getName()).log(Level.SEVERE, "", e);
            return false;
        }
    }

    @Override
    public List<HistoricoExistenciaPorClaveUmus> getByFechaIngreso(Date fechaIngreso) {
        List<HistoricoExistenciaPorClaveUmus> resultList = null;
        try {
            resultList = em.createQuery("SELECT hepcu FROM HistoricoExistenciaPorClaveUmus hepcu WHERE hepcu.fechaIngreso = :fechaIngreso").setParameter("fechaIngreso", fechaIngreso).getResultList();
        } catch (Exception e) {
            Logger.getLogger(HistoricoExistenciaPorClaveUmus.class.getName()).log(Level.SEVERE, "", e);
        }
        if (resultList != null && resultList.size() > 0) {
            return resultList;
        }
        return null;
    }

    @Override
    public List<HistoricoExistenciaPorClaveUmus> getByFiltros(Date fechaInicio, Date fechaFin, String delegacion, String numeroUmu, String nombreUmu, String clave, String clave2, String tipo) {
        List<HistoricoExistenciaPorClaveUmus> resultList = null;
        try {
            String query = "SELECT hecu FROM HistoricoExistenciaPorClaveUmus hecu WHERE hecu.fechaInventario BETWEEN :fechaInicio AND :fechaFin \n";
            if (!delegacion.equals("-1")) {
                query = query + " AND hecu.nombreDelegacion = '" + delegacion + "'";
            }
            if (!tipo.equals("")) {
                query = query + " AND hecu.tipo LIKE '%" + tipo + "%'";
            }
            if (!numeroUmu.equals("")) {
                query = query + " AND hecu.umu LIKE '%" + numeroUmu + "%'";
            }
            if (!nombreUmu.equals("")) {
                query = query + " AND hecu.descUmu LIKE '%" + nombreUmu + "%'";
            }
            if (!clave.equals("-1")) {
                query = query + " AND hecu.clave = '" + clave + "'";
            }
            if (!clave2.equals("-1")) {
                query = query + " AND hecu.clave = '" + clave2 + "'";
            }
            
            Query q = em.createQuery(query).setParameter("fechaInicio", fechaInicio).setParameter("fechaFin", fechaFin);
            resultList = q.getResultList();

        } catch (Exception e) {
            Logger.getLogger(HistoricoExistenciaPorClaveUmusDAOImplement.class.getName()).log(Level.SEVERE, "", e);
        }
        if (resultList != null && resultList.size() > 0) {
            return resultList;
        }
        return null;
    }

    @Override
    public List<HistorialExisteciaClaveUmuDTO> getAll() {
        List<HistorialExisteciaClaveUmuDTO> resultList = null;

        Query query2;
        List<Object[]> objectList = null;
        String query = "";
        try {
            query = "SELECT  t1.fecha_inventario, \n"
                    + "                     t1.nombre_delegacion, t0.numero_umu, t0.nombre_umu, t1.clave, \n"
                    + "                     t1.nombre, t1.existencia, t0.piezas_dpn \n"
                    + "                     FROM historico_existencia_por_clave_umus t1, alertas_operativas_historico t0\n"
                    + "                     where t1.umu = t0.numero_umu and t1.cla_delegacion = t0.cla_delegacion\n"
                    + "                     and t1.clave = t0.clave and t1.umu = t0.numero_umu\n"
                    + "                     order by t1.fecha_inventario, \n"
                    + "                     t1.nombre_delegacion, t0.numero_umu, t0.nombre_umu, t1.clave, \n"
                    + "                     t1.nombre, t1.existencia, t0.piezas_dpn ";

            query2 = em.createNativeQuery(query);
            System.out.println("query---->" + query);
            objectList = query2.getResultList();
            resultList = new ArrayList();
            for (Object[] result : objectList) {
                HistorialExisteciaClaveUmuDTO ecud = new HistorialExisteciaClaveUmuDTO();
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
            Logger.getLogger(HistoricoExistenciaPorClaveUmusDAOImplement.class.getName()).log(Level.SEVERE, "", e);
        }
        if (resultList != null && resultList.size() > 0) {
            return resultList;
        }
        return null;
    }

    @Override
    public List<HistoricoExistenciaPorClaveUmus> getAllDetalle() {
        List<HistoricoExistenciaPorClaveUmus> resultList = null;
        try {
            resultList = em.createQuery("Select hecu From HistoricoExistenciaPorClaveUmus hecu").getResultList();

        } catch (Exception e) {
            Logger.getLogger(HistoricoExistenciaPorClaveUmusDAOImplement.class.getName()).log(Level.SEVERE, "", e);
        }
        if (resultList != null && resultList.size() > 0) {
            return resultList;
        }
        return null;
    }

    @Override
    public List<HistoricoExistenciaPorClaveUmus> getByFiltrosDetalle(Date fechaInicio, Date fechaFin, String delegacion, String numeroUmu, String nombreUmu, String clave, String clave2, String lote) {
        List<HistoricoExistenciaPorClaveUmus> resultList = null;
        try {
            String query = "SELECT hecu FROM HistoricoExistenciaPorClaveUmus hecu WHERE hecu.fechaIngreso BETWEEN :fechaInicio AND :fechaFin \n";
            if (!delegacion.equals("-1")) {
                query = query + " AND hecu.nombreDelegacion = '" + delegacion + "'";
            }
            if (!numeroUmu.equals("")) {
                query = query + " AND hecu.umu LIKE '%" + numeroUmu + "%'";
            }
            if (!nombreUmu.equals("")) {
                query = query + " AND hecu.descUmu LIKE '%" + nombreUmu + "%'";
            }
            if (!clave.equals("-1")) {
                query = query + " AND hecu.clave = '" + clave + "'";
            }
            if (!clave2.equals("-1")) {
                query = query + " AND hecu.clave = '" + clave2 + "'";
            }
            if (!lote.equals("")) {
                query = query + " AND hecu.lote LIKE '%" + lote + "%'";
            }
            Query q = em.createQuery(query);
            q.setParameter("fechaInicio", fechaInicio);
            q.setParameter("fechaFin", fechaFin);
            resultList = q.getResultList();

        } catch (Exception e) {
            Logger.getLogger(HistoricoExistenciaPorClaveUmusDAOImplement.class.getName()).log(Level.SEVERE, "", e);
        }
        if (resultList != null && resultList.size() > 0) {
            return resultList;
        }
        return null;
    }
    
    @Override
    public List<String> getDistinctTipo() {
        List<String> resultList = null;
        String query = "";
        try {
            resultList = em.createQuery("SELECT DISTINCT(hepcu.tipo) FROM HistoricoExistenciaPorClaveUmus hepcu", String.class).getResultList();
        } catch (Exception e) {
            Logger.getLogger(HistoricoExistenciaPorClaveUmusDAOImplement.class.getName()).log(Level.SEVERE, "", e);
        }
        if (resultList != null && resultList.size() > 0) {
            return resultList;
        }
        return null;
    }
    
    @Override
    public List<String> getDistinctNumeroUmu() {
        List<String> resultList = null;
        String query = "";
        try {
            resultList = em.createQuery("SELECT DISTINCT(hepcu.umu) FROM HistoricoExistenciaPorClaveUmus hepcu", String.class).getResultList();
        } catch (Exception e) {
            Logger.getLogger(HistoricoExistenciaPorClaveUmusDAOImplement.class.getName()).log(Level.SEVERE, "", e);
        }
        if (resultList != null && resultList.size() > 0) {
            return resultList;
        }
        return null;
    }
    
    @Override
    public List<String> getDistinctNombreUmu() {
        List<String> resultList = null;
        String query = "";
        try {
            resultList = em.createQuery("SELECT DISTINCT(hepcu.descUmu) FROM HistoricoExistenciaPorClaveUmus hepcu", String.class).getResultList();
        } catch (Exception e) {
            Logger.getLogger(HistoricoExistenciaPorClaveUmusDAOImplement.class.getName()).log(Level.SEVERE, "", e);
        }
        if (resultList != null && resultList.size() > 0) {
            return resultList;
        }
        return null;
    }

}
