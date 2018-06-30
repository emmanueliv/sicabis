/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.issste.sicabis.ejb.ln;

import com.issste.sicabis.ejb.DAO.MenuPerfilDAOImplements;
import com.issste.sicabis.ejb.modelo.MenuPerfil;
import javax.ejb.EJB;
import javax.ejb.Stateless;

@Stateless
public class MenuPerfilService {

    @EJB
    private MenuPerfilDAOImplements menuPerfilDAOImplements;
    
    public boolean borrarByIdMenuPerfil(Integer idPerfil){
        return menuPerfilDAOImplements.borrarByIdMenuPerfil(idPerfil);
    }
    
    public boolean guardaMenuPerfil(MenuPerfil menuPerfil) {
        return menuPerfilDAOImplements.guardaMenuPerfil(menuPerfil);
    }

}
