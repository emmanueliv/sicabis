package com.issste.sicabis.ejb.ln;

import com.issste.sicabis.ejb.DAO.AlertasDpnDAO;
import com.issste.sicabis.ejb.modelo.AlertasDpn;
import java.util.List;
import javax.ejb.EJB;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;

@Stateless
@LocalBean
public class AlertasDpnService {

    @EJB
    private AlertasDpnDAO alertasDpnImplement;
    
    public List<AlertasDpn> getByAnioMes(Integer anio, Integer mes) {
        return alertasDpnImplement.getByAnioMes(anio, mes);
    }
    
    public AlertasDpn getByIdDelegacionAnioMes(Integer idDelegacion, Integer anio, Integer mes) {
        return alertasDpnImplement.getByIdDelegacionAnioMes(idDelegacion, anio, mes);
    }
    
    public boolean guardar(AlertasDpn alertasDpn) {
        return alertasDpnImplement.guardar(alertasDpn);
    }
    
    public List<AlertasDpn> getAllByAnioMes(Integer anio, Integer mes) {
        return alertasDpnImplement.getAllByAnioMes(anio, mes);
    }
    
    public boolean borraAlertasDpn(Integer anio, Integer mes, Integer ur) {
        return alertasDpnImplement.borraAlertasDpn(anio, mes, ur);
    }
    
    public boolean updateEstatusById(Integer idEstatus, Integer idAlertasDpn) {
        return alertasDpnImplement.updateEstatusById(idEstatus, idAlertasDpn);
    }

}
