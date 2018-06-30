/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.issste.sicabis.ejb.DAO;

import com.issste.sicabis.ejb.modelo.InsumosRcbSolicitudInvestigacionMercado;
import com.issste.sicabis.ejb.modelo.RcbInsumos;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author erik
 */
@Local
public interface InsumosRcbSolicitudInvestigacionMercadoDAO {
    List<InsumosRcbSolicitudInvestigacionMercado> traerTodasLasSolicitudes();
    List<InsumosRcbSolicitudInvestigacionMercado> traerLasSolicitudesPorIdRcbInsumo(Integer idRcbInsumos);
    InsumosRcbSolicitudInvestigacionMercado guardarInsumosSolicitud(InsumosRcbSolicitudInvestigacionMercado insumoSolicitud);
    Integer borrarInsumosRcbSolicitudPorRcbInsumo(RcbInsumos rcbInsumo);
    
    
    
}
