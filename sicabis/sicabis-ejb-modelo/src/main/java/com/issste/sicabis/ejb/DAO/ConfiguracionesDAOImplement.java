/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.issste.sicabis.ejb.DAO;

import com.issste.sicabis.ejb.modelo.Configuraciones;
import com.issste.sicabis.ejb.utils.ErrorUtil;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author kriosoft
 */
@Stateless
public class ConfiguracionesDAOImplement implements ConfiguracionesDAO {

    @PersistenceContext(unitName = "sicabis")
    private EntityManager em;

    @Override
    public Configuraciones getValorParametroByParam(String parametro) {
        List<Configuraciones> resultList = null;
        try {
            resultList = em.createQuery("Select c From Configuraciones c WHERE c.parametro = '" + parametro + "'").getResultList();
        } catch (Exception e) {
            Logger.getLogger(UsuariosDAOImplement.class.getName()).log(Level.SEVERE, ErrorUtil.CODE_0101, e);
        }
        return resultList.get(0);
    }

    @Override
    public List<Configuraciones> obtenerConfiguraciones() {
        List<Configuraciones> resultList = null;
        try {
            resultList = em.createNamedQuery("Configuraciones.findAll").getResultList();
        } catch (Exception e) {
            Logger.getLogger(UsuariosDAOImplement.class.getName()).log(Level.SEVERE, ErrorUtil.CODE_0101, e);
        }
        return resultList;
    }

    @Override
    public void guardarConfiguracion(Configuraciones configuraciones) {
        try {
            if (configuraciones.getIdConfiguraciones() == null) {
                em.persist(configuraciones);
            } else {
                em.merge(configuraciones);
            }
        } catch (Exception e) {
            Logger.getLogger(ConfiguracionesDAOImplement.class.getName()).log(Level.SEVERE, ErrorUtil.CODE_0101, e);
        }
    }

    @Override
    public List<Configuraciones> obtenerConfigByNombre(String descConfig) {
        return em.createNamedQuery("Configuraciones.findByParametro").setParameter("parametro", descConfig).getResultList();
    }

}
