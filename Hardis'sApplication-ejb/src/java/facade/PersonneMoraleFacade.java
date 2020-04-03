/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package facade;

import entitee.Activite;
import entitee.PersonneMorale;
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
public class PersonneMoraleFacade extends AbstractFacade<PersonneMorale> implements PersonneMoraleFacadeLocal {

    @PersistenceContext(unitName = "Hardis_sApplication-ejbPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public PersonneMoraleFacade() {
        super(PersonneMorale.class);
    }
    
    @Override
    public PersonneMorale creerPersonneMorale(String siret, String raison, String adresse, Activite activite){
        em.flush();
        PersonneMorale personne = new PersonneMorale();
        personne.setSIRET(siret);
        personne.setRaisonSociale(raison);
        personne.setAdresse(adresse);
        personne.setLaActivite(activite);
        em.persist(personne);
        return personne;
    }
    
    @Override
    public PersonneMorale modifierAdresse(PersonneMorale personne, String adresse){
        personne.setAdresse(adresse);
        em.merge(personne);
        return personne;
    }

    @Override
    public PersonneMorale rechercheExistantID(long idpers) {
        PersonneMorale persmor;
        String txt="SELECT persmo FROM PersonneMorale AS persmo WHERE persmo.id=:ii";
        Query req=getEntityManager().createQuery(txt);
        req=req.setParameter("ii",idpers);
        persmor=null;
        List <PersonneMorale> result = req.getResultList();
        if (result.size()==1)
            {persmor=(PersonneMorale)result.get(0);};
        return persmor;
    }

    @Override
    public boolean rechercheDispoLogin(String logintest) {
        String txt="SELECT pers FROM PersonneMorale AS pers WHERE pers.Login=:lo";
        Query req=getEntityManager().createQuery(txt);
        req=req.setParameter("lo",logintest);

        List <PersonneMorale> result = req.getResultList();
        return result.isEmpty();
    }

    @Override
    public List<PersonneMorale> recherchePersmo() {
        List<PersonneMorale> result=null;
        String txt="SELECT per FROM PersonneMorale AS per";
        Query req=getEntityManager().createQuery(txt);
        result = req.getResultList();
        return result;
    }
    
    
    
}
