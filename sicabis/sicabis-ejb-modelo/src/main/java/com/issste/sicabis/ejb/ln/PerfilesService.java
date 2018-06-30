package com.issste.sicabis.ejb.ln;

import com.issste.sicabis.ejb.DAO.PerfilesDAO;
import com.issste.sicabis.ejb.modelo.Perfiles;
import java.util.List;
import javax.ejb.EJB;
import javax.ejb.Stateless;

@Stateless
public class PerfilesService {

    @EJB
    private PerfilesDAO perfilesDAOImplement;
    
    private Perfiles perfiles;
    private List<Perfiles> listaPerfiles;
    
    public List<Perfiles> obtenerPerfilesService(){
        listaPerfiles = perfilesDAOImplement.obtenerPerfiles();
        return listaPerfiles;
    }
    
    public boolean guardaPerfilService(Perfiles perfiles){
        boolean bandera = true;
        bandera = perfilesDAOImplement.guardaPerfil(perfiles);
        return bandera;
    }
    
    public boolean obtenerPerfilByNombreService(String nombre, Integer idPerfil){
        boolean bandera = true;
        bandera = perfilesDAOImplement.obtenerPerfilByNombre(nombre, idPerfil);
        return bandera;
    }

    public Perfiles obtenerPerfilByIdUsuario(Integer idUsuario) {
        return perfilesDAOImplement.obtenerPerfilByIdUsuario(idUsuario);
    }
    
    public List<Perfiles> getByNombreActivo(String nombre, Integer activo) {
        return perfilesDAOImplement.getByNombreActivo(nombre, activo);
    }

}
