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

/**
 *
 * @author lixin
 */
@Local
public interface AffilieSessionLocal {

    PersonnePhysique authentificationAffilie(String login, String mdp);

    PersonnePhysique modifierMDP(String nvMDP,PersonnePhysique pers);

    PersonnePhysique modifierMail(String nvMail, PersonnePhysique pers);

    PersonnePhysique modifierAdresse(String nvAdresse, PersonnePhysique pers);

    Devis creerDevis(double prix, Date dateDevis, List<PersonnePhysique> listpers, Produit prod);

    PersonnePhysique creerPersonnePhysiqueDevis(String nom, String prenom, String mail, Population pop);

    StatutBeneficiaire creerStatutBeneficiaireDevis(Date datedeb, Beneficiaire statut,PersonnePhysique pers);

    PersonnePhysique renseignerInfos(PersonnePhysique pers, String numeroSS, String adresse, Genre genre, boolean adherent);    

    Contrat modifierDateFinContrat(Contrat contrat, Date datef);

    List<Contrat> rechercheContrats(PersonnePhysique pers);

    List<Remboursement> rechercheRemboursements(PersonnePhysique pers);
}
