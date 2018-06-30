package com.issste.sicabis.ejb.DAO;

import com.issste.sicabis.ejb.modelo.Menu;
import com.issste.sicabis.ejb.utils.ErrorUtil;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.LocalBean;
import javax.ejb.Stateful;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

@Stateful
@LocalBean
public class MenuDAOImplement implements MenuDAO {

    @PersistenceContext(unitName = "sicabis")
    private EntityManager em;

    @Override
    public List<Menu> obtenerMenu() {
        List<Menu> resultList = null;
        try {
            resultList = em.createQuery("Select m From Menu m where m.fechaBaja is null ").getResultList();
        } catch (Exception e) {
            Logger.getLogger(MenuDAOImplement.class.getName()).log(Level.SEVERE, ErrorUtil.CODE_0101, e);
        }
        if (resultList != null && resultList.size() > 0) {
            return resultList;
        }
        return null;
    }

    @Override
    public List<Menu> obtenerMenuByidPerfil(Integer idPerfil) {
        List<Menu> resultList = new ArrayList<>();
        try {
            resultList = em.createQuery("Select mp.idMenu From MenuPerfil mp WHERE mp.idPerfil.idPerfil = :idPerfil ").setParameter("idPerfil", idPerfil).getResultList();
        } catch (Exception e) {
            Logger.getLogger(MenuDAOImplement.class.getName()).log(Level.SEVERE, ErrorUtil.CODE_0101, e);
        }
        return resultList;
    }

    @Override
    public Menu obtenerMenuById(Integer idMenu) {
        List<Menu> menu = new ArrayList<>();
        try {
            menu = em.createQuery("Select m FROM Menu m WHERE m.idMenu = :idMenu ").setParameter("idMenu", idMenu).getResultList();
        } catch (Exception e) {
            Logger.getLogger(MenuDAOImplement.class.getName()).log(Level.SEVERE, ErrorUtil.CODE_0101, e);
        }
        return menu.get(0);
    }

    @Override
    public List<Menu> obtenerMenuByIdMenuPadre(Integer idMenuPadre, Integer activo) {
        List<Menu> resultList = null;
        try {
            Query q = em.createNamedQuery("Menu.findByIdMenuPadre");
            q.setParameter("idMenuPadre", idMenuPadre);
            q.setParameter("activo", activo);
            resultList = q.getResultList();
        } catch (Exception e) {
            Logger.getLogger(MenuDAOImplement.class.getName()).log(Level.SEVERE, ErrorUtil.CODE_0101, e);
        }
        if (resultList != null && resultList.size() > 0) {
            return resultList;
        }
        return null;
    }

    @Override
    public List<Menu> obtenerMenuByFinalIdTarea(Integer final1, Integer idTarea) {
        List<Menu> resultList = null;
        try {
            Query q = em.createQuery("SELECT m FROM Menu m WHERE m.final1 = :final1 AND m.idTarea.idTarea = :idTarea AND m.activo = 1");
            q.setParameter("final1", final1);
            q.setParameter("idTarea", idTarea);
            resultList = q.getResultList();
        } catch (Exception e) {
            Logger.getLogger(MenuDAOImplement.class.getName()).log(Level.SEVERE, ErrorUtil.CODE_0101, e);
        }
        if (resultList != null && resultList.size() > 0) {
            return resultList;
        }
        return null;
    }

}
