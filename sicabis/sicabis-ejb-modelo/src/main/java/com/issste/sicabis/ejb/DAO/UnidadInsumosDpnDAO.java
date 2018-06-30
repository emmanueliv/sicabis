
package com.issste.sicabis.ejb.DAO;

import com.issste.sicabis.ejb.modelo.UnidadInsumosDpn;
import com.issste.sicabis.ejb.modelo.UnidadesMedicas;
import java.util.List;
import javax.ejb.Local;

@Local
public interface UnidadInsumosDpnDAO {
    
    boolean guardarActualizar(UnidadInsumosDpn unidadInsumosDpn);
    List<UnidadInsumosDpn> getAll();
    List<UnidadInsumosDpn> getByIdInsumoDpn(Integer idInsumoDpn);
    List<UnidadesMedicas> getUMByIdInsumoDpn(Integer idInsumoDpn);
    boolean actualizaByIdInsumoDpn(Integer idInsumoDpn);
}
