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
import java.util.List;
import java.util.Date;
import java.util.List;
import javax.ejb.Local;
import javax.servlet.http.HttpServletRequest;

/**
 *
 * @author lixin
 */
@Local
public interface AffilieSessionLocal {

    List<Object> authentificationAffilie(String login, String mdp, HttpServletRequest request);

    PersonnePhysique modifierMDP(String nvMDP,PersonnePhysique pers);

    PersonnePhysique modifierMail(String nvMail, PersonnePhysique pers);

    PersonnePhysique modifierAdresse(String nvAdresse, PersonnePhysique pers);

    Devis creerDevis(PersonnePhysique pers,double prix, Produit prod,Date date, List<PersonnePhysique> listpers );

    PersonnePhysique creerPersonnePhysiqueDevis(String nom, String prenom, String mail,String numeroSS,Date datenaiss, Population pop);

    StatutBeneficiaire creerStatutBeneficiaireDevis(Date datedeb, Beneficiaire statut,PersonnePhysique pers);

    PersonnePhysique renseignerInfos(PersonnePhysique pers, String adresse, Genre genre);    

    Contrat modifierDateFinContrat(Contrat contrat, Date datef);

    List<Contrat> rechercheContrats(PersonnePhysique pers);

    List<Remboursement> rechercheRemboursements(PersonnePhysique pers);
    
    List<Object> calculPacksAffilie(PersonnePhysique pers,List<Object[]>listeinfos);
    
    List<Object> creerDevisCompletAffilie(PersonnePhysique pers,Object[] pack,List<Object[]>listeinfos);
}
