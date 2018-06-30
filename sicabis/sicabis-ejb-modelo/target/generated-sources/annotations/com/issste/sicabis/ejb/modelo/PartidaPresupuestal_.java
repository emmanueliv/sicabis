package com.issste.sicabis.ejb.modelo;

import com.issste.sicabis.ejb.modelo.Contratos;
import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2018-06-22T12:18:02")
@StaticMetamodel(PartidaPresupuestal.class)
public class PartidaPresupuestal_ { 

    public static volatile SingularAttribute<PartidaPresupuestal, String> usuarioModificacion;
    public static volatile SingularAttribute<PartidaPresupuestal, String> descripcion;
    public static volatile SingularAttribute<PartidaPresupuestal, Integer> idPadre;
    public static volatile SingularAttribute<PartidaPresupuestal, String> partidaPresupuestal;
    public static volatile SingularAttribute<PartidaPresupuestal, Integer> activo;
    public static volatile SingularAttribute<PartidaPresupuestal, Date> fechaBaja;
    public static volatile SingularAttribute<PartidaPresupuestal, String> usuarioBaja;
    public static volatile SingularAttribute<PartidaPresupuestal, Date> fechaAlta;
    public static volatile SingularAttribute<PartidaPresupuestal, Date> fechaModificacion;
    public static volatile SingularAttribute<PartidaPresupuestal, String> partida;
    public static volatile SingularAttribute<PartidaPresupuestal, Integer> idPartidaPresupuestal;
    public static volatile SingularAttribute<PartidaPresupuestal, String> usuarioAlta;
    public static volatile ListAttribute<PartidaPresupuestal, Contratos> contratosList;

}