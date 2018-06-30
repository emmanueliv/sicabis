package com.issste.sicabis.ejb.ln;

import com.issste.sicabis.ejb.DAO.InsumosSiamDAO;
import com.issste.sicabis.ejb.DAO.InsumosSiamDAOImplement;
import com.issste.sicabis.ejb.modelo.InsumosSiam;
import com.issste.sicabis.ejb.utils.ErrorUtil;
import java.sql.SQLException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.EJB;
import javax.ejb.Stateful;

@Stateful
public class InsumosSiamService {

    @EJB
    private InsumosSiamDAO insumosSiamDAOImplement;
    
    public boolean guardarActualizar(InsumosSiam is) {
        return insumosSiamDAOImplement.guardarActualizar(is);        
    }
    
    public InsumosSiam obtenerByClave(String clave) {
        return insumosSiamDAOImplement.obtenerByClave(clave);
    }
    
    public boolean borrarByClave(String clave) {
        return insumosSiamDAOImplement.borrarByClave(clave);
    }
    
    public List<InsumosSiam> obtenerAll() {
        return insumosSiamDAOImplement.obtenerAll();
    }

}
