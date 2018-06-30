/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.issste.sicabis.ejb.utils;

/**
 *
 * @author Toshiba Manolo
 */
public class ErrorUtil {
    
    public static final String CODE_0101 = "Error en UsersDAOImplement en metodo getUserByUsername(String username)";
    public static final String CODE_0102 = "Error en RemisionesDAOImplemnt en metodo getByRemision()";
    
    /**
     *
     * @param serror
     * @return
     */
    public static String ObtenerError(String serror) {
        String saux = serror;
        try {
            if (saux.contains("-20001")) {
                int iaux = saux.indexOf("/*");
                saux = saux.substring(iaux + 2, saux.length() - 1);
                iaux = saux.indexOf("*/");
                saux = saux.substring(0, iaux);
            }
        } catch (Exception ex) {
            return saux;
        }
        return saux;
    }
    
}
