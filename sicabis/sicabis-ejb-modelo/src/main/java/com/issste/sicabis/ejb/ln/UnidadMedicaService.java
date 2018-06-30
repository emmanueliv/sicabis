/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.issste.sicabis.ejb.ln;

import com.issste.sicabis.ejb.DAO.UnidadMedicaDAO;
import com.issste.sicabis.ejb.modelo.UnidadesMedicas;
import java.util.List;
import javax.ejb.EJB;
import javax.ejb.Stateful;
import javax.ejb.Stateless;

/**
 *
 * @author fabianvr
 */
@Stateful
public class UnidadMedicaService {

    @EJB
    private UnidadMedicaDAO unidadMedicaDAOImplement;

    public List<UnidadesMedicas> unidadMedica() {
        List<UnidadesMedicas> unidadMedicaList;
        unidadMedicaList = unidadMedicaDAOImplement.unidadMedica();
        return unidadMedicaList;
    }

    public void guardarUnidadesMedicas(UnidadesMedicas unidadMedica) {
        unidadMedicaDAOImplement.guardarUnidadesMedicas(unidadMedica);
    }

    public List<UnidadesMedicas> obtenUnidadesMedicasByActivoAndId(int unidadMed) {
        return unidadMedicaDAOImplement.obtenUnidadesMedicasByActivoAndId(unidadMed);
    }

    public UnidadesMedicas obtenerUnidadesMedicasByNombre(String nombreUnidadesMedicas) {
        List<UnidadesMedicas> lista = unidadMedicaDAOImplement.unidadMedicaByNombre(nombreUnidadesMedicas);
        if (lista.isEmpty()) {
            return null;
        }
        return lista.get(0);
    }

    public List<UnidadesMedicas> obtenUnidadesMedicas() {
        return unidadMedicaDAOImplement.obtenUnidadesMedicas();
    }

    public UnidadesMedicas getByIdUnidadMedica(Integer idUnidadesMedicas) {
        return unidadMedicaDAOImplement.getByIdUnidadMedica(idUnidadesMedicas);
    }

    public List<UnidadesMedicas> unidadMedicaByActivo() {
        return unidadMedicaDAOImplement.unidadMedicaByActivo();
    }
    
    public List<UnidadesMedicas> getByConcentradora(Integer concentradora) {
        return unidadMedicaDAOImplement.getByConcentradora(concentradora);
    }
    
    public List<UnidadesMedicas> getByHospitalRegional(Integer hospitalRegional) {
        return unidadMedicaDAOImplement.getByHospitalRegional(hospitalRegional);
    }

}
