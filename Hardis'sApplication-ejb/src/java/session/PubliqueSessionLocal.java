/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package session;

import entitee.Beneficiaire;
import entitee.Devis;
import entitee.Genre;
import entitee.PersonnePhysique;
import entitee.Population;
import entitee.Produit;
import entitee.StatutBeneficiaire;
import java.util.ArrayList;
import java.util.Date;
import javax.ejb.Local;

/**
 *
 * @author lixin
 */
@Local
public interface PubliqueSessionLocal {
    
        Devis creerDevis(double prix, Date dateDevis, ArrayList<PersonnePhysique> listpers, Produit prod);

        PersonnePhysique creerPersonnePhysiqueDevis(String nom, String prenom, String mail, Population pop);

        StatutBeneficiaire creerStatutBeneficiaireDevis(Date datedeb, Beneficiaire statut,PersonnePhysique pers);

    PersonnePhysique renseignerInfos(PersonnePhysique pers, String numeroSS, String adresse, Genre genre, boolean adherent);
    
}
