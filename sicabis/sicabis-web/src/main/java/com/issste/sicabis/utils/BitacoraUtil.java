/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.issste.sicabis.utils;

/**
 * Clase para la implementacion de descripcion de acciones 
 * realizadas en los flujos del sistema.
 * @author kriosoft
 */
public class BitacoraUtil {

    /**
     * Metodo que regresa la descripcion a insertar en bitacora
     *
     * @param parametro
     * @return
     */
    public String detalleGuardaroBitacora(String parametro) {
        return "SE HA GUARDADO EL DATO " + parametro + " EN BITACORA.";
    }
    
    /**
     * Metodo que regresa la actualizacion a insertar en bitacora
     *
     * @param parametro
     * @return
     */
    public String detalleActualizarBitacora(String parametro) {
        return "SE HA ACTUALIZADO EL DATO " + parametro + " EN BITACORA.";
    }
    
    /**
     * Metodo que regresa la descripcion de la eliminacion logica de objetos para insertar en bitacora
     *
     * @param parametro
     * @return
     */
    public String detalleEliminarBitacora(String parametro) {
        return "SE HA ELIMINADO EL DATO " + parametro + " EN BITACORA.";
    }
    
}
