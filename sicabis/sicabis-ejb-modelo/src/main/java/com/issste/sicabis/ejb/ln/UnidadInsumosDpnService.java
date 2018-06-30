package com.issste.sicabis.ejb.ln;

import com.issste.sicabis.ejb.DAO.UnidadInsumosDpnDAO;
import com.issste.sicabis.ejb.modelo.UnidadInsumosDpn;
import com.issste.sicabis.ejb.modelo.UnidadesMedicas;
import java.util.List;
import javax.ejb.EJB;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;

@LocalBean
@Stateless
public class UnidadInsumosDpnService {

    @EJB
    private UnidadInsumosDpnDAO unidadInsumosDpnDAOImplement;
    
    public boolean guardarActualizar(UnidadInsumosDpn unidadInsumosDpn) {
        return unidadInsumosDpnDAOImplement.guardarActualizar(unidadInsumosDpn);
    }
    
    public List<UnidadInsumosDpn> getAll() {
        return unidadInsumosDpnDAOImplement.getAll();
    }
    
    public List<UnidadInsumosDpn> getByIdInsumoDpn(Integer idInsumoDpn) {
        return unidadInsumosDpnDAOImplement.getByIdInsumoDpn(idInsumoDpn);
    }
    
    public List<UnidadesMedicas> getUMByIdInsumoDpn(Integer idInsumoDpn) {
        return unidadInsumosDpnDAOImplement.getUMByIdInsumoDpn(idInsumoDpn);
    }
    
    public boolean actualizaByIdInsumoDpn(Integer idInsumoDpn) {
        return unidadInsumosDpnDAOImplement.actualizaByIdInsumoDpn(idInsumoDpn);
    }

}
