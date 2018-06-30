/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.issste.sicabis.ejb.DAO;

import com.issste.sicabis.ejb.modelo.UnidadesMedicas;
import com.issste.sicabis.ejb.utils.ErrorUtil;
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
public class UnidadMedicaDAOImplement implements UnidadMedicaDAO {

    @PersistenceContext(unitName = "sicabis")
    private EntityManager em;

    @Override
    public List<UnidadesMedicas> unidadMedica() {
        List<UnidadesMedicas> resultList = null;
        try {
            resultList = em.createQuery("SELECT um FROM UnidadesMedicas um ORDER BY um.nombre").getResultList();
        } catch (Exception e) {
            Logger.getLogger(UnidadMedicaDAOImplement.class.getName()).log(Level.SEVERE, ErrorUtil.CODE_0101, e);
        }
        if (resultList != null && resultList.size() > 0) {
            return resultList;
        }
        return null;
    }

    @Override
    public List<UnidadesMedicas> unidadMedicaByActivo() {
        List<UnidadesMedicas> resultList = null;
        try {
            resultList = em.createQuery("SELECT um FROM UnidadesMedicas um WHERE um.activo = 1 ORDER BY um.nombre").getResultList();
        } catch (Exception e) {
            Logger.getLogger(UnidadMedicaDAOImplement.class.getName()).log(Level.SEVERE, ErrorUtil.CODE_0101, e);
        }
        if (resultList != null && resultList.size() > 0) {
            return resultList;
        }
        return null;
    }

    @Override
    public void guardarUnidadesMedicas(UnidadesMedicas unidadMedica) {
        try {
            if (unidadMedica.getIdUnidadesMedicas() == null) {
                em.persist(unidadMedica);
            } else {
                em.merge(unidadMedica);
            }
        } catch (Exception e) {
            Logger.getLogger(UnidadMedicaDAOImplement.class.getName()).log(Level.SEVERE, ErrorUtil.CODE_0101, e);
        }
    }

    @Override
    public List<UnidadesMedicas> unidadMedicaByNombre(String nombreUnidadesMedicas) {
        List<UnidadesMedicas> resultList = null;
        try {
            resultList = em.createQuery("SELECT um FROM UnidadesMedicas um WHERE um.nombre = :nombre AND um.activo = 1 ORDER BY um.nombre").setParameter("nombre", nombreUnidadesMedicas).getResultList();
        } catch (Exception e) {
            Logger.getLogger(UnidadMedicaDAOImplement.class.getName()).log(Level.SEVERE, ErrorUtil.CODE_0101, e);
        }
        return resultList;
    }

    @Override
    public List<UnidadesMedicas> obtenUnidadesMedicas() {

        List<UnidadesMedicas> resultList = null;
        try {
            resultList = em.createQuery("SELECT u FROM UnidadesMedicas u").getResultList();
        } catch (Exception e) {
            Logger.getLogger(UnidadMedicaDAOImplement.class.getName()).log(Level.SEVERE, ErrorUtil.CODE_0101, e);
        }
        return resultList;

    }

    @Override
    public List<UnidadesMedicas> obtenUnidadesMedicasByActivoAndId(int unidadMed) {

        List<UnidadesMedicas> resultList = null;
        try {
            resultList = em.createNativeQuery("select * from unidades_medicas where activo = 1 and id_unidades_medicas = " + unidadMed + "\n", UnidadesMedicas.class).getResultList();
        } catch (Exception e) {
            Logger.getLogger(UnidadMedicaDAOImplement.class.getName()).log(Level.SEVERE, ErrorUtil.CODE_0101, e);
        }
        return resultList;

    }

    @Override
    public UnidadesMedicas getByIdUnidadMedica(Integer idUnidadesMedicas) {
        List<UnidadesMedicas> resultList = null;
        try {
            resultList = em.createNamedQuery("UnidadesMedicas.findByIdUnidadesMedicas").setParameter("idUnidadesMedicas", idUnidadesMedicas).getResultList();
        } catch (Exception e) {
            Logger.getLogger(UnidadMedicaDAOImplement.class.getName()).log(Level.SEVERE, ErrorUtil.CODE_0101, e);
        }
        if (resultList != null && resultList.size() > 0) {
            return resultList.get(0);
        }
        return null;
    }

    @Override
    public List<UnidadesMedicas> getByConcentradora(Integer concentradora) {
        List<UnidadesMedicas> resultList = null;
        try {
            resultList = em.createNamedQuery("UnidadesMedicas.findByConcentradora").setParameter("concentradora", concentradora).getResultList();
        } catch (Exception e) {
            Logger.getLogger(UnidadMedicaDAOImplement.class.getName()).log(Level.SEVERE, ErrorUtil.CODE_0101, e);
        }
        if (resultList != null && resultList.size() > 0) {
            return resultList;
        }
        return null;
    }

    @Override
    public List<UnidadesMedicas> getByHospitalRegional(Integer hospitalRegional) {
        List<UnidadesMedicas> resultList = null;
        try {
            resultList = em.createQuery("SELECT um FROM UnidadesMedicas um WHERE um.hospitalRegional = :hospitalRegional AND um.activo = 1 ORDER BY um.nombre").setParameter("hospitalRegional", hospitalRegional).getResultList();
        } catch (Exception e) {
            Logger.getLogger(UnidadMedicaDAOImplement.class.getName()).log(Level.SEVERE, ErrorUtil.CODE_0101, e);
        }
        if (resultList != null && resultList.size() > 0) {
            return resultList;
        }
        return null;
    }

}
