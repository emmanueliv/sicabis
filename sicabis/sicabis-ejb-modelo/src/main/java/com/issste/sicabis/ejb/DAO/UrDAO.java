
package com.issste.sicabis.ejb.DAO;

import com.issste.sicabis.ejb.modelo.Ur;
import java.util.List;
import javax.ejb.Local;

@Local
public interface UrDAO {
    
    List<Ur> getAll();
    Ur getByUr(Integer ur);
    
}
