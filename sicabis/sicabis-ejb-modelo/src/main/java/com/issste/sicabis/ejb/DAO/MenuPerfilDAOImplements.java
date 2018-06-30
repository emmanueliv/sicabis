/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.issste.sicabis.ejb.DAO;

import com.issste.sicabis.ejb.modelo.MenuPerfil;
import com.issste.sicabis.ejb.modelo.Perfiles;
import com.issste.sicabis.ejb.utils.ErrorUtil;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.LocalBean;
import javax.ejb.Stateful;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

@Stateful
@LocalBean
public class MenuPerfilDAOImplements implements MenuPerfilDAO{
    
    @PersistenceContext(unitName = "sicabis")
    private EntityManager em;

    @Override
    public boolean borrarByIdMenuPerfil(Integer idPerfil) {
        String sQuery = "";
        Query query;
        int result = 0;
        try {
            sQuery = "DELETE menu_perfil where id_perfil = " + idPerfil;
            query = em.createNativeQuery(sQuery);
            result = query.executeUpdate();
            System.out.println("result--->"+result);
        } catch (Exception e) {
            Logger.getLogger(MenuPerfilDAOImplements.class.getName()).log(Level.SEVERE, ErrorUtil.CODE_0101, e);
            return false;
        }
        return true;
    }

    @Override
    public boolean guardaMenuPerfil(MenuPerfil menuPerfil) {
        try {
            if (menuPerfil.getIdMenuPerfil() == null) {
                em.persist(menuPerfil);
            } else {
                em.merge(menuPerfil);
            }
            return true;
        } catch (Exception e) {
            Logger.getLogger(MenuPerfilDAOImplements.class.getName()).log(Level.SEVERE, ErrorUtil.CODE_0101, e);
            return false;
        }
    }
    
}
