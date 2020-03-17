/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package session;

import entitee.Activite;
import entitee.Beneficiaire;
import entitee.Fiscalite;
import entitee.Gestionnaire;
import entitee.PersonneMorale;
import entitee.Population;
import entitee.Produit;
import entitee.Responsable;
import entitee.TypeGarantie;
import entitee.TypeProduit;
import java.util.EnumSet;
import java.util.List;
import javax.ejb.Local;
import javax.servlet.http.HttpServletRequest;

/**
 *
 * @author lixin
 */
@Local
public interface GestionnaireSessionLocal {
    
    List<Object> authentificationGestionnaire(String login, String mdp, HttpServletRequest request);
    
    Gestionnaire modifiermdp(Gestionnaire resp, String mdp);

    PersonneMorale creerMorale(String siret,String raisonSo,String adresse,Activite acti);

    List<Object> creerMoraleComplet(List<String> pers);

    Responsable creerResponsable(String nom, String prenom, String login, String mdp, String mail, String tel, PersonneMorale personneMorale);

    List<Object> creerResponsableComplet(List<String> pers);

    Produit creerProduit(String nom, EnumSet<Beneficiaire> lesBeneficiaires, EnumSet<Beneficiaire> lesAssiettes, List<TypeGarantie> lesTypesGaranties, TypeProduit leTypeProduit, List<Fiscalite> lesFiscalites,List<Population> lesPopulations, PersonneMorale laPersonneMorale);

    List<Object> creerProduitComplet(List<String> infos,List<String> lesbenefs,List<String> lesassiettes,List<Long> lestypes,List<Long> lesfiscas,List<Long> lespops);
    
    
}
