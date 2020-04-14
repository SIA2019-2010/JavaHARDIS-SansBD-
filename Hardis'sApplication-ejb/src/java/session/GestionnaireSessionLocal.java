/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package session;

import entitee.Acte;
import entitee.Activite;
import entitee.Beneficiaire;
import entitee.Contrat;
import entitee.Domaine;
import entitee.Fiscalite;
import entitee.Garantie;
import entitee.Gestionnaire;
import entitee.PersonneMorale;
import entitee.PersonnePhysique;
import entitee.Population;
import entitee.PriseEnCharge;
import entitee.Produit;
import entitee.Remboursement;
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
    
    long CompterPersonnesPhysiques(String ReSS);
    
    List<Object> modifiermdp(Gestionnaire gest, String OMDP, String NMDP, String RMDP);

    PersonneMorale creerMorale(String siret,String raisonSo,String adresse,Activite acti);

    List<Object> creerMoraleComplet(List<String> pers);

    Responsable creerResponsable(String nom, String prenom, String login, String mdp, String mail, String tel, PersonneMorale personneMorale);

    List<Object> creerResponsableComplet(List<String> pers);

    Produit creerProduit(String nom, List<Beneficiaire> lesBeneficiaires, Beneficiaire lesAssiette, List<TypeGarantie> lesTypesGaranties, TypeProduit leTypeProduit, Fiscalite laFiscalite,List<Population> lesPopulations, PersonneMorale laPersonneMorale,Domaine leDomaine);

    List<Object> creerProduitComplet(List<String> infos,List<String> lesbenefs,String leassiette,List<Long> lestypes,List<Long> lespops);
    
    List<Object> calculPacksGestionnaire(Object[] pers,List<Object[]>listeinfos);
    
    List<Object> creerDevisCompletGestionnaire(Object[] pers,Object[] pack,List<Object[]>listeinfos);

    List<Object> creerRemboursement(String ida);

    List<Acte> rechercheActesNonRemboursePersonne(Long idpers);

    List<Object> validerRemboursement(String idrv);

    List<Object> refuserRemboursement(String idrv);

    List<Object> DevisAvecRecherchePersonne(Gestionnaire gest,String idp);

    List<Object> cloturerContrat(String idc);

    List<Object> ajouterPersonneProduitCollectif(String idp,Object[] pers,List<Object[]>listeinfos);

    List<Acte> rechercheListeActesNonRembourse(int Page, String ReSS);

    List<Produit> rechercheProduitsCollectif(int page, String RePr);
    
    long CompterProduitCollectif(String RePr);
    
    List<Remboursement> afficherRempoursementEncours();
    
    void creerActivite(String n);

    List<Activite> recupererActivites();

    List<PersonneMorale> recupererPersonneMorale();
    
    List<Contrat> AfficherContratGestionnaire(Domaine dom, String ReSS, int page);
    
    List<Contrat> AfficherContratCree(Domaine dom, String ReSS, int page);
    
    List<Contrat> AfficherContratValide(Domaine dom, String ReSS, int page);

    List<Object> validerContrat(String idcontrat);

    List<Object> refuserContrat(String idc);
    
    List<PersonnePhysique> AfficherPersonnesPhysiques(String SS, int page);
    
    List<Domaine> AfficherDomaine();
    
    List<TypeProduit> AfficherTypeProduit();
    
    List<Fiscalite> AfficherFiscalite();
    
    List<TypeGarantie> AfficherTypeGarantie();
    
    long CompterActesNonRembourse(String ReSS);
    
    List<Object> rechercheProduitsCollectifID(String idprod);
    
    long CompterContratGestionnaire(Domaine dom, String ReSS);
    
    long CompterContratCree(Domaine dom, String Ress);
    
    long CompterContratValide(Domaine dom, String ReSS);
    
    List<Garantie> AfficherGarantie();
        
}
