/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.issste.sicabis.controller;

import com.issste.sicabis.ejb.ln.BitacoraTareaSerice;
import com.issste.sicabis.ejb.ln.ProveedorService;
import com.issste.sicabis.ejb.ln.TipoProveedorService;
import com.issste.sicabis.ejb.modelo.BitacoraTareaEstatus;
import com.issste.sicabis.ejb.modelo.Contactos;
import com.issste.sicabis.ejb.modelo.Proveedores;
import com.issste.sicabis.ejb.modelo.TipoProveedor;
import com.issste.sicabis.ejb.modelo.Usuarios;
import com.issste.sicabis.utils.Mensajes;
import com.issste.sicabis.utils.Utilidades;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Objects;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;

public class DetalleProveedoresBean implements Serializable {

    @EJB
    private BitacoraTareaSerice bitacoraTareaSerice;

    @EJB
    private TipoProveedorService tipoProveedorService;

    @EJB
    private ProveedorService proveedorService;

    private String correo;
    private String nombreProveedor;
    private String nombreProveedorAnt;
    private Integer numeroProveedor;
    private Integer numeroProveedorAnt;
    private String direccion;
    private String rfc;
    private String razonSocial;
    private Integer tipoProveedor;
    private Integer noRupa;

    private Usuarios usuarioLogin;
    private Contactos contacto;
    private Proveedores proveedor;
    private Proveedores proveedorAnterior;
    private BitacoraTareaEstatus bitacoraTareaEstatus;
    private List<Contactos> contactoList;
    private List<Proveedores> proveedoresList;
    private List<TipoProveedor> tipoProveedoresList;

    private Utilidades util = new Utilidades();
    private Mensajes mensaje = new Mensajes();

    public DetalleProveedoresBean() {
        usuarioLogin = new Usuarios();
        contacto = new Contactos();
        proveedor = new Proveedores();
        proveedorAnterior = new Proveedores();
        contactoList = new ArrayList<>();
        proveedoresList = new ArrayList<>();
        tipoProveedoresList = new ArrayList<>();
        bitacoraTareaEstatus = new BitacoraTareaEstatus();
    }

    @PostConstruct
    public void init() {
        usuarioLogin = (Usuarios) util.getSessionAtributte("usuario");
        proveedor = (Proveedores) util.getSessionAtributte("proveedor");
        tipoProveedoresList = tipoProveedorService.obtenerTipoProveedores();
        nombreProveedorAnt = proveedor.getNombreProveedor();
        numeroProveedorAnt = proveedor.getNumeroProveedor();
        tipoProveedor = proveedor.getIdTipoProveedor().getIdTipoProveedor();
        if (proveedor.getMicro() != null) {
            if (proveedor.getMicro().intValue() == 1) {
                proveedor.setMicro(0);
            }
            if (proveedor.getPequena().intValue() == 1) {
                proveedor.setMicro(1);
            }
            if (proveedor.getMediana().intValue() == 1) {
                proveedor.setMicro(2);
            }
        }
        proveedorAnterior = proveedor;
    }

    public void agregarContacto() {
        contactoList.add(contacto);
        contacto = new Contactos();
        correo = "";
    }

