package com.issste.sicabis.ejb.ln;

import com.issste.sicabis.ejb.DAO.DpnInsumosDAO;
import com.issste.sicabis.ejb.modelo.Dpn;
import com.issste.sicabis.ejb.modelo.DpnInsumoTmp;
import com.issste.sicabis.ejb.modelo.DpnInsumos;
import java.util.Date;
import java.util.List;
import javax.ejb.EJB;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;

@Stateless
@LocalBean
public class DpnInsumosService {

    @EJB
    private DpnInsumosDAO dpnInsumosDAOImplement;

    public boolean guardaActualiza(DpnInsumos dpnInsumos) {
        return dpnInsumosDAOImplement.guardaActualiza(dpnInsumos);
    }

    public List<DpnInsumos> getListaAll(String clave, String claveUmu) {
        return dpnInsumosDAOImplement.getListaAll(clave, claveUmu);
    }

    public DpnInsumos getByIdDpnClaveUmu(String clave, String claveUmu, Integer previo) {
        return dpnInsumosDAOImplement.getByIdDpnClaveUmu(clave, claveUmu, previo);
    }

    public boolean eliminaPrevio() {
        return dpnInsumosDAOImplement.eliminaPrevio();
    }

    public Integer getByDpnByInsumo(String clave) {
        return dpnInsumosDAOImplement.getByDpnByInsumo(clave);
    }

    public Integer getBySumDpnByInsumo(String clave,String clavePresupuestal) {
        return dpnInsumosDAOImplement.getBySumDpnByInsumo(clave,clavePresupuestal);
    }

    public DpnInsumos getUltimaDpnByUnidadClave(String clave, String claveUmu) {
        return dpnInsumosDAOImplement.getUltimaDpnByUnidadClave(clave, claveUmu);
    }

    public List<DpnInsumos> llenaDpnInsumos(Dpn idDpn, String clave, String claveUmu) {
        return dpnInsumosDAOImplement.llenaDpnInsumos(idDpn, clave, claveUmu);
    }

    public boolean actualizaDpnInsumosByProcedure(String clave, String claveUmu, Date fechaInicio, Date fechaFin, Date fechaInicio2, Date fechaInicio3) {
        return dpnInsumosDAOImplement.actualizaDpnInsumosByProcedure(clave, claveUmu, fechaInicio, fechaFin, fechaInicio2, fechaInicio3);
    }

    public List<DpnInsumos> getByPrevio(Integer previo) {
        return dpnInsumosDAOImplement.getByPrevio(previo);
    }

    public boolean actualizaIdDpnPrevio(Dpn dpn) {
        return dpnInsumosDAOImplement.actualizaIdDpnPrevio(dpn);
    }

    public List<DpnInsumos> getListaDpnInsumos(Integer idDpn, String clave, String claveUmu) {
        return dpnInsumosDAOImplement.getListaDpnInsumos(idDpn, clave, claveUmu);
    }

    public boolean actualizaDpnInsumos() {
        return dpnInsumosDAOImplement.actualizaDpnInsumos();
    }

    public List<DpnInsumos> llenaDpnInsumosAnterior(Dpn idDpn, String clave, String claveUmu) {
        return dpnInsumosDAOImplement.llenaDpnInsumosAnterior(idDpn, clave, claveUmu);
    }

    public List<DpnInsumos> llenaDpnInsumosActivos(Dpn idDpn, String clave, String claveUmu) {
        return dpnInsumosDAOImplement.llenaDpnInsumosActivos(idDpn, clave, claveUmu);
    }

    public List<DpnInsumos> getByUnidadMedicaOrDelegacion(Integer idUnidadMedica, Integer idDelegacion) {
        return dpnInsumosDAOImplement.getByUnidadMedicaOrDelegacion(idUnidadMedica, idDelegacion);
    }
    
    public DpnInsumos getByIdDpnClaveInsumoClaveUnidad(Integer idDpn, String clave, String claveUmu) {
        return dpnInsumosDAOImplement.getByIdDpnClaveInsumoClaveUnidad(idDpn, clave, claveUmu);
    }
    
    public boolean guardaTmp(DpnInsumoTmp dpnInsumoTmp) {
        return dpnInsumosDAOImplement.guardaTmp(dpnInsumoTmp);
    }
    
}
