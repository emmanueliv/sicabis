/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.issste.sicabis.ejb.DAO;

import com.issste.sicabis.ejb.modelo.TipoConvenio;
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
public class TiposConvenioDAOImplement implements TiposConvenioDAO {

    @PersistenceContext(unitName = "sicabis")
    private EntityManager em;

    @Override
    public List<TipoConvenio> obtenerTipoConvenios() {
        List<TipoConvenio> resultList = null;
        try {
            resultList = em.createQuery("SELECT t FROM TipoConvenio t WHERE t.activo = 1").getResultList();
        } catch (Exception e) {
            Logger.getLogger(TiposConvenioDAOImplement.class.getName()).log(Level.SEVERE, ErrorUtil.CODE_0101, e);
        }
        return resultList;
    }

    @Override
    public void guardarTipoConvenio(TipoConvenio convenio) {
        try {
            if (convenio.getIdTipoConvenio() == null) {
                em.persist(convenio);
            } else {
                em.merge(convenio);
            }
        } catch (Exception e) {
            Logger.getLogger(TareasDAOImplement.class.getName()).log(Level.SEVERE, ErrorUtil.CODE_0101, e);
        }
    }

    @Override
    public List<TipoConvenio> obtenerTipoConveniosByDesc(String nombreConvenio) {
        List<TipoConvenio> resultList = null;
        try {
            resultList = em.createQuery("SELECT t FROM TipoConvenio t WHERE t.nombre = :nombre AND t.activo = 1").setParameter("nombre", nombreConvenio).getResultList();
        } catch (Exception e) {
            Logger.getLogger(TiposConvenioDAOImplement.class.getName()).log(Level.SEVERE, ErrorUtil.CODE_0101, e);
        }
        return resultList;
    }

}
