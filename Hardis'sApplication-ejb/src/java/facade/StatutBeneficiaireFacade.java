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
import entitee.StatutContrat;
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
        em.flush();
        StatutBeneficiaire statutbeneficiaire = new StatutBeneficiaire();
        statutbeneficiaire.setDateDebutValidite(datedubut);
        statutbeneficiaire.setLaBeneficiaire(statut);
        statutbeneficiaire.setLeContrat(lecontrat);
        statutbeneficiaire.setLaPersonnePhysique(lapersonne);
        em.persist(statutbeneficiaire);
        return statutbeneficiaire;
    }
    
    
        @Override
    public StatutBeneficiaire creerStatutBeneficiaireDevis(Date datedubut, Beneficiaire statut, PersonnePhysique lapersonne) {
        em.flush();
        StatutBeneficiaire statutbeneficiaire = new StatutBeneficiaire();
        statutbeneficiaire.setDateDebutValidite(datedubut);
        statutbeneficiaire.setLaBeneficiaire(statut);
        statutbeneficiaire.setLaPersonnePhysique(lapersonne);
        em.persist(statutbeneficiaire);
        return statutbeneficiaire;
    }
            
         
    @Override
    public StatutBeneficiaire modifierDateFinValide(StatutBeneficiaire statutbeneficiaire, Date datefin) {
        statutbeneficiaire.setDateFinValidite(datefin);
        em.merge(statutbeneficiaire);
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
        String tx = "SELECT stb.leContrat FROM StatutBeneficiaire AS stb where stb.laPersonnePhysique=:pers";// and stb.laBeneficiaire.LibelleBeneficiaire=:af"; 
        Query req = getEntityManager().createQuery(tx); 
        req.setParameter("pers", persphy);
        //req.setParameter("af", "Affilie");
        listcontrats= req.getResultList (); 
        return listcontrats;
    }
    
    @Override
    public List<StatutBeneficiaire> rechercherStatutBeneficiaire(PersonneMorale persmo) {
        String txt = "SELECT s FROM StatutBeneficiaire AS s WHERE s.leContrat.leProduit.laPersonneMorale=:pm "
                + "order by s.leContrat.leProduit.laPersonneMorale, s.leContrat.leProduit, s.leContrat, s.laBeneficiaire";
        Query req = getEntityManager().createQuery(txt); 
        req = req.setParameter("pm",persmo);
        List<StatutBeneficiaire> result = req.getResultList();
        return result;
    }
    
    @Override
    public List<StatutBeneficiaire> rechercherStatutBeneficiaire(PersonnePhysique persph) {
        String txt = "SELECT s FROM StatutBeneficiaire AS s WHERE s.laPersonnePhysique=:pp or "
                + "s.leContrat in (SELECT s.leContrat from StatutBeneficiaire AS s "
                + "where s.laPersonnePhysique=:pp and s.laBeneficiaire.LibelleBeneficiaire='Affilie') "
                + "order by s.leContrat.leProduit, s.leContrat, s.laBeneficiaire";
        Query req = getEntityManager().createQuery(txt); 
        req = req.setParameter("pp",persph);
        List<StatutBeneficiaire> result = req.getResultList();
        return result;
    }

    @Override
    public StatutBeneficiaire rechercheAffilieDomaine(PersonnePhysique persph, Domaine domaine) {
        StatutBeneficiaire statut = null;
        String txt = "SELECT s FROM StatutBeneficiaire AS s WHERE s.laPersonnePhysique=:pp "
                + "and s.leContrat.leProduit.leDomaine=:dom and s.laBeneficiaire.LibelleBeneficiaire='Affilie'";
        Query req = getEntityManager().createQuery(txt); 
        req = req.setParameter("pp",persph);
        req = req.setParameter("dom", domaine);
        List<StatutBeneficiaire> result = req.getResultList();
        if(result.size()==1){
            statut = (StatutBeneficiaire)result.get(0);
        }
        return statut;
    }
    
    @Override
    public List<Contrat> AfficherContratGestionnaire(Domaine dom, String ReSS, int page) {
        List<Contrat> listcontrats; 
        ReSS="%"+ReSS+"%";
        String tx = "SELECT distinct (sb.leContrat) FROM StatutBeneficiaire AS sb "
                + "where sb.leContrat.leProduit.leDomaine=:dom "
                + "and sb.leContrat.DateDebut IS NULL "
                + "and sb.laBeneficiaire.LibelleBeneficiaire=Affilie "
                + "and sb.laPersonnePhysique.NumeroSS like :ReSS"; 
        Query req = getEntityManager().createQuery(tx); 
        req.setParameter("dom", dom); 
        req.setParameter("ReSS", ReSS);
        req.setFirstResult(20*(page-1));
        req.setMaxResults(20);
        listcontrats= req.getResultList (); 
        return listcontrats;
    }
