/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.issste.sicabis.ejb.ln;

import com.issste.sicabis.ejb.DAO.ContactosAlertasDpnDAO;
import com.issste.sicabis.ejb.modelo.ContactosAlertasDpn;
import com.issste.sicabis.ejb.modelo.Usuarios;
import java.util.List;
import javax.ejb.EJB;
import javax.ejb.Stateless;

/**
 *
 * @author 9RZCBG2
 */
@Stateless
public class ContactosAlertasDpnService {

    @EJB
    private ContactosAlertasDpnDAO contactosAlertasDpnDAOImplement;

    public List<ContactosAlertasDpn> getByIdDelegacion(Integer idDelegacion) {
        return contactosAlertasDpnDAOImplement.getByIdDelegacion(idDelegacion);
    }

    public List<ContactosAlertasDpn> getByIdUnidadMedica(Integer idUnidadMedica) {
        return contactosAlertasDpnDAOImplement.getByIdUnidadMedica(idUnidadMedica);
    }

    public Boolean guardarActualiza(ContactosAlertasDpn contactosAlertasDpn) {
        return contactosAlertasDpnDAOImplement.guardarActualiza(contactosAlertasDpn);
    }

    public ContactosAlertasDpn getByIdContactoAlectaDpn(Integer idContactoAlertaDpn) {
        return contactosAlertasDpnDAOImplement.getByIdContactoAlectaDpn(idContactoAlertaDpn);
    }

    public List<ContactosAlertasDpn> getAllContactos(Integer activo) {
        return contactosAlertasDpnDAOImplement.getAllContactos(activo);
    }
    
    public List<ContactosAlertasDpn> getByMapas(Integer mapas) {
        return contactosAlertasDpnDAOImplement.getByMapas(mapas);
    }
}
