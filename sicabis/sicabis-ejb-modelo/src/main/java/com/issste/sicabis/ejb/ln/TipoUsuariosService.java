/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.issste.sicabis.ejb.ln;

import com.issste.sicabis.ejb.DAO.TipoUsuariosDAO;
import com.issste.sicabis.ejb.modelo.TipoUsuarios;
import java.util.List;
import javax.ejb.EJB;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;

/**
 *
 * @author 9RZCBG2
 */
@Stateless
@LocalBean
public class TipoUsuariosService {

    @EJB
    private TipoUsuariosDAO tipoUsuariosDAOImplement;

    public void guadarActualizar(TipoUsuarios tipoUsuario) {
        tipoUsuariosDAOImplement.guadarActualizar(tipoUsuario);
    }

    public List<TipoUsuarios> obtenerListaTiposUsuarios(String nombre) {
        return tipoUsuariosDAOImplement.obtenerListaTiposUsuarios(nombre);
    }

    public TipoUsuarios obtenerTipoUsuarioByNombre(String nombre) {
        return tipoUsuariosDAOImplement.obtenerTipoUsuarioByNombre(nombre);
    }
}
