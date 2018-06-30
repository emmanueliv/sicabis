/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.issste.sicabis.ejb.DAO;

import com.issste.sicabis.ejb.modelo.Fabricante;
import com.issste.sicabis.ejb.modelo.Proveedores;
import com.issste.sicabis.ejb.modelo.RcbInsumos;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author erik
 */
@Local
public interface RcbInsumosDAO {
    
    List<RcbInsumos> getListRCBInsumosByidRCB(Integer idRcb);
    RcbInsumos getRcbInsumoById(Integer idRcbInsumos);
    List<Proveedores> getProveedores();
    List<Fabricante> getFabricantes();
    List<RcbInsumos> getListClavesByNumeroTipoCompra(String numeroRcb, int tipoCompra);
    List<RcbInsumos> getListClavesProcByNumeroTipoCompra(String numeroRcb, int tipoCompra);
    Integer traerMaxRCBPorArea(Integer idArea,Integer idTipoCompra);
    Integer traerMaxRCBPorAreaGrupo(Integer idArea,Integer idTipoCompra,Integer idGrupo);
    Integer deleteRcbInsumosByIdRcb(Integer idRcb);
    Integer deleteRcbInsumos(RcbInsumos rcbInsumo);
    RcbInsumos guardarRcbInsumo(RcbInsumos rcbInsumo);
    RcbInsumos actualizarRcbInsumo(RcbInsumos rcbInsumo);

}
