package com.issste.sicabis.ejb.ln;

import com.issste.sicabis.ejb.DAO.InsumoDpnDAO;
import com.issste.sicabis.ejb.modelo.InsumoDpn;
import java.util.List;
import javax.ejb.EJB;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;

@LocalBean
@Stateless
public class InsumoDpnService {

    @EJB
    private InsumoDpnDAO insumoDpnDAOImplement;
    
    public boolean guardarActualizar(InsumoDpn insumoDpn) {
        return insumoDpnDAOImplement.guardarActualizar(insumoDpn);
    }
    
    public List<InsumoDpn> getall() {
        return insumoDpnDAOImplement.getall();
    }
    
    public List<InsumoDpn> getByInsumoDpn(InsumoDpn insumoDpn) {
        return insumoDpnDAOImplement.getByInsumoDpn(insumoDpn);
    }
    
    public InsumoDpn getByIdInsumoDpn(Integer idInsumoDpn) {
        return insumoDpnDAOImplement.getByIdInsumoDpn(idInsumoDpn);
    }

}
