package com.issste.sicabis.ejb.modelo;

import com.issste.sicabis.ejb.modelo.Estatus;
import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2018-06-22T12:18:02")
@StaticMetamodel(EstatusSiguiente.class)
public class EstatusSiguiente_ { 

    public static volatile SingularAttribute<EstatusSiguiente, Integer> idTarea;
    public static volatile SingularAttribute<EstatusSiguiente, String> usuarioModificacion;
    public static volatile SingularAttribute<EstatusSiguiente, Date> fechaBaja;
    public static volatile SingularAttribute<EstatusSiguiente, String> usuarioBaja;
    public static volatile SingularAttribute<EstatusSiguiente, Estatus> estatusFinal;
    public static volatile SingularAttribute<EstatusSiguiente, Date> fechaAlta;
    public static volatile SingularAttribute<EstatusSiguiente, Estatus> estatusInicial;
    public static volatile SingularAttribute<EstatusSiguiente, Date> fechaModificacion;
    public static volatile SingularAttribute<EstatusSiguiente, String> usuarioAlta;
    public static volatile SingularAttribute<EstatusSiguiente, Integer> idEstatusSiguiente;
    public static volatile SingularAttribute<EstatusSiguiente, Integer> activo;

}