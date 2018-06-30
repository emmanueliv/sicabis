/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.issste.sicabis.ejb.ln;

import com.issste.sicabis.ejb.DAO.RcbInsumosDAO;
import com.issste.sicabis.ejb.modelo.Fabricante;
import com.issste.sicabis.ejb.modelo.Proveedores;
import com.issste.sicabis.ejb.modelo.RcbInsumos;
import java.util.List;
import javax.ejb.EJB;
import javax.ejb.Stateful;

/**
 *
 * @author erik
 */
@Stateful
public class RcbInsumosService {
    @EJB
    private RcbInsumosDAO rcbInsumosDAO;
    
    public List<RcbInsumos> getListRCBInsumosByidRCB(Integer idRcb) {
        return rcbInsumosDAO.getListRCBInsumosByidRCB(idRcb);
    }
    
    public RcbInsumos getRcbInsumosById(Integer idRcbInsumos) {
      return rcbInsumosDAO.getRcbInsumoById(idRcbInsumos);
    }
    
    
    public List<Proveedores> getProveedores() {
        return rcbInsumosDAO.getProveedores();
    }

    public List<Fabricante> getFabricantes() {
        return rcbInsumosDAO.getFabricantes();
    } 
    
    public List<RcbInsumos> getListClavesByNumeroTipoCompra(String numeroRcb, int tipoCompra){
        return rcbInsumosDAO.getListClavesByNumeroTipoCompra(numeroRcb, tipoCompra);
    }
    
    public List<RcbInsumos> getListClavesProcByNumeroTipoCompra(String numeroRcb, int tipoCompra){
        return rcbInsumosDAO.getListClavesProcByNumeroTipoCompra(numeroRcb, tipoCompra);
    }
    
    public Integer borrarInsumosRCBPorIdRcb(Integer idRcb) {
        return rcbInsumosDAO.deleteRcbInsumosByIdRcb(idRcb);
    }
    
    public RcbInsumos guardarInsumosRCBPorIdRCB(RcbInsumos rcbInsumos){
        return rcbInsumosDAO.guardarRcbInsumo(rcbInsumos);
    }
    
    public RcbInsumos actualizarRcbInsumo(RcbInsumos rcbInsumo) {
        return rcbInsumosDAO.actualizarRcbInsumo(rcbInsumo);
    }
    
    public Integer borrarRcbInsumo(RcbInsumos rcbInsumo) {
        return rcbInsumosDAO.deleteRcbInsumos(rcbInsumo);
    }
    
    public Integer traerUltimaRcbPorArea(Integer idArea,Integer idTipoCompra) {
        return rcbInsumosDAO.traerMaxRCBPorArea(idArea,idTipoCompra);
    }
    
    public Integer traerUltimaRcbPorAreaGrupo(Integer idArea,Integer idTipoCompra,Integer idGrupo) {
        return rcbInsumosDAO.traerMaxRCBPorAreaGrupo(idArea,idTipoCompra,idGrupo);
    }
}
