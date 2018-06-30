/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.issste.sicabis.ejb.ln;

import com.issste.sicabis.ejb.DAO.LoteDAO;
import com.issste.sicabis.ejb.modelo.Lote;
import java.util.Date;
import java.util.List;
import javax.ejb.EJB;
import javax.ejb.Stateless;

/**
 *
 * @author fabianvr
 */
@Stateless
public class LoteService {

    @EJB
    private LoteDAO loteDAOImplement;

    public LoteService() {

    }

    public Date loteByClave(String clave, String lote) {
        return loteDAOImplement.loteByClave(clave, lote);
    }

    public List<Lote> traeListaLotes() {
        return loteDAOImplement.buscarLotes();
    }

    public void guardarLote(Lote lote) {
        loteDAOImplement.guardarLotes(lote);
    }

    public Lote obtenerLoteByNombre(String nombreLote) {
        List<Lote> lista = loteDAOImplement.getLoteByCve(nombreLote);
        if (lista.isEmpty()) {
            return null;
        } else {
            return lista.get(0);
        }
    }

    public List<Lote> lote(String lote) {
        return loteDAOImplement.lote(lote);
    }

    public List<Lote> loteByR(Integer idRemision) {
        return loteDAOImplement.loteByRemision(idRemision);
    }
    
    public List<Lote> getloteByLoteInsumo(String lote, String clave) {
        return loteDAOImplement.loteByLoteInsumo(lote, clave);
    }
    
    public List<Lote> getLoteByCve(String nombreLote) {
        return loteDAOImplement.getLoteByCve(nombreLote);
    }
    
    public Lote getByIdLote(Integer idLote) {
        return loteDAOImplement.getByIdLote(idLote);
    }

}
