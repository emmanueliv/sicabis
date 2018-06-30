
package com.issste.sicabis.ejb.DAO;

import com.issste.sicabis.ejb.modelo.AlertasEnvio;
import java.util.List;
import javax.ejb.Local;

@Local
public interface AlertasEnvioDAO {
    
    boolean guardar(AlertasEnvio alertasEnvio);
    AlertasEnvio getByAnioMesClaveUnidad(AlertasEnvio alertasEnvio);
    List<AlertasEnvio> getByAnioMes(Integer anio, Integer mes);
    boolean updateAllByActivo();
    List<AlertasEnvio> getAlertasByClaveUnidad(Integer claveUnidad,String claveInsumo,Integer delegacion);
    List<AlertasEnvio> getByIdAlertaDpnUr(Integer ur, Integer anio, Integer mes);
    boolean generaAlertasEnvio(String ur, Integer idAlertasDpn, Integer idDpn);
    boolean borraAlertasEnvio(Integer mes, Integer anio, Integer ur);
    boolean updateEstatusDpnById(Integer idAlertaEnvio, Integer idEstatus, Integer dpnSugerida);
    
}
