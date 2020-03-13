/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package session;

import entitee.Beneficiaire;
import entitee.Contrat;
import entitee.Devis;
import entitee.Genre;
import entitee.PersonnePhysique;
import entitee.Population;
import entitee.Produit;
import entitee.Remboursement;
import entitee.StatutBeneficiaire;
import facade.ActeFacadeLocal;
import facade.ContratFacadeLocal;
import facade.DevisFacadeLocal;
import facade.PersonnePhysiqueFacadeLocal;
import facade.StatutBeneficiaireFacadeLocal;
import java.util.List;
import java.util.Date;
import java.util.List;
import javax.ejb.EJB;
import javax.ejb.Stateless;

/**
 *
 * @author lixin
 */
@Stateless
public class AffilieSession implements AffilieSessionLocal {

    @EJB
    private ActeFacadeLocal acteFacade;

    @EJB
    private ContratFacadeLocal contratFacade;

    @EJB
    private DevisFacadeLocal devisFacade;

    @EJB
    private PersonnePhysiqueFacadeLocal personnePhysiqueFacade;
    
    @EJB
    private StatutBeneficiaireFacadeLocal statutBeneficiaireFacade;
    
    
    
    
 
    

    @Override
    public PersonnePhysique authentificationAffilie(String login, String mdp) {
        return personnePhysiqueFacade.authentificationAffilie(login, mdp); 
    }

    @Override
    public PersonnePhysique modifierMDP(String nvMDP, PersonnePhysique pers) {
        return personnePhysiqueFacade.modifierMdp(nvMDP, pers);
    }

    @Override
    public PersonnePhysique modifierMail(String nvMail, PersonnePhysique pers) {
        return personnePhysiqueFacade.modifierMail(pers, nvMail);
    }

    @Override
    public PersonnePhysique modifierAdresse(String nvAdresse, PersonnePhysique pers) {
        return personnePhysiqueFacade.modifierAdresse(pers, nvAdresse);
    }

    @Override
    public Devis creerDevis(PersonnePhysique pers,double prix,Produit prod,Date dateDevis,List<PersonnePhysique>listeAyantdroits) {
        return devisFacade.creerDevis(pers, prod, prix, dateDevis,listeAyantdroits);
    }
   

    @Override
    public PersonnePhysique creerPersonnePhysiqueDevis(String nom, String prenom, String mail, String numeroSS, Date datenaiss, Population laPopulation) {
        return personnePhysiqueFacade.creerPersonnePhysiqueDevis(nom, prenom, mail,numeroSS,datenaiss, laPopulation);
    }

    @Override
    public StatutBeneficiaire creerStatutBeneficiaireDevis(Date datedeb, Beneficiaire statut,PersonnePhysique pers) {
        return statutBeneficiaireFacade.creerStatutBeneficiaireDevis(datedeb, statut, pers);
    }

    @Override
    public PersonnePhysique renseignerInfos(PersonnePhysique pers, String numeroSS, String adresse, Genre genre, boolean adherent) {
        return personnePhysiqueFacade.renseignerInfos(pers, numeroSS, adresse, genre, adherent);
    }

    @Override
    public Contrat modifierDateFinContrat(Contrat contrat, Date datef) {
        return contratFacade.modifierDateFin(datef, contrat);
    }

    @Override
    public List<Contrat> rechercheContrats(PersonnePhysique pers) {
        return statutBeneficiaireFacade.rechercheContrats(pers);
    }

    @Override
    public List<Remboursement> rechercheRemboursements(PersonnePhysique pers) {
        return acteFacade.rechercheRemboursementsPersonne(pers);
    }
    
    
    
    
    
    
    
    
    

    
    
    
    
    
    
    
    
    
}