    public void guardarProveedor() {
        nombreProveedor = proveedor.getNombreProveedor();
        numeroProveedor = proveedor.getNumeroProveedor();
        int auto = proveedor.getAutorizado();
        if (validar()) {
            if (validarAgregar()) {
                proveedorAnterior.setFechaModificacion(new Date());
                proveedorAnterior.setUsuarioModificacion(usuarioLogin.getUsuario());
                if (proveedor.getMicro() != null) {
                    switch (proveedor.getMicro().intValue()) {
                        case 0:
                            proveedorAnterior.setMicro(1);
                            proveedorAnterior.setPequena(0);
                            proveedorAnterior.setMediana(0);
                            break;
                        case 1:
                            proveedorAnterior.setMicro(0);
                            proveedorAnterior.setPequena(1);
                            proveedorAnterior.setMediana(0);
                            break;
                        case 2:
                            proveedorAnterior.setMicro(0);
                            proveedorAnterior.setPequena(0);
                            proveedorAnterior.setMediana(1);
                            break;
                    }
                }
                if (auto == 0) {
                    proveedorAnterior.setActivo(1);
                    proveedorAnterior.setAutorizado(1);
                } else {
                    proveedorAnterior.setActivo(0);
                    proveedorAnterior.setAutorizado(1);
                    proveedorAnterior.setIdProveedor(proveedor.getIdProveedor());
                }
                proveedorService.guardarProveedor(proveedorAnterior);

                if (auto == 1) {
                    if (proveedor.getMicro() != null) {
                        switch (proveedor.getMicro().intValue()) {
                            case 0:
                                proveedor.setMicro(1);
                                proveedor.setPequena(0);
                                proveedor.setMediana(0);
                                break;
                            case 1:
                                proveedor.setMicro(0);
                                proveedor.setPequena(1);
                                proveedor.setMediana(0);
                                break;
                            case 2:
                                proveedor.setMicro(0);
                                proveedor.setPequena(0);
                                proveedor.setMediana(1);
                                break;
                        }
                    }
                    proveedor.setUsuarioModificacion(usuarioLogin.getUsuario());
                    proveedor.setFechaModificacion(new Date());
                    proveedor.setAutorizado(1);
                    proveedor.setActivo(1);
                    proveedor.setIdPadre(proveedor.getIdProveedor());
                    proveedorService.guarda(proveedor);
                }
                bitacoraTareaEstatus.setDescripcion("Actualiza proveedor:" + nombreProveedor + "");
                bitacoraTareaEstatus.setFecha(new Date());
                bitacoraTareaEstatus.setIdModulos(4);
                bitacoraTareaEstatus.setIdUsuarios(usuarioLogin.getIdUsuario());
                bitacoraTareaEstatus.setIdTareaId(16);
                bitacoraTareaSerice.guardarEnBitacora(bitacoraTareaEstatus);
                //proveedor = new Proveedores();
                mensaje.mensaje("El proveedor " + nombreProveedor + " se ha guardado correctamente.", "verde");
            } else {
                mensaje.mensaje("Se deben capturar los datos completos", "rojo");
            }
        }
    }
    
    public boolean validar() {
        boolean bandera = true;
        if(proveedor.getNumeroProveedor() == null || proveedor.getNumeroProveedor() == 0){
            mensaje.mensaje("Debes capturar el número de proveedor", "amarillo");
            bandera = false;
        }
        return bandera;
    }

    public void validaNumeroProveedor() {
        numeroProveedor = proveedor.getNumeroProveedor();
        if (!this.consultaNumeroProveedor()) { 
            proveedor.setNumeroProveedor(null);
            mensaje.mensaje("El número de proveedor ya esta asignado a un proveedor activo", "amarillo");
        }
    }

    public boolean consultaNumeroProveedor() {
        boolean band = true;
        if (numeroProveedorAnt.intValue() != numeroProveedor.intValue()) {
            Proveedores p = proveedorService.getByNumeroProveedor(numeroProveedor);
            if (p != null) {
                band = false;
            }
        }
        return band;
    }

    public boolean validarAgregar() {
        if(!this.consultaNumeroProveedor()){
            return false;
        }
        Proveedores prov = proveedorService.obtenerProveedorByNombre(nombreProveedor);
        if (prov != null) {
            if (Objects.equals(prov.getIdProveedor(), proveedor.getIdProveedor())) {
                return true;
            } else {
                return false;
            }
        }
        return true;
    }

    public TipoProveedorService getTipoProveedorService() {
        return tipoProveedorService;
    }

    public void setTipoProveedorService(TipoProveedorService tipoProveedorService) {
        this.tipoProveedorService = tipoProveedorService;
    }

    public String getCorreo() {
        return correo;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }

    public String getNombreProveedor() {
        return nombreProveedor;
    }

    public void setNombreProveedor(String nombreProveedor) {
        this.nombreProveedor = nombreProveedor;
    }

    public Usuarios getUsuarioLogin() {
        return usuarioLogin;
    }

    public void setUsuarioLogin(Usuarios usuarioLogin) {
        this.usuarioLogin = usuarioLogin;
    }

    public Contactos getContacto() {
        return contacto;
    }

    public void setContacto(Contactos contacto) {
        this.contacto = contacto;
    }

    public Proveedores getProveedor() {
        return proveedor;
    }

    public void setProveedor(Proveedores proveedor) {
        this.proveedor = proveedor;
    }

    public List<Contactos> getContactoList() {
        return contactoList;
    }

    public void setContactoList(List<Contactos> contactoList) {
        this.contactoList = contactoList;
    }

    public List<Proveedores> getProveedoresList() {
        return proveedoresList;
    }

    public void setProveedoresList(List<Proveedores> proveedoresList) {
        this.proveedoresList = proveedoresList;
    }

    public List<TipoProveedor> getTipoProveedoresList() {
        return tipoProveedoresList;
    }

    public void setTipoProveedoresList(List<TipoProveedor> tipoProveedoresList) {
        this.tipoProveedoresList = tipoProveedoresList;
    }

}
