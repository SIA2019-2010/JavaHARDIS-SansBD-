/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package facade;

import entitee.BaseRemboursementSecu;
import entitee.Garantie;
import entitee.TypeGarantie;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

/**
 *
 * @author lixin
 */
@Stateless
public class GarantieFacade extends AbstractFacade<Garantie> implements GarantieFacadeLocal {

    @PersistenceContext(unitName = "Hardis_sApplication-ejbPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public GarantieFacade() {
        super(Garantie.class);
    }
    
    @Override
    public Garantie creerGarantie(String libellegarantie, BaseRemboursementSecu labase, List<TypeGarantie> lestypes) {
        em.flush();
        Garantie garantie=new Garantie();
        garantie.setLibelleGarantie(libellegarantie);
        em.persist(garantie);
        return garantie;
    }
    
    @Override
    public Garantie modifierLibelleGarantie(Garantie garantie, String libellegarantie) {
        garantie.setLibelleGarantie(libellegarantie);
        em.merge(garantie);
        return garantie;
    }

    @Override
    public Garantie recupererGarantieID(Long idgar) {
        Garantie pers;
        String txt="SELECT gar FROM Garantie AS gar WHERE gar.id=:idd";
        Query req=getEntityManager().createQuery(txt);
        req=req.setParameter("idd",idgar);
        pers=null;
        List <Garantie> result = req.getResultList();
        if (result.size()==1)
            {pers=(Garantie)result.get(0);};
        return pers;
    }
 
    
    
    
    
    
    
}
