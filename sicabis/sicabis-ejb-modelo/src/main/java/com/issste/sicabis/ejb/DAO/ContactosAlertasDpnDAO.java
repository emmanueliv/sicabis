/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.issste.sicabis.ejb.DAO;

import com.issste.sicabis.ejb.modelo.ContactosAlertasDpn;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author 9RZCBG2
 */
@Local
public interface ContactosAlertasDpnDAO {

    List<ContactosAlertasDpn> getByIdDelegacion(Integer idDelegacion);

    List<ContactosAlertasDpn> getByIdUnidadMedica(Integer idUnidadMedica);

    Boolean guardarActualiza(ContactosAlertasDpn contactosAlertasDpn);

    ContactosAlertasDpn getByIdContactoAlectaDpn(Integer idContactoAlertaDpn);
    
    List<ContactosAlertasDpn> getAllContactos(Integer activo);
    
    List<ContactosAlertasDpn> getByMapas(Integer mapas);
}
