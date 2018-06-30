package com.issste.sicabis.ejb.modelo;

import com.issste.sicabis.ejb.modelo.Compradores;
import com.issste.sicabis.ejb.modelo.Contratos;
import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2018-06-22T12:18:02")
@StaticMetamodel(CompradorContrato.class)
public class CompradorContrato_ { 

    public static volatile SingularAttribute<CompradorContrato, Integer> idCompradorContrato;
    public static volatile SingularAttribute<CompradorContrato, String> usuarioModificacion;
    public static volatile SingularAttribute<CompradorContrato, Date> fechaBaja;
    public static volatile SingularAttribute<CompradorContrato, Compradores> idComprador;
    public static volatile SingularAttribute<CompradorContrato, String> usuarioBaja;
    public static volatile SingularAttribute<CompradorContrato, Date> fechaAlta;
    public static volatile SingularAttribute<CompradorContrato, Date> fechaModificacion;
    public static volatile SingularAttribute<CompradorContrato, Contratos> idContrato;
    public static volatile SingularAttribute<CompradorContrato, String> usuarioAlta;
    public static volatile SingularAttribute<CompradorContrato, Integer> activo;

}