
package com.issste.sicabis.ejb.DAO;

import com.issste.sicabis.ejb.modelo.HistoricoExistenciaPorClaveCenadi;
import java.util.Date;
import java.util.List;
import javax.ejb.Local;

@Local
public interface HistoricoExistenciaPorClaveCenadiDAO {
    
    boolean guardar(HistoricoExistenciaPorClaveCenadi hepcc);
    List<HistoricoExistenciaPorClaveCenadi> getByFechaIngreso(Date fechaIngreso);
    List<HistoricoExistenciaPorClaveCenadi> getAll();
    List<HistoricoExistenciaPorClaveCenadi> getByFiltros(Date fechaInicio, Date fechaFin, String tipoClave, String clave,String clave2, String subinventario,String localizador,String lote );
    List<String> getDistinctSubinventario();
    List<String> getDistinctLocalizador();
    
}
