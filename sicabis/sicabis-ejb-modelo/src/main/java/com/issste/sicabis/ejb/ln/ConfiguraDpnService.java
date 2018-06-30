package com.issste.sicabis.ejb.ln;

import com.issste.sicabis.ejb.DAO.ConfiguraDpnDAO;
import com.issste.sicabis.ejb.modelo.ConfiguraDpn;
import java.util.List;
import javax.ejb.EJB;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;

@Stateless
@LocalBean
public class ConfiguraDpnService {

    @EJB
    private ConfiguraDpnDAO configuraDpnDAOImplement;
    
    public boolean guardaConfiguraDpn(ConfiguraDpn configuraDpn) {
        return configuraDpnDAOImplement.guardaConfiguraDpn(configuraDpn);
    }
    
    public List<ConfiguraDpn> getAllByActivo(Integer activo) {
        return configuraDpnDAOImplement.getAllByActivo(activo);
    }

}
