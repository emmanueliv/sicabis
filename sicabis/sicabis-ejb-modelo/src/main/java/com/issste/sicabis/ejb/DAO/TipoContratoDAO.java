/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.issste.sicabis.ejb.DAO;

import com.issste.sicabis.ejb.modelo.TipoContrato;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author kriosoft
 */
@Local
public interface TipoContratoDAO {

    List<TipoContrato> getTiposContrato();

    List<TipoContrato> obtenerTipoContratoByNombre(String nombre);

    void guardarTipoContrato(TipoContrato tipoContrato);
    
}
