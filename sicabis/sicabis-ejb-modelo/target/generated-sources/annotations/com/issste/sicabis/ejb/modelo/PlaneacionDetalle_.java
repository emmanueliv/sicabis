package com.issste.sicabis.ejb.modelo;

import com.issste.sicabis.ejb.modelo.Insumos;
import com.issste.sicabis.ejb.modelo.Pacientes;
import com.issste.sicabis.ejb.modelo.Planeacion;
import com.issste.sicabis.ejb.modelo.UnidadesMedicas;
import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2018-06-22T12:18:02")
@StaticMetamodel(PlaneacionDetalle.class)
public class PlaneacionDetalle_ { 

    public static volatile SingularAttribute<PlaneacionDetalle, Pacientes> idPaciente;
    public static volatile SingularAttribute<PlaneacionDetalle, String> usuarioModificacion;
    public static volatile SingularAttribute<PlaneacionDetalle, Planeacion> idPlaneacion;
    public static volatile SingularAttribute<PlaneacionDetalle, Insumos> idInsumo;
    public static volatile SingularAttribute<PlaneacionDetalle, Integer> cantidadSolicitada;
    public static volatile SingularAttribute<PlaneacionDetalle, Integer> activo;
    public static volatile SingularAttribute<PlaneacionDetalle, Integer> cantidadProyectada;
    public static volatile SingularAttribute<PlaneacionDetalle, Integer> necesidadPeriodoSiguiente;
    public static volatile SingularAttribute<PlaneacionDetalle, Integer> existenciasCenadi;
    public static volatile SingularAttribute<PlaneacionDetalle, UnidadesMedicas> idUnidadesMedicas;
    public static volatile SingularAttribute<PlaneacionDetalle, Date> fechaBaja;
    public static volatile SingularAttribute<PlaneacionDetalle, String> usuarioBaja;
    public static volatile SingularAttribute<PlaneacionDetalle, Integer> cantidadPromedioMensual;
    public static volatile SingularAttribute<PlaneacionDetalle, Integer> insumosPendientesContratos;
    public static volatile SingularAttribute<PlaneacionDetalle, Date> fechaAlta;
    public static volatile SingularAttribute<PlaneacionDetalle, Date> fechaModificacion;
    public static volatile SingularAttribute<PlaneacionDetalle, String> usuarioAlta;
    public static volatile SingularAttribute<PlaneacionDetalle, Integer> idPlaneacionDetalle;

}