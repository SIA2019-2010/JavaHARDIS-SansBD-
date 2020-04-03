/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package facade;

import entitee.Domaine;
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
public class DomaineFacade extends AbstractFacade<Domaine> implements DomaineFacadeLocal {

    @PersistenceContext(unitName = "Hardis_sApplication-ejbPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public DomaineFacade() {
        super(Domaine.class);
    }
    
    @Override
    public Domaine creerDomaine(String libelleDomaine) {
        em.flush();
        Domaine domaine = new Domaine();
        domaine.setLibelleDomaine(libelleDomaine);
        em.persist(domaine);
        return domaine;
    }
    
    @Override
    public Domaine modifierLibelleDomaine(Domaine domaine, String libelleDomaine){
        domaine.setLibelleDomaine(libelleDomaine);
        em.merge(domaine);
        return domaine;
    }

    @Override
    public Domaine rechercheExistantID(Long iddo) {
        Domaine dom = null;
        String txt = "SELECT d FROM Domaine AS d WHERE d.id=:ii";
        Query req = getEntityManager().createQuery(txt); 
        req = req.setParameter("ii",iddo);
        List<Domaine> result = req.getResultList();
        if(result.size()==1){
            dom = (Domaine)result.get(0);
        }
        return dom;
    }
  
    
    
    
    
    
}
