/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.issste.sicabis.ejb.ln;

import com.issste.sicabis.ejb.DAO.TipoCompraDAO;
import com.issste.sicabis.ejb.modelo.TipoCompra;
import java.util.List;
import javax.ejb.EJB;
import javax.ejb.Stateful;
import javax.ejb.Stateless;

/**
 *
 * @author erik
 */
@Stateful
public class TipoCompraService {


    @EJB
    private TipoCompraDAO tipoCompraDao;

    public List<TipoCompra> traeListaTipoCompra() {
        return tipoCompraDao.obtenerTiposCompra();
    }

    public void guardar(TipoCompra tipoCompra) {
        tipoCompraDao.guardarTipoConvenio(tipoCompra);
    }
    
    public TipoCompra obtenerTipoCompraByNombre(String nombreCompra) {
        List<TipoCompra> lista = tipoCompraDao.obtenerTipoCompraByNom(nombreCompra);
        if(lista.isEmpty()){
            return null;
        }
        return lista.get(0);
    }    
    
}
