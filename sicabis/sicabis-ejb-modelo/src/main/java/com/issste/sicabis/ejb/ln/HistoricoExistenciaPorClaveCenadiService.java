package com.issste.sicabis.ejb.ln;

import com.issste.sicabis.ejb.DAO.HistoricoExistenciaPorClaveCenadiDAO;
import com.issste.sicabis.ejb.modelo.HistoricoExistenciaPorClaveCenadi;
import java.util.Date;
import java.util.List;
import javax.ejb.EJB;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;

@LocalBean
@Stateless
public class HistoricoExistenciaPorClaveCenadiService {

    @EJB
    private HistoricoExistenciaPorClaveCenadiDAO historicoExistenciaPorClaveCenadiDAOImplement;

    public boolean guardar(HistoricoExistenciaPorClaveCenadi hepcc) {
        return historicoExistenciaPorClaveCenadiDAOImplement.guardar(hepcc);
    }

    public List<HistoricoExistenciaPorClaveCenadi> getByFechaIngreso(Date fechaIngreso) {
        return historicoExistenciaPorClaveCenadiDAOImplement.getByFechaIngreso(fechaIngreso);
    }

    public List<HistoricoExistenciaPorClaveCenadi> getAll() {
        return historicoExistenciaPorClaveCenadiDAOImplement.getAll();
    }

    public List<HistoricoExistenciaPorClaveCenadi> getByFiltros(Date fechaInicio, Date fechaFin, String tipoClave, String clave,String clave2,String subinventario,String localizador,String lote){
    return  historicoExistenciaPorClaveCenadiDAOImplement.getByFiltros(fechaInicio, fechaFin, tipoClave, clave, clave2,subinventario, localizador,lote);
    }
    
    public List<String> getDistinctSubinventario() {
        return historicoExistenciaPorClaveCenadiDAOImplement.getDistinctSubinventario();
    }
    
    public List<String> getDistinctLocalizador() {
        return historicoExistenciaPorClaveCenadiDAOImplement.getDistinctLocalizador();
    }

}
