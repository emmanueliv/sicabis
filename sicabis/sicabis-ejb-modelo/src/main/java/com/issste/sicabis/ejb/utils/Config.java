//################################################################################
//      ## Fecha de creación: 18/12/15
//      ## Fecha de última modificación: 18/12/15
//      ## Responsable: Emmanuel De la Isla Vértiz
//      ## Módulos asociados: Correo configuración
//      ## Id Tickets asociados al cambio: C-R-012150
//################################################################################
package com.issste.sicabis.ejb.utils;

import java.io.Serializable;

public class Config implements Serializable{
    
    private String host;
    private String port;
    private boolean auth;
    private String usr;
    private String password;
    private ProtocolActual protocol;
    private boolean isTLS;
    private boolean ssl;
    

    public Config() {
    }
        

    public Config(String host, String port, boolean auth, String usr, String password, ProtocolActual protocol) {
        this.host = host;
        this.port = port;
        this.auth = auth;
        this.usr = usr;
        this.password = password;
        this.protocol = protocol;
    }

    public String getHost() {
        return host;
    }

    public void setHost(String host) {
        this.host = host;
    }

    public String getPort() {
        return port;
    }

    public void setPort(String port) {
        this.port = port;
    }

    public boolean isAuth() {
        return auth;
    }

    public void setAuth(boolean auth) {
        this.auth = auth;
    }

    public String getUsr() {
        return usr;
    }

    public void setUsr(String usr) {
        this.usr = usr;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public ProtocolActual getProtocol() {
        return protocol;
    }

    public void setProtocol(ProtocolActual protocol) {
        this.protocol = protocol;
    }

    public boolean isTLS() {
        return isTLS;
    }

    public void setIsTLS(boolean isTLS) {
        this.isTLS = isTLS;
    }

    public boolean isSsl() {
        return ssl;
    }

    public void setSsl(boolean ssl) {
        this.ssl = ssl;
    }
}