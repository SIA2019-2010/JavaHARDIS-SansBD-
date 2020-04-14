/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package session;

import entitee.Beneficiaire;
import entitee.Contrat;
import entitee.Devis;
import entitee.Domaine;
import entitee.Garantie;
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

    List<Object> modifiermdp(PersonnePhysique affilie, String OMDP, String NMDP, String RMDP);

    String modifierMail(String nvMail, PersonnePhysique pers);

    String modifierAdresse(String nvAdresse, PersonnePhysique pers);

    Devis creerDevis(PersonnePhysique pers,double prix, Produit prod,Date date, List<PersonnePhysique> listpers );

    PersonnePhysique creerPersonnePhysiqueDevis(String nom, String prenom, String mail,String numeroSS,Date datenaiss, Population pop);

    StatutBeneficiaire creerStatutBeneficiaireDevis(Date datedeb, Beneficiaire statut,PersonnePhysique pers);

    PersonnePhysique renseignerInfos(PersonnePhysique pers, String adresse, Genre genre,String RIB);    

    Contrat modifierDateFinContrat(Contrat contrat, Date datef);

    List<Contrat> rechercheContrats(PersonnePhysique pers);

    List<Remboursement> rechercheRemboursements(PersonnePhysique pers);
    
    List<Object> calculPacksAffilie(PersonnePhysique pers,List<Object[]>listeinfos);
    
    List<Object> creerDevisCompletAffilie(PersonnePhysique pers,Object[] pack,List<Object[]>listeinfos);
    
    List<Remboursement> afficherRempoursementPers(PersonnePhysique perso);
    
    List<StatutBeneficiaire> rechercherStatutBeneficiaire(PersonnePhysique persph);

    List<Object> afficherGarantie(Long idcont);

    Garantie recupererGarantieChoisie(Long idgar);
    
    
    
}
