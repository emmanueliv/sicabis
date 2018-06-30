/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.issste.sicabis.ejb.service.silodisa.service;

import com.issste.sicabis.ejb.service.silodisa.DAO.SalidasCenadiUmuGuiaDeDistribucionDAO;
import com.issste.sicabis.ejb.service.silodisa.modelo.SalidasCenadiUmuGuiaDeDistribucion;
import java.util.List;
import javax.ejb.EJB;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;

/**
 *
 * @author 9RZCBG2
 */
@LocalBean
@Stateless
public class SalidasCenadiUmuGuiaDeDistribucionService {

    @EJB
    private SalidasCenadiUmuGuiaDeDistribucionDAO salidasCenadiUmuGuiaDeDistribucionDAOImplement;

    public boolean guardar(SalidasCenadiUmuGuiaDeDistribucion scugd) {
        return salidasCenadiUmuGuiaDeDistribucionDAOImplement.guardar(scugd);
    }

    public boolean actualizar(SalidasCenadiUmuGuiaDeDistribucion scugd) {
        return salidasCenadiUmuGuiaDeDistribucionDAOImplement.actualizar(scugd);
    }

    public List<SalidasCenadiUmuGuiaDeDistribucion> SalidasCenadiUmuGuiaDeDistribucionByUmu(String umu) {
        return salidasCenadiUmuGuiaDeDistribucionDAOImplement.salidasCenadiUmuGuiaDeDistribucionByUmu(umu);
    }

    public boolean eliminarExistencias() {
        return salidasCenadiUmuGuiaDeDistribucionDAOImplement.eliminarExistencias();
    }
}
