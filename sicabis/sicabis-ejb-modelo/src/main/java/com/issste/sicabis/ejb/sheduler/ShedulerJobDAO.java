/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.issste.sicabis.ejb.sheduler;

import javax.ejb.Local;

/**
 *
 * @author 9RZCBG2
 */
@Local
public interface ShedulerJobDAO {

    void execute();
    
    void executeWebServiceMapas();
    
    void executeWebService();
        
    void SendMailInsumosPendientesExecute();
    
    void ejecutaAlerta();
    
    void ordenesSuministroAutoExecute();
    
    void executeAlertas();
}
