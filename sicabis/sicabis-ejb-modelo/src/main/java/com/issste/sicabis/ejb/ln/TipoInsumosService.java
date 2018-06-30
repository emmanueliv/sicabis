/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.issste.sicabis.ejb.ln;

import com.issste.sicabis.ejb.DAO.TipoInsumosDAO;
import com.issste.sicabis.ejb.modelo.TipoInsumos;
import java.util.List;
import javax.ejb.EJB;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;

/**
 *
 * @author
 */
@Stateless
@LocalBean
public class TipoInsumosService {

    @EJB
    private TipoInsumosDAO tipoInsumosDAOImplement;

    public List<TipoInsumos> listTipoInsumos() {
        return tipoInsumosDAOImplement.listTipoInsumos();
    }
}
