/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.issste.sicabis.ejb.ln;

import com.issste.sicabis.ejb.DAO.TiposConvenioDAO;
import com.issste.sicabis.ejb.modelo.TipoConvenio;
import java.util.List;
import javax.ejb.EJB;
import javax.ejb.Stateless;

/**
 *
 * @author kriosoft
 */
@Stateless
public class TiposConvenioService {

    @EJB
    TiposConvenioDAO conveniosDAOImpl;
    
    public List<TipoConvenio> obtenerTipoConvenios() {
        return conveniosDAOImpl.obtenerTipoConvenios();
    }

    public void guardarTipoConvenio(TipoConvenio convenio) {
        conveniosDAOImpl.guardarTipoConvenio(convenio);
    }

    public TipoConvenio obtenerTipoConvenioByNombre(String nombreConvenio) {
        List<TipoConvenio> lista = conveniosDAOImpl.obtenerTipoConveniosByDesc(nombreConvenio);
        if(lista.isEmpty()){
            return null;
        }
        return lista.get(0);
    }
    
}
