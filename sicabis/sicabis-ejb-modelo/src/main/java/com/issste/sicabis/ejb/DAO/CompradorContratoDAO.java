
package com.issste.sicabis.ejb.DAO;

import com.issste.sicabis.ejb.modelo.CompradorContrato;
import java.util.List;
import javax.ejb.Local;

@Local
public interface CompradorContratoDAO {
    
    boolean guardaCompradorContrato(CompradorContrato compradorContrato);
    boolean actualizaCompradorContrato(CompradorContrato compradorContrato);
    boolean borrarByIdContrato(Integer idContrato);
    CompradorContrato obtenerByIdContrato(Integer idContrato);
    List<CompradorContrato> obtenerListByIdContrato(Integer idContrato);
    
}
