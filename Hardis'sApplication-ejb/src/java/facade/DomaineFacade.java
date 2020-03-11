/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package facade;

import entitee.Domaine;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

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
    
}
