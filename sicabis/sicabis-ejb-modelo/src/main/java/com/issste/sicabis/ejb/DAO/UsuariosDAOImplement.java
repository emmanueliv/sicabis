package com.issste.sicabis.ejb.DAO;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
import com.issste.sicabis.ejb.modelo.Area;
import com.issste.sicabis.ejb.modelo.TipoUsuarios;
import com.issste.sicabis.ejb.modelo.UsuarioPerfil;
import com.issste.sicabis.ejb.modelo.Usuarios;
import com.issste.sicabis.ejb.modelo.UsuariosTipoUsuarios;
import com.issste.sicabis.ejb.utils.ErrorUtil;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@Stateless
public class UsuariosDAOImplement implements UsuariosDAO {

    @PersistenceContext(unitName = "sicabis")
    private EntityManager em;

    @Override
    public Usuarios getByUsuario(String usuario) {
        List<Usuarios> resultList = null;
        try {
            resultList = em.createQuery("SELECT u FROM Usuarios u WHERE u.usuario = :usuario").setParameter("usuario", usuario).getResultList();
        } catch (Exception e) {
            Logger.getLogger(UsuariosDAOImplement.class.getName()).log(Level.SEVERE, ErrorUtil.CODE_0101, e);
        }
        if (resultList != null && resultList.size() > 0) {
            return resultList.get(0);
        }
        return null;
    }

    @Override
    public void guardarUsuario(Usuarios usuarioNuevo) {
        try {
            if (usuarioNuevo.getIdUsuario() == null) {
                em.persist(usuarioNuevo);
            } else {
                em.merge(usuarioNuevo);
            }
        } catch (Exception e) {
            Logger.getLogger(UsuariosDAOImplement.class.getName()).log(Level.SEVERE, ErrorUtil.CODE_0101, e);
        }

    }

    @Override
    public void guardarUsuariosTipoUsuarios(UsuariosTipoUsuarios usuariosTipoUsuarios) {
        try {
            if (usuariosTipoUsuarios.getIdUsuariosTipoUsuarios() == 0) {
                em.persist(usuariosTipoUsuarios);
            } else {
                em.merge(usuariosTipoUsuarios);
            }
        } catch (Exception e) {
            Logger.getLogger(UsuariosDAOImplement.class.getName()).log(Level.SEVERE, ErrorUtil.CODE_0101, e);
        }

    }

    @Override
    public List<Usuarios> getUsuarios() {
        List<Usuarios> resultList = null;
        try {
            resultList = em.createQuery("SELECT u FROM Usuarios u WHERE u.activo = 1").getResultList();
        } catch (Exception e) {
            Logger.getLogger(UsuariosDAOImplement.class.getName()).log(Level.SEVERE, ErrorUtil.CODE_0101, e);
        }
        return resultList;
    }

    @Override
    public List<UsuarioPerfil> getUsuariosByPerfil(Integer perfil) {
        List<UsuarioPerfil> resultList = null;
        try {
            resultList = em.createQuery("SELECT uf FROM UsuarioPerfil uf WHERE uf.idPerfil.idPerfil = :idPerfil").setParameter("idPerfil", perfil).getResultList();
        } catch (Exception e) {
            Logger.getLogger(UsuariosDAOImplement.class.getName()).log(Level.SEVERE, ErrorUtil.CODE_0101, e);
        }
        return resultList;
    }

    @Override
    public List<Usuarios> getUsuariosByIdPadre(int idSuperior) {
        List<Usuarios> resultList = null;
        try {
            resultList = em.createQuery("Select u From Usuarios u WHERE u.idUsuarioSuperior = :idSuperior").setParameter("idSuperior", idSuperior).getResultList();
        } catch (Exception e) {
            Logger.getLogger(UsuariosDAOImplement.class.getName()).log(Level.SEVERE, ErrorUtil.CODE_0101, e);
        }
        return resultList;
    }

    @Override
    public List<UsuariosTipoUsuarios> getUsuariosByTipoUsuario(int idTipoUsuario) {
        List<UsuariosTipoUsuarios> resultList = null;
        try {
            String query = "Select utu From UsuariosTipoUsuarios utu "
                    + "JOIN utu.idUsuario u "
                    + "JOIN utu.idTipoUsuario tu ";
            if (idTipoUsuario != 0) {
                query = query + "WHERE utu.idTipoUsuario.idTipoUsuario = :idTipoUsuario";
                resultList = em.createQuery(query)
                        .setParameter("idTipoUsuario", idTipoUsuario).getResultList();
            } else {
                resultList = em.createQuery(query).getResultList();
            }
        } catch (Exception e) {
            Logger.getLogger(UsuariosDAOImplement.class.getName()).log(Level.SEVERE, ErrorUtil.CODE_0101, e);
        }
        return resultList;
    }

    @Override
    public List<Area> getAreas() {
        List<Area> resultList = null;
        try {
            resultList = em.createQuery("SELECT area FROM Area area").getResultList();
        } catch (Exception e) {
            Logger.getLogger(UsuariosDAOImplement.class.getName()).log(Level.SEVERE, ErrorUtil.CODE_0101, e);
        }
        return resultList;
    }

