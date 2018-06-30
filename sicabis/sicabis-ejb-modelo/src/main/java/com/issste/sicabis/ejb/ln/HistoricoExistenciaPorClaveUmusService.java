package com.issste.sicabis.ejb.ln;

import com.issste.sicabis.ejb.DAO.HistoricoExistenciaPorClaveUmusDAO;
import com.issste.sicabis.ejb.DTO.ExistenciaClaveUmuDTO;
import com.issste.sicabis.ejb.DTO.HistorialExisteciaClaveUmuDTO;
import com.issste.sicabis.ejb.modelo.HistoricoExistenciaPorClaveUmus;
import java.util.Date;
import java.util.List;
import javax.ejb.EJB;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;

@Stateless
@LocalBean
public class HistoricoExistenciaPorClaveUmusService {

    @EJB
    private HistoricoExistenciaPorClaveUmusDAO historicoExistenciaPorClaveUmusDAOImplement;

    public boolean guardar(HistoricoExistenciaPorClaveUmus hepcu) {
        return historicoExistenciaPorClaveUmusDAOImplement.guardar(hepcu);
    }

    public List<HistoricoExistenciaPorClaveUmus> getByFechaIngreso(Date fechaIngreso) {
        return historicoExistenciaPorClaveUmusDAOImplement.getByFechaIngreso(fechaIngreso);
    }

    public List<HistoricoExistenciaPorClaveUmus> getByFiltros(Date fechaInicio, Date fechaFin, String delegacion, String numeroUmu, String nombreUmu, String clave, String clave2, String tipo) {
        return historicoExistenciaPorClaveUmusDAOImplement.getByFiltros(fechaInicio, fechaFin, delegacion, numeroUmu, nombreUmu, clave, clave2, tipo);
    }

    public List<HistorialExisteciaClaveUmuDTO> getAll() {
        return historicoExistenciaPorClaveUmusDAOImplement.getAll();
    }

    public List<HistoricoExistenciaPorClaveUmus> getAllDetalle() {
        return historicoExistenciaPorClaveUmusDAOImplement.getAllDetalle();
    }

    public List<HistoricoExistenciaPorClaveUmus> getByFiltrosDetalle(Date fechaInicio, Date fechaFin, String delegacion, String numeroUmu, String nombreUmu, String clave, String clave2, String lote) {
        return historicoExistenciaPorClaveUmusDAOImplement.getByFiltrosDetalle(fechaInicio, fechaFin, delegacion, numeroUmu, nombreUmu, clave, clave2, lote);
    }
    
    public List<String> getDistinctTipo() {
        return historicoExistenciaPorClaveUmusDAOImplement.getDistinctTipo();
    }
    
    public List<String> getDistinctNumeroUmu() {
        return historicoExistenciaPorClaveUmusDAOImplement.getDistinctNumeroUmu();
    }
    
    public List<String> getDistinctNombreUmu() {
        return historicoExistenciaPorClaveUmusDAOImplement.getDistinctNombreUmu();
    }
    
}
