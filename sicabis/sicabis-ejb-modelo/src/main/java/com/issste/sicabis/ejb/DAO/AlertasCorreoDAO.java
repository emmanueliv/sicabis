
package com.issste.sicabis.ejb.DAO;

import com.issste.sicabis.ejb.modelo.AlertasCorreo;
import com.issste.sicabis.ejb.modelo.AlertasEnvio;
import java.util.List;
import javax.ejb.Local;

@Local
public interface AlertasCorreoDAO {
    
    boolean guardar(AlertasCorreo alertasCorreo);
    
    List<AlertasCorreo> obtenerListaAlertasCorreo(String claveUnidad);
    
}
