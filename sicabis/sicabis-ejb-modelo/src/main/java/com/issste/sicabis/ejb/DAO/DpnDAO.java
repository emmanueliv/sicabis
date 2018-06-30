
package com.issste.sicabis.ejb.DAO;

import com.issste.sicabis.ejb.modelo.Dpn;
import com.issste.sicabis.ejb.modelo.DpnInsumos;
import java.util.List;
import javax.ejb.Local;

@Local
public interface DpnDAO {
    
    List<Dpn> getAllDesc();
    List<Dpn> getByAnio(int anio);
    boolean guardaActualiza(Dpn dpn);
    Dpn getDpnPrevio();
    Dpn getByIdDpn(Integer idDpn);
    Dpn getUltimaAutorizada();
    boolean actualizaUltimaDpn();
    boolean actualizaDpn(Dpn dpn);
    boolean actualizaDpnInsumo(DpnInsumos di);
    Dpn getByAnioMesIdEstatus(Integer anio, Integer mes, Integer idEstatus);
    Dpn getByIdPeriodoMes(Integer idPeriodoMes);
    
}
