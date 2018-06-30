package com.issste.sicabis.ejb.DAO;

import com.issste.sicabis.ejb.modelo.Dpn;
import com.issste.sicabis.ejb.modelo.DpnInsumoTmp;
import com.issste.sicabis.ejb.modelo.DpnInsumos;
import java.util.Date;
import java.util.List;
import javax.ejb.Local;

@Local
public interface DpnInsumosDAO {

    boolean guardaActualiza(DpnInsumos dpnInsumos);

    List<DpnInsumos> getListaAll(String clave, String claveUmu);

    DpnInsumos getByIdDpnClaveUmu(String clave, String claveUmu, Integer previo);

    boolean eliminaPrevio();

    Integer getBySumDpnByInsumo(String clave,String clavePresupuestal);

    Integer getByDpnByInsumo(String clave);

    DpnInsumos getUltimaDpnByUnidadClave(String clave, String claveUmu);

    List<DpnInsumos> llenaDpnInsumos(Dpn idDpn, String clave, String claveUmu);

    boolean actualizaDpnInsumosByProcedure(String clave, String claveUmu, Date fechaInicio, Date fechaFin, Date fechaInicio2, Date fechaInicio3);

    List<DpnInsumos> getByPrevio(Integer previo);

    boolean actualizaIdDpnPrevio(Dpn dpn);

    List<DpnInsumos> getListaDpnInsumos(Integer idDpn, String clave, String claveUmu);

    boolean actualizaDpnInsumos();

    List<DpnInsumos> llenaDpnInsumosAnterior(Dpn idDpn, String clave, String claveUmu);
    
    List<DpnInsumos> llenaDpnInsumosActivos(Dpn idDpn, String clave, String claveUmu);
    
    List<DpnInsumos> getByUnidadMedicaOrDelegacion(Integer idUnidadMedica, Integer idDelegacion);
    
    DpnInsumos getByIdDpnClaveInsumoClaveUnidad(Integer idDpn, String clave, String claveUmu);
    
    boolean guardaTmp(DpnInsumoTmp dpnInsumoTmp);
    
}
