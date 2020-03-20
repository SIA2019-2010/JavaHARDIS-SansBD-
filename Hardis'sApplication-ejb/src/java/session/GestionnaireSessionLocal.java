/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package session;

import entitee.Acte;
import entitee.Activite;
import entitee.Beneficiaire;
import entitee.Domaine;
import entitee.Fiscalite;
import entitee.Gestionnaire;
import entitee.PersonneMorale;
import entitee.PersonnePhysique;
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

    Produit creerProduit(String nom, EnumSet<Beneficiaire> lesBeneficiaires, EnumSet<Beneficiaire> lesAssiettes, List<TypeGarantie> lesTypesGaranties, TypeProduit leTypeProduit, Fiscalite laFiscalite,List<Population> lesPopulations, PersonneMorale laPersonneMorale,Domaine leDomaine);

    List<Object> creerProduitComplet(List<String> infos,List<String> lesbenefs,List<String> lesassiettes,List<Long> lestypes,List<Long> lespops);
    
    List<Object> calculPacksGestionnaire(Object[] pers,List<Object[]>listeinfos);
    
    List<Object> creerDevisCompletGestionnaire(Object[] pers,Object[] pack,List<Object[]>listeinfos);

    List<Object> creerRemboursement(Long idpers);

    List<Acte> rechercheActesNonRemboursePersonne(Long idpers);

    List<Object> validerRemboursement(Long idremb);

    List<Object> refuserRemboursement(Long idremb);

    List<Object> DevisAvecRecherchePersonne(Gestionnaire gest,Long idpers);

    List<Object> cloturerContrat(Long idcontrat);

    List<Object> ajouterPersonneProduit(Long idproduit,Object[] pers,List<Object[]>listeinfos);

    List<Acte> rechercheListeActesNonRembourse();

    List<Produit> rechercheProduitsCollectif();
}
