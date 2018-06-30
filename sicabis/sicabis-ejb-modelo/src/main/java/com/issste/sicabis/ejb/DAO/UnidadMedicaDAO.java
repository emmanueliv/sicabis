/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.issste.sicabis.ejb.DAO;

import com.issste.sicabis.ejb.modelo.UnidadesMedicas;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author fabianvr
 */
@Local
public interface UnidadMedicaDAO {

    List<UnidadesMedicas> unidadMedica();
    
    List<UnidadesMedicas> obtenUnidadesMedicasByActivoAndId(int unidadMed);
    
    List<UnidadesMedicas> unidadMedicaByActivo();

    void guardarUnidadesMedicas(UnidadesMedicas unidadMedica);

    List<UnidadesMedicas> unidadMedicaByNombre(String nombreUnidadesMedicas);
    
    List<UnidadesMedicas> obtenUnidadesMedicas();
    
    UnidadesMedicas getByIdUnidadMedica(Integer idUnidadesMedicas);
    
    List<UnidadesMedicas> getByConcentradora(Integer concentradora);
    
    List<UnidadesMedicas> getByHospitalRegional(Integer hospitalRegional);
}
