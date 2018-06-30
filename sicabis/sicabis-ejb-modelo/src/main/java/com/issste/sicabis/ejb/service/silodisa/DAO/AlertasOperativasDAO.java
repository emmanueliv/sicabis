/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.issste.sicabis.ejb.service.silodisa.DAO;

import com.issste.sicabis.ejb.service.silodisa.modelo.AlertasOperativas;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author 9RZCBG2
 */
@Local
public interface AlertasOperativasDAO {

    boolean guardar(AlertasOperativas ao);

    boolean actualizar(AlertasOperativas ao);

    Integer alertasOperativasByClave(String clave);

    Integer alertasOperativasByClaveAndUmu(String clave, String umu);
     
    boolean eliminarExistencias();
    List<AlertasOperativas> getAll();
}
