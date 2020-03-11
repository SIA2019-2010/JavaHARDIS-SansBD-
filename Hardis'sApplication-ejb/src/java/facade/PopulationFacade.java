/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package facade;

import entitee.Population;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author lixin
 */
@Stateless
public class PopulationFacade extends AbstractFacade<Population> implements PopulationFacadeLocal {

    @PersistenceContext(unitName = "Hardis_sApplication-ejbPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public PopulationFacade() {
        super(Population.class);
    }
    
    @Override
    public Population creerPopulation(String libelle) {
        Population popu=new Population();
        popu.setLibellePopulation(libelle);
        em.persist(popu);
        return popu;
    }
    
    @Override
    public Population modifierLibellePopulation(Population popu, String libelle) {
        popu.setLibellePopulation(libelle);
        em.merge(popu);
        return popu;
    }
    
}
