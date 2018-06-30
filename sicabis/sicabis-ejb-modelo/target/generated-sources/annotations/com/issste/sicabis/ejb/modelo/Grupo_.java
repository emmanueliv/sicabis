package com.issste.sicabis.ejb.modelo;

import com.issste.sicabis.ejb.modelo.Insumos;
import com.issste.sicabis.ejb.modelo.RcbGrupo;
import com.issste.sicabis.ejb.modelo.RcbInsumos;
import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2018-06-22T12:18:02")
@StaticMetamodel(Grupo.class)
public class Grupo_ { 

    public static volatile SingularAttribute<Grupo, String> grupo;
    public static volatile ListAttribute<Grupo, Insumos> insumosList;
    public static volatile SingularAttribute<Grupo, String> usuarioModificacion;
    public static volatile ListAttribute<Grupo, RcbGrupo> rcbGrupoList;
    public static volatile SingularAttribute<Grupo, Date> fechaBaja;
    public static volatile SingularAttribute<Grupo, String> usuarioBaja;
    public static volatile SingularAttribute<Grupo, Integer> idGrupo;
    public static volatile ListAttribute<Grupo, RcbInsumos> rcbInsumosList;
    public static volatile SingularAttribute<Grupo, String> descripcion;
    public static volatile SingularAttribute<Grupo, Date> fechaAlta;
    public static volatile SingularAttribute<Grupo, Date> fechaModificacion;
    public static volatile SingularAttribute<Grupo, String> usuarioAlta;
    public static volatile SingularAttribute<Grupo, Integer> activo;

}