package com.issste.sicabis.ejb.ln;

import com.issste.sicabis.ejb.DAO.TipoInsumoDpnDAO;
import com.issste.sicabis.ejb.modelo.TipoInsumoDpn;
import java.util.List;
import javax.ejb.EJB;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;

@LocalBean
@Stateless
public class TipoInsumoDpnService {

    @EJB
    private TipoInsumoDpnDAO tipoInsumoDpnDAOImplement;
    
    public List<TipoInsumoDpn> getAll() {
        return tipoInsumoDpnDAOImplement.getAll();
    }
    
    public TipoInsumoDpn getById(Integer idTipoInsumoDpn) {
        return tipoInsumoDpnDAOImplement.getById(idTipoInsumoDpn);
    }

}
