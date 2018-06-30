
package com.issste.sicabis.ejb.DAO;

import com.issste.sicabis.ejb.modelo.Jefatura;
import java.util.List;
import javax.ejb.Local;

@Local
public interface JefaturaDAO {
    
    List<Jefatura> getAll();
    List<Jefatura> getByIdArea(Integer idArea);
    
}
