/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.issste.sicabis.ejb.service.silodisa.service;

import com.issste.sicabis.ejb.DTO.ExistenciaClaveUmuDTO;
import com.issste.sicabis.ejb.modelo.DpnInsumos;
import com.issste.sicabis.ejb.service.silodisa.DAO.ExistenciaPorClaveUmusDAO;
import com.issste.sicabis.ejb.service.silodisa.modelo.ExistenciaPorClaveUmus;
import java.util.Date;
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
public class ExistenciaPorClaveUmusService {

    @EJB
    private ExistenciaPorClaveUmusDAO existenciaPorClaveUmusDAOImplement;

    public boolean guardar(ExistenciaPorClaveUmus epcu) {
        return existenciaPorClaveUmusDAOImplement.guardar(epcu);
    }

    public boolean actualizar(ExistenciaPorClaveUmus epcu) {
        return existenciaPorClaveUmusDAOImplement.actualiar(epcu);
    }

    public List<ExistenciaPorClaveUmus> existenciaPorClaveUmusByUmu(String umu) {
        return existenciaPorClaveUmusDAOImplement.existenciaPorClaveUmusByUmu(umu);
    }

    public Integer existenciaPorClaveUmusByUmuAndClave(String umu, String clave) {
        return existenciaPorClaveUmusDAOImplement.existenciaPorClaveUmusByUmuAndClave(umu, clave);
    }

    public boolean eliminarExistenciasUmus() {
        return existenciaPorClaveUmusDAOImplement.eliminarExistenciasUmus();
    }

    public List<DpnInsumos> getByUMUClaveFecha(String clave, String claveUmu, Date fecha) {
        return existenciaPorClaveUmusDAOImplement.getByUMUClaveFecha(clave, claveUmu, fecha);
    }

    public List<ExistenciaClaveUmuDTO> getAll() {
        return existenciaPorClaveUmusDAOImplement.getAll();
    }
}
