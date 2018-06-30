/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.issste.sicabis.ejb.DAO;

import com.issste.sicabis.ejb.modelo.TipoCompra;
import com.issste.sicabis.ejb.modelo.TipoConvenio;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author erik
 */
@Local
public interface TipoCompraDAO {
    
    List<TipoCompra> obtenerTiposCompra();

    void guardarTipoConvenio(TipoCompra tipoCompra);

    List<TipoCompra> obtenerTipoCompraByNom(String nombreTipoCompra);
    
}
