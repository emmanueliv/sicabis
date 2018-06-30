
package com.issste.sicabis.ejb.DAO;

import com.issste.sicabis.ejb.modelo.TipoInsumoDpn;
import java.util.List;
import javax.ejb.Local;

@Local
public interface TipoInsumoDpnDAO {
    
    List<TipoInsumoDpn> getAll();
    TipoInsumoDpn getById(Integer idTipoInsumoDpn);
    
}
