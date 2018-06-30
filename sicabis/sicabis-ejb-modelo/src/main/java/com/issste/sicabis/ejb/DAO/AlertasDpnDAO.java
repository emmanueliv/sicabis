
package com.issste.sicabis.ejb.DAO;

import com.issste.sicabis.ejb.modelo.AlertasDpn;
import java.util.List;
import javax.ejb.Local;

@Local
public interface AlertasDpnDAO {
    
    List<AlertasDpn> getByAnioMes(Integer anio, Integer mes);
    AlertasDpn getByIdDelegacionAnioMes(Integer idDelegacion, Integer anio, Integer mes);
    boolean guardar(AlertasDpn alertasDpn);
    List<AlertasDpn> getAllByAnioMes (Integer anio, Integer mes);
    boolean borraAlertasDpn (Integer anio, Integer mes, Integer ur);
    boolean updateEstatusById(Integer idEstatus, Integer idAlertasDpn);
}
