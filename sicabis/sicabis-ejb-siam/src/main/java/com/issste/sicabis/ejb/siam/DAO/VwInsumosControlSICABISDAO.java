
package com.issste.sicabis.ejb.siam.DAO;

import com.issste.sicabis.ejb.siam.modelo.VwInsumosControlSICABIS;
import java.util.List;
import javax.ejb.Local;

@Local
public interface VwInsumosControlSICABISDAO {
    
    List<VwInsumosControlSICABIS> obtenerByClave(String clave);
    
}
