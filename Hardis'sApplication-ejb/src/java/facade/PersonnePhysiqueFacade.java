/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package facade;

import entitee.Devis;
import entitee.Genre;
import entitee.PersonnePhysique;
import entitee.Population;
import java.util.Date;
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
public class PersonnePhysiqueFacade extends AbstractFacade<PersonnePhysique> implements PersonnePhysiqueFacadeLocal {

    @PersistenceContext(unitName = "Hardis_sApplication-ejbPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public PersonnePhysiqueFacade() {
        super(PersonnePhysique.class);
    }

    @Override
    public PersonnePhysique creerPersonnePhysique(String nom, String prenom, Date dateNaiss, String numeroSS, String adresse, String mail, Genre genre, boolean adherentCAS,Population laPopulation) {
        PersonnePhysique pers = new PersonnePhysique();
        pers.setAdherentCAS(adherentCAS);
        pers.setAdresse(adresse);
        pers.setDateNaiss(dateNaiss);
        pers.setGenre(genre);
        pers.setLaPopulation(laPopulation);
        pers.setMail(mail);
        pers.setNom(nom);
        pers.setNumeroSS(numeroSS);
        pers.setPrenom(prenom);
        
        em.persist(pers);
        return pers;
    }

    @Override
    public PersonnePhysique recherchePersonnePhysique(String nom, String prenom) {
         PersonnePhysique pers;
        String txt="SELECT pers FROM PersonnePhysique AS pers WHERE pers.Nom=:nom and pers.Prenom=:prenom";
        Query req=getEntityManager().createQuery(txt);
        req=req.setParameter("nom",nom);
        req=req.setParameter("prenom",prenom);
        pers=null;
        List <PersonnePhysique> result = req.getResultList();
        if (result.size()==1)
            {pers=(PersonnePhysique)result.get(0);}
        
        return pers;
         
    }

    @Override
    public PersonnePhysique modifierAdresse(PersonnePhysique pers, String adresse) {
        pers.setAdresse(adresse);
        em.merge(pers);
        
        return pers;
    }

    @Override
    public PersonnePhysique modifierMail(PersonnePhysique pers, String mail) {
        pers.setAdresse(mail);
        em.merge(pers);
        
        return pers;
    }

    @Override
    public PersonnePhysique authentificationAffilie(String login, String mdp) {
        PersonnePhysique pers;
        String txt="SELECT pers FROM PersonnePhysique AS pers WHERE pers.Login=:lo and pers.Mdp=:mdp";
        Query req=getEntityManager().createQuery(txt);
        req=req.setParameter("lo",login);
        req=req.setParameter("motp",mdp);
        pers=null;
        List <PersonnePhysique> result = req.getResultList();
        if (result.size()==1)
            {pers=(PersonnePhysique)result.get(0);};
        return pers;
    }

    @Override
    public PersonnePhysique modifierMdp(String nvMDP, PersonnePhysique pers) {
        pers.setMdp(nvMDP);
        
        em.merge(pers);
        return pers;
    }

    @Override
    public PersonnePhysique enregistrerCompte(PersonnePhysique pers, String login, String mdp) {
        pers.setMdp(mdp);
        pers.setLogin(login);
        
        em.merge(pers);
        return pers;
    }

    @Override
    public boolean rechercheDispoLogin(String logintest) {
        boolean existant=true;
        
        String txt="SELECT pers FROM PersonnePhysique AS pers WHERE pers.Login=:lo";
        Query req=getEntityManager().createQuery(txt);
        req=req.setParameter("lo",logintest);

        List <PersonnePhysique> result = req.getResultList();
        if (result.isEmpty())
            {existant=false;};

        return existant;
    }
    
    
    
    
    
    
    
    
    
}
