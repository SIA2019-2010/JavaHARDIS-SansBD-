/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package session;

import entitee.Beneficiaire;
import entitee.Devis;
import entitee.Genre;
import entitee.Gestionnaire;
import entitee.PersonnePhysique;
import entitee.Population;
import entitee.Produit;
import entitee.Responsable;
import entitee.StatutBeneficiaire;
import entitee.Population;
import java.util.List;
import java.util.Date;
import javax.ejb.Local;
import javax.servlet.http.HttpSession;

/**
 *
 * @author lixin
 */
@Local
public interface PubliqueSessionLocal {
    
        Devis creerDevis(PersonnePhysique pers,Produit prod,double prix, Date dateDevis,List<PersonnePhysique> listeayantdroit);

        PersonnePhysique creerPersonnePhysiqueDevis(String nom, String prenom, String mail,String numeroSS,Date datenaiss, Population pop);

        StatutBeneficiaire creerStatutBeneficiaireDevis(Date datedeb, Beneficiaire statut,PersonnePhysique pers);

    PersonnePhysique renseignerInfos(PersonnePhysique pers, String adresse, Genre genre);
    
    List<Object> rechercherConnexion(HttpSession session, Gestionnaire sessiongestionnaire, PersonnePhysique sessionaffilie, Responsable sessionresponsable);

    List<Object> calculPacks(Object[] pers,List<Object[]>listeinfos);
    
    List<Object> creerDevisComplet(Object[] pers,Object[] pack,List<Object[]>listeinfos);

    List<Population> recherchePopulations();

    PersonnePhysique renseignerLoginMDP(PersonnePhysique pers, String login, String mdp);

    List<Object> validerDevis(Long iddevis,Object[] pack,List<Object[]>listeinfos);

    List<Object> recupererInfosDevis(Long iddev);
    
    List<Object> VerifierDevisID(String iddevis);
    
    List<Beneficiaire> rechercheBeneficiaires();
    
}
