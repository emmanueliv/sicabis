/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.issste.sicabis.ejb.DAO;

import static com.issste.sicabis.ejb.modelo.Pacientes_.curp;
import com.issste.sicabis.ejb.modelo.TipoUsuarios;
import com.issste.sicabis.ejb.utils.ErrorUtil;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author 9RZCBG2
 */
@Stateless
public class TipoUsuariosDAOImplement implements TipoUsuariosDAO {

    @PersistenceContext(unitName = "sicabis")
    private EntityManager em;

    @Override
    public void guadarActualizar(TipoUsuarios tipoUsuario) {
        try {
            if (tipoUsuario.getIdTipoUsuario() == null) {
                em.persist(tipoUsuario);
            } else {
                em.merge(tipoUsuario);
            }
        } catch (Exception e) {
            Logger.getLogger(TipoUsuariosDAOImplement.class.getName()).log(Level.SEVERE, ErrorUtil.CODE_0101, e);
        }
    }

    @Override
    public List<TipoUsuarios> obtenerListaTiposUsuarios(String nombre) {
        List<TipoUsuarios> resultList = null;
        String query;
        try {
            query = "SELECT tu FROM TipoUsuarios tu WHERE tu.activo = 1 ";
            if (nombre != null && !nombre.equals("")) {
                query = query + "AND tu.nombre = '" + nombre.toUpperCase() + "'";
            }
            resultList = em.createQuery(query).getResultList();
        } catch (Exception e) {
            Logger.getLogger(TipoUsuariosDAOImplement.class.getName()).log(Level.SEVERE, ErrorUtil.CODE_0101, e);
        }
        return resultList;
    }

    @Override
    public TipoUsuarios obtenerTipoUsuarioByNombre(String nombre) {
        TipoUsuarios tu = new TipoUsuarios();
        try {
            tu = (TipoUsuarios) em.createQuery("SELECT tu FROM TipoUsuarios tu where tu.nombre=:nombre ")
                    .setParameter("nombre", nombre)
                    .getSingleResult();
        } catch (Exception e) {
            System.out.println("Usuario inexistente");
            // Logger.getLogger(TipoUsuariosDAOImplement.class.getName()).log(Level.SEVERE, ErrorUtil.CODE_0101, e);
        }
        return tu == null ? new TipoUsuarios() : tu;
    }

}
