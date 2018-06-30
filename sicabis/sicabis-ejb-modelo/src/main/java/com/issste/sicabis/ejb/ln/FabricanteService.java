package com.issste.sicabis.ejb.ln;

import com.issste.sicabis.ejb.DAO.FabricanteDAO;
import com.issste.sicabis.ejb.modelo.Fabricante;
import java.util.List;
import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.ejb.LocalBean;

@Stateless
@LocalBean
public class FabricanteService {

    @EJB
    private FabricanteDAO fabricanteDAOImplement;

    public List<Fabricante> fabricanteList() {
        return fabricanteDAOImplement.fabricanteList();
    }

    public boolean guardarFabricante(Fabricante fabricante) {
        return fabricanteDAOImplement.guardarFabricante(fabricante);
    }

    public Fabricante fabricanteByIdFacbricante(Integer idFabricante) {
        return fabricanteDAOImplement.fabricanteByIdFacbricante(idFabricante);
    }
    
    public Fabricante fabricanteByNombre(String nombre){
        return fabricanteDAOImplement.fabricanteByNombre(nombre);
    }

    public List<Fabricante> fabricantesByIdRemisionList(Integer idRemision) {
        return fabricanteDAOImplement.fabricanteByIdRemision(idRemision);
    }

}
