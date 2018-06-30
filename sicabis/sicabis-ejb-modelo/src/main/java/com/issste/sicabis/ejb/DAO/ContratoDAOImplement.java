package com.issste.sicabis.ejb.DAO;

import com.issste.sicabis.ejb.modelo.Contratos;
import com.issste.sicabis.ejb.utils.ErrorUtil;
import java.util.Calendar;
import java.util.List;
import java.util.Locale;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

@Stateless
public class ContratoDAOImplement implements ContratoDAO {

    @PersistenceContext(unitName = "sicabis")
    private EntityManager em;

    @Override
    public boolean guardaContrato(Contratos contratos) {
        try {
            em.persist(contratos);
            return true;
        } catch (Exception e) {
            Logger.getLogger(ContratoDAOImplement.class.getName()).log(Level.SEVERE, ErrorUtil.CODE_0101, e);
            return false;
        }
    }

    @Override
    public boolean actualizaContrato(Contratos contratos) {
        try {
            em.merge(contratos);
            em.getEntityManagerFactory().getCache().evictAll();
            return true;
        } catch (Exception e) {
            Logger.getLogger(ContratoDAOImplement.class.getName()).log(Level.SEVERE, ErrorUtil.CODE_0101, e);
            return false;
        }
    }

    @Override
    public List<Contratos> obtenerByNumeroContrato(String numeroContrato) {
        try {
            return em.createQuery("SELECT c FROM Contratos c WHERE c.activo = 1 and c.idEstatus.idEstatus != 51 and c.numeroContrato = :numeroContrato order by c.fechaAlta desc").setParameter("numeroContrato", numeroContrato).getResultList();
        } catch (Exception e) {
            Logger.getLogger(ContratoDAOImplement.class.getName()).log(Level.SEVERE, ErrorUtil.CODE_0101, e);
            return null;
        }
    }

    @Override
    public List<Contratos> obtenerContratos(Contratos contratos) {
        List<Contratos> resultList = null;
        String query = "";
        boolean bandera = false;
        try {
            query = "  Select c \n"
                    + "  From Contratos c \n"
                    + " Where c.activo = 1 \n"
                    + "   and c.idPadre = 0";

            if (!contratos.getNumeroContrato().equals("")) {
                query = query + "   and c.numeroContrato = '" + contratos.getNumeroContrato() + "' \n";
            }

            resultList = em.createQuery(query).getResultList();
        } catch (Exception e) {
            Logger.getLogger(ContratoDAOImplement.class.getName()).log(Level.SEVERE, ErrorUtil.CODE_0101, e);
        }
        if (resultList != null && resultList.size() > 0) {
            return resultList;
        }
        return null;
    }

    @Override
    public List<Contratos> obtenerConvenios(Contratos contratos) {
        List<Contratos> resultList = null;
        String query = "";
        boolean bandera = false;
        try {
            query = "  Select c \n"
                    + "  From Contratos c \n"
                    + " Where c.activo = 1 \n"
                    + "   and c.idPadre != 0";

            if (!contratos.getNumeroContrato().equals("")) {
                query = query + "   and c.numeroContrato = '" + contratos.getNumeroContrato() + "' \n";
            }

            if (!contratos.getNumeroConvenio().equals("")) {
                query = query + "   and c.numeroConvenio = '" + contratos.getNumeroConvenio() + "' \n";
            }

            resultList = em.createQuery(query).getResultList();
        } catch (Exception e) {
            Logger.getLogger(ContratoDAOImplement.class.getName()).log(Level.SEVERE, ErrorUtil.CODE_0101, e);
        }
        if (resultList != null && resultList.size() > 0) {
            return resultList;
        }
        return null;
    }

    @Override
    public List<Contratos> contratoById(Integer id) {
        try {
            return em.createQuery("SELECT c FROM Contratos c "
                    + "WHERE c.idContrato = " + id + "").getResultList();
        } catch (Exception e) {
            Logger.getLogger(ContratoDAOImplement.class.getName()).log(Level.SEVERE, ErrorUtil.CODE_0101, e);
            return null;
        }
    }

    @Override
    public List<Contratos> contratoConvenioById(Integer id) {
        try {
            return em.createQuery("SELECT c FROM Contratos c "
                    + "WHERE c.idPadre is null "
                    + "OR c.idPadre != 0 "
                    + "AND c.idContrato = " + id + "").getResultList();
        } catch (Exception e) {
            Logger.getLogger(ContratoDAOImplement.class.getName()).log(Level.SEVERE, ErrorUtil.CODE_0101, e);
            return null;
        }
    }

