package com.issste.sicabis.ejb.ln;

import com.issste.sicabis.ejb.DAO.AlertasEnvioDAO;
import com.issste.sicabis.ejb.modelo.AlertasEnvio;
import java.util.List;
import javax.ejb.EJB;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;

@Stateless
@LocalBean
public class AlertasEnvioService {

    @EJB
    private AlertasEnvioDAO alertasEnvioDAOImplement;

    public boolean guardar(AlertasEnvio alertasEnvio) {
        return alertasEnvioDAOImplement.guardar(alertasEnvio);
    }

    public AlertasEnvio getByAnioMesClaveUnidad(AlertasEnvio alertasEnvio) {
        return alertasEnvioDAOImplement.getByAnioMesClaveUnidad(alertasEnvio);
    }

    public List<AlertasEnvio> getByAnioMes(Integer anio, Integer mes) {
        return alertasEnvioDAOImplement.getByAnioMes(anio, mes);
    }

    public boolean updateAllByActivo() {
        return alertasEnvioDAOImplement.updateAllByActivo();
    }

    public List<AlertasEnvio> getAlertasByClaveUnidad(Integer claveUnidad,String claveInsumo,Integer delegacion ) {
        return alertasEnvioDAOImplement.getAlertasByClaveUnidad(claveUnidad,claveInsumo,delegacion);
    }
    
    public List<AlertasEnvio> getByIdAlertaDpnUr(Integer ur, Integer anio, Integer mes) {
        return alertasEnvioDAOImplement.getByIdAlertaDpnUr(ur, anio, mes);
    }
    
    public boolean generaAlertasEnvio(String ur, Integer idAlertasDpn, Integer idDpn) {
        return alertasEnvioDAOImplement.generaAlertasEnvio(ur, idAlertasDpn, idDpn);
    }
    
    public boolean borraAlertasEnvio(Integer mes, Integer anio, Integer ur) {
        return alertasEnvioDAOImplement.borraAlertasEnvio(mes, anio, ur);
    }
    
    public boolean updateEstatusDpnById(Integer idAlertaEnvio, Integer idEstatus, Integer dpnSugerida) {
        return alertasEnvioDAOImplement.updateEstatusDpnById(idAlertaEnvio, idEstatus, dpnSugerida);
    }

}
