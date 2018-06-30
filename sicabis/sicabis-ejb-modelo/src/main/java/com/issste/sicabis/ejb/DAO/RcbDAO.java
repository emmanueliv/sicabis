/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.issste.sicabis.ejb.DAO;

import com.issste.sicabis.ejb.modelo.Rcb;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author erik
 */
@Local
public interface RcbDAO {
    
    List<Rcb> getAllRcb();
    List<Rcb> getRcbByNumRcb(String numeroRcb);
    List<Rcb> getRcbByEstatus(Integer idEstatus);
    List<Rcb> buscaRcbPorNumRcb(String numeroRcb,Integer idArea);
    List<Rcb> buscaRcbTypedQuery(Rcb rcb);
    Integer getMaxRCB();
    Integer getAnteriorRCB(Integer idRcb);
    Rcb getRCBByID(Integer idRcb);
    boolean save(Rcb rcb);
    Rcb update(Rcb rcb);
    Integer updateEstatusRcb(Rcb rcb);
    Integer updateRcb(Rcb rcb);
    Rcb getRCByNum(String idRcb);
    List<Rcb> getRcbProcedimiento();
    List<Rcb> getRcbPendientes();
    
}
