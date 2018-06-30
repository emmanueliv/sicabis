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
import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.servlet.ServletContext;
import org.primefaces.context.RequestContext;

/**
 *
 * @author kriosoft
 */
public class ProveedoresBean implements Serializable {

    @EJB
    private BitacoraTareaSerice bitacoraTareaSerice;

    @EJB
    private TipoProveedorService tipoProveedorService;

    @EJB
    private ProveedorService proveedorService;

    private String correo;
    private String nombreProveedor;
    private String nombreProveedorB;
    private Integer numeroProveedor;
    private Integer numeroProveedorB;

    private Usuarios usuarioLogin;
    private Contactos contacto;
    private Proveedores proveedor;
    private BitacoraTareaEstatus bitacoraTareaEstatus;
    private List<Contactos> contactoList;
    private List<Proveedores> proveedoresList;
    private List<TipoProveedor> tipoProveedoresList;

    private Utilidades util = new Utilidades();
    private Mensajes mensaje = new Mensajes();

    public ProveedoresBean() {
        usuarioLogin = new Usuarios();
        contacto = new Contactos();
        proveedor = new Proveedores();
        contactoList = new ArrayList<>();
        proveedoresList = new ArrayList<>();
        tipoProveedoresList = new ArrayList<>();
        bitacoraTareaEstatus = new BitacoraTareaEstatus();
    }

    @PostConstruct
    public void init() {
        usuarioLogin = (Usuarios) util.getSessionAtributte("usuario");
        tipoProveedoresList = tipoProveedorService.obtenerTipoProveedores();
    }

    public void guardarProveedor() {
        nombreProveedor = proveedor.getNombreProveedor();
        numeroProveedor = proveedor.getNumeroProveedor();
        if (validar()) {
            if (validarAgregar()) {
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
                proveedor.setActivo(1);
                proveedor.setFechaAlta(new Date());
                proveedor.setUsuarioAlta(usuarioLogin.getUsuario());
                proveedor.setAutorizado(1);
                proveedorService.guardarProveedor(proveedor);
                bitacoraTareaEstatus.setDescripcion("Guardar proveedor:" + nombreProveedor + "");
                bitacoraTareaEstatus.setFecha(new Date());
                bitacoraTareaEstatus.setIdModulos(4);
                bitacoraTareaEstatus.setIdUsuarios(usuarioLogin.getIdUsuario());
                bitacoraTareaEstatus.setIdTareaId(16);
                bitacoraTareaSerice.guardarEnBitacora(bitacoraTareaEstatus);
                proveedor = new Proveedores();
                contactoList = new ArrayList<>();
                mensaje.mensaje("El proveedor " + nombreProveedor + " se ha guardado correctamente.", "verde");
            } else {
                mensaje.mensaje("El proveedor " + nombreProveedor + " o número de proveedor " + numeroProveedor +" ya existe.", "amarillo");
            }
        }
    }

    public boolean validar() {
        boolean bandera = true;
        if (proveedor.getNombreProveedor() == null || proveedor.getNombreProveedor().equals("")) {
            mensaje.mensaje("Debes capturar el nombre del proveedor", "amarillo");
            bandera = false;
        }
        if(proveedor.getNumeroProveedor() == null || proveedor.getNumeroProveedor() == 0){
            mensaje.mensaje("Debes capturar el número de proveedor", "amarillo");
            bandera = false;
        }
        if (proveedor.getIdTipoProveedor() == null || proveedor.getIdTipoProveedor().equals("")) {
            mensaje.mensaje("Debes seleccionar el tipo de proveedor", "amarillo");
            bandera = false;
        }
        if (!proveedor.getCorreo().equals("")) {
            if (!util.validaCorreo(proveedor.getCorreo())) {
                mensaje.mensaje("El correo capturado es incorrecto", "amarillo");
                bandera = false;
            }
        }
        return bandera;
    }

    public Contactos getContacto() {
        return contacto;
    }

    public void setContacto(Contactos contacto) {
        this.contacto = contacto;
    }

    public boolean validarAgregar() {
        if(!this.consultaNumeroProveedor()){
            return false;
        }
        Proveedores prov = proveedorService.obtenerProveedorByNombre(nombreProveedor);
        return prov == null;
    }
    
    public void validaNumeroProveedor(){
        numeroProveedor = proveedor.getNumeroProveedor();
        if(!this.consultaNumeroProveedor()){
            proveedor.setNumeroProveedor(null);
            mensaje.mensaje("El número de proveedor ya esta asignado a un proveedor activo", "amarillo");
        }
    }
    
