
package com.issste.sicabis.ejb.DAO;

import com.issste.sicabis.ejb.modelo.ConfiguraDpn;
import java.util.List;
import javax.ejb.Local;

@Local
public interface ConfiguraDpnDAO {
    
    boolean guardaConfiguraDpn(ConfiguraDpn configuraDpn);
    List<ConfiguraDpn> getAllByActivo (Integer activo);
    
}
