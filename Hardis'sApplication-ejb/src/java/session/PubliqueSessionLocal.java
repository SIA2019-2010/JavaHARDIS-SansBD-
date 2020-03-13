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
    
        //Devis creerDevis(double prix, Date dateDevis, List<PersonnePhysique> listpers, Produit prod);

        //PersonnePhysique creerPersonnePhysiqueDevis(String nom, String prenom, String mail, Population pop);

        StatutBeneficiaire creerStatutBeneficiaireDevis(Date datedeb, Beneficiaire statut,PersonnePhysique pers);

    PersonnePhysique renseignerInfos(PersonnePhysique pers, String numeroSS, String adresse, Genre genre, boolean adherent);
    
    List<Object> rechercherConnexion(HttpSession session, Gestionnaire sessiongestionnaire, PersonnePhysique sessionaffilie, Responsable sessionresponsable, boolean sessionpublique);

    List<Object> calculPacks(Object[] pers,List<Object>listeinfos,Date datedeb);
    
    List<Object> creerDevisComplet(double prix,Object[] pers,List<Object>listeinfos);
    
}
