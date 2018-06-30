
package com.issste.sicabis.ejb.DAO;

import com.issste.sicabis.ejb.modelo.CorreoElectronico;
import java.util.List;
import javax.ejb.Local;

@Local
public interface CorreoElectronicoDAO {
    
    boolean guardar(CorreoElectronico correoElectronico);
    
}
