/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.issste.sicabis.ejb.DAO;

import com.issste.sicabis.ejb.modelo.ColorPorcentaje;
import com.issste.sicabis.ejb.utils.ErrorUtil;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

/**
 *
 * @author 6JWBBG2
 */
@Stateless
public class ColorPorcentajeDAOImplement implements ColorPorcentajeDAO {

    @PersistenceContext(unitName = "sicabis")
    private EntityManager em;

    @Override
    public List<ColorPorcentaje> colorPorcentajeAllActivos() {
        List<ColorPorcentaje> resultList = null;
        try {
            resultList = em.createNamedQuery("ColorPorcentaje.findByEstatusActivo").setParameter("estusactivo", 1).getResultList();
        } catch (Exception e) {
            Logger.getLogger(ColorPorcentajeDAOImplement.class.getName()).log(Level.SEVERE, ErrorUtil.CODE_0101, e);
        }
        return resultList;

    }

    @Override
    public List<ColorPorcentaje> colorPorcentajeAllInactivos() {
        List<ColorPorcentaje> resultList = null;
        try {
            resultList = em.createNamedQuery("ColorPorcentaje.findByEstatusActivo").setParameter("estusactivo", 0).getResultList();
        } catch (Exception e) {
            Logger.getLogger(ColorPorcentajeDAOImplement.class.getName()).log(Level.SEVERE, ErrorUtil.CODE_0101, e);
        }
        System.out.println("Lista de colores inacctivos :" + resultList);
        return resultList;
    }

    @Override
    public List<ColorPorcentaje> colorPorcentajeAll() {
        List<ColorPorcentaje> resultList = null;
        try {
            resultList = em.createNamedQuery("ColorPorcentaje.findAll").getResultList();
        } catch (Exception e) {
            Logger.getLogger(ColorPorcentajeDAOImplement.class.getName()).log(Level.SEVERE, ErrorUtil.CODE_0101, e);
        }
        return resultList;
    }

    @Override
    public boolean guardarActualizaColorPorcentaje(ColorPorcentaje colorPorcentaje) {
        boolean result = false;
        try {
            if (colorPorcentaje.getIdColorPorcentaje() == null) {
                em.persist(colorPorcentaje);
                result = true;
            } else {
                em.merge(colorPorcentaje);
                result = true;
            }
        } catch (Exception e) {
            Logger.getLogger(ColorPorcentajeDAOImplement.class.getName()).log(Level.SEVERE, ErrorUtil.CODE_0101, e);
        }
        return result;
    }

    @Override
    public boolean eliminaColorPorcentaje(ColorPorcentaje colorPorcentaje) {
        boolean result = false;
        try {
            Query query = em.createNativeQuery("DELETE FROM color_porcentaje WHERE id_color_porcentaje = :idColorPorcentaje");
            query.setParameter("idColorPorcentaje", colorPorcentaje.getIdColorPorcentaje()).executeUpdate();
            em.flush();
            em.getEntityManagerFactory().getCache().evictAll();
            result = true;
        } catch (Exception e) {
            Logger.getLogger(ColorPorcentajeDAOImplement.class.getName()).log(Level.SEVERE, "", e);

        }
        return result;
    }

    @Override
    public ColorPorcentaje validaExistenciaFiltroColorPorcentaje(ColorPorcentaje colorPorcentaje) {
        List<ColorPorcentaje> resultList = null;
        ColorPorcentaje filtroEncontrado = null;
        try {
            resultList
                    = em.createNamedQuery("ColorPorcentaje.findByPorcentajeInicialFinalColor").setParameter("porcentajeInicial", colorPorcentaje.getPorcentajeInicial()).setParameter("porcentajeFinal", colorPorcentaje.getPorcentajeFinal()).setParameter("hexcolor", colorPorcentaje.getHexColor()).getResultList();

        } catch (Exception e) {

            Logger.getLogger(ColorPorcentajeDAOImplement.class.getName()).log(Level.SEVERE, ErrorUtil.CODE_0101, e);
        }
        if (resultList != null && resultList.size() > 0) {
            filtroEncontrado = resultList.get(0);
        } else {
            filtroEncontrado = null;
        }
        return filtroEncontrado;
    }

}
