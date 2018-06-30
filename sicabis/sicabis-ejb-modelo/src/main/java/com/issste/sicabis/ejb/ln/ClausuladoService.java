/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.issste.sicabis.ejb.ln;

import com.issste.sicabis.ejb.DAO.ClausuladoDAO;
import com.issste.sicabis.ejb.modelo.Clausulado;
import java.util.List;
import javax.ejb.EJB;
import javax.ejb.Stateless;

/**
 *
 * @author kriosoft
 */
@Stateless
public class ClausuladoService {

    @EJB
    ClausuladoDAO clausuladoDAO;

    public List<Clausulado> obtenerClausulados() {
        return clausuladoDAO.obtenerClausulados();
    }

    public Clausulado obtenerClausuladoByTipo(Integer tipo) {
        List<Clausulado> clList = clausuladoDAO.obtenerClausuladoByTipo(tipo);
        if (clList != null && !clList.isEmpty()) {
            return clList.get(0);
        }
        return null;
    }

    public void modificarClausulado(Clausulado clausulado) {
        clausuladoDAO.modificarClausulado(clausulado);
    }

}
