package com.issste.sicabis.ejb.modelo;

import com.issste.sicabis.ejb.modelo.Insumos;
import com.issste.sicabis.ejb.modelo.RcbInsumos;
import com.issste.sicabis.ejb.modelo.UnidadesMedicas;
import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2018-06-22T12:18:02")
@StaticMetamodel(Nivel.class)
public class Nivel_ { 

    public static volatile SingularAttribute<Nivel, Integer> idNivel;
    public static volatile ListAttribute<Nivel, Insumos> insumosList;
    public static volatile SingularAttribute<Nivel, String> usuarioModificacion;
    public static volatile SingularAttribute<Nivel, Date> fechaBaja;
    public static volatile SingularAttribute<Nivel, String> usuarioBaja;
    public static volatile ListAttribute<Nivel, RcbInsumos> rcbInsumosList;
    public static volatile SingularAttribute<Nivel, String> descripcion;
    public static volatile SingularAttribute<Nivel, Date> fechaAlta;
    public static volatile SingularAttribute<Nivel, Date> fechaModificacion;
    public static volatile ListAttribute<Nivel, UnidadesMedicas> unidadesMedicasList;
    public static volatile SingularAttribute<Nivel, String> usuarioAlta;
    public static volatile SingularAttribute<Nivel, Integer> activo;

}