    @Override
    public List<Usuarios> getUsuarioAreayUsuario(String usuario, Integer idArea, Integer idSuperior) {
        List<Usuarios> resultList = null;
        try {
            String query = "SELECT us FROM Usuarios us WHERE us.idUsuarioSuperior = "+idSuperior+" ";
            if (!usuario.equals("")) {
                query = query + " AND us.usuario LIKE '%"+usuario+"%'";
            }
            if (idArea != null) {
                query = query + " AND us.idArea.idArea = "+idArea+"";
            }
            resultList = em.createQuery(query).getResultList();
        } catch (Exception e) {
            Logger.getLogger(UsuariosDAOImplement.class.getName()).log(Level.SEVERE, ErrorUtil.CODE_0101, e);
        }
        return resultList;
    }

    @Override
    public void guardarUsuarioPerfil(UsuarioPerfil usuarioPerfil) {
        try {
            if (usuarioPerfil.getIdPerfilUsuarios() == null) {
                em.persist(usuarioPerfil);
            } else {
                em.merge(usuarioPerfil);
            }
        } catch (Exception e) {
            Logger.getLogger(UsuariosDAOImplement.class.getName()).log(Level.SEVERE, ErrorUtil.CODE_0101, e);
        }
    }

    @Override
    public UsuarioPerfil getUsuarioPerfil(int idUsuario) {
        List<UsuarioPerfil> resultList = null;
        try {
            resultList = em.createQuery("SELECT up FROM UsuarioPerfil up WHERE up.idUsuario.idUsuario = :idUsuario").setParameter("idUsuario", idUsuario).getResultList();
        } catch (Exception e) {
            Logger.getLogger(UsuariosDAOImplement.class.getName()).log(Level.SEVERE, ErrorUtil.CODE_0101, e);
        }
        return resultList.get(0);
    }

    @Override
    public Usuarios getByIdUsuario(int idUsuario) {
        List<Usuarios> resultList = null;
        try {
            resultList = em.createNamedQuery("Usuarios.findByIdUsuario").setParameter("idUsuario", idUsuario).getResultList();
        } catch (Exception e) {
            Logger.getLogger(UsuariosDAOImplement.class.getName()).log(Level.SEVERE, ErrorUtil.CODE_0101, e);
        }
        if (resultList != null && resultList.size() > 0) {
            return resultList.get(0);
        }
        return null;
    }

    @Override
    public List<Usuarios> getByIdUnidadesMedicas(Integer idUnidadMedica) {
        List<Usuarios> resultList = null;
        System.out.println("idUnidadMedica-->" + idUnidadMedica);
        try {
            resultList = em.createQuery("SELECT u FROM Usuarios u WHERE u.idUnidadMedica.idUnidadesMedicas = :idUnidadMedica").setParameter("idUnidadMedica", idUnidadMedica).getResultList();
        } catch (Exception e) {
            Logger.getLogger(UsuariosDAOImplement.class.getName()).log(Level.SEVERE, ErrorUtil.CODE_0101, e);
        }
        if (resultList != null && resultList.size() > 0) {
            return resultList;
        }
        return null;
    }

    @Override
    public List<Usuarios> getByIdDelegacion(Integer idDelegacion) {
        List<Usuarios> resultList = null;
        try {
            resultList = em.createQuery("SELECT u FROM Usuarios u WHERE u.idDelegacion.idDelegacion = :idDelegacion").setParameter("idDelegacion", idDelegacion).getResultList();
        } catch (Exception e) {
            Logger.getLogger(UsuariosDAOImplement.class.getName()).log(Level.SEVERE, ErrorUtil.CODE_0101, e);
        }
        if (resultList != null && resultList.size() > 0) {
            return resultList;
        }
        return null;
    }

    @Override
    public List<UsuariosTipoUsuarios> obtenerTiposUsuariosByIdUsuario(Integer idUsuario) {
        List<UsuariosTipoUsuarios> resultList = null;
        try {
            resultList = (List<UsuariosTipoUsuarios>) em.createQuery("SELECT utu FROM UsuariosTipoUsuarios utu "
                    + "WHERE utu.idUsuario.idUsuario = :idUsuario ")
                    .setParameter("idUsuario", idUsuario)
                    .getResultList();
        } catch (Exception e) {
            Logger.getLogger(TipoUsuariosDAOImplement.class.getName()).log(Level.SEVERE, ErrorUtil.CODE_0101, e);
        }
        if (resultList != null && resultList.size() > 0) {
            return resultList;
        }
        return null;
    }

    @Override
    public boolean deleteByIdUsuario(Integer idUsuario) {
        try {
            em.createQuery("DELETE FROM UsuariosTipoUsuarios utu WHERE utu.idUsuario.idUsuario = :idUsuario").setParameter("idUsuario", idUsuario).executeUpdate();
            return true;
        } catch (Exception e) {
            Logger.getLogger(TipoUsuariosDAOImplement.class.getName()).log(Level.SEVERE, ErrorUtil.CODE_0101, e);
            return false;
        }
        
    }

    @Override
    public List<UsuariosTipoUsuarios> getByIdUsuarioIdTipoUsuario(Integer idUsuario, Integer idTipoUsuario) {
        List<UsuariosTipoUsuarios> resultList = null;
        try {
            resultList = em.createQuery("SELECT utu FROM UsuariosTipoUsuarios utu WHERE utu.idUsuario.idUsuario = :idUsuario AND utu.idTipoUsuario.idTipoUsuario = :idTipoUsuario ")
                    .setParameter("idUsuario", idUsuario)
                    .setParameter("idTipoUsuario", idTipoUsuario)
                    .getResultList();
        } catch (Exception e) {
            Logger.getLogger(TipoUsuariosDAOImplement.class.getName()).log(Level.SEVERE, ErrorUtil.CODE_0101, e);
        }
        if (resultList != null && resultList.size() > 0) {
            return resultList;
        }
        return null;
    }
}
