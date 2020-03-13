/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package facade;

import entitee.Population;
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

    @Override
    public Population rechercheExistantPopulationID(long idd) {
            Population popu;
        String txt="SELECT pop FROM Population AS pop WHERE pop.Id=:id";
        Query req=getEntityManager().createQuery(txt);
        req=req.setParameter("id",idd);
        popu=null;
        List <Population> result = req.getResultList();
        if (result.size()==1)
            {popu=(Population)result.get(0);};
        return popu;
    }

    @Override
    public List<Population> recherchePopulations() {
        List<Population> result=null;
        String txt="SELECT p FROM Population AS p";
        Query req=getEntityManager().createQuery(txt);
        result = req.getResultList();
        return result;
    }
    
    
    
    
}

