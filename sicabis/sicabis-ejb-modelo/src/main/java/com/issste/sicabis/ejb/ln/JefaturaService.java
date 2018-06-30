package com.issste.sicabis.ejb.ln;

import com.issste.sicabis.ejb.DAO.JefaturaDAO;
import com.issste.sicabis.ejb.modelo.Jefatura;
import java.util.List;
import javax.ejb.EJB;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;

@LocalBean
@Stateless
public class JefaturaService {

    @EJB
    private JefaturaDAO jefaturaDAOImplement;
    
    public List<Jefatura> getAll() {
        return jefaturaDAOImplement.getAll();
    }
    
    public List<Jefatura> getByIdArea(Integer idArea) {
        return jefaturaDAOImplement.getByIdArea(idArea);
    }

}
