package com.issste.sicabis.ejb.modelo;

import com.issste.sicabis.ejb.modelo.Jefatura;
import com.issste.sicabis.ejb.modelo.Usuarios;
import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2018-06-22T12:18:02")
@StaticMetamodel(CatDetalleIm.class)
public class CatDetalleIm_ { 

    public static volatile SingularAttribute<CatDetalleIm, String> penasAtraso;
    public static volatile SingularAttribute<CatDetalleIm, String> usuarioModificacion;
    public static volatile SingularAttribute<CatDetalleIm, String> formaPago;
    public static volatile SingularAttribute<CatDetalleIm, String> garantias;
    public static volatile SingularAttribute<CatDetalleIm, String> plazo;
    public static volatile SingularAttribute<CatDetalleIm, Integer> activo;
    public static volatile SingularAttribute<CatDetalleIm, Jefatura> idJefatura;
    public static volatile SingularAttribute<CatDetalleIm, String> condicionesEntrega;
    public static volatile SingularAttribute<CatDetalleIm, Usuarios> idUsuario;
    public static volatile SingularAttribute<CatDetalleIm, Date> fechaBaja;
    public static volatile SingularAttribute<CatDetalleIm, Integer> idCatDetalleIm;
    public static volatile SingularAttribute<CatDetalleIm, String> usuarioBaja;
    public static volatile SingularAttribute<CatDetalleIm, String> normasCumplir;
    public static volatile SingularAttribute<CatDetalleIm, Date> fechaAlta;
    public static volatile SingularAttribute<CatDetalleIm, Date> fechaModificacion;
    public static volatile SingularAttribute<CatDetalleIm, String> usuarioAlta;

}