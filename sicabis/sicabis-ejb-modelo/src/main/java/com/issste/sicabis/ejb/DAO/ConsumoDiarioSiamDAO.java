
package com.issste.sicabis.ejb.DAO;

import com.issste.sicabis.ejb.modelo.ConsumoDiarioSiam;
import java.sql.ResultSet;
import java.util.Date;
import java.util.List;
import javax.ejb.Local;

@Local
public interface ConsumoDiarioSiamDAO {
    
    boolean guarda(ConsumoDiarioSiam cds);
    boolean eliminaTodo();
    Integer sumaConsumo(String claveInsumo, String claveUnidad, Date fechaInicio, Date fechaFin);
    boolean eliminaFecha(Date fecha, Date fechaFin);
    ResultSet ejecutaQuery(String query);
    int ejecutaUpdate(String query);
    List<ConsumoDiarioSiam> getByFechas(String fechaIni, String fechaFin);
    
}
