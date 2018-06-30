//################################################################################
//      ## Fecha de creación: 18/12/15
//      ## Fecha de última modificación: 18/12/15
//      ## Responsable: Emmanuel De la Isla Vértiz
//      ## Módulos asociados: Protocolo correo
//      ## Id Tickets asociados al cambio: C-R-012150
//################################################################################
package com.issste.sicabis.utils;

public enum ProtocolActual {
    SMTP(1,"SMTP"),
    SMTPS(2,"SMTPS"),
    TLS(3,"TLS");
    
    private int idProtocolo;
    private String protocolo;

    private ProtocolActual(int idProtocolo, String protocolo) {
        this.idProtocolo = idProtocolo;
        this.protocolo = protocolo;
    }

    public int getIdProtocolo() {
        return idProtocolo;
    }

    public void setIdProtocolo(int idProtocolo) {
        this.idProtocolo = idProtocolo;
    }

    public String getProtocolo() {
        return protocolo;
    }

    public void setProtocolo(String protocolo) {
        this.protocolo = protocolo;
    }

    @Override
    public String toString() {
        return "Protocol{"+ idProtocolo + "," + protocolo + '}';
    }
}
