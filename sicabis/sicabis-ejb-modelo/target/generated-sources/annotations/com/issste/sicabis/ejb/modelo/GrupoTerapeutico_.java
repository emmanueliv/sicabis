package com.issste.sicabis.ejb.modelo;

import com.issste.sicabis.ejb.modelo.Insumos;
import com.issste.sicabis.ejb.modelo.RcbInsumos;
import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2018-06-22T12:18:02")
@StaticMetamodel(GrupoTerapeutico.class)
public class GrupoTerapeutico_ { 

    public static volatile SingularAttribute<GrupoTerapeutico, String> usuarioModificacion;
    public static volatile ListAttribute<GrupoTerapeutico, Insumos> insumosList;
    public static volatile SingularAttribute<GrupoTerapeutico, Date> fechaBaja;
    public static volatile SingularAttribute<GrupoTerapeutico, String> usuarioBaja;
    public static volatile SingularAttribute<GrupoTerapeutico, String> descripcion;
    public static volatile ListAttribute<GrupoTerapeutico, RcbInsumos> rcbInsumosList;
    public static volatile SingularAttribute<GrupoTerapeutico, Integer> idGrupoTerapeutico;
    public static volatile SingularAttribute<GrupoTerapeutico, Date> fechaAlta;
    public static volatile SingularAttribute<GrupoTerapeutico, Date> fechaModificacion;
    public static volatile SingularAttribute<GrupoTerapeutico, String> usuarioAlta;
    public static volatile SingularAttribute<GrupoTerapeutico, Integer> activo;

}