/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.issste.sicabis.ejb.ln;

import com.issste.sicabis.ejb.DAO.AreaDAO;
import com.issste.sicabis.ejb.DAO.UsuariosDAO;
import com.issste.sicabis.ejb.modelo.Area;
import com.issste.sicabis.ejb.modelo.TipoUsuarios;
import com.issste.sicabis.ejb.modelo.UsuarioPerfil;
import com.issste.sicabis.ejb.modelo.Usuarios;
import com.issste.sicabis.ejb.modelo.UsuariosTipoUsuarios;
import java.util.List;
import javax.ejb.EJB;
import javax.ejb.Stateless;

/**
 *
 * @author Toshiba Manolo
 */
@Stateless
public class UsuariosService {

    @EJB
    private UsuariosDAO userDAOImp;

    @EJB
    private AreaDAO areaDAOImpl;

    private Usuarios usuarios;

    public Usuarios obtenerUsuario(String usuario) {
        usuarios = null;
        usuarios = userDAOImp.getByUsuario(usuario);
        return usuarios;
    }

    public List<Usuarios> getUsuarioAreayUsuario(String usuario, Integer idArea, Integer idSuperior) {
        return userDAOImp.getUsuarioAreayUsuario(usuario, idArea, idSuperior);
    }

    public List<UsuarioPerfil> getUsuariosByPerfil(Integer perfil) {
        return userDAOImp.getUsuariosByPerfil(perfil);
    }

    public void guardarUsuario(Usuarios usuarioNuevo, UsuarioPerfil usuarioPerfil, List<String> listaTipoUsuarios, Integer opcion) {
        try {
            userDAOImp.guardarUsuario(usuarioNuevo);
            if (usuarioPerfil != null) {
                usuarioPerfil.setIdUsuario(obtenerUsuario(usuarioNuevo.getUsuario()));
                userDAOImp.guardarUsuarioPerfil(usuarioPerfil);
            }
            if(opcion == 0){
                this.deleteByIdUsuario(usuarioNuevo.getIdUsuario());
            }
            if (listaTipoUsuarios != null && listaTipoUsuarios.size() > 0) {
                //this.deleteByIdUsuario(usuarioNuevo.getIdUsuario());
                for (String iterator : listaTipoUsuarios) {
                    //Usuario guardado tipo
                    UsuariosTipoUsuarios usuariosTipoUsuarios = new UsuariosTipoUsuarios();
                    usuariosTipoUsuarios.setActivo(1);
                    usuariosTipoUsuarios.setIdTipoUsuario(new TipoUsuarios(Integer.parseInt(iterator)));
                    usuariosTipoUsuarios.setIdUsuario(obtenerUsuario(usuarioNuevo.getUsuario()));
                    userDAOImp.guardarUsuariosTipoUsuarios(usuariosTipoUsuarios);
                }

            }
        } catch (Exception e) {

        }

    }

    public List<Area> getAreas() {
        return userDAOImp.getAreas();
    }

    public List<Usuarios> getUsuarios() {
        return userDAOImp.getUsuarios();
    }

    public List<Usuarios> getUsuariosByIdPadre(int idSuperior) {
        return userDAOImp.getUsuariosByIdPadre(idSuperior);
    }

    public UsuarioPerfil getUsuarioPerfil(int idUsuario) {
        return userDAOImp.getUsuarioPerfil(idUsuario);
    }

    public Usuarios getByIdUsuario(int idUsuario) {
        return userDAOImp.getByIdUsuario(idUsuario);
    }

    public List<Usuarios> getByIdUnidadesMedicas(Integer idUnidadMedica) {
        return userDAOImp.getByIdUnidadesMedicas(idUnidadMedica);
    }

    public List<Usuarios> getByIdDelegacion(Integer idDelegacion) {
        return userDAOImp.getByIdDelegacion(idDelegacion);
    }

    public void guardarUsuariosTipoUsuarios(UsuariosTipoUsuarios usuariosTipoUsuarios) {
        userDAOImp.guardarUsuariosTipoUsuarios(usuariosTipoUsuarios);
    }

    public List<UsuariosTipoUsuarios> getUsuariosByTipoUsuario(int idTipoUsuario) {
        return userDAOImp.getUsuariosByTipoUsuario(idTipoUsuario);
    }

    public List<UsuariosTipoUsuarios> obtenerTiposUsuariosByIdUsuario(Integer idUsuario) {
        return userDAOImp.obtenerTiposUsuariosByIdUsuario(idUsuario);
    }

    public boolean deleteByIdUsuario(Integer idUsuario) {
        return userDAOImp.deleteByIdUsuario(idUsuario);
    }
    
    public List<UsuariosTipoUsuarios> getByIdUsuarioIdTipoUsuario(Integer idUsuario, Integer idTipoUsuario) {
        return userDAOImp.getByIdUsuarioIdTipoUsuario(idUsuario, idTipoUsuario);
    }

}
