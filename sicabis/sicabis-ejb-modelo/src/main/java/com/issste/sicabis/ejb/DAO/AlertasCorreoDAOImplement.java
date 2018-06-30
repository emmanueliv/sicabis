package com.issste.sicabis.ejb.DAO;

import com.issste.sicabis.ejb.modelo.AlertasCorreo;
import com.issste.sicabis.ejb.modelo.AlertasEnvio;
import com.issste.sicabis.ejb.utils.ErrorUtil;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@Stateless
public class AlertasCorreoDAOImplement implements AlertasCorreoDAO {

    @PersistenceContext(unitName = "sicabis")
    private EntityManager em;

    @Override
    public boolean guardar(AlertasCorreo alertasCorreo) {
        try {
            if (alertasCorreo.getIdAlertasCorreo() == null) {
                em.persist(alertasCorreo);
            } else {
                em.merge(alertasCorreo);
            }
            return true;
        } catch (Exception e) {
            Logger.getLogger(AlertasCorreoDAOImplement.class.getName()).log(Level.SEVERE, ErrorUtil.CODE_0101, e);
            return false;
        }
    }

    @Override
    public List<AlertasCorreo> obtenerListaAlertasCorreo(String claveUnidad) {
        return em.createQuery("SELECT ac FROM AlertasCorreo ac where "
                + "ac.idUsuario.idUnidadMedica.clavePresupuestal = '" + claveUnidad + "'").getResultList();
    }
}
