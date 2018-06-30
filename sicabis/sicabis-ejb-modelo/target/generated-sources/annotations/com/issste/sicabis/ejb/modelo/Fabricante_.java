package com.issste.sicabis.ejb.modelo;

import com.issste.sicabis.ejb.modelo.ProveedorFabricante;
import com.issste.sicabis.ejb.modelo.Remisiones;
import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2018-06-22T12:18:02")
@StaticMetamodel(Fabricante.class)
public class Fabricante_ { 

    public static volatile SingularAttribute<Fabricante, String> nombre;
    public static volatile SingularAttribute<Fabricante, Integer> idFabricante;
    public static volatile SingularAttribute<Fabricante, String> usuarioModificacion;
    public static volatile SingularAttribute<Fabricante, String> pais;
    public static volatile SingularAttribute<Fabricante, Date> fechaBaja;
    public static volatile SingularAttribute<Fabricante, String> registroSanitario;
    public static volatile ListAttribute<Fabricante, Remisiones> remisionesList;
    public static volatile SingularAttribute<Fabricante, String> usuarioBaja;
    public static volatile SingularAttribute<Fabricante, Date> fechaAlta;
    public static volatile ListAttribute<Fabricante, ProveedorFabricante> proveedorFabricanteList;
    public static volatile SingularAttribute<Fabricante, Date> fechaModificacion;
    public static volatile SingularAttribute<Fabricante, String> usuarioAlta;
    public static volatile SingularAttribute<Fabricante, Integer> activo;

}