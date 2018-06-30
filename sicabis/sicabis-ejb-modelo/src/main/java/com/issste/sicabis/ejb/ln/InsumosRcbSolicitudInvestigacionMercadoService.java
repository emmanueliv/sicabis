/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.issste.sicabis.ejb.ln;

import com.issste.sicabis.ejb.DAO.InsumosRcbSolicitudInvestigacionMercadoDAO;
import com.issste.sicabis.ejb.modelo.InsumosRcbSolicitudInvestigacionMercado;
import com.issste.sicabis.ejb.modelo.RcbInsumos;
import java.util.List;
import javax.ejb.EJB;
import javax.ejb.Stateful;
import javax.persistence.Query;

/**
 *
 * @author erik
 */
@Stateful
public class InsumosRcbSolicitudInvestigacionMercadoService {
    
    @EJB
    private InsumosRcbSolicitudInvestigacionMercadoDAO insumosRcbSolicitudDAO;
    
    public List<InsumosRcbSolicitudInvestigacionMercado> traerTodasLasSolicitudes() {
        return insumosRcbSolicitudDAO.traerTodasLasSolicitudes();
    }


    public InsumosRcbSolicitudInvestigacionMercado guardarInsumosSolicitud(InsumosRcbSolicitudInvestigacionMercado insumoSolicitud) {
        return insumosRcbSolicitudDAO.guardarInsumosSolicitud(insumoSolicitud);
    }


    public List<InsumosRcbSolicitudInvestigacionMercado> traerLasSolicitudesPorIdRcbInsumo(Integer idRcbInsumos) {
        return insumosRcbSolicitudDAO.traerLasSolicitudesPorIdRcbInsumo(idRcbInsumos);
    }
    
    public Integer borrarInsumosRcbSolicitudPorRcbInsumo(RcbInsumos rcbInsumo) {          
       return insumosRcbSolicitudDAO.borrarInsumosRcbSolicitudPorRcbInsumo(rcbInsumo);
    }
    
}
