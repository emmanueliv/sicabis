package com.issste.sicabis.ejb.ln;

import com.issste.sicabis.ejb.DAO.UrDAO;
import com.issste.sicabis.ejb.modelo.Ur;
import java.util.List;
import javax.ejb.EJB;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;

@Stateless
@LocalBean
public class UrService {

    @EJB
    private UrDAO urDAOImplement;
    
    public List<Ur> getAll() {
        return urDAOImplement.getAll();
    }
    
    public Ur getByUr(Integer ur) {
        return urDAOImplement.getByUr(ur);
    }

}
