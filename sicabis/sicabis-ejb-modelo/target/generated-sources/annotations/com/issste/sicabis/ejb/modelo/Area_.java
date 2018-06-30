package com.issste.sicabis.ejb.modelo;

import com.issste.sicabis.ejb.modelo.AsignacionInsumos;
import com.issste.sicabis.ejb.modelo.Cr;
import com.issste.sicabis.ejb.modelo.Jefatura;
import com.issste.sicabis.ejb.modelo.PeriodoArea;
import com.issste.sicabis.ejb.modelo.PeriodoMes;
import com.issste.sicabis.ejb.modelo.Planeacion;
import com.issste.sicabis.ejb.modelo.Rcb;
import com.issste.sicabis.ejb.modelo.Solicitudes;
import com.issste.sicabis.ejb.modelo.Tareas;
import com.issste.sicabis.ejb.modelo.Usuarios;
import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2018-06-22T12:18:02")
@StaticMetamodel(Area.class)
public class Area_ { 

    public static volatile SingularAttribute<Area, Tareas> idTarea;
    public static volatile ListAttribute<Area, Jefatura> jefaturaList;
    public static volatile SingularAttribute<Area, String> usuarioModificacion;
    public static volatile SingularAttribute<Area, String> nombreArea;
    public static volatile SingularAttribute<Area, Integer> idPadre;
    public static volatile ListAttribute<Area, Solicitudes> solicitudesList;
    public static volatile ListAttribute<Area, PeriodoMes> periodoMesList;
    public static volatile ListAttribute<Area, Cr> crList;
    public static volatile ListAttribute<Area, Planeacion> planeacionList;
    public static volatile SingularAttribute<Area, Integer> activo;
    public static volatile ListAttribute<Area, PeriodoArea> periodoAreaList;
    public static volatile SingularAttribute<Area, Date> fechaBaja;
    public static volatile SingularAttribute<Area, String> usuarioBaja;
    public static volatile SingularAttribute<Area, Integer> idArea;
    public static volatile ListAttribute<Area, AsignacionInsumos> asignacionInsumosList;
    public static volatile SingularAttribute<Area, Date> fechaAlta;
    public static volatile SingularAttribute<Area, Integer> maestra;
    public static volatile ListAttribute<Area, Usuarios> usuariosList;
    public static volatile SingularAttribute<Area, Date> fechaModificacion;
    public static volatile SingularAttribute<Area, String> usuarioAlta;
    public static volatile ListAttribute<Area, Rcb> rcbList;

}