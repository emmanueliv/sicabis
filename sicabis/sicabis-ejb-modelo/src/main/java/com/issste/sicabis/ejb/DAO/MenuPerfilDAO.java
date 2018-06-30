/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.issste.sicabis.ejb.DAO;

import com.issste.sicabis.ejb.modelo.MenuPerfil;
import com.issste.sicabis.ejb.modelo.Perfiles;
import javax.ejb.Local;

@Local
public interface MenuPerfilDAO {
    
    boolean borrarByIdMenuPerfil(Integer idPerfil);
    boolean guardaMenuPerfil(MenuPerfil menuPerfil);
    
}
