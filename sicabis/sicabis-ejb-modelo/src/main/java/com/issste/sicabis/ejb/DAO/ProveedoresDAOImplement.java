package com.issste.sicabis.ejb.DAO;

import com.issste.sicabis.ejb.modelo.Contactos;
import com.issste.sicabis.ejb.modelo.Proveedores;
import com.issste.sicabis.ejb.utils.ErrorUtil;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

@Stateless
public class ProveedoresDAOImplement implements ProveedoresDAO {

    @PersistenceContext(unitName = "sicabis")
    private EntityManager em;

    @Override
    public List<Proveedores> proveedoresAll() {
        List<Proveedores> resultList = null;
        try {
            resultList = em.createQuery("Select p From Proveedores p where p.activo = 1").getResultList();
        } catch (Exception e) {
            Logger.getLogger(ProveedoresDAOImplement.class.getName()).log(Level.SEVERE, ErrorUtil.CODE_0101, e);
        }
        if (resultList != null && resultList.size() > 0) {
            return resultList;
        }
        return null;
    }

    @Override
    public List<Proveedores> proveedoresWhitFallo() {
        List<Proveedores> resultList = null;
        try {
            resultList = em.createNativeQuery("select unique p.* \n"
                    + " from proveedores p join fallo_procedimiento_rcb fpr on fpr.id_proveedor = p.id_proveedor", Proveedores.class).getResultList();
        } catch (Exception e) {
            Logger.getLogger(ProveedoresDAOImplement.class.getName()).log(Level.SEVERE, ErrorUtil.CODE_0101, e);
        }
        if (resultList != null && resultList.size() > 0) {
            return resultList;
        }
        return null;
    }

    @Override
    public List<Proveedores> getAllProveedoresByActivo() {
        try {
            return (List<Proveedores>) em.createNamedQuery("Proveedores.findAllByActivo").getResultList();
        } catch (Exception e) {
            Logger.getLogger(ProcedimientoDAOImplement.class.getName()).log(Level.SEVERE, ErrorUtil.CODE_0101, e);
            return null;
        }
    }

    @Override
    public Proveedores obtenerByIdProveedor(Integer idProveedor) {
        try {
            return (Proveedores) em.createNamedQuery("Proveedores.findByIdProveedor").setParameter("idProveedor", idProveedor).getSingleResult();
        } catch (Exception e) {
            Logger.getLogger(ProcedimientoDAOImplement.class.getName()).log(Level.SEVERE, ErrorUtil.CODE_0101, e);
            return null;
        }
    }

    @Override
    public void guardarProveedor(Proveedores proveedor) {
        try {
            if (proveedor.getIdProveedor() == null) {
                em.persist(proveedor);
            } else {
                em.merge(proveedor);
            }
        } catch (Exception e) {
            Logger.getLogger(ProveedoresDAOImplement.class.getName()).log(Level.SEVERE, ErrorUtil.CODE_0101, e);
        }
    }

    @Override
    public Contactos guardarCP(Contactos cp) {
        try {
            if (cp.getIdContacto() == null) {
                em.persist(cp);
            } else {
                em.merge(cp);
            }
        } catch (Exception e) {
            Logger.getLogger(ProveedoresDAOImplement.class.getName()).log(Level.SEVERE, ErrorUtil.CODE_0101, e);
        }
        return cp;
    }

    @Override
    public List<Proveedores> obtenerProveedoresByNombre(String nombreProveedor) {
        return (List<Proveedores>) em.createNamedQuery("Proveedores.findByNombreProveedor").setParameter("nombreProveedor", nombreProveedor).getResultList();
    }

    @Override
    public List<Proveedores> obtenerByAutorizado() {
        try {
            return em.createQuery("Select p From Proveedores p Where p.activo = 1 and p.autorizado = 1").getResultList();
        } catch (Exception e) {
            Logger.getLogger(ProcedimientoDAOImplement.class.getName()).log(Level.SEVERE, ErrorUtil.CODE_0101, e);
            return null;
        }
    }

    @Override
    public void guarda(Proveedores proveedor) {
        try {
            em.persist(proveedor);
        } catch (Exception e) {
            Logger.getLogger(ProveedoresDAOImplement.class.getName()).log(Level.SEVERE, ErrorUtil.CODE_0101, e);
        }
    }

    @Override
    public List<Proveedores> getByNombreNumero(String nombreProveedor, Integer numeroProveedor) {
        List<Proveedores> resultList = null;
        try {
            Query q = em.createQuery("SELECT p FROM Proveedores p WHERE p.activo = 1 AND (:nombreProveedor IS NULL OR p.nombreProveedor LIKE :nombreProveedor ) AND (:numeroProveedor IS NULL OR p.numeroProveedor = :numeroProveedor) ORDER BY p.nombreProveedor");
            q.setParameter("nombreProveedor", nombreProveedor);
            q.setParameter("numeroProveedor", numeroProveedor);
            resultList = q.getResultList();
        } catch (Exception e) {
            Logger.getLogger(ProveedoresDAOImplement.class.getName()).log(Level.SEVERE, ErrorUtil.CODE_0101, e);
        }
        if (resultList != null && resultList.size() > 0) {
            return resultList;
        }
        return null;
    }

    @Override
    public Proveedores getByNumeroProveedor(Integer numeroProveedor) {
        Proveedores p = null;
        try {
            p = (Proveedores) em.createNamedQuery("Proveedores.findByNumeroProveedor").setParameter("numeroProveedor", numeroProveedor).getSingleResult();
            return p;
        } catch (Exception e) {
            Logger.getLogger(ProcedimientoDAOImplement.class.getName()).log(Level.SEVERE, ErrorUtil.CODE_0101, e);
            return null;
        }
    }

}
