package com.issste.sicabis.ejb.DAO;

import com.issste.sicabis.ejb.modelo.Fabricante;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author fabianvr
 */
@Local
public interface FabricanteDAO {

    List<Fabricante> fabricanteByNumero(Integer numero, Integer nombre);

    List<Fabricante> fabricanteList();

    Fabricante fabricanteByIdFacbricante(Integer idFabricante);

    boolean guardarFabricante(Fabricante fabricante);

    Fabricante fabricanteByNombre(String nombre);

    List<Fabricante> fabricanteByIdRemision(Integer idRemision);
}
