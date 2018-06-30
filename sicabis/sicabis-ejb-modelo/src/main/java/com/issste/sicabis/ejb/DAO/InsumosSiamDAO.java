
package com.issste.sicabis.ejb.DAO;

import com.issste.sicabis.ejb.modelo.InsumosSiam;
import java.util.List;
import javax.ejb.Local;

@Local
public interface InsumosSiamDAO {
    
    boolean guardarActualizar(InsumosSiam is);
    InsumosSiam obtenerByClave(String clave);
    boolean borrarByClave (String clave);
    List<InsumosSiam> obtenerAll();
    
}
