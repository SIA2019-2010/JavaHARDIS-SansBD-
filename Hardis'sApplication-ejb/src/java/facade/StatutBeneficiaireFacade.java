/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package facade;

import entitee.Beneficiaire;
import entitee.Contrat;
import entitee.Domaine;
import entitee.PersonneMorale;
import entitee.PersonnePhysique;
import entitee.StatutBeneficiaire;
import java.util.List;
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
public class StatutBeneficiaireFacade extends AbstractFacade<StatutBeneficiaire> implements StatutBeneficiaireFacadeLocal {

    @PersistenceContext(unitName = "Hardis_sApplication-ejbPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public StatutBeneficiaireFacade() {
        super(StatutBeneficiaire.class);
    }
    
    @Override
    public StatutBeneficiaire creerStatutBeneficiaire(Date datedubut, Beneficiaire statut, Contrat lecontrat, PersonnePhysique lapersonne) {
        StatutBeneficiaire statutbeneficiaire = new StatutBeneficiaire();
        statutbeneficiaire.setDateDebutValidite(datedubut);
        statutbeneficiaire.setStatutBeneficiare(statut);
        statutbeneficiaire.setLeContrat(lecontrat);
        statutbeneficiaire.setLaPersonnePhysique(lapersonne);
        em.persist(statutbeneficiaire);
        return statutbeneficiaire;
    }
    
    
        @Override
    public StatutBeneficiaire creerStatutBeneficiaireDevis(Date datedubut, Beneficiaire statut, PersonnePhysique lapersonne) {
        StatutBeneficiaire statutbeneficiaire = new StatutBeneficiaire();
        statutbeneficiaire.setDateDebutValidite(datedubut);
        statutbeneficiaire.setStatutBeneficiare(statut);
        statutbeneficiaire.setLaPersonnePhysique(lapersonne);
        em.persist(statutbeneficiaire);
        return statutbeneficiaire;
    }
            
         
    @Override
    public StatutBeneficiaire modifierDateFinValide(StatutBeneficiaire statutbeneficiaire, Date datefin) {
        statutbeneficiaire.setDateFinValidite(datefin);
        em.persist(statutbeneficiaire);
        return statutbeneficiaire;
    }
    
    @Override
    public StatutBeneficiaire rechercherStatutBeneficiaire(long id) {
        StatutBeneficiaire statut = null;
        String txt = "SELECT s FROM StatutBeneficiaire AS s WHERE s.id=:ii";
        Query req = getEntityManager().createQuery(txt); 
        req = req.setParameter("ii",id);
        List<StatutBeneficiaire> result = req.getResultList();
        if(result.size()==1){
            statut = (StatutBeneficiaire)result.get(0);
        }
        return statut;
    }

    @Override
    public List<Contrat> rechercheContrats(PersonnePhysique persphy) {
        List<Contrat> listcontrats; 
        String tx = "SELECT stb.leContrat FROM StatutBeneficiaire AS stb where stb.laPersonnePhysique=:pers"; 
        Query req = getEntityManager().createQuery(tx); 
        req.setParameter("pers", persphy); 
        listcontrats= req.getResultList (); 
        return listcontrats;
        

    }

    @Override
    public List<Contrat> rechercheContratsAffilie(PersonnePhysique persphy) {
        List<Contrat> listcontrats; 
        Beneficiaire benef=Beneficiaire.Affilie;
        String tx = "SELECT stb.leContrat FROM StatutBeneficiaire AS stb where stb.laPersonnePhysique=:pers and stb.statutBeneficiare=:aff"; 
        Query req = getEntityManager().createQuery(tx); 
        req.setParameter("pers", persphy);
        req.setParameter("aff", benef); 
        listcontrats= req.getResultList (); 
        return listcontrats;
    }
    
    @Override
    public List<StatutBeneficiaire> rechercherStatutBeneficiaire(PersonneMorale persmo) {
        String txt = "SELECT s FROM StatutBeneficiaire AS s WHERE s.leContrat.leProduit.laPersonneMorale=:pm "
                + "order by s.leContrat.leProduit.laPersonneMorale, s.leContrat.leProduit, s.leContrat, s.statutBeneficiare";
        Query req = getEntityManager().createQuery(txt); 
        req = req.setParameter("pm",persmo);
        List<StatutBeneficiaire> result = req.getResultList();
        return result;
    }
    
    @Override
    public List<StatutBeneficiaire> rechercherStatutBeneficiaire(PersonnePhysique persph) {
        String txt = "SELECT s FROM StatutBeneficiaire AS s WHERE s.laPersonnePhysique=:pp or "
                + "s.leContrat in (SELECT s.leContrat from StatutBeneficiaire AS s "
                + "where s.laPersonnePhysique=:pp and s.statutBeneficiare=:statut) "
                + "order by s.leContrat.leProduit, s.leContrat, s.statutBeneficiare";
        Query req = getEntityManager().createQuery(txt); 
        req = req.setParameter("pp",persph);
        req = req.setParameter("statut",Beneficiaire.Affilie);//com.myexample.Beneficiaire.Affilie
        List<StatutBeneficiaire> result = req.getResultList();
        return result;
    }

    @Override
    public StatutBeneficiaire rechercheAffilieDomaine(PersonnePhysique persph, Domaine domaine) {
        StatutBeneficiaire statut = null;
        String txt = "SELECT s FROM StatutBeneficiaire AS s WHERE s.laPersonnePhysique=:pp "
                + "and s.leContrat.leProduit.leDomaine=:dom and s.statutBeneficiare=:statut";
        Query req = getEntityManager().createQuery(txt); 
        req = req.setParameter("pp",persph);
        req = req.setParameter("statut",Beneficiaire.Affilie);
        req = req.setParameter("dom", domaine);
        List<StatutBeneficiaire> result = req.getResultList();
        if(result.size()==1){
            statut = (StatutBeneficiaire)result.get(0);
        }
        return statut;
    }
    
    
    
}
