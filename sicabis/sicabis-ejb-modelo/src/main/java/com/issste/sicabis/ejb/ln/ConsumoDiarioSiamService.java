package com.issste.sicabis.ejb.ln;

import com.issste.sicabis.ejb.DAO.ConsumoDiarioSiamDAO;
import com.issste.sicabis.ejb.modelo.ConsumoDiarioSiam;
import java.sql.ResultSet;
import java.util.Date;
import java.util.List;
import javax.ejb.EJB;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;

@LocalBean
@Stateless
public class ConsumoDiarioSiamService {

    @EJB
    private ConsumoDiarioSiamDAO consumoDiarioSiamDAOImplement;

    public boolean guarda(ConsumoDiarioSiam cds) {
        return consumoDiarioSiamDAOImplement.guarda(cds);
    }

    public boolean eliminaTodo() {
        return consumoDiarioSiamDAOImplement.eliminaTodo();
    }
    
    public Integer sumaConsumo(String claveInsumo, String claveUnidad, Date fechaInicio, Date fechaFin) {
        return consumoDiarioSiamDAOImplement.sumaConsumo(claveInsumo, claveUnidad, fechaInicio, fechaFin);
    }
    
    public boolean eliminaFecha(Date fecha, Date fechaFin) {
        return consumoDiarioSiamDAOImplement.eliminaFecha(fecha, fechaFin);
    }
    
    public ResultSet ejecutaQuery(String query) {
        return consumoDiarioSiamDAOImplement.ejecutaQuery(query);
    }
    
    public int ejecutaUpdate(String query) {
        return consumoDiarioSiamDAOImplement.ejecutaUpdate(query);
    }
    
    public List<ConsumoDiarioSiam> getByFechas(String fechaIni, String fechaFin) {
        return consumoDiarioSiamDAOImplement.getByFechas(fechaIni, fechaFin);
    }

}
