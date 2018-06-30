/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.issste.sicabis.ejb.ln;

import com.issste.sicabis.ejb.DAO.RcbDAO;
import com.issste.sicabis.ejb.modelo.Rcb;
import java.util.List;
import javax.ejb.EJB;
import javax.ejb.Stateful;

/**
 *
 * @author erik
 */
@Stateful
public class RcbService {
    @EJB
    private RcbDAO rcbDAOImplemet;

    

    public List<Rcb> traeRcbList() {
        return rcbDAOImplemet.getAllRcb();
    }

    public List<Rcb> traeRcbListPorNumRcb(String numeroRcb) {
        System.out.println("traeRcbListPorNumRcb");
        return rcbDAOImplemet.getRcbByNumRcb(numeroRcb);
    }

    public Integer traeIdMaxRcb() {
        return rcbDAOImplemet.getMaxRCB();
    }

    public Integer traeIdAnteriorRcb(Integer idRcb) {
        Integer result = rcbDAOImplemet.getAnteriorRCB(idRcb);
        System.out.println("result anterior: " + result);
        return result;
    }

    public Rcb traerRcbId(Integer idRcb) {
        return rcbDAOImplemet.getRCBByID(idRcb);
    }

    public boolean guardarRcb(Rcb rcb) {
        return rcbDAOImplemet.save(rcb);
    }

    public Rcb actualizarRcbMerge(Rcb rcb) {
        rcbDAOImplemet.update(rcb);
        return rcb;
    }

    public Integer actualizaEstatusRCB(Rcb rcb) {
        Integer result = rcbDAOImplemet.updateEstatusRcb(rcb);
        return result;
    }

    public Integer actualizaTablaRCB(Rcb rcb) {
        Integer result = rcbDAOImplemet.updateRcb(rcb);
        return result;
    }

    public List<Rcb> traeRcbListPorEstatus(Integer idEstatus) {
        System.out.println("traeRcbListPorEstatus");
        return rcbDAOImplemet.getRcbByEstatus(idEstatus);
    }

    public List<Rcb> buscarRcbPorNumRcb(String numRcb,Integer idArea) {
        System.out.println("traeRcbListPorEstatus");
        return rcbDAOImplemet.buscaRcbPorNumRcb(numRcb.toUpperCase(),idArea);
    }

    public List<Rcb> buscarRcbPorCampos(Rcb rcb) {
        System.out.println("buscarRcbPorCampos");
        return rcbDAOImplemet.buscaRcbTypedQuery(rcb);
    }

    public Rcb getRCByNum(String numero) {
        return rcbDAOImplemet.getRCByNum(numero);
    }
    
    public List<Rcb> getRcbPendientes() {
        return rcbDAOImplemet.getRcbPendientes();
    }
    
}
