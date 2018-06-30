/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.issste.sicabis.ejb.ln;

import com.issste.sicabis.ejb.DAO.GrupoTerapeuticoDAO;
import com.issste.sicabis.ejb.modelo.GrupoTerapeutico;
import com.issste.sicabis.ejb.modelo.TipoSolicitud;
import java.util.List;
import javax.ejb.EJB;
import javax.ejb.Stateless;

/**
 *
 * @author kriosoft
 */
@Stateless
public class GrupoTerapeuticoService {

    @EJB
    GrupoTerapeuticoDAO grupoTerapeuticoDAOImpl;

    public List<GrupoTerapeutico> obtenerGruposTerapeuticos() {
        return grupoTerapeuticoDAOImpl.getGruposTerapeuticos();
    }

    public GrupoTerapeutico obtenerGPByNombre(String nombre) {
        List<GrupoTerapeutico> list
                = grupoTerapeuticoDAOImpl.obtenerGpTerapeuticoByNombre(nombre);
        if (list.isEmpty()) {
            return null;
        } else {
            return list.get(0);
        }
    }

    public void guardarGruposTerapeuticos(GrupoTerapeutico gp) {
        grupoTerapeuticoDAOImpl.guardarGrupoTerapeutico(gp);
    }

}
