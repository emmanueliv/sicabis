/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.issste.sicabis.ejb.DAO;

import com.issste.sicabis.ejb.modelo.TipoProveedor;
import com.issste.sicabis.ejb.utils.ErrorUtil;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author kriosoft
 */
@Stateless
public class TipoProveedorDAOImplement implements TipoProveedorDAO {

    @PersistenceContext(unitName = "sicabis")
    private EntityManager em;

    @Override
    public List<TipoProveedor> obtenerTipoProveedores(){
        return (List<TipoProveedor>) em.createNamedQuery("TipoProveedor.findAll").getResultList();
    }

    @Override
    public List<TipoProveedor> obtenerTipoProveedoresByNombre(String nombre) {
        return (List<TipoProveedor>) em.createNamedQuery("TipoProveedor.findByDescripcion").setParameter("descripcion", nombre).getResultList();
    }

    @Override
    public void guardarTipoDocumento(TipoProveedor tipoProveedor) {
        try {
            if (tipoProveedor.getIdTipoProveedor() == null) {
                em.persist(tipoProveedor);
            } else {
                em.merge(tipoProveedor);
            }
        } catch (Exception e) {
            Logger.getLogger(TipoDocumentosDAOImplement.class.getName()).log(Level.SEVERE, ErrorUtil.CODE_0101, e);
        }
    }
    
    @Override
    public TipoProveedor obtenerByTipo(String tipo){
        try {
            return (TipoProveedor) em.createQuery("Select tp from TipoProveedor tp Where tp.activo = 1 and tp.tipo = :tipo").setParameter("tipo", tipo).getSingleResult();
        } catch (Exception e) {
            Logger.getLogger(TipoDocumentosDAOImplement.class.getName()).log(Level.SEVERE, ErrorUtil.CODE_0101, e);
            return null;
        }
    }

}
