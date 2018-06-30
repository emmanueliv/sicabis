
package com.issste.sicabis.utils;


public enum EstatusInsumoDpnEnum {
    
    en_dpn(1),
    baja_tmp(2)
    ;
    
    private int value;

    private EstatusInsumoDpnEnum(int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }

    public void setValue(int value) {
        this.value = value;
    }
    
    
}
