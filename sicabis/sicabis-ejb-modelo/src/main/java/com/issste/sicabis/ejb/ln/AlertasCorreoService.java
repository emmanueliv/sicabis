package com.issste.sicabis.ejb.ln;

import com.issste.sicabis.ejb.DAO.AlertasCorreoDAO;
import com.issste.sicabis.ejb.modelo.AlertasCorreo;
import com.issste.sicabis.ejb.modelo.AlertasEnvio;
import java.util.List;
import javax.ejb.EJB;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;

@Stateless
@LocalBean
public class AlertasCorreoService {

    @EJB
    private AlertasCorreoDAO alertasCorreoDAOImplement;

    public boolean guardar(AlertasCorreo alertasCorreo) {
        return alertasCorreoDAOImplement.guardar(alertasCorreo);
    }

    public List<AlertasCorreo> obtenerListaAlertasCorreo(String claveUnidad) {
        return alertasCorreoDAOImplement.obtenerListaAlertasCorreo(claveUnidad);
    }
}
