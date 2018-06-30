package com.issste.sicabis.ejb.DAO;

import com.issste.sicabis.ejb.DTO.ExistenciaClaveUmuDTO;
import com.issste.sicabis.ejb.DTO.HistorialExisteciaClaveUmuDTO;
import com.issste.sicabis.ejb.modelo.HistoricoExistenciaPorClaveUmus;
import java.util.Date;
import java.util.List;
import javax.ejb.Local;

@Local
public interface HistoricoExistenciaPorClaveUmusDAO {

    boolean guardar(HistoricoExistenciaPorClaveUmus hepcu);
    List<HistoricoExistenciaPorClaveUmus> getByFechaIngreso(Date fechaIngreso);
    List<HistoricoExistenciaPorClaveUmus> getByFiltros(Date fechaInicio, Date fechaFin, String delegacion, String numeroUmu, String nombreUmu, String clave, String clave2 ,String tipo);
    List<HistorialExisteciaClaveUmuDTO> getAll();
    List<HistoricoExistenciaPorClaveUmus> getAllDetalle();
    List<HistoricoExistenciaPorClaveUmus> getByFiltrosDetalle(Date fechaInicio, Date fechaFin, String delegacion, String numeroUmu, String nombreUmu, String clave, String clave2,String lote);
    List<String> getDistinctTipo();
    List<String> getDistinctNumeroUmu();
    List<String> getDistinctNombreUmu();
}
