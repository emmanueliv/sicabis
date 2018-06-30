package com.issste.sicabis.ejb.modelo;

import com.issste.sicabis.ejb.modelo.AlertasCorreo;
import com.issste.sicabis.ejb.modelo.AlertasDpn;
import com.issste.sicabis.ejb.modelo.Area;
import com.issste.sicabis.ejb.modelo.CatDetalleIm;
import com.issste.sicabis.ejb.modelo.Delegaciones;
import com.issste.sicabis.ejb.modelo.UnidadesMedicas;
import com.issste.sicabis.ejb.modelo.Ur;
import com.issste.sicabis.ejb.modelo.UsuarioPerfil;
import com.issste.sicabis.ejb.modelo.UsuariosTipoUsuarios;
import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2018-06-22T12:18:02")
@StaticMetamodel(Usuarios.class)
public class Usuarios_ { 

    public static volatile SingularAttribute<Usuarios, String> usuario;
    public static volatile SingularAttribute<Usuarios, String> apellidoPaterno;
    public static volatile SingularAttribute<Usuarios, String> nombre;
    public static volatile SingularAttribute<Usuarios, Integer> idUsuario;
    public static volatile SingularAttribute<Usuarios, Date> fechaBaja;
    public static volatile SingularAttribute<Usuarios, String> usuarioBaja;
    public static volatile SingularAttribute<Usuarios, Integer> idUsuarioSuperior;
    public static volatile SingularAttribute<Usuarios, Date> ultimoAcceso;
    public static volatile SingularAttribute<Usuarios, String> apellidoMaterno;
    public static volatile SingularAttribute<Usuarios, Date> fechaModificacion;
    public static volatile SingularAttribute<Usuarios, UnidadesMedicas> idUnidadMedica;
    public static volatile SingularAttribute<Usuarios, Integer> primerAcceso;
    public static volatile SingularAttribute<Usuarios, Date> fechaCambioContrasenia;
    public static volatile SingularAttribute<Usuarios, String> usuarioModificacion;
    public static volatile ListAttribute<Usuarios, UsuarioPerfil> usuarioPerfilList;
    public static volatile SingularAttribute<Usuarios, String> contrasenia;
    public static volatile SingularAttribute<Usuarios, String> telefono;
    public static volatile SingularAttribute<Usuarios, Ur> ur;
    public static volatile SingularAttribute<Usuarios, Integer> activo;
    public static volatile SingularAttribute<Usuarios, Delegaciones> idDelegacion;
    public static volatile SingularAttribute<Usuarios, Area> idArea;
    public static volatile ListAttribute<Usuarios, AlertasCorreo> alertasCorreoList;
    public static volatile SingularAttribute<Usuarios, Date> fechaAlta;
    public static volatile ListAttribute<Usuarios, CatDetalleIm> catDetalleImList;
    public static volatile ListAttribute<Usuarios, UsuariosTipoUsuarios> usuariosTipoUsuariosList;
    public static volatile ListAttribute<Usuarios, AlertasDpn> alertasDpnList;
    public static volatile SingularAttribute<Usuarios, String> usuarioAlta;
    public static volatile SingularAttribute<Usuarios, String> correo;

}