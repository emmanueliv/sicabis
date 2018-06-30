/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.issste.sicabis.ejb.ln;

import com.issste.sicabis.ejb.DAO.UnidadResponsableDao;
import com.issste.sicabis.ejb.modelo.UnidadResponsable;
import java.util.List;
import javax.ejb.EJB;
import javax.ejb.Stateful;
import javax.ejb.Stateless;

/**
 *
 * @author erik
 */
@Stateful
public class UnidadResponsableService {

    @EJB
    UnidadResponsableDao unidadResponsableDao;

    public List<UnidadResponsable> traeListaUnidadesResponsables() {
        return unidadResponsableDao.obtenerUnidadesResponsables();
    }
}
