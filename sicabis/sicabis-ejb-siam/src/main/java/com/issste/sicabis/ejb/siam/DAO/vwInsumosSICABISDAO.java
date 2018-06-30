
package com.issste.sicabis.ejb.siam.DAO;

import com.issste.sicabis.ejb.siam.modelo.VwInsumosSICABIS;
import java.util.List;
import javax.ejb.Local;

@Local
public interface vwInsumosSICABISDAO {
    
    List<VwInsumosSICABIS> obtenerVwInsumos();
    
}
