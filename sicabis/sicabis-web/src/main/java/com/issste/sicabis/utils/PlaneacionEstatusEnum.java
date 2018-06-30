/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.issste.sicabis.utils;

/**
 *
 * @author Toshiba Manolo
 */
public enum PlaneacionEstatusEnum {

    RCB_CREADA(11),
    RCB_INVEST_MERCADO(12),
    RCB_ESPERANDO_INVEST(13),
    RCB_INVEST_RESUELTA(14),
    RCB_DOC_PENDIENTE(15),
    RCB_COMPLETA(16),
    RCB_MODIFICADA(17),
    IDSOLICITUD_EXTRAORDINARIA(3),
    IDSOLICITUD_MENSUAL(1),
    IDSOLICITUD_SOPORTE(2),
    SOLICITUD_TERMINADA(152),
    SOLICITUD_CREADA(151),
    SOLICITUD_PROCESADA(153),
    ID_MODULO_SOLICITUDES(15),
    ID_MODULO_RCB(1),
    ID_MODULO_CR(2),
    ID_AREA_PLANEACION(10),
    ID_AREA_INFRAESTRUCTURA(11),
    ID_AREA_PREVENCION(12),
    ID_AREA_REGULACION_VIH(13),
    ID_AREA_REGULACION_TRANSPLANTES(14),
    ID_GRUPO_010(1),
    ID_GRUPO_020(2),
    ID_GRUPO_030(3),
    ID_GRUPO_040(4),
    ID_GRUPO_060(5),
    ID_GRUPO_070(6),
    ID_GRUPO_080(7),
    ID_GRUPO_100(8),
    ID_GRUPO_220(9),
    ID_GRUPO_370(10),
    ID_GRUPO_500(11),
    ID_NIVEL_100(1),
    ID_NIVEL_011(2),
    ID_NIVEL_111(3),
    ID_NIVEL_000(4),
    ID_NIVEL_010(5),
    ID_NIVEL_001(6),
    ID_NIVEL_110(7)
    ;

    private int value;

    private PlaneacionEstatusEnum(int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }
}