    @Override
    public List<Contratos> obtenerConvenioByNumeroContratoIdContrato(String numeroContrato, Integer idContrato) {
        Query query = null;
        try {
            query = em.createQuery("SELECT c FROM Contratos c WHERE c.activo = 1 and c.numeroContrato = :numeroContrato and c.idContrato != :idContrato order by c.fechaAlta desc");
            query.setParameter("numeroContrato", numeroContrato);
            query.setParameter("idContrato", idContrato);
            return query.getResultList();
        } catch (Exception e) {
            Logger.getLogger(ContratoDAOImplement.class.getName()).log(Level.SEVERE, ErrorUtil.CODE_0101, e);
            return null;
        }
    }

    @Override
    public List<Contratos> obtenerByNumeroConvenio(String numeroConvenio) {
        try {
            return em.createQuery("SELECT c FROM Contratos c WHERE c.activo = 1 and c.numeroConvenio = :numeroConvenio order by c.fechaAlta desc").setParameter("numeroConvenio", numeroConvenio).getResultList();
        } catch (Exception e) {
            Logger.getLogger(ContratoDAOImplement.class.getName()).log(Level.SEVERE, ErrorUtil.CODE_0101, e);
            return null;
        }
    }

    @Override
    public Integer obtenerByMaxNumeroContratos() {
        try {
            return (Integer) em.createQuery("SELECT MAX(c.idContrato) FROM Contratos c").getSingleResult();
        } catch (Exception e) {
            Logger.getLogger(ContratoDAOImplement.class.getName()).log(Level.SEVERE, ErrorUtil.CODE_0101, e);
            return -1;
        }
    }

    @Override
    public Contratos obtenerByOneNumeroContrato(String numeroContrato) {
        try {
            return (Contratos) em.createNamedQuery("Contratos.findByNumeroContrato").setParameter("numeroContrato", numeroContrato).getSingleResult();
        } catch (Exception e) {
            Logger.getLogger(FalloDAOImplement.class.getName()).log(Level.SEVERE, ErrorUtil.CODE_0101, e);
            return null;
        }
    }

    @Override
    public List<Contratos> obtenerByNumeroContratoOrderById(String numeroContrato) {
        try {
            return em.createQuery("SELECT c FROM Contratos c WHERE c.numeroContrato = :numeroContrato order by c.idContrato desc").setParameter("numeroContrato", numeroContrato).getResultList();
        } catch (Exception e) {
            Logger.getLogger(ContratoDAOImplement.class.getName()).log(Level.SEVERE, ErrorUtil.CODE_0101, e);
            return null;
        }
    }

    @Override
    public Integer obtenerByMaxNumero() {
        Integer maxNumero = 0;
        List<Integer> integerList = null;
        Query query;
        String consult = "";
        Calendar c = Calendar.getInstance(Locale.ENGLISH);
        consult = "select  max (cast (numero_contrato as int)) from contratos";
        try {
            query = em.createNativeQuery(consult);
            integerList = query.getResultList();
            for (Integer result : integerList) {
                maxNumero = result;
            }
            if (maxNumero == null) {
                maxNumero = 0;
            } else {
                String year = maxNumero.toString().substring(0, 2);
                year = "20" + year;
                Integer yearA = c.get(Calendar.YEAR);
                if (!yearA.toString().equals(year)) {
                    maxNumero = 1;
                }
            }
        } catch (Exception e) {
            Logger.getLogger(ContratoFalloProcedimientoRcbDAOImplement.class.getName()).log(Level.SEVERE, ErrorUtil.CODE_0101, e);
            return null;
        }
        return maxNumero;
    }
    
    @Override
    public Integer obtenerByMaxNumeroConvenio() {
        Integer maxNumero = 0;
        List<Integer> integerList = null;
        Query query;
        String consult = "";
        Calendar c = Calendar.getInstance(Locale.ENGLISH);
        consult = "select  max (cast (numero_convenio as int)) from contratos";
        try {
            query = em.createNativeQuery(consult);
            integerList = query.getResultList();
            for (Integer result : integerList) {
                maxNumero = result;
            }
            if (maxNumero == null) {
                maxNumero = 0;
            } else {
                String year = maxNumero.toString().substring(4, 6);
                year = "20" + year;
                Integer yearA = c.get(Calendar.YEAR);
                if (!yearA.toString().equals(year)) {
                    maxNumero = 1;
                }
            }
        } catch (Exception e) {
            Logger.getLogger(ContratoFalloProcedimientoRcbDAOImplement.class.getName()).log(Level.SEVERE, ErrorUtil.CODE_0101, e);
            return null;
        }
        return maxNumero;
    }

}