    public boolean consultaNumeroProveedor(){
        boolean band = true;
        Proveedores p = proveedorService.getByNumeroProveedor(numeroProveedor);
        if(p != null){
            band = false;
        }
        return band;
    }

    public void cancel() {
        ExternalContext ctx = FacesContext.getCurrentInstance().getExternalContext();
        String ctxPath = ((ServletContext) ctx.getContext()).getContextPath();
        try {
            ctx.redirect(ctxPath + "/vistas/administracion/catalogos/proveedores.xhtml");
        } catch (IOException ex) {
            Logger.getLogger(TiposDocumentoBean.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void consultarProveedores() {
        if (nombreProveedorB == null || nombreProveedorB.equals("")) {
            proveedoresList = proveedorService.getByNombreNumero(null, numeroProveedorB);
        } else {
            proveedoresList = proveedorService.getByNombreNumero("%"+nombreProveedorB+"%", numeroProveedorB);
        }
        if (proveedoresList == null || proveedoresList.isEmpty()) {
            mensaje.mensaje("No se encontraron proveedores.", "amarillo");
        }
    }

    public void mostrarDialogo(Proveedores prov) {
        nombreProveedor = prov.getNombreProveedor();
        proveedor = prov;
        RequestContext.getCurrentInstance().execute("PF('dialogEliminaProveedor').show();");
    }

    public void eliminarProveedor() {
        nombreProveedor = proveedor.getNombreProveedor();
        proveedor.setActivo(0);
        proveedor.setFechaBaja(new Date());
        proveedor.setUsuarioBaja(usuarioLogin.getUsuario());
        proveedorService.guardarProveedor(proveedor);
        bitacoraTareaEstatus.setDescripcion("Elimina proveedor:" + nombreProveedor + "");
        bitacoraTareaEstatus.setFecha(new Date());
        bitacoraTareaEstatus.setIdModulos(4);
        bitacoraTareaEstatus.setIdUsuarios(usuarioLogin.getIdUsuario());
        bitacoraTareaEstatus.setIdTareaId(16);
        bitacoraTareaSerice.guardarEnBitacora(bitacoraTareaEstatus);
        proveedoresList = new ArrayList<>();
        proveedoresList = proveedorService.proveedoresAll();
        mensaje.mensaje("El proveedor " + nombreProveedor + " ha sido dado de baja.", "verde");
    }

    public void modificarRedirect(Proveedores prov) {
        ExternalContext ctx = FacesContext.getCurrentInstance().getExternalContext();
        String ctxPath = ((ServletContext) ctx.getContext()).getContextPath();
        util.setSessionMapValue("proveedor", prov);
        try {
            ctx.redirect(ctxPath + "/vistas/administracion/catalogos/detalleProveedor.xhtml");
        } catch (IOException ex) {
            Logger.getLogger(TiposDocumentoBean.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public Proveedores getProveedor() {
        return proveedor;
    }

    public void setProveedor(Proveedores proveedor) {
        this.proveedor = proveedor;
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

    public List<Proveedores> getProveedoresList() {
        return proveedoresList;
    }

    public void setProveedoresList(List<Proveedores> proveedoresList) {
        this.proveedoresList = proveedoresList;
    }

    public List<Contactos> getContactoList() {
        return contactoList;
    }

    public void setContactoList(List<Contactos> contactoList) {
        this.contactoList = contactoList;
    }

    public List<TipoProveedor> getTipoProveedoresList() {
        return tipoProveedoresList;
    }

    public void setTipoProveedoresList(List<TipoProveedor> tipoProveedoresList) {
        this.tipoProveedoresList = tipoProveedoresList;
    }

    public Integer getNumeroProveedor() {
        return numeroProveedor;
    }

    public void setNumeroProveedor(Integer numeroProveedor) {
        this.numeroProveedor = numeroProveedor;
    }

    public String getNombreProveedorB() {
        return nombreProveedorB;
    }

    public void setNombreProveedorB(String nombreProveedorB) {
        this.nombreProveedorB = nombreProveedorB.toUpperCase();
    }

    public Integer getNumeroProveedorB() {
        return numeroProveedorB;
    }

    public void setNumeroProveedorB(Integer numeroProveedorB) {
        this.numeroProveedorB = numeroProveedorB;
    }

}
