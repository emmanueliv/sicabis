/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.issste.sicabis.ejb.service.silodisa.DAO;

import com.issste.sicabis.ejb.DTO.ExistenciaClaveUmuDTO;
import com.issste.sicabis.ejb.modelo.DpnInsumos;
import com.issste.sicabis.ejb.service.silodisa.modelo.ExistenciaPorClaveUmus;
import java.util.Date;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author 9RZCBG2
 */
@Local
public interface ExistenciaPorClaveUmusDAO {

    boolean guardar(ExistenciaPorClaveUmus epcu);

    boolean actualiar(ExistenciaPorClaveUmus epcu);

    List<ExistenciaPorClaveUmus> existenciaPorClaveUmusByUmu(String umu);

    Integer existenciaPorClaveUmusByUmuAndClave(String umu, String clave);

    boolean eliminarExistenciasUmus();
    
    List<DpnInsumos> getByUMUClaveFecha(String clave, String claveUmu, Date fecha);
    
    List<ExistenciaClaveUmuDTO> getAll();
    
}
