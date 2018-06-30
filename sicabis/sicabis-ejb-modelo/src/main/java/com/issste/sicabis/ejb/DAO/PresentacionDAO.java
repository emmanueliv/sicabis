package com.issste.sicabis.ejb.DAO;

import com.issste.sicabis.ejb.modelo.Presentacion;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author fabianvr
 */
@Local
public interface PresentacionDAO {
    
    List<Presentacion> presentacionList(); 

    List<Presentacion> presentacionByDesc(String desc);

    void guardarPresentacion(Presentacion presentacion);
    
}
