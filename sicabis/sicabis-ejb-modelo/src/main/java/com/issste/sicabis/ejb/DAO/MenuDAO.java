package com.issste.sicabis.ejb.DAO;

import com.issste.sicabis.ejb.modelo.Menu;
import java.util.List;
import javax.ejb.Local;

@Local
public interface MenuDAO {
    
    List<Menu> obtenerMenu();
    List<Menu> obtenerMenuByidPerfil(Integer idPerfil);
    Menu obtenerMenuById(Integer idMenu);
    List<Menu> obtenerMenuByIdMenuPadre(Integer idMenuPadre, Integer activo);
    List<Menu> obtenerMenuByFinalIdTarea(Integer final1, Integer idTarea);
    
}
