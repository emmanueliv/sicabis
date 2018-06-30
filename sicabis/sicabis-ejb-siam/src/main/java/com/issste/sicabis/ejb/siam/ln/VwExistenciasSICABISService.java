package com.issste.sicabis.ejb.siam.ln;

import com.issste.sicabis.ejb.siam.DAO.VwExistenciasSICABISDAO;
import com.issste.sicabis.ejb.siam.modelo.VwExistenciasSICABIS;
import java.util.Date;
import java.util.List;
import javax.ejb.EJB;
import javax.ejb.Stateful;

@Stateful
public class VwExistenciasSICABISService {

    @EJB
    private VwExistenciasSICABISDAO vwExistenciasSICABISDAOImplement;
    
    public Integer obtenerSumExistenciasByClave(String clave) {
        return vwExistenciasSICABISDAOImplement.obtenerSumExistenciasByClave(clave);
    }
    
    public List<VwExistenciasSICABIS> getByUMUClaveFecha(String clave, String claveUmu, Date fecha, Date fechaIni, Date fechaFin) {
        return vwExistenciasSICABISDAOImplement.getByUMUClaveFecha(clave, claveUmu, fecha, fechaIni, fechaFin);
    }
    
    public void cargaConsumoDiario(String fechaInicio) {
        vwExistenciasSICABISDAOImplement.cargaConsumoDiario(fechaInicio);
    }
    
    public void cargaConsumoDiario3(String fechaInicio) {
        vwExistenciasSICABISDAOImplement.cargaConsumoDiario3(fechaInicio);
    }
    
    public String cargaConsumoDiarioAux(String fechaInicio, String fechaFin) {
        return vwExistenciasSICABISDAOImplement.cargaConsumoDiarioAux(fechaInicio, fechaFin);
    }

}
