/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.issste.sicabis.ejb.DAO;

import com.issste.sicabis.ejb.modelo.Area;
import com.issste.sicabis.ejb.modelo.UsuarioPerfil;
import com.issste.sicabis.ejb.modelo.Usuarios;
import com.issste.sicabis.ejb.modelo.UsuariosTipoUsuarios;
import java.util.List;
import javax.ejb.Local;

@Local
public interface UsuariosDAO {

    Usuarios getByUsuario(String usuario);

    List<Usuarios> getUsuarioAreayUsuario(String usuario, Integer idArea,Integer idSuperior);

    void guardarUsuario(Usuarios usuarioNuevo);

    List<Area> getAreas();

    List<UsuarioPerfil> getUsuariosByPerfil(Integer perfil);

    List<Usuarios> getUsuarios();

    List<Usuarios> getUsuariosByIdPadre(int idSuperior);

    void guardarUsuarioPerfil(UsuarioPerfil usuarioPerfil);

    UsuarioPerfil getUsuarioPerfil(int idUsuario);

    Usuarios getByIdUsuario(int idUsuario);

    List<Usuarios> getByIdUnidadesMedicas(Integer idUnidadMedica);

    List<Usuarios> getByIdDelegacion(Integer idDelegacion);

    void guardarUsuariosTipoUsuarios(UsuariosTipoUsuarios usuariosTipoUsuarios);

    List<UsuariosTipoUsuarios> getUsuariosByTipoUsuario(int idTipoUsuario);

    List<UsuariosTipoUsuarios> obtenerTiposUsuariosByIdUsuario(Integer idUsuario);
    
    boolean deleteByIdUsuario(Integer idUsuario);
    
    List<UsuariosTipoUsuarios> getByIdUsuarioIdTipoUsuario(Integer idUsuario, Integer idTipoUsuario);

}
