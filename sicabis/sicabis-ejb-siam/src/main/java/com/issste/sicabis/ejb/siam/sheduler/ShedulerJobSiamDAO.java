/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.issste.sicabis.ejb.siam.sheduler;

import javax.ejb.Local;

@Local
public interface ShedulerJobSiamDAO {

    void execute();

    void executeInsumos();
}
