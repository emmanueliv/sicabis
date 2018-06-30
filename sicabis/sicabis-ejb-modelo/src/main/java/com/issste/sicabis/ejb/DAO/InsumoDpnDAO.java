
package com.issste.sicabis.ejb.DAO;

import com.issste.sicabis.ejb.modelo.InsumoDpn;
import java.util.List;
import javax.ejb.Local;

@Local
public interface InsumoDpnDAO {
    
    boolean guardarActualizar(InsumoDpn insumoDpn);
    List<InsumoDpn> getall();
    List<InsumoDpn> getByInsumoDpn(InsumoDpn insumoDpn);
    InsumoDpn getByIdInsumoDpn(Integer idInsumoDpn);
    
}
