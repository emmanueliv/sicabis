/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.issste.sicabis.ejb.ln;

import com.issste.sicabis.ejb.DAO.MenuDAO;
import com.issste.sicabis.ejb.modelo.Menu;
import java.util.List;
import javax.ejb.EJB;
import javax.ejb.Stateful;
import javax.ejb.Stateless;

/**
 *
 * @author Toshiba Manolo
 */
@Stateful
@Stateless
public class MenuService {

    @EJB
    private MenuDAO menuDAOImplement;

    Menu menu;
    private List<Menu> listaMenu;

    public List<Menu> obtenerMenuService() {
        listaMenu = menuDAOImplement.obtenerMenu();
        return listaMenu;
    }

    public List<Menu> obtenerMenuByidPerfil(Integer idPerfil) {
        return menuDAOImplement.obtenerMenuByidPerfil(idPerfil);
    }
    
    public Menu obtenerMenuById(Integer idMenu){
        return menuDAOImplement.obtenerMenuById(idMenu);
    }
    
    public List<Menu> obtenerMenuByIdMenuPadre(Integer idMenuPadre, Integer activo) {
        return menuDAOImplement.obtenerMenuByIdMenuPadre(idMenuPadre, activo);
    }
    
    public List<Menu> obtenerMenuByFinalIdTarea(Integer final1, Integer idTarea) {
        return menuDAOImplement.obtenerMenuByFinalIdTarea(final1, idTarea);
    }
}
