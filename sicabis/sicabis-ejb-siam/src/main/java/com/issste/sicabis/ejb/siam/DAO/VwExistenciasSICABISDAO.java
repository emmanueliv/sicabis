
package com.issste.sicabis.ejb.siam.DAO;

import com.issste.sicabis.ejb.modelo.ConsumoDiarioSiam;
import com.issste.sicabis.ejb.siam.modelo.VwExistenciasSICABIS;
import java.util.Date;
import java.util.List;
import javax.ejb.Local;

@Local
public interface VwExistenciasSICABISDAO {
    
    Integer obtenerSumExistenciasByClave(String clave);
    List<VwExistenciasSICABIS> getByUMUClaveFecha(String clave, String claveUmu, Date fecha, Date fechaIni, Date fechafin);
    Integer getSumaConsumo(String clave, String umu, Date fechaIni, Date fechafin);
    void cargaConsumoDiario(String fechaInicio);
    void cargaConsumoDiario3(String fechaInicio);
    String cargaConsumoDiarioAux(String fechaInicio, String fechaFin);
}
