package com.issste.sicabis.ejb.ln;

import com.issste.sicabis.ejb.DAO.DpnDAO;
import com.issste.sicabis.ejb.modelo.Dpn;
import com.issste.sicabis.ejb.modelo.DpnInsumos;
import java.util.List;
import javax.ejb.EJB;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;

@Stateless
@LocalBean
public class DpnService {

    @EJB
    private DpnDAO dpnDAOImplement;

    public List<Dpn> getAllDesc() {
        return dpnDAOImplement.getAllDesc();
    }

    public List<Dpn> getByAnio(int anio) {
        return dpnDAOImplement.getByAnio(anio);
    }

    public boolean guardaActualiza(Dpn dpn) {
        return dpnDAOImplement.guardaActualiza(dpn);
    }

    public Dpn getDpnPrevio() {
        return dpnDAOImplement.getDpnPrevio();
    }

    public Dpn getByIdDpn(Integer idDpn) {
        return dpnDAOImplement.getByIdDpn(idDpn);
    }

    public Dpn getUltimaAutorizada() {
        return dpnDAOImplement.getUltimaAutorizada();
    }
    
    public boolean actualizaUltimaDpn() {
        return dpnDAOImplement.actualizaUltimaDpn();
    }
    
    public boolean actualizaDpn(Dpn dpn) {
        System.out.println("llegue al service");
        return dpnDAOImplement.actualizaDpn(dpn);
    }
    
    public boolean actualizaDpnInsumo(DpnInsumos di) {
        return dpnDAOImplement.actualizaDpnInsumo(di);
    }
    
    public Dpn getByAnioMesIdEstatus(Integer anio, Integer mes, Integer idEstatus) {
        return dpnDAOImplement.getByAnioMesIdEstatus(anio, mes, idEstatus);
    }
    
    public Dpn getByIdPeriodoMes(Integer idPeriodoMes) {
        return dpnDAOImplement.getByIdPeriodoMes(idPeriodoMes);
    }

}
