
package com.issste.sicabis.DTO;

import com.issste.sicabis.ejb.modelo.Menu;
import com.issste.sicabis.ejb.modelo.MenuPerfil;
import java.util.List;


public class MenuPerfilDTO {
    
    private Menu menu;
    private List<MenuPerfil> listMenuPerfil;
    private boolean todos;

    public Menu getMenu() {
        return menu;
    }

    public void setMenu(Menu menu) {
        this.menu = menu;
    }

    public List<MenuPerfil> getListMenuPerfil() {
        return listMenuPerfil;
    }

    public void setListMenuPerfil(List<MenuPerfil> listMenuPerfil) {
        this.listMenuPerfil = listMenuPerfil;
    }

    public boolean isTodos() {
        return todos;
    }

    public void setTodos(boolean todos) {
        this.todos = todos;
    }
    
}