//    @Override
//    public long CompterActesNonRembourse(String ReSS) {
//        long taille;
//        ReSS="%"+ReSS+"%";
//        String tx = "SELECT count(act) FROM Acte AS act "
//                + "left join act.leRemboursement rem "
//                + "where rem is null "
//                + "and act.laPersonnePhysique.NumeroSS like :ReSS "; //==null 
//        Query req = getEntityManager().createQuery(tx);
//        req.setParameter("ReSS", ReSS); 
//        taille= (long)req.getResultList().get(0);
//        return taille;
//    }
    @Override
    public long CompterContratGestionnaire(Domaine dom, String ReSS) {
        ReSS="%"+ReSS+"%";
        String tx = "SELECT count(distinct (sb.leContrat)) FROM StatutBeneficiaire AS sb "
                + "where sb.leContrat.leProduit.leDomaine=:dom "
                + "and sb.leContrat.DateDebut IS NULL "
                + "and sb.laBeneficiaire.LibelleBeneficiaire=Affilie "
                + "and sb.laPersonnePhysique.NumeroSS like :ReSS"; 
        Query req = getEntityManager().createQuery(tx); 
        req.setParameter("dom", dom); 
        req.setParameter("ReSS", ReSS);
//        listcontrats= req.getResultList (); 
        long taille= (long)req.getResultList().get(0);
        return taille;
    }
    
    @Override
    public List<Contrat> AfficherContratCree(Domaine dom, String ReSS, int page) {
        List<Contrat> listcontrats; 
        ReSS="%"+ReSS+"%";
        System.out.println("ss : "+ReSS);
        String tx = "SELECT distinct (sb.leContrat) FROM StatutBeneficiaire AS sb "
                + "where sb.leContrat.leProduit.leDomaine=:dom "
                + "and sb.leContrat.leStatut=:cr "
                + "and sb.laBeneficiaire.LibelleBeneficiaire='Affilie' "
                + "and sb.laPersonnePhysique.NumeroSS like :ReSS";
        Query req = getEntityManager().createQuery(tx); 
        req.setParameter("dom", dom); 
        req.setParameter("cr", StatutContrat.Créé); 
        req.setParameter("ReSS", ReSS);
        req.setFirstResult(20*(page-1));
        req.setMaxResults(20);
        listcontrats= req.getResultList (); 
        return listcontrats;
    }
    
    @Override
    public long CompterContratCree(Domaine dom, String ReSS) {
        ReSS="%"+ReSS+"%";
        String tx = "SELECT count(distinct (sb.leContrat)) FROM StatutBeneficiaire AS sb "
                + "where sb.leContrat.leProduit.leDomaine=:dom "
                + "and sb.leContrat.leStatut=:cr "
                + "and sb.laBeneficiaire.LibelleBeneficiaire='Affilie' "
                + "and sb.laPersonnePhysique.NumeroSS like :ReSS";
        Query req = getEntityManager().createQuery(tx); 
        req.setParameter("dom", dom); 
        req.setParameter("cr", StatutContrat.Créé); 
        req.setParameter("ReSS", ReSS);
        long taille= (long)req.getResultList().get(0);
        return taille;
    }
    
    @Override
    public long CompterContratValide(Domaine dom, String ReSS) {
        ReSS="%"+ReSS+"%";
        String tx = "SELECT count(distinct (sb.leContrat)) FROM StatutBeneficiaire AS sb "
                + "where sb.leContrat.leProduit.leDomaine=:dom "
                + "and sb.leContrat.leStatut=:va "
                + "and sb.laBeneficiaire.LibelleBeneficiaire='Affilie' "
                + "and sb.laPersonnePhysique.NumeroSS like :ReSS"; 
        Query req = getEntityManager().createQuery(tx); 
        req.setParameter("dom", dom); 
        req.setParameter("va", StatutContrat.Validé); 
        req.setParameter("ReSS", ReSS);
        long taille= (long)req.getResultList().get(0);
        return taille;
    }
    
    @Override
    public List<Contrat> AfficherContratValide(Domaine dom, String ReSS, int page) {
        List<Contrat> listcontrats; 
        ReSS="%"+ReSS+"%";
        String tx = "SELECT distinct (sb.leContrat) FROM StatutBeneficiaire AS sb "
                + "where sb.leContrat.leProduit.leDomaine=:dom "
                + "and sb.leContrat.leStatut=:va "
                + "and sb.laBeneficiaire.LibelleBeneficiaire='Affilie' "
                + "and sb.laPersonnePhysique.NumeroSS like :ReSS"; 
        Query req = getEntityManager().createQuery(tx); 
        req.setParameter("dom", dom); 
        req.setParameter("va", StatutContrat.Validé); 
        req.setParameter("ReSS", ReSS);
        req.setFirstResult(20*(page-1));
        req.setMaxResults(20);
        listcontrats= req.getResultList (); 
        return listcontrats;
    }
    
    @Override
    public List<PersonnePhysique> AfficherPersonnesPhysiques(String ReSS, int page){
        List<PersonnePhysique> listpers; 
        ReSS="%"+ReSS+"%";
        String tx = "SELECT sb.laPersonnePhysique FROM StatutBeneficiaire AS sb "
                + "where sb.laPersonnePhysique.NumeroSS like :ReSS "
                + "and sb.laBeneficiaire.LibelleBeneficiaire='Affilie'"; //==null 
        Query req = getEntityManager().createQuery(tx); 
        req.setParameter("ReSS", ReSS); 
        req.setFirstResult(20*(page-1));
        req.setMaxResults(20);
        listpers= req.getResultList (); 
        return listpers;
    }
    
    @Override
    public long CompterPersonnesPhysiques(String ReSS) {
        long taille;
        ReSS="%"+ReSS+"%";
        String tx = "SELECT count(sb.laPersonnePhysique) FROM StatutBeneficiaire AS sb "
                + "where sb.laPersonnePhysique.NumeroSS like :ReSS "
                + "and sb.laBeneficiaire.LibelleBeneficiaire='Affilie'"; //==null 
        Query req = getEntityManager().createQuery(tx);
        req.setParameter("ReSS", ReSS); 
        taille= (long)req.getResultList().get(0);
        return taille;
    }
    
}
