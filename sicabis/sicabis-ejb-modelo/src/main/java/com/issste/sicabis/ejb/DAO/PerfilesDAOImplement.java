/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.issste.sicabis.ejb.DAO;

import com.issste.sicabis.ejb.modelo.Perfiles;
import com.issste.sicabis.ejb.utils.ErrorUtil;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.LocalBean;
import javax.ejb.Stateful;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author Toshiba Manolo
 */
@Stateful
@LocalBean
public class PerfilesDAOImplement implements PerfilesDAO {

    @PersistenceContext(unitName = "sicabis")
    private EntityManager em;

    @Override
    public List<Perfiles> obtenerPerfiles() {
        List<Perfiles> resultList = null;
        try {
            resultList = em.createQuery("Select p From Perfiles p where p.fechaBaja is null AND p.nombre not in ('administrador')").getResultList();
        } catch (Exception e) {
            Logger.getLogger(PerfilesDAOImplement.class.getName()).log(Level.SEVERE, ErrorUtil.CODE_0101, e);
        }
        if (resultList != null && resultList.size() > 0) {
            return resultList;
        }
        return null;
    }

    @Override
    public boolean guardaPerfil(Perfiles perfiles) {
        try {
            if (perfiles.getIdPerfil() == null) {
                em.persist(perfiles);
            } else {
                em.merge(perfiles);
            }
            return true;
        } catch (Exception e) {
            Logger.getLogger(PerfilesDAOImplement.class.getName()).log(Level.SEVERE, ErrorUtil.CODE_0101, e);
            return false;
        }
    }

    @Override
    public boolean obtenerPerfilByNombre(String nombre, Integer idPerfil) {
        List<Perfiles> resultList = null;
        String query = "";
        query = " Select p From Perfiles p where p.nombre = '" + nombre + "' and p.fechaBaja is null";
        if (idPerfil != 0) {
            query = query + "\n and p.idPerfil != " + idPerfil;
        }
        try {
            resultList = em.createQuery(query).getResultList();
        } catch (Exception e) {
            Logger.getLogger(PerfilesDAOImplement.class.getName()).log(Level.SEVERE, ErrorUtil.CODE_0101, e);
        }
        if (resultList != null && resultList.size() > 0) {
            return false;
        }
        return true;
    }

    @Override
    public Perfiles obtenerPerfilByIdUsuario(Integer idUsuario) {
        List<Perfiles> resultList = null;
        try {
            resultList = em.createQuery("SELECT u.idPerfil FROM UsuarioPerfil u WHERE u.idUsuario.idUsuario = :idUsuario ").setParameter("idUsuario", idUsuario).getResultList();
            System.out.println("size-->" + resultList.size());
        } catch (Exception e) {
            Logger.getLogger(PerfilesDAOImplement.class.getName()).log(Level.SEVERE, ErrorUtil.CODE_0101, e);
        }
        return resultList.get(0);
    }

    @Override
    public List<Perfiles> getByNombreActivo(String nombre, Integer activo) {
        List<Perfiles> resultList = null;
        String query = "";
        try {
            if (nombre.equals("")) {
                nombre = null;
            }
            if (activo.intValue() == -1) {
                activo = null;
            }
            query = "SELECT p FROM Perfiles p WHERE (:nombre IS NULL OR p.nombre = :nombre) AND (:activo IS NULL OR p.activo = :activo)";
            resultList = em.createQuery(query).setParameter("nombre", nombre).setParameter("activo", activo).getResultList();
        } catch (Exception e) {
            Logger.getLogger(PerfilesDAOImplement.class.getName()).log(Level.SEVERE, ErrorUtil.CODE_0101, e);
        }
        if (resultList != null && resultList.size() > 0) {
            return resultList;
        }
        return null;
    }

}
